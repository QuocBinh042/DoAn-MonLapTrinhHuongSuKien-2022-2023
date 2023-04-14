package phong;

import java.io.Serializable;
import java.util.Objects;

public class Phong implements Serializable{
	private String maPhong;
	private String tenPhong;
	private String loaiPhong;
	private String moTa;
	private double giaPhong;
	private String tinhTrang;

	
	
	
	
public Phong(String maPhong, String tenPhong, String loaiPhong, String moTa, double giaPhong, String tinhTrang) {
	super();
	this.maPhong = maPhong;
	this.tenPhong = tenPhong;
	this.loaiPhong = loaiPhong;
	this.moTa = moTa;
	this.giaPhong = giaPhong;
	this.tinhTrang = tinhTrang;
}



public Phong() {
this("", "", "", "", 0, "");
}



	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}





	public String getMaPhong() {
		return maPhong;
	}





	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}





	public String getTenPhong() {
		return tenPhong;
	}





	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}





	public String getLoaiPhong() {
		return loaiPhong;
	}





	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}





	public String getMoTa() {
		return moTa;
	}





	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}





	public double getGiaPhong() {
		return giaPhong;
	}





	public void setGiaPhong(double giaPhong) {
		this.giaPhong = giaPhong;
	}








	public String getTinhTrang() {
		return tinhTrang;
	}



	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}



	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", loaiPhong=" + loaiPhong + ", moTa=" + moTa
				+ ", giaPhong=" + giaPhong + ", tinhTrang=" + tinhTrang + "]";
	}
	
	

	

}
