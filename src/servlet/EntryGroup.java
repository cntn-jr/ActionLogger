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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		EntryGrp entry = new EntryGrp();
		entryDAO etDAO = new entryDAO();
		GroupDAO gdao = new GroupDAO();
		String user_id = (String) session.getAttribute("loginUser_id");
		String group_id = request.getParameter("group_id");

		// 時間があれば、組み込む
//		if(!etDAO.confirmAdimnGroup(user_id, request.getParameter("group_id"))) {
//			//参加するグループを既に管理していなければ、...
//			//情報のセット
//			entry.setGroup_id(request.getParameter("group_id"));
//			entry.setUser_id(user_id);
//			
//			//データベースに保存
//			etDAO.save(entry);
//		}else {
//			String view = "entryGroup";
//			request.setAttribute("view", view);
//		}

		// 参加しようとするグループが存在しているかつ、すでに参加してないか確認
		if (gdao.isGroup(group_id) && etDAO.ableEntry(user_id, group_id)) {

			// 情報のセット
			entry.setGroup_id(group_id);
			entry.setUser_id(user_id);

			// データベースに保存
			etDAO.save(entry);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else { // グループにアクセスする権限がない
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("現在参加しようとしているグループは存在しないまたは、既に参加しています。", "トップに戻る", "/ActionLogger/Main");
			request.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

	}

}
