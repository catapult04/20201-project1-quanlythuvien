package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SachModelService {
	public void updateTrangthai_Tienphat() {
		try {
			MuontraBeanService mtBeanService = new MuontraBeanService();
			ChitietMuonService ctService = new ChitietMuonService();
			
			ObservableList<String> idMTQuaHan = mtBeanService.getIdQuaHan();
			for(int i=0; i<idMTQuaHan.size(); i++) {
				ObservableList<String> idSach = ctService.getMasachByMaMT(idMTQuaHan.get(i));
				for(int j=0; j<idSach.size(); j++) {
					if(getTrangthaiById(idSach.get(j)).equals("Đang mượn")) {
						setTrangthaiById(idSach.get(j), "Quá hạn");
						ctService.setTienphat(idMTQuaHan.get(i), idSach.get(j), getDongiaById(idSach.get(j)));
					}
				}
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getDongiaById(String id) {
		try {
			String sql = "select Dongia_20183955 from sach_minhn where Masach_20183955 = ?";
	        PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, id);
	        
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int res = rs.getInt(1);
			preparedStatement.close();
			
			return res;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public String getTrangthaiById(String id) {
		try {
			String sql = "select Trangthaisach_20183955 from sach_minhn where Masach_20183955 = ?";
	        PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, id);
	        
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			String res = rs.getString(1);
			preparedStatement.close();
			
			return res;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean setTrangthaiById(String id, String status) {
		try {
			String sql = "UPDATE sach_minhhn set Trangthaisach_20183955=? where Masach_20183955 = ?";
	        PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, status);
			preparedStatement.setString(1, id);
	        
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
}
