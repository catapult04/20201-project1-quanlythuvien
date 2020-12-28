package model;

public class ThongkeModel {
	private String noidung;
	private int soluong;
	private double tile;
	
	public ThongkeModel(String noidung, int soluong, double tile) {
		super();
		this.noidung = noidung;
		this.soluong = soluong;
		this.tile = tile;
	}
	
	
	public String getNoidung() {
		return noidung;
	}
	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public double getTile() {
		return tile;
	}
	public void setTile(double tile) {
		this.tile = tile;
	}
	
	
}
