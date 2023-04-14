package nhanvien;

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

	 
	public DanhSachNhanVien read_NV(String part) throws Exception {
		DanhSachNhanVien ds = new DanhSachNhanVien();
		File f = new File(part);
		if (f.exists()) {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) { 
				String line = sc.nextLine();
				String[] data = line.split(","); 
				NhanVien nv = new NhanVien(data[0], data[1], data[2], data[3], (data[4]),(data[5]));
				ds.themNhanVien(nv);
			}
			sc.close();
		} else {
			f.createNewFile();
		}
		return ds;
	}

	
	public void writeNV(String part, DanhSachNhanVien ds) throws Exception {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream(part), true); 
			for (NhanVien nv : ds.getList()) {
				String data = nv.getMaNV() + "," + nv.getHoTen() + "," + nv.getCMThu() + "," + nv.getSdthoai() + "," + nv.getGmail() + "," + nv.getChucVu();
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