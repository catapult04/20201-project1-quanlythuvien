package controller;

import java.net.URL;
import java.sql.Date;
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

public class AddMTController implements Initializable{
	@FXML private TextField tf1;
	@FXML private ComboBox<String> tf2;
	@FXML private Label lb1; //name
	@FXML private Label lb2; //cmnd
	@FXML private TextField tf3;
	@FXML private DatePicker tf4;
	@FXML private DatePicker tf5;
	@FXML private TextField tf6;
	
	@FXML private Button addBtn;
	@FXML private Button cancelBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tf3.setText(MainQLTV.maTT);
		tf3.setEditable(false);
		DocgiaModelService ser = new DocgiaModelService();
		tf2.setItems(ser.getAllId());
		tf2.setVisibleRowCount(8);
	}
	
	public void onTf2() {
		DocgiaModelService ser = new DocgiaModelService();
		lb1.setText(ser.getNameById(tf2.getValue()));
		lb2.setText(ser.getCMNDById(tf2.getValue()));
	}
	
	public void onAddBtn() {
		DocgiaModelService dgService = new DocgiaModelService();
		MuontraBeanService mtService = new MuontraBeanService();
		
		MuontraModel mtModel = new MuontraModel(tf1.getText(), tf2.getValue(), tf3.getText(), Date.valueOf(tf4.getValue()), Date.valueOf(tf5.getValue()), Integer.parseInt(tf6.getText()) );
		MuontraBean mtBean = new MuontraBean(mtModel, dgService.getNameById(tf2.getValue()), MainQLTV.tenTT);
		
		if(mtService.insert(mtBean)==true) {
			MainController.dataMT.add(mtBean);
		} else {}
	}
	
	public void onCancelBtn(ActionEvent event) {
		Stage s = (Stage)cancelBtn.getScene().getWindow();
		s.close();
	}
}
