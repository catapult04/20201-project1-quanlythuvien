package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import model.SachModel;
import services.ConnService;
import view.MainQLTV;

public class AddSachController implements Initializable{
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	private MainController mainController;
	
	@FXML private TextField tf1;
	@FXML private TextField tf2;
	@FXML private TextField tf3;
	@FXML private TextField tf4;
	@FXML private TextField tf5;
	@FXML private TextField tf6;
	@FXML private TextField tf7;
	@FXML private TextField tf8; 
	
	
	@FXML private Button addBtn;
	@FXML private Button cancelBtn;
	
	public void onAddBtn(ActionEvent event) {
		ObservableList<TextField> ltf = FXCollections.observableArrayList(tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8);
		ObservableList<String> info = FXCollections.observableArrayList();
		for(int i=0; i<ltf.size(); i++) {
			info.add(ltf.get(i).getText());
		}
		if(ConnService.insertInto("sach_minhhn", info)==true) {
			MainQLTV.control.dataSach.add(new SachModel(info));
			for(int i=0; i<ltf.size(); i++) {
				ltf.get(i).setText(null);
			}
		}
	}
	
	public void onCancelBtn(ActionEvent event) {
		Stage s = (Stage)cancelBtn.getScene().getWindow();
		s.close();
	}
}
