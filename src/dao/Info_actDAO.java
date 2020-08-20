package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.InformationAction;
import model.User;

public class Info_actDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/ActionLogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public List<InformationAction> getAll(String user_id) {
		InformationAction log = null;
		List<InformationAction> logList = new ArrayList<InformationAction>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "SELECT * FROM info_act WHERE user_id = ? ORDER BY date_submit;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				log = new InformationAction();
				log.setLog_id(rs.getString("log_id"));
				log.setUser_id(rs.getString("user_id"));
				log.setDateSbm(rs.getString("date_submit"));
				log.setOut_datetime(rs.getString("goout_start").substring(0, 16));
				log.setIn_datetime(rs.getString("goout_end").substring(0, 16));
				log.setPlace(rs.getString("place"));
				log.setReason(rs.getString("reason"));
				log.setRemarks(rs.getString("remarks"));

				logList.add(log);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return logList;
	}

	public List<InformationAction> getLimit(String user_id) {
		InformationAction log = null;
		List<InformationAction> logList = new ArrayList<InformationAction>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "SELECT * FROM info_act WHERE user_id = ? ORDER BY date_submit LIMIT 5;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				log = new InformationAction();
				log.setLog_id(rs.getString("log_id"));
				log.setUser_id(rs.getString("user_id"));
				log.setDateSbm(rs.getString("date_submit"));
				log.setOut_datetime(rs.getString("goout_start").substring(0, 16));
				log.setIn_datetime(rs.getString("goout_end").substring(0, 16));
				log.setPlace(rs.getString("place"));
				log.setReason(rs.getString("reason"));
				log.setRemarks(rs.getString("remarks"));

				logList.add(log);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return logList;
	}

	// 自分の行動履歴の条件付検索のSELECT文
	public List<InformationAction> getSelfConditional(String user_id, String searchDate, String searchPlace) {
		InformationAction log = null;
		List<InformationAction> logList = new ArrayList<InformationAction>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "SELECT * FROM info_act "
					+ "WHERE user_id = ? AND (goout_start LIKE ? OR goout_end LIKE ?) AND place LIKE ? "
					+ "ORDER BY date_submit;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user_id);
			pStmt.setString(2, "%" + searchDate + "%");
			pStmt.setString(3, "%" + searchDate + "%");
			pStmt.setString(4, "%" + searchPlace + "%");

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				log = new InformationAction();
				log.setLog_id(rs.getString("log_id"));
				log.setUser_id(rs.getString("user_id"));
				log.setDateSbm(rs.getString("date_submit"));
				log.setOut_datetime(rs.getString("goout_start").substring(0, 16));
				log.setIn_datetime(rs.getString("goout_end").substring(0, 16));
				log.setPlace(rs.getString("place"));
				log.setReason(rs.getString("reason"));
				log.setRemarks(rs.getString("remarks"));

				logList.add(log);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return logList;
	}

	public boolean save(InformationAction infoAct) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			String sql = "INSERT INTO info_act "
					+ "( log_id, user_id, date_submit, goout_start, goout_end, place ,reason ,remarks ) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, infoAct.getLog_id());
			pStmt.setString(2, infoAct.getUser_id());
			pStmt.setString(3, infoAct.getDateSbm());
			pStmt.setString(4, infoAct.getOut_datetime());
			pStmt.setString(5, infoAct.getIn_datetime());
			pStmt.setString(6, infoAct.getPlace());
			pStmt.setString(7, infoAct.getReason());
			pStmt.setString(8, infoAct.getRemarks());

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

	// 次のlog_idを取得
	public String getNextId() {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT max(log_id) FROM info_act";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			String nextId = null;

			while (rs.next()) {
				nextId = rs.getString("max(log_id)");
			}
			if (nextId == null) {
				nextId = "1";
			} else {
				Integer i = Integer.parseInt(nextId);
				i++;
				nextId = i.toString();
			}
			return nextId;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 管理しているグループの参加者の行動履歴を取得
	public List<InformationAction> getParticipantLog(String group_id) {
		InformationAction log = null;
		List<InformationAction> logList = new ArrayList<InformationAction>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM info_act, entry WHERE info_act.user_id = entry.user_id AND entry.group_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, group_id);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				log = new InformationAction();
				log.setLog_id(rs.getString("log_id"));
				log.setUser_id(rs.getString("user_id"));
				log.setDateSbm(rs.getString("date_submit"));
				log.setOut_datetime(rs.getString("goout_start").substring(0, 16));
				log.setIn_datetime(rs.getString("goout_end").substring(0, 16));
				log.setPlace(rs.getString("place"));
				log.setReason(rs.getString("reason"));
				log.setRemarks(rs.getString("remarks"));

				logList.add(log);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return logList;
	}

	// 管理しているグループの参加者の絞り込みありの行動履歴を取得
	public List<InformationAction> getParticipantConditional(String group_id, String searchDate, String searchPlace) {
		InformationAction log = null;
		List<InformationAction> logList = new ArrayList<InformationAction>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM info_act, entry "
					+ "WHERE info_act.user_id = entry.user_id AND entry.group_id = ? "
					+ "AND (info_act.goout_start LIKE ? OR info_act.goout_end LIKE ?) AND info_act.place LIKE ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, group_id);
			pStmt.setString(2, "%" + searchDate + "%");
			pStmt.setString(3, "%" + searchDate + "%");
			pStmt.setString(4, "%" + searchPlace + "%");
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				log = new InformationAction();
				log.setLog_id(rs.getString("log_id"));
				log.setUser_id(rs.getString("user_id"));
				log.setDateSbm(rs.getString("date_submit"));
				log.setOut_datetime(rs.getString("goout_start").substring(0, 16));
				log.setIn_datetime(rs.getString("goout_end").substring(0, 16));
				log.setPlace(rs.getString("place"));
				log.setReason(rs.getString("reason"));
				log.setRemarks(rs.getString("remarks"));

				logList.add(log);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return logList;
	}
}
