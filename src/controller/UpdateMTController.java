package controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import application.MainQLTV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MuontraBean;
import model.MuontraModel;
import model.SachModel;
import services.ConnService;
import services.DocgiaModelService;
import services.MuontraBeanService;
import services.MuontraModelService;

public class UpdateMTController implements Initializable{
	@FXML private TextField tf1;
	@FXML private ComboBox<String> tf2;
	@FXML private Label lb1; //name
	@FXML private Label lb2; //cmnd
	@FXML private TextField tf3;
	@FXML private DatePicker tf4;
	@FXML private DatePicker tf5;
	@FXML private TextField tf6;
	
	@FXML private Button updateBtn;
	@FXML private Button cancelBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DocgiaModelService dgService = new DocgiaModelService();
		
		MuontraModel model = MainController.choosingMTBean.getMtModel();
		tf1.setText(model.getMaMT_20183955());
		tf3.setText(MainQLTV.maTT);
		tf3.setEditable(false);
		DocgiaModelService ser = new DocgiaModelService();
		tf2.setItems(ser.getAllId());
		tf2.setVisibleRowCount(8);
		tf2.setValue(model.getMaDG_20183955());
		lb1.setText(dgService.getNameById(model.getMaDG_20183955()));
		lb2.setText(dgService.getCMNDById(model.getMaDG_20183955()));
		
		LocalDate from = ((Date)model.getNgaymuon_20183955()).toLocalDate();
		tf4.setValue(from);
		
		LocalDate to = ((Date)model.getNgayhentra_20183955()).toLocalDate();
		tf5.setValue(to);
		
		tf6.setText(String.valueOf(model.getTiencoc_20183955()));
	}
	
	public void onTf2() {
		DocgiaModelService ser = new DocgiaModelService();
		lb1.setText(ser.getNameById(tf2.getValue()));
		lb2.setText(ser.getCMNDById(tf2.getValue()));
	}
	
	public void onUpdateBtn() {
		DocgiaModelService dgService = new DocgiaModelService();
		MuontraBeanService mtService = new MuontraBeanService();
		
		MuontraModel mtModel = new MuontraModel(tf1.getText(), tf2.getValue(), tf3.getText(), Date.valueOf(tf4.getValue()), Date.valueOf(tf5.getValue()), Integer.parseInt(tf6.getText()) );
		MuontraBean mtBean = new MuontraBean(mtModel, dgService.getNameById(tf2.getValue()), MainQLTV.tenTT);
		
		if(mtService.update(MainController.choosingMTBean.getMtModel().getMaMT_20183955(), mtModel)==true) {
			MainController.dataMT.remove(MainController.choosingMTBean);
			MainController.dataMT.add(mtBean);
			MainController.choosingMTBean = mtBean;
		};
	}
	
	public void onCancelBtn(ActionEvent event) {
		Stage s = (Stage)cancelBtn.getScene().getWindow();
		s.close();
	}
}
