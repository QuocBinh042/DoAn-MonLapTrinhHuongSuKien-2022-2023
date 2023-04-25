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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DanhSach.DanhSachDichVu;
import Entity.DichVu;

public class FrmDichVu extends JFrame implements ActionListener, MouseListener {
	private JTable table;
	private JTextField txtMaDichVu, txtTenDichVu, txtLoaiDichVu, txtGiaDichVu, txtTimMa, txtTimTen, txtMess;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTimMa, btnTimTen, btnSua, btnThoat;
	private DefaultTableModel tableModel;
	private DanhSachDichVu ds = new DanhSachDichVu();

	public FrmDichVu() {
		createGUI();
	}

	private void createGUI() {
		// TODO Auto-generated method stub
		setTitle("Quản lý dịch vụ");
		setSize(1000, 650);
		setResizable(false);
		setLocationRelativeTo(null);

		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("QUẢN LÝ DỊCH VỤ");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.red);
		pnlNorth.add(lblTieuDe);

		Box b = Box.createVerticalBox();
		Box bb = Box.createHorizontalBox();
		Box b1, b2, b3, b4, b5, b6, b7, bTimMa, bTimTen;
		JLabel lblMaDichVu, lblLoaiDichVu, lblTenDichVu, lblGiaDichVu;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.setBorder(BorderFactory.createTitledBorder("Dịch vụ"));
		b1.add(lblMaDichVu = new JLabel("Mã dịch vụ: "));
		b1.add(txtMaDichVu = new JTextField(20));

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblTenDichVu = new JLabel("Tên dịch vụ: "));
		b2.add(txtTenDichVu = new JTextField(20));

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblLoaiDichVu = new JLabel("Loại dịch vụ: "));
		b3.add(txtLoaiDichVu = new JTextField(20));

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblGiaDichVu = new JLabel("Giá dịch vụ: "));
		b4.add(txtGiaDichVu = new JTextField(20));

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

		b.add(Box.createVerticalStrut(70));
		b.add(b7 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(50));
		b7.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		b7.add(Box.createVerticalStrut(10));
		b7.add(bTimMa = Box.createHorizontalBox());
		bTimMa.add(new JLabel("Mã dịch vụ:  "));
		bTimMa.add(txtTimMa = new JTextField(10));
		bTimMa.add(btnTimMa = new JButton("Tìm"));
		b7.add(Box.createVerticalStrut(20));
		b7.add(bTimTen = Box.createHorizontalBox());
		bTimTen.add(new JLabel("Tên dịch vụ: "));
		bTimTen.add(txtTimTen = new JTextField(10));
		bTimTen.add(btnTimTen = new JButton("Tìm"));
		b7.add(Box.createVerticalStrut(10));
		b.add(Box.createVerticalStrut(200));
		add(bb, BorderLayout.CENTER);

		lblMaDichVu.setPreferredSize(lblLoaiDichVu.getPreferredSize());
		lblGiaDichVu.setPreferredSize(lblLoaiDichVu.getPreferredSize());
		lblTenDichVu.setPreferredSize(lblLoaiDichVu.getPreferredSize());

		add(b, BorderLayout.WEST);
		b.add(Box.createVerticalGlue());
		add(bb, BorderLayout.CENTER);

		bb.setBorder(BorderFactory.createTitledBorder("Danh sách dịch vụ"));
		String[] headers = "Mã dịch vụ;Tên dịch vụ;Loại dịch vụ;Giá dịch vụ;".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bb.add(scroll);

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

	public static void main(String[] args) {
		new FrmDichVu().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themPhieuDatPhong();
		} else if (o.equals(btnSua)) {
			SuaDichVu();
		} else if (o.equals(btnXoaTrang))
			xoaTrang();
		else if (o.equals(btnXoa)) {
			try {
				XoaDichVu();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnTimMa)) {
			TimDichVu();
		} else if (o.equals(btnLuu)) {
			Luu();
		} else if (o.equals(btnThoat))
			System.exit(0);
	}

	private void Luu() {
		// TODO Auto-generated method stub
		try {
			JOptionPane.showMessageDialog(this, "Lưu thành công");

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}

	private void SuaDichVu() {
		// TODO Auto-generated method stub
		String maDV = txtMaDichVu.getText();
		String tenDichVu = txtTenDichVu.getText();
		String loaiDichVu = txtLoaiDichVu.getText();
		Double giaDichVu = Double.valueOf(txtGiaDichVu.getText());
		DichVu dv = new DichVu(loaiDichVu, tenDichVu, loaiDichVu, giaDichVu);
		if (ds.suaDichVu(dv)) {
			int index = ds.getList().indexOf(dv);
			tableModel.setValueAt(tenDichVu, index, 1);
			tableModel.setValueAt(loaiDichVu, index, 2);
			tableModel.setValueAt(giaDichVu, index, 3);
			JOptionPane.showMessageDialog(null, "Sửa thành công");
		}
	}

	private void TimDichVu() {
		// TODO Auto-generated method stub
		int pos = ds.timDichVuTheoMa(txtTimMa.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Tồn tại dịch vụ có mã số này");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Không tồn tại dịch vụ có mã số này");
	}

	private void XoaDichVu() throws Exception {
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá không", "Chú ý", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				ds.xoaDichVu(r);
				tableModel.removeRow(r);
				JOptionPane.showMessageDialog(null, "Xoá thành công!");
				xoaTrang();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ muốn xoá!");
		}
	}

	private void themPhieuDatPhong() {
		// TODO Auto-generated method stub
		try {
			String maDV = txtMaDichVu.getText();
			String tenDichVu = txtTenDichVu.getText();
			String loaiDichVu = txtLoaiDichVu.getText();
			Double giaDichVu = Double.valueOf(txtGiaDichVu.getText());
			DichVu dv = new DichVu(loaiDichVu, tenDichVu, loaiDichVu, giaDichVu);

			if (ds.themDichVu(dv)) {
				String[] row = { loaiDichVu, tenDichVu, loaiDichVu, String.valueOf(giaDichVu) };
				tableModel.addRow(row);
				xoaTrang();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã dịch vụ");
				txtMaDichVu.selectAll();
				txtMaDichVu.requestFocus();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi nhập liệu");
			return;
		}
	}

	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaDichVu.setText("");
		txtLoaiDichVu.setText("");
		txtGiaDichVu.setText("");
		txtTenDichVu.setText("");
		txtTimMa.setText("");
		txtMaDichVu.setEnabled(true);
		txtMaDichVu.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaDichVu.setEnabled(false);
		txtMaDichVu.setText(table.getValueAt(row, 0).toString());
		txtTenDichVu.setText(table.getValueAt(row, 1).toString());
		txtLoaiDichVu.setText(table.getValueAt(row, 2).toString());
		txtGiaDichVu.setText(table.getValueAt(row, 3).toString());

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
