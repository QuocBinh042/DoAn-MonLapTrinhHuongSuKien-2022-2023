package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.HoaDonDichVuPhong;
import connectDB.ConnectDB;

public class DAOHoaDonDichVuPhong implements DAOInterface<HoaDonDichVuPhong>{

	@Override
	public ArrayList<HoaDonDichVuPhong> getAll() {
		// TODO Auto-generated method stub
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

	@Override
	public void add(HoaDonDichVuPhong t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(HoaDonDichVuPhong t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(HoaDonDichVuPhong t) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		System.out.println(con);
	}
}
