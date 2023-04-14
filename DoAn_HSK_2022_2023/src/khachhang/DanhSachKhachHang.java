package khachhang;

import java.util.ArrayList;

public class DanhSachKhachHang {
	private ArrayList<KhachHang> list;

	public DanhSachKhachHang() {
		list = new ArrayList<KhachHang>();
	}
	
	
	public String LayDanhSachKhachHang() {
		String s = "";
		for(KhachHang kh: list)
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

	public boolean xoaKHang(int index) {
		if (index >= 0 && index <= list.size() - 1) {
			list.remove(index);
			return true;
		} else
			return false;
	}

	public int timKHang(String maKHang) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaKHang().equalsIgnoreCase(maKHang))
				return i;
		return -1;
	}
	
	public boolean capNhatThongTinKhachHang(KhachHang kh) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getMaKHang().equalsIgnoreCase(kh.getMaKHang())) {
				return false;
			}
		list.add(kh);
		return true;
	}

	
 
	public ArrayList<KhachHang> getList() {
		return list;
	}

	public int LayDSKhachHang() {
		return list.size();
	}
}
