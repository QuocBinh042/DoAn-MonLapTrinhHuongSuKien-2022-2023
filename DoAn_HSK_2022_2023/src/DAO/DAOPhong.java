package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.Phong;
import connectDB.ConnectDB;

public class DAOPhong {
	
	public List<Phong> getAll(){
		List<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select *from Phong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				dsPhong.add(new Phong(
				rs.getString("MaPhong"),
				rs.getString("TenPhong"),
				rs.getString("LoaiPhong"),
				rs.getString("MoTa"),
				rs.getDouble("GiaPhong"),
				rs.getString("TinhTrang")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	public void add(Phong phong) {
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
		}
		finally {
			close(stm);
		}
		
	}
	public void updateSoLuongPhong(Phong phong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE Phong "
				+ " MaPhong = ? "
				+ " TenPhong = ? "
				+ " LoaiPhong = ? "
				+ " GiaPhong = ?"
				+ " MoTa = ? "
				+ " TinhTrang = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, phong.getMaPhong());
			stm.setString(2, phong.getTenPhong());
			stm.setString(3, phong.getLoaiPhong());
			stm.setDouble(4, phong.getGiaPhong());
			stm.setString(5, phong.getMoTa());
			stm.setString(6, phong.getTinhTrang());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
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
				+ " MaPhong = ?";
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
