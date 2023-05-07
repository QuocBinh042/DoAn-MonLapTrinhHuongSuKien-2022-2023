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
			String sql = "select MaDatPhong,dvp.MaDichVu,SoLuong,dv.Gia,ThanhTienDichVu from HoaDonDichVuPhong dvp join DichVu dv on dvp.MaDichVu = dv.MaDichVu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				dsDVP.add(new HoaDonDichVuPhong(rs.getString("MaDatPhong"), rs.getString("MaDichVu"), rs.getInt("SoLuong"), rs.getFloat("Gia"), rs.getFloat("ThanhTienDichVu")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsDVP;
	}
	
	public boolean add(HoaDonDichVuPhong dvp) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO HoaDonDichVuPhong (MaDatPhong,MaDichVu,SoLuong) "
				+ "values(?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, dvp.getMaDatPhong());
			stm.setString(2, dvp.getMaDichVu());
			stm.setInt(3, dvp.getSoLuong());
			System.out.println(stm);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally {
			close(stm);
		}
		return true;
	}

	public boolean updateSoLuong(HoaDonDichVuPhong dvp) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE HoaDonDichVuPhong "
				+ "Set SoLuong = ? "
				+ "where MaDatPhong = ? "
				+ "and MaDichVu = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1, dvp.getSoLuong());
			stm.setString(2, dvp.getMaDatPhong());
			stm.setString(3, dvp.getMaDichVu());
			
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally {
			close(stm);
		}
		return true;
	}
	
	public void updateThanhTien(String maDP, String maDv) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE HoaDonDichVuPhong "
				+ "Set ThanhTienDichVu = SoLuong * (select Gia from DichVu where MaDichVu = ?)"
				+ "where MaDatPhong = ? "
				+ "and MaDichVu = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maDv);
			stm.setString(2, maDP);
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
				+ "where MaDatPhong = ? and MaDichVu = ?";
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
