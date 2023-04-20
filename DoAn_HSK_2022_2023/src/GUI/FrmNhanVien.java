package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import Entity.NhanVien;

public class FrmNhanVien extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private DanhSachNhanVien ds;
	private JTable table;
	private JTextField txtMaNV, txtHoTen, txtCMThu, txtSDThoai, txtGmail, txtTim;
	private JComboBox cbChucVu;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnThoat;
	private DefaultTableModel tableModel;

	public FrmNhanVien() {
		ds = new DanhSachNhanVien();
		createGUI();
	}

	public static void main(String[] args) {
		new FrmNhanVien().setVisible(true);
	}

	public void createGUI() {
		setTitle("Quản lý nhân viên");
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.blue);
		pnlNorth.add(lblTieuDe);

		// CENTER
		Box b = Box.createVerticalBox();
		Box bb = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5;
		JLabel lblMaNV, lblTen, lblCMThu, lblSDThoai, lblGmail, lblChucVu;
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaNV = new JLabel("Mã nhân viên: "));
		b1.add(txtMaNV = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b2.add(lblTen = new JLabel("Họ tên: "));
		b2.add(txtHoTen = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b3.add(lblCMThu = new JLabel("Chứng minh thư: "));
		b3.add(txtCMThu = new JTextField());
		b3.add(lblChucVu = new JLabel("Chức vụ: "));

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblSDThoai = new JLabel("Số điện thoại: "));
		b4.add(txtSDThoai = new JTextField());

		cbChucVu = new JComboBox<>();
		b3.add(cbChucVu);
		cbChucVu.addItem("Lễ tân");
		cbChucVu.addItem("Bảo vệ");
		cbChucVu.addItem("Lao công");

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblGmail = new JLabel("Gmail: "));
		b4.add(txtGmail = new JTextField());

		lblMaNV.setPreferredSize(lblCMThu.getPreferredSize());
		lblTen.setPreferredSize(lblCMThu.getPreferredSize());
		lblSDThoai.setPreferredSize(lblCMThu.getPreferredSize());
		lblGmail.setPreferredSize(lblCMThu.getPreferredSize());

		b.add(b5 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(15));

		String[] headers = "Mã nhân viên;Họ tên;Chứng minh thư ;SĐT;Gmail ;Chức vụ;".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		b5.add(scroll);
		add(b, BorderLayout.CENTER);
		// SOUTH
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(split, BorderLayout.SOUTH);
		JPanel pnlLeft = new JPanel(), pnlRight = new JPanel();
		split.add(pnlLeft);
		split.add(pnlRight);

		pnlLeft.add(new JLabel("Nhập mã nhân viên cần tìm: "));
		pnlLeft.add(txtTim = new JTextField(10));
		pnlLeft.add(btnTim = new JButton("Tìm"));
		pnlRight.add(btnThem = new JButton("Thêm"));
		pnlRight.add(btnXoaTrang = new JButton("Xoá trắng"));
		pnlRight.add(btnXoa = new JButton("Xoá"));
		pnlRight.add(btnLuu = new JButton("Lưu"));
		pnlRight.add(btnThoat = new JButton("Thoát"));

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

	private void xoaTrangActions() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		txtCMThu.setText("");
		txtSDThoai.setText("");
		txtTim.setText("");
		txtGmail.setText("");
		cbChucVu.setSelectedIndex(0);
		txtMaNV.requestFocus();
	}

	private void timActions() {
		// TODO Auto-generated method stub
		int pos = ds.timNV(txtTim.getText());
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
		try {
			String maNV = txtMaNV.getText();
			String tenNV = txtHoTen.getText();
			String cmt = txtCMThu.getText();
			String sdt = txtSDThoai.getText();
			String gmail = txtGmail.getText();
			String chucVu = cbChucVu.getSelectedItem().toString();
			NhanVien nv = new NhanVien(maNV, tenNV, cmt, sdt, gmail, chucVu);
			if (ds.themNhanVien(nv)) {
				String[] row = {maNV, tenNV, cmt, sdt, gmail, chucVu};
				tableModel.addRow(row);
				xoaTrangActions();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã nhân viên");
				txtMaNV.selectAll();
				txtMaNV.requestFocus();
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			return;
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
		txtGmail.setText(table.getValueAt(row, 5).toString());
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