package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.HoaDonDichVuPhong;
import connectDB.ConnectDB;

public class DAOHoaDonDichVuPhong{

	public ArrayList<HoaDonDichVuPhong> getAll() {
		// TODO Auto-generated method stubArrayList<HoaDonDichVuPhong> dsDVP = new ArrayList<HoaDonDichVuPhong>();
		ArrayList<HoaDonDichVuPhong> dsDVP = new ArrayList<HoaDonDichVuPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			//ThanhTienDichVu = SoLuong * Gia
			String sql = "select MaPhong,dvp.MaDichVu,SoLuong,dv.Gia,ThanhTienDichVu from HoaDonDichVuPhong dvp join DichVu dv on dvp.MaDichVu = dv.MaDichVu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				dsDVP.add(new HoaDonDichVuPhong(rs.getString("MaPhong"), rs.getString("MaDichVu"), rs.getInt("SoLuong"), rs.getFloat("Gia"), rs.getFloat("ThanhTienDichVu")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsDVP;
	}
	
	public void add(HoaDonDichVuPhong dvp) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO HoaDonDichVuPhong (MaPhong,MaDichVu,SoLuong) "
				+ "values(?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, dvp.getMaPhong());
			stm.setString(2, dvp.getMaDichVu());
			stm.setInt(3, dvp.getSoLuong());
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

	public void updateSoLuong(HoaDonDichVuPhong dvp) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE HoaDonDichVuPhong "
				+ "Set SoLuong = ? "
				+ "where MaPhong = ? "
				+ "and MaDichVu = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1, dvp.getSoLuong());
			stm.setString(2, dvp.getMaPhong());
			stm.setString(3, dvp.getMaDichVu());
			
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			close(stm);
		}
	}

	public void delete(String maP, String maDV) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from HoaDonDichVuPhong "
				+ "where MaPhong = ? and MaDichVu = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maP);
			stm.setString(2, maDV);
			
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	public void close(PreparedStatement stm) {
		if(stm!=null) {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public void updateThanhTien(String maP, String maDv) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE HoaDonDichVuPhong "
				+ "Set ThanhTienDichVu = SoLuong * (select Gia from DichVu where MaDichVu = ?)"
				+ "where MaPhong = ? "
				+ "and MaDichVu = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maDv);
			stm.setString(2, maP);
			stm.setString(3, maDv);
			
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			close(stm);
		}
}
	
//	public float getGiaFromMaDV(String maDV) {
//	// TODO Auto-generated method stubArrayList<HoaDonDichVuPhong> dsDVP = new ArrayList<HoaDonDichVuPhong>();
//	float gia = -1;		
//	ConnectDB.getInstance();
//	Connection con = ConnectDB.getConnection();
//	String sql = "select Gia from DichVu where MaDichVu = \'" + maDV+"\'";
//	System.out.println(sql);
//	try {
//		Statement statement = con.createStatement();
//		ResultSet rs = statement.executeQuery(sql);
//		while (rs.next()) {
//			gia = rs.getInt("Gia");
//			System.out.println(gia);
//		}
//	} catch (SQLException e) {
//		// TODO: handle exception
//		e.printStackTrace();
//	}
//	return gia;
//}
//



}
