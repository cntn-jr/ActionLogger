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
		//VK5ฬsฎ๐
		List<InformationAction> easyLogList = new ArrayList<>();
		easyLogList = infoDAO.getLimit(user_id);
		//Sฤฬsฎ๐
		List<InformationAction> allLogList = new ArrayList<>();
		allLogList = infoDAO.getAll(user_id);
		if(search != null){
			String searchDate = (String) request.getParameter("searchDate");
			String searchPlace = (String) request.getParameter("searchPlace");
			allLogList = infoDAO.getSelfConditional(user_id, searchDate, searchPlace);
		}
		//วO[vฬXg
		List<String[]> groupList = new ArrayList<>();
		groupList = gdao.getGroupList(user_id);
		//QมO[vฬXg
		List<String> entryNameList = new ArrayList<>();
		entryNameList = entDAO.getEntryGroupNameList(user_id);
		
		if(mgtGroup != null) {//I๐ตฝวO[vฬsฎ๐ฬๆพ
			List<InformationAction> participantLogList = new ArrayList<>();
			participantLogList = infoDAO.getParticipantLog(mgtGroup);
			session.setAttribute("participantLogList", participantLogList);
		}
		
//		List<InformationAction> participantLogList  = infoDAO.getParticipantLog(mgtGroup);
//		session.setAttribute("participantLogList", participantLogList);


		session.setAttribute("easyLogList", easyLogList);
		session.setAttribute("allLogList", allLogList);
		session.setAttribute("groupList", groupList);
		session.setAttribute("entryNameList", entryNameList);




		if (user_id == null) {
			// MainViewใ่กจ็คบ
			response.sendRedirect("/ActionLogger/Login");

		} else {
			// MainViewใ่กจ็คบ
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp");
			dispatcher.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
