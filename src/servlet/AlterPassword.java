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
			if(!checkPassword(pass) || !checkPassword(rePass)) {
				session.setAttribute("rewright", "�p�X���[�h�́A3�`30�����œ��͂��ĉ�����");
				response.sendRedirect("/ActionLogger/AlterPassword");
			}
			else if (!pass.equals(rePass)) {
				// �p�X�Ɗm�F�p�X���Ԉ���Ă�����A���͂����Ȃ���
				session.setAttribute("rewright", "�m�F�p�p�X���[�h���Ⴂ�܂�");
				response.sendRedirect("/ActionLogger/AlterPassword");
			} else {
				UserDAO udao = new UserDAO();

				// �p�X���[�h�̃n�b�V����
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
			// �\���f�[�^��p�ӂ���
			ErrorViewData errorData = new ErrorViewData("��肪�������܂����B", "�g�b�v�ɖ߂�", "/ActionLogger/");
			request.setAttribute("errorData", errorData);
			// �G���[�\���Ƀt�H���[�h
			RequestDispatcher dispatcher3 = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher3.forward(request, response);
			return;
		}
	}

}
