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
import dao.Info_actDAO;
import model.ErrorViewData;
import model.GroupMgt;
import model.InformationAction;

/**
 * Servlet implementation class CreateGroupConfirm
 */
@WebServlet("/CreateGroupConfirm")
public class CreateGroupConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateGroupConfirm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 表示データを用意する
		ErrorViewData errorData = new ErrorViewData("アクセス出来ませんでした。", "トップに戻る", "/ActionLogger/Main");
		request.setAttribute("errorData", errorData);
		// エラー表示にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

//		// フォームから送られた確認キーが保存したものと一致するか確認
//		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
//		if (!request.getParameter("vKey").equals(validationKey.getValue())) {
//			 // 一致しなかったので、セッションスコープに保存したキーを破棄し、エラーページに
//			 session.removeAttribute("validationKey");
//			//表示データを用意する
//				ErrorViewData errorData = new ErrorViewData("問題が発生しました。",
//														"トップに戻る","/ActionLogger/Main");
//				request.setAttribute("errorData", errorData);
//			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
//			 dispatcher.forward(request, response);
//			 return;
//		}

		GroupMgt group = (GroupMgt) session.getAttribute("group");
		GroupDAO gdao = new GroupDAO();
		gdao.save(group);
		session.removeAttribute("group");// セッションスコープの除去
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);
	}

}
