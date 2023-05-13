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

	public DanhSachPhieuDatPhong getAll() {
		// TODO Auto-generated method stub
		DanhSachPhieuDatPhong dsPhieuDatPhong = new DanhSachPhieuDatPhong();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select *from PhieuDatPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsPhieuDatPhong.themPhieuDatPhong(
						new PhieuDatPhong(rs.getString("MaDatPhong"), rs.getString("MaNV"), rs.getString("MaPhong"),
								rs.getString("IDNguoiDatPhong"), rs.getDate("NgayDatPhong"), rs.getDate("NgayCheckIn"),
								rs.getDate("NgayCheckOut"), rs.getInt("SoNguoi"), rs.getString("GhiChu")));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhieuDatPhong;
	}

	public boolean add(PhieuDatPhong pdp) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO PhieuDatPhong (MaDatPhong,MaNV, MaPhong, IDNguoiDatPhong, NgayDatPhong, NgayCheckIn, NgayCheckOut, SoNguoi, GhiChu)  "
				+ "values(?,?,?,?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, pdp.getMaDatPhong());
			stm.setString(2, pdp.getMaNhanVien());
			stm.setString(3, pdp.getMaPhong());
			stm.setString(4, pdp.getMaKhachHang());
			stm.setDate(5, pdp.getNgayDatPhong());
			stm.setDate(6, pdp.getNgayDen());
			stm.setDate(7, pdp.getNgayDi());
			stm.setInt(8, pdp.getSoNguoi());
			stm.setString(9, pdp.getGhiChu());
			System.out.println(stm);
			stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			close(stm);
		}
		return true;
	}

	public boolean update(PhieuDatPhong pdp) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE PhieuDatPhong set MaNV = ?, MaPhong = ?, IDNguoiDatPhong = ?, NgayDatPhong = ?, NgayCheckIn = ?, NgayCheckOut = ?, SoNguoi = ?, GhiChu = ?\r\n"
				+ " where MaDatPhong = ?";
		try {
			stm = con.prepareStatement(sql);

			stm.setString(1, pdp.getMaNhanVien());
			stm.setString(2, pdp.getMaPhong());
			stm.setString(3, pdp.getMaKhachHang());
			stm.setDate(4, pdp.getNgayDatPhong());
			stm.setDate(5, pdp.getNgayDen());
			stm.setDate(6, pdp.getNgayDi());
			stm.setInt(7, pdp.getSoNguoi());
			stm.setString(8, pdp.getGhiChu());
			stm.setString(9, pdp.getMaDatPhong());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			close(stm);
		}
		return true;
	}

	public void delete(String maDatPhong) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from PhieuDatPhong " + "where MaDatPhong = ?";
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
