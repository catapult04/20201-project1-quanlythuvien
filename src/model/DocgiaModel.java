package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DocgiaModel extends Model{
	private String MaDG_20183955;
	private String TenDG_20183955;
	private String Gioitinh_20183955;
	private String Diachi_20183955;
	private String Namsinh_20183955;
	private String CMND_20183955;
	private String Email_20183955;
	private String Dthoai_20183955;
	
	public static ObservableList<String> LIST_FIELDS_NAME = FXCollections.observableArrayList("MaDG_20183955", "TenDG_20183955",
			"Gioitinh_20183955", "Diachi_20183955", "Namsinh_20183955", "CMND_20183955", "Email_20183955", "Dthoai_20183955");
	
	public DocgiaModel(ObservableList<String> input) {
		MaDG_20183955 = input.get(0);
		TenDG_20183955 = input.get(1);
		Gioitinh_20183955 = input.get(2);
		Diachi_20183955 = input.get(3);
		Namsinh_20183955 = input.get(4);
		CMND_20183955 = input.get(5);
		Email_20183955 = input.get(6);
		Dthoai_20183955 = input.get(7);
	}
}
