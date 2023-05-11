package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DanhSach.DanhSachLeTanData;
import Entity.LeTanData;
import Entity.PhieuDatPhong;
import connectDB.ConnectDB;

public class DAOLeTanData {
	public ArrayList<LeTanData> dsCheckIn() {
		// TODO Auto-generated method stub
		List<LeTanData> ds = new ArrayList<LeTanData>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select pdp.MaDatPhong,kh.TenKhachHang,p.TenPhong,p.TinhTrang,pdp.NgayCheckIn,pdp.NgayCheckOut,pdp.GhiChu from PhieuDatPhong pdp\r\n"
					+ "join Phong p on pdp.MaPhong = p.MaPhong\r\n"
					+ "join KhachHang kh on kh.MaKhachHang = pdp.IDNguoiDatPhong\r\n"
					+ "where pdp.NgayCheckIn >= GETDATE() and p.TinhTrang = 0;";
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ds.add(
						new LeTanData(rs.getString("MaDatPhong"), rs.getString("TenKhachHang"), rs.getString("TenPhong"),
								rs.getString("TinhTrang"), rs.getDate("NgayCheckIn"), rs.getDate("NgayCheckOut"),rs.getString("GhiChu")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<LeTanData>) ds;
	}
	public ArrayList<LeTanData> dsCheckOut() {
		// TODO Auto-generated method stub
		List<LeTanData> ds = new ArrayList<LeTanData>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select pdp.MaDatPhong,kh.TenKhachHang,p.TenPhong,p.TinhTrang,pdp.NgayCheckIn,pdp.NgayCheckOut,pdp.GhiChu from PhieuDatPhong pdp\r\n"
					+ "join Phong p on pdp.MaPhong = p.MaPhong\r\n"
					+ "join KhachHang kh on kh.MaKhachHang = pdp.IDNguoiDatPhong\r\n"
					+ "where p.TinhTrang = 1 and pdp.NgayCheckOut > pdp.NgayCheckIn;";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				ds.add(
						new LeTanData(rs.getString("MaDatPhong"), rs.getString("TenKhachHang"), rs.getString("TenPhong"),
								rs.getString("TinhTrang"), rs.getDate("NgayCheckIn"), rs.getDate("NgayCheckOut"),rs.getString("GhiChu")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<LeTanData>) ds;
	}
	
}
