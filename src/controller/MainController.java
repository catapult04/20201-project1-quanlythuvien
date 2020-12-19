package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import model.DocgiaModel;
import model.Model;
import model.MuontraModel;
import model.SachModel;
import model.ThuthuModel;
import services.ConnService;
import view.MainQLTV;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
	public MainController() {
		super();
		MainQLTV.control = this;
	}
	
	/***************
	 * For All
	 ***************/
	@Override //DOAN MA KHOI TAO MOI THU
	public void initialize(URL arg0, ResourceBundle arg1) {
		buildHomeTab();
		
		ConnService.buildTable("sach_minhhn", tableSach, SachModel.LIST_FIELDS_NAME, "select * from sach_minhhn");
		buildRadioBtnsSach();
		
//		ConnService.buildTable("muontra_minhhn", tableMT, columnsMT, SachModel.LIST_FIELDS_NAME, SachModel.NUMBER_FIELDS);
		
		ConnService.buildTable("docgia_minhhn", tableDG, DocgiaModel.LIST_FIELDS_NAME, "select * from docgia_minhhn");
	}	
	
	
	/*******************
     * Home tab
     *******************/
	@FXML private Label helloLabel;
	@FXML private Label usernameLabel;
	@FXML private JFXButton signOutBtn;
	
	public void buildHomeTab() {
		helloLabel.setText("Xin chào, " + MainQLTV.tenTT);
		usernameLabel.setText(MainQLTV.username);
	}
	
	public void onSignOutBtn(ActionEvent event) {
		try {
			Stage s = (Stage) signOutBtn.getScene().getWindow();
			Scene loginScene = new Scene(FXMLLoader.load(getClass() .getResource("/view/LoginScene.fxml")));
			s.setScene(loginScene);
			s.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
    /*******************
     * Sach Manager
     *******************/
	@FXML private TableView<Model> tableSach;
	public ObservableList<Model> dataSach;
    @FXML private TableColumn<Model, String> c1Sach;
    @FXML private TableColumn<Model, String> c2Sach;
    @FXML private TableColumn<Model, String> c3Sach;
    @FXML private TableColumn<Model, String> c4Sach;
    @FXML private TableColumn<Model, String> c5Sach;
    @FXML private TableColumn<Model, String> c6Sach;
    @FXML private TableColumn<Model, String> c7Sach;
    @FXML private TableColumn<Model, String> c8Sach;
    @FXML private TableColumn<Model, JFXButton> c9Sach;
    @FXML private TableColumn<Model, JFXButton> c10Sach;
    
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
    @FXML private Button add2BtnSach;
    
    @FXML private Label countSach;
    @FXML private Button countBtn;
    
    public void onSearchBtnSach() {
    	String from = search5Sach.getText().length()==4 ? search5Sach.getText() : "0000";
    	String to = search6Sach.getText().length()==4 ? search6Sach.getText() : "9999";
    	String SQL="select * from sach_minhhn where "
    			+ "Masach_20183955 LIKE '%" + search1Sach.getText() + "%'"
    			+ " and Tensach_20183955 LIKE '%" + search2Sach.getText() + "%'"
    			+ " and Tacgia_20183955 LIKE '%" + search3Sach.getText() + "%'"
    			+ " and NhaXB_20183955 LIKE '%" + search4Sach.getText() + "%'"
    			+ " and NamXB_20183955>='" + from + "'"
    			+ " and NamXB_20183955<='" + to + "'";
    	ConnService.buildTable("sach_minhhn", tableSach, SachModel.LIST_FIELDS_NAME, SQL);
    }
    
    public void onResetBtnSach() {
    	search1Sach.clear();
    	search2Sach.clear();
    	search3Sach.clear();
    	search4Sach.clear();
    	search5Sach.clear();
    	search6Sach.clear();
    	radio1Sach.setSelected(true);
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
                    onSearchBtnSach();
                    if(! s.equals("Tất cả")) {
                    	int sz = dataSach.size();
                    	for(int i=0; i<sz; i++) 
                    		if(! ((SachModel) dataSach.get(i)).getTrangthaisach_20183955().equals(s)) {
                    			dataSach.remove(dataSach.get(i));
                    			sz--;
                    			i--;
                    		}
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
    
    public void onAdd2BtnSach(ActionEvent event) {
    	
    }
    
    public void onCountBtn() {
    	countSach.setText(String.valueOf(dataSach.size()));
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
    public ObservableList<Model> dataDG;
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
