package phong;

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
	private DanhSachPhong ds;
	private JTable table;
	private JTextField txtMaPhong, txtTen, txtLoai, txtMoTa, txtGiaPhong,txtTim;
	private JRadioButton radTrong, radDaDat;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim;
	private DefaultTableModel tableModel;
	private Database databasee;

	public GUI() {
		databasee = new Database();
		ds = new DanhSachPhong();
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
		JLabel lblTieuDe = new JLabel("THÔNG TIN PHÒNG");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.red);
		pnlNorth.add(lblTieuDe);

		// CENTER
		
		Box b = Box.createVerticalBox();

		Box b1, b2, b3, b4, b5;
		JLabel lblMaPhong, lblTen, lblLoai, lblMoTa, lblGiaPhong, lblTinhTrang;
		lblMaPhong = new JLabel("Mã phòng: ");
		lblTen = new JLabel("Tên phòng: ");
		lblLoai = new JLabel("Loại phòng: ");
		lblMoTa = new JLabel("Mô tả: ");
		lblGiaPhong = new JLabel("Giá phòng: ");
		lblTinhTrang = new JLabel("Tình trạng: ");
		txtMaPhong = new JTextField();
		txtTen = new JTextField();
		txtLoai = new JTextField();
		txtMoTa = new JTextField();
		txtGiaPhong = new JTextField();


		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaPhong);
		b1.add(txtMaPhong);

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b2.add(lblTen);
		b2.add(txtTen);
		
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b3.add(lblLoai);
		b3.add(txtLoai);
		b3.add(lblTinhTrang);
	

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblMoTa);
		b4.add(txtMoTa);
	

		ButtonGroup bg = new ButtonGroup();
		radTrong = new JRadioButton("Trống");
		radDaDat = new JRadioButton("Đã đặt");
		bg.add(radTrong);
		bg.add(radDaDat);
		b3.add(radTrong);
		b3.add(radDaDat);

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblGiaPhong);
		b4.add(txtGiaPhong);

		lblMaPhong.setPreferredSize(lblLoai.getPreferredSize());
		lblTen.setPreferredSize(lblLoai.getPreferredSize());
		lblMoTa.setPreferredSize(lblLoai.getPreferredSize());
		lblGiaPhong.setPreferredSize(lblLoai.getPreferredSize());

		b.add(b5 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(15));

		String[] headers = "Mã phòng;Tên;Loại phòng;Tình trạng; Mô tả; Giá phòng".split(";");
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


		pnlLeft.add(new JLabel("Nhập mã phòng cần tìm: "));
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
				databasee.writeNV("Phòng.txt", ds);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	private void xoaTrangActions() {
		txtMaPhong.setText("");
		txtTen.setText("");
		txtLoai.setText("");
		txtMoTa.setText("");
		txtTim.setText("");
		txtGiaPhong.setText("");
		radTrong.setSelected(false);
		radDaDat.setSelected(false);
		txtMaPhong.requestFocus();
	}

	private void timActions() {
		// TODO Auto-generated method stub
		int pos = ds.timPhong(txtTim.getText());
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
				ds.xoaPhong(r);
				tableModel.removeRow(r);
				xoaTrangActions();
				databasee.writeNV("Phòng.txt", ds);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên muốn xoá!");
		}
	}

	private void themActions() {
		try {
			
			String maP = txtMaPhong.getText();
			String ten = txtTen.getText();
			String loai = txtLoai.getText();
			String mota = txtMoTa.getText();
			String tinhtrang = "";
			double luong = Double.parseDouble(txtGiaPhong.getText());
			if (radTrong.isSelected())
				tinhtrang = radTrong.getText();
			if (radDaDat.isSelected())
				tinhtrang = radDaDat.getText();
			Phong ph = new Phong(maP, ten, loai, mota, luong, tinhtrang );
			if (ds.themPhong(ph)) {
				String[] row = { maP, ten, loai, tinhtrang, Double.toString(luong) + "" };
				tableModel.addRow(row);
				xoaTrangActions();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã nhân viên");
				txtMaPhong.selectAll();
				txtMaPhong.requestFocus();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			return;
		}
	}


	public void loadData() {
		try {
			ds = databasee.read_NV("Phòng.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ds == null) {
			ds = new DanhSachPhong();
		} else {
			for (Phong nv : ds.getList()) {
				String[] row = { nv.getMaPhong(), nv.getTenPhong(), nv.getLoaiPhong(), nv.getMoTa(), nv.getGiaPhong() + "",
						nv.getTinhTrang() + "" };
				tableModel.addRow(row);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaPhong.setText(table.getValueAt(row, 0).toString());
		txtTen.setText(table.getValueAt(row, 1).toString());
		txtLoai.setText(table.getValueAt(row, 2).toString());
		if (tableModel.getValueAt(row, 3).toString().equalsIgnoreCase("Trống")) {
			radTrong.setSelected(true);
			radDaDat.setSelected(false);
		} else {
			radTrong.setSelected(false);
			radDaDat.setSelected(true);
		}
		txtMoTa.setText(table.getValueAt(row, 4).toString());
		txtGiaPhong.setText(table.getValueAt(row, 5).toString());
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