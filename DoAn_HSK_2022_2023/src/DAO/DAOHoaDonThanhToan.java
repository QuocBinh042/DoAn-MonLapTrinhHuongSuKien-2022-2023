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
				rs.getString("MaDatPhong"),
				rs.getDate("NgayThanhToan"),
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
	
	public boolean add(HoaDonThanhToan hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO HoaDonThanhToan (MaHoaDon, MaDatPhong, ngayThanhToan, hinhThucThanhToan, thanhTienPhong, tongThanhToan, ghiChu) "
				+ "values(?,?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, hd.getMaHoaDon());
			stm.setString(2, hd.getMaDatPhong());
			stm.setDate(3, hd.getNgayThanhToan());
			stm.setString(4, hd.getHinhThucThanhToan());
			stm.setDouble(5, hd.getThanhTienPhong());
			stm.setDouble(6, hd.getTongThanhToan());
			stm.setString(7, hd.getGhiChu());
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
	public void updateHoaDonThanhToan(HoaDonThanhToan hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "UPDATE HoaDonThanhToan "
				+ " MaHoaDon = ? "
				+ " MaDatPhong = ? "
				+ " NgayThanhToan = ? "
				+ " HinhThucThanhToan = ? "
				+ " ThanhTienPhong = ?"
				+ " TongThanhToan = ? "
				+ " GhiChu = ? ";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, hd.getMaHoaDon());
			stm.setString(2, hd.getMaHoaDon());
			stm.setDate(3, hd.getNgayThanhToan());
			stm.setString(4, hd.getHinhThucThanhToan());
			stm.setDouble(5, hd.getThanhTienPhong());
			stm.setDouble(6, hd.getTongThanhToan());
			stm.setString(7, hd.getGhiChu());
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
				+ "where MaHoaDon = ?";
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