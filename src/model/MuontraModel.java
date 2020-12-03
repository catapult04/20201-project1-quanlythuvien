package model;

import java.util.Date;

import javafx.collections.ObservableList;

public class MuontraModel {
	private String MaMT_29183955; //char 9
	private String MaDG_29183955; //char 5
	private String MaTT_29183955; // char 3
	private String Ngaymuon_29183955;
	private String Ngayhentra_29183955;
	private int Tiencoc_29183955;
	
	
	public MuontraModel(ObservableList<String> input) {
		setMaMT_29183955(input.get(0));
		setMaDG_29183955(input.get(1));
		setMaTT_29183955(input.get(2));
		setNgaymuon_29183955(input.get(3));
		setNgayhentra_29183955(input.get(4));
		setTiencoc_29183955(Integer.parseInt(input.get(5)));
	}

	
	public String getMaMT_29183955() {
		return MaMT_29183955;
	}
	public void setMaMT_29183955(String maMT_29183955) {
		MaMT_29183955 = maMT_29183955;
	}
	public String getMaDG_29183955() {
		return MaDG_29183955;
	}
	public void setMaDG_29183955(String maDG_29183955) {
		MaDG_29183955 = maDG_29183955;
	}
	public String getMaTT_29183955() {
		return MaTT_29183955;
	}
	public void setMaTT_29183955(String maTT_29183955) {
		MaTT_29183955 = maTT_29183955;
	}
	public String getNgaymuon_29183955() {
		return Ngaymuon_29183955;
	}
	public void setNgaymuon_29183955(String ngaymuon_29183955) {
		Ngaymuon_29183955 = ngaymuon_29183955;
	}
	public String getNgayhentra_29183955() {
		return Ngayhentra_29183955;
	}
	public void setNgayhentra_29183955(String ngayhentra_29183955) {
		Ngayhentra_29183955 = ngayhentra_29183955;
	}
	public int getTiencoc_29183955() {
		return Tiencoc_29183955;
	}
	public void setTiencoc_29183955(int tiencoc_29183955) {
		Tiencoc_29183955 = tiencoc_29183955;
	}
}
