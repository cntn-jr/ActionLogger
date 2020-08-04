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

import dao.UserDAO;
import model.ErrorViewData;

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
			checked = false;
			session.setAttribute("checked",checked);
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
			if (!pass.equals(rePass)) {
				// パスと確認パスが間違っていたら、入力させなおす
				response.sendRedirect("/ActionLogger/AlterPassword");

			}
			UserDAO udao = new UserDAO();

			// パスワードのハッシュ化
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(pass.getBytes("utf8"));
			String pwdhash = String.format("%064x", new BigInteger(1, digest.digest()));

			udao.updatePass(user_id, pwdhash);
			response.sendRedirect("/ActionLogger");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
