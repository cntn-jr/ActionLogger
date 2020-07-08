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
import model.User;
import model.ValidationKey;

/**
 * Servlet implementation class SignupConfirm
 */
@WebServlet("/SignupConfirm")
public class SignupConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupConfirm() {
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
		
		// フォームから送られた確認キーが保存したものと一致するか確認
		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
		if (!request.getParameter("vKey").equals(validationKey.getValue())) {
			 // 一致しなかったので、セッションスコープに保存したキーを破棄し、エラーページに
			 session.removeAttribute("validationKey");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			 dispatcher.forward(request, response);
			 return;
		}
		
		
		User user = (User)session.getAttribute("newUser");
		UserDAO userDAO = new UserDAO();
		userDAO.save(user);
		session.setAttribute("loginUser", user);
		session.setAttribute("newUser", null);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);
	}

}
