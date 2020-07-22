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
import model.GroupMgt;
import model.InformationAction;
/**
 * Servlet implementation class Main
 */
@WebServlet("/")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String user_id = (String) session.getAttribute("loginUser_id");

		Info_actDAO infoDAO = new Info_actDAO();
		GroupDAO gdao = new GroupDAO();
		//�V�K5���̍s������
		List<InformationAction> easyLogList = new ArrayList<>();
		easyLogList = infoDAO.getLimit(user_id);
		//�S�Ă̍s������
		List<InformationAction> allLogList = new ArrayList<>();
		allLogList = infoDAO.getAll(user_id);
		//�Ǘ��O���[�v�̃��X�g
		List<String> groupNameList = new ArrayList<>();
		groupNameList = gdao.getGroupNameList(user_id);

		session.setAttribute("easyLogList", easyLogList);
		session.setAttribute("allLogList", allLogList);
		session.setAttribute("groupNameList", groupNameList);




		if (user_id == null) {
			// MainViewを表示
			response.sendRedirect("/ActionLogger/Login");

		} else {
			// MainViewを表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
