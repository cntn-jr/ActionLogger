package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Z�b�V�����X�R�[�v����p�����[�^���擾
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
				
		if(loginUser == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("/ActionLogger");//���O�C���ς�
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�p�����[�^���擾
				request.setCharacterEncoding("UTF-8");
				String user_id = request.getParameter("user_id");
				String password = request.getParameter("password");
				HttpSession session = request.getSession();
				
				User loginUser = new User(user_id);
				
				LoginLogic loginRogic = new LoginLogic();
				Boolean canLogin = loginRogic.loginLogic(user_id,password,session);//user�̃p�X���[�h�����������`�F�b�N
				
				if(canLogin == true) {//�p�X���[�h�����������
//					session.setAttribute("loginUser_id", canLogin.getUser_id());
//					session.setAttribute("loginName", canLogin.getName());
					response.sendRedirect("/ActionLogger/Main");
				}else {
					response.sendRedirect("/ActionLogger/Login");//������x���O�C����ʂ�
				}

}
}