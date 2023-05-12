package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.ThongKe;
import connectDB.ConnectDB;

public class DAOThongKe {
	public ArrayList<ThongKe> getAll(String start, String end) {
		ArrayList<ThongKe> dsTK = new ArrayList<ThongKe>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select p.MaPhong,TienDV = SUM(ThanhTienDichVu) ,TienPhong = Sum(ThanhTienPhong), Tong = SUM(ThanhTienDichVu) + SUM(ThanhTienPhong)\r\n"
					+ "from PhieuDatPhong pdp full join Phong p on pdp.MaPhong = p.MaPhong join HoaDonThanhToan hd on pdp.MaDatPhong = hd.MaDatPhong join HoaDonDichVuPhong dvp on pdp.MaDatPhong=dvp.MaDatPhong\r\n"
					+ "where NgayThanhToan BETWEEN CAST('"+start+"' AS DATE) AND CAST('"+end+"' AS DATE)\r\n"
					+ "group by p.MaPhong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				dsTK.add(new ThongKe(rs.getString("MaPhong"), rs.getFloat("TienDV"), rs.getFloat("TienPhong"), rs.getFloat("Tong")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTK;
	}
}
