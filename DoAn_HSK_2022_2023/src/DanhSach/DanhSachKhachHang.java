package DanhSach;

import java.util.ArrayList;

import Entity.KhachHang;

public class DanhSachKhachHang {
	private ArrayList<KhachHang> list;

	public DanhSachKhachHang() {
		list = new ArrayList<KhachHang>();
	}

	public String LayDanhSachKhachHang() {
		String s = "";
		for (KhachHang kh : list)
			s += kh + "\n";
		return s;
	}

	public boolean themKHang(KhachHang kh) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaKHang().equalsIgnoreCase(kh.getMaKHang())) {
				return false;
			}
		list.add(kh);
		return true;
	}

	public boolean capNhatThongTinKhachHang(KhachHang kh) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaKHang().equalsIgnoreCase(kh.getMaKHang())) {
				list.get(i).setTenKHang(kh.getTenKHang());
				list.get(i).setCMThu(kh.getCMThu());
				list.get(i).setGmail(kh.getGmail());
				list.get(i).setSDThoai(kh.getSDThoai());
				return true;
			}
		return false;
	}

	public boolean xoaKHang(int index) {
		if (index >= 0 && index <= list.size() - 1) {
			list.remove(index);
			return true;
		} else
			return false;
	}

	public int timKhachHangTheoMa(String maKhachHang) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMaKHang().equalsIgnoreCase(maKhachHang))
				return i;
		}
		return -1;
	}

	public int timKhachHangTheoTen(String tenKhachHang) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTenKHang().equalsIgnoreCase(tenKhachHang))
				return i;
		}
		return -1;
	}

	public ArrayList<KhachHang> getList() {
		return list;
	}

	public int LayDSKhachHang() {
		return list.size();
	}
}
