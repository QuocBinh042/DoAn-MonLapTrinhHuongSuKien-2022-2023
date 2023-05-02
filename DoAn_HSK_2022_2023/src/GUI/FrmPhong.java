package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DanhSach.DanhSachPhong;
import Entity.KhachHang;
import Entity.Phong;
import DAO.DAOKhachHang;
import DAO.DAOPhong;
import connectDB.ConnectDB;
import Entity.HoaDonDichVuPhong;
import DAO.DAOHoaDonDichVuPhong;


public class FrmPhong extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private DanhSachPhong ds;
	private JTable table;
	private JTextField txtMaPhong, txtTen, txtLoai, txtGiaPhong, txtMoTa, txtTimMa, txtTimTen, txtMess;
	private JRadioButton radTrong, radDaDat;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnThoat, btnSua;
	private DefaultTableModel tableModel;
	private DAOPhong DAO_Phong ;

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
		setSize(1000, 650);
		setResizable(false);
		setLocationRelativeTo(null);
		ds = new DanhSachPhong();

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

//		b.add(b4 = Box.createHorizontalBox());
//		b.add(Box.createVerticalStrut(15));
//		b4.add(lblTinhTrang = new JLabel("Tình trạng: "));
//		b4.add(Box.createHorizontalStrut(50));
//		ButtonGroup bg = new ButtonGroup();
//		bg.add(radTrong = new JRadioButton("Còn trống"));
//		bg.add(radDaDat = new JRadioButton("Đã đặt"));
//		b4.add(radTrong);
//		b4.add(Box.createHorizontalStrut(50));
//		b4.add(radDaDat);
//
//		b.add(b5 = Box.createHorizontalBox());
//		b.add(Box.createVerticalStrut(15));
//		b5.add(lblGiaPhong = new JLabel("Giá phòng: "));
//		b5.add(txtGiaPhong = new JTextField());
//
//		b.add(b6 = Box.createHorizontalBox());
//		b.add(Box.createVerticalStrut(15));
//		b6.add(lblMoTa = new JLabel("Mô tả: "));
//		b6.add(txtMoTa = new JTextField());
		

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
		b6.add(Box.createHorizontalStrut(50));
		ButtonGroup bg = new ButtonGroup();
		bg.add(radTrong = new JRadioButton("Còn trống"));
		bg.add(radDaDat = new JRadioButton("Đã đặt"));
		b6.add(radTrong);
		b6.add(Box.createHorizontalStrut(50));
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
		bTimMa.add(btnTim = new JButton("Tìm"));
		b9.add(Box.createVerticalStrut(20));
		b9.add(bTimTen = Box.createHorizontalBox());
		bTimTen.add(new JLabel("Tên phòng: "));
		bTimTen.add(txtTimTen = new JTextField(10));
		bTimTen.add(btnTim = new JButton("Tìm"));
		b9.add(Box.createVerticalStrut(10));
		b.add(Box.createVerticalStrut(200));

		bb.setBorder(BorderFactory.createTitledBorder("Danh sách phòng"));
		String[] headers = "Mã phòng;Tên;Loại phòng; Giá phòng; Mô tả;Tình trạng".split(";");
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
		loadData();
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

		// ĐĂNG KÝ SỰ KIỆN
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
		table.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem))
			themActions();
		if (o.equals(btnXoa))
			try {
				xoaActions();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (o.equals(btnXoaTrang))
			xoaTrangActions();
		if (o.equals(btnTim))
			timActions();
		if (o.equals(btnLuu))
			try {
				JOptionPane.showMessageDialog(this, "Lưu thành công");

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (o.equals(btnThoat))
			System.exit(0);

	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}

	private boolean validData() {
		String maPhong = txtMaPhong.getText().trim();
		String loaiPhong = txtLoai.getText().trim();
		String tenPhong = txtTen.getText().trim();
		String giaPhong = txtGiaPhong.getText().trim();
		String moTa = txtMoTa.getText().trim();

		Pattern p = Pattern.compile("^(P)[0-9]{3}");
		if (!(maPhong.length() > 0 && p.matcher(maPhong).find())) {
			showMessage("Lỗi mã phòng", txtMaPhong);
			return false;
		}

		Pattern p1 = Pattern.compile("[a-zA-Z0-9]+");
		if (!(tenPhong.length() > 0 && p1.matcher(tenPhong).find())) {
			showMessage("Tên phòng không tồn tại", txtTen);
			return false;
		}

		Pattern p2 = Pattern.compile("[a-zA-Z]+[0-9]{4}+");
		if (!(loaiPhong.length() > 0 && p2.matcher(loaiPhong).find())) {
			showMessage("Loại phòng này không tồn tại", txtLoai);
			return false;
		}

		Pattern p3 = Pattern.compile("[0-9]");
		if (!(giaPhong.length() > 0 && p3.matcher(giaPhong).find())) {
			showMessage("Giá phòng này không tồn tại", txtGiaPhong);
			return false;
		}

		Pattern p4 = Pattern.compile("[a-zA-Z0-9\\,]+");
		if (!(moTa.length() > 0 && p4.matcher(moTa).find())) {
			showMessage("Mô tả này không tồn tại", txtMoTa);
			return false;
		}

		return true;
	}

	private void xoaTrangActions() {
		txtMaPhong.setText("");
		txtTen.setText("");
		txtLoai.setText("");
		txtMoTa.setText("");
		txtTimMa.setText("");
		txtGiaPhong.setText("");
		radTrong.setSelected(false);
		radDaDat.setSelected(false);
		txtMaPhong.requestFocus();
		txtMaPhong.setEnabled(true);
	}

	private void timActions() {
		// TODO Auto-generated method stub
		int pos = ds.timPhong(txtTimMa.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Tồn tại phòng có mã số này");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Không tồn tại phòng có mã số này");
	}

	public void xoaActions() throws Exception {
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá không", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaPhong(r);
				tableModel.removeRow(r);
				xoaTrangActions();

			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng muốn xoá!");
		}
	}

	private void themActions() {
		String maphong = txtMaPhong.getText();
		String tenphong = txtTen.getText();
		String loaip = txtLoai.getText();
		double giaP = Double.parseDouble(txtGiaPhong.getText());
		String mota = txtMoTa.getText();
		String tinhTrang = "";

		if (radTrong.isSelected())
			tinhTrang = radTrong.getText();
		if (radDaDat.isSelected())
			tinhTrang = radDaDat.getText();
		Phong ph = new Phong(maphong, tenphong, loaip, mota, giaP,tinhTrang);
		try {
			if (validData() == true) {
				if (ds.themPhong(ph)) {
					String[] row = { maphong, tenphong, loaip, Double.toString(giaP) + "", mota,tinhTrang };
					tableModel.addRow(row);
					xoaTrangActions();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					showMessage("Thêm thành công", txtMaPhong);
				} else {
					JOptionPane.showMessageDialog(null, "Trùng mã phòng");
					txtMaPhong.selectAll();
					txtMaPhong.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thêm không thành công");
				txtMaPhong.selectAll();
				txtMaPhong.requestFocus();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			e.printStackTrace();
		}
	}
	//add func loadData from SQL
		public void loadData() {
			//delete all
			//Load data
			DAO_Phong = new DAOPhong();
			for(Phong phong : DAO_Phong.getAll()) {
				Object row[] = {phong.getMaPhong(),phong.getTenPhong(),phong.getLoaiPhong(),phong.getGiaPhong(),phong.getMoTa(),phong.getTinhTrang()};
				tableModel.addRow(row);
			}
		}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaPhong.setText(table.getValueAt(row, 0).toString());
		txtMaPhong.setEnabled(false);
		txtTen.setText(table.getValueAt(row, 1).toString());
		txtLoai.setText(table.getValueAt(row, 2).toString());
		if (tableModel.getValueAt(row, 3).toString().equalsIgnoreCase("Trống")) {
			radTrong.setSelected(true);
			radDaDat.setSelected(false);
		} else {
			radTrong.setSelected(false);
			radDaDat.setSelected(true);
		}
		txtGiaPhong.setText(table.getValueAt(row, 4).toString());
		txtMoTa.setText(table.getValueAt(row, 5).toString());

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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}