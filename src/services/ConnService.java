package services;

import java.sql.*;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DocgiaModel;
import model.Model;
import model.SachModel;
import model.ThuthuModel;
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
	
	//this is used for buildTable method bellow
	public static Model createModel(String tableName, ObservableList<String> input) {
		Model tmp;
		switch(tableName) {
			case "sach_minhhn": tmp = new SachModel(input); break;
			case "docgia_minhhn": tmp = new DocgiaModel(input); break;
			default: tmp = new ThuthuModel(input); break;
		}
		return tmp;
	}
	
	//build Table by SQL
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
	
	//Cau lenh insert
	public static void insertInto(String tableName, ObservableList<String> info) {
		String SQL = "insert into " + tableName + " values (";
		int i;
		for(i=0; i<info.size()-1; i++) {
			SQL = SQL.concat("'" + info.get(i) + "',");
		}
		SQL = SQL.concat("'" + info.get(i) + "');");
//		System.out.println(SQL);
		try {
			conn.createStatement().execute(SQL);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Thêm thành công!");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Có lỗi xảy ra!");
			alert.showAndWait();
//			e.printStackTrace();
		}
	}
}
