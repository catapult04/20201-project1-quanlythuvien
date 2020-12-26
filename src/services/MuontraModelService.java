package services;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
}	
