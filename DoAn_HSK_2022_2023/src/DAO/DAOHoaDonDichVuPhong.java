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
		String sql = "INSERT INTO HoaDonDichVuPhong (MaPhong,MaDichVu,SoLuong)"+"values(?,?,?)";
		System.out.println(sql);
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


	public void update() {
		// TODO Auto-generated method stub
		
	}


	public void delete() {
		// TODO Auto-generated method stub
		
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
