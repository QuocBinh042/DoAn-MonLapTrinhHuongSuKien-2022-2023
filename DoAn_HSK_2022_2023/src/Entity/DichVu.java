package Entity;

import java.util.Objects;

public class DichVu {
	private String maDichVu;
	private String tenDichVu;
	private Double giaDichVu;

	public DichVu(String maDichVu, String tenDichVu, Double giaDichVu) {
		super();
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.giaDichVu = giaDichVu;
	}

	public DichVu() {
		this("", "", 0.0);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDichVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDichVu, other.maDichVu);
	}

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public Double getGiaDichVu() {
		return giaDichVu;
	}

	public void setGiaDichVu(Double giaDichVu) {
		this.giaDichVu = giaDichVu;
	}

	@Override
	public String toString() {
		return "DichVu [maDichVu=" + maDichVu + ", tenDichVu=" + tenDichVu + ", giaDichVu=" + giaDichVu + "]";
	}

}
