package khachhang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class Database implements Serializable {
	public Database() {
		super();
	}

	 
	public DanhSachKhachHang read_KH(String part) throws Exception {
		DanhSachKhachHang ds = new DanhSachKhachHang();
		File f = new File(part);
		if (f.exists()) {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) { 
				String line = sc.nextLine();
				String[] data = line.split(","); 
				KhachHang kh = new KhachHang(data[0], data[1], data[2], data[3], (data[4]));
				ds.themKHang(kh);
			}
			sc.close();
		} else {
			f.createNewFile();
		}
		return ds;
	}

	
	public void writeKH(String part, DanhSachKhachHang ds) throws Exception {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(part), true); 
			for (KhachHang kh : ds.getList()) {
				String data = kh.getMaKHang() + "," + kh.getTenKHang() + "," + kh.getCMThu() + "," + kh.getSDThoai() + "," + kh.getGmail();
				out.println(data);
			}
		}catch (Exception e) {
			System.out.println("Không ghi được file!");
		
		
	}
	}

	
	

	public void saveFile(String fileName, Object o) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
			oos.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "IO Error!");
			return;
		}
	}

	 
	public Object readFile(String fileName) {
		Object o = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			o = ois.readObject();
			ois.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "IO Error!");
		}
		return o;
	}
	
}