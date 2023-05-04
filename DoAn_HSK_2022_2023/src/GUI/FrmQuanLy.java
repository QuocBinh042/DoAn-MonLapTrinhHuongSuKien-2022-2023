package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrmQuanLy extends JFrame implements ActionListener {
	JButton btnDsNhanVien, btnThongKe, btnThoat;
	private FrmNhanVien guiNhanVien = new FrmNhanVien();
	private FrmHoaDonThanhToan frmHDTT = new FrmHoaDonThanhToan();

	public FrmQuanLy() {
		setTitle("GIAO DIỆN CỦA QUẢN LÝ");
		setSize(800, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		createGUI();
	}

	private void createGUI() {
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(btnDsNhanVien = new JButton("QUẢN LÝ NHÂN VIÊN"));
		btnDsNhanVien.setFont(new Font("Arial", Font.BOLD, 20));
		btnDsNhanVien.setBounds(250, 50, 300, 50);
		contentPane.add(btnThongKe = new JButton("THỐNG KÊ DOANH THU"));
		btnThongKe.setFont(new Font("Arial", Font.BOLD, 20));

		btnThongKe.setBounds(250, 150, 300, 50);
		contentPane.add(btnThoat = new JButton("THOÁT"));
		btnThoat.setFont(new Font("Arial", Font.BOLD, 20));
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setBackground(Color.ORANGE);
		btnThoat.setBounds(250, 250, 300, 50);

		btnThoat.addActionListener(this);
		btnDsNhanVien.addActionListener(this);
		btnThongKe.addActionListener(this);

	}

	public static void main(String[] args) {
		new FrmQuanLy().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDsNhanVien)) {
			guiNhanVien.setVisible(true);
		}
		if (o.equals(btnThongKe)) {
			frmHDTT.setVisible(true);
		}
		if (o.equals(btnThoat)) {
			System.exit(0);
		}
	}
}
