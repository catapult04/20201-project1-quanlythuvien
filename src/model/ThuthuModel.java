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
	
	public void setField(int pos, String value) {
		switch(pos) {
		case 0: setMaTT_20183955(value); break;
		case 1: setTen_20183955(value); break;
		case 2: setGioitinh_20183955(value); break;
		case 4: setNamsinh_20183955(value); break;
		case 5: setCMND_20183955(value); break;
		case 6: setEmail_20183955(value); break;
		case 7: setDthoai_20183955(value); break;
		default: break;
		}
	}
	
	public String getField(int pos) {
		switch(pos) {
		case 0: return getMaTT_20183955(); 
		case 1: return getTen_20183955(); 
		case 2: return getGioitinh_20183955();  
		case 4: return getNamsinh_20183955(); 
		case 5: return getCMND_20183955(); 
		case 6: return getEmail_20183955(); 
		case 7: return getDthoai_20183955();  
		default: return"";
		}
	}
	
	public ThuthuModel(ObservableList<String> input) {
		MaTT_20183955 = input.get(0);
		Ten_20183955 = input.get(1);
		Gioitinh_20183955 = input.get(2);
		Namsinh_20183955 = input.get(3);
		CMND_20183955 = input.get(4);
		Email_20183955 = input.get(5);
		Dthoai_20183955 = input.get(6);
	}
	
	
	public String getMaTT_20183955() {
		return MaTT_20183955;
	}
	public void setMaTT_20183955(String maTT_20183955) {
		MaTT_20183955 = maTT_20183955;
	}
	public String getTen_20183955() {
		return Ten_20183955;
	}
	public void setTen_20183955(String ten_20183955) {
		Ten_20183955 = ten_20183955;
	}
	public String getGioitinh_20183955() {
		return Gioitinh_20183955;
	}
	public void setGioitinh_20183955(String gioitinh_20183955) {
		Gioitinh_20183955 = gioitinh_20183955;
	}
	public String getNamsinh_20183955() {
		return Namsinh_20183955;
	}
	public void setNamsinh_20183955(String namsinh_20183955) {
		Namsinh_20183955 = namsinh_20183955;
	}
	public String getCMND_20183955() {
		return CMND_20183955;
	}
	public void setCMND_20183955(String cMND_20183955) {
		CMND_20183955 = cMND_20183955;
	}
	public String getEmail_20183955() {
		return Email_20183955;
	}
	public void setEmail_20183955(String email_20183955) {
		Email_20183955 = email_20183955;
	}
	public String getDthoai_20183955() {
		return Dthoai_20183955;
	}
	public void setDthoai_20183955(String dthoai_20183955) {
		Dthoai_20183955 = dthoai_20183955;
	}
	public static ObservableList<String> getLIST_FIELDS_NAME() {
		return LIST_FIELDS_NAME;
	}
	public static void setLIST_FIELDS_NAME(ObservableList<String> lIST_FIELDS_NAME) {
		LIST_FIELDS_NAME = lIST_FIELDS_NAME;
	}
}
