package services;

import javafx.collections.FXCollections;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.ObservableList;
import model.SachModel;

public class SachModelService {
	public boolean insert(SachModel model)  {
        String query = "INSERT INTO sach_minhhn(Masach_20183955, Tensach_20183955, Tacgia_20183955, NhaXB_20183955, NamXB_20183955, Dongia_20183955, Trangthaisach_20183955, Gioithieu_20183955)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
        	PreparedStatement preparedStatement = ConnService.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, model.getMasach_20183955());
            preparedStatement.setString(2, model.getTensach_20183955());
            preparedStatement.setString(3, model.getTacgia_20183955());
            preparedStatement.setString(4, model.getNhaXB_20183955());
            preparedStatement.setString(5, model.getNamXB_20183955());
            preparedStatement.setInt(6, Integer.parseInt(model.getDongia_20183955()));
            preparedStatement.setString(7, model.getTrangthaisach_20183955());
            preparedStatement.setString(8, model.getGioithieu_20183955());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
	
	public ObservableList<String> getAllId(){
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			String sql = "select Masach_20183955 from sach_minhhn";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String getNameById(String id) {
		try {
			String sql = "select Tensach_20183955 from sach_minhhn where Masach_20183955='" + id + "'";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			rs.next();
			String res = rs.getString(1);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}	
	}
	
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
						ctService.setTienphatById(idMTQuaHan.get(i), idSach.get(j), getDongiaById(idSach.get(j))/3);
					}
				}
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getDongiaById(String id) {
		try {
			String sql = "select Dongia_20183955 from sach_minhhn where Masach_20183955 = ?";
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
			String sql = "select Trangthaisach_20183955 from sach_minhhn where Masach_20183955 = ?";
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
			String sql = "UPDATE sach_minhhn set Trangthaisach_20183955 = ? where Masach_20183955 = ?";
	        PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, status);
	        preparedStatement.setString(2, id);
	        
			preparedStatement.execute();
			preparedStatement.close();
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			UtilService.fail();
			return false;
		}
	}
}
