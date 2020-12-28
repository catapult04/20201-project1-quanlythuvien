package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.ThuthuModel;

public class ThuthuModelService {
	public boolean insert(ThuthuModel model)  {
        String query = "INSERT INTO thuthu_minhhn(MaTT_20183955, Ten_20183955, Gioitinh_20183955, Namsinh_20183955, CMND_20183955, Email_20183955, Dthoai_20183955)" 
                + " values (?, ?, ?, ?, ?, ?, ?)";
        try {
        	PreparedStatement preparedStatement = ConnService.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, model.getMaTT_20183955());
            preparedStatement.setString(2, model.getTen_20183955());
            preparedStatement.setString(3, model.getGioitinh_20183955());
            preparedStatement.setString(4, model.getNamsinh_20183955());
            preparedStatement.setString(5, model.getCMND_20183955());
            preparedStatement.setString(6, model.getEmail_20183955());
            preparedStatement.setString(7, model.getDthoai_20183955());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
	
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
