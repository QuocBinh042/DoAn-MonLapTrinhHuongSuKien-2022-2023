package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmDichVu extends JFrame{
	private JTable table;
	private JTextField txtMaDichVu, txtTenDichVu, txtLoaiDichVu, txtGiaDichVu, txtTim;
	private JButton btnThem, btnXoa, btnXoaTrang, btnLuu, btnTim, btnSua, btnThoat;
	private DefaultTableModel tableModel;
	public FrmDichVu() {
		createGUI();
	}
	
	private void createGUI() {
		// TODO Auto-generated method stub
		setTitle("Quản lý dịch vụ");
		setSize(1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// NORTH
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		JLabel lblTieuDe = new JLabel("QUẢN LÝ DỊCH VỤ");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.blue);
		pnlNorth.add(lblTieuDe);
		
		Box b = Box.createVerticalBox();
		Box bb = Box.createHorizontalBox();
		Box b1, b2, b3, b4, b5;
		JLabel lblMaDichVu, lblLoaiDichVu, lblTenDichVu, lblGiaDichVu;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMaDichVu = new JLabel("Mã dịch vụ: "));
		b1.add(txtMaDichVu = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblTenDichVu = new JLabel("Tên dịch vụ: "));
		b2.add(txtTenDichVu = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblLoaiDichVu = new JLabel("Loại dịch vụ: "));
		b3.add(txtLoaiDichVu = new JTextField());

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblGiaDichVu = new JLabel("Giá dịch vụ: "));
		b4.add(txtGiaDichVu = new JTextField());

//		b.add(b5 = Box.createHorizontalBox());
//		b.add(Box.createVerticalStrut(10));
//		b5.add(txtMess = new JTextField());
//		txtMess.setEditable(false);
//		txtMess.setBorder(null);
//		txtMess.setForeground(Color.red);
//		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));
		lblMaDichVu.setPreferredSize(lblLoaiDichVu.getPreferredSize());
		lblGiaDichVu.setPreferredSize(lblLoaiDichVu.getPreferredSize());
		lblTenDichVu.setPreferredSize(lblLoaiDichVu.getPreferredSize());
		
		bb.add(b);
		add(bb, BorderLayout.CENTER);

		bb.add(b5 = Box.createVerticalBox());
		bb.add(Box.createVerticalStrut(10));
		String[] headers = "Mã dịch vụ;Tên dịch vụ;Loại dịch vụ;Giá dịch vụ"
				.split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(20);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		b5.add(scroll);
	}

	public static void main(String[] args) {
		new FrmDichVu().setVisible(true);
	}
}
