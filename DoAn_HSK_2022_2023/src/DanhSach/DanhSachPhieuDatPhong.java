package DanhSach;

import java.util.ArrayList;

import Entity.PhieuDatPhong;

public class DanhSachPhieuDatPhong {
	private ArrayList<PhieuDatPhong> ds;
	public DanhSachPhieuDatPhong() {
		ds = new ArrayList<PhieuDatPhong>();
	}
	
	public Boolean themPhieuDatPhong(PhieuDatPhong pdp) {
		for (int i = 0; i<ds.size(); i++ ) {
			if (ds.get(i).getMaPhong().equals(pdp.getMaPhieuDatPhong()))
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
			if (ds.get(i).getMaPhieuDatPhong().equals(maDatPhong))
				return i;
		}
		return -1;
		
	}
	public boolean suaPhieuDatPhong(PhieuDatPhong pdp) {
		for (int i = 0; i < ds.size(); i++)
			if (ds.get(i).getMaPhieuDatPhong().equalsIgnoreCase(pdp.getMaPhieuDatPhong())) {
				ds.get(i).setMaPhong(pdp.getMaPhong());
				ds.get(i).setMaNhanVien(pdp.getMaNhanVien());
				ds.get(i).setMaHoaDon(pdp.getMaHoaDon());
				ds.get(i).setMaKhachHang(pdp.getMaKhachHang());
				ds.get(i).setSoNguoi(pdp.getSoNguoi());
				ds.get(i).setNgayDen(pdp.getNgayDen());
				ds.get(i).setNgayDi(pdp.getNgayDi());
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
