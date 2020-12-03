package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import com.mysql.jdbc.Statement;

import model.Model;
import model.MuontraModel;
import model.SachModel;
import services.MyConnectionService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class SachManageController implements Initializable {
	/***************
	 * Start FOR ALL
	 ***************/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buildData("sach_minhhn", tableSach, SachModel.LIST_FIELDS_NAME);
//		buildData("muontra_minhhn", tableMT, columnsMT, SachModel.LIST_FIELDS_NAME, SachModel.NUMBER_FIELDS);
	}
	
	
	public Model createModel(String type, ObservableList<String> input) {
		Model tmp;
		switch(type) {
			case "sach": tmp = new SachModel(input); break;
			default: tmp = new ThuthuModel(input); break;
		}
		return tmp;
	}
	public void buildData(String tableName, TableView table, ObservableList<String> LIST_FIELDS_NAME){
        try{        	
          MyConnectionService connService = new MyConnectionService(tableName);
          //SQL FOR SELECTING ALL OF BOOK
          String SQL = "SELECT * from " + tableName;
          //ResultSet
          ResultSet rs = connService.conn.createStatement().executeQuery(SQL);

          //1. Data added to ObservableList
          ObservableList<SachModel> data = FXCollections.observableArrayList();
          ObservableList<String> row;
          while(rs.next()){
        	  //Iterate Row
        	  row = FXCollections.observableArrayList();;
              for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                  //Iterate Column
                  row.add(rs.getString(i));
              }
              data.add(new SachModel(row));
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
    public ObservableList<TableColumn> columnsSach = FXCollections.observableArrayList(c1Sach,c2Sach,c3Sach,c4Sach,c5Sach,c6Sach,c7Sach,c8Sach);
    
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
    private ObservableList<TableColumn> columnsMT = FXCollections.observableArrayList(c1MT,c2MT,c3MT,c4MT,c5MT,c6MT);
    private final int NUMBER_COMLUMNS_MT = 6;
    
    @FXML private TextField c1AddMT;
    @FXML private TextField c2AddMT;
    @FXML private TextField c3AddMT;
    @FXML private TextField c4AddMT;
    @FXML private TextField c5AddMT;
    @FXML private TextField c6AddMT;
    @FXML private Button addBtnMT;

    
}
