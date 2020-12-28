package services;

import javafx.collections.FXCollections;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.ObservableList;
import model.DocgiaModel;

public class DocgiaModelService {
	public boolean insert(DocgiaModel model)  {
        String query = "INSERT INTO thuthu_minhhn(MaDG_20183955, TenDG_20183955, Gioitinh_20183955, Diachi_20183955, Namsinh_20183955, CMND_20183955, Email_20183955, Dthoai_20183955)" 
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
        	PreparedStatement preparedStatement = ConnService.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, model.getMaDG_20183955());
            preparedStatement.setString(2, model.getTenDG_20183955());
            preparedStatement.setString(3, model.getGioitinh_20183955());
            preparedStatement.setString(4, model.getDiachi_20183955());
            preparedStatement.setString(5, model.getNamsinh_20183955());
            preparedStatement.setString(6, model.getCMND_20183955());
            preparedStatement.setString(7, model.getEmail_20183955());
            preparedStatement.setString(8, model.getDthoai_20183955());
            
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
	
	public String getCMNDById(String id) {
		try {
			String sql = "select CMND_20183955 from docgia_minhhn where MaDG_20183955='" + id + "'";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			rs.next();
			String res = rs.getString(1);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}	
	}
	
	public ObservableList<String> getAllId(){
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			String sql = "select MaDG_20183955 from docgia_minhhn";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
