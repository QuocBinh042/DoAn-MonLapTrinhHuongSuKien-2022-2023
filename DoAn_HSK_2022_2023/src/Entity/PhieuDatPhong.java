package Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PhieuDatPhong implements Serializable {
	private String maPhieuDatPhong;
	private String maNhanVien;
	private String maPhong;
	private String maHoaDon;
	private String maKhachHang;
	private int soNguoi;
	private String ngayDen;
	private String ngayDi;
	private String ngayDatPhong;
	private String ghiChu;

	public PhieuDatPhong(String maPhieuDatPhong, String maNhanVien, String maPhong, String maHoaDon, String maKhachHang,
			String ngayDatPhong, int soNguoi, String ngayDen, String ngayDi, String ghiChu) {
		super();
		this.maPhieuDatPhong = maPhieuDatPhong;
		this.maNhanVien = maNhanVien;
		this.maPhong = maPhong;
		this.maHoaDon = maHoaDon;
		this.maKhachHang = maKhachHang;
		this.soNguoi = soNguoi;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
		this.ngayDatPhong = ngayDatPhong;
		this.ghiChu = ghiChu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhieuDatPhong);
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
		return Objects.equals(maPhieuDatPhong, other.maPhieuDatPhong);
	}

	public String getMaPhieuDatPhong() {
		return maPhieuDatPhong;
	}

	public void setMaPhieuDatPhong(String maPhieuDatPhong) {
		this.maPhieuDatPhong = maPhieuDatPhong;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public int getSoNguoi() {
		return soNguoi;
	}

	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
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

	public String getNgayDatPhong() {
		return ngayDatPhong;
	}

	public void setNgayDatPhong(String ngayDatPhong) {
		this.ngayDatPhong = ngayDatPhong;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "PhieuDatPhong [maPhieuDatPhong=" + maPhieuDatPhong + ", maNhanVien=" + maNhanVien + ", maPhong="
				+ maPhong + ", maHoaDon=" + maHoaDon + ", maKhachHang=" + maKhachHang + ", soNguoi=" + soNguoi
				+ ", ngayDen=" + ngayDen + ", ngayDi=" + ngayDi + ", ngayDatPhong=" + ngayDatPhong + ", ghiChu="
				+ ghiChu + "]";
	}

}
