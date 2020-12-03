package view;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/*
 * 1.Build path for project: 	add all jar files in javafx/lib to Modules path
 * 2.Set run configuration for main method: Arguments -> VM Arguments:	   --module-path "\\paste path to folder where save jar files" --add-modules javafx.controls,javafx.fxml
 * --module-path "D:\20201\Project I\mylib\javafx\lib" --add-modules javafx.controls,javafx.fxml
 * 
 * 3.In each fxml file, add:
 *   + fx:controller="application.Controller"  for Container
 *   + fx:id="..."  for elements that need controlled
 *   + onAction="#name-Of-Method-Defined-In-Controller"  for elements needing controlled
 * 4.fx:id of elements in fxml must be the same name as declaring in Controller 
 */

public class MainQLTV extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Đọc file fxml và vẽ giao diện.
	        Scene loginScene = new Scene(FXMLLoader.load(getClass() .getResource("/view/LoginScene.fxml")));
			//loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Quản lý thư viện");
			primaryStage.setScene(loginScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
