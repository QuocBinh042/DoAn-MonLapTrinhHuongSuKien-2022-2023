package Entity;

import java.util.Objects;

public class HoaDonThanhToan {
	private String maHoaDon;
	private String ngayThanhToan;
	private String hinhThucThanhToan;
	private double thanhTienPhong;
	private double tongThanhToan;
	private String ghiChu;

	public HoaDonThanhToan(String maHoaDon, String ngayThanhToan, String hinhThucThanhToan, double thanhTienPhong,
			double tongThanhToan, String ghiChu) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayThanhToan = ngayThanhToan;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.thanhTienPhong = thanhTienPhong;
		this.tongThanhToan = tongThanhToan;
		this.ghiChu = ghiChu;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(String ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}

	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}

	public double getThanhTienPhong() {
		return thanhTienPhong;
	}

	public void setThanhTienPhong(double thanhTienPhong) {
		this.thanhTienPhong = thanhTienPhong;
	}

	public double getTongThanhToan() {
		return tongThanhToan;
	}

	public void setTongThanhToan(double tongThanhToan) {
		this.tongThanhToan = tongThanhToan;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDonThanhToan other = (HoaDonThanhToan) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	@Override
	public String toString() {
		return "HoaDonThanhToan [maHoaDon=" + maHoaDon + ", ngayThanhToan=" + ngayThanhToan + ", hinhThucThanhToan="
				+ hinhThucThanhToan + ", thanhTienPhong=" + thanhTienPhong + ", tongThanhToan=" + tongThanhToan
				+ ", ghiChu=" + ghiChu + "]";
	}

}
