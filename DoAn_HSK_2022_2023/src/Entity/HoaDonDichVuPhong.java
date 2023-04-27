package Entity;

import java.util.Objects;

public class HoaDonDichVuPhong {
	 private String maPhong, maDichVu;
	 private int soLuong;
	 private float thanhTienDichVu;
	public HoaDonDichVuPhong(String maPhong, String maDichVu, int soLuong, float thanhTienDichVu) {
		super();
		this.maPhong = maPhong;
		this.maDichVu = maDichVu;
		this.soLuong = soLuong;
		this.thanhTienDichVu = thanhTienDichVu;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getMaDichVu() {
		return maDichVu;
	}
	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public float getThanhTienDichVu() {
		return thanhTienDichVu;
	}
	public void setThanhTienDichVu(float thanhTienDichVu) {
		this.thanhTienDichVu = thanhTienDichVu;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maDichVu, maPhong, soLuong, thanhTienDichVu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDonDichVuPhong other = (HoaDonDichVuPhong) obj;
		return Objects.equals(maDichVu, other.maDichVu) && Objects.equals(maPhong, other.maPhong)
				&& soLuong == other.soLuong
				&& Float.floatToIntBits(thanhTienDichVu) == Float.floatToIntBits(other.thanhTienDichVu);
	}
	 
}
