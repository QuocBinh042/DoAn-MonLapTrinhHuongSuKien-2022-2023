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
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.DAOKhachHang;
import DAO.DAOPhieuDatPhong;
import DAO.DAOPhong;
import DanhSach.DanhSachKhachHang;
import DanhSach.DanhSachPhieuDatPhong;
import DanhSach.DanhSachPhong;
import Entity.KhachHang;
import Entity.PhieuDatPhong;
import Entity.Phong;
import connectDB.ConnectDB;

public class FrmPhieuDatPhong extends JFrame implements ActionListener, MouseListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	private JTextField txtMaDatPhong, txtMaNV, txtSoNguoi, txtTim;
	private JTextArea txtaGhiChu;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnSua, btnThoat;
	private DefaultTableModel tableModel;
	private JTextField txtMess;
	private Calendar startDate = Calendar.getInstance();
	private Calendar endDate = Calendar.getInstance();
	private Calendar bookDate = Calendar.getInstance();
	private JComboBox startYear, startMonth, startDay;
	private JComboBox endYear, endMonth, endDay;
	private JComboBox bookYear, bookMonth, bookDay;
	private JComboBox<String> cbMaPhong, cbMaKhachHang;
	private DAOPhieuDatPhong DAO_datPhong;
	private DAOPhong DAO_phong;
	private DanhSachPhong dsPhong;
	private DAOKhachHang DAO_khachHang;
	private DanhSachKhachHang dsKhachHang;
	private DanhSachPhieuDatPhong dsPhieuDatPhong = new DanhSachPhieuDatPhong();
	private String maNhanVien;

	public FrmPhieuDatPhong() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createGUI();

	}

	public static void main(String[] args) {
		new FrmPhieuDatPhong().setVisible(true);
	}

	private void createGUI() {
		// TODO Auto-generated method stub
		setTitle("Quản lý phiếu đặt phòng");
		setSize(1350, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("QUẢN LÝ PHIẾU ĐẶT PHÒNG");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.red);
		pnlNorth.add(lblTieuDe);

		// CENTER
		Box b = Box.createVerticalBox();
		Box bb = Box.createHorizontalBox();
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, bTimMa, b10, b11, b12, b13, b14;
		JLabel lblMaDatPhong, lblMaPhong, lblMaKhachHang, lblMaNV, lblMaHoaDon, lblNgayDen, lblNgayDatPhong, lblNgayDi,
				lblSoNguoi, lblGhiChu, lblThanhTienPhong;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.setBorder(BorderFactory.createTitledBorder("Phiếu đặt phòng"));

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaDatPhong = new JLabel("Mã đặt phòng: "));
		b1.add(txtMaDatPhong = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b2.add(lblMaNV = new JLabel("Mã nhân viên: "));
		b2.add(txtMaNV = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b3.add(lblMaPhong = new JLabel("Mã phòng: "));
		b3.add(cbMaPhong = new JComboBox<>());
		DAO_phong = new DAOPhong();
		dsPhong = DAO_phong.getAll();
		for (Phong p : dsPhong.getList()) {
			if (p.getTinhTrang().equals("0"))
				cbMaPhong.addItem(p.getMaPhong());
		}

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblMaKhachHang = new JLabel("Mã khách hàng: "));
		b4.add(cbMaKhachHang = new JComboBox<>());
		DAO_khachHang = new DAOKhachHang();
		dsKhachHang = DAO_khachHang.getAll();
		for (KhachHang kh : dsKhachHang.getList()) {
			cbMaKhachHang.addItem(kh.getMaKHang());
		}

		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b7.add(lblNgayDatPhong = new JLabel("Ngày đặt phòng: "));
		bookYear = new JComboBox();
		buildYearsList(bookYear);
		bookYear.setSelectedIndex(5);
		bookMonth = new JComboBox();
		buildMonthsList(bookMonth);
		bookMonth.setSelectedIndex(bookDate.get(Calendar.MONTH));
		bookDay = new JComboBox();
		buildDaysList(bookDate, bookDay, bookMonth);
		bookDay.setSelectedItem(Integer.toString(bookDate.get(Calendar.DATE)));
		b7.add(bookDay);
		b7.add(bookMonth);
		b7.add(bookYear);

		b.add(b8 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b8.add(lblNgayDen = new JLabel("Ngày đến: "));
		startYear = new JComboBox();
		buildYearsList(startYear);
		startYear.setSelectedIndex(5);
		startMonth = new JComboBox();
		buildMonthsList(startMonth);
		startMonth.setSelectedIndex(startDate.get(Calendar.MONTH));
		startDay = new JComboBox();
		buildDaysList(startDate, startDay, startMonth);
		startDay.setSelectedItem(Integer.toString(startDate.get(Calendar.DATE)));
		b8.add(startDay);
		b8.add(startMonth);
		b8.add(startYear);

		b.add(b9 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b9.add(lblNgayDi = new JLabel("Ngày đi: "));
		endYear = new JComboBox();
		buildYearsList(endYear);
		endYear.setSelectedIndex(5);
		endMonth = new JComboBox();
		buildMonthsList(endMonth);
		endMonth.setSelectedIndex(endDate.get(Calendar.MONTH));
		endDay = new JComboBox();
		buildDaysList(endDate, endDay, endMonth);
		endDay.setSelectedItem(Integer.toString(endDate.get(Calendar.DATE)));
		b9.add(endDay);
		b9.add(endMonth);
		b9.add(endYear);

		b.add(b10 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b10.add(lblSoNguoi = new JLabel("Số người: "));
		b10.add(txtSoNguoi = new JTextField());

		b.add(b11 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b11.add(lblGhiChu = new JLabel("Ghi chú: "));
		b11.add(txtaGhiChu = new JTextArea(6, 6));
		txtaGhiChu.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		b.add(b12 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b12.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		b.add(b13 = Box.createHorizontalBox());
		b13.add(Box.createHorizontalStrut(90));
		b13.add(btnThem = new JButton("Thêm"));
		b13.add(Box.createHorizontalStrut(10));
		b13.add(btnSua = new JButton("Sửa"));
		b13.add(Box.createHorizontalStrut(10));
		b13.add(btnXoaTrang = new JButton("Xoá trắng"));

		b.add(Box.createVerticalStrut(20));
		b.add(b14 = Box.createVerticalBox());
		b14.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		b14.add(Box.createVerticalStrut(10));
		b14.add(bTimMa = Box.createHorizontalBox());
		bTimMa.add(new JLabel("Mã phiếu đặt phòng:  "));
		bTimMa.add(txtTim = new JTextField(10));
		bTimMa.add(btnTim = new JButton("Tìm"));
		b10.add(Box.createVerticalStrut(10));
		b.add(Box.createVerticalStrut(20));
		add(b, BorderLayout.WEST);
		lblMaDatPhong.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblMaNV.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblMaPhong.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblNgayDen.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblMaKhachHang.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblNgayDi.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblSoNguoi.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblGhiChu.setPreferredSize(lblNgayDatPhong.getPreferredSize());

		bb.setBorder(BorderFactory.createTitledBorder("Danh sách phiếu đặt phòng"));
		String[] headers = "Mã đặt phòng; Mã nhân viên; Mã phòng; Mã khách hàng; Ngày đặt phòng;Ngày đến;Ngày đi;Số người; Ghi chú"
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

		// Gọi hàm load data
		loadData();
		txtMaNV.setText(maNhanVien);
		// ĐĂNG KÝ SỰ KIỆN
		startYear.addItemListener(this);
		startMonth.addItemListener(this);
		startDay.addItemListener(this);
		endYear.addItemListener(this);
		endMonth.addItemListener(this);
		endDay.addItemListener(this);
		bookYear.addItemListener(this);
		bookMonth.addItemListener(this);
		bookDay.addItemListener(this);

		TXTedit_false();
		btnLuu.setEnabled(false);
		btnXoaTrang.setEnabled(false);
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnThoat.addActionListener(this);
		table.addMouseListener(this);
		cbMaPhong.addItemListener(this);
	}

	private void TXTedit_false() {
		txtMaDatPhong.setEditable(false);
		txtSoNguoi.setEditable(false);
		txtaGhiChu.setEditable(false);
		txtMaNV.setEditable(false);
		cbMaKhachHang.setEnabled(false);
		cbMaPhong.setEnabled(false);
		startYear.setEnabled(false);
		startMonth.setEnabled(false);
		startDay.setEnabled(false);

		endYear.setEnabled(false);
		endMonth.setEnabled(false);
		endDay.setEnabled(false);

		bookYear.setEnabled(false);
		bookMonth.setEnabled(false);
		bookDay.setEnabled(false);
	}

	private void TXTedit_true() {
		txtMaDatPhong.setEditable(true);
		txtSoNguoi.setEditable(true);
		txtaGhiChu.setEditable(true);
		cbMaPhong.setEnabled(true);

		startYear.setEnabled(true);
		startMonth.setEnabled(true);
		startDay.setEnabled(true);

		endYear.setEnabled(true);
		endMonth.setEnabled(true);
		endDay.setEnabled(true);

		bookYear.setEnabled(true);
		bookMonth.setEnabled(true);
		bookDay.setEnabled(true);
	}

	private void buildMonthsList(JComboBox monthsList) {
		monthsList.removeAllItems();
		for (int monthCount = 1; monthCount <= 12; monthCount++)
			monthsList.addItem(monthCount);
	}

	private void buildDaysList(Calendar dateIn, JComboBox daysList, JComboBox monthsList) {
		daysList.removeAllItems();
		dateIn.set(Calendar.MONTH, monthsList.getSelectedIndex());
		int lastDay = startDate.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int dayCount = 1; dayCount <= lastDay; dayCount++)
			daysList.addItem(Integer.toString(dayCount));
	}

	private void buildYearsList(JComboBox yearsList) {
		int currentYear = startDate.get(Calendar.YEAR);
		for (int yearCount = currentYear - 5; yearCount <= currentYear + 5; yearCount++)
			yearsList.addItem(Integer.toString(yearCount));
	}

	private String getBookDate() {
		return bookYear.getSelectedItem().toString() + "-" + bookMonth.getSelectedItem().toString() + "-"
				+ bookDay.getSelectedItem().toString();
	}

	private String getStartDate() {
		return startYear.getSelectedItem().toString() + "-" + startMonth.getSelectedItem().toString() + "-"
				+ startDay.getSelectedItem().toString();
	}

	private String getEndDate() {
		return endYear.getSelectedItem().toString() + "-" + endMonth.getSelectedItem().toString() + "-"
				+ endDay.getSelectedItem().toString();
	}

	public void deleteAllDataJtable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}
	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}

	public void loadData() {
		// delete all
		deleteAllDataJtable();
		// Load data
		DAO_datPhong = new DAOPhieuDatPhong();
		dsPhieuDatPhong = DAO_datPhong.getAll();
		for (PhieuDatPhong pdp : dsPhieuDatPhong.getList()) {
			Object row[] = { pdp.getMaDatPhong(), pdp.getMaNhanVien(), pdp.getMaPhong(), pdp.getMaKhachHang(),
					pdp.getNgayDatPhong(), pdp.getNgayDen(), pdp.getNgayDi(), pdp.getSoNguoi(), pdp.getGhiChu() };
			tableModel.addRow(row);
		}
		maNhanVien = (String) tableModel.getValueAt(0, 1);
		tableModel.removeRow(0);
	}

	private boolean validData() {
		String maDP = txtMaDatPhong.getText().trim();
		String soNguoi = txtSoNguoi.getText().trim();
		Pattern p1 = Pattern.compile("^(PDP)[0-9]{3}");
		if (!(maDP.length() > 0 && p1.matcher(maDP).find())) {
			showMessage("Lỗi mã đặt phòng!", txtMaDatPhong);
			return false;
		}

		Pattern p2 = Pattern.compile("^[0-9]+");
		if (!(soNguoi.length() > 0 && p2.matcher(soNguoi).find())) {
			showMessage("Lỗi số người!", txtSoNguoi);
			return false;
		}
		return true;
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
			}
		} else if (o.equals(btnSua)) {
			btnThem.setEnabled(false);
			if (btnSua.getText().equals("Sửa")) {
				try {
					int r = table.getSelectedRow();
					if (r != -1) {
						TXTedit_true();
						txtMaDatPhong.setEditable(false);
						btnSua.setText("Hoàn tất");
					} else {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu đặt phòng muốn xoá!");
						btnThem.setEnabled(true);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			} else {
				SuaPhieuDatPhong();
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
				XoaPhieuDatPhong();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnTim)) {
			TimPhieuDatPhong();
		} else if (o.equals(btnLuu)) {
			themPhieuDatPhong();
		} else if (o.equals(btnThoat))
			System.exit(0);
	}

	private void themPhieuDatPhong() {
		// TODO Auto-generated method stub
		String maDP = txtMaDatPhong.getText();
		String maNV = txtMaNV.getText();
		String maPhong = cbMaPhong.getSelectedItem().toString();
		String maKhachHang = cbMaKhachHang.getSelectedItem().toString();
		int soNguoi = Integer.parseInt(txtSoNguoi.getText());
		String ngayDen = getStartDate();
		String ngayDi = getEndDate();
		String ngayDatPhong = getBookDate();
		String ghiChu = txtaGhiChu.getText();
		PhieuDatPhong p = new PhieuDatPhong(maDP, maNV, maPhong, maKhachHang, Date.valueOf(ngayDatPhong),
				Date.valueOf(ngayDen), Date.valueOf(ngayDi), soNguoi, ghiChu);

		try {
			if (validData()) {
				if (DAO_datPhong.add(p)) {
					dsPhieuDatPhong.themPhieuDatPhong(p);
					String[] row = { maDP, maNV, maPhong, maKhachHang, ngayDatPhong, ngayDen, ngayDi,
							Integer.toString(soNguoi), ghiChu };
					tableModel.addRow(row);
					xoaTrang();
					JOptionPane.showMessageDialog(null, "Thêm thành công phiếu đặt phòng!");
				} else {
					JOptionPane.showMessageDialog(null, "Trùng mã phiếu đặt phòng!");
					txtMaDatPhong.selectAll();
					txtMaDatPhong.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thêm phiếu đặt phòng không thành công!");
				txtMaDatPhong.selectAll();
				txtMaDatPhong.requestFocus();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu!");
			return;
		}
	}

	private void TimPhieuDatPhong() {
		// TODO Auto-generated method stub
		int pos = dsPhieuDatPhong.timPhieuDatPhongTheoMa(txtTim.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Tồn tại phiếu đặt phòng có mã số này!");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Không tồn tại phiếu đặt phòng có mã số này!");
	}

	private void SuaPhieuDatPhong() {
		// TODO Auto-generated method stub
		String maDP = txtMaDatPhong.getText();
		String maNV = txtMaNV.getText();
		String maPhong = cbMaPhong.getSelectedItem().toString();
		String maKhachHang = cbMaKhachHang.getSelectedItem().toString();
		int soNguoi = Integer.parseInt(txtSoNguoi.getText());
		String ngayDen = getStartDate();
		String ngayDi = getEndDate();
		String ngayDatPhong = getBookDate();
		String ghiChu = txtaGhiChu.getText();

		PhieuDatPhong p = new PhieuDatPhong(maDP, maNV, maPhong, maKhachHang, Date.valueOf(ngayDatPhong),
				Date.valueOf(ngayDen), Date.valueOf(ngayDi), soNguoi, ghiChu);
		if (validData()) {
			if (DAO_datPhong.update(p)) {
				int index = dsPhieuDatPhong.getList().indexOf(p);
				tableModel.setValueAt(maNV, index, 1);
				tableModel.setValueAt(maPhong, index, 2);
				tableModel.setValueAt(maKhachHang, index, 3);
				tableModel.setValueAt(ngayDatPhong, index, 4);
				tableModel.setValueAt(ngayDen, index, 5);
				tableModel.setValueAt(ngayDi, index, 6);
				tableModel.setValueAt(soNguoi, index, 7);
				tableModel.setValueAt(ghiChu, index, 8);
				dsPhieuDatPhong.suaPhieuDatPhong(p);
				showMessage("Cập nhật thành công!", txtMess);
				JOptionPane.showMessageDialog(null, "Cập nhật phiếu đặt phòng thành công!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Cập nhật phiếu đặt phòng không thành công!");
			txtMaDatPhong.selectAll();
			txtMaDatPhong.requestFocus();
		}
	}

	private void XoaPhieuDatPhong() throws Exception {
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá phiếu đặt phòng này không?", "Chú ý!",
					JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				dsPhieuDatPhong.xoaPhieuDatPhong(r);
				DAO_datPhong.delete(table.getValueAt(r, 0).toString());
				tableModel.removeRow(r);
				JOptionPane.showMessageDialog(null, "Xoá phiếu đặt phòng thành công!");
				xoaTrang();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu đặt phòng muốn xoá!");
		}
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaDatPhong.setText("");
		txtMaNV.setText("");
		txtMaNV.setText(maNhanVien);
		txtSoNguoi.setText("");
		txtaGhiChu.setText("");
		txtTim.setText("");
		cbMaPhong.setSelectedIndex(0);
		cbMaKhachHang.setSelectedIndex(0);

		startDate = Calendar.getInstance();
		startYear.setSelectedIndex(5);
		startDay.setSelectedItem(Integer.toString(startDate.get(Calendar.DATE)));
		startMonth.setSelectedIndex(bookDate.get(Calendar.MONTH));
		endDate = Calendar.getInstance();
		endYear.setSelectedIndex(5);
		endDay.setSelectedItem(Integer.toString(endDate.get(Calendar.DATE)));
		endMonth.setSelectedIndex(endDate.get(Calendar.MONTH));
		bookDate = Calendar.getInstance();
		bookYear.setSelectedIndex(5);
		bookDay.setSelectedItem(Integer.toString(bookDate.get(Calendar.DATE)));
		bookMonth.setSelectedIndex(bookDate.get(Calendar.MONTH));

		txtMaDatPhong.setEnabled(true);
		txtMaDatPhong.requestFocus();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaDatPhong.setText(table.getValueAt(row, 0).toString());
		txtMaNV.setText(table.getValueAt(row, 1).toString());
		cbMaPhong.setSelectedItem(table.getValueAt(row, 2).toString());
		cbMaKhachHang.setSelectedItem(table.getValueAt(row, 3).toString());

		Date bDate = Date.valueOf(table.getValueAt(row, 4).toString());
		bookDay.setSelectedItem(String.valueOf(bDate.getDate()));
		bookMonth.setSelectedIndex(bDate.getMonth());
		bookYear.setSelectedItem(String.valueOf(bDate.getYear() + 1900));
		Date sDate = Date.valueOf(table.getValueAt(row, 5).toString());
		startDay.setSelectedItem(String.valueOf(sDate.getDate()));
		startMonth.setSelectedIndex(bDate.getMonth());
		startYear.setSelectedItem(String.valueOf(sDate.getYear() + 1900));
		Date eDate = Date.valueOf(table.getValueAt(row, 6).toString());
		endDay.setSelectedItem(String.valueOf(eDate.getDate()));
		endMonth.setSelectedIndex(bDate.getMonth());
		endYear.setSelectedItem(String.valueOf(eDate.getYear() + 1900));
		txtSoNguoi.setText(table.getValueAt(row, 7).toString());
		txtaGhiChu.setText(table.getValueAt(row, 8).toString());
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
		if (event.getSource() == startYear && event.getStateChange() == ItemEvent.SELECTED) {
			int year = Integer.parseInt((String) startYear.getSelectedItem());
			startDate.set(Calendar.YEAR, year);
			startMonth.setSelectedIndex(0);
			startDate.set(Calendar.MONTH, 0);
			buildDaysList(startDate, startDay, startMonth);
			startDate.set(Calendar.DATE, 1);
		} else if (event.getSource() == startMonth && event.getStateChange() == ItemEvent.SELECTED) {
			startDate.set(Calendar.MONTH, startMonth.getSelectedIndex());
			buildDaysList(startDate, startDay, startMonth);
			startDate.set(Calendar.DATE, 1);
		} else if (event.getSource() == startDay && event.getStateChange() == ItemEvent.SELECTED) {
			int day = Integer.parseInt((String) startDay.getSelectedItem());
			startDate.set(Calendar.DATE, day);
		} else if (event.getSource() == endYear && event.getStateChange() == ItemEvent.SELECTED) {
			int year = Integer.parseInt((String) endYear.getSelectedItem());
			endDate.set(Calendar.YEAR, year);
			endMonth.setSelectedIndex(0);
			endDate.set(Calendar.MONTH, 0);
			buildDaysList(endDate, endDay, endMonth);
			endDate.set(Calendar.DATE, 1);
		} else if (event.getSource() == endMonth && event.getStateChange() == ItemEvent.SELECTED) {
			endDate.set(Calendar.MONTH, endMonth.getSelectedIndex());
			buildDaysList(endDate, endDay, endMonth);
			endDate.set(Calendar.DATE, 1);
		} else if (event.getSource() == endDay && event.getStateChange() == ItemEvent.SELECTED) {
			int day = Integer.parseInt((String) endDay.getSelectedItem());
			endDate.set(Calendar.DATE, day);
		} else if (event.getSource() == bookYear && event.getStateChange() == ItemEvent.SELECTED) {
			int year = Integer.parseInt((String) endYear.getSelectedItem());
			bookDate.set(Calendar.YEAR, year);
			bookMonth.setSelectedIndex(0);
			bookDate.set(Calendar.MONTH, 0);
			buildDaysList(bookDate, bookDay, bookMonth);
			bookDate.set(Calendar.DATE, 1);
		} else if (event.getSource() == bookMonth && event.getStateChange() == ItemEvent.SELECTED) {
			bookDate.set(Calendar.MONTH, bookMonth.getSelectedIndex());
			buildDaysList(bookDate, bookDay, bookMonth);
			bookDate.set(Calendar.DATE, 1);
		} else if (event.getSource() == bookDay && event.getStateChange() == ItemEvent.SELECTED) {
			int day = Integer.parseInt((String) bookDay.getSelectedItem());
			bookDate.set(Calendar.DATE, day);
		}
	}

}
