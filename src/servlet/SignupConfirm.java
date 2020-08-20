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
		
		
		//新規ユーザをデータベースに登録
		User newUser = (User)session.getAttribute("newUser");
		UserDAO userDAO = new UserDAO();
		userDAO.save(newUser);
		session.setAttribute("loginUser_id", newUser.getUser_id());
		//ユーザIDから新規ユーザの情報を取得
		User user = userDAO.getInfo(newUser.getUser_id());
		session.setAttribute("user", user);
		response.sendRedirect("/ActionLogger");
	}

}
