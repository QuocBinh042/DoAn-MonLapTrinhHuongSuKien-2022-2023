package DanhSach;

import java.util.ArrayList;

import Entity.DichVu;

public class DanhSachDichVu {
	private ArrayList<DichVu> ds;
	public DanhSachDichVu() {
		ds = new ArrayList<DichVu>();
	}
	
	public Boolean themDichVu(DichVu dv) {
		for (DichVu p: ds ) {
			if (p.getMaDichVu().equals(dv.getMaDichVu()))
				return false;
		}
		ds.add(dv);
		return true;
	}
	
	public Boolean xoaDichVu(int viTri) {
		if (viTri >= 0 && viTri < ds.size()) {
			ds.remove(viTri);
			return true;
		}
		return false;
	}
	
	public int timDichVuTheoMa(String maDichVu) {
		for (int i = 0; i<ds.size(); i++ ) {
			if (ds.get(i).getMaDichVu().equals(maDichVu))
				return i;
		}
		return -1;
	}
	
	public int timDichVuTheoTen(String tenDichVu) {
		for (int i = 0; i<ds.size(); i++ ) {
			if (ds.get(i).getTenDichVu().equalsIgnoreCase(tenDichVu))
				return i;
		}
		return -1;
	}
	
	public boolean suaDichVu(DichVu dv) {
		for (int i = 0; i < ds.size(); i++)
			if (ds.get(i).getMaDichVu().equalsIgnoreCase(dv.getMaDichVu())) {
				ds.get(i).setTenDichVu(dv.getTenDichVu());
				ds.get(i).setGiaDichVu(dv.getGiaDichVu());
				return true;
			}
		return false;
	}

	public ArrayList<DichVu> getList() {
		return ds;
	}

	public int soLuongDichVu() {
		return ds.size();
	}
}
