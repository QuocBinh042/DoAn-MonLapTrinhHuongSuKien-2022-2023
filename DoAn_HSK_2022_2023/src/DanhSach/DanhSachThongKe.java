package DanhSach;

import java.util.ArrayList;

import Entity.ThongKe;

public class DanhSachThongKe {
	private ArrayList<ThongKe> ds;
	public DanhSachThongKe() {
		ds = new ArrayList<ThongKe>();
	}
	public boolean them(ThongKe tk) {
		ds.add(tk);
		return true;
	}
	public ArrayList<ThongKe> getList(){
		return ds;
	}
	public void clear() {
	    for (int i = 0; i < ds.size(); i++)
	    	ds.remove(i);
	}
}
