package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.DichVu;
import Entity.HoaDonDichVuPhong;
import connectDB.ConnectDB;

public class DAODichVu {
	public ArrayList<DichVu> getAll() {
		ArrayList<DichVu> danhSachDichVu = new ArrayList<DichVu>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			//ThanhTienDichVu = SoLuong * Gia
			String sql = "select *from DichVu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				danhSachDichVu.add(new DichVu(
						rs.getString("MaDichVu"),
						rs.getString("TenDichVu"),
						rs.getDouble("Gia")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return danhSachDichVu;
	}
	
	public boolean add(DichVu dv) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO DichVu (MaDichVu,TenDichVu,Gia) "
				+ "values(?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, dv.getMaDichVu());
			stm.setString(2, dv.getTenDichVu());
			stm.setDouble(3, dv.getGiaDichVu());
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
	
	public boolean updateGia(DichVu dv) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE DichVu "
							+ "Set Gia = ? "
							+ ",TenDichVu = ? "
							+ "where MaDichVu = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setDouble(1, dv.getGiaDichVu());
			stm.setString(2, dv.getTenDichVu());
			stm.setString(3, dv.getMaDichVu());
			
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
	
	public void delete(String maDichVu) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from DichVu "
				+ "where MaDichVu = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maDichVu);
			
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
