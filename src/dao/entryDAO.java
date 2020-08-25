package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EntryGrp;
import model.GroupMgt;

public class entryDAO {

	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/ActionLogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public boolean save(EntryGrp entry) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "INSERT INTO entry " + "( user_id, group_id, isentry ) " + "VALUES ( ?, ?, ? )";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, entry.getUser_id());
			pStmt.setString(2, entry.getGroup_id());
			pStmt.setBoolean(3, true);

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

	// �Q�����Ă���O���[�v�̃��X�g��Ԃ�
	public List<GroupMgt> getEntryGroupNameList(String user_id) {
		List<GroupMgt> groupList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM mgt_group,entry "
					+ "where entry.user_id = ? AND mgt_group.group_id = entry.group_id AND entry.isentry = true;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			ResultSet rs = pStmt.executeQuery();
			GroupMgt group;
			while (rs.next()) {
				group = new GroupMgt();
				group.setGroup_id(rs.getString("mgt_group.group_id"));
				group.setGroup_name(rs.getString("mgt_group.group_name"));
				groupList.add(group);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return groupList;
	}

	// �Q�����悤�Ƃ��Ă���O���[�v�Ɋ��ɎQ�����Ă��邩�m�F(�Q���ł����true)
	public boolean alreadyEntry(String user_id, String group_id) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM ENTRY WHERE user_id = ? AND group_id = ? AND isentry = true;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			pStmt.setString(2, group_id);
			ResultSet rs = pStmt.executeQuery();
			String id = null;
			while (rs.next()) {
				id = rs.getString("group_id");
			}
			if (id != null) {
				// �Q�����Ă���΁A
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// �O���[�v�̑މ�
	public boolean leave(String user_id, String group_id) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "UPDATE entry SET isentry = false " + "WHERE user_id = ? AND group_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			pStmt.setString(2, group_id);
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

	// �O���[�v�̎Q��������
	public boolean re_entry(String user_id, String group_id) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "UPDATE entry SET isentry = true " + "WHERE user_id = ? AND group_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			pStmt.setString(2, group_id);
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

	// �f�[�^�����ɓo�^���Ă��邩�ǂ���
	public boolean isData(String user_id, String group_id) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM ENTRY WHERE user_id = ? AND group_id = ? AND isentry = false;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			pStmt.setString(2, group_id);
			ResultSet rs = pStmt.executeQuery();
			String id = null;
			while (rs.next()) {
				id = rs.getString("group_id");
			}
			if (id != null) {
				// �o�^���Ă���΁A
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ���Ԃ�����ΏC��
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
