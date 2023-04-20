package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import DanhSach.DanhSachHoaDonThanhToan;
import Entity.HoaDonThanhToan;

public class FrmHoaDonThanhToan extends JFrame implements ActionListener, MouseListener {
	private DanhSachHoaDonThanhToan ds = new DanhSachHoaDonThanhToan();
	private JTable table;
	private JTextField txtMaHoaDon, txtNgayThanhToan, txtHinhThucThanhToan, txtTongThanhToan, txtTim;
	private JTextArea txtaGhiChu;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnSua, btnThoat;
	private DefaultTableModel tableModel;
	private JTextField txtMess;

	public FrmHoaDonThanhToan() {
		setTitle("Quản lý hoá đơn thanh toán");
		setSize(1000, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createGUI();

	}

	public static void main(String[] args) {
		new FrmHoaDonThanhToan().setVisible(true);
	}

	private void createGUI() {
		// TODO Auto-generated method stub
		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("QUẢN LÝ HOÁ ĐƠN THANH TOÁN");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.blue);
		pnlNorth.add(lblTieuDe);

		// CENTER
		Box b = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6;
		JLabel lblMaHoaDon, lblNgayThanhToan, lblHinhThucThanhToan, lblTongThanhToan, lblGhiChu;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMaHoaDon = new JLabel("Mã hoá đơn: "));
		b1.add(txtMaHoaDon = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblNgayThanhToan = new JLabel("Ngày thanh toán: "));
		b2.add(txtNgayThanhToan = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblHinhThucThanhToan = new JLabel("Hình thức thanh toán: "));
		b3.add(txtHinhThucThanhToan = new JTextField());

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblTongThanhToan = new JLabel("Tổng thanh toán: "));
		b4.add(txtTongThanhToan = new JTextField());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b5.add(lblGhiChu = new JLabel("Ghi chú: "));
		b5.add(txtaGhiChu = new JTextArea(6, 6));
		txtaGhiChu.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b6.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));
		lblMaHoaDon.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblNgayThanhToan.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblTongThanhToan.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		lblGhiChu.setPreferredSize(lblHinhThucThanhToan.getPreferredSize());
		add(b, BorderLayout.CENTER);

		b.add(b5 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(10));
		String[] headers = "Mã hoá đơn;Ngày thanh toán;Hình thức thanh toán;Tổng thanh toán;Ghi chú"
				.split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(20);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		b5.add(scroll);

		// SOUTH
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(split, BorderLayout.SOUTH);
		JPanel pnlLeft = new JPanel(), pnlRight = new JPanel();
		split.add(pnlLeft);
		split.add(pnlRight);
		add(b, BorderLayout.CENTER);
		pnlLeft.add(new JLabel("Nhập mã số cần tìm: "));
		pnlLeft.add(txtTim = new JTextField(10));
		pnlLeft.add(btnTim = new JButton("Tìm"));
		pnlRight.add(btnThem = new JButton("Thêm"));
		pnlRight.add(btnSua = new JButton("Sửa"));
		pnlRight.add(btnXoaTrang = new JButton("Xoá trắng"));
		pnlRight.add(btnXoa = new JButton("Xoá"));
		pnlRight.add(btnLuu = new JButton("Lưu"));

		// ĐĂNG KÝ SỰ KIỆN
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		table.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themHoaDonThanhToan();
		} else if (o.equals(btnSua)) {
			SuaHoaDonThanhToan();
		} else if (o.equals(btnXoaTrang))
			xoaTrang();
		else if (o.equals(btnXoa)) {
			try {
				XoaHoaDonThanhToan();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnTim)) {
			TimHoaDonThanhToan();
		} else if (o.equals(btnLuu)) {
			Luu();
		}
	}

	private void Luu() {
		// TODO Auto-generated method stub
		try {
			JOptionPane.showMessageDialog(this, "Lưu thành công");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void SuaHoaDonThanhToan() {
		// TODO Auto-generated method stub
		String maHDTT = txtMaHoaDon.getText();
		String hinhThucTT = txtHinhThucThanhToan.getText();
		String ngayTT = txtNgayThanhToan.getText();
		double tongTT = Double.parseDouble(txtTongThanhToan.getText());
		String ghiChu = txtaGhiChu.getText();
		HoaDonThanhToan p = new HoaDonThanhToan(maHDTT, ngayTT, hinhThucTT, tongTT, ghiChu);
		if (ds.suaHoaDon(p)) {
			int index = ds.getList().indexOf(p);
			tableModel.setValueAt(ngayTT, index, 1);
			tableModel.setValueAt(hinhThucTT, index, 2);
			tableModel.setValueAt(tongTT, index, 3);
			tableModel.setValueAt(ghiChu, index, 4);
			JOptionPane.showMessageDialog(null, "Sửa thành công");
		}
	}

	private void TimHoaDonThanhToan() {
		// TODO Auto-generated method stub
		int pos = ds.timHoaDonTheoMa(txtTim.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Tồn tại hoá đơn có mã số này");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Không tồn tại hoá đơn có mã số này");
	}

	private void XoaHoaDonThanhToan() throws Exception {
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá không", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaHoaDon(r);
				tableModel.removeRow(r);
				JOptionPane.showMessageDialog(null, "Xoá hoá đơn thành công!");
				xoaTrang();

			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn hoá đơn muốn xoá!");
		}
	}

	private void themHoaDonThanhToan() {
		// TODO Auto-generated method stub
		try {
			String maHDTT = txtMaHoaDon.getText();
			String hinhThucTT = txtHinhThucThanhToan.getText();
			String ngayTT = txtNgayThanhToan.getText();
			double tongTT = Double.parseDouble(txtTongThanhToan.getText());
			String ghiChu = txtaGhiChu.getText();
			HoaDonThanhToan hd = new HoaDonThanhToan(maHDTT, ngayTT, hinhThucTT, tongTT, ghiChu);

			kiemTraDauVao(hd);
			if (ds.themHoaDonThanhToan(hd)) {
				String[] row = { maHDTT, ngayTT, hinhThucTT, String.valueOf(tongTT), ghiChu };
				tableModel.addRow(row);
				xoaTrang();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã hoá đơn");
				txtMaHoaDon.selectAll();
				txtMaHoaDon.requestFocus();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			return;
		}
	}

	private void kiemTraDauVao(HoaDonThanhToan hd) {
		// TODO Auto-generated method stub
//		Pattern p = Pattern.compile("^NV|nv[0-9]{8}");
//		if (p.matcher(hd.getMaHoaDon()).find()) {
//			txtMess.setText("Mã Nhân viên Hợp lệ");
//		}
//		else
//			txtMess.setText("Mã nhân viên không hợp lệ");
//		
//		Pattern p2 = Pattern.compile("^[A-Za-z0-9 ]+$");
//		if (p2.matcher(hd.getHinhThucThanhToan()).find()) {
//			txtMess.setText("Tên nhân viên Hợp lệ");
//		}
//		else 
//			txtMess.setText("Tên nhân viên không hợp lệ");
//		

//		Pattern p3 = Pattern.compile("^[A-Za-z][A-Za-z0-9\\.\\_]+@iuh|google|Yahoo");
//		if (p3.matcher(hd.getGhiChu()).find()) {
//			txtMess.setText("Email Hợp lệ");
//		}
//		else 
//			txtMess.setText("Email không hợp lệ");

//		
//		
//		Pattern p = Pattern.compile("^HD[0-9]{8}$");
//		if (!(p.matcher(hd.getHinhThucThanhToan()).find())) {
//			txtMess.setText("Lỗi");
//			JOptionPane.showMessageDialog(this,"Lỗi" );
//		}

//		Pattern p = Pattern.compile("[A-Za-z0-9 ]+");
//		if (!(p.matcher(hd.getMaHoaDon()).find())) {
//			txtMess.setText("Lỗi");
//		}

		Pattern p = Pattern.compile("\\.");
		if (!(p.matcher(hd.getMaHoaDon()).find())) {
			txtMess.setText("Lỗi");
		} else
			txtMess.setText("Không Lỗi");

	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaHoaDon.setText("");
		txtHinhThucThanhToan.setText("");
		txtTongThanhToan.setText("");
		txtNgayThanhToan.setText("");
		txtaGhiChu.setText("");
		txtTim.setText("");
		txtMaHoaDon.setEnabled(true);
		txtMaHoaDon.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaHoaDon.setEnabled(false);
		txtMaHoaDon.setText(table.getValueAt(row, 0).toString());
		txtNgayThanhToan.setText(table.getValueAt(row, 1).toString());
		txtHinhThucThanhToan.setText(table.getValueAt(row, 2).toString());
		txtTongThanhToan.setText(table.getValueAt(row, 3).toString());
		txtaGhiChu.setText(table.getValueAt(row, 4).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
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
}
