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
import model.ErrorViewData;
import model.GroupMgt;
import model.InformationAction;

@WebServlet("/")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Main() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.removeAttribute("newUser");// �T�C���A�b�v�̎��̃Z�b�V�����X�R�[�v�̏���

		String user_id = (String) session.getAttribute("loginUser_id");
		String mgtGroup = (String) request.getParameter("mgtGroup");// �\������O���[�v�̃O���[�vID
		String view = (String) request.getParameter("view");
		String search = (String) request.getParameter("search");

		Info_actDAO infoDAO = new Info_actDAO();
		GroupDAO gdao = new GroupDAO();
		entryDAO etdao = new entryDAO();

		// �V�K5���̍s���������擾
		List<InformationAction> easyLogList = new ArrayList<>();
		easyLogList = infoDAO.getLimit(user_id);
		session.setAttribute("easyLogList", easyLogList);

		// �����̍s���������擾
		if (view != null && view.equals("activities")) {
			List<InformationAction> allLogList = new ArrayList<>();
			allLogList = infoDAO.getAll(user_id);
			if (search != null) {
				String searchDate = (String) request.getParameter("searchDate");
				String searchPlace = (String) request.getParameter("searchPlace");
				allLogList = infoDAO.getSelfConditional(user_id, searchDate, searchPlace);
			}
			session.setAttribute("allLogList", allLogList);
		}

		// �Ǘ��O���[�v�̏��̃��X�g���擾
		List<String[]> groupList = new ArrayList<>();
		groupList = gdao.getGroupList(user_id);
		session.setAttribute("groupList", groupList);

		// �Q���O���[�v�̏��̃��X�g���擾
		List<GroupMgt> entryGroupList = new ArrayList<>();
		entryGroupList = etdao.getEntryGroupNameList(user_id);
		session.setAttribute("entryGroupList", entryGroupList);

		// �I�������Ǘ��O���[�v�̍s�������̎擾
		if (mgtGroup != null) {
			// �O���[�v�Ǘ��҂��ǂ�������
			if (gdao.isAdmin(mgtGroup, user_id)) {
				List<InformationAction> participantLogList = new ArrayList<>();
				participantLogList = infoDAO.getParticipantLog(mgtGroup);
				if (search != null) {// �i�荞�݌���������΁A
					String searchDate = (String) request.getParameter("searchDate");
					String searchPlace = (String) request.getParameter("searchPlace");
					participantLogList = infoDAO.getParticipantConditional(mgtGroup, searchDate, searchPlace);
				}
				request.setAttribute("mgtGroup", mgtGroup);
				session.setAttribute("participantLogList", participantLogList);
			} else { // �O���[�v�ɃA�N�Z�X���錠�����Ȃ�
				// �\���f�[�^��p�ӂ���
				ErrorViewData errorData = new ErrorViewData("�A�N�Z�X����������܂���B", "�g�b�v�ɖ߂�", "/ActionLogger/Main");
				request.setAttribute("errorData", errorData);
				// �G���[�\���Ƀt�H���[�h
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}

		// �O���[�v�̏����擾
		if (view != null && view.equals("groupInfo")) {
			GroupMgt selectGroup = new GroupMgt();
			String select_id = request.getParameter("selectGroup");
			selectGroup = gdao.getGroup(select_id);
			session.setAttribute("selectGroup", selectGroup);
			// ����\������O���[�v�ɎQ�����Ă��邩�ǂ������X�R�[�v�ɕۑ�
			boolean alreadyEntry = etdao.alreadyEntry(user_id, select_id);
			session.setAttribute("alreadyEntry", alreadyEntry);
		}

		if (user_id == null) {
			// ���O�C��������
			response.sendRedirect("/ActionLogger/Login");

		} else {
			// MainView�ɔ��
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}