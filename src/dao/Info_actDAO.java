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
	// 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ謗･邯壹↓菴ｿ逕ｨ縺吶ｋ諠�蝣ｱ
		private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/ActionLogger";
		private final String DB_USER = "sa";
		private final String DB_PASS = "";

		//繝ｦ繝ｼ繧ｶ繝ｼID繧呈欠螳壹＠縺ｦ縲√Θ繝ｼ繧ｶ繝ｼ諠�蝣ｱ繧貞叙蠕�
		//繝ｦ繝ｼ繧ｶ繝ｼID縺悟ｭ伜惠縺励↑縺�蝣ｴ蜷医�ｯnull繧定ｿ斐☆
		public List<InformationAction> getAll(String user_id) {
			InformationAction log= null;
			List<InformationAction> logList = new ArrayList<InformationAction>();

			// 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ謗･邯�
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				// SELECT譁�縺ｮ貅門ｙ
				String sql = "SELECT * FROM info_act WHERE user_id = ? ORDER BY date_submit;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user_id);

				// SELECT繧貞ｮ溯｡�
				ResultSet rs = pStmt.executeQuery();
				
				// SELECT譁�縺ｮ邨先棡繧置ser縺ｫ譬ｼ邏�
				while (rs.next()) {
					log= new InformationAction();
					log.setLog_id(rs.getString("log_id"));
					log.setUser_id(rs.getString("user_id"));
					log.setDateSbm(rs.getString("date_submit"));
					log.setOut_datetime(rs.getString("goout_start"));
					log.setIn_datetime(rs.getString("goout_end"));
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
			InformationAction log= null;
			List<InformationAction> logList = new ArrayList<InformationAction>();

			// 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ謗･邯�
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				// SELECT譁�縺ｮ貅門ｙ
				String sql = "SELECT * FROM info_act WHERE user_id = ? ORDER BY date_submit LIMIT 5;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user_id);

				// SELECT繧貞ｮ溯｡�
				ResultSet rs = pStmt.executeQuery();
				
				// SELECT譁�縺ｮ邨先棡繧置ser縺ｫ譬ｼ邏�
				while (rs.next()) {
					log= new InformationAction();
					log.setLog_id(rs.getString("log_id"));
					log.setUser_id(rs.getString("user_id"));
					log.setDateSbm(rs.getString("date_submit"));
					log.setOut_datetime(rs.getString("goout_start"));
					log.setIn_datetime(rs.getString("goout_end"));
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

		//繝ｦ繝ｼ繧ｶ繝ｼ繧呈欠螳壹＠縺ｦ縲√Θ繝ｼ繧ｶ繝ｼ諠�蝣ｱ繧剃ｿ晏ｭ�
		//謌ｻ繧雁�､:true 謌仙粥 , false 螟ｱ謨�
		public boolean save(InformationAction infoAct) {
			// 繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ謗･邯�
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				// INSERT譁�縺ｮ貅門ｙ(id縺ｯ閾ｪ蜍暮�｣逡ｪ縺ｪ縺ｮ縺ｧ謖�螳壹＠縺ｪ縺上※繧医＞�ｼ�
				String sql = "INSERT INTO info_act " + "( log_id, user_id, date_submit, goout_start, goout_end, place ,reason ,remarks ) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// INSERT譁�荳ｭ縺ｮ縲�?縲阪↓菴ｿ逕ｨ縺吶ｋ蛟､繧定ｨｭ螳壹＠SQL繧貞ｮ梧��
				pStmt.setString(1, infoAct.getLog_id());
				pStmt.setString(2, infoAct.getUser_id());
				pStmt.setString(3, infoAct.getDateSbm());
				pStmt.setString(4, infoAct.getOut_datetime() );
				pStmt.setString(5, infoAct.getIn_datetime() );
				pStmt.setString(6, infoAct.getPlace());
				pStmt.setString(7, infoAct.getReason());
				pStmt.setString(8, infoAct.getRemarks());

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
		
		//次のlog_idを取得
		public String getNextId() {
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				String sql = "SELECT max(log_id) FROM info_act";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				ResultSet rs = pStmt.executeQuery();
				String nextId = null;
				
				while(rs.next()) {
					nextId = rs.getString("max(log_id)");
				}
				if(nextId == null) {
					nextId = "1";
				}else{
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
}
