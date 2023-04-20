package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FrmQuanLy extends JFrame implements ActionListener {
	JButton btnDsNhanVien, btnThongKe;
	private FrmNhanVien guiNhanVien = new FrmNhanVien();

	public FrmQuanLy() {
		setTitle("GIAO DIỆN CỦA QUẢN LÝ");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createGUI();
	}

	private void createGUI() {

		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(10));
		b.add(btnDsNhanVien = new JButton("DANH SÁCH NHÂN VIÊN"));
		b.add(btnThongKe = new JButton("THỐNG KÊ DOANH THU"));
		add(b, BorderLayout.NORTH);

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

	}
}
