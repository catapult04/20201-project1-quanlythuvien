package controller;

import model.DocgiaModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import services.ConnService;
import view.MainQLTV;

public class AddDGController implements Initializable{
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> gioitinh = FXCollections.observableArrayList("Nam", "Nữ", "No3");
		tf3.setItems(gioitinh);
	}
	
	private MainController mainController;
	
	@FXML private TextField tf1;
	@FXML private TextField tf2;
	@FXML private ComboBox<String> tf3;
	@FXML private TextField tf4;
	@FXML private TextField tf5;
	@FXML private TextField tf6;
	@FXML private TextField tf7;
	@FXML private TextField tf8; 
	
	
	@FXML private Button addBtn;
	@FXML private Button cancelBtn;
	
	public void onAddBtn(ActionEvent event) {
		ObservableList ltf = FXCollections.observableArrayList(tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8);
		ObservableList<String> info = FXCollections.observableArrayList();
		for(int i=0; i<ltf.size(); i++) {
			if(i!=2) info.add(((TextField)(ltf.get(i))).getText());
			else	 info.add(((ComboBox<String>)(ltf.get(i))).getValue());
		}
		
		
		if(ConnService.insertInto("docgia_minhhn", info)==true) {
			MainQLTV.control.dataDG.add(new DocgiaModel(info));
			for(int i=0; i<ltf.size(); i++) {
				if(i!=2) ((TextField)(ltf.get(i))).setText(null);
				else	 ((ComboBox<String>)(ltf.get(i))).setValue(null);
			}
		}
	}
	
	public void onCancelBtn(ActionEvent event) {
		Stage s = (Stage)cancelBtn.getScene().getWindow();
		s.close();
	}
}
