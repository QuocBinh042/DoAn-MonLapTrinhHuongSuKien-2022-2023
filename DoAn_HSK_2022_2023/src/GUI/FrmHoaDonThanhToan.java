package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import DAO.DAOHoaDonDichVuPhong;
import DAO.DAOHoaDonThanhToan;
import DAO.DAOPhieuDatPhong;
import DAO.DAOPhong;
import DanhSach.DanhSachHoaDonDichVuPhong;
import DanhSach.DanhSachHoaDonThanhToan;
import DanhSach.DanhSachPhieuDatPhong;
import DanhSach.DanhSachPhong;
import Entity.HoaDonDichVuPhong;
import Entity.HoaDonThanhToan;
import Entity.PhieuDatPhong;
import Entity.Phong;
import connectDB.ConnectDB;

public class FrmHoaDonThanhToan extends JFrame implements ActionListener, MouseListener, ItemListener {
	private static final long serialVersionUID = 1L;
	private DanhSachHoaDonThanhToan ds = new DanhSachHoaDonThanhToan();
	private JTable table;
	private JTextField txtMaHoaDon, txtTongThanhToan, txtTim, txtThanhTienPhong, txtTienDichVu;
	private JTextArea txtaGhiChu;
	private JRadioButton radChuyenKhoan, radTienMat;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnSua, btnThoat;
	private DefaultTableModel tableModel;
	private JTextField txtMess;
	private Calendar dDate = Calendar.getInstance();
	private JComboBox yearTT, monthTT, dayTT, cbMaDatPhong, cbMaDichVu;
	private DAOHoaDonThanhToan DAO_hoaDon;
	private DAOPhieuDatPhong DAO_phieudDatPhong;
	private DAOPhong DAO_Phong = new DAOPhong();
	private DanhSachPhong dsPhong;
	private DAOHoaDonDichVuPhong DAO_DichVuPhong = new DAOHoaDonDichVuPhong();
	private List<HoaDonDichVuPhong> dsDVP;
	private DanhSachPhieuDatPhong dsPhieuDatPhong;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");
	private List<String> tenDichVu;

	public FrmHoaDonThanhToan() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createGUI();
	}

	public static void main(String[] args) {
		new FrmHoaDonThanhToan().setVisible(true);
	}

	private void createGUI() {
		setTitle("Quản lý hoá đơn thanh toán");
		setSize(1350, 700);
		setResizable(false);
		setLocationRelativeTo(null);

		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("QUẢN LÝ HOÁ ĐƠN THANH TOÁN");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.red);
		pnlNorth.add(lblTieuDe);

		// CENTER
		Box b = Box.createVerticalBox();
		Box bb = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, bTimMa;
		JLabel lblMaHoaDon, lblThanhTienPhong, lblNgayThanhToan, lblHinhThucThanhToan, lblTongThanhToan, lblGhiChu,
				lblMaDatPhong, lblTienDichVu;

		b.setBorder(BorderFactory.createTitledBorder("Hoá đơn thanh toán"));
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMaHoaDon = new JLabel("Mã hoá đơn: "));
		b1.add(Box.createHorizontalStrut(5));
		b1.add(txtMaHoaDon = new JTextField());

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaDatPhong = new JLabel("Mã đặt phòng: "));
		b1.add(Box.createHorizontalStrut(5));
		b1.add(cbMaDatPhong = new JComboBox<>());
		DAO_phieudDatPhong = new DAOPhieuDatPhong();
		dsPhieuDatPhong = DAO_phieudDatPhong.getAll();
		for (PhieuDatPhong pdp : dsPhieuDatPhong.getList()) {
			cbMaDatPhong.addItem(pdp.getMaDatPhong());
		}

		dsPhong = DAO_Phong.getAll();
		dsDVP = DAO_DichVuPhong.getAll();
		tenDichVu = DAO_DichVuPhong.getTenDichVu();

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblNgayThanhToan = new JLabel("Ngày thanh toán: "));
		b2.add(Box.createHorizontalStrut(5));
		yearTT = new JComboBox();
		buildYearsList(yearTT);
		yearTT.setSelectedIndex(5);
		monthTT = new JComboBox();
		buildMonthsList(monthTT);
		monthTT.setSelectedIndex(dDate.get(Calendar.MONTH));
		dayTT = new JComboBox();
		buildDaysList(dDate, dayTT, monthTT);
		dayTT.setSelectedItem(Integer.toString(dDate.get(Calendar.DATE)));
		b2.add(dayTT);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(monthTT);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(yearTT);

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblHinhThucThanhToan = new JLabel("Hình thức thanh toán: "));
		b3.add(Box.createHorizontalStrut(20));
		ButtonGroup bg = new ButtonGroup();
		bg.add(radChuyenKhoan = new JRadioButton("Chuyển khoản"));
		bg.add(radTienMat = new JRadioButton("Tiền mặt"));
		b3.add(radTienMat);
		b3.add(Box.createHorizontalStrut(35));
		b3.add(radChuyenKhoan);

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblThanhTienPhong = new JLabel("Thành tiền phòng: "));
		b4.add(Box.createHorizontalStrut(5));
		b4.add(txtThanhTienPhong = new JTextField());

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblTienDichVu = new JLabel("Phí dịch vụ: "));
		b4.add(Box.createHorizontalStrut(5));
		b4.add(txtTienDichVu = new JTextField());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b5.add(lblTongThanhToan = new JLabel("Tổng thanh toán"));
		b5.add(Box.createHorizontalStrut(5));
		b5.add(txtTongThanhToan = new JTextField());
		txtTongThanhToan.setFont(new Font("Arial", Font.BOLD, 20));
		txtTongThanhToan.setForeground(Color.red);
		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b6.add(lblGhiChu = new JLabel("Ghi chú: "));

		txtaGhiChu = new JTextArea(10, 10);
		txtaGhiChu.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		JScrollPane scrolll = new JScrollPane(txtaGhiChu);
		scrolll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrolll.setBorder(null);
		b6.add(Box.createHorizontalStrut(5));
		b6.add(scrolll);
		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b7.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		b.add(b8 = Box.createHorizontalBox());
		b8.add(Box.createHorizontalStrut(125));
		b8.add(btnThem = new JButton("Thêm"));
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btnSua = new JButton("Sửa"));
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btnXoaTrang = new JButton("Xoá trắng"));

		b.add(Box.createVerticalStrut(30));
		b.add(b9 = Box.createVerticalBox());

		b9.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		b9.add(Box.createVerticalStrut(10));
		b9.add(bTimMa = Box.createHorizontalBox());
		bTimMa.add(new JLabel("Mã hoá đơn thanh toán:  "));
		bTimMa.add(txtTim = new JTextField(10));
		bTimMa.add(btnTim = new JButton("Tìm"));
		b9.add(Box.createVerticalStrut(10));

		lblMaHoaDon.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblTienDichVu.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblMaDatPhong.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblThanhTienPhong.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblNgayThanhToan.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblTongThanhToan.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblGhiChu.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());

		add(b, BorderLayout.WEST);

		bb.setBorder(BorderFactory.createTitledBorder("Danh sách hoá đơn thanh toán"));
		String[] headers = "Mã hoá đơn; Mã đặt phòng; Ngày thanh toán; Hình thức thanh toán; Thành tiền phòng; Tổng thanh toán; Ghi chú"
				.split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(20);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bb.add(scroll);
		add(bb, BorderLayout.CENTER);

		// SOUTH
		JPanel pnlSouth;
		add(pnlSouth = new JPanel(), BorderLayout.SOUTH);
		pnlSouth.add(btnXoa = new JButton("Xoá"));
		btnXoa.setBackground(Color.ORANGE);
		btnXoa.setForeground(Color.WHITE);
		pnlSouth.add(btnLuu = new JButton("Lưu"));
		btnLuu.setBackground(Color.GREEN);
		btnLuu.setForeground(Color.WHITE);
		pnlSouth.add(btnThoat = new JButton("Thoát"));
		btnThoat.setBackground(Color.RED);
		btnThoat.setForeground(Color.WHITE);

		// Gọi hàm loadData
		loadData();
		// ĐĂNG KÝ SỰ KIỆN
		TXTedit_false();
		btnLuu.setEnabled(false);
		btnXoaTrang.setEnabled(false);
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
		table.addMouseListener(this);
		dayTT.addItemListener(this);
		monthTT.addItemListener(this);
		yearTT.addItemListener(this);
		cbMaDatPhong.addItemListener(this);
	}

	private void TXTedit_false() {
		txtMaHoaDon.setEditable(false);
		txtThanhTienPhong.setEditable(false);
		txtTongThanhToan.setEditable(false);
		radChuyenKhoan.setEnabled(false);
		txtTienDichVu.setEditable(false);
		radTienMat.setEnabled(false);
		dayTT.setEnabled(false);
		monthTT.setEnabled(false);
		yearTT.setEnabled(false);
		txtaGhiChu.setEditable(false);
	}

	private void TXTedit_true() {
		txtMaHoaDon.setEditable(true);
		radChuyenKhoan.setEnabled(true);
		radTienMat.setEnabled(true);
		dayTT.setEnabled(true);
		monthTT.setEnabled(true);
		yearTT.setEnabled(true);
		txtaGhiChu.setEditable(true);
	}

	public void deleteAllDataJtable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}
	}

	private void buildMonthsList(JComboBox monthsList) {
		monthsList.removeAllItems();
		for (int monthCount = 1; monthCount <= 12; monthCount++)
			monthsList.addItem(monthCount);
	}

	private void buildDaysList(Calendar dateIn, JComboBox daysList, JComboBox monthsList) {
		daysList.removeAllItems();
		dateIn.set(Calendar.MONTH, monthsList.getSelectedIndex());
		int lastDay = dDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int dayCount = 1; dayCount <= lastDay; dayCount++)
			daysList.addItem(Integer.toString(dayCount));
	}

	private void buildYearsList(JComboBox yearsList) {
		int currentYear = dDate.get(Calendar.YEAR);
		for (int yearCount = currentYear - 5; yearCount <= currentYear + 5; yearCount++)
			yearsList.addItem(Integer.toString(yearCount));
	}

	private String getNgayTT() {
		return yearTT.getSelectedItem().toString() + "-" + monthTT.getSelectedItem().toString() + "-"
				+ dayTT.getSelectedItem().toString();
	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}

	private boolean validData() {
		String maHDTT = txtMaHoaDon.getText().trim();

		Pattern p1 = Pattern.compile("^(HD)[0-9]{3}");
		if (!(maHDTT.length() > 0 && p1.matcher(maHDTT).find())) {
			showMessage("Lỗi mã hoá đơn", txtMaHoaDon);
			return false;
		}
		return true;
	}

	public void loadData() {
		// delete all
		deleteAllDataJtable();
		// Load data
		DAO_hoaDon = new DAOHoaDonThanhToan();
		ds = DAO_hoaDon.getAll();
		String ht;
		for (HoaDonThanhToan hd : ds.getList()) {
			if (hd.getHinhThucThanhToan().equals("1")) {
				ht = "Tiên mặt";
			} else
				ht = "Chuyển khoản";
			Object row[] = { hd.getMaHoaDon(), hd.getMaDatPhong(), hd.getNgayThanhToan(), ht,
					formatter.format(hd.getThanhTienPhong()) + " VNĐ", formatter.format(hd.getTongThanhToan()) + " VNĐ",
					hd.getGhiChu() };
			tableModel.addRow(row);
		}
	}

	public Double changeDigit(String st) {
		String tien = "";
		for (int i = 0; i < st.length(); i++) {
			if (Character.isDigit(st.charAt(i))) {
				tien += st.charAt(i);
			}
		}
		return Double.valueOf(tien);
		
	}
	public void testArr() {
		DanhSachHoaDonDichVuPhong dsdvp = new DanhSachHoaDonDichVuPhong();
		ArrayList<HoaDonDichVuPhong> arrdvp = new ArrayList<HoaDonDichVuPhong>();
		arrdvp = dsdvp.getList();
		for (int i = 0; i < arrdvp.size(); i++) {
			System.out.println(i);
			System.out.println(arrdvp.get(i).getThanhTienDichVu());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				TXTedit_true();
				btnXoaTrang.setEnabled(true);
				btnThem.setText("Huỷ");
				btnSua.setEnabled(false);
				btnLuu.setEnabled(true);
			} else {
				xoaTrang();
				TXTedit_false();
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				btnLuu.setEnabled(false);
				showMessage("", txtMess);
				loadData();
			}
		} else if (o.equals(btnSua)) {
			btnThem.setEnabled(false);
			if (btnSua.getText().equals("Sửa")) {
				try {
					int r = table.getSelectedRow();
					if (r != -1) {
						TXTedit_true();
						txtMaHoaDon.setEditable(false);
						btnSua.setText("Hoàn tất");
					} else {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn hoá đơn thanh toán muốn xoá!");
						btnThem.setEnabled(true);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			} else {
				SuaHoaDonThanhToan();
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				TXTedit_false();
				xoaTrang();
				showMessage("", txtMess);
			}
		} else if (o.equals(btnXoaTrang))
			xoaTrang();
		else if (o.equals(btnXoa)) {
			try {
				XoaHoaDonThanhToan();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnTim)) {
			TimHoaDonThanhToan();
		} else if (o.equals(btnLuu)) {
			themHoaDonThanhToan();
		} else if (o.equals(btnThoat))
			System.exit(0);

	}

	private void themHoaDonThanhToan() {
		// TODO Auto-generated method stub
		String maHDTT = txtMaHoaDon.getText();
		String maDP = cbMaDatPhong.getSelectedItem().toString();	
		double thanhTienPhong = changeDigit(txtThanhTienPhong.getText());
		double phiDV = changeDigit(txtTienDichVu.getText());
		double tongTT = thanhTienPhong + phiDV;

		String ghiChu = txtaGhiChu.getText();
		Date ngayTT = Date.valueOf(getNgayTT());
		Boolean hinhThucTT = true;
		if (radTienMat.isSelected())
			hinhThucTT = false;
		HoaDonThanhToan hd = new HoaDonThanhToan(maHDTT, maDP, ngayTT, hinhThucTT.toString(), thanhTienPhong, tongTT,
				ghiChu);
		try {
			if (validData()) {
				if (DAO_hoaDon.add(hd)) {
					ds.themHoaDonThanhToan(hd);
					String ht = "";
					if (hinhThucTT) {
						ht = "Chuyển khoản";
					} else
						ht = "Tiền mặt";
					String[] row = { maHDTT, maDP, ngayTT.toString(), ht, formatter.format(thanhTienPhong)+" VNĐ",
							formatter.format(tongTT)+" VNĐ", ghiChu };
					tableModel.addRow(row);
					xoaTrang();
					JOptionPane.showMessageDialog(null, "Thêm hoá đơn thành công!");
				} else {
					JOptionPane.showMessageDialog(null, "Trùng mã hoá đơn!");
					txtMaHoaDon.selectAll();
					txtMaHoaDon.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thêm hóa đơn không thành công!");
				txtMaHoaDon.selectAll();
				txtMaHoaDon.requestFocus();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu!");
			return;
		}
	}

	private void TimHoaDonThanhToan() {
		// TODO Auto-generated method stub
		int pos = ds.timHoaDonTheoMa(txtTim.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Tồn tại hoá đơn có mã số này!");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Không tồn tại hoá đơn có mã số này!");
	}

	private void SuaHoaDonThanhToan() {
		// TODO Auto-generated method stub
		String maHDTT = txtMaHoaDon.getText();
		String maDP = cbMaDatPhong.getSelectedItem().toString();
		double thanhTienPhong = changeDigit(txtThanhTienPhong.getText());
		double phiDV = changeDigit(txtTienDichVu.getText());
		double tongTT = thanhTienPhong + phiDV;
		String ghiChu = txtaGhiChu.getText();
		Date ngayTT = Date.valueOf(getNgayTT());
		String hinhThucTT = "";
		if (radChuyenKhoan.isSelected())
			hinhThucTT = radChuyenKhoan.getText();
		if (radTienMat.isSelected())
			hinhThucTT = radTienMat.getText();
		HoaDonThanhToan hd = new HoaDonThanhToan(maHDTT, maDP, ngayTT, hinhThucTT, thanhTienPhong, tongTT, ghiChu);

		if (validData()) {
			if (ds.suaHoaDon(hd)) {
				int index = ds.getList().indexOf(hd);
				tableModel.setValueAt(maDP, index, 1);
				tableModel.setValueAt(ngayTT, index, 2);
				tableModel.setValueAt(hinhThucTT, index, 3);
				tableModel.setValueAt(thanhTienPhong, index, 4);
				tableModel.setValueAt(tongTT, index, 5);
				tableModel.setValueAt(ghiChu, index, 6);
				DAO_hoaDon.updateHoaDonThanhToan(hd);
				showMessage("Cập nhật thành công", txtMess);
				JOptionPane.showMessageDialog(null, "Cập nhật hóa đơn thanh toán thành công!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Cập nhật hóa đơn không thành công!");
			txtMaHoaDon.selectAll();
			txtMaHoaDon.requestFocus();
		}
	}

	private void XoaHoaDonThanhToan() throws Exception {
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá hóa đơn này không?", "Chú ý!",
					JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaHoaDon(r);
				DAO_hoaDon.delete(table.getValueAt(r, 0).toString());
				tableModel.removeRow(r);
				JOptionPane.showMessageDialog(null, "Xoá hoá đơn thành công!");
				xoaTrang();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn hoá đơn muốn xoá!");
		}
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaHoaDon.setText("");
		txtTongThanhToan.setText("");
		txtaGhiChu.setText("");
		txtThanhTienPhong.setText("");
		dDate = Calendar.getInstance();
		yearTT.setSelectedIndex(5);
		dayTT.setSelectedItem(Integer.toString(dDate.get(Calendar.DATE)));
		monthTT.setSelectedIndex(dDate.get(Calendar.MONTH));
		txtTienDichVu.setText("");
		txtTim.setText("");
		txtMaHoaDon.requestFocus();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaHoaDon.setText(table.getValueAt(row, 0).toString());
		cbMaDatPhong.setSelectedItem(table.getValueAt(row, 1).toString());
		Date dateTT = Date.valueOf(table.getValueAt(row, 2).toString());
		dayTT.setSelectedItem(String.valueOf(dateTT.getDate()));
		monthTT.setSelectedIndex(dateTT.getMonth());
		yearTT.setSelectedItem(String.valueOf(dateTT.getYear() + 1900));
		if (tableModel.getValueAt(row, 3).toString().equalsIgnoreCase("Chuyển khoản")) {
			radChuyenKhoan.setSelected(true);
		} else {
			radTienMat.setSelected(true);
		}
		Double pdv = changeDigit(table.getValueAt(row, 5).toString())
				- changeDigit(table.getValueAt(row, 4).toString());
		txtTienDichVu.setText(pdv + "");
		txtThanhTienPhong.setText(table.getValueAt(row, 4).toString());
		txtTongThanhToan.setText(table.getValueAt(row, 5).toString());
		txtaGhiChu.setText(table.getValueAt(row, 6).toString());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == yearTT && event.getStateChange() == ItemEvent.SELECTED) {
			int year = Integer.parseInt((String) yearTT.getSelectedItem());
			dDate.set(Calendar.YEAR, year);
			monthTT.setSelectedIndex(0);
			dDate.set(Calendar.MONTH, 0);
			buildDaysList(dDate, dayTT, monthTT);
			dDate.set(Calendar.DATE, 1);
		} else if (event.getSource() == monthTT && event.getStateChange() == ItemEvent.SELECTED) {
			dDate.set(Calendar.MONTH, monthTT.getSelectedIndex());
			buildDaysList(dDate, dayTT, monthTT);
			dDate.set(Calendar.DATE, 1);
		} else if (event.getSource() == dayTT && event.getStateChange() == ItemEvent.SELECTED) {
			int day = Integer.parseInt((String) dayTT.getSelectedItem());
			dDate.set(Calendar.DATE, day);
		}
		if (event.getSource() == cbMaDatPhong) {

			for (PhieuDatPhong pdp : dsPhieuDatPhong.getList()) {
				if (cbMaDatPhong.getSelectedItem().equals(pdp.getMaDatPhong())) {
					Date ngayDen = pdp.getNgayDen();
					Date ngayDi = pdp.getNgayDi();
					Date ngayTT = Date.valueOf(getNgayTT());
					long soNgay = TimeUnit.MILLISECONDS.toDays((ngayTT.getTime() - ngayDen.getTime()));
					Phong p = dsPhong.getList().get(dsPhong.timPhongTheoMa(pdp.getMaPhong()));
					Double tienPhong = soNgay * p.getGiaPhong();
					txtThanhTienPhong.setText(formatter.format(tienPhong) + " VNĐ");
					p.setTinhTrang("Còn trống");

					String ghiChu = "Chi tiết dịch vụ:";
					double phiDichVu = 0;
					for (HoaDonDichVuPhong dvp : dsDVP) {
						if (pdp.getMaDatPhong().equals(dvp.getMaDatPhong())) {
							phiDichVu += dvp.getThanhTienDichVu();
							if (tenDichVu.contains(dvp.getMaDichVu())) {
								ghiChu += String.format("\n- %s \n\tSố lượng: %s \n\tThành tiền: %s",
										tenDichVu.get(tenDichVu.indexOf(dvp.getMaDichVu()) + 1), dvp.getSoLuong(),
										formatter.format(dvp.getThanhTienDichVu()));
							}
						}
					}
					if (phiDichVu == 0)
						ghiChu = "Không sử dụng dịch vụ";
					txtTienDichVu.setText(formatter.format(phiDichVu) + " VNĐ");
					if (ngayDi.equals(ngayTT)) {
						ghiChu += "\nThanh toán đúng hạn!";
					} else if (ngayDi.before(ngayTT))
						ghiChu += "\nTrả phòng sớm!";
					txtaGhiChu.setText(ghiChu);
					txtTongThanhToan.setText(formatter.format(phiDichVu + tienPhong) + "VNĐ");
					break;

				}
			}

		}
	}

}
