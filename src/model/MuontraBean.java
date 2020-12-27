package model;

import java.util.Optional;
import com.jfoenix.controls.JFXButton;

import application.MainQLTV;
import controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import services.MuontraBeanService;
import services.UtilService;

public class MuontraBean{
	private MuontraModel mtModel;
	private String TenDG_20183955;
	private String TenTT_20183955;
	JFXButton updateBtn;
	JFXButton delBtn;
	
	public MuontraBean(MuontraModel mtModel, String tenDG, String tenTT) {
		this.mtModel = mtModel;
		TenDG_20183955 = tenDG;
		TenTT_20183955 = tenTT;
		
		MuontraBeanService mtBeanService = new MuontraBeanService();
		
		delBtn = new JFXButton();
		delBtn.setText("Xóa");
		delBtn.setStyle("-fx-background-color: #CCFF66");
		delBtn.setOnAction(event -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setHeaderText("Xác nhận xóa?");
			Optional<ButtonType> option = a.showAndWait();
	        if (option.get() == ButtonType.OK) {
	        		String str = getMtModel().getMaMT_20183955();
	        		MainController.dataMT.remove(this);
	        		mtBeanService.delete(str);
	        } else {}
		});
		delBtn.autosize();
		
		updateBtn = new JFXButton();
		updateBtn.setText("Sửa");
		updateBtn.setStyle("-fx-background-color: #CCFF66");
		updateBtn.setOnAction(event -> {
			MainController.choosingMTBean = this;
			try {
	        	UtilService.popUp("UpdateMTForm");
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
		});
		updateBtn.autosize();
	}
	
	public MuontraModel getMtModel() {
		return mtModel;
	}
	public void setMtModel(MuontraModel mtModel) {
		this.mtModel = mtModel;
	}

	public String getTenDG_20183955() {
		return TenDG_20183955;
	}

	public void setTenDG_20183955(String tenDG_20183955) {
		TenDG_20183955 = tenDG_20183955;
	}

	public String getTenTT_20183955() {
		return TenTT_20183955;
	}

	public void setTenTT_20183955(String ten_20183955) {
		TenTT_20183955 = ten_20183955;
	}

	public JFXButton getDelBtn() {
		return delBtn;
	}

	public void setDelBtn(JFXButton delBtn) {
		this.delBtn = delBtn;
	}

	public JFXButton getUpdateBtn() {
		return updateBtn;
	}

	public void setUpdateBtn(JFXButton updateBtn) {
		this.updateBtn = updateBtn;
	}
	
}
