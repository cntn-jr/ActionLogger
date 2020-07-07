package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

//DB荳翫�ｮuser繝�繝ｼ繝悶Ν縺ｫ蟇ｾ蠢懊☆繧汽AO
public class UserDAO {
	// 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ謗･邯壹↓菴ｿ逕ｨ縺吶ｋ諠�蝣ｱ
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/ActionLogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	//繝ｦ繝ｼ繧ｶ繝ｼID繧呈欠螳壹＠縺ｦ縲√Θ繝ｼ繧ｶ繝ｼ諠�蝣ｱ繧貞叙蠕�
	//繝ｦ繝ｼ繧ｶ繝ｼID縺悟ｭ伜惠縺励↑縺�蝣ｴ蜷医�ｯnull繧定ｿ斐☆
	public User get(String userId) {
		User user = null;

		// 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ謗･邯�
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT譁�縺ｮ貅門ｙ
			String sql = "SELECT * FROM user WHERE userid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);

			// SELECT繧貞ｮ溯｡�
			ResultSet rs = pStmt.executeQuery();

			// SELECT譁�縺ｮ邨先棡繧置ser縺ｫ譬ｼ邏�
			while (rs.next()) {
				user = new User();
				user.setUser_id(rs.getString("userid"));
				user.setPasswordHash(rs.getString("pwdhash"));
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

	//繝ｦ繝ｼ繧ｶ繝ｼ繧呈欠螳壹＠縺ｦ縲√Θ繝ｼ繧ｶ繝ｼ諠�蝣ｱ繧剃ｿ晏ｭ�
	//謌ｻ繧雁�､:true 謌仙粥 , false 螟ｱ謨�
	public boolean save(User user) {
		// 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ謗･邯�
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT譁�縺ｮ貅門ｙ(id縺ｯ閾ｪ蜍暮�｣逡ｪ縺ｪ縺ｮ縺ｧ謖�螳壹＠縺ｪ縺上※繧医＞�ｼ�
			String sql = "INSERT INTO user " + "( userid, pwdhash, name, address, tel, email ) "
					+ "VALUES ( ?, ?, ?, ?, ?, ? )";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT譁�荳ｭ縺ｮ縲�?縲阪↓菴ｿ逕ｨ縺吶ｋ蛟､繧定ｨｭ螳壹＠SQL繧貞ｮ梧��
			pStmt.setString(1, user.getUser_id());
			pStmt.setString(2, user.getPasswordHash());
			pStmt.setString(3, user.getName());
			pStmt.setString(4, user.getAddress());
			pStmt.setString(5, user.getTel_number());
			pStmt.setString(6, user.getMail());

			// INSERT譁�繧貞ｮ溯｡�
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
