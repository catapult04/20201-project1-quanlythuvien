package services;

import java.sql.Connection;
import java.sql.ResultSet;

public class DocgiaModelService {
	public String getNameById(String id) {
		try {
			String sql = "select TenDG_20183955 from docgia_minhhn where MaDG_20183955='" + id + "'";
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
