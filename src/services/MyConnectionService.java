package services;

import java.sql.*;

public class MyConnectionService {
	private static String dbURL = "jdbc:mysql://localhost:3306/qlthuviendb";
	private static String username = "root";
	private static String password = "";
	public Connection conn;
	
	public MyConnectionService() throws ClassNotFoundException {
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
