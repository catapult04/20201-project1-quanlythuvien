package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SachModel extends Model{
	private String Masach_20183955; //char 7
	private String Tensach_20183955; // varchar 80
	private String Tacgia_20183955; //varchar 40
	private String NhaXB_20183955; //varchar 40
	private String NamXB_20183955; //year 4
	private int Dongia_20183955; //int 9
	private String Trangthaisach_20183955; //varchar 30
	private String Gioithieu_20183955; //var char 150
	
	public static ObservableList<String> LIST_FIELDS_NAME = 
			FXCollections.observableArrayList("Masach_20183955", "Tensach_20183955","Tacgia_20183955", "NhaXB_20183955", 
					"NamXB_20183955", "Dongia_20183955", "Trangthaisach_20183955", "Gioithieu_20183955");
	
	public SachModel(ObservableList<String> input) {
		setMasach_20183955(input.get(0));
		setTensach_20183955(input.get(1));
		setTacgia_20183955(input.get(2));
		setNhaXB_20183955(input.get(3));
		setNamXB_20183955(input.get(4));
		setDongia_20183955(Integer.parseInt(input.get(5)));
		setTrangthaisach_20183955(input.get(6));
		setGioithieu_20183955(input.get(7));
	}

	public String getMasach_20183955() {
		return Masach_20183955;
	}
	public void setMasach_20183955(String masach_20183955) {
		Masach_20183955 = masach_20183955;
	}
	public String getTensach_20183955() {
		return Tensach_20183955;
	}
	public void setTensach_20183955(String tensach_20183955) {
		Tensach_20183955 = tensach_20183955;
	}
	public String getTacgia_20183955() {
		return Tacgia_20183955;
	}
	public void setTacgia_20183955(String tacgia_20183955) {
		Tacgia_20183955 = tacgia_20183955;
	}
	public String getNhaXB_20183955() {
		return NhaXB_20183955;
	}
	public void setNhaXB_20183955(String nhaXB_20183955) {
		NhaXB_20183955 = nhaXB_20183955;
	}
	public String getNamXB_20183955() {
		return NamXB_20183955;
	}
	public void setNamXB_20183955(String namXB_20183955) {
		NamXB_20183955 = namXB_20183955;
	}
	public int getDongia_20183955() {
		return Dongia_20183955;
	}
	public void setDongia_20183955(int dongia_20183955) {
		Dongia_20183955 = dongia_20183955;
	}
	public String getTrangthaisach_20183955() {
		return Trangthaisach_20183955;
	}
	public void setTrangthaisach_20183955(String trangthaisach_20183955) {
		Trangthaisach_20183955 = trangthaisach_20183955;
	}
	public String getGioithieu_20183955() {
		return Gioithieu_20183955;
	}
	public void setGioithieu_20183955(String gioithieu_20183955) {
		Gioithieu_20183955 = gioithieu_20183955;
	}
}
