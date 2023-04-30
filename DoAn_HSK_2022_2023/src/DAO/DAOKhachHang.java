package DAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.KhachHang;
import connectDB.ConnectDB;

public class DAOKhachHang{

	public List<KhachHang> getAll() {
		List<KhachHang> DanhSachKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * KhachHang";
			Statement statement = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(sql);
		while(rs.next()) {
			DanhSachKhachHang.add(new KhachHang(
					rs.getString("maKHang"),
					rs.getString("tenKHang"),
					rs.getString("CMThu"),
					rs.getString("SDThoai"),
					rs.getString("Gmail")));
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
			return DanhSachKhachHang;	
	}	
	
		
	

	public void add(KhachHang kh) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO KhachHang (maKHang,tenKHang,CMThu, SDThoai, Gmail) "
				+ "values(?,?,?,?,?)";
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
		}
		finally {
			close(stm);
		}
	}

	public void updateSoLuong(KhachHang kh) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE KhachHang "
				+ " maKHang = ? "
				+ " tenKHang = ? "
				+ " CMThu = ? "
				+ " SDThoai = ?"
				+ " Gmail = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, kh.getMaKHang());
			stm.setString(2, kh.getTenKHang());
			stm.setString(3, kh.getCMThu());
			stm.setString(4, kh.getSDThoai());
			stm.setString(4, kh.getGmail());
			
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			close(stm);
		}
	}
	public void delete(String maKHang) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from KhachHang "
				+ " maKHang = ?";
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
		
	}
}





	

