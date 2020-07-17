package model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

public class LoginLogic {
	public boolean loginLogic(String user_id,String password,HttpSession session) {
		String passwordHash = ""; 
		try {
			//繝代せ繝ｯ繝ｼ繝峨�ｮ繝上ャ繧ｷ繝･蛹�
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(password.getBytes("utf8"));
			passwordHash = String.format("%064x", new BigInteger(1, digest.digest()));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.get( user_id );
		
		if(user != null && user.getPasswordHash().equals(passwordHash)) {
			session.setAttribute("loginUser_id", user.getUser_id());
			session.setAttribute("loginUserName", user.getName());
			return true;
		}
		
		return false;
	}
}
