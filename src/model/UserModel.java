package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel extends Model{
	private String username;
	private String maTT;
	private String password;
	
	public static ObservableList<String> LIST_FIELDS_NAME = FXCollections.observableArrayList("username", "maTT", "password");
	
	public void setField(int pos, String value) {
		switch(pos) {
		case 0: setUsername(value); break;
		case 1: setMaTT(value); break;
		case 2: setPassword(value); break;
		default: break;
		}
	}
	
	public String getField(int pos) {
		switch(pos) {
		case 0: return getUsername(); 
		case 1: return getMaTT(); 
		case 2: return getPassword();  
		default: return"";
		}
	}
	
	public UserModel(ObservableList<String> input) {
		username = input.get(0);
		maTT = input.get(1);
		password = input.get(2);
		oldId = username;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMaTT() {
		return maTT;
	}
	public void setMaTT(String maTT) {
		this.maTT = maTT;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
