package nhanvien;

import java.io.Serializable;
import java.util.Objects;

public class NhanVien implements Serializable{
	private String maNV;
	private String hoTen;
	private String CMThu;
	private String Sdthoai;
	private String Gmail;
	private String chucVu;

 


	public NhanVien(String maNV, String hoTen, String cMThu, String sdthoai, String gmail, String chucVu) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.CMThu = cMThu;
		this.Sdthoai = sdthoai;
		this.Gmail = gmail;
		this.chucVu = chucVu;
	}


	public NhanVien() {
		this("", "", "", "", "", "");
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


	public String getCMThu() {
		return CMThu;
	}


	public void setCMThu(String cMThu) {
		CMThu = cMThu;
	}


	public String getSdthoai() {
		return Sdthoai;
	}


	public void setSdthoai(String sdthoai) {
		Sdthoai = sdthoai;
	}


	public String getGmail() {
		return Gmail;
	}


	public void setGmail(String gmail) {
		Gmail = gmail;
	}


	public String getChucVu() {
		return chucVu;
	}


	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}


	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", CMThu=" + CMThu + ", Sdthoai=" + Sdthoai + ", Gmail="
				+ Gmail + ", chucVu=" + chucVu + "]";
	}



}
