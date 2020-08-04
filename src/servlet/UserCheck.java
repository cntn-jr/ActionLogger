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
		session.setAttribute("alter", alter);
		// �p�X���[�h�m�F��ʂɔ��
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userCheck.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("loginUser_id");
		// ����ύX���邩���ʂ���
		String alter = (String) session.getAttribute("alter");
		LoginLogic lg = new LoginLogic();
		if (lg.userCheck(user_id, password)) {
			switch (alter) {
			case "userInfo":
				UserDAO udao = new UserDAO();
				User user = udao.get(user_id);
				session.setAttribute("user", user);
				session.removeAttribute("alter");
				response.sendRedirect("/ActionLogger/AlterUserInfo");
				break;
			case "password":
				session.removeAttribute("alter");
				response.sendRedirect("/ActionLogger/AlterPassword");
				break;
			default:
				session.setAttribute("alter", null);
				//�\���f�[�^��p�ӂ���
				ErrorViewData errorData = new ErrorViewData("��肪�������܂����B","�g�b�v�ɖ߂�","/ActionLogger/Main");
				request.setAttribute("errorData", errorData);
				//�G���[�\���Ƀt�H���[�h
				RequestDispatcher dispatcher3 = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher3.forward(request, response);
				return;
			}
		}else {
			//�p�X���[�h���Ԉ���Ă�����A�s���ɃA�N�Z�X���ꂽ��
			RequestDispatcher dispatcher4 = request.getRequestDispatcher("/WEB-INF/jsp/userCheck.jsp");
			dispatcher4.forward(request, response);
		}
	}

}
