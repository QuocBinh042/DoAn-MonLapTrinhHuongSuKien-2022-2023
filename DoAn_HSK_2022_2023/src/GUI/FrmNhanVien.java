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
import javax.swing.JComboBox;
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

import DanhSach.DanhSachNhanVien;
import Entity.DichVu;
import Entity.NhanVien;
import DAO.DAONhanVien;
import connectDB.ConnectDB;

public class FrmNhanVien extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private DanhSachNhanVien dsNV;
	private JTable table;
	private JTextField txtMaNV, txtHoTen, txtCMThu, txtSDThoai, txtGmail, txtTimMa, txtTimTen, txtDiaChi, txtMess,
			txtMatKhau;
	private JComboBox<Object> cbChucVu;
	private JRadioButton radNam, radNu;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTimMa, btnTimTen, btnThoat, btnSua;
	private DAONhanVien DAO_NV;
	private DefaultTableModel tableModel;

	public FrmNhanVien() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createGUI();
	}

	public static void main(String[] args) {
		new FrmNhanVien().setVisible(true);
	}

	public void createGUI() {
		setTitle("Quản lý thông tin nhân viên");
		setSize(1350, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		dsNV = new DanhSachNhanVien();
		
		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.red);
		pnlNorth.add(lblTieuDe);

		// CENTER
		Box b = Box.createVerticalBox(), bb = Box.createHorizontalBox();
		b.setBorder(BorderFactory.createTitledBorder("Nhân viên"));
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, bTimMa, bTimTen;
		JLabel lblMaNV, lblTenNV, lblCMinhThu, lblSDT, lblGMail, lblChucVu, lblGioiTinh, lblDiaChi, lblMatKhau;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaNV = new JLabel("Mã nhân viên: "));
		b1.add(txtMaNV = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b2.add(lblTenNV = new JLabel("Tên nhân viên: "));
		b2.add(txtHoTen = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblGioiTinh = new JLabel("Giới tính: "));
		b3.add(Box.createHorizontalStrut(80));
		ButtonGroup bg = new ButtonGroup();
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		bg.add(radNam);
		bg.add(radNu);
		b3.add(radNam);
		b3.add(Box.createHorizontalStrut(80));
		b3.add(radNu);

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblCMinhThu = new JLabel("Chứng minh thư: "));
		b4.add(txtCMThu = new JTextField());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b5.add(lblSDT = new JLabel("Số điện thoại: "));
		b5.add(txtSDThoai = new JTextField());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b5.add(lblGMail = new JLabel("Gmail: "));
		b5.add(txtGmail = new JTextField());

		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b6.add(lblDiaChi = new JLabel("Địa chỉ"));
		b6.add(txtDiaChi = new JTextField());

		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b6.add(lblChucVu = new JLabel("Chức vụ: "));
		cbChucVu = new JComboBox<>();
		b6.add(cbChucVu);
		cbChucVu.addItem("Lễ tân");
		cbChucVu.addItem("Bảo vệ");
		cbChucVu.addItem("Lao công");

		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b7.add(lblMatKhau = new JLabel("Mật khẩu"));
		b7.add(txtMatKhau = new JTextField());

		lblTenNV.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblMaNV.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblSDT.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblGMail.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblDiaChi.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblChucVu.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblMatKhau.setPreferredSize(lblCMinhThu.getPreferredSize());

		b.add(b8 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b8.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		b.add(b9 = Box.createHorizontalBox());
		b9.add(Box.createHorizontalStrut(75));
		b9.add(btnThem = new JButton("Thêm"));
		b9.add(Box.createHorizontalStrut(10));
		b9.add(btnSua = new JButton("Sửa"));
		b9.add(Box.createHorizontalStrut(10));
		b9.add(btnXoaTrang = new JButton("Xoá trắng"));

		b.add(Box.createVerticalStrut(60));
		b.add(b10 = Box.createVerticalBox());
		b10.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		b10.add(Box.createVerticalStrut(10));
		b10.add(bTimMa = Box.createHorizontalBox());
		bTimMa.add(new JLabel("Mã nhân viên:  "));
		bTimMa.add(txtTimMa = new JTextField(10));
		bTimMa.add(btnTimMa = new JButton("Tìm"));
		b10.add(Box.createVerticalStrut(20));
		b10.add(bTimTen = Box.createHorizontalBox());
		bTimTen.add(new JLabel("Tên nhân viên: "));
		bTimTen.add(txtTimTen = new JTextField(10));
		bTimTen.add(btnTimTen = new JButton("Tìm"));
		b10.add(Box.createVerticalStrut(10));
		add(b, BorderLayout.WEST);

		bb.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
		String[] headers = "Mã nhân viên;Họ tên;Chứng minh thư ;SĐT;Gmail ; Địa chỉ; Giới tính; Chức vụ; Mật khẩu;"
				.split(";");
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
		
		//Gọi hàm loadData
		loadData();
		
		/**
		 * Đăng ký sự kiện
		 */
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
		txtMaNV.setEditable(false);
		txtHoTen.setEditable(false);
		txtCMThu.setEditable(false);
		txtSDThoai.setEditable(false);
		txtDiaChi.setEditable(false);
		txtGmail.setEditable(false);
		txtMatKhau.setEditable(false);
		radNam.setEnabled(false);
		radNu.setEnabled(false);
		cbChucVu.setEnabled(false);
	}

	private void TXTedit_true() {
		txtMaNV.setEditable(true);
		txtHoTen.setEditable(true);
		txtCMThu.setEditable(true);
		txtSDThoai.setEditable(true);
		txtDiaChi.setEditable(true);
		txtGmail.setEditable(true);
		txtMatKhau.setEditable(true);
		radNam.setEnabled(true);
		radNu.setEnabled(true);
		cbChucVu.setEnabled(true);
	}
	
	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}
	
	private boolean validData() {
		String maNV = txtMaNV.getText().trim();
		String tenNV = txtHoTen.getText().trim();
		String cmthu = txtCMThu.getText().trim();
		String sdt = txtSDThoai.getText().trim();
		String gmail = txtGmail.getText().trim();
		String diachi = txtDiaChi.getText().trim();
		String matKhau = txtMatKhau.getText().trim();

		Pattern p = Pattern.compile("^(NV)[0-9]{3}");
		if (!(maNV.length() > 0 && p.matcher(maNV).find())) {
			showMessage("Lỗi mã nhân viên!", txtMaNV);
			return false;
		}

		Pattern p1 = Pattern.compile("[a-zA-Z]+");
		if (!(tenNV.length() > 0 && p1.matcher(tenNV).find())) {
			showMessage("Tên không tồn tại!", txtHoTen);
			return false;
		}

		Pattern p2 = Pattern.compile("[0-9]{11}");
		if (!(cmthu.length() > 0 && p2.matcher(cmthu).find())) {
			showMessage("Chứng minh thư này không tồn tại!", txtCMThu);
			return false;
		}

		Pattern p3 = Pattern.compile("[0-9]{10}");
		if (!(sdt.length() > 0 && p3.matcher(sdt).find())) {
			showMessage("Số điện thoại này không tồn tại!", txtSDThoai);
			return false;
		}

		Pattern p4 = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
		if (!(gmail.length() > 0 && p4.matcher(gmail).find())) {
			showMessage("GMail này không tồn tại!", txtGmail);
			return false;
		}

		Pattern p5 = Pattern.compile("[a-zA-Z0-9]+");
		if (!(diachi.length() > 0 && p5.matcher(diachi).find())) {
			showMessage("Địa chỉ này không tồn tại!", txtDiaChi);
			return false;
		}

		Pattern p6 = Pattern.compile("(.)+");
		if (!(matKhau.length() > 0 && p6.matcher(matKhau).find())) {
			showMessage("Mật khẩu này không tồn tại!", txtMatKhau);
			return false;
		}
		return true;
	}

	public void loadData() {
		// delete all
		// Load data
		DAO_NV = new DAONhanVien();
		for (NhanVien nv : DAO_NV.getAll()) {
			Object row[] = { nv.getMaNV(), nv.getHoTen(), nv.getMatKhau(), nv.getChucVu(), nv.getGioiTinh(),
					nv.getCmthu(), nv.getChucVu(), nv.getGmail(), nv.getDiaChi() };
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
						txtMaNV.setEditable(false);
						btnSua.setText("Hoàn tất");
					} else {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên muốn xoá!");
						btnThem.setEnabled(true);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			} else {
				CapNhatNhanVien();
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
				xoaNhanVien();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnTimMa)) {
			TimNhanVienTheoMa();
		} else if (o.equals(btnTimTen)) {
			TimNhanVienTheoTen();
		} else if (o.equals(btnLuu)) {
			themNhanVien();
		} else if (o.equals(btnThoat))
			System.exit(0);
	}
	
	private void themNhanVien() {
		String maNV = txtMaNV.getText();
		String tenNV = txtHoTen.getText();
		String cmthu = txtCMThu.getText();
		String sdt = txtSDThoai.getText();
		String gmail = txtGmail.getText();
		String diaChi = txtDiaChi.getText();
		String gioiTinh = "";
		String matKhau = txtMatKhau.getText();
		if (radNam.isSelected())
			gioiTinh = radNam.getText();
		if (radNu.isSelected())
			gioiTinh = radNu.getText();
		String chucVu = cbChucVu.getSelectedItem().toString();

		NhanVien nv = new NhanVien(maNV, tenNV, cmthu, sdt, gmail, diaChi, gioiTinh, chucVu, matKhau);
		try {
			if (!validData()) {
				if (dsNV.themNhanVien(nv)) {
					String[] row = { maNV, tenNV, cmthu, sdt, gmail, diaChi, gioiTinh, chucVu, matKhau };
					tableModel.addRow(row);
					xoaTrang();
					JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
					showMessage("Thêm thành công!", txtMaNV);
				} else {
					JOptionPane.showMessageDialog(null, "Trùng mã nhân viên!");
					txtMaNV.selectAll();
					txtMaNV.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thêm nhân viên không thành công!");
				txtMaNV.selectAll();
				txtMaNV.requestFocus();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu!");
			e.printStackTrace();
		}
	}

	private void TimNhanVienTheoMa(){
		// TODO Auto-generated method stub
		int pos = dsNV.timNhanVienTheoMa(txtTimMa.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Nhân viên này có trong danh sách!");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Nhân viên này không có trong danh sách!");
		txtTimTen.setText("");
		showMessage("", txtMess);
	}

	private void TimNhanVienTheoTen(){
		// TODO Auto-generated method stub
		int pos = dsNV.timNhanVienTheoTen(txtTimTen.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Nhân viên này có trong danh sách!");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Nhân viên này không có trong danh sách!");
		txtTimMa.setText("");
		showMessage("", txtMess);
	}
	
	private void CapNhatNhanVien() {
		// TODO Auto-generated method stub
		String maNV = txtMaNV.getText();
		String tenNV = txtHoTen.getText();
		String cmthu = txtCMThu.getText();
		String sdt = txtSDThoai.getText();
		String gmail = txtGmail.getText();
		String diaChi = txtDiaChi.getText();
		String gioiTinh = "";
		String matKhau = txtMatKhau.getText();
		if (radNam.isSelected())
			gioiTinh = radNam.getText();
		if (radNu.isSelected())
			gioiTinh = radNu.getText();
		String chucVu = cbChucVu.getSelectedItem().toString();
		NhanVien nv = new NhanVien(maNV, tenNV, cmthu, sdt, gmail, diaChi, gioiTinh, chucVu, matKhau);
		if (dsNV.capNhatThongTinNhanVien(nv)) {
			int index = dsNV.getList().indexOf(nv);
			tableModel.setValueAt(tenNV, index, 1);
			tableModel.setValueAt(cmthu, index, 2);
			tableModel.setValueAt(sdt, index, 3);
			tableModel.setValueAt(gmail, index, 4);
			tableModel.setValueAt(diaChi, index, 5);
			tableModel.setValueAt(gioiTinh, index, 6);
			tableModel.setValueAt(chucVu, index, 7);
			tableModel.setValueAt(matKhau, index, 8);
			showMessage("Cập nhật thành công thông tin nhân viên!", txtMess);
			JOptionPane.showMessageDialog(null, "Cập nhật thành công thông tin nhân viên!");
		}
	}
	
	public void xoaNhanVien() throws Exception {
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá nhân viên này không?", "Chú ý!", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				dsNV.xoaNV(r);
				tableModel.removeRow(r);
				xoaTrang();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên muốn xoá!");
		}
	}
	
	private void xoaTrang() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		txtCMThu.setText("");
		txtSDThoai.setText("");
		txtDiaChi.setText("");
		txtGmail.setText("");
		txtMatKhau.setText("");
		txtTimTen.setText("");
		txtTimMa.setText("");
		txtMaNV.requestFocus();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNV.setText(table.getValueAt(row, 0).toString());
		txtHoTen.setText(table.getValueAt(row, 1).toString());
		txtCMThu.setText(table.getValueAt(row, 2).toString());
		txtSDThoai.setText(table.getValueAt(row, 3).toString());
		txtGmail.setText(table.getValueAt(row, 4).toString());
		txtDiaChi.setText(table.getValueAt(row, 5).toString());
		if (table.getValueAt(row, 6).toString().equalsIgnoreCase("Nam"))
			radNam.setSelected(true);
		else
			radNu.setSelected(true);
		cbChucVu.setSelectedIndex(cbChucVu.getSelectedIndex());
		txtMatKhau.setText(table.getValueAt(row, 8).toString());
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