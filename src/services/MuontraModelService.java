package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import model.MuontraModel;

public class MuontraModelService {
	public boolean insert(MuontraModel model)  {
        String query = "INSERT INTO muontra_minhhn(MaMT_20183955, MaDG_20183955, MaTT_20183955, Ngaymuon_20183955, Ngayhentra_20183955, Tiencoc_20183955, )"
                + " values (?, ?, ?, ?, ?, ?)";
        try {
        	PreparedStatement preparedStatement = ConnService.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, model.getMaMT_20183955());
            preparedStatement.setString(2, model.getMaDG_20183955());
            preparedStatement.setString(3, model.getMaTT_20183955());
            preparedStatement.setDate(4, (Date) model.getNgaymuon_20183955());
            preparedStatement.setDate(5, (Date) model.getNgayhentra_20183955());
            preparedStatement.setInt(6, model.getTiencoc_20183955());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
	
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
