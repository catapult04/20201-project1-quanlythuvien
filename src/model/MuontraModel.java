package model;

import java.util.Date;

import javafx.collections.ObservableList;

public class MuontraModel  {
	private String MaMT_20183955; 
	private String MaDG_20183955; 
	private String MaTT_20183955;
	private Date Ngaymuon_20183955;
	private Date Ngayhentra_20183955;
	private int Tiencoc_20183955;
	
	
	public MuontraModel(String maMT_29183955, String maDG_29183955, String maTT_29183955, Date ngaymuon_29183955,
			Date ngayhentra_29183955, int tiencoc_29183955) {
		super();
		MaMT_20183955 = maMT_29183955;
		MaDG_20183955 = maDG_29183955;
		MaTT_20183955 = maTT_29183955;
		Ngaymuon_20183955 = ngaymuon_29183955;
		Ngayhentra_20183955 = ngayhentra_29183955;
		Tiencoc_20183955 = tiencoc_29183955;
	}


	public String getMaMT_20183955() {
		return MaMT_20183955;
	}


	public void setMaMT_20183955(String maMT_20183955) {
		MaMT_20183955 = maMT_20183955;
	}


	public String getMaDG_20183955() {
		return MaDG_20183955;
	}


	public void setMaDG_20183955(String maDG_20183955) {
		MaDG_20183955 = maDG_20183955;
	}


	public String getMaTT_20183955() {
		return MaTT_20183955;
	}


	public void setMaTT_20183955(String maTT_20183955) {
		MaTT_20183955 = maTT_20183955;
	}


	public Date getNgaymuon_20183955() {
		return Ngaymuon_20183955;
	}


	public void setNgaymuon_20183955(Date ngaymuon_20183955) {
		Ngaymuon_20183955 = ngaymuon_20183955;
	}


	public Date getNgayhentra_20183955() {
		return Ngayhentra_20183955;
	}


	public void setNgayhentra_20183955(Date ngayhentra_20183955) {
		Ngayhentra_20183955 = ngayhentra_20183955;
	}


	public int getTiencoc_20183955() {
		return Tiencoc_20183955;
	}


	public void setTiencoc_20183955(int tiencoc_20183955) {
		Tiencoc_20183955 = tiencoc_20183955;
	}
	
}
