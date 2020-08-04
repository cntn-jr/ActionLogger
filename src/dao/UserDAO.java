package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

//IDと一致するuserを返す
public class UserDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/ActionLogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public User get(String userId) {
		User user = null;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "SELECT * FROM user WHERE userid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setUser_id(rs.getString("userid"));
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setTel_number(rs.getString("tel"));
				user.setMail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	public User getPass(String userId) {
		User user = null;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "SELECT * FROM user WHERE userid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setPasswordHash(rs.getString("pwdhash"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	// ユーザの登録
	public boolean save(User user) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "INSERT INTO user " + "( userid, pwdhash, name, address, tel, email ) "
					+ "VALUES ( ?, ?, ?, ?, ?, ? )";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getUser_id());
			pStmt.setString(2, user.getPasswordHash());
			pStmt.setString(3, user.getName());
			pStmt.setString(4, user.getAddress());
			pStmt.setString(5, user.getTel_number());
			pStmt.setString(6, user.getMail());

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ユーザ情報の変更
	public boolean updateInfo(String user_id, String name, String address, String tel, String mail) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "UPDATE user SET name = ?, address = ?, tel = ?, email = ? WHERE userid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, address);
			pStmt.setString(3, tel);
			pStmt.setString(4, mail);
			pStmt.setString(5, user_id);

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// パスワードの変更
		public boolean updatePass(String user_id, String pwdhash) {
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				String sql = "UPDATE user SET pwdhash = ? WHERE userid = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, pwdhash);
				pStmt.setString(2, user_id);

				int result = pStmt.executeUpdate();
				if (result != 1) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
}
