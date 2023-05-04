package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrmDangNhap extends JFrame implements ActionListener {
	private JButton btnLeTan, btnQuanLy, btnThoat;
	private FrmNVLeTan frmLeTan = new FrmNVLeTan();
	private FrmQuanLy frmQuanLy = new FrmQuanLy();

	public FrmDangNhap() {
		setTitle("Đăng nhập");
		setSize(500, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createGUI();
	}

	public static void main(String[] args) {
		new FrmDangNhap().setVisible(true);
	}

	private void createGUI() {
		// TODO Auto-generated method stub
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(btnLeTan = new JButton("LỄ TÂN"));
		btnLeTan.setFont(new Font("Arial", Font.BOLD, 20));
		btnLeTan.setBounds(130, 50, 200, 50);
		contentPane.add(btnQuanLy = new JButton("QUẢN LÝ"));
		btnQuanLy.setFont(new Font("Arial", Font.BOLD, 20));

		btnQuanLy.setBounds(130, 150, 200, 50);
		contentPane.add(btnThoat = new JButton("THOÁT"));
		btnThoat.setFont(new Font("Arial", Font.BOLD, 20));
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setBackground(Color.RED);
		btnThoat.setBounds(130, 250, 200, 50);
		btnLeTan.addActionListener(this);
		btnQuanLy.addActionListener(this);
		btnThoat.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnLeTan)) {
			frmLeTan.setVisible(true);
		} else if (o.equals(btnQuanLy)) {
			frmQuanLy.setVisible(true);
		} else if (o.equals(btnThoat)) {
			System.exit(0);
		}

	}

}
