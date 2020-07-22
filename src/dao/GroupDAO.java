package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GroupMgt;
import model.RamdomIdCreate;

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
	
	public List<String> getGroupNameList(String user_id){
		List<String> nameList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT group_name FROM mgt_group where admin_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			ResultSet rs = pStmt.executeQuery();
			String gName;
			while(rs.next()) {
				gName = rs.getString("group_name");
				nameList.add(gName);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return nameList;
	}
	
	public String nextGroupId(){
		List<String> idList = new ArrayList<>();
		String nextGroupId = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT group_id FROM mgt_group";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			String id = null;
			RamdomIdCreate createId = new RamdomIdCreate();
			while(rs.next()) {
				id = rs.getString("group_id");
				idList.add(id);//データベースに保存されているグループIDのリスト
			}
			first: while(true) {
				nextGroupId = createId.createId();
				for(String i:idList) {
					if( i.equals(nextGroupId)) {
						continue first;//グループIDがかぶれば、作り直し
					}
				}
				break;//かぶらなければ
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return nextGroupId;
	}

}
