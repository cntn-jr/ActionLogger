package servlet;

import static model.InputChecker.checkLongInput;
import static model.InputChecker.checkMailAddress;
import static model.InputChecker.checkPhoneNumber;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Info_actDAO;
import model.ErrorViewData;
import model.InformationAction;
import model.InputCheckException;
import model.User;

@WebServlet("/Submit")
public class SubmitAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubmitAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/ActionLooger");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("loginUser_id");
		InformationAction logAct = new InformationAction();

		try {//out_date out_time in_date in_time place reason remarks dateSbm user_id
			
			LocalDateTime date1 = LocalDateTime.now();
			DateTimeFormatter dtformat1 = 
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String fdate1 = dtformat1.format(date1);
			
			Info_actDAO infodao = new Info_actDAO(); 
			
			logAct.setLog_id(infodao.getNextId());
			logAct.setOut_datetime(request.getParameter("out_date") + " " + request.getParameter("out_time") + ":00");
			logAct.setIn_datetime(request.getParameter("in_date") + " " + request.getParameter("in_time") + ":00");			
//			logAct.setPlace(checkLongInput(request.getParameter("place")));
//			logAct.setReason(checkLongInput(request.getParameter("reason")));
//			logAct.setRemarks(checkLongInput(request.getParameter("remarks")));
			logAct.setPlace(request.getParameter("place"));
			logAct.setReason(request.getParameter("reason"));
			logAct.setRemarks(request.getParameter("remarks"));
			logAct.setDateSbm(fdate1);
			logAct.setUser_id(user_id);
			
			session.setAttribute("logAct", logAct);
//			String view = request.getParameter("view");
//			view = "submitConfirm";
//			request.setAttribute("view", view);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/submitConfirm.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("/ActionLogger/Main");

//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
//		} catch (InputCheckException e) {
//			// 表示データを用意する
//			ErrorViewData errorData = new ErrorViewData("フォームに入力された内容に問題がありました。", "入力画面に戻る",
//					"/ActionLogger/adduser");
//			request.setAttribute("errorData", errorData);
//			// エラー表示にフォワード
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
//			dispatcher.forward(request, response);
	}
	}

}
