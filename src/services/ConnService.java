package services;

import java.sql.*;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.DocgiaModel;
import model.Model;
import model.SachModel;
import model.ThuthuModel;
import model.UserModel;
import view.MainQLTV;

public class ConnService {
	private static String dbURL = "jdbc:mysql://localhost:3306/qlthuviendb";
	private static String username = "root";
	private static String password = "";
	public static Connection conn;
	
	public ConnService() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = null;	
		try {			
			conn = DriverManager.getConnection(dbURL, username, password);
			if(conn != null)
				System.out.println("Kết nối thành công!\n");			
			else
				System.out.println("Kết nối thất bại!");		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//used for buildTable method bellow
	public static Model createModel(String tableName, ObservableList<String> input) {
		Model tmp;
		switch(tableName) {
			case "sach_minhhn": tmp = new SachModel(input); break;
			case "docgia_minhhn": tmp = new DocgiaModel(input); break;
			default: tmp = new ThuthuModel(input); break;
		}
		return tmp;
	}
	
	//build data for Table by SQL
	public static void buildTable(String tableName, TableView table, ObservableList<String> LIST_FIELDS_NAME, String SQL){
        try{        	
          ResultSet rs = conn.createStatement().executeQuery(SQL);

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
          switch(tableName) {
          case "sach_minhhn": MainQLTV.control.dataSach = data; break;
          case "docgia_minhhn": MainQLTV.control.dataDG = data; break;
          case "thuthu_minhhn": MainQLTV.control.dataTT = data; break;
          }
          
          //2. set property for columns
          ObservableList<TableColumn<Model, String>> columns = table.getColumns();
          int sz = LIST_FIELDS_NAME.size();
          for(int i = 0; i < sz-2; i++) {
          	columns.get(i).setCellValueFactory(new PropertyValueFactory<>(LIST_FIELDS_NAME.get(i)));
          	if(! tableName.equals("thuthu_minhhn")) {
          		columns.get(i).setCellFactory(TextFieldTableCell.forTableColumn());
          		columns.get(i).setOnEditCommit(
          			new EventHandler<CellEditEvent<Model, String>>() {
          		        @Override
          		        public void handle(CellEditEvent<Model, String> t) {
          		        	int pos = t.getTablePosition().getColumn();
          		            ((Model) t.getTableView().getItems().get(t.getTablePosition().getRow())).setField(pos, t.getNewValue());
          		        }
          		    }
          		);
          	}
          }
          for(int i = sz-2; i <sz ; i++) {
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
	
	//Cau lenh select from where
	public static ObservableList<String> select(String tableName, String field, String condition) {
		try {
			ObservableList<String> res = FXCollections.observableArrayList();
			
			String SQL = "select * from " + tableName + " where " + field + "='" + condition + "'";
			ResultSet rs = conn.createStatement().executeQuery(SQL);
			rs.next();
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
				res.add(rs.getString(i));
			}
			return res;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Cau lenh insert into
	public static boolean insertInto(String tableName, ObservableList<String> info) {
		String SQL = "insert into " + tableName + " values (";
		int i;
		for(i=0; i<info.size()-1; i++) {
			SQL = SQL.concat("'" + info.get(i) + "',");
		}
		SQL = SQL.concat("'" + info.get(i) + "');");
		try {
			conn.createStatement().execute(SQL);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Thêm thành công!");
			alert.showAndWait();
			return true;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Có lỗi xảy ra khi insert!");
			alert.showAndWait();
			return false;
		}
	}
	
	//Cau lenh delete
	public static boolean delete(String tableName, String str) {
		String fieldName;
		switch(tableName) {
		case "sach_minhhn" : fieldName="Masach_20183955"; break;
		case "docgia_minhhn": fieldName="MaDG_20183955"; break;
		case "thuthu_minhhn": fieldName="MaTT_20183955"; break;
		default: fieldName="";
		}
		String SQL = "delete from " + tableName + " where " + fieldName + "=" + "'" + str + "'";
		try {
			conn.createStatement().execute(SQL);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Xóa thành công!");
			alert.showAndWait();
			return true;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Có lỗi xảy ra khi delete!");
			alert.showAndWait();
			return false;
		}
	}
	
	//cau lenh update
	public static boolean update(String tableName, ObservableList<String> input, String oldId) {
		ObservableList<String> LFN = FXCollections.observableArrayList();
		switch(tableName) {
		case "sach_minhhn" :  LFN=SachModel.LIST_FIELDS_NAME; break;
		case "docgia_minhhn": LFN=DocgiaModel.LIST_FIELDS_NAME; break;
		case "thuthu_minhhn": LFN=ThuthuModel.LIST_FIELDS_NAME; break;
		case "user_minhhn":   LFN=UserModel.LIST_FIELDS_NAME; break;
		default: LFN=SachModel.LIST_FIELDS_NAME; break;
		}
		
		String SQL = "update " + tableName + " set ";
		int i;
		for(i=0; i<input.size()-1; i++) {
			SQL = SQL.concat(LFN.get(i) + "='" + input.get(i) + "',");
		}
		SQL = SQL.concat(LFN.get(i) + "='" + input.get(i) + "'" + " where " + LFN.get(0) + "='" +  oldId + "'");
		try {
			conn.createStatement().execute(SQL);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Update thành công!");
			alert.showAndWait();
			return true;
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			e.printStackTrace();
			alert.setContentText("Có lỗi xảy ra khi update!");
			alert.showAndWait();
			return false;
		}
	}
	
	//counting
	public static String count(String SQL) {
		try {
			ResultSet rs = conn.createStatement().executeQuery(SQL);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
	}
	
}
