package QuanLy_HoaDonThanhToan;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

public class DatabaseHoaDonThanhToan implements Serializable{
	public DatabaseHoaDonThanhToan() {
		super();
	}

	// Doc file
	public DanhSachHoaDonThanhToan read_HoaDonThanhToan(String part) throws Exception {
		DanhSachHoaDonThanhToan ds = new DanhSachHoaDonThanhToan();
		File f = new File(part);
		if (f.exists()) {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(",");
				HoaDonThanhToan hd = new HoaDonThanhToan(data[0], data[1], data[2],Double.parseDouble(data[3]),data[4]);
				ds.themHoaDonThanhToan(hd);
			}
			sc.close();
		} else {
			f.createNewFile();
		}
		return ds;
	}

	// Ghi file
	public void write_HoaDonThanhToan(String part, DanhSachHoaDonThanhToan ds) throws Exception {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(part), true);
			for (HoaDonThanhToan hd: ds.getList()) {
				String data = hd.getMaHoaDon() + "," + hd.getNgayThanhToan() + "," + hd.getHinhThucThanhToan() + "," + hd.getTongThanhToan() + ","
						+ hd.getGhiChu();
				out.print(data);
			}

		} catch (Exception e) {
			System.out.println("Không ghi được file!");

		}
	}
}
