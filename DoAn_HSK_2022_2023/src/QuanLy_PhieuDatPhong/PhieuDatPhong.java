package QuanLy_PhieuDatPhong;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PhieuDatPhong implements Serializable{
	private String maPhong;
	private int soNguoi;
	private String ngayDen;
	private String ngayDi;
	private double giaTien;
	private String ngayDatPhong;
	private String ghiChu;

	
	public PhieuDatPhong(String maPhong, int soNguoi, String ngayDen, String ngayDi, double giaTien,
			String ngayDatPhong, String ghiChu) {
		super();
		this.maPhong = maPhong;
		this.soNguoi = soNguoi;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
		this.giaTien = giaTien;
		this.ngayDatPhong = ngayDatPhong;
		this.ghiChu = ghiChu;
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
		PhieuDatPhong other = (PhieuDatPhong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getNgayDatPhong() {
		return ngayDatPhong;
	}

	public void setNgayDatPhong(String ngayDatPhong) {
		this.ngayDatPhong = ngayDatPhong;
	}

	public String getNgayDen() {
		return ngayDen;
	}

	public void setNgayDen(String ngayDen) {
		this.ngayDen = ngayDen;
	}

	public String getNgayDi() {
		return ngayDi;
	}

	public void setNgayDi(String ngayDi) {
		this.ngayDi = ngayDi;
	}

	public int getSoNguoi() {
		return soNguoi;
	}

	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}

	public double getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "PhieuDatPhong [maPhong=" + maPhong + ", soNguoi=" + soNguoi + ", ngayDen=" + ngayDen + ", ngayDi="
				+ ngayDi + ", giaTien=" + giaTien + ", ngayDatPhong=" + ngayDatPhong + ", ghiChu=" + ghiChu + "]";
	}

	
}
