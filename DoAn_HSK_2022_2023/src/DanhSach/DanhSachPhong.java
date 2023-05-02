package DanhSach;

import java.util.ArrayList;

import Entity.Phong;

public class DanhSachPhong {
	private ArrayList<Phong> list;

	public DanhSachPhong() {
		list = new ArrayList<Phong>();
	}

	public String LayDanhSachPhong() {
		String s = "";
		for (Phong ph : list)
			s += ph + "\n";
		return s;
	}

	public boolean themPhong(Phong ph) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaPhong().equalsIgnoreCase(ph.getMaPhong())) {
				return false;
			}
		list.add(ph);
		return true;
	}

	public boolean xoaPhong(int index) {
		if (index >= 0 && index <= list.size() - 1) {
			list.remove(index);
			return true;
		} else
			return false;
	}

	public int timPhongTheoMa(String maPhong) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaPhong().equalsIgnoreCase(maPhong))
				return i;
		return -1;
	}

	public int timPhongTheoTen(String tenPhong) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getTenPhong().equalsIgnoreCase(tenPhong))
				return i;
		return -1;
	}

	public boolean capNhatThongTinPhong(Phong ph) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaPhong().equalsIgnoreCase(ph.getMaPhong())) {
				list.get(i).setTenPhong(ph.getTenPhong());
				list.get(i).setLoaiPhong(ph.getLoaiPhong());
				list.get(i).setGiaPhong(ph.getGiaPhong());
				list.get(i).setMoTa(ph.getMoTa());
				list.get(i).setTinhTrang(ph.getTinhTrang());
				return true;
			}
		return false;
	}

	public ArrayList<Phong> getList() {
		return list;
	}

	public int LayDSPhong() {
		return list.size();
	}
}
