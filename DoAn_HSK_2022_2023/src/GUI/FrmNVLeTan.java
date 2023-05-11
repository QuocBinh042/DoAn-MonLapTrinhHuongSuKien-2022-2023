package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

import DAO.DAOLeTanData;
import Entity.LeTanData;


public class FrmNVLeTan extends JFrame implements MenuListener {
	private FrmPhieuDatPhong frmPhieuDatPhong = new FrmPhieuDatPhong();
	private FrmHoaDonThanhToan frmHoaDonThanhToan = new FrmHoaDonThanhToan();
	private FrmPhong frmPhong = new FrmPhong();
	private FrmKhachHang frmKhachHang = new FrmKhachHang();
	private FrmDichVu frmDichVu = new FrmDichVu();
	private FrmHoaDonDichVuPhong frmHDDVP = new FrmHoaDonDichVuPhong();
	private DefaultTableModel tableModel;
	private JTable table;
	private JMenuBar mnubar = new JMenuBar();
	private JMenu mnuPhong, mnuKhachHang, mnuPhieuDatPhong, mnuHoaDonThanhToan,mnuHoaDonDichVuPhong, mnunDichVu, mnuTrangChu, mnuThoat;
	private JList<String> myList;
	private DAOLeTanData DAO_LeTanData;
	
	public FrmNVLeTan() {
		setTitle("QUẢN LÝ THÔNG TIN ĐẶT PHÒNG KHÁCH SẠN");
		setSize(1350, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		createGUI();
	}

	private void createGUI() {
//		JPanel pnlNorth;
//		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
//		JLabel lblTilte;
//		pnlNorth.add(lblTilte = new JLabel("QUẢN LÝ THÔNG TIN ĐẶT PHÒNG KHÁCH SẠN"));
//		lblTilte.setFont(new Font("Arial", Font.BOLD, 20));
//		lblTilte.setForeground(Color.BLUE);

		setJMenuBar(mnubar);
		mnubar.add(mnuTrangChu = new JMenu("Trang chủ"));
		mnubar.add(mnuPhong = new JMenu("Phòng"));
		mnubar.add(mnuTrangChu = new JMenu(""));
		mnubar.add(mnuKhachHang = new JMenu("Khách hàng"));
		mnubar.add(mnuTrangChu = new JMenu(""));
		mnubar.add(mnunDichVu = new JMenu("Dịch vụ"));
		mnubar.add(mnuTrangChu = new JMenu(""));
		mnubar.add(mnuPhieuDatPhong = new JMenu("Phiếu đặt phòng"));
		mnubar.add(mnuTrangChu = new JMenu(""));
		mnubar.add(mnuHoaDonDichVuPhong = new JMenu("Hoá đơn dịch vụ phòng"));
		mnubar.add(mnuTrangChu = new JMenu(""));
		mnubar.add(mnuHoaDonThanhToan = new JMenu("Hoá đơn thanh toán"));
		mnubar.add(mnuTrangChu = new JMenu(""));
		mnubar.add(mnuThoat = new JMenu("Thoát"));
		JPanel pnlWest;
		DefaultListModel<String> model = new DefaultListModel<>();
        // add element to model
        model.addElement("Danh sách checkin");
        model.addElement("Danh sách checkout");
        // set model to JList
        myList = new JList<String>(model);
        add(pnlWest = new JPanel(), BorderLayout.WEST);
        pnlWest.setBorder(new EmptyBorder(0, 10, 10, 10));
        pnlWest.setBorder(BorderFactory.createTitledBorder("Danh sách"));
        pnlWest.add(myList);
       
		Box bb = Box.createVerticalBox();
		bb.setBorder(BorderFactory.createEtchedBorder());
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createVerticalBox();
		Box b = Box.createHorizontalBox();
		bb.add(b1);
		add(bb, BorderLayout.CENTER);

		bb.add(Box.createVerticalStrut(10));
		bb.add(b);
		String[] headers = "Mã đặt phòng; Họ tên khách hàng; Tên phòng; Tình trạng ở; Ngày đến; Ngày đi; Ghi chú".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		b.add(scroll);
		bb.add(b);
		
		mnuPhong.addMenuListener(this);
		mnuKhachHang.addMenuListener(this);
		mnunDichVu.addMenuListener(this);
		mnuHoaDonDichVuPhong.addMenuListener(this);
		mnuPhieuDatPhong.addMenuListener(this);
		mnuHoaDonThanhToan.addMenuListener(this);
		mnuThoat.addMenuListener(this);
		
		//Jlist
		myList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			  public void valueChanged(ListSelectionEvent evt) {
			    if (!evt.getValueIsAdjusting()) {
			    	JList source = (JList)evt.getSource();
		            String selected = source.getSelectedValue().toString();
		            if(selected.equalsIgnoreCase("Danh sách checkin")) {
		            	//test item danh sach checkin
		            	DanhSachCheckIn();
		            }else if(selected.equalsIgnoreCase("Danh sách checkout")){
		            	//test item danh sach checkout
		            	DanhSachCheckOut();
		            }
			    }
			  }
			});
	}

	public static void main(String[] args) {
		new FrmNVLeTan().setVisible(true);
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(mnuPhong)) {
			frmPhong.setVisible(true);
		}
		if (o.equals(mnuKhachHang)) {
			frmKhachHang.setVisible(true);
		}
		if (o.equals(mnunDichVu)) {
			frmDichVu.setVisible(true);
		}
		if (o.equals(mnuPhieuDatPhong)) {
			frmPhieuDatPhong.setVisible(true);
		}
		if (o.equals(mnuHoaDonDichVuPhong)) {
			frmHDDVP.setVisible(true);
		}
		if (o.equals(mnuHoaDonThanhToan)) {
			frmHoaDonThanhToan.setVisible(true);
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
	public void DanhSachCheckIn() {
		// delete all
		deleteAllDataJtable();
		// Load data
		DAO_LeTanData = new DAOLeTanData();
		for (LeTanData d : DAO_LeTanData.dsCheckIn()) {
			Object row[] = { d.getMaDatPhong(), d.getHoTenKhachHang(), d.getTenPhong(), d.getTinhTrang(),
					d.getNgayDen(), d.getNgayDi(), d.getGhiChu() };
			tableModel.addRow(row);
		}
	}
	public void DanhSachCheckOut() {
		// delete all
		deleteAllDataJtable();
		// Load data
		DAO_LeTanData = new DAOLeTanData();
		for (LeTanData d : DAO_LeTanData.dsCheckOut()) {
			Object row[] = { d.getMaDatPhong(), d.getHoTenKhachHang(), d.getTenPhong(), d.getTinhTrang(),
					d.getNgayDen(), d.getNgayDi(), d.getGhiChu() };
			tableModel.addRow(row);
		}
	}
	private void deleteAllDataJtable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}
	}
}
