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

import DAO.DAOHoaDonDichVuPhong;
import DAO.DAOKhachHang;
import DanhSach.DanhSachKhachHang;
import Entity.HoaDonDichVuPhong;
import Entity.KhachHang;
import connectDB.ConnectDB;

public class FrmKhachHang extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private DanhSachKhachHang ds;
	private JTable table;
	private JTextField txtMaKH, txtTenKH, txtCMinhThu, txtSDT, txtGMail, txtTimMa, txtTimTen, txtMess;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnThoat, btnSua;
	private DefaultTableModel tableModel;
	private DAOKhachHang DAO_KH ;
	public FrmKhachHang() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createGUI();
	}

	public static void main(String[] args) {
		new FrmKhachHang().setVisible(true);
	}

	public void createGUI() {
		setTitle("Quản lý thông tin khách hàng");
		setSize(1000, 650);
		setResizable(false);
		setLocationRelativeTo(null);
		ds = new DanhSachKhachHang();

		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.red);
		pnlNorth.add(lblTieuDe);

		// CENTER

		Box b = Box.createVerticalBox(), bb = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6, b7, b8, bTimTen, bTimMa;
		JLabel lblMaKH, lblTenKH, lblCMinhThu, lblSDT, lblGMail;

		b.add(b1 = Box.createHorizontalBox());
		b.setBorder(BorderFactory.createTitledBorder("Khách hàng"));
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaKH = new JLabel("Mã khách hàng: "));
		b1.add(txtMaKH = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b2.add(lblTenKH = new JLabel("Họ tên: "));
		b2.add(txtTenKH = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b3.add(lblCMinhThu = new JLabel("Chứng minh thư: "));
		b3.add(txtCMinhThu = new JTextField());

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblSDT = new JLabel("Số điện thoại: "));
		b4.add(txtSDT = new JTextField());

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b5.add(lblGMail = new JLabel("Gmail: "));
		b5.add(txtGMail = new JTextField());

		lblTenKH.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblMaKH.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblSDT.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblGMail.setPreferredSize(lblCMinhThu.getPreferredSize());

		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b6.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		b.add(b7 = Box.createHorizontalBox());
		b7.add(Box.createHorizontalStrut(75));
		b7.add(btnThem = new JButton("Thêm"));
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnSua = new JButton("Sửa"));
		b7.add(Box.createHorizontalStrut(10));
		b7.add(btnXoaTrang = new JButton("Xoá trắng"));

		b.add(Box.createVerticalStrut(70));
		b.add(b8 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(50));
		b8.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		b8.add(Box.createVerticalStrut(10));
		b8.add(bTimMa = Box.createHorizontalBox());
		bTimMa.add(new JLabel("Mã khách hàng:  "));
		bTimMa.add(txtTimMa = new JTextField(10));
		bTimMa.add(btnTim = new JButton("Tìm"));
		b8.add(Box.createVerticalStrut(20));
		b8.add(bTimTen = Box.createHorizontalBox());
		bTimTen.add(new JLabel("Tên khách hàng: "));
		bTimTen.add(txtTimTen = new JTextField(10));
		bTimTen.add(btnTim = new JButton("Tìm"));
		b8.add(Box.createVerticalStrut(10));
		b.add(Box.createVerticalStrut(200));
		add(b, BorderLayout.WEST);

		bb = Box.createVerticalBox();
		bb.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
		String[] headers = "Mã khách hàng;Họ tên;Chứng minh thư ;SĐT;Gmail ;".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bb.add(scroll);
		add(bb, BorderLayout.CENTER);
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
		String maKhang = txtMaKH.getText().trim();
		String tenKH = txtTenKH.getText().trim();
		String cmthu = txtCMinhThu.getText().trim();
		String sdt = txtSDT.getText().trim();
		String gmail = txtGMail.getText().trim();

		Pattern p = Pattern.compile("^(KH)[0-9]{3}");
		if (!(maKhang.length() > 0 && p.matcher(maKhang).find())) {
			showMessage("Lỗi mã khách hàng", txtMaKH);
			return false;
		}

		Pattern p1 = Pattern.compile("[a-zA-Z]+");
		if (!(tenKH.length() > 0 && p1.matcher(tenKH).find())) {
			showMessage("Tên không tồn tại", txtTenKH);
			return false;
		}

		Pattern p2 = Pattern.compile("[0-9]{15}");
		if (!(cmthu.length() > 0 && p2.matcher(cmthu).find())) {
			showMessage("Chứng minh thư này không tồn tại", txtCMinhThu);
			return false;
		}

		Pattern p3 = Pattern.compile("[0-9]{10}");
		if (!(sdt.length() > 0 && p3.matcher(sdt).find())) {
			showMessage("Số điện thoại này không tồn tại", txtSDT);
			return false;
		}

		Pattern p4 = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
		if (!(gmail.length() > 0 && p4.matcher(gmail).find())) {
			showMessage("GMail này không tồn tại", txtGMail);
			return false;
		}

		return true;
	}

	private void xoaTrangActions() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtCMinhThu.setText("");
		txtSDT.setText("");
		txtTimMa.setText("");
		txtGMail.setText("");
		txtMaKH.requestFocus();
	}

	private void timActions() {
		// TODO Auto-generated method stub
		int pos = ds.timKHang(txtTimMa.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Tồn tại khách hàng có mã số này");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Không tồn tại khách hàng có mã số này");
	}

	public void xoaActions() throws Exception {
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá không", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaKHang(r);
				tableModel.removeRow(r);
				xoaTrangActions();

			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng muốn xoá!");
		}
	}

	private void themActions() {
		String makh = txtMaKH.getText();
		String ten = txtTenKH.getText();
		String cmthu = txtCMinhThu.getText();
		String sdt = txtSDT.getText();
		String gmail = txtGMail.getText();

		KhachHang kh = new KhachHang(makh, ten, cmthu, sdt, gmail);
		try {
			if (validData() == true) {
				if (ds.themKHang(kh)) {
					String[] row = { makh, ten, cmthu, sdt, gmail };
					tableModel.addRow(row);
					xoaTrangActions();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					showMessage("Thêm thành công", txtMaKH);
				} else {
					JOptionPane.showMessageDialog(null, "Trùng mã khách hàng");
					txtMaKH.selectAll();
					txtMaKH.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Thêm không thành công");
				txtMaKH.selectAll();
				txtMaKH.requestFocus();
			}

		} catch (Exception e) {
			// JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			e.printStackTrace();
		}
	}
	//add func loadData from SQL
	public void loadData() {
		//delete all
		//Load data
		DAO_KH = new DAOKhachHang();
		for(KhachHang kh:DAO_KH.getAll()) {
			Object row[] = {kh.getMaKHang(),kh.getTenKHang(),kh.getCMThu(),kh.getSDThoai(),kh.getGmail()};
			tableModel.addRow(row);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaKH.setText(table.getValueAt(row, 0).toString());
		txtTenKH.setText(table.getValueAt(row, 1).toString());
		txtCMinhThu.setText(table.getValueAt(row, 2).toString());
		txtSDT.setText(table.getValueAt(row, 3).toString());
		txtGMail.setText(table.getValueAt(row, 4).toString());
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