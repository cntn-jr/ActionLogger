package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EntryGrp;

public class entryDAO {

	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/ActionLogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public boolean save(EntryGrp entry) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "INSERT INTO entry " + "( user_id, group_id ) " + "VALUES ( ?, ? )";
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

	// 参加しているグループの名前のリストを返す
	public List<String> getEntryGroupNameList(String user_id) {
		List<String> nameList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT mgt_group.group_name FROM mgt_group,entry where entry.user_id = ? AND mgt_group.group_id = entry.group_id;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			ResultSet rs = pStmt.executeQuery();
			String groupName;
			while (rs.next()) {
				groupName = rs.getString("group_name");
				nameList.add(groupName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return nameList;
	}

	// 参加しようとしているグループに既に参加しているか確認(参加できればtrue)
	public boolean ableEntry(String user_id, String group_id) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM ENTRY WHERE user_id = ? AND group_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			pStmt.setString(2, group_id);
			ResultSet rs = pStmt.executeQuery();
			String id = null;
			while (rs.next()) {
				id = rs.getString("group_id");
			}
			if (id == null) {
				// 既に参加していなければ、
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 時間があれば修正
//	//参加しようとしているグループを管理しているか確認（管理していればtrueを返す）
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
//				return false;//管理しているグループがなければ、false
//			}
//		}catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;//管理しているグループがあれば、trueを返す
//	}

}
