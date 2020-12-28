package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import model.MuontraModel;

public class MuontraModelService {
	public ObservableList<MuontraModel> getAll(){
		ObservableList<MuontraModel> list = FXCollections.observableArrayList();
		try {
			String sql = "select * from muontra_minhhn";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				MuontraModel mtModel = new MuontraModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
				list.add(mtModel);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ObservableList<MuontraModel> getAll(String maMT, String maDG, String maTT, Date muonfrom, Date muonto, Date henfrom, Date hento){
		ObservableList<MuontraModel> list = FXCollections.observableArrayList();
		try {
			String sql = "select * from muontra_minhhn where MaMT_20183955 like ? and MaDG_20183955 like ? and MaTT_20183955 like ? AND Ngaymuon_20183955>=? AND Ngaymuon_20183955<=? AND Ngayhentra_20183955>=? AND Ngayhentra_20183955<=?";
			PreparedStatement preState = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preState.setString(1, "%"+maMT+"%");
			preState.setString(2, "%"+maDG+"%");
			preState.setString(3, "%"+maTT+"%");
			preState.setDate(4, muonfrom);
			preState.setDate(5, muonto);
			preState.setDate(6, henfrom);
			preState.setDate(7, hento);
			
			ResultSet rs = preState.executeQuery();
			
			while(rs.next()) {
				MuontraModel mtModel = new MuontraModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
				list.add(mtModel);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}	
