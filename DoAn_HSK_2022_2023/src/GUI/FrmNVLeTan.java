package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FrmNVLeTan extends JFrame implements ActionListener {
	private JButton btnDSPhong, btnDSKhachHang, btnDSPhieuDatPhong, btnDSHoaDonThanhToan;
	private FrmPhieuDatPhong frmPDP = new FrmPhieuDatPhong();
	private FrmHoaDonThanhToan frmHDTT = new FrmHoaDonThanhToan();
	private FrmPhong frmDSP = new FrmPhong();
	private FrmKhachHang frmDSKH = new FrmKhachHang();

	public FrmNVLeTan() {
		setTitle("GIAO DIỆN CỦA LỄ TÂN");
		setSize(800, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createGUI();
	}

	private void createGUI() {
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(10));
		b.add(btnDSPhong = new JButton("DANH SÁCH PHÒNG"));
		btnDSPhong.setBackground(Color.white);
		b.add(btnDSKhachHang = new JButton("DANH SÁCH KHÁCH HÀNG"));
		
		b.add(btnDSPhieuDatPhong = new JButton("DANH SÁCH PHIẾU ĐẶT PHÒNG"));
		b.add(btnDSHoaDonThanhToan = new JButton("DANH SÁCH HOÁ ĐƠN THANH TOÁN"));
		add(b, BorderLayout.NORTH);

		btnDSPhong.addActionListener(this);
		btnDSKhachHang.addActionListener(this);
		btnDSPhieuDatPhong.addActionListener(this);
		btnDSHoaDonThanhToan.addActionListener(this);
	}

	public static void main(String[] args) {
		new FrmNVLeTan().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDSPhong)) {
			frmDSP.setVisible(true);
		}
		if (o.equals(btnDSKhachHang)) {
			frmDSKH.setVisible(true);
		}
		if (o.equals(btnDSPhieuDatPhong)) {
			frmPDP.setVisible(true);
		}
		if (o.equals(btnDSHoaDonThanhToan)) {
			frmHDTT.setVisible(true);
		}

	}
}
