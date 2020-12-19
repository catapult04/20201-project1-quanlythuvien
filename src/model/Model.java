package model;

import java.util.Optional;

import com.jfoenix.controls.JFXButton;

import controller.MainController;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.MainQLTV;
import javafx.scene.control.ButtonType;
import services.ConnService;

public class Model {
	public static ObservableList<String> LIST_FIELDS_NAME;
	private JFXButton delBtn;
	private JFXButton saveBtn;
	
	public Model() {
		delBtn = new JFXButton();
		saveBtn = new JFXButton();
		
		delBtn.setStyle("-fx-background-color: #CCFF66");
		delBtn.setOnAction(event -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setHeaderText("Xác nhận xóa?");
			Optional<ButtonType> option = a.showAndWait();
	        if (option.get() == ButtonType.OK) {
	        	if(this.getClass().equals(SachModel.class)) {
	        		String str = ((SachModel) this).getMasach_20183955();
	        		MainQLTV.control.dataSach.remove(this);
	        		ConnService.delete("sach_minhhn", str);
	        	} else if(this.getClass().equals(DocgiaModel.class)) {
	        		String str = ((DocgiaModel) this).getMaDG_20183955();
	        		MainQLTV.control.dataSach.remove(this);
	        		ConnService.delete("docgia_minhhn", str);
	        	} 
	        	// anything else?
	        	Alert b = new Alert(AlertType.INFORMATION);
				b.setHeaderText("Xóa thành công!");
				b.showAndWait();
	        } else {}
		});
		delBtn.setText("Xóa");
		delBtn.autosize();
		
		saveBtn.setStyle("-fx-background-color: #CCFF66");
		saveBtn.setOnAction(event -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setHeaderText("Lưu các thay đổi?");
			Optional<ButtonType> option = a.showAndWait();
	        if (option.get() == ButtonType.OK) {
	        	if(this.getClass().equals(SachModel.class)) {
	        		String str = ((SachModel) this).getMasach_20183955();
	        		MainQLTV.control.dataSach.remove(this);
	        		ConnService.delete("sach_minhhn", str);
	        	} else if(this.getClass().equals(DocgiaModel.class)) {
	        		String str = ((DocgiaModel) this).getMaDG_20183955();
	        		MainQLTV.control.dataSach.remove(this);
	        		ConnService.delete("docgia_minhhn", str);
	        	} 
	        	// anything else?
	        	
	        	Alert b = new Alert(AlertType.INFORMATION);
				b.setHeaderText("Xóa thành công!");
				b.showAndWait();
	        } else {}
		});
		saveBtn.setText("Lưu");
		saveBtn.autosize();
	}
	
	public void setField(int order, String value) {
	}
	
	
	public JFXButton getDelBtn() {
		return delBtn;
	}
	public void setDelBtn(JFXButton delBtn) {
		this.delBtn = delBtn;
	}
	public JFXButton getSaveBtn() {
		return saveBtn;
	}
	public void setSaveBtn(JFXButton saveBtn) {
		this.saveBtn = saveBtn;
	}
}
