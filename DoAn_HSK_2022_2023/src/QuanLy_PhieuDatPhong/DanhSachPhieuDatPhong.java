package QuanLy_PhieuDatPhong;

import java.util.ArrayList;

public class DanhSachPhieuDatPhong {
	private ArrayList<PhieuDatPhong> ds;
	public DanhSachPhieuDatPhong() {
		ds = new ArrayList<PhieuDatPhong>();
	}
	
	public Boolean themPhieuDatPhong(PhieuDatPhong pdp) {
		for (PhieuDatPhong p: ds ) {
			if (p.getMaPhong().equals(pdp.getMaPhong()))
				return false;
		}
		ds.add(pdp);
		return true;
	}
	
	public Boolean xoaPhieuDatPhong(int viTri) {
		if (viTri >= 0 && viTri < ds.size()) {
			ds.remove(viTri);
			return true;
		}
		return false;
	}
	
	public int timPhieuDatPhongTheoMa(String maDatPhong) {
		for (int i = 0; i<ds.size(); i++ ) {
			if (ds.get(i).getMaPhong().equals(maDatPhong))
				return i;
		}
		return -1;
		
	}
	public boolean suaPhieuDatPhong(PhieuDatPhong pdp) {
		for (int i = 0; i < ds.size(); i++)
			if (ds.get(i).getMaPhong().equalsIgnoreCase(pdp.getMaPhong())) {
				ds.get(i).setSoNguoi(pdp.getSoNguoi());
				ds.get(i).setNgayDen(pdp.getNgayDen());
				ds.get(i).setNgayDi(pdp.getNgayDi());
				ds.get(i).setGiaTien(pdp.getGiaTien());
				ds.get(i).setNgayDatPhong(pdp.getNgayDatPhong());
				ds.get(i).setGhiChu(pdp.getGhiChu());
				return true;
			}
		return false;
	}

	public ArrayList<PhieuDatPhong> getList() {
		return ds;
	}

	public int soLuongPhieu() {
		return ds.size();
	}
}
