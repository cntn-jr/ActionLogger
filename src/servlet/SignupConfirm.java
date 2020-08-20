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
import model.User;
import model.ValidationKey;

@WebServlet("/SignupConfirm")
public class SignupConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignupConfirm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
//		// �t�H�[�����瑗��ꂽ�m�F�L�[���ۑ��������̂ƈ�v���邩�m�F
//		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
//		if (!request.getParameter("vKey").equals(validationKey.getValue())) {
//			 // ��v���Ȃ������̂ŁA�Z�b�V�����X�R�[�v�ɕۑ������L�[��j�����A�G���[�y�[�W��
//			 session.removeAttribute("validationKey");
//			//�\���f�[�^��p�ӂ���
//				ErrorViewData errorData = new ErrorViewData("��肪�������܂����B",
//														"�g�b�v�ɖ߂�","/ActionLogger/Main");
//				request.setAttribute("errorData", errorData);
//			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
//			 dispatcher.forward(request, response);
//			 return;
//		}
		
		
		//�V�K���[�U���f�[�^�x�[�X�ɓo�^
		User newUser = (User)session.getAttribute("newUser");
		UserDAO userDAO = new UserDAO();
		userDAO.save(newUser);
		session.setAttribute("loginUser_id", newUser.getUser_id());
		//���[�UID����V�K���[�U�̏����擾
		User user = userDAO.getInfo(newUser.getUser_id());
		session.setAttribute("user", user);
		response.sendRedirect("/ActionLogger");
	}

}
