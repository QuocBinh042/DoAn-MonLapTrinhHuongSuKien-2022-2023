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
import DAO.DAODichVu;
import DAO.DAOKhachHang;
import Entity.DichVu;
import Entity.KhachHang;
import connectDB.ConnectDB;

public class FrmDichVu extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtMaDichVu, txtTenDichVu, txtGiaDichVu, txtTimMa, txtTimTen, txtMess;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTimMa, btnTimTen, btnSua, btnThoat;
	private DefaultTableModel tableModel;
	private DanhSachDichVu dsDV = new DanhSachDichVu();
	private DAODichVu DAO_DV;

	public FrmDichVu() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createGUI();
	}

	private void createGUI() {
		// TODO Auto-generated method stub
		setTitle("Quản lý dịch vụ");
		setSize(1350, 700);
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
		Box b1, b2, b3, b4, b5, b6, bTimMa, bTimTen;
		JLabel lblMaDichVu, lblTenDichVu, lblGiaDichVu;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.setBorder(BorderFactory.createTitledBorder("Dịch vụ phòng"));
		b1.add(lblMaDichVu = new JLabel("Mã dịch vụ: "));
		b1.add(txtMaDichVu = new JTextField(20));

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblTenDichVu = new JLabel("Tên dịch vụ: "));
		b2.add(txtTenDichVu = new JTextField(20));

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblGiaDichVu = new JLabel("Giá dịch vụ: "));
		b3.add(txtGiaDichVu = new JTextField(20));

		b.add(Box.createHorizontalStrut(5));
		b.add(b4 = Box.createHorizontalBox());
		b4.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));
		b.add(Box.createVerticalStrut(10));
		b.add(Box.createHorizontalStrut(100));

		b.add(b5 = Box.createHorizontalBox());
		b5.add(Box.createHorizontalStrut(75));
		b5.add(btnThem = new JButton("Thêm"));
		b5.add(Box.createHorizontalStrut(10));
		b5.add(btnSua = new JButton("Sửa"));
		b5.add(Box.createHorizontalStrut(10));
		b5.add(btnXoaTrang = new JButton("Xoá trắng"));

		b.add(Box.createVerticalStrut(70));
		b.add(b6 = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(50));
		b6.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		b6.add(Box.createVerticalStrut(10));
		b6.add(bTimMa = Box.createHorizontalBox());
		bTimMa.add(new JLabel("Mã dịch vụ:  "));
		bTimMa.add(txtTimMa = new JTextField(10));
		bTimMa.add(btnTimMa = new JButton("Tìm"));
		b6.add(Box.createVerticalStrut(20));
		b6.add(bTimTen = Box.createHorizontalBox());
		bTimTen.add(new JLabel("Tên dịch vụ: "));
		bTimTen.add(txtTimTen = new JTextField(10));
		bTimTen.add(btnTimTen = new JButton("Tìm"));
		b6.add(Box.createVerticalStrut(10));
		b.add(Box.createVerticalStrut(200));
		add(bb, BorderLayout.CENTER);

		lblMaDichVu.setPreferredSize(lblTenDichVu.getPreferredSize());
		lblGiaDichVu.setPreferredSize(lblTenDichVu.getPreferredSize());

		add(b, BorderLayout.WEST);
		b.add(Box.createVerticalGlue());
		add(bb, BorderLayout.CENTER);

		bb.setBorder(BorderFactory.createTitledBorder("Danh sách dịch vụ phòng"));
		String[] headers = "Mã dịch vụ;Tên dịch vụ;Giá dịch vụ".split(";");
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

		TXTedit_false();
		btnLuu.setEnabled(false);
		btnXoaTrang.setEnabled(false);
		
		//Gọi hàm loadData
		loadData();
		
		/**
		 * Đăng ký sự kiện
		 */
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
	
	private void TXTedit_false() {
		txtMaDichVu.setEditable(false);
		txtTenDichVu.setEditable(false);
		txtGiaDichVu.setEditable(false);
	}

	private void TXTedit_true() {
		txtMaDichVu.setEditable(true);
		txtTenDichVu.setEditable(true);
		txtGiaDichVu.setEditable(true);
	}
	
	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}
	
	private boolean validData() {
		String maDichVu = txtMaDichVu.getText().trim();
		String tenDichVu = txtTenDichVu.getText().trim();
		String giaDichVu = txtGiaDichVu.getText().trim();

		Pattern p1 = Pattern.compile("^(DV)[0-9]{3}$");
		if (!(maDichVu.length() > 0 && p1.matcher(maDichVu).find())) {
			showMessage("Lỗi mã dịch vụ", txtMaDichVu);
			return false;
		}
		Pattern p2 = Pattern.compile("^(.)+");
		if (!(tenDichVu.length() > 0 && p2.matcher(tenDichVu).find())) {
			showMessage("Lỗi tên dịch vụ", txtTenDichVu);
			return false;
		}
		Double gia = Double.valueOf(giaDichVu);
		if (!(giaDichVu.length() > 0 && (gia >= 0))) {
			showMessage("Lỗi giá dịch vụ", txtGiaDichVu);
			return false;
		}
		return true;
	}

	public void loadData() {
		// delete all
		deleteAllDataJtable();
		// Load data
		DAO_DV = new DAODichVu();
		for (DichVu dv : DAO_DV.getAll()) {
			Object row[] = { dv.getMaDichVu(), dv.getTenDichVu(), dv.getGiaDichVu() };
			tableModel.addRow(row);
		}
	}

	private void deleteAllDataJtable() {
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		while(dm.getRowCount() > 0)
		{
		    dm.removeRow(0);
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
			if(btnSua.getText().equals("Sửa")) {
				btnSua.setText("Hoàn tất");
				txtGiaDichVu.setEditable(true);
				txtTenDichVu.setEditable(true);
			}else {
				SuaDichVu();
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				TXTedit_false();
				xoaTrang();
				showMessage("", txtMess);
			}
		} else if (o.equals(btnXoaTrang))
			xoaTrang();
		else if (o.equals(btnXoa)) {
			try {
				XoaDichVu();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnTimMa)) {
			TimDichVuTheoMa();
		} else if (o.equals(btnTimTen)) {
			TimDichVuTheoTen();
		} else if (o.equals(btnLuu)) {
			themDichVu();
		} else if (o.equals(btnThoat))
			System.exit(0);
	}
	
	private void themDichVu() {
		// TODO Auto-generated method stub
		String maDichVu = txtMaDichVu.getText();
		String tenDichVu = txtTenDichVu.getText();
		Double giaDichVu = Double.valueOf(txtGiaDichVu.getText());
		DichVu dv = new DichVu(maDichVu, tenDichVu, giaDichVu);
		showMessage("", txtMess);
		if (validData()) {
			if (DAO_DV.add(dv)) {
				dsDV.themDichVu(dv);
				String[] row = { maDichVu, tenDichVu, String.valueOf(giaDichVu) };
				tableModel.addRow(row);
				xoaTrang();
				showMessage("Thêm dịch vụ thành công!", txtMess);
				JOptionPane.showMessageDialog(null, "Thêm dịch vụ thành công!");
			} else {
				JOptionPane.showMessageDialog(null, "Trùng mã dịch vụ!");
				txtMaDichVu.selectAll();
				txtMaDichVu.requestFocus();
			}
		} else {
			txtMaDichVu.selectAll();
			txtMaDichVu.requestFocus();
		}
	}
	
	private void TimDichVuTheoMa() {
		// TODO Auto-generated method stub
		int pos = dsDV.timDichVuTheoMa(txtTimMa.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Dịch vụ này có trong danh sách!");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Dịch vụ này không có trong danh sách!");
		txtTimTen.setText("");
		showMessage("", txtMess);
	}

	private void TimDichVuTheoTen() {
		// TODO Auto-generated method stub
		int pos = dsDV.timDichVuTheoTen(txtTimTen.getText());
		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Dịch vụ này có trong danh sách!");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Dịch vụ này không có trong danh sách!");
		txtTimMa.setText("");
		showMessage("", txtMess);
	}
	
	private void SuaDichVu() {
		int r = table.getSelectedRow();
		if (r != -1) {	
			String maDichVu = txtMaDichVu.getText();
			String tenDichVu = txtTenDichVu.getText();
			Double giaDichVu = Double.valueOf(txtGiaDichVu.getText());
			DichVu dv = new DichVu(maDichVu, tenDichVu, giaDichVu);
			if(DAO_DV.updateGia(dv)) {
				DAO_DV.updateThanhTienDVP(dv.getMaDichVu());
				dsDV.suaDichVu(dv);
				loadData();
				JOptionPane.showMessageDialog(null, "Cập nhật dịch vụ thành công!");
			}else {
				JOptionPane.showMessageDialog(null, "Không thành công! Vui lòng kiểm tra lại...");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ muốn sửa!");
		}
	}
	
	private void xoaTrang() {
		// TODO Auto-generated method stub
		txtMaDichVu.setText("");
		txtGiaDichVu.setText("");
		txtTenDichVu.setText("");
		txtTimMa.setText("");
		txtTimTen.setText("");
		txtMaDichVu.requestFocus();
	}

	private void XoaDichVu() throws Exception {
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Chắn chắn xoá dịch vụ này không!", "Chú ý!", JOptionPane.YES_NO_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				dsDV.xoaDichVu(r);
				DAO_DV.delete(table.getValueAt(r, 0).toString());
				tableModel.removeRow(r);
				JOptionPane.showMessageDialog(null, "Xoá dịch vụ thành công!");
				xoaTrang();
				showMessage("Xoá dịch vụ thành công!", txtMess);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ muốn xoá!");
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaDichVu.setText(table.getValueAt(row, 0).toString());
		txtTenDichVu.setText(table.getValueAt(row, 1).toString());
		txtGiaDichVu.setText(table.getValueAt(row, 2).toString());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
