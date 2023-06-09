package DanhSach;

import java.util.ArrayList;

import Entity.NhanVien;

public class DanhSachNhanVien {
	private ArrayList<NhanVien> list;

	public DanhSachNhanVien() {
		list = new ArrayList<NhanVien>();
	}

	public String LayDanhSachNhanVien() {
		String s = "";
		for (NhanVien nv : list)
			s += nv + "\n";
		return s;
	}

	public boolean themNhanVien(NhanVien nv) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaNV().equalsIgnoreCase(nv.getMaNV())) {
				return false;
			}
		list.add(nv);
		return true;
	}

	public boolean xoaNV(int index) {
		if (index >= 0 && index <= list.size() - 1) {
			list.remove(index);
			return true;
		} else
			return false;
	}

	public int timNhanVienTheoMa(String maNhanVien) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMaNV().equalsIgnoreCase(maNhanVien))
				return i;
		}
		return -1;
	}

	public int timNhanVienTheoTen(String tenNhanVien) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getHoTen().equalsIgnoreCase(tenNhanVien))
				return i;
		}
		return -1;
	}

	public boolean capNhatThongTinNhanVien(NhanVien nv) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaNV().equalsIgnoreCase(nv.getMaNV())) {
				list.get(i).setChucVu(nv.getChucVu());
				list.get(i).setCmthu(nv.getCmthu());
				list.get(i).setDiaChi(nv.getDiaChi());
				list.get(i).setGioiTinh(nv.getGioiTinh());
				list.get(i).setGmail(nv.getGmail());
				list.get(i).setHoTen(nv.getHoTen());
				list.get(i).setMatKhau(nv.getMatKhau());
				list.get(i).setSdthoai(nv.getSdthoai());
				return true;
			}
		return false;
	}

	public ArrayList<NhanVien> getList() {
		return list;
	}

	public int LayDSNhanVien() {
		return list.size();
	}
}
