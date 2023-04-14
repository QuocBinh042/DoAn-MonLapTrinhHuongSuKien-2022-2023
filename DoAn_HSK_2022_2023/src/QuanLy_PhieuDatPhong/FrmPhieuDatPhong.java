package QuanLy_PhieuDatPhong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

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
import javax.swing.table.DefaultTableModel;

public class FrmPhieuDatPhong extends JFrame implements ActionListener, MouseListener {

	private DanhSachPhieuDatPhong ds = new DanhSachPhieuDatPhong();
	private JTable table;
	private JTextField txtMaDatPhong, txtNgayDatPhong, txtNgayDen, txtNgayDi, txtSoNguoi, txtGiaTien, txtTim;
	private JTextArea txtaGhiChu;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnSua;
	private DefaultTableModel tableModel;
	private DatabasePhieuDatPhong databasee = new DatabasePhieuDatPhong();
	private JTextField txtMess;
	public FrmPhieuDatPhong() {
		setTitle("Quản lý phiếu đặt phòng");
		setSize(1000, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		createGUI();
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FrmPhieuDatPhong().setVisible(true);
	}

	private void createGUI() {
		// TODO Auto-generated method stub

		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("QUẢN LÝ PHIẾU ĐẶT PHÒNG");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.blue);
		pnlNorth.add(lblTieuDe);

		// CENTER
		Box b = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6, b7;
		JLabel lblMaDatPhong, lblNgayDen, lblNgayDatPhong, lblNgayDi, lblSoNguoi, lblGiaTien, lblGhiChu;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMaDatPhong = new JLabel("Mã đặt phòng: "));
		b1.add(txtMaDatPhong = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblSoNguoi = new JLabel("Số người: "));
		b2.add(txtSoNguoi = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblNgayDen = new JLabel("Ngày đến: "));
		b3.add(txtNgayDen = new JTextField());
		b3.add(Box.createHorizontalStrut(10));
		b3.add(lblNgayDi = new JLabel("Ngày đi: "));
		b3.add(Box.createHorizontalStrut(2));
		b3.add(txtNgayDi = new JTextField());

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblGiaTien = new JLabel("Giá tiền: "));
		b4.add(txtGiaTien = new JTextField());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b5.add(lblNgayDatPhong = new JLabel("Ngày đặt phòng: "));
		b5.add(txtNgayDatPhong = new JTextField());

		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b6.add(lblGhiChu = new JLabel("Ghi chú: "));
		b6.add(txtaGhiChu = new JTextArea(6,6));
		txtaGhiChu.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b7.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));
		lblMaDatPhong.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblNgayDen.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblSoNguoi.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblGiaTien.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		lblGhiChu.setPreferredSize(lblNgayDatPhong.getPreferredSize());
		add(b, BorderLayout.CENTER);

		b.add(b5 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(10));
		String[] headers = "Mã đặt phòng;Số người;Ngày đến;Ngày đi;Giá tiền;Ngày đặt phòng;Ghi chú"
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

	public void loadData() {
		try {
			ds = databasee.read_PhieuDatPhong("QuanLy_PhieuDatPhong.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ds == null) {
			ds = new DanhSachPhieuDatPhong();
		} else {
			for (PhieuDatPhong pdp : ds.getList()) {
				String[] row = { pdp.getMaPhong(), pdp.getSoNguoi() + "", pdp.getNgayDen(), pdp.getNgayDi(),
						pdp.getGiaTien() + "", pdp.getNgayDatPhong(), pdp.getGhiChu() };
				tableModel.addRow(row);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themPhieuDatPhong();
		} else if (o.equals(btnSua)) {
			SuaPhieuDatPhong();
		} else if (o.equals(btnXoaTrang))
			xoaTrang();
		else if (o.equals(btnXoa)) {
			try {
				XoaPhieuDatPhong();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnTim)) {
			TimPhieuDatPhong();
		} else if (o.equals(btnLuu)) {
			Luu();
		}
	}

	private void Luu() {
		// TODO Auto-generated method stub
		try {
			JOptionPane.showMessageDialog(this, "Lưu thành công");
			databasee.write_PhieuDatPhong("QuanLy_PhieuDatPhong.txt", ds);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void SuaPhieuDatPhong() {
		// TODO Auto-generated method stub
		String maDP = txtMaDatPhong.getText();
		int soNguoi = Integer.parseInt(txtSoNguoi.getText());
		String ngayDen = txtNgayDen.getText();
		String ngayDi = txtNgayDi.getText();
		double giaTien = Double.parseDouble(txtGiaTien.getText());
		String ngayDatPhong = txtNgayDatPhong.getText();
		String ghiChu = txtaGhiChu.getText();
		PhieuDatPhong p = new PhieuDatPhong(maDP, soNguoi, ngayDen, ngayDi, giaTien, ngayDatPhong, ghiChu);
		if (ds.suaPhieuDatPhong(p)) {
			int index = ds.getList().indexOf(p);
			tableModel.setValueAt(soNguoi, index, 1);
			tableModel.setValueAt(ngayDen, index, 2);
			tableModel.setValueAt(ngayDi, index, 3);
			tableModel.setValueAt(giaTien, index, 4);
			tableModel.setValueAt(ngayDatPhong, index, 5);
			tableModel.setValueAt(ghiChu, index, 6);
			JOptionPane.showMessageDialog(null, "Sửa thành công");
		}
	}

	private void TimPhieuDatPhong() {
		// TODO Auto-generated method stub
		int pos = ds.timPhieuDatPhongTheoMa(txtTim.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Tồn tại phiếu đặt phòng có mã số này");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Không tồn tại phiếu đặt phòng có mã số này");
	}

	private void XoaPhieuDatPhong() throws Exception {
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá không", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaPhieuDatPhong(r);
				tableModel.removeRow(r);
				JOptionPane.showMessageDialog(null, "Xoá thành công!");
				xoaTrang();
				databasee.write_PhieuDatPhong("QuanLy_PhieuDatPhong.txt", ds);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu đặt phòng muốn xoá!");
		}
	}

	private void themPhieuDatPhong() {
		// TODO Auto-generated method stub
		try {
			String maDP = txtMaDatPhong.getText();
			int soNguoi = Integer.parseInt(txtSoNguoi.getText());
			String ngayDen = txtNgayDen.getText();
			String ngayDi = txtNgayDi.getText();
			double giaTien = Double.parseDouble(txtGiaTien.getText());
			String ghiChu = txtaGhiChu.getText();
			String ngayDatPhong = txtNgayDatPhong.getText();
			PhieuDatPhong p = new PhieuDatPhong(maDP, soNguoi, ngayDen, ngayDi, giaTien, ngayDatPhong, ghiChu);
			if (ds.themPhieuDatPhong(p)) {
				String[] row = { maDP, String.valueOf(soNguoi), ngayDen, ngayDi, String.valueOf(giaTien), ngayDatPhong,
						ghiChu };
				tableModel.addRow(row);
				xoaTrang();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã đặt phòng");
				txtMaDatPhong.selectAll();
				txtMaDatPhong.requestFocus();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			return;
		}
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaDatPhong.setText("");
		txtSoNguoi.setText("");
		txtNgayDen.setText("");
		txtNgayDi.setText("");
		txtTim.setText("");
		txtGiaTien.setText("");
		txtNgayDatPhong.setText("");
		txtaGhiChu.setText("");
		txtMaDatPhong.setEnabled(true);
		txtMaDatPhong.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaDatPhong.setEnabled(false);
		txtMaDatPhong.setText(table.getValueAt(row, 0).toString());
		txtSoNguoi.setText(table.getValueAt(row, 1).toString());
		txtNgayDen.setText(table.getValueAt(row, 2).toString());
		txtNgayDi.setText(table.getValueAt(row, 3).toString());
		txtGiaTien.setText(table.getValueAt(row, 4).toString());
		txtNgayDatPhong.setText(table.getValueAt(row, 5).toString());
		txtaGhiChu.setText(table.getValueAt(row, 6).toString());
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
