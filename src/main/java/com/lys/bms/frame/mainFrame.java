
package com.lys.bms.frame;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.lys.bms.Login;
import com.lys.bms.dataTemplate.svg;
import com.lys.bms.jdbc.ConnectionManager;
import com.lys.bms.model.Manager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import java.awt.Color;


import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.TimerTask;

public class mainFrame extends JFrame {

    //	当前用户
    static Manager manager;
    private JPanel panel;
    private JLabel statusBar;
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
    public mainFrame(Manager manager) throws SQLException, IOException {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("TabbedPane.showTabSeparators", true);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        this.manager = manager;

        setIconImage(svg.getSVGImg("/svg/book.svg","#515151",25,25));
        setFont(new Font("Courier New", Font.BOLD, 21));
        setTitle("图书信息管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 730, 600);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        JMenuItem libManageMenuItem = new JMenuItem("书库管理", svg.getSVGIcon("/svg/gear.svg","#515151",18,18));
        JMenuItem queryPanelMenuItem = new JMenuItem("信息查询",svg.getSVGIcon("/svg/search.svg","#515151",18,18));
        JMenuItem bookSaleMenuItem = new JMenuItem("图书销售",svg.getSVGIcon("/svg/cart4.svg","#515151",18,18));
        JMenu advanceMenu = new JMenu("高级");
        JMenuItem settingPanelMenuItem = new JMenuItem("设置",svg.getSVGIcon("/svg/gear.svg","#515151",18,18));
        JMenuItem logOutMenuItem = new JMenuItem("登出",svg.getSVGIcon("/svg/box-arrow-in-left.svg","#515151",18,18));
        menuBar.add(libManageMenuItem);
        menuBar.add(queryPanelMenuItem);
        menuBar.add(bookSaleMenuItem);
        menuBar.add(advanceMenu);
        advanceMenu.add(settingPanelMenuItem);
        advanceMenu.add(logOutMenuItem);
        setJMenuBar(menuBar);

        panel = new JPanel();
        panel.setForeground(new Color(51, 51, 255)); // 前景色
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

//		内容显示面板，可切换，卡片布局

        JPanel tabViewPane = new JPanel();
        tabViewPane.setBounds(5, 10, 710, 530);
        panel.add(tabViewPane);
        tabViewPane.setLayout(new CardLayout(0, 0));
//		给内容面板设置卡片布局
        tabViewPane.setLayout(cardLayout);
//		添加图书面板
        libManage libManage = new libManage();
        tabViewPane.add(libManage, "libManage");
//		添加信息查询面板
        queryPanel queryPanel = new queryPanel();
        tabViewPane.add(queryPanel, "queryPanel");
//		添加图书销售模块
        bookSale bookSale = new bookSale();
        tabViewPane.add(bookSale, "bookSale");
//		添加系统设置面板
        settingPanel settingPanel = new settingPanel();
        tabViewPane.add(settingPanel, "4");

        libManageMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(tabViewPane, "libManage");
            }
        });
        queryPanelMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(tabViewPane, "queryPanel");
            }

        });
        bookSaleMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(tabViewPane, "bookSale");

            }
        });
        settingPanelMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(tabViewPane, "4");
                statusWarn("123", 5);
            }
        });
        logOutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = null;
                try {
                    login = new Login();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
//				关闭当前
                dispose();
                login.setVisible(true);
            }
        });
        statusBar = new JLabel("");
        statusBar.setBounds(5, 540, 710, 20);
        panel.add(statusBar);
        statusBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        statusBar.setFont(new Font(statusBar.getFont().getFontName(), Font.ITALIC, statusBar.getFont().getSize()));
//        public static void statusRecover() {
//            statusBar.setText("[Information] 当前用户ID: " + manager.getUserString() + " | 服务器日期: " + ConnectionManager.getday());
//        }
        statusRecover();

//        JLabel day_mess = new JLabel("");
//        String string = ConnectionManager.getday();
//        day_mess.setText(string);
//        day_mess.setBounds(540, 540, 150, 20);
//        panel.add(day_mess);


    }

    public void statusRecover() {
        statusBar.setForeground(Color.BLACK);
        statusBar.setText("[Information] 当前用户ID: " + manager.getUserString() + " | 服务器日期: " + ConnectionManager.getday());
    }

    public void statusWarn(String msg, int time) {
        statusBar.setForeground(Color.RED);
        statusBar.setText("[Warning] " + msg);
        Timer timer = new Timer(time * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusRecover();
            }
        });
        timer.start();
    }
}

