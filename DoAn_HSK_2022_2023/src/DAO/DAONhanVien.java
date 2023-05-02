

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Entity.NhanVien;
import connectDB.ConnectDB;

public class DAONhanVien{

	public List<NhanVien> getAll() {
		List<NhanVien> DanhSachNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from NhanVien";
			Statement statement =con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			DanhSachNhanVien.add(new NhanVien(
					rs.getString("MaNhanVien"),
					rs.getString("HoTen"),
					rs.getString("Pwd"),
					rs.getString("ChucVu"),
					rs.getString("GioiTinh"), 
					rs.getString("CMT"), 
					rs.getString("SDT"), 
					rs.getString("Gmail"), 
					rs.getString("DiaChi")));
		}			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			return DanhSachNhanVien;	
	}	
	
		

	public void add(NhanVien nv) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO NhanVien (maNV, hoTen, matKhau, chucVu, gioiTinh, cmthu, sdthoai, gmail, diaChi) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, nv.getMaNV());
			stm.setString(2, nv.getHoTen());
			stm.setString(3, nv.getMatKhau());
			stm.setString(4, nv.getChucVu());
			stm.setString(5, nv.getGioiTinh());
			stm.setString(6, nv.getCmthu());
			stm.setString(7, nv.getSdthoai());
			stm.setString(8, nv.getGmail());
			stm.setString(9, nv.getDiaChi());
			System.out.println(stm);
			stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			close(stm);
		}
	}

	public void updateSoLuong(NhanVien nv) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE NhanVien "
				+ " maNV = ? "
				+ " hoTen = ? "
				+ " matKhau = ? "
				+ " chucVu = ? "
				+ " gioiTinh = ? "
				+ " cmthu = ? "
				+ " sdthoai = ? "
				+ " gmail = ? "
				+ " diaChi = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, nv.getMaNV());
			stm.setString(2, nv.getHoTen());
			stm.setString(3, nv.getMatKhau());
			stm.setString(4, nv.getChucVu());
			stm.setString(5, nv.getGioiTinh());
			stm.setString(6, nv.getCmthu());
			stm.setString(7, nv.getSdthoai());
			stm.setString(8, nv.getGmail());
			stm.setString(9, nv.getDiaChi());
			
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			close(stm);
		}
	}
	public void delete(String maNV) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from NhanVien "
				+ " maNV = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maNV);
			
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	private void close(PreparedStatement stm) {
		// TODO Auto-generated method stub
		if(stm!=null) {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}




