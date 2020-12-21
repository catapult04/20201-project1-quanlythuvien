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
			"Gioitinh_20183955", "Diachi_20183955", "Namsinh_20183955", "CMND_20183955", "Email_20183955", "Dthoai_20183955", "delBtn", "saveBtn");
	
	public void setField(int pos, String value) {
		switch(pos) {
		case 0: setMaDG_20183955(value); break;
		case 1: setTenDG_20183955(value); break;
		case 2: setGioitinh_20183955(value); break;
		case 3: setDiachi_20183955(value); break;
		case 4: setNamsinh_20183955(value); break;
		case 5: setCMND_20183955(value); break;
		case 6: setEmail_20183955(value); break;
		case 7: setDthoai_20183955(value); break;
		default: break;
		}
	}
	
	public String getField(int pos) {
		switch(pos) {
		case 0: return getMaDG_20183955(); 
		case 1: return getTenDG_20183955(); 
		case 2: return getGioitinh_20183955(); 
		case 3: return getDiachi_20183955(); 
		case 4: return getNamsinh_20183955(); 
		case 5: return getCMND_20183955(); 
		case 6: return getEmail_20183955(); 
		case 7: return getDthoai_20183955();  
		default: return"";
		}
	}
	
	public DocgiaModel(ObservableList<String> input) {
		MaDG_20183955 = input.get(0);
		TenDG_20183955 = input.get(1);
		Gioitinh_20183955 = input.get(2);
		Diachi_20183955 = input.get(3);
		Namsinh_20183955 = input.get(4);
		CMND_20183955 = input.get(5);
		Email_20183955 = input.get(6);
		Dthoai_20183955 = input.get(7);
		oldId = getMaDG_20183955();
	}

	public String getMaDG_20183955() {
		return MaDG_20183955;
	}

	public void setMaDG_20183955(String maDG_20183955) {
		MaDG_20183955 = maDG_20183955;
	}

	public String getTenDG_20183955() {
		return TenDG_20183955;
	}

	public void setTenDG_20183955(String tenDG_20183955) {
		TenDG_20183955 = tenDG_20183955;
	}

	public String getGioitinh_20183955() {
		return Gioitinh_20183955;
	}

	public void setGioitinh_20183955(String gioitinh_20183955) {
		Gioitinh_20183955 = gioitinh_20183955;
	}

	public String getDiachi_20183955() {
		return Diachi_20183955;
	}

	public void setDiachi_20183955(String diachi_20183955) {
		Diachi_20183955 = diachi_20183955;
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
