package model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

public class LoginLogic {
	public boolean loginLogic(String user_id, String password) {
		String passwordHash = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(password.getBytes("utf8"));
			passwordHash = String.format("%064x", new BigInteger(1, digest.digest()));

			UserDAO userDAO = new UserDAO();
			User user = userDAO.getInfo(user_id);
			User userPwd = userDAO.getPass(user_id);

			if (user.getUser_id() != null && userPwd.getPasswordHash().equals(passwordHash)) {
				return true;
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;

	}

	// ログインしているユーザにパスワードの確認をする
	public boolean userCheck(String user_id, String password) {
		String passwordHash = "";
		try {
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
		User userPwd = userDAO.getPass(user_id);

		if (userPwd != null && userPwd.getPasswordHash().equals(passwordHash)) {
			return true;
		}

		return false;
	}
}
