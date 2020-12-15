package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSachController implements Initializable{
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
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
		
	}
	
	public void onCancelBtn(ActionEvent event) {
		Stage s = (Stage)cancelBtn.getScene().getWindow();
		s.close();
	}
}
