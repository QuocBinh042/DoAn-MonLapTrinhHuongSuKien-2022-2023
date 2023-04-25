package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
	private JButton btnDSPhong, btnDSKhachHang, btnDSPhieuDatPhong, btnDSHoaDonThanhToan, btnDichVu, btnThoat;
	private JButton btnDSCheckin, btnDSCheckout;
	private FrmPhieuDatPhong frmPDP = new FrmPhieuDatPhong();
	private FrmHoaDonThanhToan frmHDTT = new FrmHoaDonThanhToan();
	private FrmPhong frmDSP = new FrmPhong();
	private FrmKhachHang frmDSKH = new FrmKhachHang();
	private FrmDichVu frmDV = new FrmDichVu();
	private DefaultTableModel tableModel;
	private JTable table;

	public FrmNVLeTan() {
		setTitle("QUẢN LÝ THÔNG TIN ĐẶT PHÒNG KHÁCH SẠN");
		setSize(850, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		createGUI();
	}

	private void createGUI() {
		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		JLabel lblTilte;
		pnlNorth.add(lblTilte = new JLabel("QUẢN LÝ THÔNG TIN ĐẶT PHÒNG KHÁCH SẠN"));
		lblTilte.setFont(new Font("Arial", Font.BOLD, 20));
		lblTilte.setForeground(Color.BLUE);

		Box bb = Box.createVerticalBox();
		bb.setBorder(BorderFactory.createEtchedBorder());
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createVerticalBox();
		Box b = Box.createHorizontalBox();
		b1.add(btnDSPhong = new JButton("PHÒNG"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(btnDSKhachHang = new JButton("THÔNG TIN KHÁCH HÀNG"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(btnDichVu = new JButton("DỊCH VỤ"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(btnDSPhieuDatPhong = new JButton("PHIẾU ĐẶT PHÒNG"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(btnDSHoaDonThanhToan = new JButton("HOÁ ĐƠN THANH TOÁN"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(btnThoat = new JButton("THOÁT"));
		btnThoat.setBackground(Color.RED);
		btnThoat.setForeground(Color.WHITE);
		bb.add(b1);
		add(bb, BorderLayout.CENTER);
		b2.setBorder(BorderFactory.createTitledBorder("Danh sách"));
		b2.add(btnDSCheckin = new JButton("Danh sách chờ đến  "));
		b2.add(btnDSCheckout = new JButton("Danh sách checkout"));
		b2.add(Box.createVerticalGlue());

		b.add(b2);
		bb.add(Box.createVerticalStrut(10));
		bb.add(b);
		String[] headers = "Mã khách hàng;Họ tên; Mã đặt phòng".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		b.add(scroll);
		bb.add(b);

		btnDSPhong.addActionListener(this);
		btnDSKhachHang.addActionListener(this);
		btnDSPhieuDatPhong.addActionListener(this);
		btnDSHoaDonThanhToan.addActionListener(this);
		btnDichVu.addActionListener(this);
		btnThoat.addActionListener(this);

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
		if (o.equals(btnDichVu)) {
			frmDV.setVisible(true);
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
		if (o.equals(btnThoat)) {
			System.exit(0);
			;
		}

	}
}
