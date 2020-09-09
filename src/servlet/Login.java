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
		// �Z�b�V�����X�R�[�v����p�����[�^���擾
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("/ActionLogger");// ���O�C���ς�
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �p�����[�^���擾
		request.setCharacterEncoding("UTF-8");
		try {
			String user_id = checkLongInput(request.getParameter("user_id"));
			String password = checkLongInput(request.getParameter("password"));
			HttpSession session = request.getSession();

			User loginUser = new User(user_id);

			LoginLogic loginRogic = new LoginLogic();
			UserDAO udao = new UserDAO();

			//���[�U�����݂��Ă��
			if (udao.isUser(user_id)) {
				// �p�X���[�h�����������
				if (loginRogic.loginLogic(user_id, password)) {
					loginUser = udao.getInfo(user_id);
					session.setAttribute("loginUser_id", loginUser.getUser_id());
					session.setAttribute("user", loginUser);
					// �v���t�B�[����p�X���[�h�ύX������Ƃ��́A�p�X���[�h�m�F���������ǂ���
					boolean checked = false;
					session.setAttribute("checked", checked);
					session.setAttribute("alter", null);
					response.sendRedirect("/ActionLogger/");
					return;
				}
			}
			request.setAttribute("rewright", "���[�UID�܂��̓p�X���[�h���Ⴂ�܂�");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (InputCheckException e) {
			// �\���f�[�^��p�ӂ���
			ErrorViewData errorData = new ErrorViewData("�t�H�[���ɓ��͂��ꂽ���e�ɖ�肪����܂����B", "���͉�ʂɖ߂�", "/ActionLogger/");
			request.setAttribute("errorData", errorData);
			// �G���[�\���Ƀt�H���[�h
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}

	}
}