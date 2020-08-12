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

			String sql = "INSERT INTO mgt_group " + "( group_id, group_name, admin_id ) " + "VALUES ( ?, ?, ? )";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, group.getGroup_id());
			pStmt.setString(2, group.getGroup_name());
			pStmt.setString(3, group.getAdmin_id());

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

	public List<String[]> getGroupList(String user_id) {
		List<String[]> groupList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM mgt_group where admin_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String groupArray[] = new String[2];
				groupArray[0] = rs.getString("group_id");
				groupArray[1] = rs.getString("group_name");
				groupList.add(groupArray);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return groupList;
	}

	// グループIDの取得
	public String nextGroupId() {
		List<String> idList = new ArrayList<>();
		String nextGroupId = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT group_id FROM mgt_group";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			String id = null;
			RamdomIdCreate createId = new RamdomIdCreate();
			while (rs.next()) {
				id = rs.getString("group_id");
				idList.add(id);// データベースに保存されているグループIDのリスト
			}
			first: while (true) {
				nextGroupId = createId.createId();// ランダム6桁のIDを生成
				for (String i : idList) {
					if (i.equals(nextGroupId)) {
						continue first;// グループIDがかぶれば、作り直し
					}
				}
				break;// かぶらなければ
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return nextGroupId;
	}

	// そのグループの管理者かどうかを判定
	public boolean isAdmin(String group_id, String user_id) {
		String id = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT admin_id FROM mgt_group WHERE group_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, group_id);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("admin_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (id.equals(user_id)) {
			return true;
		} else {
			return false;
		}
	}

	// グループが存在しているかチェック(存在していればtrue)
	public boolean isGroup(String group_id) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM mgt_group";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			String id;
			while (rs.next()) {
				id = rs.getString("group_id");
				if (group_id.equals(id)) {
					// 参加しようとしているグループが存在していれば、
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
