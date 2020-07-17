package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ErrorViewData;
import model.InputCheckException;
import model.User;
import model.ValidationKey;

//static import
import static model.InputChecker.checkLongInput;
import static model.InputChecker.checkPhoneNumber;
import static model.InputChecker.checkMailAddress;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		// 正当なフォームから送られたデータであることを確認するためのキーの生成
//				ValidationKey validationKey = new ValidationKey();
//				try {
//					Random random = new Random();
//					String randomStr = String.valueOf(random.nextLong());
//					MessageDigest validation = MessageDigest.getInstance("MD5");
//					validation.reset();
//					validation.update(randomStr.getBytes("utf8"));
//					String vkey = String.format("%032x", new BigInteger(1, validation.digest()));
//					validationKey.setValue(vkey);
//				} catch (NoSuchAlgorithmException e) {
//					e.printStackTrace();
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				}

				// フォーム確認キーをセッションスコープに設定
				HttpSession session = request.getSession();
//				session.setAttribute("validationKey", validationKey);
		
		//HttpSession session = request.getSession();
		String loginUser_id = (String)session.getAttribute("loginUser_id");
				
		if(loginUser_id == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("/ActionLogger");//ログイン済み
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
//		// フォームから送られた確認キーが保存したものと一致するか確認
//				ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
//				if (!request.getParameter("vKey").equals(validationKey.getValue())) {
//					 // 一致しなかったので、セッションスコープに保存したキーを破棄し、エラーページに
//					session.removeAttribute("validationKey");
//					//表示データを用意する
//					ErrorViewData errorData = new ErrorViewData("問題が発生しました。","トップに戻る","/ActionLogger/Main");
//					request.setAttribute("errorData", errorData);
//					//エラー表示にフォワード
//					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
//					dispatcher.forward(request, response);
//					return;
//				}
		
		User user = new User();
		
		try {
			user.setUser_id( checkLongInput(request.getParameter("user_id")) );
			user.setName( checkLongInput(request.getParameter("name")) );
			user.setAddress( checkLongInput(request.getParameter("address")) );
			user.setTel_number( checkPhoneNumber(request.getParameter("tel_number")) );
			user.setMail( checkMailAddress(request.getParameter("mail")) );
			// パスワードのハッシュ化
			String rawPassword = request.getParameter("password");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(rawPassword.getBytes("utf8"));
			String passwordHash = String.format("%064x", new BigInteger(1, digest.digest()));
			
			user.setPasswordHash(passwordHash);
			
			session.setAttribute("newUser", user);
		    
			RequestDispatcher dispatcher =
					request.getRequestDispatcher
			            ("/WEB-INF/jsp/signupConfirm.jsp");
			    dispatcher.forward(request, response);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InputCheckException e) {
			//表示データを用意する
			ErrorViewData errorData = new ErrorViewData("フォームに入力された内容に問題がありました。",
													"入力画面に戻る","/ActionLogger/adduser");
			request.setAttribute("errorData", errorData);
			//エラー表示にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
