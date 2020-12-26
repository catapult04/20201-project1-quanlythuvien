package services;

import java.sql.ResultSet;

public class ThuthuModelService {
	public String getNameById(String id) {
		try {
			String sql = "select Ten_20183955 from thuthu_minhhn where MaTT_20183955='" + id + "'";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			rs.next();
			String res = rs.getString(1);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
}	
