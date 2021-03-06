package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static model.InputChecker.*;
import dao.UserDAO;
import model.ErrorViewData;
import model.InputCheckException;

@WebServlet("/AlterPassword")
public class AlterPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlterPassword() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean checked = (boolean) session.getAttribute("checked");
		if (checked) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/alterPassword.jsp");
			dispatcher.forward(request, response);
		} else {
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("不正なアクセスを検知しました", "入力画面に戻る", "/ActionLogger/");
			request.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("loginUser_id");
			String pass = request.getParameter("password");
			String rePass = request.getParameter("re_password");
			if(!checkPassword(pass) || !checkPassword(rePass)) {
				session.setAttribute("rewright", "パスワードは、3〜30文字で入力して下さい");
				response.sendRedirect("/ActionLogger/AlterPassword");
			}
			else if (!pass.equals(rePass)) {
				// パスと確認パスが間違っていたら、入力させなおす
				session.setAttribute("rewright", "確認用パスワードが違います");
				response.sendRedirect("/ActionLogger/AlterPassword");
			} else {
				UserDAO udao = new UserDAO();

				// パスワードのハッシュ化
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				digest.reset();
				digest.update(pass.getBytes("utf8"));
				String pwdhash = String.format("%064x", new BigInteger(1, digest.digest()));
				session.removeAttribute("rewright");
				udao.updatePass(user_id, pwdhash);
				session.setAttribute("checked", false);
				response.sendRedirect("/ActionLogger");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("問題が発生しました。", "トップに戻る", "/ActionLogger/");
			request.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher3 = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher3.forward(request, response);
			return;
		}
	}

}
