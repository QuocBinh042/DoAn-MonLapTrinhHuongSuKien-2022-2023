package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

public class FrmQuanLy extends JFrame implements MenuListener {
	JButton btnDsNhanVien, btnThongKe, btnThoat;
	private FrmNhanVien guiNhanVien = new FrmNhanVien();
	private FrmThongKe frmTK = new FrmThongKe();
	private JMenuBar mnubar = new JMenuBar();
	private JMenu mnuTrangChu, mnuDSNhanVien, mnuThongKe, mnuThoat;
	private DefaultTableModel tableModel;
	private JTable table;
	private JList<String> myList;

	public FrmQuanLy() {
		setTitle("GIAO DIỆN CỦA QUẢN LÝ");
		setSize(1350, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		createGUI();
	}

	private void createGUI() {
		setJMenuBar(mnubar);
		mnubar.add(mnuTrangChu = new JMenu("Trang chủ"));
		mnubar.add(mnuTrangChu = new JMenu(""));
		mnubar.add(mnuDSNhanVien = new JMenu("Quản lý nhân viên"));
		mnubar.add(mnuTrangChu = new JMenu(""));
		mnubar.add(mnuThongKe = new JMenu("Thống kê doanh thu"));
		mnubar.add(mnuTrangChu = new JMenu(""));
		mnubar.add(mnuThoat = new JMenu("Thoát"));

		JPanel pnlWest;
		DefaultListModel<String> model = new DefaultListModel<>();
		// add element to model
		model.addElement("Danh sách nhân viên");
		model.addElement("Danh sách hóa đơn thanh toán");
		// set model to JList
		myList = new JList<String>(model);
		add(pnlWest = new JPanel(), BorderLayout.WEST);
		pnlWest.setBorder(new EmptyBorder(0, 10, 10, 10));
		pnlWest.setBorder(BorderFactory.createTitledBorder("Danh sách"));
		pnlWest.add(myList);

//		Box bb = Box.createVerticalBox();
//		bb.setBorder(BorderFactory.createEtchedBorder());
//		Box b1 = Box.createHorizontalBox();
//		Box b2 = Box.createVerticalBox();
//		Box b = Box.createHorizontalBox();
//		bb.add(b1);
//		add(bb, BorderLayout.CENTER);
//		bb.add(Box.createVerticalStrut(10));
//		bb.add(b);
//		String[] headers = "Mã nhân viên; Họ tên; Chứng minh thư; SĐT; Gmail; Địa chỉ; Giới tính;Chức vụ;Mật khẩu"
//				.split(";");
////		String[] headers1 = "Mã hóa đơn; Mã đặt phòng; Ngày thanh toán; Hình thức thanh toán; Thành tiền; Tổng thanh toán; Ghi chú".split(";");
//		tableModel = new DefaultTableModel(headers, 0);
//		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		scroll.setViewportView(table = new JTable(tableModel));
//		table.setRowHeight(25);
//		table.setAutoCreateRowSorter(true);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		b.add(scroll);
//		bb.add(b);

//		JPanel contentPane = new JPanel();
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		contentPane.add(btnDsNhanVien = new JButton("QUẢN LÝ NHÂN VIÊN"));
//		btnDsNhanVien.setFont(new Font("Arial", Font.BOLD, 20));
//		btnDsNhanVien.setBounds(250, 50, 300, 50);
//		contentPane.add(btnThongKe = new JButton("THỐNG KÊ DOANH THU"));
//		btnThongKe.setFont(new Font("Arial", Font.BOLD, 20));
//
//		btnThongKe.setBounds(250, 150, 300, 50);
//		contentPane.add(btnThoat = new JButton("THOÁT"));
//		btnThoat.setFont(new Font("Arial", Font.BOLD, 20));
//		btnThoat.setForeground(Color.WHITE);
//		btnThoat.setBackground(Color.ORANGE);
//		btnThoat.setBounds(250, 250, 300, 50);
//
//		btnThoat.addActionListener(this);
//		btnDsNhanVien.addActionListener(this);
//		btnThongKe.addActionListener(this);
		mnuDSNhanVien.addMenuListener(this);
		mnuThongKe.addMenuListener(this);
		mnuThoat.addMenuListener(this);

		//
		// Jlist
		myList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (!evt.getValueIsAdjusting()) {
					JList source = (JList) evt.getSource();
					String selected = source.getSelectedValue().toString();
					if (selected.equalsIgnoreCase("Danh sách nhân viên")) {
						// DS nhan vien
					} else if (selected.equalsIgnoreCase("Danh sách hóa đơn thanh toán")) {
						// DS hoa don
					}
				}
			}
		});

	}

	public static void main(String[] args) {
		new FrmQuanLy().setVisible(true);
	}

	@Override
	public void menuSelected(MenuEvent e) {
		Object o = e.getSource();
		if (o.equals(mnuDSNhanVien)) {
			guiNhanVien.setVisible(true);
		}
		if (o.equals(mnuThongKe)) {
			frmTK.setVisible(true);
		}
		if (o.equals(mnuThoat)) {
			System.exit(0);
		}

	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}

}
