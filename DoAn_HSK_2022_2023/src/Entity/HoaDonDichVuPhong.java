package Entity;

import java.util.Objects;

public class HoaDonDichVuPhong {
	 private String maDatPhong, maDichVu;
	 private int soLuong;
	 private float gia;
	 private float thanhTienDichVu;
	 
	
	public HoaDonDichVuPhong(String maDatPhong, String maDichVu, int soLuong) {
		super();
		this.maDatPhong = maDatPhong;
		this.maDichVu = maDichVu;
		this.soLuong = soLuong;
	}
	
	public HoaDonDichVuPhong(String maDatPhong, String maDichVu, int soLuong, float thanhTienDichVu) {
		super();
		this.maDatPhong = maDatPhong;
		this.maDichVu = maDichVu;
		this.soLuong = soLuong;
		this.thanhTienDichVu = thanhTienDichVu;
	}

	
	public HoaDonDichVuPhong(String maDatPhong, String maDichVu, int soLuong, float gia, float thanhTienDichVu) {
		super();
		this.maDatPhong = maDatPhong;
		this.maDichVu = maDichVu;
		this.soLuong = soLuong;
		this.gia = gia;
		this.thanhTienDichVu = thanhTienDichVu;
	}

	public String getMaDatPhong() {
		return maDatPhong;
	}
	public void setMaDatPhong(String maDatPhong) {
		this.maDatPhong = maDatPhong;
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
	public float getGia() {
		return gia;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}
	public float getThanhTienDichVu() {
		return thanhTienDichVu;
	}
	public void setThanhTienDichVu(float thanhTienDichVu) {
		this.thanhTienDichVu = thanhTienDichVu;
	}
	@Override
	public int hashCode() {
		return Objects.hash(gia, maDatPhong, maDichVu, soLuong, thanhTienDichVu);
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
		return Float.floatToIntBits(gia) == Float.floatToIntBits(other.gia)
				&& Objects.equals(maDatPhong, other.maDatPhong) && Objects.equals(maDichVu, other.maDichVu)
				&& soLuong == other.soLuong
				&& Float.floatToIntBits(thanhTienDichVu) == Float.floatToIntBits(other.thanhTienDichVu);
	}
	 
}
