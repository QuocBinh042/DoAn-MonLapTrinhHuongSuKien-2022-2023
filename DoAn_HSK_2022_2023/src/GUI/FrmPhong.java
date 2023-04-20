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
import Entity.Phong;

public class FrmPhong extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private DanhSachPhong ds;
	private JTable table;
	private JTextField txtMaPhong, txtTen, txtLoai, txtGiaPhong, txtMoTa, txtTim;
	private JRadioButton radTrong, radDaDat;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnThoat;
	private DefaultTableModel tableModel;

	public FrmPhong() {
		ds = new DanhSachPhong();
		createGUI();
	}

	public static void main(String[] args) {
		new FrmPhong().setVisible(true);
	}

	public void createGUI() {
		setTitle("Quản lý phòng");
		setSize(1000, 700);
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
		Box b = Box.createVerticalBox(), bb = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5;
		JLabel lblMaPhong, lblTen, lblLoai, lblGiaPhong, lblMoTa, lblTinhTrang;
	
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
		b3.add(lblTinhTrang = new JLabel("Tình trạng: "));

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblGiaPhong = new JLabel("Giá phòng: "));
		b4.add(txtGiaPhong = new JTextField());

		ButtonGroup bg = new ButtonGroup();
		radTrong = new JRadioButton("Trống");
		radDaDat = new JRadioButton("Đã đặt");
		bg.add(radTrong);
		bg.add(radDaDat);
		b3.add(radTrong);
		b3.add(radDaDat);

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		b4.add(lblMoTa = new JLabel("Mô tả: "));
		b4.add(txtMoTa = new JTextField());

		lblMaPhong.setPreferredSize(lblLoai.getPreferredSize());
		lblTen.setPreferredSize(lblLoai.getPreferredSize());
		lblMoTa.setPreferredSize(lblLoai.getPreferredSize());
		lblGiaPhong.setPreferredSize(lblLoai.getPreferredSize());
		
		
		b.add(b5 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(15));
		
		String[] headers = "Mã phòng;Tên;Loại phòng;Tình trạng; Giá phòng; Mô tả".split(";");
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
		txtMaPhong.setEnabled(true);
	}

	private void timActions() {
		// TODO Auto-generated method stub
		int pos = ds.timPhong(txtTim.getText());
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
		try {

			String maP = txtMaPhong.getText();
			String tenP = txtTen.getText();
			String loaiP = txtLoai.getText();
			double giaP = Double.parseDouble(txtGiaPhong.getText());
			String moTa = txtMoTa.getText();
			String tinhTrang = "";

			if (radTrong.isSelected())
				tinhTrang = radTrong.getText();
			if (radDaDat.isSelected())
				tinhTrang = radDaDat.getText();
			Phong ph = new Phong(maP, tenP, loaiP, tinhTrang, giaP, moTa);
			if (ds.themPhong(ph)) {
				String[] row = { maP, tenP, loaiP, tinhTrang, Double.toString(giaP) + "", moTa };
				tableModel.addRow(row);
				xoaTrangActions();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã phòng");
				txtMaPhong.selectAll();
				txtMaPhong.requestFocus();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			return;
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