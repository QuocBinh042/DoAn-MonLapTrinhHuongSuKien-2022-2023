package nhanvien;

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
	private DanhSachNhanVien ds;
	private JTable table;
	private JTextField txtMaNV, txtHoTen, txtCMThu, txtSDThoai, txtGmail,txtTim;
	private JRadioButton radQLy, radLeTan;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim;
	private DefaultTableModel tableModel;
	private Database databasee;

	public GUI() {
		databasee = new Database();
		ds = new DanhSachNhanVien();
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
		JLabel lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.blue);
		pnlNorth.add(lblTieuDe);

		// CENTER
		
		Box b = Box.createVerticalBox();

		Box b1, b2, b3, b4, b5;
		JLabel lblMaNV, lblTen, lblCMThu, lblSDThoai, lblGmail, lblChucVu;
		lblMaNV = new JLabel("Mã nhân viên: ");
		lblTen = new JLabel("Họ tên: ");
		lblCMThu = new JLabel("Chứng minh thư: ");
		lblSDThoai = new JLabel("Số điện thoại: ");
		lblGmail = new JLabel("Gmail: ");
		lblChucVu = new JLabel("Chức vụ: ");
		txtMaNV = new JTextField();
		txtHoTen = new JTextField();
		txtCMThu = new JTextField();
		txtSDThoai = new JTextField();
		txtGmail = new JTextField();


		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaNV);
		b1.add(txtMaNV);

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b2.add(lblTen);
		b2.add(txtHoTen);
		
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b3.add(lblCMThu);
		b3.add(txtCMThu);
		b3.add(lblChucVu);
	

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblSDThoai);
		b4.add(txtSDThoai);
	

		ButtonGroup bg = new ButtonGroup();
		radQLy = new JRadioButton("Quản lý");
		radLeTan = new JRadioButton("Lễ tân");
		bg.add(radQLy);
		bg.add(radLeTan);
		b3.add(radQLy);
		b3.add(radLeTan);

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblGmail);
		b4.add(txtGmail);

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
				databasee.writeNV("Nhân viên.txt", ds);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	private void xoaTrangActions() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		txtCMThu.setText("");
		txtSDThoai.setText("");
		txtTim.setText("");
		txtGmail.setText("");
		radQLy.setSelected(false);
		radLeTan.setSelected(false);
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
				databasee.writeNV("Nhân viên.txt", ds);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên muốn xoá!");
		}
	}

	private void themActions() {
		try {
			
			String manv = txtMaNV.getText();
			String ten = txtHoTen.getText();
			String cmthu = txtCMThu.getText();
			String sdt = txtSDThoai.getText();
			String chucvu = "";
			String gmail = txtGmail.getText();
			if (radQLy.isSelected())
				chucvu = radQLy.getText();
			if (radLeTan.isSelected())
				chucvu = radLeTan.getText();
			NhanVien nv = new NhanVien(manv, ten, cmthu, sdt, chucvu, gmail);
			if (ds.themNhanVien(nv)) {
				String[] row = { manv, ten, cmthu, sdt, chucvu, gmail + "" };
				tableModel.addRow(row);
				xoaTrangActions();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã nhân viên");
				txtMaNV.selectAll();
				txtMaNV.requestFocus();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			return;
		}
	}


	public void loadData() {
		try {
			ds = databasee.read_NV("Nhân viên.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ds == null) {
			ds = new DanhSachNhanVien();
		} else {
			for (NhanVien nv : ds.getList()) {
				String[] row = { nv.getMaNV(), nv.getHoTen(), nv.getCMThu(), nv.getSdthoai(), nv.getGmail() + "",
						nv.getChucVu() + "" };
				tableModel.addRow(row);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaNV.setText(table.getValueAt(row, 0).toString());
		txtHoTen.setText(table.getValueAt(row, 1).toString());
		txtCMThu.setText(table.getValueAt(row, 2).toString());
		if (tableModel.getValueAt(row, 3).toString().equalsIgnoreCase("Quản lý")) {
			radQLy.setSelected(true);
			radLeTan.setSelected(false);
		} else {
			radQLy.setSelected(false);
			radLeTan.setSelected(true);
		}
		txtSDThoai.setText(table.getValueAt(row, 4).toString());
		txtGmail.setText(table.getValueAt(row, 5).toString());
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