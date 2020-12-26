package services;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;

import application.MainQLTV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MuontraBean;
import model.MuontraModel;

public class MuontraBeanService {
	public boolean insert(MuontraBean mtBean) {
		try {			
			String sql = "INSERT INTO muontra_minhhn(MaMT_20183955, MaDG_20183955, MaTT_20183955, Ngaymuon_20183955, Ngayhentra_20183955, Tiencoc_20183955)"
	                + " values (?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            
	        preparedStatement.setString(1, mtBean.getMtModel().getMaMT_29183955());
	        preparedStatement.setString(2, mtBean.getMtModel().getMaDG_29183955());
	        preparedStatement.setString(3, mtBean.getMtModel().getMaTT_29183955());
	        preparedStatement.setDate(4, (java.sql.Date) mtBean.getMtModel().getNgaymuon_29183955());
	        preparedStatement.setDate(5, (java.sql.Date) mtBean.getMtModel().getNgayhentra_29183955());
	        preparedStatement.setInt(6, mtBean.getMtModel().getTiencoc_29183955());
	        preparedStatement.close();
	        
	        Alert a = new Alert(AlertType.INFORMATION);
			a.setHeaderText("Thành công");
			a.showAndWait();
	       
	        return true;
		} catch(Exception e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR);
			a.setHeaderText("Thất bại");
			a.showAndWait();
			return false;
		}
	}
	
	
	public ObservableList<MuontraBean> getAll(){
		ObservableList<MuontraBean> mtBeanList = FXCollections.observableArrayList();
		try {
			MuontraModelService mtModelService = new MuontraModelService();
			DocgiaModelService dgModelService = new DocgiaModelService();
			ThuthuModelService ttModelService = new ThuthuModelService();
			
			ObservableList<MuontraModel> mtModelList = mtModelService.getAll();
			for(int i=0; i< mtModelList.size(); i++) {
				MuontraModel mtModel = mtModelList.get(i);
				String tenDG = dgModelService.getNameById(mtModel.getMaDG_29183955());
				String tenTT = ttModelService.getNameById(mtModel.getMaTT_29183955());
				MuontraBean mtBean = new MuontraBean(mtModel, tenDG, tenTT);
				mtBeanList.add(mtBean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mtBeanList;
	}
	
	public boolean delete(String maMT) {
		try {
			String sql = "delete from muontra_minhhn where MaMT_20183955='" + maMT + "'";
			ConnService.conn.createStatement().execute(sql);
			
			Alert a = new Alert(AlertType.INFORMATION);
			a.setHeaderText("Xóa thành công");
			a.showAndWait();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR);
			a.setHeaderText("Xóa thất bại");
			a.showAndWait();
			return false;
		}
	}
	
	
}
