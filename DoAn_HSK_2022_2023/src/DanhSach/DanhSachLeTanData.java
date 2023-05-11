package DanhSach;

import java.util.ArrayList;

import Entity.LeTanData;

public class DanhSachLeTanData {
	
	private ArrayList<LeTanData> ds;
	
	public DanhSachLeTanData() {
		ds = new ArrayList<LeTanData>();
	}
	
	public boolean them(LeTanData d) {
		for (int i = 0; i<ds.size(); i++ ) {
			if (ds.get(i).getMaDatPhong().equals(d.getMaDatPhong()))
				return false;
		}
		ds.add(d);
		return true;
	}
	
	public ArrayList<LeTanData> getList(){
		return ds;
	}
	
}
