package services;

import javafx.collections.FXCollections;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

import javafx.collections.ObservableList;
import model.ChitietMuonModel;
import model.MuontraBean;
import model.MuontraModel;

public class ChitietMuonService {
	public void setTienphatGhichuById(String maMT, String maSach, int tienphat, String ghichu) {
String sql = "UPDATE chitiet_muon_minhhn set Tienphat_20183955= ?, Ghichu_20183955= ? where MaMT_20183955 = ? and Masach_20183955 = ?";
        
        try{
        	PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        	preparedStatement.setInt(1, tienphat);
        	preparedStatement.setString(2, ghichu);
            preparedStatement.setString(3, maMT);
            preparedStatement.setString(4, maSach);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            UtilService.success();
        } catch (Exception ex){
            ex.printStackTrace();
            UtilService.fail();
        }
	}
	
	public String getIdByMasach(String masach) {
		try {
			String sql = "select MaMT_20183955 from chitiet_muon_minhhn where Masach_20183955='" + masach + "' and Ngaytra_20183955 is NULL";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			rs.next();
			String res = rs.getString(1);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return "null";
		}	
	}
	
	public void setNgaytraById(String maMT, String maSach, Date ngaytra) {
		String sql = "UPDATE chitiet_muon_minhhn set Ngaytra_20183955= ? where MaMT_20183955 = ? and Masach_20183955 = ?";
        
        try{
        	PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        	preparedStatement.setDate(1, ngaytra);
        	preparedStatement.setString(2, maMT);
            preparedStatement.setString(3, maSach);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
        } catch (Exception ex){
            ex.printStackTrace();
        }
	}
	
	public boolean insert(ChitietMuonModel model) {
		try {			
			String sql = "INSERT INTO chitiet_muon_minhhn(MaMT_20183955, Masach_20183955, Ngaytra_20183955, Tienphat_20183955, Ghichu_20183955)"
	                + " values (?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            
	        preparedStatement.setString(1, model.getMaMT_20183955());
	        preparedStatement.setString(2, model.getMasach_20183955());
	        preparedStatement.setDate(3, (Date) model.getNgaytra_20183955());
	        preparedStatement.setInt(4, model.getTienphat_20183955());
	        preparedStatement.setString(5, model.getGhichu_20183955());

	        preparedStatement.execute();
	        preparedStatement.close();
	        
	        
	        UtilService.success();
		    return true;
		} catch(Exception e) {
			e.printStackTrace();
			UtilService.fail();
			return false;
		}
	}
	
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
	
	public ObservableList<ChitietMuonModel> selectById(String id) {
		try {
			ObservableList<ChitietMuonModel> list = FXCollections.observableArrayList();
			
			String sql = "select * from chitiet_muon_minhhn where MaMT_20183955='" + id + "'";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			
			while(rs.next()) {
				ChitietMuonModel model = new ChitietMuonModel(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getString(5));
				model.oldStatus = "";
				list.add(model);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
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
	
	
	public void setTienphatById(String idMT, String idSach, int tienphat) {
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