package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.HoaDonThanhToan;
import connectDB.ConnectDB;

public class DAOHoaDonThanhToan {
	
	public List<HoaDonThanhToan> getAll(){
		List<HoaDonThanhToan> DanhSachHoaDonThanhToan = new ArrayList<HoaDonThanhToan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select *from HoaDonThanhToan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				DanhSachHoaDonThanhToan.add(new HoaDonThanhToan(
				rs.getString("MaHoaDon"),
				rs.getString("NgayThanhToan"),
				rs.getString("HinhThucThanhToan"),
				rs.getDouble("ThanhTienPhong"),
				rs.getDouble("TongThanhToan"),
				rs.getString("GhiChu")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DanhSachHoaDonThanhToan;
	}
	
	public void add(HoaDonThanhToan hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO HoaDonThanhToan (MaHoaDon, ngayThanhToan, hinhThucThanhToan, thanhTienPhong, tongThanhToan, ghiChu) "
				+ "values(?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, hd.getMaHoaDon());
			stm.setString(2, hd.getNgayThanhToan());
			stm.setString(3, hd.getHinhThucThanhToan());
			stm.setDouble(4, hd.getThanhTienPhong());
			stm.setDouble(5, hd.getTongThanhToan());
			stm.setString(6, hd.getGhiChu());
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
	public void updateHoaDonThanhToan(HoaDonThanhToan hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE HoaDonThanhToan "
				+ " MaHoaDon = ? "
				+ " NgayThanhToan = ? "
				+ " HinhThucThanhToan = ? "
				+ " ThanhTienPhong = ?"
				+ " TongThanhToan = ? "
				+ " GhiChu = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, hd.getMaHoaDon());
			stm.setString(2, hd.getNgayThanhToan());
			stm.setString(3, hd.getHinhThucThanhToan());
			stm.setDouble(4, hd.getThanhTienPhong());
			stm.setDouble(5, hd.getTongThanhToan());
			stm.setString(6, hd.getGhiChu());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			close(stm);
		}
	}
	
	public void delete(String maHoaDon) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from HoaDonThanhToan "
				+ " MaHoaDon = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maHoaDon);
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