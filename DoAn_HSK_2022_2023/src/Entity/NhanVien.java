package Entity;

import java.io.Serializable;
import java.util.Objects;

public class NhanVien implements Serializable {
	private String maNV;
	private String hoTen;
	private String cmthu;
	private String sdthoai;
	private String gmail;
	private String diaChi;
	private String gioiTinh;
	private String chucVu;
	private String matKhau;

	public NhanVien(String maNV, String hoTen, String cmthu, String sdthoai, String gmail, String diaChi,
			String gioiTinh, String chucVu, String matKhau) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.cmthu = cmthu;
		this.sdthoai = sdthoai;
		this.gmail = gmail;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.chucVu = chucVu;
		this.matKhau = matKhau;
	
	}

	public NhanVien() {
		this("", "", "", "", "", "", "", "", "");
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getCmthu() {
		return cmthu;
	}

	public void setCmthu(String cmthu) {
		this.cmthu = cmthu;
	}

	public String getSdthoai() {
		return sdthoai;
	}

	public void setSdthoai(String sdthoai) {
		this.sdthoai = sdthoai;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", cmthu=" + cmthu + ", sdthoai=" + sdthoai + ", gmail="
				+ gmail + ", diaChi=" + diaChi + ", gioiTinh=" + gioiTinh + ", chucVu=" + chucVu + ", matKhau="
				+ matKhau + "]";
	}

	

}
