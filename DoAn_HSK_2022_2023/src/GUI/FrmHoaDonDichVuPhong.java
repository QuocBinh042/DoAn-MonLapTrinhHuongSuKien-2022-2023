package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DanhSach.DanhSachHoaDonDichVuPhong;
import Entity.HoaDonDichVuPhong;

public class FrmHoaDonDichVuPhong extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtMaDichVu, txtMaPhong, txtSoLuong, txtThanhTienDichVu, txtMess;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnSua, btnThoat;
	private DefaultTableModel tableModel;
	private DanhSachHoaDonDichVuPhong ds;

	public FrmHoaDonDichVuPhong() {
		createGUI();
	}

	private void createGUI() {
		// TODO Auto-generated method stub
		setTitle("QUẢN LÝ HÓA ĐƠN DỊCH VỤ PHÒNG");
		setSize(1000, 650);
		setResizable(false);
		setLocationRelativeTo(null);

		ds = new DanhSachHoaDonDichVuPhong();
		
		
		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("QUẢN LÝ DỊCH VỤ");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.red);
		pnlNorth.add(lblTieuDe);

		Box b = Box.createVerticalBox();
		Box bb = Box.createHorizontalBox();
		Box b1, b2, b3, b5, b6;
		JLabel lblMaDichVu, lblMaPhong, lblSoLuong;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.setBorder(BorderFactory.createTitledBorder("Dịch vụ phòng"));
		b1.add(lblMaPhong = new JLabel("Mã phòng: "));
		b1.add(txtMaPhong = new JTextField(20));

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblMaDichVu = new JLabel("Mã dịch vụ: "));
		b2.add(txtMaDichVu = new JTextField(20));
		
		
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblSoLuong = new JLabel("Số lượng: "));
		b3.add(txtSoLuong = new JTextField(20));

		b.add(Box.createHorizontalStrut(5));
		b.add(b5 = Box.createHorizontalBox());
		b5.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));
		b.add(Box.createVerticalStrut(10));
		b.add(Box.createHorizontalStrut(100));

		b.add(b6 = Box.createHorizontalBox());
		b6.add(Box.createHorizontalStrut(75));
		b6.add(btnThem = new JButton("Thêm"));
		b6.add(Box.createHorizontalStrut(10));
		b6.add(btnSua = new JButton("Sửa"));
		b6.add(Box.createHorizontalStrut(10));
		b6.add(btnXoaTrang = new JButton("Xoá trắng"));

		b.add(Box.createVerticalStrut(300));
		add(bb, BorderLayout.CENTER);

		lblMaPhong.setPreferredSize(lblMaDichVu.getPreferredSize());
		lblSoLuong.setPreferredSize(lblMaDichVu.getPreferredSize());

		add(b, BorderLayout.WEST);
		b.add(Box.createVerticalGlue());
		add(bb, BorderLayout.CENTER);

		bb.setBorder(BorderFactory.createTitledBorder("Danh sách dịch vụ phòng"));
		String[] headers = "Mã phòng;Mã dịch vụ;Số lượng;Giá;Thành tiền".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bb.add(scroll);

		//South
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

		TXTedit_false();
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
		table.addMouseListener(this);
	}

	public static void main(String[] args) {
		new FrmHoaDonDichVuPhong().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			TXTedit_true();
		} else if (o.equals(btnSua)) {
//			TXTedit_true();
//			btnSua.setText("Huỷ");
//			if(btnSua.getText().equals("Hoàn tất")) {
//				//SuaDichVu();
//				System.out.println("done");
//			}
		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnXoa)) {
			try {
				XoaDichVu();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnLuu)) {
			saveData();
		} else if (o.equals(btnThoat))
			System.exit(0);
	}
	private void TXTedit_false() {
		txtMaDichVu.setEditable(false);
		txtMaPhong.setEditable(false);
		txtSoLuong.setEditable(false);
	}
	private void TXTedit_true() {
		txtMaDichVu.setEditable(true);
		txtMaPhong.setEditable(true);
		txtSoLuong.setEditable(true);
	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}

	private void SuaDichVu() {
		int r = table.getSelectedRow();
		if (r != -1) {	
			String maPhong = txtMaPhong.getText();
			String maDichVu = txtMaDichVu.getText();
			String soLuong = txtSoLuong.getText();
			String thanhTien = txtThanhTienDichVu.getText();
			HoaDonDichVuPhong dvp = new HoaDonDichVuPhong(maPhong, maDichVu, Integer.parseInt(soLuong), Float.parseFloat(thanhTien));
			if (ds.suaDichVu(dvp)) {
				int index = ds.getList().indexOf(dvp);
				tableModel.setValueAt(maPhong, index, 1);
				tableModel.setValueAt(maDichVu, index, 2);
				tableModel.setValueAt(soLuong, index, 3);
				JOptionPane.showMessageDialog(null, "Sửa thành công");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ phòng muốn xoá!");
		}
		// TODO Auto-generated method stub
		
	}

	private void XoaDichVu() throws Exception {
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá không", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaDichVuPhong(r);
				tableModel.removeRow(r);
				JOptionPane.showMessageDialog(null, "Xoá thành công!");
				xoaTrang();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ phòng muốn xoá!");
		}
	}

	private void saveData() {
		// TODO Auto-generated method stub
		String maPhong = txtMaPhong.getText();
		String maDichVu = txtMaDichVu.getText();
		String soLuong = txtSoLuong.getText();
		float thanhTien = Integer.parseInt(soLuong) * 3000;
		float gia = 3000;
		try {
			HoaDonDichVuPhong dvp = new HoaDonDichVuPhong(maPhong, maDichVu, Integer.parseInt(soLuong), thanhTien);
			
			if (ds.themDichVuPhong(dvp)) {
				String[] row = {maPhong,maDichVu,soLuong,String.valueOf(gia),String.valueOf(thanhTien)};
				tableModel.addRow(row);
				xoaTrang();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			
		}
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaDichVu.setText("");
		txtMaPhong.setText("");
		txtSoLuong.setText("");
		txtMaDichVu.setEnabled(true);
		txtMaDichVu.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		TXTedit_false();
		txtMaDichVu.setText(table.getValueAt(row, 0).toString());
		txtMaPhong.setText(table.getValueAt(row, 1).toString());
		txtSoLuong.setText(table.getValueAt(row, 2).toString());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
}
