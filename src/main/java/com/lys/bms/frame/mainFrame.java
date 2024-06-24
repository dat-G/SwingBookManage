
package com.lys.bms.frame;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.lys.bms.Login;
import com.lys.bms.jdbc.ConnectionManager;
import com.lys.bms.model.Manager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;


import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame {

    //	当前用户
    static Manager manager;
    private JPanel contentPane;
    CardLayout cardLayout = new CardLayout();
    //	标价设置
    static double inprice_add = 1.0;
    //	折扣设置
    static double zhekou = 1.00;

    /**
     * Create the frame.
     *
     * @throws SQLException
     */
    public mainFrame(Manager manager) throws SQLException {
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        this.manager = manager;

        setIconImage(Toolkit.getDefaultToolkit().getImage("/img/线性图书 (1).png"));
        setFont(new Font("Courier New", Font.BOLD, 21));
        setTitle("图书信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 730, 600);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        JMenuItem libManageMenuItem = new JMenuItem("书库管理");
        JMenuItem queryPanelMenuItem = new JMenuItem("信息查询");
        JMenuItem bookSaleMenuItem = new JMenuItem("图书销售");
        JMenu advanceMenu = new JMenu("高级");
        JMenuItem settingPanelMenuItem = new JMenuItem("设置");
        JMenuItem logOutMenuItem = new JMenuItem("登出");
        menuBar.add(libManageMenuItem);
        menuBar.add(queryPanelMenuItem);
        menuBar.add(bookSaleMenuItem);
        menuBar.add(advanceMenu);
        advanceMenu.add(settingPanelMenuItem);
        advanceMenu.add(logOutMenuItem);
        setJMenuBar(menuBar);

        contentPane = new JPanel();
        contentPane.setForeground(new Color(51, 51, 255)); // 前景色
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

//		内容显示面板，可切换，卡片布局

        JPanel main_panel = new JPanel();
        main_panel.setBounds(5, 10, 730, 530);
        contentPane.add(main_panel);
        main_panel.setLayout(new CardLayout(0, 0));
//		给内容面板设置卡片布局
        main_panel.setLayout(cardLayout);
//		添加图书面板
        libManage libManage = new libManage();
        main_panel.add(libManage, "libManage");
//		添加信息查询面板
        queryPanel queryPanel = new queryPanel();
        main_panel.add(queryPanel, "queryPanel");
//		添加图书销售模块
        bookSale bookSale = new bookSale();
        main_panel.add(bookSale, "bookSale");
//		添加系统设置面板
        settingPanel settingPanel = new settingPanel();
        main_panel.add(settingPanel, "4");

        libManageMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(main_panel, "libManage");
            }
        });
        queryPanelMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(main_panel, "queryPanel");
            }

        });
        bookSaleMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(main_panel, "bookSale");

            }
        });
        settingPanelMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(main_panel, "4");
            }
        });
        logOutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
//				关闭当前
                dispose();
                login.setVisible(true);
            }
        });

        JLabel welcome_mess = new JLabel("");
//        welcome_mess.setFont(new Font("宋体", Font.BOLD, 15));
        welcome_mess.setBounds(48, 543, 153, 20);
        contentPane.add(welcome_mess);
        welcome_mess.setText("你好！" + manager.getUserString());

        JLabel day_mess = new JLabel("");
        String string = ConnectionManager.getday();
        day_mess.setText(string);
//        day_mess.setFont(new Font("宋体", Font.BOLD, 15));
        day_mess.setBounds(541, 543, 153, 20);
        contentPane.add(day_mess);


    }
}

