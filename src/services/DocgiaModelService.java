package services;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MuontraModel;

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
