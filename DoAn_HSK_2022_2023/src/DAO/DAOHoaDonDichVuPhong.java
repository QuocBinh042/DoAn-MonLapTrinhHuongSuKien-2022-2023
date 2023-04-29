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

	private ResultSet rs;
	public ArrayList<HoaDonDichVuPhong> getAll() {
		// TODO Auto-generated method stubArrayList<HoaDonDichVuPhong> dsDVP = new ArrayList<HoaDonDichVuPhong>();
		ArrayList<HoaDonDichVuPhong> dsDVP = new ArrayList<HoaDonDichVuPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
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
	public float getGiaFromMaDV(String maDV) {
		// TODO Auto-generated method stubArrayList<HoaDonDichVuPhong> dsDVP = new ArrayList<HoaDonDichVuPhong>();
		float gia = -1;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "select Gia from DichVu where MaDichVu = ?";
		rs = null;
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maDV);
			stm.executeUpdate();
			while (rs.next()) {
				gia = rs.getFloat("Gia");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			close(stm);
		}
		return gia;
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
	public void updateThanhTien(String maP, String maDv) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE HoaDonDichVuPhong "
				+ "Set ThanhTienDichVu = SoLuong * ?"
				+ "where MaPhong = ? "
				+ "and MaDichVu = ? ";
		float gia = getGiaFromMaDV(maDv);
		System.out.println(gia);
		try {
			stm = con.prepareStatement(sql);
			stm.setFloat(1, gia);
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
	
	
}
