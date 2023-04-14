package phong;

import java.util.ArrayList;

public class DanhSachPhong {
	private ArrayList<Phong> list;

	public DanhSachPhong() {
		list = new ArrayList<Phong>();
	}
 
	public boolean themPhong(Phong ph) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaPhong().equalsIgnoreCase(ph.getMaPhong())) {
				return false;
			}
		list.add(ph);
		return true;
	}

	public boolean xoaViTri(int index) {
		if (index >= 0 && index <= list.size() - 1) {
			list.remove(index);
			return true;
		} else
			return false;
	}

	public int timPhong(String manv) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaPhong().equalsIgnoreCase(manv))
				return i;
		return -1;
	}
 
	public ArrayList<Phong> getList() {
		return list;
	}

	public int soLuongNV() {
		return list.size();
	}
}
