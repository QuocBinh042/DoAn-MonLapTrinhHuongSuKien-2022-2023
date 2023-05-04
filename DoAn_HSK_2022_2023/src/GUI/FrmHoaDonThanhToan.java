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
import javax.swing.table.DefaultTableModel;

import DAO.DAOHoaDonThanhToan;
import DanhSach.DanhSachHoaDonThanhToan;
import Entity.HoaDonThanhToan;
import connectDB.ConnectDB;

public class FrmHoaDonThanhToan extends JFrame implements ActionListener, MouseListener, ItemListener {
	private static final long serialVersionUID = 1L;
	private DanhSachHoaDonThanhToan ds = new DanhSachHoaDonThanhToan();
	private JTable table;
	private JTextField txtMaHoaDon, txtTongThanhToan, txtTim, txtThanhTienPhong;
	private JTextArea txtaGhiChu;
	private JRadioButton radChuyenKhoan, radTienMat;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnSua, btnThoat;
	private DefaultTableModel tableModel;
	private JTextField txtMess;
	private Calendar dDate = Calendar.getInstance();
	private JComboBox yearTT, monthTT, dayTT, cbMaPhieuDatPhong, cbMaDichVu;
	private DAOHoaDonThanhToan DAO_hd;

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
		JLabel lblMaHoaDon, lblThanhTienPhong, lblNgayThanhToan, lblHinhThucThanhToan, lblTongThanhToan, lblGhiChu;

		b.setBorder(BorderFactory.createTitledBorder("Hoá đơn thanh toán"));
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMaHoaDon = new JLabel("Mã hoá đơn: "));
		b1.add(txtMaHoaDon = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblNgayThanhToan = new JLabel("Ngày thanh toán: "));
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
		b3.add(Box.createHorizontalStrut(40));
		ButtonGroup bg = new ButtonGroup();
		bg.add(radChuyenKhoan = new JRadioButton("Chuyển khoản"));
		bg.add(radTienMat = new JRadioButton("Tiền mặt"));
		b3.add(radTienMat);
		b3.add(Box.createHorizontalStrut(80));
		b3.add(radChuyenKhoan);

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblThanhTienPhong = new JLabel("Thành tiền phòng: "));
		b4.add(txtThanhTienPhong = new JTextField());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b5.add(lblTongThanhToan = new JLabel("Tổng thanh toán: "));
		b5.add(txtTongThanhToan = new JTextField());

		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b6.add(lblGhiChu = new JLabel("Ghi chú: "));
		b6.add(txtaGhiChu = new JTextArea(6, 6));
		txtaGhiChu.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b7.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		b.add(b8 = Box.createHorizontalBox());
		b8.add(Box.createHorizontalStrut(130));
		b8.add(btnThem = new JButton("Thêm"));
		b8.add(Box.createHorizontalStrut(10));
		b8.add(btnSua = new JButton("Sửa"));
		b8.add(Box.createHorizontalStrut(10));
		b8.add(btnXoaTrang = new JButton("Xoá trắng"));

		b.add(Box.createVerticalStrut(100));
		b.add(b9 = Box.createVerticalBox());

		b9.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		b9.add(Box.createVerticalStrut(10));
		b9.add(bTimMa = Box.createHorizontalBox());
		bTimMa.add(new JLabel("Mã hoá đơn thanh toán:  "));
		bTimMa.add(txtTim = new JTextField(10));
		bTimMa.add(btnTim = new JButton("Tìm"));
		b9.add(Box.createVerticalStrut(10));
		b.add(Box.createVerticalStrut(70));

		lblMaHoaDon.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblThanhTienPhong.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblNgayThanhToan.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblTongThanhToan.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblGhiChu.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());

		add(b, BorderLayout.WEST);

		bb.setBorder(BorderFactory.createTitledBorder("Danh sách hoá đơn thanh toán"));
		String[] headers = "Mã hoá đơn; Ngày thanh toán; Hình thức thanh toán; Thành tiền phòng; Tổng thanh toán; Ghi chú"
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
	}

	private void TXTedit_false() {
		txtMaHoaDon.setEditable(false);
		txtThanhTienPhong.setEditable(false);
		txtTongThanhToan.setEditable(false);
		radChuyenKhoan.setEnabled(false);
		radTienMat.setEnabled(false);
		dayTT.setEnabled(false);
		monthTT.setEnabled(false);
		yearTT.setEnabled(false);
		txtaGhiChu.setEditable(false);
	}

	private void TXTedit_true() {
		txtMaHoaDon.setEditable(true);
		txtThanhTienPhong.setEditable(true);
		txtTongThanhToan.setEditable(true);
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
		String thanhTienPhong = txtThanhTienPhong.getText().trim();

		Pattern p1 = Pattern.compile("^(HD)[0-9]{3}");
		if (!(maHDTT.length() > 0 && p1.matcher(maHDTT).find())) {
			showMessage("Lỗi mã hoá đơn", txtMaHoaDon);
			return false;
		}

		Pattern p2 = Pattern.compile("^[0-9]+");
		if (!(thanhTienPhong.length() > 0 && p2.matcher(thanhTienPhong).find())) {
			showMessage("Lỗi thành tiền phòng", txtThanhTienPhong);
			return false;
		}

		return true;
	}

	public void loadData() {
		// delete all
		deleteAllDataJtable();
		// Load data
		DAO_hd = new DAOHoaDonThanhToan();
		for (HoaDonThanhToan hd : DAO_hd.getAll()) {
			Object row[] = { hd.getMaHoaDon(), hd.getNgayThanhToan(), hd.getHinhThucThanhToan(), hd.getThanhTienPhong(),
					hd.getTongThanhToan(), hd.getGhiChu() };
			tableModel.addRow(row);
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
		double thanhTienPhong = Double.parseDouble(txtThanhTienPhong.getText());
		double tongTT = Double.parseDouble(txtTongThanhToan.getText());
		String ghiChu = txtaGhiChu.getText();
		Date ngayTT = Date.valueOf(getNgayTT());
		String hinhThucTT = "";
		if (radChuyenKhoan.isSelected())
			hinhThucTT = radChuyenKhoan.getText();
		if (radTienMat.isSelected())
			hinhThucTT = radTienMat.getText();
		HoaDonThanhToan hd = new HoaDonThanhToan(maHDTT, ngayTT, hinhThucTT, thanhTienPhong, tongTT, ghiChu);
		try {
			if (validData()) {
				if (ds.themHoaDonThanhToan(hd)) {
					String[] row = { maHDTT, ngayTT.toString(), hinhThucTT, String.valueOf(thanhTienPhong),
							String.valueOf(tongTT), ghiChu };
					tableModel.addRow(row);
					DAO_hd.add(hd);
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
		double thanhTienPhong = Double.parseDouble(txtThanhTienPhong.getText());
		double tongTT = Double.parseDouble(txtTongThanhToan.getText());
		String ghiChu = txtaGhiChu.getText();
		Date ngayTT = Date.valueOf(getNgayTT());
		String hinhThucTT = "";
		if (radChuyenKhoan.isSelected())
			hinhThucTT = radChuyenKhoan.getText();
		if (radTienMat.isSelected())
			hinhThucTT = radTienMat.getText();

		HoaDonThanhToan hd = new HoaDonThanhToan(maHDTT, ngayTT, hinhThucTT, thanhTienPhong, tongTT, ghiChu);
		if (validData()) {
			if (ds.suaHoaDon(hd)) {
				int index = ds.getList().indexOf(hd);
				tableModel.setValueAt(ngayTT, index, 1);
				tableModel.setValueAt(hinhThucTT, index, 2);
				tableModel.setValueAt(thanhTienPhong, index, 3);
				tableModel.setValueAt(tongTT, index, 4);
				tableModel.setValueAt(ghiChu, index, 5);
				DAO_hd.updateHoaDonThanhToan(hd);
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
				DAO_hd.delete(table.getValueAt(r, 0).toString());
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
		txtTim.setText("");
		txtMaHoaDon.requestFocus();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaHoaDon.setText(table.getValueAt(row, 0).toString());
		if (tableModel.getValueAt(row, 2).toString().equalsIgnoreCase("Chuyển khoản")) {
			radChuyenKhoan.setSelected(true);
		} else {
			radTienMat.setSelected(true);
		}
		txtThanhTienPhong.setText(table.getValueAt(row, 3).toString());
		txtTongThanhToan.setText(table.getValueAt(row, 4).toString());
		txtaGhiChu.setText(table.getValueAt(row, 5).toString());
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
	}
	
	
}
s