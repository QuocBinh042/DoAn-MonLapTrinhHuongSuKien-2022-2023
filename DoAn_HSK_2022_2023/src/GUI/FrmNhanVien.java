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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DanhSach.DanhSachNhanVien;
import Entity.NhanVien;

public class FrmNhanVien extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private DanhSachNhanVien ds;
	private JTable table;
	private JTextField txtMaNV, txtHoTen, txtCMThu, txtSDThoai, txtGmail, txtTimMa, txtTimTen, txtDiaChi, txtMess;
	private JComboBox cbChucVu;
	private JRadioButton radNam, radNu;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnThoat, btnSua;
	private DefaultTableModel tableModel;

	public FrmNhanVien() {
		createGUI();
	}

	public static void main(String[] args) {
		new FrmNhanVien().setVisible(true);
	}

	public void createGUI() {
		setTitle("Quản lý thông tin nhân viên");
		setSize(1200, 650);
		setResizable(false);
		setLocationRelativeTo(null);

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
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, bTimMa, bTimTen;
		JLabel lblMaNV, lblTenNV, lblCMinhThu, lblSDT, lblGMail, lblChucVu, lblGioiTinh, lblDiaChi;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaNV = new JLabel("Mã nhân viên: "));
		b1.add(txtMaNV = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b2.add(lblTenNV = new JLabel("Tên nhân viên: "));
		b2.add(txtHoTen = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b3.add(lblGioiTinh = new JLabel("Giới tính: "));
		b3.add(Box.createHorizontalStrut(80));
		ButtonGroup bg = new ButtonGroup();
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		bg.add(radNam);
		b.add(radNu);
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

		lblTenNV.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblMaNV.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblSDT.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblGMail.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblDiaChi.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblChucVu.setPreferredSize(lblCMinhThu.getPreferredSize());

		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b7.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		b.add(b8 = Box.createHorizontalBox());
		b8.add(Box.createHorizontalStrut(75));
		b8.add(btnThem = new JButton("Thêm"));
		b8.add(Box.createHorizontalStrut(10));
		b8.add(btnSua = new JButton("Sửa"));
		b8.add(Box.createHorizontalStrut(10));
		b8.add(btnXoaTrang = new JButton("Xoá trắng"));

		b.add(Box.createVerticalStrut(70));
		b.add(b9 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(50));
		b9.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		b9.add(Box.createVerticalStrut(10));
		b9.add(bTimMa = Box.createHorizontalBox());
		bTimMa.add(new JLabel("Mã nhân viên:  "));
		bTimMa.add(txtTimMa = new JTextField(10));
		bTimMa.add(btnTim = new JButton("Tìm"));
		b9.add(Box.createVerticalStrut(20));
		b9.add(bTimTen = Box.createHorizontalBox());
		bTimTen.add(new JLabel("Tên nhân viên: "));
		bTimTen.add(txtTimTen = new JTextField(10));
		bTimTen.add(btnTim = new JButton("Tìm"));
		add(b, BorderLayout.WEST);

		bb.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
		String[] headers = "Mã nhân viên;Họ tên;Chứng minh thư ;SĐT;Gmail ; Địa chỉ; Giới tính; Chức vụ ;".split(";");
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

		// ĐĂNG KÝ SỰ KIỆN
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
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
		String maNV = txtMaNV.getText().trim();
		String tenNV = txtHoTen.getText().trim();
		String cmthu = txtCMThu.getText().trim();
		String sdt = txtSDThoai.getText().trim();
		String gmail = txtGmail.getText().trim();
		String diachi = txtDiaChi.getText().trim();

		Pattern p = Pattern.compile("^(NV)[0-9]{3}");
		if (!(maNV.length() > 0 && p.matcher(maNV).find())) {
			showMessage("Lỗi mã nhân viên", txtMaNV);
			return false;
		}

		Pattern p1 = Pattern.compile("[a-zA-Z]+");
		if (!(tenNV.length() > 0 && p1.matcher(tenNV).find())) {
			showMessage("Tên không tồn tại", txtHoTen);
			return false;
		}

		Pattern p2 = Pattern.compile("[0-9]{15}");
		if (!(cmthu.length() > 0 && p2.matcher(cmthu).find())) {
			showMessage("Chứng minh thư này không tồn tại", txtCMThu);
			return false;
		}

		Pattern p3 = Pattern.compile("[0-9]{10}");
		if (!(sdt.length() > 0 && p3.matcher(sdt).find())) {
			showMessage("Số điện thoại này không tồn tại", txtSDThoai);
			return false;
		}

		Pattern p4 = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
		if (!(gmail.length() > 0 && p4.matcher(gmail).find())) {
			showMessage("GMail này không tồn tại", txtGmail);
			return false;
		}

		Pattern p5 = Pattern.compile("[a-zA-Z0-9]+");
		if (!(diachi.length() > 0 && p5.matcher(diachi).find())) {
			showMessage("Địa chỉ này không tồn tại", txtDiaChi);
			return false;
		}

		return true;
	}

	private void xoaTrangActions() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		txtCMThu.setText("");
		txtSDThoai.setText("");
		txtDiaChi.setText("");
		txtGmail.setText("");
		txtTimTen.setText("");
		txtTimMa.setText("");
		txtMaNV.requestFocus();
	}

	private void timActions() {
		// TODO Auto-generated method stub
		int pos = ds.timNV(txtTimMa.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Tồn tại nhân viên có mã số này");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Không tồn tại nhân viên có mã số này");
	}

	public void xoaActions() throws Exception {
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá không", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaNV(r);
				tableModel.removeRow(r);
				xoaTrangActions();

			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên muốn xoá!");
		}
	}

	private void themActions() {
		String manv = txtMaNV.getText();
		String ten = txtHoTen.getText();
		String cmthu = txtCMThu.getText();
		String sdt = txtSDThoai.getText();
		String gmail = txtGmail.getText();
		String diachi = txtDiaChi.getText();
		String gioitinh = "";

		if (radNam.isSelected())
			gioitinh = radNam.getText();
		if (radNu.isSelected())
			gioitinh = radNu.getText();
		String chucVu = cbChucVu.getSelectedItem().toString();

		NhanVien nv = new NhanVien(manv, ten, cmthu, sdt, gmail, diachi, gioitinh, chucVu);
		try {
			if (validData() == true) {
				if (ds.themNhanVien(nv)) {
					String[] row = { manv, ten, cmthu, sdt, gmail, diachi, gioitinh, chucVu };
					tableModel.addRow(row);
					xoaTrangActions();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					showMessage("Thêm thành công", txtMaNV);
				} else {
					JOptionPane.showMessageDialog(null, "Trùng mã nhân viên");
					txtMaNV.selectAll();
					txtMaNV.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thêm không thành công");
				txtMaNV.selectAll();
				txtMaNV.requestFocus();
			}

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNV.setText(table.getValueAt(row, 0).toString());
		txtHoTen.setText(table.getValueAt(row, 1).toString());
		txtCMThu.setText(table.getValueAt(row, 2).toString());
		txtSDThoai.setText(table.getValueAt(row, 3).toString());
		txtGmail.setText(table.getValueAt(row, 4).toString());
		cbChucVu.setSelectedIndex(cbChucVu.getSelectedIndex());
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