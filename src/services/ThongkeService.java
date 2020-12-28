package services;

import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ThongkeModel;

public class ThongkeService {
	public int total;
	public ObservableList<ThongkeModel> thongkeSach(String type){
		ObservableList<ThongkeModel> list = FXCollections.observableArrayList();
		try {
			String sqlcounttotal = "select count(Masach_20183955) from sach_minhhn";
			ResultSet rscount = ConnService.conn.createStatement().executeQuery(sqlcounttotal);
			rscount.next();
			total = rscount.getInt(1);
			rscount.close();
			
			String sql = "select "+ type +", COUNT(Masach_20183955) from sach_minhhn group by " + type;
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				ThongkeModel model = new ThongkeModel(rs.getString(1), rs.getInt(2), 100.0*rs.getInt(2)/total);
				list.add(model);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ObservableList<ThongkeModel> thongkeSachTheoTheloai(){
		ObservableList<ThongkeModel> list = FXCollections.observableArrayList();
		try {
			String sqlcounttotal = "select count(Masach_20183955) from sach_minhhn";
			ResultSet rscount = ConnService.conn.createStatement().executeQuery(sqlcounttotal);
			rscount.next();
			total = rscount.getInt(1);
			rscount.close();
			
			String sql = "select SUBSTRING(Masach_20183955, 1, 2) AS theloai, COUNT(Masach_20183955) from sach_minhhn group by theloai";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				ThongkeModel model = new ThongkeModel(rs.getString(1), rs.getInt(2), 100.0*rs.getInt(2)/total);
				list.add(model);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ObservableList<ThongkeModel> thongkePhieu(String type){
		ObservableList<ThongkeModel> list = FXCollections.observableArrayList();
		try {
			String sqlcounttotal = "select count(MaMT_20183955) from muontra_minhhn";
			ResultSet rscount = ConnService.conn.createStatement().executeQuery(sqlcounttotal);
			rscount.next();
			total = rscount.getInt(1);
			rscount.close();
			
			String sql = "select "+ type +", COUNT(MaMT_20183955) from muontra_minhhn group by " + type;
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				ThongkeModel model = new ThongkeModel(rs.getString(1), rs.getInt(2), 100.0*rs.getInt(2)/total);
				list.add(model);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ObservableList<ThongkeModel> thongkeDG(String type){
		ObservableList<ThongkeModel> list = FXCollections.observableArrayList();
		try {
			String sqlcounttotal = "select count(MaDG_20183955) from docgia_minhhn";
			ResultSet rscount = ConnService.conn.createStatement().executeQuery(sqlcounttotal);
			rscount.next();
			total = rscount.getInt(1);
			rscount.close();
			
			String sql = "select "+ type +", COUNT(MaDG_20183955) from docgia_minhhn group by " + type;
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				ThongkeModel model = new ThongkeModel(rs.getString(1), rs.getInt(2), 100.0*rs.getInt(2)/total);
				list.add(model);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
