package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
	 
	   @FXML
	   private Button myButton;
	  
	   @FXML
	   private TextField myTextField;
	  
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	       // TODO (don't really need to do anything here).	      
	   }
	 
	   // When user click on myButton
	   // this method will be called.
	   // Khi người dùng nhấn vào Button myButton
	   // phương thức này sẽ được gọi
	   public void showDateTime(ActionEvent event) {	      
	         // Dữ liệu Model
	        String dateTimeString = "Không biết hôm nay là ngày gì nữa";
	  
	        // Hiển thị lên VIEW.
	        myTextField.setText(dateTimeString);
	      
	   }
	  
	}
