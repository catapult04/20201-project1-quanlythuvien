package controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import application.MainQLTV;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ChitietMuonModel;
import model.MuontraBean;
import model.MuontraModel;
import model.SachModel;
import services.ChitietMuonService;
import services.ConnService;
import services.DocgiaModelService;
import services.MuontraBeanService;
import services.MuontraModelService;
import services.UtilService;

public class DetailMTController implements Initializable{
	public static ObservableList<ChitietMuonModel> data;
	
	@FXML private Label idLabel;
	@FXML private ComboBox<String> idSach;
	@FXML private DatePicker tf5;
	@FXML private TextField des;
	
	@FXML private Button addBtn;
	@FXML private Button cancelBtn;
	
	@FXML private TableView<ChitietMuonModel> table;
	@FXML private TableColumn<ChitietMuonModel, String> c1;
	@FXML private TableColumn<ChitietMuonModel, String> c2;
	@FXML private TableColumn<ChitietMuonModel, String> c3;
	@FXML private TableColumn<ChitietMuonModel, String> c4;
	@FXML private TableColumn<ChitietMuonModel, String> c5;
	@FXML private TableColumn<ChitietMuonModel, String> c6;
	@FXML private TableColumn<ChitietMuonModel, String> c7;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
	}
	
	public void buildTable() {
		ChitietMuonService service = new ChitietMuonService();
    	data = service.getAll();
    	
    	c1.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getMaMT_20183955()));
    	c2.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().
    	c3.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getTenDG_20183955()));
    	c4.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getMtModel().getMaTT_29183955()));
    	c5.setCellValueFactory(new PropertyValueFactory<>("saveBtn"));
    	c6.setCellValueFactory(new PropertyValueFactory<>("traBtn"));
    	c7.setCellValueFactory(new PropertyValueFactory<>("delBtn"));
    	
    	//on double clicked for row
        tableMT.setRowFactory( tv -> {
            TableRow<MuontraBean> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                	choosingMTBean = tableMT.getSelectionModel().getSelectedItem();
                    UtilService.popUp("DetailMTForm");
                }
            });
            return row ;
        });
        
    	tableMT.setItems(dataMT);
	}
}
