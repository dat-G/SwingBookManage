package com.lys.bms.frame;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.lys.bms.Login;
import com.lys.bms.dataTemplate.svg;
import com.lys.bms.jdbc.ConnectionManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;



import javax.swing.border.EtchedBorder;
import java.awt.*;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class settingPanel extends JPanel {
	private JTextField OriginalCode;
	private JTextField NewPassword;
	private JTextField jt1;
	private JTextField jt2;
	/**
	 * Create the panel.系统设置
	 */
	public settingPanel() throws IOException {
		try {
			UIManager.setLookAndFeel( new FlatIntelliJLaf() );
			UIManager.put( "TabbedPane.showTabSeparators", true );
		} catch( Exception ex ) {
			System.err.println( "Failed to initialize LaF" );
		}
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u7CFB\u7EDF\u8BBE\u7F6E\u6A21\u5757", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setSize(710, 460);
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 24, 690, 426);
		add(tabbedPane);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("买卖设置", svg.getSVGIcon("/svg/sliders.svg","#5555FF",20,20), panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new GridLayout(1,4,20,0));
		panel_4.setBounds(10, 20, 650, 30);
		panel_3.add(panel_4);

		JLabel lblNewLabel_4 = new JLabel("标价  =  进价  *");
//		lblNewLabel_4.setBounds(190, 27, 136, 27);
//		lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 23));
		panel_4.add(lblNewLabel_4);
		jt1 = new JTextField();
//		jt1.setBounds(331, 24, 136, 33);
//		jt1.setFont(new Font("宋体", Font.BOLD, 23));
		panel_4.add(jt1);
		jt1.setColumns(10);
		jt1.setText(Double.toString(mainFrame.inprice_add));

		JPanel panel_4_1 = new JPanel();
		panel_3.add(panel_4_1);
		panel_4_1.setLayout(new GridLayout(1,4,20,0));
//		panel_4_1.setLayout(null);
		panel_4_1.setBounds(10, 60, 650, 30);


		JLabel lblNewLabel_4_13 = new JLabel("");
		panel_4.add(lblNewLabel_4_13);


		JLabel lblNewLabel_4_1 = new JLabel("今日折扣 ： ");
//		lblNewLabel_4_1.setFont(new Font("宋体", Font.BOLD, 23));
//		lblNewLabel_4_1.setBounds(211, 28, 121, 27);
		panel_4_1.add(lblNewLabel_4_1);

		jt2 = new JTextField();
//		jt2.setFont(new Font("宋体", Font.BOLD, 23));
		jt2.setColumns(10);
		jt2.setBounds(330, 25, 136, 33);
		panel_4_1.add(jt2);
		jt2.setText(Double.toString(mainFrame.zhekou));

		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setBounds(10, 263, 665, 80);
		panel_3.add(panel_4_1_1);
	JLabel lblNewLabel_4_133 = new JLabel("");
//		lblNewLabel_4_1.setFont(new Font("宋体", Font.BOLD, 23));
//		lblNewLabel_4_1.setBounds(211, 28, 121, 27);
		panel_4_1.add(lblNewLabel_4_133);
		JButton jb_reset = new JButton("重置");
		jb_reset.setIcon(svg.getSVGIcon("/svg/arrow-clockwise.svg","#2F4F4F",20,20));
		jb_reset.setBounds(420, 0, 150, 35);
		jb_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jt1.setText("");
				jt2.setText("");
			}
		});
//		panel_4_1_1.setLayout(null);
//		jb_reset.setFont(new Font("宋体", Font.BOLD, 20));
//		panel_4_1_1.add();
		panel_4.add(jb_reset);
		JButton jb_ok = new JButton("确定修改");
		jb_ok.setIcon(svg.getSVGIcon("/svg/check-square.svg","#2F4F4F",15,15));
		jb_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				拿到数据,设置新数值
				mainFrame.inprice_add=Double.parseDouble(jt1.getText());
				mainFrame.zhekou=Double.parseDouble(jt2.getText());
				System.out.println(mainFrame.inprice_add);
				System.out.println(mainFrame.zhekou);
				bookSale.jt_zhekou.setText(jt2.getText());
//				
			}
		});
		jb_ok.setBounds(420, 40, 150, 35);
//		jb_ok.setFont(new Font("宋体", Font.BOLD, 20));
//		panel_4_1_1.add();
		panel_4_1.add(jb_ok);
		JPanel panel = new JPanel();
		tabbedPane.addTab("修改密码", svg.getSVGIcon("/svg/key.svg","#2F4F4F",20,20), panel, null);
		panel.setLayout(null);
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(new GridLayout(1,4,20,0));
		panel_6.setBounds(10, 20, 650, 30);
		panel.add(panel_6);
		//JLabel lblNewLabel_2 = new JLabel("请输入");
//		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 26));
		//panel_2.add(lblNewLabel_2);

		OriginalCode = new JTextField();
//		OriginalCode.setFont(new Font("宋体", Font.BOLD, 25));
		OriginalCode.setColumns(10);
//		OriginalCode.setBounds(200, 120, 280, 50);


		JLabel lblNewLabel = new JLabel("原密码：");
//		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 25));
//		lblNewLabel.setBounds(116, 95, 130, 100);
		panel_6.add(lblNewLabel);
panel_6.add(OriginalCode);
JLabel lblNewLabel_54 = new JLabel("");
		panel_6.add(lblNewLabel_54);
JLabel lblNewLabel_5 = new JLabel("");

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(new GridLayout(1,4,20,0));
		panel_7.setBounds(10, 60, 650, 30);
		panel.add(panel_7);

		JLabel lblNewLabel_1 = new JLabel("新密码：");
//		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 25));
//		lblNewLabel_1.setBounds(116, 163, 130, 100);
		panel_7.add(lblNewLabel_1);
		
		NewPassword = new JTextField();
//		NewPassword.setFont(new Font("宋体", Font.BOLD, 25));
		NewPassword.setColumns(10);
//		NewPassword.setBounds(200, 200, 280, 50);
		panel_7.add(NewPassword);
		JLabel lblNewLabel_9 = new JLabel("");
		panel_7.add(lblNewLabel_9);
//JLabel lblNewLabel_10 = new JLabel("");
//		panel_7.add(lblNewLabel_10);
		JButton btnNewButton = new JButton("重置");
		btnNewButton.setIcon(svg.getSVGIcon("/svg/arrow-clockwise.svg","#2F4F4F",20,20));
//		btnNewButton.setFont(new Font("宋体", Font.BOLD, 21));
//		btnNewButton.setBounds(120, 300, 150, 60);
//		panel.add(btnNewButton);
		panel_6.add(btnNewButton);
		JButton btnNewButton_1 = new JButton("确定");
		btnNewButton_1.setIcon(svg.getSVGIcon("/svg/check-square.svg","#2F4F4F",20,20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				先获取输入的密码与已经登录的密码匹配是否一致
				String originalCodeString=OriginalCode.getText();
				if (originalCodeString.equals(mainFrame.manager.getPswString())) {
					System.out.println("原密码正确！");
//					获取新密码
					String newPasswordString=NewPassword.getText();
//					保存新密码到用户表中
					System.out.println(newPasswordString);
					String sql="update manager set password=? where user=?";
					try {
						int n= ConnectionManager.Update(sql, new Object[] {newPasswordString, mainFrame.manager.getUserString()});
						if (n>0) {
//							密码修改成功
							JOptionPane.showMessageDialog(null,"密码修改成功，请重新登录！！！","提示",JOptionPane.INFORMATION_MESSAGE);
							Login.jrame.dispose();
							System.out.println("当前窗口关闭");
							Login login=new Login();
							login.setVisible(true);
							System.out.println("打开新窗口，请登录！");
						}else {
							System.out.println("密码修改发生错误！！！！！！");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}else {
					JOptionPane.showMessageDialog(null,"原密码错误!","提示",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("原密码错误!");
				}
				
			}
		});
//		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 21));
//		btnNewButton_1.setBounds(360, 300, 150, 60);
//		panel.add(btnNewButton_1);
panel_7.add(btnNewButton_1);
	}
}
