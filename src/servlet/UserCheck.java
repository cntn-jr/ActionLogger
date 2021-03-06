package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.ErrorViewData;
import model.InputCheckException;

import static model.InputChecker.*;
import model.LoginLogic;
import model.User;

@WebServlet("/UserCheck")
public class UserCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserCheck() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String alter = request.getParameter("alter");
		HttpSession session = request.getSession();
		boolean checked = (boolean) session.getAttribute("checked");
		if (checked) {
			switch (alter) {
			case "userInfo":
				session.removeAttribute("alter");
				response.sendRedirect("/ActionLogger/AlterUserInfo");
				break;
			case "password":
				session.removeAttribute("alter");
				response.sendRedirect("/ActionLogger/AlterPassword");
				break;
			default:
				session.setAttribute("alter", null);
				// 表示データを用意する
				ErrorViewData errorData = new ErrorViewData("問題が発生しました。", "トップに戻る", "/ActionLogger/Main");
				request.setAttribute("errorData", errorData);
				// エラー表示にフォワード
				RequestDispatcher dispatcher3 = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher3.forward(request, response);
				return;
			}
		} else {
			session.setAttribute("alter", alter);
			// パスワード確認画面に飛ぶ
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userCheck.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("loginUser_id");
		try {
			String password = checkLongInput(request.getParameter("password"));
			// 何を変更するか識別する
			String alter = (String) session.getAttribute("alter");
			LoginLogic lg = new LoginLogic();
			boolean checked;
			if (lg.userCheck(user_id, password)) {
				switch (alter) {
				case "userInfo":
					checked = true;
					session.setAttribute("checked", checked);
					session.removeAttribute("alter");
					response.sendRedirect("/ActionLogger/AlterUserInfo");
					break;
				case "password":
					session.removeAttribute("alter");
					checked = true;
					session.setAttribute("checked", checked);
					response.sendRedirect("/ActionLogger/AlterPassword");
					break;
				default:
					session.setAttribute("alter", null);
					// 表示データを用意する
					ErrorViewData errorData = new ErrorViewData("問題が発生しました。", "トップに戻る", "/ActionLogger/");
					request.setAttribute("errorData", errorData);
					// エラー表示にフォワード
					RequestDispatcher dispatcher3 = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
					dispatcher3.forward(request, response);
					return;
				}
			} else {
				// パスワードが間違っていたり、不正にアクセスされたら
				request.setAttribute("rewright", "パスワードが違います");
				RequestDispatcher dispatcher4 = request.getRequestDispatcher("/WEB-INF/jsp/userCheck.jsp");
				dispatcher4.forward(request, response);
			}
		} catch (NullPointerException e) {
			session.setAttribute("alter", null);
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("問題が発生しました。", "トップに戻る", "/ActionLogger/");
			request.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher3 = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher3.forward(request, response);
			return;
		}catch(InputCheckException e) {
			session.setAttribute("alter", null);
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("入力された値に問題がありました。", "トップに戻る", "/ActionLogger/");
			request.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher3 = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher3.forward(request, response);
			return;
		}
	}

}
