package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DanhSach.DanhSachPhong;
import Entity.Phong;
import connectDB.ConnectDB;

public class DAOPhong {
	
	public DanhSachPhong getAll(){
		DanhSachPhong dsPhong = new DanhSachPhong();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select *from Phong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				dsPhong.themPhong(new Phong(
				rs.getString("MaPhong"),
				rs.getString("TenPhong"),
				rs.getString("LoaiPhong"),
				rs.getDouble("GiaPhong"),
				rs.getString("MoTa"),
				rs.getString("TinhTrang")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	public boolean add(Phong phong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO Phong (MaPhong,TenPhong,LoaiPhong,GiaPhong,MoTa,TinhTrang) "
				+ "values(?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, phong.getMaPhong());
			stm.setString(2, phong.getTenPhong());
			stm.setString(3, phong.getLoaiPhong());
			stm.setDouble(4, phong.getGiaPhong());
			stm.setString(5, phong.getMoTa());
			stm.setString(6, phong.getTinhTrang());
			System.out.println(stm);
			stm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally {
			close(stm);
		}
		return true;
		
	}
	public boolean updatePhong(Phong phong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int tt;
		if(phong.getTinhTrang().equalsIgnoreCase("Đã đặt")) {
			tt=1;
		}else {
			tt=0;
		}
		String sql = "Update Phong set TenPhong = ?, LoaiPhong = ?, GiaPhong = ?, MoTa = ?, TinhTrang = ?\r\n"
				+ "where MaPhong = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, phong.getTenPhong());
			stm.setString(2, phong.getLoaiPhong());
			stm.setDouble(3, phong.getGiaPhong());
			stm.setString(4, phong.getMoTa());
			stm.setInt(5, tt);
			stm.setString(6, phong.getMaPhong());
			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally {
			close(stm);
		}
		
	}
	
	public void delete(String maPhong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from Phong "
				+ "where MaPhong = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maPhong);
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
