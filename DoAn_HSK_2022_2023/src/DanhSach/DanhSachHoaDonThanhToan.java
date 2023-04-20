package DanhSach;

import java.util.ArrayList;

import Entity.HoaDonThanhToan;
public class DanhSachHoaDonThanhToan {
	private ArrayList<HoaDonThanhToan> ds;
	public DanhSachHoaDonThanhToan() {
		ds = new ArrayList<HoaDonThanhToan>();
	}
	
	public Boolean themHoaDonThanhToan(HoaDonThanhToan hd) {
		for (HoaDonThanhToan p: ds ) {
			if (p.getMaHoaDon().equals(hd.getMaHoaDon()))
				return false;
		}
		ds.add(hd);
		return true;
	}
	
	public Boolean xoaHoaDon(int viTri) {
		if (viTri >= 0 && viTri < ds.size()) {
			ds.remove(viTri);
			return true;
		}
		return false;
	}
	
	public int timHoaDonTheoMa(String maHoaDon) {
		for (int i = 0; i<ds.size(); i++ ) {
			if (ds.get(i).getMaHoaDon().equals(maHoaDon))
				return i;
		}
		return -1;
		
	}
	public boolean suaHoaDon(HoaDonThanhToan hd) {
		for (int i = 0; i < ds.size(); i++)
			if (ds.get(i).getMaHoaDon().equalsIgnoreCase(hd.getMaHoaDon())) {
				ds.get(i).setHinhThucThanhToan(hd.getHinhThucThanhToan());
				ds.get(i).setNgayThanhToan(hd.getNgayThanhToan());
				ds.get(i).setTongThanhToan(hd.getTongThanhToan());
				ds.get(i).setGhiChu(hd.getGhiChu());
				return true;
			}
		return false;
	}

	public ArrayList<HoaDonThanhToan> getList() {
		return ds;
	}

	public int soLuongHoaDon() {
		return ds.size();
	}
}
