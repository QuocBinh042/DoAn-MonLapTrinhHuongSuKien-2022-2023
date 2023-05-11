package Entity;

import java.util.Objects;

public class ThongKe {
	private String maPhong;
	private double tienDV, tienPhong, tongTien;
	public ThongKe(String maPhong, double tienDV, double tienPhong, double tongTien) {
		super();
		this.maPhong = maPhong;
		this.tienDV = tienDV;
		this.tienPhong = tienPhong;
		this.tongTien = tongTien;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public double getTienDV() {
		return tienDV;
	}
	public void setTienDV(double tienDV) {
		this.tienDV = tienDV;
	}
	public double getTienPhong() {
		return tienPhong;
	}
	public void setTienPhong(double tienPhong) {
		this.tienPhong = tienPhong;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maPhong, tienDV, tienPhong, tongTien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThongKe other = (ThongKe) obj;
		return Objects.equals(maPhong, other.maPhong)
				&& Double.doubleToLongBits(tienDV) == Double.doubleToLongBits(other.tienDV)
				&& Double.doubleToLongBits(tienPhong) == Double.doubleToLongBits(other.tienPhong)
				&& Double.doubleToLongBits(tongTien) == Double.doubleToLongBits(other.tongTien);
	}
	
	
}
