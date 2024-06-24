package com.lys.bms.frame;

import com.formdev.flatlaf.FlatIntelliJLaf;
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
    private JPasswordField OriginalPassword;
    private JPasswordField NewPassword;
    private JTextField jt1;
    private JTextField jt2;

    /**
     * Create the panel.系统设置
     */
    public settingPanel() throws IOException {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("TabbedPane.showTabSeparators", true);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u7CFB\u7EDF\u8BBE\u7F6E\u6A21\u5757", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        setSize(710, 460);
        setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 24, 690, 426);
        add(tabbedPane);

        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("买卖设置", svg.getSVGIcon("/svg/sliders.svg", "#2F4F4F", 20, 20), panel_3, null);
        panel_3.setLayout(null);

        JPanel panel_4 = new JPanel();
        panel_4.setLayout(new GridLayout(1, 4, 20, 0));
        panel_4.setBounds(10, 20, 650, 30);
        panel_3.add(panel_4);

        JLabel lblNewLabel_4 = new JLabel("标价  =  进价  *");
        panel_4.add(lblNewLabel_4);
        jt1 = new JTextField();
        panel_4.add(jt1);
        jt1.setColumns(10);
        jt1.setText(Double.toString(mainFrame.inprice_add));

        JPanel panel_4_1 = new JPanel();
        panel_3.add(panel_4_1);
        panel_4_1.setLayout(new GridLayout(1, 4, 20, 0));
        panel_4_1.setBounds(10, 60, 650, 30);


        JLabel lblNewLabel_4_13 = new JLabel("");
        panel_4.add(lblNewLabel_4_13);


        JLabel lblNewLabel_4_1 = new JLabel("今日折扣 ： ");
        panel_4_1.add(lblNewLabel_4_1);

        jt2 = new JTextField();
        jt2.setColumns(10);
        jt2.setBounds(330, 25, 136, 33);
        panel_4_1.add(jt2);
        jt2.setText(Double.toString(mainFrame.zhekou));

        JPanel panel_4_1_1 = new JPanel();
        panel_4_1_1.setBounds(10, 263, 665, 80);
        panel_3.add(panel_4_1_1);
        JLabel lblNewLabel_4_133 = new JLabel("");
        panel_4_1.add(lblNewLabel_4_133);
        JButton jb_reset = new JButton("重置");
        jb_reset.setIcon(svg.getSVGIcon("/svg/arrow-clockwise.svg", "#2F4F4F", 20, 20));
        jb_reset.setBounds(420, 0, 150, 35);
        jb_reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jt1.setText("1.0");
                jt2.setText("1.0");
            }
        });
        panel_4.add(jb_reset);
        JButton jb_ok = new JButton("确定");
        jb_ok.setIcon(svg.getSVGIcon("/svg/check-square.svg", "#2F4F4F", 20, 20));
        jb_ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				拿到数据,设置新数值
                mainFrame.inprice_add = Double.parseDouble(jt1.getText());
                mainFrame.zhekou = Double.parseDouble(jt2.getText());
                System.out.println(mainFrame.inprice_add);
                System.out.println(mainFrame.zhekou);
                bookSale.jt_zhekou.setText(jt2.getText());
//				
            }
        });
        jb_ok.setBounds(420, 40, 150, 35);
        panel_4_1.add(jb_ok);
        JPanel panel = new JPanel();
        tabbedPane.addTab("修改密码", svg.getSVGIcon("/svg/key.svg", "#2F4F4F", 20, 20), panel, null);
        panel.setLayout(null);
        JPanel panel_6 = new JPanel();
        panel_6.setLayout(new GridLayout(1, 4, 20, 0));
        panel_6.setBounds(10, 20, 650, 30);
        panel.add(panel_6);

        OriginalPassword = new JPasswordField();
        OriginalPassword.setColumns(10);


        JLabel lblNewLabel = new JLabel("原密码：");
        panel_6.add(lblNewLabel);
        panel_6.add(OriginalPassword);
        JLabel lblNewLabel_54 = new JLabel("");
        panel_6.add(lblNewLabel_54);
        JLabel lblNewLabel_5 = new JLabel("");

        JPanel panel_7 = new JPanel();
        panel_7.setLayout(new GridLayout(1, 4, 20, 0));
        panel_7.setBounds(10, 60, 650, 30);
        panel.add(panel_7);

        JLabel lblNewLabel_1 = new JLabel("新密码：");
        panel_7.add(lblNewLabel_1);

        NewPassword = new JPasswordField();
        NewPassword.setColumns(10);
        panel_7.add(NewPassword);
        JLabel lblNewLabel_9 = new JLabel("");
        panel_7.add(lblNewLabel_9);

        JButton btnNewButton = new JButton("重置");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OriginalPassword.setText("");
                NewPassword.setText("");
            }
        });
        btnNewButton.setIcon(svg.getSVGIcon("/svg/arrow-clockwise.svg", "#2F4F4F", 20, 20));
        panel_6.add(btnNewButton);
        JButton btnNewButton_1 = new JButton("确定");
        btnNewButton_1.setIcon(svg.getSVGIcon("/svg/check-square.svg", "#2F4F4F", 20, 20));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				先获取输入的密码与已经登录的密码匹配是否一致
                String originalCodeString = OriginalPassword.getText();
                if (originalCodeString.equals(mainFrame.manager.getPswString())) {
//					获取新密码
                    String newPasswordString = NewPassword.getText();
//					保存新密码到用户表中
                    System.out.println(newPasswordString);
                    String sql = "update manager set password=? where user=?";
                    try {
                        int n = ConnectionManager.Update(sql, new Object[]{newPasswordString, mainFrame.manager.getUserString()});
                        if (n > 0) {
//							密码修改成功
                            JOptionPane.showMessageDialog(null, "修改成功，请重新登录！", "提示", JOptionPane.INFORMATION_MESSAGE);
                            Login.jrame.dispose();
                            Login login = new Login();
                            login.setVisible(true);
                        }
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "原密码错误！", "警告", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        panel_7.add(btnNewButton_1);

        JPanel userPane = new JPanel();
        tabbedPane.addTab("用户创建", svg.getSVGIcon("/svg/key.svg", "#515151", 20, 20), userPane, null);
        userPane.setLayout(null);
        JPanel panel_8 = new JPanel();
        panel_8.setLayout(new GridLayout(1, 4, 20, 0));
        panel_8.setBounds(10, 20, 650, 30);
        userPane.add(panel_8);
        JTextField usr = new JTextField();
        usr.setColumns(30);
        JLabel lblusr = new JLabel("用户名：");
        panel_8.add(lblusr);
        panel_8.add(usr);
		JLabel lblusr1 = new JLabel("");
		panel_8.add(lblusr1);

        JPanel panel_9 = new JPanel();
        panel_9.setLayout(new GridLayout(1, 4, 20, 0));
        panel_9.setBounds(10, 60, 650, 30);
		userPane.add(panel_9);
		JTextField pwd = new JTextField();
		pwd.setColumns(30);
        JLabel lblpwd = new JLabel("密码：");
        panel_9.add(lblpwd);
        panel_9.add(pwd);
		JLabel lblusr12= new JLabel("");
		panel_9.add(lblusr12);
		JLabel lblusr123 = new JLabel("");
		panel_9.add(lblusr123);
        JButton btnUsrNewButton = new JButton("重置");
        btnUsrNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usr.setText("");
                pwd.setText("");
            }
        });
        btnNewButton.setIcon(svg.getSVGIcon("/svg/arrow-clockwise.svg", "#515151", 20, 20));
        panel_6.add(btnNewButton);
        JButton btnUsrSubmitButton = new JButton("确定");
        btnUsrSubmitButton.setIcon(svg.getSVGIcon("/svg/check-square.svg", "#515151", 20, 20));
        btnUsrSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel_8.add(btnUsrSubmitButton);
    }
}
