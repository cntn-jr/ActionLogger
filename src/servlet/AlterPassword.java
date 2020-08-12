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
			session.setAttribute("checked",false);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/alterPassword.jsp");
			dispatcher.forward(request, response);
		} else {
			// �\���f�[�^��p�ӂ���
			ErrorViewData errorData = new ErrorViewData("�s���ȃA�N�Z�X�����m���܂���", "���͉�ʂɖ߂�", "/ActionLogger/");
			request.setAttribute("errorData", errorData);
			// �G���[�\���Ƀt�H���[�h
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
				// �p�X�Ɗm�F�p�X���Ԉ���Ă�����A���͂����Ȃ���
				response.sendRedirect("/ActionLogger/AlterPassword");

			} else {
				UserDAO udao = new UserDAO();

				// �p�X���[�h�̃n�b�V����
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				digest.reset();
				digest.update(pass.getBytes("utf8"));
				String pwdhash = String.format("%064x", new BigInteger(1, digest.digest()));

				udao.updatePass(user_id, pwdhash);
				response.sendRedirect("/ActionLogger");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
