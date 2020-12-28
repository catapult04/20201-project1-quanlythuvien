package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import application.MainQLTV;
import model.DocgiaModel;
import model.Model;
import model.MuontraBean;
import model.SachModel;
import model.ThongkeModel;
import model.ThuthuModel;
import services.ChitietMuonService;
import services.ConnService;
import services.MuontraBeanService;
import services.SachModelService;
import services.ThaotacFile;
import services.ThongkeService;
import services.UtilService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
		onTabSach();
		
		onTabHome();
		
		buildMT();
		
		ConnService.buildTable("docgia_minhhn", tableDG, DocgiaModel.LIST_FIELDS_NAME, "select * from docgia_minhhn");
		buildRadioBtnsDG();
		
		ConnService.buildTable("thuthu_minhhn", tableTT, ThuthuModel.LIST_FIELDS_NAME, "select * from thuthu_minhhn");
		buildRadioBtnsTT();
		buildAccountInfo();
		
		formatTableTK();
	}	
	
	
	/*******************
     * home tab
     *******************/
	@FXML private Label helloLabel;
	@FXML private Label usernameLabel;
	@FXML private JFXButton signOutBtn;
	@FXML private Label num1;
	@FXML private Label num2;
	@FXML private Label num3;
	@FXML private Label num4;
	@FXML private Label num5;
	@FXML private Label num6;
	@FXML private Label datenow;
	
	public void onTabHome() {
		helloLabel.setText("Xin chào, " + MainQLTV.tenTT);
		usernameLabel.setText(MainQLTV.username);
		num1.setText(String.valueOf(ConnService.count("select count(Masach_20183955) from sach_minhhn")));
		num2.setText(String.valueOf(ConnService.count("select count(Masach_20183955) from sach_minhhn where Trangthaisach_20183955='Có sẵn'")));
		num3.setText(String.valueOf(ConnService.count("select count(Masach_20183955) from sach_minhhn where Trangthaisach_20183955='Đang mượn'")));
		num4.setText(String.valueOf(ConnService.count("select count(Masach_20183955) from sach_minhhn where Trangthaisach_20183955='Quá hạn'")));
		num5.setText(String.valueOf(ConnService.count("select count(MaDG_20183955) from docgia_minhhn")));
		num6.setText(String.valueOf(ConnService.count("select count(MaMT_20183955) from muontra_minhhn")));
		String datetime = java.time.LocalDate.now().toString();
		String day = datetime.substring(8,10);
		String mon = datetime.substring(5,7);
		String yea = datetime.substring(0,4);
		datenow.setText("Ngày " + day + "/" + mon + "/" + yea);
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
     * sach tab
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
    
    @FXML private TextField traTfSach;
    @FXML private Button traBtnSach;
    @FXML private Button resetBtnSach2;
    @FXML private Label resultLabelSach;
    
    @FXML private TextField search1Sach;
    @FXML private TextField search2Sach;
    @FXML private TextField search3Sach;
    @FXML private TextField search4Sach;
    @FXML private TextField search5Sach;
    @FXML private TextField search6Sach;
    @FXML private Button searchBtnSach;
    @FXML private Button resetBtnSach;
    
    ToggleGroup trangthaisach = new ToggleGroup();
    @FXML private RadioButton radio1Sach;
    @FXML private RadioButton radio2Sach;
    @FXML private RadioButton radio3Sach;
    @FXML private RadioButton radio4Sach;
    
    @FXML private Button addBtnSach;
    @FXML private Button xuatBtnSach;
    @FXML private Button add2BtnSach;
    
    @FXML private Label countSach;
    @FXML private Button countBtn;
    
    public void onTabSach() {
    	SachModelService sachService = new SachModelService();
		sachService.updateTrangthai_Tienphat();
		ConnService.buildTable("sach_minhhn", tableSach, SachModel.LIST_FIELDS_NAME, "select * from sach_minhhn");
		buildRadioBtnsSach();
    }
    
    public void onTraBtnSach() {
    	ChitietMuonService ctMTService = new ChitietMuonService();
    	resultLabelSach.setText("Kết quả: " + ctMTService.getIdByMasach(traTfSach.getText()));
    }
    
    public void onResetBtnSach2() {
    	traTfSach.clear();
    	ConnService.buildTable("sach_minhhn", tableSach, SachModel.LIST_FIELDS_NAME, "select * from sach_minhhn");
    }
    
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
    	
    	RadioButton rb = (RadioButton)trangthaisach.getSelectedToggle(); 
        if (rb != null) { 
            String s = rb.getText();
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
		radio1Sach.setToggleGroup(trangthaisach);
		radio2Sach.setToggleGroup(trangthaisach);
		radio3Sach.setToggleGroup(trangthaisach);
		radio4Sach.setToggleGroup(trangthaisach);
		radio1Sach.setSelected(true);
		trangthaisach.selectedToggleProperty().addListener(new ChangeListener<Toggle>() { 
			@Override
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o1, Toggle o2) 
            { 
                onSearchBtnSach();
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
//    	ThaotacFile.ghiFile();
    }
    
    public void onXuatBtnSach() {
    	ThaotacFile.ghiFile("bangsach");
    }
    
    public void onCountBtn() {
    	countSach.setText(String.valueOf(dataSach.size()));
    }
    

    /************************
     * muon tra tab
     ************************/
    public static ObservableList<MuontraBean> dataMT;
    public static MuontraBean choosingMTBean;
    
    @FXML private TableView<MuontraBean> tableMT;
    @FXML private TableColumn<MuontraBean, String> c1MT;
    @FXML private TableColumn<MuontraBean, String> c2MT;
    @FXML private TableColumn<MuontraBean, String> c3MT;
    @FXML private TableColumn<MuontraBean, String> c4MT;
    @FXML private TableColumn<MuontraBean, String> c5MT;
    @FXML private TableColumn<MuontraBean, String> c6MT;
    @FXML private TableColumn<MuontraBean, String> c7MT;
    @FXML private TableColumn<MuontraBean, String> c8MT;
    @FXML private TableColumn<MuontraBean, String> c9MT;
    @FXML private TableColumn<MuontraBean, String> c10MT;
    
    @FXML private TextField search1MT;
    @FXML private ComboBox<String> search2MT;
    @FXML private ComboBox<String> search3MT;
    @FXML private DatePicker muonfrom;
    @FXML private DatePicker muonto;
    @FXML private DatePicker henfrom;
    @FXML private DatePicker hento;
    @FXML private Button searchBtnMT;
    @FXML private Button resetBtnMT;
    
    @FXML private Button addBtnMT;
    @FXML private Button addBtnMT2;
    @FXML private Button xuatBtnMT;
    @FXML private JFXToggleButton quahanToggle;
    
    public void onSearchBtnMT() {
    	MuontraBeanService mtBeanService = new MuontraBeanService();
    	Date muonfrom_ = muonfrom.getValue()!=null ? Date.valueOf(muonfrom.getValue()) : Date.valueOf("0000-1-1");
    	Date muonto_ = muonto.getValue()!=null ? Date.valueOf(muonto.getValue()) : Date.valueOf("9999-12-31");
    	Date henfrom_ = henfrom.getValue()!=null ? Date.valueOf(henfrom.getValue()) : Date.valueOf("0000-1-1");
    	Date hento_ = hento.getValue()!=null ? Date.valueOf(hento.getValue()) : Date.valueOf("9999-12-31");
    	dataMT = mtBeanService.getAll(search1MT.getText(), search2MT.getValue(), search3MT.getValue(), muonfrom_, muonto_, henfrom_, hento_);
    	tableMT.setItems(dataMT);
    }
    
    public void onResetBtnMT() {
    	MuontraBeanService mtBeanService = new MuontraBeanService();
    	dataMT = mtBeanService.getAll();
    	tableMT.setItems(dataMT);
    	
    	buildComboBoxMT();
    	quahanToggle.setSelected(false);
    }
    
    public void onQuahanToggle() {
    	MuontraBeanService ser = new MuontraBeanService();
    	if(quahanToggle.isSelected()==true) {
    		dataMT = ser.getQuaHan();
    		tableMT.setItems(dataMT);
    	} else {
    		dataMT = ser.getAll();
    		tableMT.setItems(dataMT);
    	}
    }
    
    public void onXuatBtnMT() {
    	
    }
    
    public void onAddBtnMT2() {
    	
    }
    
    public void buildComboBoxMT() {
    	MuontraBeanService ser = new MuontraBeanService();
    	search2MT.setItems(ser.getAllMaDG()); search2MT.setValue("");
    	search3MT.setItems(ser.getAllMaTT()); search3MT.setValue("");
    }

    public void buildMT() {
    	buildComboBoxMT();
    	
    	MuontraBeanService mtBeanService = new MuontraBeanService();
    	dataMT = mtBeanService.getAll();
    	
    	c1MT.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getMtModel().getMaMT_20183955()));
    	c2MT.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getMtModel().getMaDG_20183955()));
    	c3MT.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getTenDG_20183955()));
    	c4MT.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getMtModel().getMaTT_20183955()));
    	c5MT.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getTenTT_20183955()));
    	c6MT.setCellValueFactory(cell-> new SimpleStringProperty(String.valueOf(cell.getValue().getMtModel().getNgaymuon_20183955())));
    	c7MT.setCellValueFactory(cell-> new SimpleStringProperty(String.valueOf(cell.getValue().getMtModel().getNgayhentra_20183955())));
    	c8MT.setCellValueFactory(cell-> new SimpleStringProperty(String.valueOf(cell.getValue().getMtModel().getTiencoc_20183955())));
    	c9MT.setCellValueFactory(new PropertyValueFactory<>("updateBtn"));
    	c10MT.setCellValueFactory(new PropertyValueFactory<>("delBtn"));
    	
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
    
    public void onAddBtnMT() {
    	try {
        	Stage s = new Stage();
        	Scene scene = new Scene(FXMLLoader.load(getClass() .getResource("/view/AddMTForm.fxml")));
        	s.setScene(scene);
        	s.centerOnScreen();
        	s.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
     
    
    /************************
     * Doc gia Tab
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
    @FXML private TableColumn<Model, JFXButton> c9DG;
    @FXML private TableColumn<Model, JFXButton> c10DG;
    
    @FXML private TextField search1DG;
    @FXML private TextField search2DG;
    @FXML private TextField search3DG;
    @FXML private TextField search4DG;
    @FXML private TextField search5DG;
    @FXML private TextField search6DG;
    @FXML private TextField search7DG;
    @FXML private TextField search8DG;
    @FXML private Button searchBtnDG;
    @FXML private Button resetBtnDG;
    
    @FXML private RadioButton radio1DG;
    @FXML private RadioButton radio2DG;
    @FXML private RadioButton radio3DG;
    @FXML private RadioButton radio4DG;
    
    @FXML private Button addBtnDG;
    @FXML private Button add2BtnDG;
    
    public void onSearchBtnDG() {
    	String from = search7DG.getText().length()==4 ? search7DG.getText() : "0000";
    	String to = search8DG.getText().length()==4 ? search8DG.getText() : "9999";
    	String SQL="select * from docgia_minhhn where "
    			+ "MaDG_20183955 LIKE '%" + search1DG.getText() + "%'"
    			+ " and TenDG_20183955 LIKE '%" + search2DG.getText() + "%'"
    			+ " and Diachi_20183955 LIKE '%" + search3DG.getText() + "%'"
    			+ " and CMND_20183955 LIKE '%" + search4DG.getText() + "%'"
    			+ " and Email_20183955 LIKE '%" + search5DG.getText() + "%'"
    			+ " and Dthoai_20183955 LIKE '%" + search6DG.getText() + "%'"
    			+ " and Namsinh_20183955>='" + from + "'"
    			+ " and Namsinh_20183955<='" + to + "'";
    	ConnService.buildTable("docgia_minhhn", tableDG, DocgiaModel.LIST_FIELDS_NAME, SQL);
    }
    
    public void onResetBtnDG() {
    	search1DG.clear();
    	search2DG.clear();
    	search3DG.clear();
    	search4DG.clear();
    	search5DG.clear();
    	search6DG.clear();
    	search7DG.clear();
    	search8DG.clear();
    	radio1DG.setSelected(true);
    	ConnService.buildTable("docgia_minhhn", tableDG, DocgiaModel.LIST_FIELDS_NAME, "select * from docgia_minhhn");
    }
    
    public void buildRadioBtnsDG() {
    	ToggleGroup gioitinh = new ToggleGroup();
		radio1DG.setToggleGroup(gioitinh);
		radio2DG.setToggleGroup(gioitinh);
		radio3DG.setToggleGroup(gioitinh);
		radio4DG.setToggleGroup(gioitinh);
		radio1DG.setSelected(true);
		gioitinh.selectedToggleProperty().addListener(new ChangeListener<Toggle>() { 
			@Override
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o1, Toggle o2) 
            { 
                RadioButton rb = (RadioButton)gioitinh.getSelectedToggle(); 
                if (rb != null) { 
                    String s = rb.getText();
                    onSearchBtnDG();
                    if(! s.equals("Tất cả")) {
                    	int sz = dataDG.size();
                    	for(int i=0; i<sz; i++) 
                    		if(! ((DocgiaModel) dataDG.get(i)).getGioitinh_20183955().equals(s)) {
                    			dataDG.remove(dataDG.get(i));
                    			sz--;
                    			i--;
                    		}
                    }
                } 
            }
        });
    }
    
    public void onAddBtnDG(ActionEvent event) {
    	try {
		    Scene addForm = new Scene(FXMLLoader.load(getClass().getResource("/view/AddDGForm.fxml")));
	        Stage stage = new Stage();
	        stage.setScene(addForm);
	        stage.centerOnScreen();	
	        stage.show();        
	    }catch (IOException io){
	        io.printStackTrace();
	    }
    }
    
    public void onAdd2BtnDG(ActionEvent event) {
    	
    }
    
    public void onXuatBtnDG() {
    	
    }

    /************************
     * Thu thu Tab
     ************************/
    @FXML private TableView tableTT;
    public ObservableList<Model> dataTT;
    public String oldIdTT, oldIdUser;
    
    @FXML private TableColumn c1TT;
    @FXML private TableColumn c2TT;
    @FXML private TableColumn c3TT;
    @FXML private TableColumn c4TT;
    @FXML private TableColumn c5TT;
    @FXML private TableColumn c6TT;
    @FXML private TableColumn c7TT;
    
    @FXML private TextField search1TT;
    @FXML private TextField search2TT;
    @FXML private TextField search3TT;
    @FXML private TextField search4TT;
    @FXML private TextField search5TT;
    @FXML private TextField search6TT;
    @FXML private TextField search7TT;
    
    @FXML private RadioButton radio1TT;
    @FXML private RadioButton radio2TT;
    @FXML private RadioButton radio3TT;
    @FXML private RadioButton radio4TT;
    
    @FXML private TextField tf1;
    @FXML private TextField tf2;
    @FXML private TextField tf3;
    @FXML private TextField tf4;
    @FXML private ComboBox tf5;
    @FXML private TextField tf6;
    @FXML private TextField tf7;
    @FXML private TextField tf8;
    @FXML private TextField tf9;
    @FXML private Button btn10;
    @FXML private Button btn11;
    
    public void onSearchBtnTT() {
    	String from = search6TT.getText().length()==4 ? search6TT.getText() : "0000";
    	String to = search7TT.getText().length()==4 ? search7TT.getText() : "9999";
    	String SQL="select * from thuthu_minhhn where "
    			+ "MaTT_20183955 LIKE '%" + search1DG.getText() + "%'"
    			+ " and Ten_20183955 LIKE '%" + search2DG.getText() + "%'"
    			+ " and CMND_20183955 LIKE '%" + search2DG.getText() + "%'"
    			+ " and Email_20183955 LIKE '%" + search5DG.getText() + "%'"
    			+ " and Dthoai_20183955 LIKE '%" + search6DG.getText() + "%'"
    			+ " and Namsinh_20183955>='" + from + "'"
    			+ " and Namsinh_20183955<='" + to + "'";
    	ConnService.buildTable("thuthu_minhhn", tableTT, ThuthuModel.LIST_FIELDS_NAME, SQL);
    }
    
    public void onResetBtnTT() {
    	search1TT.clear();
    	search2TT.clear();
    	search3TT.clear();
    	search4TT.clear();
    	search5TT.clear();
    	search6TT.clear();
    	search7TT.clear();
    	radio1TT.setSelected(true);
    	ConnService.buildTable("thuthu_minhhn", tableTT, ThuthuModel.LIST_FIELDS_NAME, "select * from thuthu_minhhn");
    }
    
    public void buildRadioBtnsTT() {
    	ToggleGroup gioitinh = new ToggleGroup();
		radio1TT.setToggleGroup(gioitinh);
		radio2TT.setToggleGroup(gioitinh);
		radio3TT.setToggleGroup(gioitinh);
		radio4TT.setToggleGroup(gioitinh);
		radio1TT.setSelected(true);
		gioitinh.selectedToggleProperty().addListener(new ChangeListener<Toggle>() { 
			@Override
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o1, Toggle o2) 
            { 
                RadioButton rb = (RadioButton)gioitinh.getSelectedToggle(); 
                if (rb != null) {
                    String s = rb.getText();
                    onSearchBtnTT();
                    if(! s.equals("Tất cả")) {
                    	int sz = dataTT.size();
                    	for(int i=0; i<sz; i++) 
                    		if(! ((ThuthuModel) dataTT.get(i)).getGioitinh_20183955().equals(s)) {
                    			dataTT.remove(dataTT.get(i));
                    			sz--;
                    			i--;
                    		}
                    }
                }
            }
        });
    }
    
    public void buildAccountInfo() {
    	tf1.setText(MainQLTV.username);
    	ObservableList<String> user = ConnService.select("user_minhhn", "username", MainQLTV.username);
    	ObservableList<String> TT = ConnService.select("thuthu_minhhn", "maTT_20183955", MainQLTV.maTT);
    	tf2.setText(user.get(2));
    	tf3.setText(TT.get(0));
    	tf4.setText(TT.get(1));
    	ObservableList<String> gioitinh = FXCollections.observableArrayList("Nam", "Nữ", "No3");
    	tf5.setItems(gioitinh);
    	tf5.setValue(TT.get(2));
    	tf6.setText(TT.get(3));
    	tf7.setText(TT.get(4));
    	tf8.setText(TT.get(5));
    	tf9.setText(TT.get(6));
    	oldIdTT = MainQLTV.maTT;
    	oldIdUser = MainQLTV.username;
    }
    
    public void onBtn10() {
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	a.setHeaderText("Bạn chắc chắn thay đổi thông tin?");
		Optional<ButtonType> option = a.showAndWait();
        if (option.get() == ButtonType.OK) {
        	ObservableList<String> tt = FXCollections.observableArrayList();
        	tt.add(tf3.getText());  
        	tt.add(tf4.getText()); 
        	tt.add(tf5.getValue().toString());
        	tt.add(tf6.getText());
        	tt.add(tf7.getText());
        	tt.add(tf8.getText());
        	tt.add(tf9.getText());
        	
        	ObservableList<String> user = FXCollections.observableArrayList();
        	user.add(tf1.getText());
        	user.add(tf3.getText());
        	user.add(tf2.getText());
        	
        	if(ConnService.update("thuthu_minhhn", tt, oldIdTT)==true && ConnService.update("user_minhhn", user, oldIdUser)==true) {
        		MainQLTV.maTT = tf3.getText(); 
        		MainQLTV.tenTT = tf4.getText(); helloLabel.setText("Xin chào, " + MainQLTV.tenTT);
        		MainQLTV.username = tf1.getText(); usernameLabel.setText(MainQLTV.username);
        		onResetBtnTT();
        	}
        } else {}
    }
    
    public void onBtn11() {
    	buildAccountInfo();
    	onResetBtnTT();
    }
    
    
    
    /************************
     * thong ke tab
     ************************/ 
    public static ObservableList<ThongkeModel> dataTK;
    
    @FXML private TableView<ThongkeModel> tableTK;
    @FXML private TableColumn<ThongkeModel, String> c1TK;
    @FXML private TableColumn<ThongkeModel, String> c2TK;
    @FXML private TableColumn<ThongkeModel, String> c3TK;
    
    @FXML private Label tenbangTK;
    @FXML private Label sumTK;
    @FXML private PieChart pieChart;
    
    @FXML private Button theloaiBtn;
    @FXML private Button NXBBtn;
    @FXML private Button namXBBtn;
    @FXML private Button tacgiaBtn;
    @FXML private Button statusBtn;
    @FXML private Button phieuTheoDGBtn;
    @FXML private Button DGTheoNSBtn;
    
    public void formatTableTK() {
    	c1TK.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNoidung()));
    	c2TK.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getSoluong())));
    	c3TK.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getTile())));
    }
    
    public void buildPieChart() {
    	pieChart.getData().clear();
    	for(int i=0; i<dataTK.size(); i++) {
    		PieChart.Data slice = new PieChart.Data(dataTK.get(i).getNoidung(), dataTK.get(i).getSoluong());
    		pieChart.getData().add(slice);
    	}
        pieChart.setLegendSide(Side.LEFT);
    }
    
    public void onTheloaiBtn() {
    	ThongkeService tkser = new ThongkeService();
    	dataTK = tkser.thongkeSachTheoTheloai();
    	tableTK.setItems(dataTK);
    	sumTK.setText(String.valueOf(tkser.total));
    	buildPieChart();
    	tenbangTK.setText(theloaiBtn.getText());
    }
    
    public void onNXBBtn() {
    	ThongkeService tkser = new ThongkeService();
    	dataTK = tkser.thongkeSach("NhaXB_20183955");
    	tableTK.setItems(dataTK);
    	sumTK.setText(String.valueOf(tkser.total));
    }
    
    public void onNamXBBtn() {
    	ThongkeService tkser = new ThongkeService();
    	dataTK = tkser.thongkeSach("NamXB_20183955");
    	tableTK.setItems(dataTK);
    	sumTK.setText(String.valueOf(tkser.total));
    	buildPieChart();
    }
    
    public void onTacgiaBtn() {
    	ThongkeService tkser = new ThongkeService();
    	dataTK = tkser.thongkeSach("Tacgia_20183955");
    	tableTK.setItems(dataTK);
    	sumTK.setText(String.valueOf(tkser.total));
    	buildPieChart();
    }
    
    public void onStatusBtn() {
    	ThongkeService tkser = new ThongkeService();
    	dataTK = tkser.thongkeSach("Trangthaisach_20183955");
    	tableTK.setItems(dataTK);
    	sumTK.setText(String.valueOf(tkser.total));
    	buildPieChart();
    }
    
    public void onPhieuTheoDGBtn() {
    	ThongkeService tkser = new ThongkeService();
    	dataTK = tkser.thongkePhieu("MaDG_20183955");
    	tableTK.setItems(dataTK);
    	sumTK.setText(String.valueOf(tkser.total));
    	buildPieChart();
    }
    
    public void onDGTheoNSBtn() {
    	ThongkeService tkser = new ThongkeService();
    	dataTK = tkser.thongkeDG("Namsinh_20183955");
    	tableTK.setItems(dataTK);
    	sumTK.setText(String.valueOf(tkser.total));
    	buildPieChart();
    }
}
