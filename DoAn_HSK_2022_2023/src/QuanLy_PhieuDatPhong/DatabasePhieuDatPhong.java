package QuanLy_PhieuDatPhong;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

public class DatabasePhieuDatPhong implements Serializable {
	public DatabasePhieuDatPhong() {

	}

	public DanhSachPhieuDatPhong read_PhieuDatPhong(String part) throws Exception {
		DanhSachPhieuDatPhong ds = new DanhSachPhieuDatPhong();

		File f = new File(part);
		if (f.exists()) {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split(",");
				PhieuDatPhong pdp = new PhieuDatPhong(data[0], Integer.parseInt(data[1]), data[2], data[3],
						Double.parseDouble(data[5]), data[6], data[7]);
				ds.themPhieuDatPhong(pdp);
			}
			sc.close();
		} else {
			f.createNewFile();
		}
		return ds;

	}
	
	public void write_PhieuDatPhong(String part, DanhSachPhieuDatPhong ds) throws Exception {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(part), true);
			for (PhieuDatPhong pdp: ds.getList()) {
				String data = pdp.getMaPhong() + "," + pdp.getSoNguoi() + "," + pdp.getNgayDen() + "," + pdp.getNgayDi() + ","
						+ pdp.getGiaTien() + "," + pdp.getNgayDatPhong() + "," + pdp.getGhiChu();
				out.print(data);
			}

		} catch (Exception e) {
			System.out.println("Không ghi được file!");

		}
	}
}
