package com.lys.bms;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.lys.bms.dataTemplate.svg;
import com.lys.bms.frame.mainFrame;
import com.lys.bms.jdbc.ConnectionManager;
import com.lys.bms.model.Manager;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
    private JPanel contentPane;
    private JTextField jt_user;
    private JPasswordField jt_psw;
    public static mainFrame jrame;
    /**
     * @wbp.nonvisual location=1029,134
     */
    private final JPanel panel_3 = new JPanel();

    /**
     * 启动主函数
     */
    public static void main(String[] args) {
//        FlatLightLaf.setup();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建窗口
     */
    public Login() throws IOException {
//        try {
////            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
            UIManager.put( "TabbedPane.showTabSeparators", true );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

//        setBackground(new Color(224, 255, 255));
//         Login.class.getResource("/img/线性图书 (1).png")
        setIconImage(svg.getSVGImg("/svg/book-half.svg", "#515151", 256, 256));
        setTitle("图书信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 270);
        setResizable(false);
        contentPane = new JPanel();
//        contentPane.setBackground(SystemColor.menu);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
//        panel.setBackground(SystemColor.menu);
        panel.setBounds(10, 25, 300, 50);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("登录");
        lblNewLabel.setIcon(svg.getSVGIcon("/svg/box-arrow-in-right.svg","#515151", 25, 25));
        lblNewLabel.setIconTextGap(20);
        lblNewLabel.setBounds(110, 5, 400, 30);
//        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font(lblNewLabel.getFont().getFontName(), Font.PLAIN, 27));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
//        panel_1.setBackground(SystemColor.menu);
        panel_1.setBounds(0, 75, 400, 40);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("账号：");
        lblNewLabel_1.setBounds(20, 10, 80, 30);
        lblNewLabel_1.setIcon(svg.getSVGIcon("/svg/person-circle.svg","#515151",20,20));
//        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 20));
        panel_1.add(lblNewLabel_1);

        jt_user = new JTextField();
        jt_user.setBounds(100, 10, 200, 30);
//        jt_user.setFont(new Font("宋体", Font.BOLD, 20));
        panel_1.add(jt_user);
        jt_user.setColumns(15);

        JLabel mess1 = new JLabel("");
//        mess1.setFont(new Font("宋体", Font.PLAIN, 14));
        mess1.setForeground(Color.RED);
        mess1.setBounds(100, 40, 125, 24);
        panel_1.add(mess1);

        JPanel panel_1_1 = new JPanel();
//        panel_1_1.setBackground(SystemColor.menu);
        panel_1_1.setBounds(0, 120, 400, 40);
        contentPane.add(panel_1_1);
        panel_1_1.setLayout(null);

        JLabel lblNewLabel_1_1 = new JLabel("密码：");
        lblNewLabel_1_1.setBounds(20, 10, 80, 30);
        lblNewLabel_1_1.setIcon(svg.getSVGIcon("/svg/key.svg","#515151",25,25));
//        lblNewLabel_1_1.setFont(new Font("宋体", Font.BOLD, 20));
        panel_1_1.add(lblNewLabel_1_1);

        jt_psw = new JPasswordField();
        jt_psw.setBounds(100, 10, 200, 30);
//        jt_psw.setFont(new Font("宋体", Font.BOLD, 15));
        jt_psw.setColumns(15);
        panel_1_1.add(jt_psw);

        JLabel mess2 = new JLabel("");
        mess2.setForeground(Color.RED);
//        mess2.setFont(new Font("宋体", Font.PLAIN, 14));
        mess2.setBounds(100, 40, 125, 24);
        panel_1_1.add(mess2);

        JPanel panel_2 = new JPanel();
//        panel_2.setBackground(SystemColor.menu);
        panel_2.setBounds(10, 160, 400, 60);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        JButton jb_reset = new JButton("重置");
        jb_reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				重置输入框
                jt_user.setText("");
                jt_psw.setText("");
            }
        });
        jb_reset.setIcon(svg.getSVGIcon("/svg/arrow-clockwise.svg","#515151",20,20));
//        jb_reset.setFont(new Font("宋体", Font.BOLD, 17));
        jb_reset.setBounds(40, 20, 100, 30);
        panel_2.add(jb_reset);

        JButton jb_login = new JButton("提交");
        jb_login.setIcon(svg.getSVGIcon("/svg/arrow-bar-up.svg","#515151",20,20));
        jb_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				获取账号和密码
                String userString = jt_user.getText();
                char[] a = jt_psw.getPassword();
                String pswString = String.valueOf(a);
//				查询是否匹配
                String sql = "select * from manager where user=?";
                if (jt_user.getText().equals("")) {
                    mess1.setText("请输入账号：");
                } else {
                    try {
                        ResultSet set = ConnectionManager.query(sql, new Object[]{userString});
                        if (set.next()) {
//							找到用户
                            String user = set.getString("user");
                            String psw = set.getString("password");

                            System.out.println(user + psw);
//							判断密码
                            if (pswString.equals("")) {
                                mess2.setText("请输入密码!");
                            } else if (psw.equals(pswString)) {
//								登录成功
                                System.out.println("登录成功！");
//								打开新窗口
                                jrame = new mainFrame(new Manager(userString, pswString));
//								关闭当前
                                dispose();
                                jrame.setVisible(true);
                            } else {
                                System.out.println("密码输入错误！");
                                mess2.setText("密码输入错误!");
                            }
                        } else {
                            System.out.println("账号不存在！");
                            mess1.setText("该账号不存在!");
                        }
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });

//		动态清零
        Document dt = jt_user.getDocument();
        dt.addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                mess1.setText("");
                mess2.setText("");

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                mess1.setText("");
                mess2.setText("");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                mess1.setText("");
                mess2.setText("");
            }
        });
//        jb_login.setFont(new Font("宋体", Font.BOLD, 17));
        jb_login.setBounds(160, 20, 100, 30);
        panel_2.add(jb_login);
    }
}
