package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAO.DAOThongKe;
import DanhSach.DanhSachThongKe;
import Entity.ThongKe;
import connectDB.ConnectDB;

public class FrmThongKe extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel borderPanel,panelNorth,panelCenter,panelSouth;
	private JDateChooser dateStart, dateEnd;
	private JLabel lblStart, lblEnd, lblTongTien, lblValue, lblError;
	private JButton btnTim;
	private JTable table;
	private DefaultTableModel tableModel;
	private DecimalFormat fmt = new DecimalFormat("###,###");
	private DanhSachThongKe ds;
	private DAOThongKe DAO_TK;
	public FrmThongKe(){
		setTitle("THỐNG KÊ DOANH THU");
		setSize(1350, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		ds = new DanhSachThongKe();
		DAO_TK = new DAOThongKe();
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
		
		//Border Panel
		borderPanel = new JPanel();
		borderPanel.setLayout(new BorderLayout());
		
		// NORTH
		panelNorth = new JPanel();
		JLabel lblTieuDe = new JLabel("THỐNG KÊ DOANH THU");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblTieuDe.setForeground(Color.red);
		panelNorth.add(lblTieuDe);
		borderPanel.add(panelNorth, BorderLayout.NORTH);
		
		//CENTER
		panelCenter = new JPanel();
		panelCenter.setBorder(BorderFactory.createTitledBorder(""));
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		
		Box b = Box.createVerticalBox();
		Box b2 = Box.createHorizontalBox();
		b.add(Box.createVerticalStrut(5));
		Box b3 = Box.createHorizontalBox();
		b.add(Box.createVerticalStrut(5));
		
		b.add(b2);
		b.add(b3);
		
		b2.add(lblStart = new JLabel("Từ ngày:"));
		b2.add(Box.createRigidArea(new Dimension(5,0)));
		b2.add(dateStart = new JDateChooser());
		b2.add(Box.createRigidArea(new Dimension(50,0)));
		b2.add(lblEnd = new JLabel("Đến ngày:"));
		b2.add(Box.createRigidArea(new Dimension(5,0)));
		b2.add(dateEnd = new JDateChooser());
		b2.add(Box.createRigidArea(new Dimension(70,0)));
		b2.add(btnTim = new JButton("Tìm"));
		b2.add(Box.createRigidArea(new Dimension(500,0)));
		
		b3.add(lblError = new JLabel("(*)"));
		lblError.setForeground(Color.red);
		b3.add(Box.createGlue());
		panelCenter.add(b);
		//create table
		taoBang();
		loadData();
		borderPanel.add(panelCenter, BorderLayout.CENTER);
		
		//SOUTH
		panelSouth = new JPanel();
		panelSouth.add(Box.createRigidArea(new Dimension(600,0)));
		panelSouth.add(lblTongTien = new JLabel("Tổng doanh thu: "));
		panelSouth.add(lblValue = new JLabel("......"));
		lblTongTien.setFont(new Font("Arial", Font.BOLD, 15));
		lblValue.setFont(new Font("Arial", Font.BOLD, 15));
		lblTongTien.setForeground(Color.red);
		
		panelSouth.setPreferredSize(new Dimension(1350, 30));
		borderPanel.add(panelSouth,BorderLayout.SOUTH);

		this.add(borderPanel);
		btnTim.addActionListener(this);
	}

	public static void main(String[] args){
		new FrmThongKe().setVisible(true);
	}
	public void taoBang() {
		String[] headers = "Mã phòng;Tiền Dịch vụ; Tiền phòng; Tổng tiền".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scroll.setPreferredSize(new Dimension(1350,550));
		panelCenter.add(scroll);
	}
	public void deleteAllDataJtable() {
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		while(dm.getRowCount() > 0)
		{
		    dm.removeRow(0);
		}
	}
	public void display(String start, String end) {
		double sum = 0;
		ds.clear();
		for(ThongKe tk:DAO_TK.getAll(start,end)) {
			if(ds.them(tk)) {	
				sum+=tk.getTongTien();
				Object row[] = {tk.getMaPhong(),fmt.format(tk.getTienDV()),fmt.format(tk.getTienPhong()),fmt.format(tk.getTongTien())};
				tableModel.addRow(row);
			}
		}
		lblValue.setText(fmt.format(sum)+" VND");
	}
	public void loadData() {
		//delete all
		DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
		deleteAllDataJtable();
		if(dateStart.getDate() == null) {
			lblError.setText("(*) Vui lòng nhập ngày bắt đầu!");
		}
		else {
			String batDau = dfm.format(dateStart.getDate());
			lblError.setText("(*)");
			if(dateEnd.getDate() == null) {
				Calendar cal = Calendar.getInstance();
				String current_date = dfm.format(cal.getTime());
				display(batDau, current_date);
			}else {
				String ketThuc = dfm.format(dateEnd.getDate());
				display(batDau, ketThuc);
			}
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnTim)) {
			loadData();
		}
	}
	

	
}
