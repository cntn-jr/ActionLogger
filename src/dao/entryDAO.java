package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EntryGrp;
import model.InformationAction;

public class entryDAO {
	
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/ActionLogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public boolean save(EntryGrp entry) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "INSERT INTO entry " + "( user_id, group_id ) "
					+ "VALUES ( ?, ? )";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, entry.getUser_id());
			pStmt.setString(2, entry.getGroup_id());

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
	
	//�Q�����Ă���O���[�v�̖��O�̃��X�g��Ԃ�
	public List<String> getEntryGroupNameList(String user_id){
		List<String> nameList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT mgt_group.group_name FROM mgt_group,entry where entry.user_id = ? AND mgt_group.group_id = entry.group_id;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			ResultSet rs = pStmt.executeQuery();
			String groupName;
			while(rs.next()) {
				groupName = rs.getString("group_name");
				nameList.add(groupName);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return nameList;
	}

	
	//���Ԃ�����ΏC��
//	//�Q�����悤�Ƃ��Ă���O���[�v���Ǘ����Ă��邩�m�F�i�Ǘ����Ă����true��Ԃ��j
//	public boolean confirmAdimnGroup(String user_id,String group_id) {
//		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
//			String sql = "SELECT entry.group_id FROM entry,mgt_group WHERE entry.user_id = ? AND entry.group_id = ? AND entry.group_id = mgt_group.group_id AND entry.user_id = mgt_group.admin_id;";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1, user_id);
//			pStmt.setString(2, group_id);
//			ResultSet rs = pStmt.executeQuery();
//			String id = null;
//			while(rs.next()) {
//				id = rs.getString("group_id");
//				break;
//			}
//			if(id == null) {
//				return false;//�Ǘ����Ă���O���[�v���Ȃ���΁Afalse
//			}
//		}catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;//�Ǘ����Ă���O���[�v������΁Atrue��Ԃ�
//	}

}
