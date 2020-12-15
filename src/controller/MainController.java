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
import services.MyConnectionService;
import view.MainQLTV;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class MainController implements Initializable {
	/***************
	 * Start FOR ALL
	 ***************/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buildData("sach_minhhn", tableSach, SachModel.LIST_FIELDS_NAME);
//		buildData("muontra_minhhn", tableMT, columnsMT, SachModel.LIST_FIELDS_NAME, SachModel.NUMBER_FIELDS);
		buildData("docgia_minhhn", tableDG, DocgiaModel.LIST_FIELDS_NAME);
	}
	
	//this method is used for buildData method below
	public Model createModel(String tableName, ObservableList<String> input) {
		Model tmp;
		switch(tableName) {
			case "sach_minhhn": tmp = new SachModel(input); break;
			case "docgia_minhhn": tmp = new DocgiaModel(input); break;
			default: tmp = new ThuthuModel(input); break;
		}
		return tmp;
	}
	
	public void buildData(String tableName, TableView table, ObservableList<String> LIST_FIELDS_NAME){
        try{        	
          String SQL = "SELECT * from " + tableName;
          ResultSet rs = MainQLTV.conn.createStatement().executeQuery(SQL);

          //1. Data added to ObservableList
          ObservableList<Model> data = FXCollections.observableArrayList();
          ObservableList<String> row;
          while(rs.next()){
        	  //Iterate Row
        	  row = FXCollections.observableArrayList();;
              for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                  //Iterate Column
                  row.add(rs.getString(i));
              }
              data.add(createModel(tableName, row));
          }
          
          //2. set property for columns
          ObservableList<TableColumn> columns = table.getColumns();
          for(int i=0; i<columns.size(); i++) {
          	columns.get(i).setCellValueFactory(new PropertyValueFactory<>(LIST_FIELDS_NAME.get(i)));
          }

          //3. fullfill data
          table.setItems(data);
          table.setEditable(true);  //cannot edit?
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");             
        }
    }
	/****************
     * Finish FOR ALL
     ****************/
	
	
	
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
    
    @FXML private ComboBox searchComboBoxSach;
    @FXML private TextField searchTextFieldSach;
    @FXML private Button searchBtnSach;
    @FXML private Button status1BtnSach; //co san
    @FXML private Button status2BtnSach; //dang muon
    @FXML private Button status3BtnSach; //qua han
    @FXML private Button addBtnSach;
    @FXML private Button saveBtnSach;
    @FXML private Button delBtnSach;

    public void onClickAddSachBtn(ActionEvent event) {
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
