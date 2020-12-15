package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.MainQLTV;

public class LoginController implements Initializable {
	   @FXML private JFXButton btnLogin;
	   @FXML private Button closeBtn;
	   @FXML private JFXTextField userTextField;
	   @FXML private JFXPasswordField passTextField;
	   @FXML private JFXButton loginBtn;
	   @FXML private Label forgotPassLabel;
	   @FXML private Label errorLabel1;
	   @FXML private Label errorLabel2;
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {	      
	   }
	   
	   public void onClose(ActionEvent event) {
		   System.exit(0);
	   }
	   
	   public void onLogin(ActionEvent event) {
		   errorLabel1.setText(null);
		   errorLabel2.setText(null);
		   try {
			    String SQL = "SELECT maTT,password FROM user_minhhn WHERE username=" + "'" + userTextField.getText() + "'";
			    ResultSet rs = MainQLTV.conn.createStatement().executeQuery(SQL);
			    try {
			    	rs.next();
			    	if(rs.getString(2).equals(passTextField.getText())) {
			    		MainQLTV.maTT = rs.getString(1);
			    		Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("/view/MainScene.fxml")));
				        Stage stage = (Stage) forgotPassLabel.getScene().getWindow();
				        stage.setScene(mainScene);
				        stage.centerOnScreen();
			    	} else {
			    		errorLabel2.setText("Sai mật khẩu!");
			    	}
			    	
			    } catch(Exception e) {
			    	e.printStackTrace();
			    	errorLabel1.setText("Sai tên đăng nhập!");
			    }		        
		    }catch (Exception io){
		        io.printStackTrace();
		    }
	   }
	   
	   public void onForgotPass() {
		   forgotPassLabel.setTextFill(Color.GREEN);
		   Alert alert = new Alert(Alert.AlertType.INFORMATION);
		   alert.setTitle("Quên mật khẩu");
		   alert.setContentText("Đã gửi mật khẩu về số điện thoại/ email đăng ký!");
		   alert.setOnCloseRequest((event) -> {forgotPassLabel.setTextFill(Color.valueOf("#9e1482"));});
		   alert.showAndWait();
	   }
}