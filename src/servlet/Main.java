package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupDAO;
import dao.Info_actDAO;
import dao.entryDAO;
import model.ErrorViewData;
import model.GroupMgt;
import model.InformationAction;

@WebServlet("/")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Main() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute("newUser");//サインアップの時のセッションスコープの除去

		String user_id = (String) session.getAttribute("loginUser_id");
		String mgtGroup = (String) request.getParameter("mgtGroup");
		String view = (String) request.getParameter("view");
		String search = (String) request.getParameter("search");

		Info_actDAO infoDAO = new Info_actDAO();
		GroupDAO gdao = new GroupDAO();
		entryDAO entDAO = new entryDAO();

//		//プロフィールやパスワード変更をするときの、パスワード確認をしたかどうか
//		boolean checked = false;
//		session.setAttribute("checked", checked);

		// 新規5件の行動履歴を取得
		List<InformationAction> easyLogList = new ArrayList<>();
		easyLogList = infoDAO.getLimit(user_id);
		session.setAttribute("easyLogList", easyLogList);

		// 自分の行動履歴を取得
		List<InformationAction> allLogList = new ArrayList<>();
		allLogList = infoDAO.getAll(user_id);
		if (search != null) {
			String searchDate = (String) request.getParameter("searchDate");
			String searchPlace = (String) request.getParameter("searchPlace");
			allLogList = infoDAO.getSelfConditional(user_id, searchDate, searchPlace);
		}
		session.setAttribute("allLogList", allLogList);

		// 管理グループのリストを取得
		List<String[]> groupList = new ArrayList<>();
		groupList = gdao.getGroupList(user_id);
		session.setAttribute("groupList", groupList);

		// 参加グループのリストを取得
		List<String> entryNameList = new ArrayList<>();
		entryNameList = entDAO.getEntryGroupNameList(user_id);
		session.setAttribute("entryNameList", entryNameList);

		if (mgtGroup != null) {// 選択した管理グループの行動履歴の取得
			if (gdao.isAdmin(mgtGroup, user_id)) {// グループ管理者かどうか判定
				List<InformationAction> participantLogList = new ArrayList<>();
				participantLogList = infoDAO.getParticipantLog(mgtGroup);
				if (search != null) {// 絞り込み検索があれば、
					String searchDate = (String) request.getParameter("searchDate");
					String searchPlace = (String) request.getParameter("searchPlace");
					participantLogList = infoDAO.getParticipantConditional(mgtGroup, searchDate, searchPlace);
				}
				request.setAttribute("mgtGroup", mgtGroup);
				session.setAttribute("participantLogList", participantLogList);
			} else { // グループにアクセスする権限がない
				// 表示データを用意する
				ErrorViewData errorData = new ErrorViewData("アクセス権限がありません。", "トップに戻る", "/ActionLogger/Main");
				request.setAttribute("errorData", errorData);
				// エラー表示にフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}

		if (user_id == null) {
			// ログインさせる
			response.sendRedirect("/ActionLogger/Login");

		} else {
			// MainViewに飛ぶ
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}