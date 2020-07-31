package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupDAO;
import dao.Info_actDAO;
import dao.entryDAO;
import model.GroupMgt;
import model.InformationAction;

@WebServlet("/")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Main() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String user_id = (String) session.getAttribute("loginUser_id");
		String mgtGroup = (String) request.getParameter("mgtGroup");
		String view = (String) request.getParameter("view");
		String search = (String) request.getParameter("search");

		Info_actDAO infoDAO = new Info_actDAO();
		GroupDAO gdao = new GroupDAO();
		entryDAO entDAO = new entryDAO();
		//�V�K5���̍s������
		List<InformationAction> easyLogList = new ArrayList<>();
		easyLogList = infoDAO.getLimit(user_id);
		//�S�Ă̍s������
		List<InformationAction> allLogList = new ArrayList<>();
		allLogList = infoDAO.getAll(user_id);
		if(search != null){
			String searchDate = (String) request.getParameter("searchDate");
			String searchPlace = (String) request.getParameter("searchPlace");
			allLogList = infoDAO.getSelfConditional(user_id, searchDate, searchPlace);
		}
		//�Ǘ��O���[�v�̃��X�g
		List<String[]> groupList = new ArrayList<>();
		groupList = gdao.getGroupList(user_id);
		//�Q���O���[�v�̃��X�g
		List<String> entryNameList = new ArrayList<>();
		entryNameList = entDAO.getEntryGroupNameList(user_id);
		
		if(mgtGroup != null) {//�I�������Ǘ��O���[�v�̍s�������̎擾
			List<InformationAction> participantLogList = new ArrayList<>();
			participantLogList = infoDAO.getParticipantLog(mgtGroup);
			if(search != null){//�i�荞�݌���������΁A
				String searchDate = (String) request.getParameter("searchDate");
				String searchPlace = (String) request.getParameter("searchPlace");
				participantLogList = infoDAO.getParticipantConditional(mgtGroup, searchDate, searchPlace);
			}
			request.setAttribute("mgtGroup", mgtGroup);
			session.setAttribute("participantLogList", participantLogList);
		}
		
//		List<InformationAction> participantLogList  = infoDAO.getParticipantLog(mgtGroup);
//		session.setAttribute("participantLogList", participantLogList);


		session.setAttribute("easyLogList", easyLogList);
		session.setAttribute("allLogList", allLogList);
		session.setAttribute("groupList", groupList);
		session.setAttribute("entryNameList", entryNameList);




		if (user_id == null) {
			// MainViewを表示
			response.sendRedirect("/ActionLogger/Login");

		} else {
			// MainViewを表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp");
			dispatcher.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
