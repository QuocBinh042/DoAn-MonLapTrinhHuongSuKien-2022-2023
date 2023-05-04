package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DanhSach.DanhSachPhieuDatPhong;
import Entity.PhieuDatPhong;
import connectDB.ConnectDB;

public class DAOPhieuDatPhong {

	public ArrayList<PhieuDatPhong> getAll() {
		// TODO Auto-generated method stub
		List<PhieuDatPhong> DanhSachPhieuDatPhong = new ArrayList<PhieuDatPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select *from PhieuDatPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				DanhSachPhieuDatPhong.add(new PhieuDatPhong(rs.getString("MaDatPhong"), rs.getString("MaNV"),
						rs.getString("MaPhong"), rs.getString("IDNguoiDatPhong"), rs.getString("MaHoaDon"),
						rs.getDate("NgayDatPhong"), rs.getDate("NgayCheckOut"), rs.getDate("NgayCheckIn"),
						rs.getInt("SoNguoi"), rs.getString("GhiChu")));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (ArrayList<PhieuDatPhong>) DanhSachPhieuDatPhong;
	}

	public void add(PhieuDatPhong pdp) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO PhieuDatPhong (MaDatPhong,MaNV, MaPhong, IDNguoiDatPhong, MaHoaDon, NgayDatPhong, NgayCheckIn, NgayCheckOut, SoNguoi, GhiChu)  "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, pdp.getMaDatPhong());
			stm.setString(2, pdp.getMaNhanVien());
			stm.setString(3, pdp.getMaPhong());
			stm.setString(4, pdp.getMaKhachHang());
			stm.setString(5, pdp.getMaHoaDon());
			stm.setDate(6, pdp.getNgayDatPhong());
			stm.setDate(7, pdp.getNgayDen());
			stm.setDate(8, pdp.getNgayDi());
			stm.setInt(9, pdp.getSoNguoi());
			stm.setString(10, pdp.getGhiChu());
			System.out.println(stm);
			stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stm);
		}
	}

	public void update(PhieuDatPhong pdp) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE PhieuDatPhong " + " MaDatPhong = ? " + " MaNV = ? " + " MaPhong = ? "
				+ " IDNguoiDatPhong = ?" + " MaHoaDon = ? " + " NgayDatPhong = ? " + " NgayCheckIn = ? "
				+ " NgayCheckOut = ? " + " SoNguoi = ? " + " GhiChu = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, pdp.getMaDatPhong());
			stm.setString(2, pdp.getMaNhanVien());
			stm.setString(3, pdp.getMaPhong());
			stm.setString(4, pdp.getMaKhachHang());
			stm.setString(5, pdp.getMaHoaDon());
			stm.setDate(6, pdp.getNgayDatPhong());
			stm.setDate(7, pdp.getNgayDen());
			stm.setDate(8, pdp.getNgayDi());
			stm.setInt(9, pdp.getSoNguoi());
			stm.setString(10, pdp.getGhiChu());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stm);
		}
	}

	public void delete(String maDatPhong) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from PhieuDatPhong " + " MaDatPhong = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maDatPhong);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	private void close(PreparedStatement stm) {
		// TODO Auto-generated method stub
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
