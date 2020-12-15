package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import com.mysql.jdbc.Statement;

import model.DocgiaModel;
import model.Model;
import model.MuontraModel;
import model.SachModel;
import model.ThuthuModel;
import services.ConnService;
import view.MainQLTV;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class MainController implements Initializable {
	/***************
	 * For All
	 ***************/
	@Override //DOAN MA KHOI TAO MOI THU
	public void initialize(URL arg0, ResourceBundle arg1) {
		ConnService.buildTable("sach_minhhn", tableSach, SachModel.LIST_FIELDS_NAME, "select * from sach_minhhn");
		buildRadioBtnsSach();
		
//		ConnService.buildTable("muontra_minhhn", tableMT, columnsMT, SachModel.LIST_FIELDS_NAME, SachModel.NUMBER_FIELDS);
		
		ConnService.buildTable("docgia_minhhn", tableDG, DocgiaModel.LIST_FIELDS_NAME, "select * from docgia_minhhn");
	}
	
	
	
	
    /*******************
     * Sach Manager
     *******************/
	@FXML private TableView tableSach;
    @FXML private TableColumn c1Sach;
    @FXML private TableColumn c2Sach;
    @FXML private TableColumn c3Sach;
    @FXML private TableColumn c4Sach;
    @FXML private TableColumn c5Sach;
    @FXML private TableColumn c6Sach;
    @FXML private TableColumn c7Sach;
    @FXML private TableColumn c8Sach;
    
    @FXML private TextField search1Sach;
    @FXML private TextField search2Sach;
    @FXML private TextField search3Sach;
    @FXML private TextField search4Sach;
    @FXML private TextField search5Sach;
    @FXML private TextField search6Sach;
    @FXML private Button searchBtnSach;
    @FXML private Button resetBtnSach;
    
    @FXML private RadioButton radio1Sach;
    @FXML private RadioButton radio2Sach;
    @FXML private RadioButton radio3Sach;
    @FXML private RadioButton radio4Sach;
    
    
    @FXML private Button addBtnSach;
    @FXML private Button saveBtnSach;
    @FXML private Button delBtnSach;
    @FXML private Button add2BtnSach;
    
    public void onSearchBtnSach(ActionEvent event) {
    	
    }
    
    public void onResetBtnSach(ActionEvent event) {
    	ConnService.buildTable("sach_minhhn", tableSach, SachModel.LIST_FIELDS_NAME, "select * from sach_minhhn");
    }
    
    public void buildRadioBtnsSach() {
    	ToggleGroup trangthaisach = new ToggleGroup();
		radio1Sach.setToggleGroup(trangthaisach);
		radio2Sach.setToggleGroup(trangthaisach);
		radio3Sach.setToggleGroup(trangthaisach);
		radio4Sach.setToggleGroup(trangthaisach);
		radio1Sach.setSelected(true);
		trangthaisach.selectedToggleProperty().addListener(new ChangeListener<Toggle>() { 
			@Override
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o1, Toggle o2) 
            { 
                RadioButton rb = (RadioButton)trangthaisach.getSelectedToggle(); 
                if (rb != null) { 
                    String s = rb.getText(); 
                    // what do you want to do: 
                    if(! s.equals("Tất cả")) {
                    	ConnService.buildTable("sach_minhhn", tableSach, SachModel.LIST_FIELDS_NAME, "select * from sach_minhhn where Trangthaisach_20183955='"+s+"'");
                    } else {
                    	ConnService.buildTable("sach_minhhn", tableSach, SachModel.LIST_FIELDS_NAME, "select * from sach_minhhn");
                    }
                } 
            }
        });
    }
    
    public void onAddBtnSach(ActionEvent event) {
    	try {
		    Scene addSachForm = new Scene(FXMLLoader.load(getClass().getResource("/view/AddSachForm.fxml")));
	        Stage stage = new Stage();
	        stage.setScene(addSachForm);
	        stage.centerOnScreen();	
	        stage.show();        
	    }catch (IOException io){
	        io.printStackTrace();
	    }
    }
    
    public void onDelBtnSach(ActionEvent event) {
    	
    }
    
    public void onSaveBtnSach(ActionEvent event) {
    	
    }
    
    public void onAdd2BtnSach(ActionEvent event) {
    	
    }
    

    /************************
     * Muontra Manager
     ************************/
    @FXML private TableView tableMT;
    @FXML private TableColumn c1MT;
    @FXML private TableColumn c2MT;
    @FXML private TableColumn c3MT;
    @FXML private TableColumn c4MT;
    @FXML private TableColumn c5MT;
    @FXML private TableColumn c6MT;
    
    @FXML private TextField c1AddMT;
    @FXML private TextField c2AddMT;
    @FXML private TextField c3AddMT;
    @FXML private TextField c4AddMT;
    @FXML private TextField c5AddMT;
    @FXML private TextField c6AddMT;
    @FXML private Button addBtnMT;

    
    
    /************************
     * Docgia Manager
     ************************/
    @FXML private TableView tableDG;
    @FXML private TableColumn c1DG;
    @FXML private TableColumn c2DG;
    @FXML private TableColumn c3DG;
    @FXML private TableColumn c4DG;
    @FXML private TableColumn c5DG;
    @FXML private TableColumn c6DG;
    @FXML private TableColumn c7DG;
    @FXML private TableColumn c8DG;
    
    
    /************************
     * Thuthu Manager
     ************************/
    @FXML private TableView tableTT;
    @FXML private TableColumn c1TT;
    @FXML private TableColumn c2TT;
    @FXML private TableColumn c3TT;
    @FXML private TableColumn c4TT;
    @FXML private TableColumn c5TT;
    @FXML private TableColumn c6TT;
    @FXML private TableColumn c7TT;
    
   
    
   
}
