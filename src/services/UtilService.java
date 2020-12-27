package services;

import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UtilService {
	public static void success() {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setHeaderText("Thành công");
		a.showAndWait();
	}
	
	public static void fail() {
		Alert a = new Alert(AlertType.ERROR);
		a.setHeaderText("Thất bại");
		a.showAndWait();
	}
	
	public static void popUp(String fileName) {
		try {
			Stage s = new Stage();
	        Scene scene = new Scene(FXMLLoader.load(UtilService.class.getResource("/view/" + fileName +".fxml")));
			s.setScene(scene);
			s.show();
			s.centerOnScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
