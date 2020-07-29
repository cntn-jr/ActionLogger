package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.entryDAO;
import model.EntryGrp;


@WebServlet("/EntryGroup")
public class EntryGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EntryGroup() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		EntryGrp entry = new EntryGrp();
		entryDAO etDAO = new entryDAO();
		String user_id = (String)session.getAttribute("loginUser_id");
	
		//���Ԃ�����΁A�g�ݍ���
//		if(!etDAO.confirmAdimnGroup(user_id, request.getParameter("group_id"))) {
//			//�Q������O���[�v�����ɊǗ����Ă��Ȃ���΁A...
//			//���̃Z�b�g
//			entry.setGroup_id(request.getParameter("group_id"));
//			entry.setUser_id(user_id);
//			
//			//�f�[�^�x�[�X�ɕۑ�
//			etDAO.save(entry);
//		}else {
//			String view = "entryGroup";
//			request.setAttribute("view", view);
//		}
		
		//���̃Z�b�g
		entry.setGroup_id(request.getParameter("group_id"));
		entry.setUser_id(user_id);
		
		//�f�[�^�x�[�X�ɕۑ�
		etDAO.save(entry);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);

	}

}