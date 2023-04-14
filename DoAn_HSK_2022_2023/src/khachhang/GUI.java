package khachhang;

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

public class GUI extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private DanhSachKhachHang ds;
	private JTable table;
	private JTextField txtMaKH, txtTenKH, txtCMinhThu, txtSDT, txtGMail,txtTim;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim;
	private DefaultTableModel tableModel;
	private Database databasee;

	public GUI() {
		databasee = new Database();
		ds = new DanhSachKhachHang();
		gui();
		try {
			loadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	public static void main(String[] args) {
		new GUI().setVisible(true);
	}

	public void gui() {
		setTitle("Welcome");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.blue);
		pnlNorth.add(lblTieuDe);

		// CENTER
		
		Box b = Box.createVerticalBox();

		Box b1, b2, b3, b4, b5, b6 ;
		JLabel lblMaKH, lblTenKH, lblCMinhThu, lblSDT, lblGMail;
		lblMaKH = new JLabel("Mã khách hàng: ");
		lblTenKH = new JLabel("Họ tên: ");
		lblCMinhThu = new JLabel("Chứng minh thư: ");
		lblSDT = new JLabel("Số điện thoại: ");
		lblGMail = new JLabel("Gmail: ");
		txtMaKH = new JTextField();
		txtTenKH = new JTextField();
		txtCMinhThu = new JTextField();
		txtSDT = new JTextField();
		txtGMail = new JTextField();


		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaKH);
		b1.add(txtMaKH);

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b2.add(lblTenKH);
		b2.add(txtTenKH);
		
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b3.add(lblCMinhThu);
		b3.add(txtCMinhThu);
	
	

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblSDT);
		b4.add(txtSDT);
	

		
		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b5.add(lblGMail);
		b5.add(txtGMail);

		lblTenKH.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblMaKH.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblSDT.setPreferredSize(lblCMinhThu.getPreferredSize());
		lblGMail.setPreferredSize(lblCMinhThu.getPreferredSize());
		

		b.add(b6 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(15));

		String[] headers = "Mã khách hàng;Họ tên;Chứng minh thư ;SĐT;Gmail ;".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		b6.add(scroll);
		add(b, BorderLayout.CENTER);
		// SOUTH
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(split, BorderLayout.SOUTH);
		JPanel pnlLeft = new JPanel(), pnlRight = new JPanel();
		split.add(pnlLeft);
		split.add(pnlRight);


		pnlLeft.add(new JLabel("Nhập mã khách hàng cần tìm: "));
		txtTim = new JTextField(10);
		btnTim = new JButton("Tìm");
		btnThem = new JButton("Thêm");
		btnXoaTrang = new JButton("Xoá trắng");
		btnXoa = new JButton("Xoá");
		btnLuu = new JButton("Lưu");

		pnlLeft.add(txtTim);
		pnlLeft.add(btnTim);
		pnlRight.add(btnThem);
		pnlRight.add(btnXoaTrang);
		pnlRight.add(btnXoa);
		pnlRight.add(btnLuu);

		// ĐĂNG KÝ SỰ KIỆN
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
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
				databasee.writeKH("Khách hàng.txt", ds);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	private void xoaTrangActions() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtCMinhThu.setText("");
		txtSDT.setText("");
		txtTim.setText("");
		txtGMail.setText("");
		txtMaKH.requestFocus();
	}

	private void timActions() {
		// TODO Auto-generated method stub
		int pos = ds.timKHang(txtTim.getText());
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
				databasee.writeKH("Khách hàng.txt", ds);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng muốn xoá!");
		}
	}

	private void themActions() {
		try {
			
			String makh = txtMaKH.getText();
			String ten = txtTenKH.getText();
			String cmthu = txtCMinhThu.getText();
			String sdt = txtSDT.getText();
			String gmail = txtGMail.getText();
			
			KhachHang kh = new KhachHang(makh, ten, cmthu, sdt,  gmail);
			if (ds.themKHang(kh)) {
				String[] row = { makh, ten, cmthu, sdt, gmail + "" };
				tableModel.addRow(row);
				xoaTrangActions();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã khách hàng");
				txtMaKH.selectAll();
				txtMaKH.requestFocus();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			return;
		}
	}


	public void loadData() {
		try {
			ds = databasee.read_KH("Khách hàng.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ds == null) {
			ds = new DanhSachKhachHang();
		} else {
			for (KhachHang kh : ds.getList()) {
				String[] row = { kh.getMaKHang(), kh.getTenKHang(), kh.getCMThu(), kh.getSDThoai(), kh.getGmail() + ""};
				tableModel.addRow(row);
			}
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