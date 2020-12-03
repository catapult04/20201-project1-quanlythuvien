package services;

import java.sql.*;

public class MyConnectionService {
	private String dbURL = "jdbc:mysql://localhost:3306/qlthuviendb";
	private String username = "root";
	private String password = "";
	private String tablename;
	public Connection conn;
	
	public MyConnectionService(String tableName) throws ClassNotFoundException {
		this.tablename = tableName;
		Class.forName("com.mysql.jdbc.Driver");
		this.conn = null;	
		try {			
			this.conn = DriverManager.getConnection(dbURL, username, password);
			if(conn != null)
				System.out.println("Kết nối thành công!\n");			
			else
				System.out.println("Kết nối thất bại!");		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
