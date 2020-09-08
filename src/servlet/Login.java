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
import model.LoginLogic;
import static model.InputChecker.*;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションスコープからパラメータを取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("/ActionLogger");// ログイン済み
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// パラメータを取得
		request.setCharacterEncoding("UTF-8");
		try {
			String user_id = checkLongInput(request.getParameter("user_id"));
			String password = checkLongInput(request.getParameter("password"));
			HttpSession session = request.getSession();

			User loginUser = new User(user_id);

			LoginLogic loginRogic = new LoginLogic();
			Boolean canLogin = loginRogic.loginLogic(user_id, password);// userのパスワードが正しいかチェック

			if (canLogin == true) {// パスワードが正しければ
				UserDAO udao = new UserDAO();
				loginUser = udao.getInfo(user_id);
				session.setAttribute("loginUser_id", loginUser.getUser_id());
				session.setAttribute("user", loginUser);
				// プロフィールやパスワード変更をするときの、パスワード確認をしたかどうか
				boolean checked = false;
				session.setAttribute("checked", checked);
				session.setAttribute("alter", null);
				response.sendRedirect("/ActionLogger/");
			} else {
				response.sendRedirect("/ActionLogger/Login");// もう一度ログイン画面へ
				return;
			}
		} catch (InputCheckException e) {
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("フォームに入力された内容に問題がありました。", "入力画面に戻る", "/ActionLogger/");
			request.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}

	}
}