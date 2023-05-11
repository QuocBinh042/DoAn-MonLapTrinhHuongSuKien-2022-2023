package DAO;

//sai thư viện
//import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DanhSach.DanhSachKhachHang;
import Entity.KhachHang;
import connectDB.ConnectDB;

public class DAOKhachHang {

	public DanhSachKhachHang getAll() {
		DanhSachKhachHang dsKhachHang = new DanhSachKhachHang();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsKhachHang.themKHang(new KhachHang(
				// getString phải trùng với tên của các trường trong sql
						rs.getString("MaKhachHang"), rs.getString("TenKhachHang"), rs.getString("CMT"),
						rs.getString("SDT"), rs.getString("Gmail")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	public boolean add(KhachHang kh) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO KhachHang (MaKhachHang,TenKhachHang,CMT, SDT, Gmail) " + "values(?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, kh.getMaKHang());
			stm.setString(2, kh.getTenKHang());
			stm.setString(3, kh.getCMThu());
			stm.setString(4, kh.getSDThoai());
			stm.setString(5, kh.getGmail());
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

	public boolean updateKhachHang(KhachHang kh) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Update KhachHang set TenKhachHang = ?, CMT = ?, SDT = ?, Gmail = ?\r\n"
				+ "where MaKhachHang = ?";
		try {
			stm = con.prepareStatement(sql);
			
			stm.setString(1, kh.getTenKHang());
			stm.setString(2, kh.getCMThu());
			stm.setString(3, kh.getSDThoai());
			stm.setString(4, kh.getGmail());
			stm.setString(5, kh.getMaKHang());
			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(stm);
		}
		return false;
	}

	public void delete(String maKHang) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from KhachHang " + "where MaKhachHang = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maKHang);

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
