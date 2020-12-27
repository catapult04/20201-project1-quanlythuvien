package model;

import java.util.Date;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;

import controller.DetailMTController;
import controller.MainController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;
import services.ChitietMuonService;
import services.UtilService;

public class ChitietMuonModel {
	private String MaMT_20183955;
	private String Masach_20183955;
	private Date Ngaytra_20183955;
	private int Tienphat_20183955;;
	private String Ghichu_20183955;
	private JFXButton saveBtn;
	private CheckBox traBtn;
	private JFXButton delBtn;
	
	public ChitietMuonModel(String maMT_20183955, String masach_20183955, Date ngaytra_20183955,
			int tienphat_20183955, String ghichu_20183955) {
		super();
		MaMT_20183955 = maMT_20183955;
		Masach_20183955 = masach_20183955;
		Ngaytra_20183955 = ngaytra_20183955;
		Tienphat_20183955 = tienphat_20183955;
		Ghichu_20183955 = ghichu_20183955;
		
		delBtn = new JFXButton();
		delBtn.setText("Xóa");
		delBtn.setStyle("-fx-background-color: #CCFF66");
		delBtn.setOnAction(event -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setHeaderText("Xác nhận xóa?");
			Optional<ButtonType> option = a.showAndWait();
	        if (option.get() == ButtonType.OK) {
	        	ChitietMuonService service = new ChitietMuonService();
	        	DetailMTController.data.remove(this);
	        	service.delete(getMaMT_20183955(), getMasach_20183955());
	        } else {}
		});
		delBtn.autosize();
		
		saveBtn = new JFXButton();
		saveBtn.setText("Lưu");
		saveBtn.setStyle("-fx-background-color: #CCFF66");
		saveBtn.setOnAction(event -> {
			
		});
		saveBtn.autosize();
		
		traBtn = new CheckBox();
		traBtn.setText(null);
		traBtn.autosize();
	}
	
	
	public String getMaMT_20183955() {
		return MaMT_20183955;
	}
	public void setMaMT_20183955(String maMT_20183955) {
		MaMT_20183955 = maMT_20183955;
	}
	public String getMasach_20183955() {
		return Masach_20183955;
	}
	public void setMasach_20183955(String masach_20183955) {
		Masach_20183955 = masach_20183955;
	}
	public Date getNgaytra_20183955() {
		return Ngaytra_20183955;
	}
	public void setNgaytra_20183955(Date ngaytra_20183955) {
		Ngaytra_20183955 = ngaytra_20183955;
	}
	public int getTienphat_20183955() {
		return Tienphat_20183955;
	}
	public void setTienphat_20183955(int tienphat_20183955) {
		Tienphat_20183955 = tienphat_20183955;
	}
	public String getGhichu_20183955() {
		return Ghichu_20183955;
	}
	public void setGhichu_20183955(String ghichu_20183955) {
		Ghichu_20183955 = ghichu_20183955;
	}


	public JFXButton getSaveBtn() {
		return saveBtn;
	}


	public void setSaveBtn(JFXButton saveBtn) {
		this.saveBtn = saveBtn;
	}


	public JFXButton getTraBtn() {
		return traBtn;
	}


	public void setTraBtn(JFXButton traBtn) {
		this.traBtn = traBtn;
	}


	public JFXButton getDelBtn() {
		return delBtn;
	}


	public void setDelBtn(JFXButton delBtn) {
		this.delBtn = delBtn;
	}
}
