package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Info_actDAO;
import dao.UserDAO;
import model.ErrorViewData;
import model.InformationAction;
import model.User;
import model.ValidationKey;

/**
 * Servlet implementation class SubmitActionConfirm
 */
@WebServlet("/SubmitActionConfirm")
public class SubmitActionConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitActionConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
//		// フォームから送られた確認キーが保存したものと一致するか確認
//		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
//		if (!request.getParameter("vKey").equals(validationKey.getValue())) {
//			 // 一致しなかったので、セッションスコープに保存したキーを破棄し、エラーページに
//			 session.removeAttribute("validationKey");
//			//表示データを用意する
//				ErrorViewData errorData = new ErrorViewData("問題が発生しました。",
//														"トップに戻る","/ActionLogger/Main");
//				request.setAttribute("errorData", errorData);
//			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
//			 dispatcher.forward(request, response);
//			 return;
//		}
		
		
		InformationAction log = (InformationAction)session.getAttribute("logAct");
		Info_actDAO info_actDAO = new Info_actDAO();
		info_actDAO.save(log);
		session.removeAttribute("logAct");//セッションスコープの除去
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);
	}

}
