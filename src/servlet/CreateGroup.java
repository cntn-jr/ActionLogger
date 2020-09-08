package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupDAO;
import static model.InputChecker.*;

import model.ErrorViewData;
import model.GroupMgt;
import model.InputCheckException;

@WebServlet("/CreateGroup")
public class CreateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateGroup() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/ActionLooger");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			GroupDAO gdao = new GroupDAO();
			GroupMgt group = new GroupMgt();
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("loginUser_id");
			// ���̃Z�b�g
			group.setGroup_id(gdao.nextGroupId());
			group.setGroup_name(checkLongInput(request.getParameter("group_name")));
			group.setAdmin_id(user_id);

			session.setAttribute("group", group);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/createGroupConfirm.jsp");
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
