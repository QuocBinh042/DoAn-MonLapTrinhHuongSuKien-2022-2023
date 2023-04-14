package nhanvien;

import java.util.ArrayList;

public class DanhSachNhanVien {
	private ArrayList<NhanVien> list;

	public DanhSachNhanVien() {
		list = new ArrayList<NhanVien>();
	}
	
	
	public String LayDanhSachNhanVien() {
		String s = "";
		for(NhanVien nv: list)
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

	public int timNV(String maNV) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaNV().equalsIgnoreCase(maNV))
				return i;
		return -1;
	}
	
	public boolean capNhatThongTinNhanVien(NhanVien nv) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaNV().equalsIgnoreCase(nv.getMaNV())) {
				return false;
			}
		list.add(nv);
		return true;
	}

	
 
	public ArrayList<NhanVien> getList() {
		return list;
	}

	public int LayDSNhanVien() {
		return list.size();
	}
}
