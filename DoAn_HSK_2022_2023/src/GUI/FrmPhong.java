package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DanhSach.DanhSachPhong;
import Entity.Phong;
import DAO.DAOPhong;
import connectDB.ConnectDB;

public class FrmPhong extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private DanhSachPhong dsPhong = new DanhSachPhong();
	private JTable table;
	private JTextField txtMaPhong, txtTen, txtLoai, txtGiaPhong, txtMoTa, txtTimMa, txtTimTen, txtMess;
	private JRadioButton radTrong, radDaDat;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTimMa, btnTimTen, btnThoat, btnSua;
	private DefaultTableModel tableModel;
	private DAOPhong DAO_Phong;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");

	public FrmPhong() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createGUI();
	}

	public static void main(String[] args) {
		new FrmPhong().setVisible(true);
	}

	public void createGUI() {
		setTitle("Quản lý thông tin phòng");
		setSize(1350, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("THÔNG TIN PHÒNG");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.red);
		pnlNorth.add(lblTieuDe);

		// CENTER
		Box b = Box.createVerticalBox(), bb = Box.createVerticalBox();
		add(b, BorderLayout.WEST);
		Box b1, b2, b3, b4, b5, b6, b7, bTimMa, bTimTen, b8, b9;
		JLabel lblMaPhong, lblTen, lblLoai, lblGiaPhong, lblMoTa, lblTinhTrang;
		b.setBorder(BorderFactory.createTitledBorder("Phòng"));
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaPhong = new JLabel("Mã phòng: "));
		b1.add(txtMaPhong = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b2.add(lblTen = new JLabel("Tên phòng: "));
		b2.add(txtTen = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b3.add(lblLoai = new JLabel("Loại phòng: "));
		b3.add(txtLoai = new JTextField());

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblGiaPhong = new JLabel("Giá phòng: "));
		b4.add(txtGiaPhong = new JTextField());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b5.add(lblMoTa = new JLabel("Mô tả: "));
		
		b5.add(txtMoTa = new JTextField());

		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b6.add(lblTinhTrang = new JLabel("Tình trạng: "));
		b6.add(Box.createHorizontalStrut(40));
		ButtonGroup bg = new ButtonGroup();
		bg.add(radTrong = new JRadioButton("Còn trống"));
		bg.add(radDaDat = new JRadioButton("Đã đặt"));
		b6.add(radTrong);
		b6.add(Box.createHorizontalStrut(80));
		b6.add(radDaDat);

		lblMaPhong.setPreferredSize(lblLoai.getPreferredSize());
		lblTen.setPreferredSize(lblLoai.getPreferredSize());
		lblMoTa.setPreferredSize(lblLoai.getPreferredSize());
		lblGiaPhong.setPreferredSize(lblLoai.getPreferredSize());

		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));
		b7.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		b.add(b8 = Box.createHorizontalBox());
		b8.add(Box.createHorizontalStrut(70));
		b8.add(btnThem = new JButton("Thêm"));
		b8.add(Box.createHorizontalStrut(10));
		b8.add(btnSua = new JButton("Sửa"));
		b8.add(Box.createHorizontalStrut(10));
		b8.add(btnXoaTrang = new JButton("Xoá trắng"));

		b.add(Box.createVerticalStrut(50));
		b.add(b9 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(50));
		b9.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		b9.add(Box.createVerticalStrut(10));
		b9.add(bTimMa = Box.createHorizontalBox());
		bTimMa.add(new JLabel("Mã phòng:  "));
		bTimMa.add(txtTimMa = new JTextField(10));
		bTimMa.add(btnTimMa = new JButton("Tìm"));
		b9.add(Box.createVerticalStrut(20));
		b9.add(bTimTen = Box.createHorizontalBox());
		bTimTen.add(new JLabel("Tên phòng: "));
		bTimTen.add(txtTimTen = new JTextField(10));
		bTimTen.add(btnTimTen = new JButton("Tìm"));
		b9.add(Box.createVerticalStrut(10));
		b.add(Box.createVerticalStrut(200));

		bb.setBorder(BorderFactory.createTitledBorder("Danh sách phòng"));
		String[] headers = "Mã phòng;Tên phòng;Loại phòng; Giá phòng; Mô tả;Tình trạng".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
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
		
		// Đăng ký sự kiện
		TXTedit_false();
		btnLuu.setEnabled(false);
		btnXoaTrang.setEnabled(false);
		btnTimMa.addActionListener(this);
		btnTimTen.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
		table.addMouseListener(this);
	}

	private void TXTedit_false() {
		txtMaPhong.setEditable(false);
		radDaDat.setEnabled(false);
		radTrong.setEnabled(false);
		txtTen.setEditable(false);
		txtLoai.setEditable(false);
		txtMoTa.setEditable(false);
		txtGiaPhong.setEditable(false);
	}

	private void TXTedit_true() {
		txtMaPhong.setEditable(true);
		txtTen.setEditable(true);
		txtLoai.setEditable(true);
		radDaDat.setEnabled(true);
		radTrong.setEnabled(true);
		txtMoTa.setEditable(true);
		txtGiaPhong.setEditable(true);
	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}

	public void loadData() {
		// delete all
		deleteAllDataJtable();
		// Load data
		DAO_Phong = new DAOPhong();
		dsPhong = DAO_Phong.getAll();
		dsPhong.xoaPhong(0);
		String tinhTrang = "";
		for (Phong phong : dsPhong.getList()) {
			if (phong.getTinhTrang().equals("1")) {
				tinhTrang = "Đã đặt";
			} else
				tinhTrang = "Còn trống";
			Object row[] = { phong.getMaPhong(), phong.getTenPhong(), phong.getLoaiPhong(),
					formatter.format(phong.getGiaPhong()) + "VNĐ", phong.getMoTa(), tinhTrang };
			tableModel.addRow(row);
		}

	}

	private void deleteAllDataJtable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}
	}

	private boolean validData() {
		String maPhong = txtMaPhong.getText().trim();
		String loaiPhong = txtLoai.getText().trim();
		String tenPhong = txtTen.getText().trim();
		String giaPhong = txtGiaPhong.getText().trim();
		String moTa = txtMoTa.getText().trim();

		Pattern p = Pattern.compile("^(P)[0-9]{3}");
		if (!(maPhong.length() > 0 && p.matcher(maPhong).find())) {
			showMessage("Lỗi mã phòng!", txtMaPhong);
			return false;
		}
		Pattern p1 = Pattern.compile("[a-zA-Z0-9]+");
		if (!(tenPhong.length() > 0 && p1.matcher(tenPhong).find())) {
			showMessage("Tên phòng không tồn tại!", txtTen);
			return false;
		}
		Pattern p2 = Pattern.compile("[a-zA-Z]$");
		if (!(loaiPhong.length() > 0 && p2.matcher(loaiPhong).find())) {
			showMessage("Loại phòng này không tồn tại!", txtLoai);
			return false;
		}
		Pattern p3 = Pattern.compile("[0-9]");
		if (!(giaPhong.length() > 0 && p3.matcher(giaPhong).find())) {
			showMessage("Giá phòng này không tồn tại!", txtGiaPhong);
			return false;
		}
		Pattern p4 = Pattern.compile("[a-zA-Z0-9\\,]+");
		if (!(moTa.length() > 0 && p4.matcher(moTa).find())) {
			showMessage("Mô tả này không tồn tại!", txtMoTa);
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
						txtMaPhong.setEditable(false);
						btnSua.setText("Hoàn tất");
					} else {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng muốn xoá!");
						btnThem.setEnabled(true);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			} else {
				CapNhatThongTinPhong();
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
				xoaPhong();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnTimMa)) {
			TimPhongTheoMa();
		} else if (o.equals(btnTimTen)) {
			TimPhongTheoTen();
		} else if (o.equals(btnLuu)) {
			themPhong();
		} else if (o.equals(btnThoat))
			System.exit(0);
	}

	private void themPhong() {
		String maPhong = txtMaPhong.getText();
		String tenPhong = txtTen.getText();
		String loaiPhong = txtLoai.getText();
		double giaPhong = Double.parseDouble(txtGiaPhong.getText());
		String moTa = txtMoTa.getText();
		Boolean tinhTrang = true;
		if (radTrong.isSelected())
			tinhTrang = false;
		Phong ph = new Phong(maPhong, tenPhong, loaiPhong, giaPhong, moTa, tinhTrang.toString());
		try {
			if (validData()) {
				if (DAO_Phong.add(ph)) {
					dsPhong.themPhong(ph);
					String tt;
					if (tinhTrang) {
						tt = "Đã đặt";
					} else
						tt = "Còn trống";
					String[] row = { maPhong, tenPhong, loaiPhong, Double.toString(giaPhong) + "", moTa, tt };
					tableModel.addRow(row);
					xoaTrang();
					JOptionPane.showMessageDialog(null, "Thêm phòng thành công!");
					showMessage("Thêm phòng thành công!", txtMaPhong);
				} else {
					JOptionPane.showMessageDialog(null, "Trùng mã phòng!");
					txtMaPhong.selectAll();
					txtMaPhong.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thêm phòng không thành công!");
				txtMaPhong.selectAll();
				txtMaPhong.requestFocus();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu!");
			e.printStackTrace();
		}
	}

	private void TimPhongTheoMa() {
		// TODO Auto-generated method stub
		int pos = dsPhong.timPhongTheoMa(txtTimMa.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Phòng này có trong danh sách!");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Phòng này không có trong danh sách!");
		txtTimTen.setText("");
		showMessage("", txtMess);
	}

	private void TimPhongTheoTen() {
		// TODO Auto-generated method stub
		int pos = dsPhong.timPhongTheoTen(txtTimTen.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Phòng này có trong danh sách!");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Phòng này không có trong danh sách!");
		txtTimMa.setText("");
		showMessage("", txtMess);
	}

	private void CapNhatThongTinPhong() {
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		if (r != -1) {
			String maPhong = txtMaPhong.getText();
			String tenPhong = txtTen.getText();
			String loaiPhong = txtLoai.getText();
			double giaPhong = Double.parseDouble(txtGiaPhong.getText());
			String moTa = txtMoTa.getText();
			String tinhTrang = "";
			if (radTrong.isSelected())
				tinhTrang = radTrong.getText();
			if (radDaDat.isSelected())
				tinhTrang = radDaDat.getText();
			Phong ph = new Phong(maPhong, tenPhong, loaiPhong, giaPhong, moTa, tinhTrang);
			if (DAO_Phong.updatePhong(ph)) {
				dsPhong.capNhatThongTinPhong(ph);
				loadData();
				JOptionPane.showMessageDialog(null, "Cập nhật thông tin phòng thành công!");
				showMessage("Cập nhật thông tin phòng thành công!", txtMess);
			} else {
				JOptionPane.showMessageDialog(null, "Cập nhật phòng không thành công!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng muốn xoá!");
		}

	}

	private void xoaTrang() {
		txtMaPhong.setText("");
		txtTen.setText("");
		txtLoai.setText("");
		txtMoTa.setText("");
		txtTimMa.setText("");
		txtTimTen.setText("");
		txtGiaPhong.setText("");
		radTrong.setSelected(false);
		radDaDat.setSelected(false);
		txtMaPhong.requestFocus();
	}

	public void xoaPhong() throws Exception {
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá phòng này không?", "Chú ý!",
					JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				dsPhong.xoaPhong(r);
				DAO_Phong.delete(table.getValueAt(r, 0).toString());
				tableModel.removeRow(r);
				loadData();
				xoaTrang();
				JOptionPane.showMessageDialog(null, "Xóa phòng thành công!");
				showMessage("Xóa phòng thành công!", txtMess);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng muốn xoá!");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaPhong.setText(table.getValueAt(row, 0).toString());
		txtTen.setText(table.getValueAt(row, 1).toString());
		txtLoai.setText(table.getValueAt(row, 2).toString());
		txtGiaPhong.setText(table.getValueAt(row, 3).toString());
		txtMoTa.setText(table.getValueAt(row, 4).toString());
		if (tableModel.getValueAt(row, 5).toString().equalsIgnoreCase("Còn trống")) {
			radTrong.setSelected(true);
		} else {
			radDaDat.setSelected(true);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}