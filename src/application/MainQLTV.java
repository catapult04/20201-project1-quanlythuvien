package application;
	
import java.sql.Date;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import services.ConnService;
import javafx.scene.Scene;

/*
 * 1.Build path for project: 	add all jar files in javafx/lib to Modules path
 *  2.Set run configuration for main method: Arguments -> VM Arguments:	   --module-path "\\paste path to folder where save jar files" --add-modules javafx.controls,javafx.fxml
 * --module-path "D:\20201\Project1\mylib\javafx\lib" --add-modules javafx.controls,javafx.fxml
 * 
 * 3.In each fxml file, add:
 *   + fx:controller="application.Controller"  for Container
 *   + fx:id="..."  for elements that need controlled
 *   + onAction="#name-Of-Method-Defined-In-Controller"  for elements needing controlled
 * 4.fx:id of elementshoang minh in fxml must be the same name as declaring in Controller 
 */

public class MainQLTV extends Application {
	public static Date nowDate;
	public static String nowString;
	public static String maTT, tenTT, username;
	public static MainController control;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			ConnService.conn = ConnService.getConnection();
			long millis = System.currentTimeMillis();
			nowDate = new Date(millis);
			nowString = nowDate.toString();
			System.out.println(nowString);
			
	        Scene loginScene = new Scene(FXMLLoader.load(getClass().getResource("/view/LoginScene.fxml")));
			//loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(loginScene);
	        primaryStage.setTitle("QUẢN LÝ THƯ VIỆN");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
