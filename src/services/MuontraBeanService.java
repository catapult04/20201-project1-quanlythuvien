package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import application.MainQLTV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MuontraBean;
import model.MuontraModel;

public class MuontraBeanService {
	public ObservableList<String> getIdQuaHan(){
		try {
			ObservableList<String> list = FXCollections.observableArrayList();
			
			String sql = "select MaMT_20183955 from muontra_minhhn where Ngayhentra_20183955 < ?";
			PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, MainQLTV.nowString);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ObservableList<MuontraBean> getQuaHan(){
		ObservableList<MuontraBean> mtBeanList = FXCollections.observableArrayList();
		try {
			String sql = "select * from muontra_minhhn where Ngayhentra_20183955 < ?";
			PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, MainQLTV.nowString);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			DocgiaModelService dgModelService = new DocgiaModelService();
			ThuthuModelService ttModelService = new ThuthuModelService();
			
			while(rs.next()) {
				MuontraModel mtModel = new MuontraModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
				String tenDG = dgModelService.getNameById(mtModel.getMaDG_20183955());
				String tenTT = ttModelService.getNameById(mtModel.getMaTT_20183955());
				MuontraBean mtBean = new MuontraBean(mtModel, tenDG, tenTT);
				mtBeanList.add(mtBean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mtBeanList;
	}
	
	public ObservableList<String> getAllMaDG(){
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			String sql = "select distinct MaDG_20183955 from muontra_minhhn";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ObservableList<String> getAllMaTT(){
		ObservableList<String> list = FXCollections.observableArrayList();
		try {
			String sql = "select distinct MaTT_20183955 from muontra_minhhn";
			ResultSet rs = ConnService.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ObservableList<MuontraBean> getAll(String maMT, String maDG, String maTT, Date muonfrom, Date muonto, Date henfrom, Date hento){
		ObservableList<MuontraBean> mtBeanList = FXCollections.observableArrayList();
		try {
			MuontraModelService mtModelService = new MuontraModelService();
			DocgiaModelService dgModelService = new DocgiaModelService();
			ThuthuModelService ttModelService = new ThuthuModelService();
			
			ObservableList<MuontraModel> mtModelList = mtModelService.getAll(maMT, maDG, maTT, muonfrom, muonto, henfrom, hento);
			for(int i=0; i< mtModelList.size(); i++) {
				MuontraModel mtModel = mtModelList.get(i);
				String tenDG = dgModelService.getNameById(mtModel.getMaDG_20183955());
				String tenTT = ttModelService.getNameById(mtModel.getMaTT_20183955());
				MuontraBean mtBean = new MuontraBean(mtModel, tenDG, tenTT);
				mtBeanList.add(mtBean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mtBeanList;
	}
	
	
	
	public boolean insert(MuontraBean mtBean) {
		try {			
			String sql = "INSERT INTO muontra_minhhn(MaMT_20183955, MaDG_20183955, MaTT_20183955, Ngaymuon_20183955, Ngayhentra_20183955, Tiencoc_20183955)"
	                + " values (?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            
	        preparedStatement.setString(1, mtBean.getMtModel().getMaMT_20183955());
	        preparedStatement.setString(2, mtBean.getMtModel().getMaDG_20183955());
	        preparedStatement.setString(3, mtBean.getMtModel().getMaTT_20183955());
	        preparedStatement.setDate(4, (java.sql.Date) mtBean.getMtModel().getNgaymuon_20183955());
	        preparedStatement.setDate(5, (java.sql.Date) mtBean.getMtModel().getNgayhentra_20183955());
	        preparedStatement.setInt(6, mtBean.getMtModel().getTiencoc_20183955());

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
	
	
	public ObservableList<MuontraBean> getAll(){
		ObservableList<MuontraBean> mtBeanList = FXCollections.observableArrayList();
		try {
			MuontraModelService mtModelService = new MuontraModelService();
			DocgiaModelService dgModelService = new DocgiaModelService();
			ThuthuModelService ttModelService = new ThuthuModelService();
			
			ObservableList<MuontraModel> mtModelList = mtModelService.getAll();
			for(int i=0; i< mtModelList.size(); i++) {
				MuontraModel mtModel = mtModelList.get(i);
				String tenDG = dgModelService.getNameById(mtModel.getMaDG_20183955());
				String tenTT = ttModelService.getNameById(mtModel.getMaTT_20183955());
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
			
			UtilService.success();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			UtilService.fail();
			return false;
		}
	}
	
	
	public boolean update(String oldId, MuontraModel mtModel) {
        String sql = "UPDATE muontra_minhhn set MaMT_20183955= ?, MaDG_20183955 = ?, MaTT_20183955 = ?, Ngaymuon_20183955 = ?, Ngayhentra_20183955 = ?, Tiencoc_20183955 = ? where MaMT_20183955 = ?";
        
        try{
        	PreparedStatement preparedStatement = ConnService.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, mtModel.getMaMT_20183955());
            preparedStatement.setString(2, mtModel.getMaDG_20183955());
            preparedStatement.setString(3, mtModel.getMaTT_20183955());
            preparedStatement.setDate(4, (java.sql.Date) mtModel.getNgaymuon_20183955());
            preparedStatement.setDate(5, (java.sql.Date) mtModel.getNgayhentra_20183955());
            preparedStatement.setInt(6, mtModel.getTiencoc_20183955());
            preparedStatement.setString(7, oldId);
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
            
            UtilService.success();
            
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            UtilService.fail();
            return false;
        }
	}
}
