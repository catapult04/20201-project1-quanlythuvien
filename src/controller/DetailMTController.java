package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.Types;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import org.apache.poi.hwpf.model.OldSectionTable;

import com.jfoenix.controls.JFXDatePicker;

import application.MainQLTV;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.ChitietMuonModel;
import services.ChitietMuonService;
import services.SachModelService;
import services.UtilService;


public class DetailMTController implements Initializable{
	public static ObservableList<ChitietMuonModel> data;
	public static String maMT; 
	public static boolean isSelectedAll = false;
	
	@FXML private Label idMTLabel;
	@FXML private ComboBox<String> idSach;
	@FXML private Label tensach;
	@FXML private TextField des;
	@FXML private Button addBtn;
	
	@FXML private Button checkAllBtn;
	
	@FXML private TableView<ChitietMuonModel> table;
	@FXML private TableColumn<ChitietMuonModel, String> c1;
	@FXML private TableColumn<ChitietMuonModel, String> c2;
	@FXML private TableColumn<ChitietMuonModel, String> c3;
	@FXML private TableColumn<ChitietMuonModel, String> c4;
	@FXML private TableColumn<ChitietMuonModel, String> c5;
	@FXML private TableColumn<ChitietMuonModel, String> c6;
	@FXML private TableColumn<ChitietMuonModel, String> c7;
	
	@FXML private Button resetBtn;
	@FXML private DatePicker picker;
	@FXML private Button traBtn;
	@FXML private Button undoBtn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		maMT = MainController.choosingMTBean.getMtModel().getMaMT_20183955();
		
		idMTLabel.setText(maMT);
		
		SachModelService sachService = new SachModelService();
		idSach.setItems(sachService.getAllId());
		
		picker.setValue(MainQLTV.nowDate.toLocalDate());
		
		buildTable();
	}
	
	public void buildTable() {    	
    	//dinh dang
		c1.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getMasach_20183955()));
    	c2.setCellValueFactory(cell-> new SimpleStringProperty(String.valueOf(cell.getValue().getNgaytra_20183955())));
    	c3.setCellValueFactory(cell-> new SimpleStringProperty(String.valueOf(cell.getValue().getTienphat_20183955())));
    	c4.setCellValueFactory(cell-> new SimpleStringProperty(cell.getValue().getGhichu_20183955()));
    	c5.setCellValueFactory(new PropertyValueFactory<>("saveBtn"));
    	c6.setCellValueFactory(new PropertyValueFactory<>("delBtn"));
    	c7.setCellValueFactory(new PropertyValueFactory<>("traBtn"));
    
    	//dinh dang: cho phep sua cac o
    	c3.setCellFactory(TextFieldTableCell.forTableColumn());
      	c3.setOnEditCommit(
      		new EventHandler<CellEditEvent<ChitietMuonModel, String>>() {
      	        @Override
      	        public void handle(CellEditEvent<ChitietMuonModel, String> t) {
      	        	int pos = t.getTablePosition().getColumn();
      	            ((ChitietMuonModel) t.getTableView().getItems().get(t.getTablePosition().getRow())).setField(pos, t.getNewValue());
      	        }
      	    }
      	);
      	c4.setCellFactory(TextFieldTableCell.forTableColumn());
      	c4.setOnEditCommit(
      		new EventHandler<CellEditEvent<ChitietMuonModel, String>>() {
      	        @Override
      	        public void handle(CellEditEvent<ChitietMuonModel, String> t) {
      	        	int pos = t.getTablePosition().getColumn();
      	            ((ChitietMuonModel) t.getTableView().getItems().get(t.getTablePosition().getRow())).setField(pos, t.getNewValue());
      	        }
      	    }
      	);
      	table.setEditable(true);
    	
    	//do du lieu
    	onResetBtn();
	}
	
	public void onIdSach() {
		SachModelService ser = new SachModelService();
		tensach.setText(ser.getNameById(idSach.getValue()));
	}
	
	public void onResetBtn() {
		ChitietMuonService service = new ChitietMuonService();
		data = service.selectById(maMT);
    	table.setItems(data);
	}
	
	public void onAddBtn() {
		try {
			SachModelService sachService = new SachModelService();
			String masach = idSach.getValue();
			if(sachService.getTrangthaiById(masach).equals("Có sẵn")) {
				ChitietMuonService ser = new ChitietMuonService();
				ChitietMuonModel model = new ChitietMuonModel(maMT, idSach.getValue(), null, 0, null);
				if(ser.insert(model)==true) {
					data.add(model);
					sachService.setTrangthaiById(masach, "Đang mượn");
				}
			} else {
				UtilService.fail("Sách này đã được mượn!");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onCheckAll() {
		for(int i=0; i<data.size(); i++) {
			data.get(i).getTraBtn().setSelected(!isSelectedAll);
		}
		isSelectedAll= !isSelectedAll;
	}
	
	public void onTraBtn() {
		SachModelService sachService = new SachModelService();
		ChitietMuonService ctMTService = new ChitietMuonService();
		
		for(int i=0; i<data.size(); i++) {
			ChitietMuonModel model = data.get(i); 
			if(model.getTraBtn().isSelected()) {
				String masach = model.getMasach_20183955();
				String nowStatus = sachService.getTrangthaiById(masach);
				if( ! nowStatus.equals("Có sẵn")){
					data.get(i).oldStatus = nowStatus; 
					sachService.setTrangthaiById(masach, "Có sẵn");
					Date ngaytra = Date.valueOf(picker.getValue());
					ctMTService.setNgaytraById(maMT, masach, ngaytra);
					
					ChitietMuonModel newModel = data.get(i);
					newModel.setNgaytra_20183955(ngaytra);
					data.remove(i);
					data.add(newModel);
				}
			} 
		}
		UtilService.success("Đã cập nhật ngày trả trong bảng");
	}
	
	public void onUndoBtn() {
		SachModelService sachService = new SachModelService();
		ChitietMuonService ctMTService = new ChitietMuonService();
		
		for(int i=0; i<data.size(); i++) {
			ChitietMuonModel model = data.get(i); 
			if(model.getTraBtn().isSelected()) {
				String masach = model.getMasach_20183955();
				String nowStatus = sachService.getTrangthaiById(masach);
				if(nowStatus.equals("Có sẵn") && (!model.oldStatus.isEmpty())) {
					sachService.setTrangthaiById(masach, model.oldStatus);
					ctMTService.setNgaytraById(maMT, masach, null);
					
					ChitietMuonModel newModel = data.get(i);
					newModel.setNgaytra_20183955(null);
					data.remove(i);
					data.add(newModel);
				}
			}
		}
		UtilService.success("Chú ý: Sách đã được người khác mượn sẽ không thể hoàn tác");
	}
}
