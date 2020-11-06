package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	   @FXML private Button btnLogin;
	   
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).	      
	   }
	 
	   public void showDateTime(ActionEvent event) {	      
	         // Dữ liệu Model
	        
	        // Hiển thị lên VIEW.
	      
	   } 
	   
	   public void onLogin(ActionEvent event) {
		   try {
			    Scene mainScene = new Scene(FXMLLoader.load(getClass() .getResource("/application/MainScene.fxml")));
		        Stage stage = (Stage) btnLogin.getScene().getWindow();
		        stage.setScene(mainScene);
		        stage.centerOnScreen();		        
		    }catch (IOException io){
		        io.printStackTrace();
		    }
	   }
	}
