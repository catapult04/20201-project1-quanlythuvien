package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ChitietMuonModel;
import model.MuontraBean;
import model.MuontraModel;

public class ChitietMuonService {
	public ObservableList<ChitietMuonModel> getAll(){
		ObservableList<ChitietMuonModel> list = FXCollections.observableArrayList();
		try {
			String sql = "select * from chitiet_muon_minhhn";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				ChitietMuonModel model = new ChitietMuonModel(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getString(5));
				list.add(model);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ObservableList<String> getMasachByMaMT(String id) {
		try {
			ObservableList<String> res = FXCollections.observableArrayList();
			String sql = "select Masach_20183955 from chitiet_muon_minhhn where MaMT_20183955='" + id + "'";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				res.add(rs.getString(1));
			}
			rs.close();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	
	public void setTienphat(String idMT, String idSach, int tienphat) {
		String sql = "UPDATE chitiet_muon_minhhn set Tienphat_20183955= ? where MaMT_20183955 = ? and Masach_20183955 = ?";
        
        try{
        	PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        	preparedStatement.setInt(1, tienphat);
        	preparedStatement.setString(2, idMT);
            preparedStatement.setString(3, idSach);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
        } catch (Exception ex){
            ex.printStackTrace();
        }
	}
	
	
	public boolean delete(String maMT, String maSach) {
		try {
			String sql = "delete from chitiet_muon_minhhn where MaMT_20183955=? and MaSach_20183955=?";
			PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, maMT);
			preparedStatement.setString(2, maSach);
			preparedStatement.execute();
			preparedStatement.close();
			
			UtilService.success();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			UtilService.fail();
			return false;
		}
	}
}