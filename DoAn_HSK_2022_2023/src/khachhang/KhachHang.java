package khachhang;

import java.io.Serializable;
import java.util.Objects;

public class KhachHang implements Serializable{
	private String maKHang;
	private String tenKHang;
	private String CMThu;
	private String SDThoai;
	private String Gmail;

	



public KhachHang(String maKHang, String tenKHang, String cMThu, String sDThoai, String gmail) {
		super();
		this.maKHang = maKHang;
		this.tenKHang = tenKHang;
		this.CMThu = cMThu;
		this.SDThoai = sDThoai;
		this.Gmail = gmail;
	}



public KhachHang() {
       this("", "", "", "", "");
}



	@Override
	public int hashCode() {
		return Objects.hash(maKHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKHang, other.maKHang);
	}



	public String getMaKHang() {
		return maKHang;
	}



	public void setMaKHang(String maKHang) {
		this.maKHang = maKHang;
	}



	public String getTenKHang() {
		return tenKHang;
	}



	public void setTenKHang(String tenKHang) {
		this.tenKHang = tenKHang;
	}



	public String getCMThu() {
		return CMThu;
	}



	public void setCMThu(String cMThu) {
		CMThu = cMThu;
	}



	public String getSDThoai() {
		return SDThoai;
	}



	public void setSDThoai(String sDThoai) {
		SDThoai = sDThoai;
	}



	public String getGmail() {
		return Gmail;
	}



	public void setGmail(String gmail) {
		Gmail = gmail;
	}



	@Override
	public String toString() {
		return "KhachHang [maKHang=" + maKHang + ", tenKHang=" + tenKHang + ", CMThu=" + CMThu + ", SDThoai=" + SDThoai
				+ ", Gmail=" + Gmail + "]";
	}





	

	

}
