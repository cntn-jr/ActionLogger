package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupDAO;
import dao.entryDAO;
import model.EntryGrp;
import model.ErrorViewData;

@WebServlet("/EntryGroup")
public class EntryGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EntryGroup() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/ActionLooger");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		EntryGrp entry = new EntryGrp();
		entryDAO etdao = new entryDAO();
		GroupDAO gdao = new GroupDAO();
		String user_id = (String) session.getAttribute("loginUser_id");
		String group_id = request.getParameter("group_id");

		// 参加しようとするグループが存在しているか、確認
		if (gdao.isGroup(group_id)) {

			// グループに現在、参加しているか確認
			if (etdao.alreadyEntry(user_id, group_id)) {

				// 既に参加しているので、退会させる
				etdao.leave(user_id, group_id);
			} else {

				// 既にデータベースに存在している確認
				if (etdao.isData(user_id, group_id)) {

					// 存在していれば、
					etdao.re_entry(user_id, group_id);
				} else {

					// 情報のセット
					entry.setGroup_id(group_id);
					entry.setUser_id(user_id);
					// データベースに保存
					etdao.save(entry);
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);

		} else { // グループがない
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("現在参加しようとしているグループは存在しません。", "トップに戻る", "/ActionLogger/Main");
			request.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

	}

}
