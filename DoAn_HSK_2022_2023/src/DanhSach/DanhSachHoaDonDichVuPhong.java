package DanhSach;

import java.util.ArrayList;

import Entity.HoaDonDichVuPhong;

public class DanhSachHoaDonDichVuPhong {
	private ArrayList<HoaDonDichVuPhong> ds;
	public DanhSachHoaDonDichVuPhong() {
		ds = new ArrayList<HoaDonDichVuPhong>();
	}
	
	public Boolean themDichVuPhong(HoaDonDichVuPhong dv) {
		ds.add(dv);
		return true;
	}
	
	public Boolean xoaDichVuPhong(int viTri) {
		if (viTri >= 0 && viTri < ds.size()) {
			ds.remove(viTri);
			return true;
		}
		return false;
	}
	
	public boolean suaDichVu(HoaDonDichVuPhong dvp) {
		for (int i = 0; i < ds.size(); i++)
			if ((ds.get(i).getMaDichVu().equals(dvp.getMaDichVu()) && (ds.get(i).getMaPhong().equals(dvp.getMaPhong())))) {
				ds.get(i).setSoLuong(dvp.getSoLuong());
				ds.get(i).setThanhTienDichVu(dvp.getThanhTienDichVu());
				return true;
			}
		return false;
	}

	public ArrayList<HoaDonDichVuPhong> getList() {
		return ds;
	}

}
