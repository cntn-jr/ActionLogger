package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GroupMgt;

public class GroupDAO {
	
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/ActionLogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public boolean save(GroupMgt group) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			String sql = "INSERT INTO mgt_group " + "( group_id, group_name, admin_id ) "
					+ "VALUES ( ?, ?, ? )";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, group.getGroup_id());
			pStmt.setString(2, group.getGroup_name());
			pStmt.setString(3, group.getAdmin_id());

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String nextGroupId(){
		List<String> idList = new ArrayList<>();
		String nextGroupId = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT group_id FROM mgt_group";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			String id = null;
			while(rs.next()) {
				id = rs.getString("group_id");
				idList.add(id);
			}
			while(true) {
				if(true) {
					break;
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return nextGroupId;
	}

}
