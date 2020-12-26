package model;

import java.util.Date;
import javafx.collections.ObservableList;

public class MuontraModel  {
	private String MaMT_29183955; 
	private String MaDG_29183955; 
	private String MaTT_29183955;
	private Date Ngaymuon_29183955;
	private Date Ngayhentra_29183955;
	private int Tiencoc_29183955;
	
	
	public MuontraModel(String maMT_29183955, String maDG_29183955, String maTT_29183955, Date ngaymuon_29183955,
			Date ngayhentra_29183955, int tiencoc_29183955) {
		super();
		MaMT_29183955 = maMT_29183955;
		MaDG_29183955 = maDG_29183955;
		MaTT_29183955 = maTT_29183955;
		Ngaymuon_29183955 = ngaymuon_29183955;
		Ngayhentra_29183955 = ngayhentra_29183955;
		Tiencoc_29183955 = tiencoc_29183955;
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
	public Date getNgaymuon_29183955() {
		return Ngaymuon_29183955;
	}
	public void setNgaymuon_29183955(Date ngaymuon_29183955) {
		Ngaymuon_29183955 = ngaymuon_29183955;
	}
	public Date getNgayhentra_29183955() {
		return Ngayhentra_29183955;
	}
	public void setNgayhentra_29183955(Date ngayhentra_29183955) {
		Ngayhentra_29183955 = ngayhentra_29183955;
	}
	public int getTiencoc_29183955() {
		return Tiencoc_29183955;
	}
	public void setTiencoc_29183955(int tiencoc_29183955) {
		Tiencoc_29183955 = tiencoc_29183955;
	}
}
