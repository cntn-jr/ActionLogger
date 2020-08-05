package servlet;

import static model.InputChecker.checkLongInput;
import static model.InputChecker.checkMailAddress;
import static model.InputChecker.checkPhoneNumber;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import model.InputCheckException;

@WebServlet("/AlterUserInfo")
public class AlterUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlterUserInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean checked = (boolean) session.getAttribute("checked");
		if (checked) {
			checked = false;
			session.setAttribute("checked",checked);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/alterUserInfo.jsp");
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
			String name = checkLongInput(request.getParameter("name"));
			String address = checkLongInput(request.getParameter("address"));
			String tel = checkPhoneNumber(request.getParameter("tel_number"));
			String mail = checkMailAddress(request.getParameter("mail"));
			UserDAO userdao = new UserDAO();
			userdao.updateInfo(user_id, name, address, tel, mail);
			// main�Ƀt�H���[�h
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);

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
