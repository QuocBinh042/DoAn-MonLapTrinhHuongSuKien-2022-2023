package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//import javax.swing.border.Border;
//
//import DAO.DAONhanVien;
//import DanhSach.DanhSachNhanVien;
//import Entity.NhanVien;
//
//public class FrmDangNhap2 extends JFrame implements ActionListener {
//	private JLabel lblUser,lblPass;
//	private JTextField txtUser;
//	private JPasswordField txtPass;
//	private JButton btnDangnhap, btnThoat;
//	private DAONhanVien DAO_NV;
//	private ArrayList<NhanVien> ds;
//	
//	public FrmDangNhap2() {
//		createGUI();
//	}
//	public static void main(String[] args) {
//		new FrmDangNhap2().setVisible(true);
//	}
//	public void createGUI() {
//		
//		setTitle("LOGIN");
//		setSize(500, 300);
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		JPanel panel = new JPanel();
//		panel.setLayout(null);
//		add(panel,BorderLayout.CENTER);
//		
//		
//		lblUser = new JLabel("User");
//		lblUser.setFont(new Font("Arial", ICONIFIED, 13));
//		lblUser.setBounds(40,20,100,40);
//		panel.add(lblUser);
//		
//		txtUser = new JTextField();
//		txtUser.setBounds(140,20,320,40);
//		panel.add(txtUser);
//		
//		lblPass = new JLabel("Password");
//		lblPass.setFont(new Font("Arial", ICONIFIED, 13));
//		lblPass.setBounds(40,80,100,40);
//		panel.add(lblPass);
//		
//		txtPass = new JPasswordField();
//		txtPass.setBounds(140,80,320,40);
//		panel.add(txtPass);
//		
//		btnDangnhap = new JButton("LOGIN");
//		btnDangnhap.setForeground(Color.WHITE);
//		btnDangnhap.setBackground(Color.GREEN);
//		
//		btnDangnhap.setBounds(200,140,120,35);
//		panel.add(btnDangnhap);
//		
//		btnThoat = new JButton("EXIT");
//		btnThoat.setForeground(Color.WHITE);
//		btnThoat.setBackground(Color.RED);
//		btnThoat.setBounds(340,140,120,35);
//		panel.add(btnThoat);
//		
//		
//		btnThoat.addActionListener(this);
//		btnDangnhap.addActionListener(this);
//	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//		Object o = e.getSource();
//		if(o.equals(btnThoat)) {
//			System.exit(0);
//		}
//		if(o.equals(btnDangnhap)) {
////			String user = txtUser.getText();
////			String pass = txtPass.getText();
////			if(user.equalsIgnoreCase("Admin")&&pass.equalsIgnoreCase("123")) {
////				JOptionPane.showMessageDialog(null, "Login Successful!");
////			}else {
////				JOptionPane.showMessageDialog(null, "Login failed!");
////			}
//			
//		}
//		
//	}
//	public void getList() {
//		ds = new ArrayList<NhanVien>();
//		String user = txtUser.getText();
//		String pass = txtPass.getText();
//
//	}
//	
//}
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import DAO.DAONhanVien;
import DAO.DAOPhieuDatPhong;
import DanhSach.DanhSachNhanVien;
import DanhSach.DanhSachPhieuDatPhong;
import Entity.NhanVien;
import Entity.PhieuDatPhong;
import connectDB.ConnectDB;

public class FrmDangNhap extends JFrame implements ActionListener {
	private FrmNVLeTan frmLeTan = new FrmNVLeTan();
	private FrmQuanLy frmQuanLy = new FrmQuanLy();
	private JLabel lblUser, lblPass;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnDangnhap, btnThoat;
	private DAONhanVien DAO_NV = new DAONhanVien();
	private DanhSachNhanVien dsNV = new DanhSachNhanVien();
	private DAOPhieuDatPhong DAO_pdp = new DAOPhieuDatPhong();
	private DanhSachPhieuDatPhong dsPDP = DAO_pdp.getAll();
	public FrmDangNhap() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createGUI();
	}

	public static void main(String[] args) {
		new FrmDangNhap().setVisible(true);
	}

	public void createGUI() {
		setTitle("LOGIN");
		setSize(520, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		add(panel, BorderLayout.CENTER);

		lblUser = new JLabel("User");
		lblUser.setFont(new Font("Arial", ICONIFIED, 13));
		lblUser.setBounds(40, 20, 100, 40);
		panel.add(lblUser);

		txtUser = new JTextField();
		txtUser.setBounds(140, 20, 320, 40);
		panel.add(txtUser);

		lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Arial", ICONIFIED, 13));
		lblPass.setBounds(40, 80, 100, 40);
		panel.add(lblPass);

		txtPass = new JPasswordField();
		txtPass.setBounds(140, 80, 320, 40);
		panel.add(txtPass);

		btnDangnhap = new JButton("LOGIN");
		btnDangnhap.setForeground(Color.WHITE);
		btnDangnhap.setBackground(Color.GREEN);

		btnDangnhap.setBounds(200, 140, 120, 35);
		panel.add(btnDangnhap);

		btnThoat = new JButton("EXIT");
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setBackground(Color.RED);
		btnThoat.setBounds(340, 140, 120, 35);
		panel.add(btnThoat);

		btnThoat.addActionListener(this);
		btnDangnhap.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			System.exit(0);
		}
		if (o.equals(btnDangnhap)) {
			changeForm();
			
		}

	}

	public void changeForm() {
		dsNV = DAO_NV.getAll();
		String user = txtUser.getText();
		String pass = txtPass.getText();
		if (dsNV.timNhanVienTheoMa(user) != -1) {
			if (dsNV.getList().get(dsNV.timNhanVienTheoMa(user)).getMatKhau().equalsIgnoreCase(pass)) {
				if (dsNV.getList().get(dsNV.timNhanVienTheoMa(user)).getChucVu().equals("1")) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công Hotel Manager!");
					frmQuanLy.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công Hotel Reception!");
					frmLeTan.setVisible(true);
					PhieuDatPhong pdp = new PhieuDatPhong("PDP000", user, "P015", "KH015", null, null, null, 0, null);
					DAO_pdp.update(pdp);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Lỗi! Vui lòng kiểm tra lại thông tin đăng nhập!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Lỗi! Tài khoản không tồn tại!");
		}

	}

}
