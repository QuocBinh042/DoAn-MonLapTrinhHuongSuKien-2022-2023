package Entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class PhieuDatPhong implements Serializable {
	private String maDatPhong;
	private String maNhanVien;
	private String maPhong;
	private String maKhachHang;
	private String maHoaDon;
	private Date ngayDatPhong;
	private Date ngayDen;
	private Date ngayDi;
	private int soNguoi;
	private String ghiChu;

	public PhieuDatPhong(String maDatPhong, String maNhanVien, String maPhong, String maKhachHang, String maHoaDon,
			Date ngayDatPhong, Date ngayDen, Date ngayDi, int soNguoi, String ghiChu) {
		super();
		this.maDatPhong = maDatPhong;
		this.maNhanVien = maNhanVien;
		this.maPhong = maPhong;
		this.maKhachHang = maKhachHang;
		this.maHoaDon = maHoaDon;
		this.ngayDatPhong = ngayDatPhong;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
		this.soNguoi = soNguoi;
		this.ghiChu = ghiChu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDatPhong);
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
		return Objects.equals(maDatPhong, other.maDatPhong);
	}

	public String getMaDatPhong() {
		return maDatPhong;
	}

	public void setMaDatPhong(String maDatPhong) {
		this.maDatPhong = maDatPhong;
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

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public Date getNgayDatPhong() {
		return ngayDatPhong;
	}

	public void setNgayDatPhong(Date ngayDatPhong) {
		this.ngayDatPhong = ngayDatPhong;
	}

	public Date getNgayDen() {
		return ngayDen;
	}

	public void setNgayDen(Date ngayDen) {
		this.ngayDen = ngayDen;
	}

	public Date getNgayDi() {
		return ngayDi;
	}

	public void setNgayDi(Date ngayDi) {
		this.ngayDi = ngayDi;
	}

	public int getSoNguoi() {
		return soNguoi;
	}

	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "PhieuDatPhong [maDatPhong=" + maDatPhong + ", maNhanVien=" + maNhanVien + ", maPhong=" + maPhong
				+ ", maKhachHang=" + maKhachHang + ", maHoaDon=" + maHoaDon + ", ngayDatPhong=" + ngayDatPhong
				+ ", ngayDen=" + ngayDen + ", ngayDi=" + ngayDi + ", soNguoi=" + soNguoi + ", ghiChu=" + ghiChu + "]";
	}

}
