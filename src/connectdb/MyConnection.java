package connectdb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Statement;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JOptionPane;

//select, update, insert, delete
// cach su dung callable statement

public class MyConnection {
	private static final String CONFIG_FILE_NAME = "config.txt";
	private String dbURL;
	private String username ;
	private String password;
	private String tablename;
	
	private void updateConfig() {
		try {
			InputStream input = new FileInputStream(getClass().getResource(CONFIG_FILE_NAME).getPath());
			JsonReader jsonReader = Json.createReader(input);
		    JsonObject jsonObj = jsonReader.readObject();
		    
		    this.dbURL = jsonObj.getString("dbURL");
		    this.username = jsonObj.getString("username");
		    this.password = jsonObj.getString("password");
		    this.tablename = jsonObj.getString("tablename");
		    
		    jsonReader.close();
		    input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MyConnection() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSetMetaData meta = null;
		
		this.updateConfig();
		
		try {			
			//Tạo kết nối
			conn = DriverManager.getConnection(dbURL, username, password);
			if(conn != null)
				System.out.println("Kết nối thành công!\n");			
			else
				System.out.println("Kết nối thất bại!");
			
			//Câu lệnh select
			String sql = "select * from " + tablename;
			
			//Tạo đối tượng thực thi câu lệnh select
			st = conn.createStatement();
			
			//Thực thi select
			rs = st.executeQuery(sql);
			
			//Nếu không có dữ liệu trong bảng
			if(rs.isBeforeFirst() == false) {
				JOptionPane.showMessageDialog(null, "Bảng không có dữ liệu!");
				return;
			}
			
			//Xử lý dữ liệu
			meta = rs.getMetaData();
			int numberColumn = meta.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=numberColumn; i++) {
					System.out.println(meta.getColumnName(i) + ": " + rs.getObject(i).toString());
				}
				System.out.println();
			}			
			rs.close();
			st.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
