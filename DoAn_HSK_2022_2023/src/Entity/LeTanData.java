package Entity;

import java.io.Serializable;
import java.util.Date;

public class LeTanData implements Serializable{
	private String maDatPhong;
	private String hoTenKhachHang;
	private String tenPhong;
	private String tinhTrang;
	private Date ngayDen;
	private Date ngayDi;
	private String ghiChu;

	public LeTanData(String maDatPhong, String hoTenKhachHang, String tenPhong, String tinhTrang, Date ngayDen,
			Date ngayDi, String ghiChu) {
		super();
		this.maDatPhong = maDatPhong;
		this.hoTenKhachHang = hoTenKhachHang;
		this.tenPhong = tenPhong;
		this.tinhTrang = tinhTrang;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
		this.ghiChu = ghiChu;
	}

	public String getMaDatPhong() {
		return maDatPhong;
	}

	public void setMaDatPhong(String maDatPhong) {
		this.maDatPhong = maDatPhong;
	}

	public String getHoTenKhachHang() {
		return hoTenKhachHang;
	}

	public void setHoTenKhachHang(String hoTenKhachHang) {
		this.hoTenKhachHang = hoTenKhachHang;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
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

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "LeTanData [maDatPhong=" + maDatPhong + ", hoTenKhachHang=" + hoTenKhachHang + ", tenPhong=" + tenPhong
				+ ", tinhTrang=" + tinhTrang + ", ngayDen=" + ngayDen + ", ngayDi=" + ngayDi + ", ghiChu=" + ghiChu
				+ "]";
	}
	
}
