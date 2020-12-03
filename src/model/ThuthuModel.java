package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ThuthuModel extends Model{
	private String MaTT_20183955;
	private String Ten_20183955;
	private String Gioitinh_20183955;
	private String Namsinh_20183955;
	private String CMND_20183955;
	private String Email_20183955;
	private String Dthoai_20183955;
	
	public static ObservableList<String> LIST_FIELDS_NAME = FXCollections.observableArrayList("MaTT_20183955", "Ten_20183955",
			"Gioitinh_20183955", "Namsinh_20183955", "CMND_20183955", "Email_20183955", "Dthoai_20183955");
	
	public ThuthuModel(ObservableList<String> input) {
		MaTT_20183955 = input.get(0);
		Ten_20183955 = input.get(1);
		Gioitinh_20183955 = input.get(2);
		Namsinh_20183955 = input.get(3);
		CMND_20183955 = input.get(4);
		Email_20183955 = input.get(5);
		Dthoai_20183955 = input.get(6);
	}
}
