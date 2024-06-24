package com.lys.bms.frame;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.lys.bms.dataTemplate.svg;
import com.lys.bms.dataTemplate.isbn;
import com.lys.bms.jdbc.ConnectionManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.Document;


import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class bookSale extends JPanel {
    private JTextField jt_isbn;
    private JTextField jt_bookname;
    private JSpinner jt_num;
    private JTextField jt_price;
    public static JTextField jt_discount;
    private JTextField jt_shouldpay;
    private JTextField jt_receive;
    private JTextField jt_return;
    private static JTextField dingdan_nums;
    private JTable table;
    private JTextField find_text;
    private JTable table_1;
    private JLabel trips;
    private JLabel trips_1;
    private Object[] o1;
    private Object[][] o2;
    private static JRadioButton jrb_isbn;
    private static JRadioButton jrb_bookname;
    private static JRadioButton jrb_time;

    /**
     * Create the panel.
     */
    public bookSale() throws IOException {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("TabbedPane.showTabSeparators", true);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
//		setBackground(new Color(152, 251, 152));
        setBorder(new TitledBorder(null, "\u56FE\u4E66\u9500\u552E\u6A21\u5757", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 23, 690, 495);
        add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("图书销售", svg.getSVGIcon("/svg/cart-check.svg", "#2F4F4F", 20, 20), panel, null);
        panel.setLayout(null);

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(new GridLayout(1, 4, 20, 0));
        panel_3.setBounds(10, 20, 650, 30);
        panel.add(panel_3);

        JLabel lblNewLabel = new JLabel("ISBN：");
//		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 19));
//		lblNewLabel.setBounds(68, 6, 91, 28);
        panel_3.add(lblNewLabel);

        jt_isbn = new JTextField();
//		动态监听
        Document d1 = jt_isbn.getDocument();
        d1.addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                isbn.dynISBNcomplete(jt_isbn);
                // TODO Auto-generated method stub
                get_bookname_mark_price();

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                isbn.dynISBNcomplete(jt_isbn);
                // TODO Auto-generated method stub
                get_bookname_mark_price();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isbn.dynISBNcomplete(jt_isbn);
                // TODO Auto-generated method stub
                get_bookname_mark_price();
            }
        });
//		jt_isbn.setFont(new Font("宋体", Font.BOLD, 19));
        jt_isbn.setColumns(25);
        jt_isbn.setBounds(131, 10, 171, 21);
        panel_3.add(jt_isbn);
//		jt_isbn.setColumns(25);

        JLabel lblNewLabel_1 = new JLabel("书名：");
//		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 19));
        lblNewLabel_1.setBounds(352, 6, 75, 28);
        panel_3.add(lblNewLabel_1);

        jt_bookname = new JTextField();
//		jt_bookname.setFont(new Font("宋体", Font.BOLD, 19));
        jt_bookname.setColumns(25);
        jt_bookname.setBounds(420, 10, 171, 21);
        panel_3.add(jt_bookname);

        JPanel panel_3_1 = new JPanel();
        panel_3_1.setLayout(new GridLayout(1, 4, 20, 0));
        panel_3_1.setBounds(10, 60, 650, 30);
        panel.add(panel_3_1);

        JLabel lblNewLabel_2 = new JLabel("购买数量：");
//		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 19));
        lblNewLabel_2.setBounds(70, 6, 89, 28);
        panel_3_1.add(lblNewLabel_2);

        jt_num = new JSpinner();
        ((SpinnerNumberModel) jt_num.getModel()).setMinimum(0);
//		jt_num.setFont(new Font("宋体", Font.BOLD, 19));
//		jt_num.setColumns(25);
        jt_num.setBounds(131, 10, 171, 21);
////		jt_num.setToolTipText("123");
//		jt_num.createToolTip();
//		jt_num.setToolTipText("123");
        panel_3_1.add(jt_num);

        JLabel lblNewLabel_1_1 = new JLabel("价格：");
//		lblNewLabel_1_1.setFont(new Font("宋体", Font.BOLD, 19));
        lblNewLabel_1_1.setBounds(352, 6, 75, 28);
        panel_3_1.add(lblNewLabel_1_1);

        jt_price = new JTextField();
//		jt_price.setFont(new Font("宋体", Font.BOLD, 19));
        jt_price.setColumns(25);
        jt_price.setBounds(420, 10, 171, 21);
        panel_3_1.add(jt_price);

        trips_1 = new JLabel("");
        trips_1.setForeground(Color.RED);
//		trips_1.setFont(new Font("宋体", Font.BOLD, 16));
//		trips_1.setBounds(131, 250, 274, 21);
//		panel_3_1.add(trips_1);

        JPanel panel_3_2 = new JPanel();
        panel_3_2.setLayout(new GridLayout(1, 4, 20, 0));
        panel_3_2.setBounds(10, 100, 650, 30);
        panel.add(panel_3_2);

        JLabel lblNewLabel_3 = new JLabel("折扣：");
//		lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 19));
        lblNewLabel_3.setBounds(68, 6, 91, 28);
        panel_3_2.add(lblNewLabel_3);

        jt_discount = new JTextField();
        jt_discount.setText(Double.toString(mainFrame.discount));
        jt_discount.setHorizontalAlignment(4);
        jt_discount.setColumns(25);
        jt_discount.setBounds(131, 10, 171, 21);
        panel_3_2.add(jt_discount);

        JLabel lblNewLabel_1_2 = new JLabel("应付：");
//		lblNewLabel_1_2.setFont(new Font("宋体", Font.BOLD, 19));
        lblNewLabel_1_2.setBounds(352, 6, 75, 28);
        panel_3_2.add(lblNewLabel_1_2);

        jt_shouldpay = new JTextField();
//		动态计算应付金额
        jt_num.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    get_shouldpay();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
//		jt_shouldpay.setFont(new Font("宋体", Font.BOLD, 19));
        jt_shouldpay.setColumns(25);
        jt_shouldpay.setBounds(420, 10, 171, 21);
        panel_3_2.add(jt_shouldpay);

        JPanel panel_3_3 = new JPanel();
        panel_3_3.setLayout(new GridLayout(1, 4, 20, 0));
        panel_3_3.setBounds(10, 140, 650, 30);
        panel.add(panel_3_3);

        JLabel lblNewLabel_4 = new JLabel("收取：");
        panel_3_3.add(lblNewLabel_4);

        jt_receive = new JTextField();
        Document document = jt_receive.getDocument();
        document.addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                get_returnmoney();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                get_returnmoney();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub

            }
        });
        jt_receive.setColumns(25);
        jt_receive.setBounds(131, 10, 171, 21);
        panel_3_3.add(jt_receive);

        JLabel lblNewLabel_1_3 = new JLabel("找零：");
        panel_3_3.add(lblNewLabel_1_3);

        jt_return = new JTextField();
        jt_return.setColumns(25);
        jt_return.setBounds(420, 10, 171, 21);
        panel_3_3.add(jt_return);


        JButton btnNewButton = new JButton("重置");
        btnNewButton.setIcon(svg.getSVGIcon("/svg/arrow-clockwise.svg", "#2F4F4F", 20, 20));
        btnNewButton.setBounds(20, 240, 300, 60);
        panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				重置输出框
                jt_isbn.setText("");
                jt_bookname.setText("");
                jt_num.setValue(0);
                jt_price.setText("");
                jt_shouldpay.setText("");
                jt_receive.setText("");
                jt_return.setText("");
            }
        });
//		btnNewButton.setFont(new Font("宋体", Font.BOLD, 18));
//		btnNewButton.setBounds(155, 180, 113, 30);
        //panel_3_5.add(btnNewButton);
        JButton btnNewButton_1 = new JButton("确定");
        btnNewButton_1.setIcon(svg.getSVGIcon("/svg/check-square.svg", "#2F4F4F", 20, 20));
        btnNewButton_1.setBounds(360, 240, 300, 60);
        panel.add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				判断当前ISBN对应的图书是否存在

                if (jt_isbn.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入要购买的图书！", "提示", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    String isbn = jt_isbn.getText();
                    String sql = "select * from book_stack where ISBN=?";
                    try {
                        ResultSet set = ConnectionManager.query(sql, new Object[]{isbn});
//						如果该书不存在
                        if (!set.next()) {
                            JOptionPane.showMessageDialog(null, "书库中没有该图书", "提示", JOptionPane.INFORMATION_MESSAGE);
                        } else {
//							当前图书存在，保存相应订单
//							获取要保存的数据
                            String isbnString = jt_isbn.getText();
                            String bookname = jt_bookname.getText();
                            String num = jt_num.getValue().toString();
                            String mark_price = jt_price.getText();
                            String discount = jt_discount.getText();
                            String shouldpay = jt_shouldpay.getText();
                            String receive = jt_receive.getText();
                            String returnmoney = jt_return.getText();
//							下单时间
                            String time = ConnectionManager.gettime();
//							保存数据到表
                            String sqlstring = "insert into book_out values(?,?,?,?,?,?,?,?,?,?);";
                            int n = ConnectionManager.Update(sqlstring, new Object[]{null, isbnString, bookname, num, mark_price, discount, shouldpay, receive, returnmoney, time});
                            if (n > 0) {
//                                JOptionPane.showMessageDialog(null, "下单成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
//								修改库存数量
                                int stocknum = 0;
                                String sqlString2 = "select num from book_stack where ISBN=?";
                                ResultSet set1 = ConnectionManager.query(sqlString2, new Object[]{isbnString});
                                if (set1.next()) {
                                    stocknum = set1.getInt("num");
                                }
                                stocknum = stocknum - Integer.parseInt(num);
//								保存更改后的数量-库存
                                String sqlString3 = "update book_stack set num=? where ISBN=?";
                                int m = ConnectionManager.Update(sqlString3, new Object[]{stocknum, isbn});
                                if (m > 0) {
                                    JOptionPane.showMessageDialog(null, "图书数量更新完毕，购买" + num + "现在还剩下" + stocknum + "本。", "提示", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "图书数量更新失败！", "警告", JOptionPane.WARNING_MESSAGE);
                                }
//								新书表
                                String sqlString4 = "update new_book_in set num=? where ISBN=?";
                                int k = ConnectionManager.Update(sqlString4, new Object[]{stocknum, isbn});
                                if (k > 0) {
                                    JOptionPane.showMessageDialog(null, "图书数量更新完毕，购买" + num + "现在还剩下" + stocknum + "本。", "提示", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "图书数量更新失败！", "警告", JOptionPane.WARNING_MESSAGE);
                                }
//								刷新表格

                                Update_sele_table();
                                libManage.update_table_items();

                            } else {
                                JOptionPane.showMessageDialog(null, "下单失败！", "提示", JOptionPane.INFORMATION_MESSAGE);


                            }


                        }

                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

            }
        });
//		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 18));
//		btnNewButton_1.setBounds(455, 180, 113, 30);
        JLabel panel_3_5_12 = new JLabel(" ");
        //panel_3_5.add(panel_3_5_12);
        //panel_3_5.add(btnNewButton_1);
        //JLabel panel_3_5_14 = new JLabel(" ");
        //panel_3_5.add(panel_3_5_14);


        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("订单一览", svg.getSVGIcon("/svg/eye.svg", "#2F4F4F", 25, 25), panel_1, null);
        panel_1.setLayout(null);

        JPanel panel_4 = new JPanel();
        panel_4.setBounds(0, 43, 685, 470);
        panel_1.add(panel_4);
        panel_4.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 685, 415);
        panel_4.add(scrollPane);

        o1 = new Object[]{"序号", "ISBN", "书名", "购买数量", "标价", "折扣", "应付", "收取", "找零", "下单时间"};
        o2 = new Object[][]{

        };

        table = new JTable(o2, o1);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel_5 = new JLabel("订单总数：");
//		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 19));
		lblNewLabel_5.setBounds(468, 10, 124, 28);
        panel_1.add(lblNewLabel_5);

        dingdan_nums = new JTextField();
        dingdan_nums.setEditable(false);
		dingdan_nums.setBounds(556, 10, 93, 28);
        panel_1.add(dingdan_nums);
        dingdan_nums.setColumns(10);

        JPanel panel_5 = new JPanel();
        tabbedPane.addTab("订单查询", svg.getSVGIcon("/svg/search.svg", "#2F4F4F", 20, 20), panel_5, null);
        panel_5.setLayout(null);

        JPanel panel_6 = new JPanel();
        panel_6.setBounds(0, 22, 685, 43);
        panel_5.add(panel_6);

        find_text = new JTextField();
//		find_text.setFont(new Font("宋体", Font.BOLD, 20));
        panel_6.add(find_text);
		find_text.setColumns(52);

        JButton btnNewButton_2 = new JButton("查询");
        btnNewButton_2.setIcon(svg.getSVGIcon("/svg/search.svg", "#1296DB", 20, 20));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				获取文本框数据
                String text = find_text.getText();
                if (text.equals("")) {
                    JOptionPane.showMessageDialog(null, "请先输入要查询的内容！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
//					判断所选择的查询方法
                    int isselect = 0;
                    if (jrb_isbn.isSelected()) {
                        isselect = 1;
                    }
                    if (jrb_bookname.isSelected()) {
                        isselect = 2;
                    }
                    if (jrb_time.isSelected()) {
                        isselect = 3;
                    }
//					按ISBN查询结果
//					String sql2="SELECT ISBN,bookname,out_num,mark_price,discount,sum,`return`,receive,time from book_out;";

                    if (isselect == 1) {
                        String sql = "SELECT ISBN,bookname,out_num,mark_price,discount,sum,`return`,receive,time from book_out where ISBN like '" + text + "%';";
                        get_find_result(sql);
                    }
                    if (isselect == 2) {
                        String sql = "SELECT ISBN,bookname,out_num,mark_price,discount,sum,`return`,receive,time from book_out where bookname like '" + text + "%';";
                        get_find_result(sql);

                    }
                    if (isselect == 3) {
                        String sql = "SELECT ISBN,bookname,out_num,mark_price,discount,sum,`return`,receive,time from book_out where time like '" + text + "%';";
                        get_find_result(sql);
                    }
                }


            }

        });
        panel_6.add(btnNewButton_2);

        JPanel panel_7 = new JPanel();
        panel_7.setBounds(0, 67, 685, 43);
        panel_5.add(panel_7);

        JLabel lblNewLabel_6 = new JLabel("查询方式：");
        panel_7.add(lblNewLabel_6);

        jrb_isbn = new JRadioButton("ISBN");
        jrb_isbn.setSelected(true);
        panel_7.add(jrb_isbn);

        jrb_bookname = new JRadioButton("书名");
        panel_7.add(jrb_bookname);

        jrb_time = new JRadioButton("交易时间");
        jrb_time.setForeground(Color.BLACK);
        panel_7.add(jrb_time);

//		按钮组
        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(jrb_time);
        bGroup.add(jrb_bookname);
        bGroup.add(jrb_isbn);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 148, 685, 310);
        panel_5.add(scrollPane_1);

        table_1 = new JTable(o2, o1);
        scrollPane_1.setViewportView(table_1);

        JLabel lblNewLabel_7 = new JLabel("查询结果：");
        lblNewLabel_7.setBounds(2, 120, 86, 18);
        panel_5.add(lblNewLabel_7);


        Update_sele_table();


    }

    /**
     * 动态查询ISBN如有结果返回bookname和mark_price
     */
    public void get_bookname_mark_price() {
//		获取ISBN值
        String isbn = jt_isbn.getText();
//		从库存表中查询是否有该图书
        String sql = "select * from book_stack where ISBN=?;";
//		执行
        try {
            ResultSet set = ConnectionManager.query(sql, new Object[]{isbn});
//			判断结果集
            if (set.next()) {
                String bookname = set.getString("bookname");
                double mark_price = set.getDouble("mark_price");
//				将值设置到文本框
                jt_bookname.setText(bookname);
                jt_price.setText(Double.toString(mark_price));
            } else {
                jt_bookname.setText("");
                jt_price.setText("");
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * 计算应付金额
     *
     * @throws SQLException
     */
    public void get_shouldpay() throws SQLException {
//		获取该书的库存量
        boolean extis = false;
        String isbn = jt_isbn.getText();
        int num;
        String sql = "select num from book_stack where ISBN=?";
        ResultSet set = null;
        try {
            set = ConnectionManager.query(sql, new Object[]{isbn});
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int stock_num = 0;
        while (set.next()) {
            stock_num = set.getInt("num");
            extis = true;

        }

        if (extis) {
//			购买数量超过库存
            num = Integer.parseInt(jt_num.getValue().toString());
            if (num > stock_num) {
                mainFrame.statusWarn("该书当前库存仅有" + stock_num + "本！", 5);
            } else {
//				获取价格，数量，折扣
                double price = Double.parseDouble(jt_price.getText());
                double discount = Double.parseDouble(jt_discount.getText());
//				计算应付金额
                double shouldpay = num * price * discount;
//				会显
                jt_shouldpay.setText(Double.toString(shouldpay));
            }

        }


    }

    /**
     * 计算找零
     */
    public void get_returnmoney() {
        if (jt_receive.getText().equals("")) {

        } else {
//			获取应付
            double shouldpay = Double.parseDouble(jt_shouldpay.getText());
//			获取 收取
            double receive = Double.parseDouble(jt_receive.getText());
//			判断收取是否够钱
            if (receive < shouldpay) {
                mainFrame.statusWarn("应收款不足。", 5);
            } else {
                double returnmoney = receive - shouldpay;
                jt_return.setText(Double.toString(returnmoney));
            }
        }
    }

    /**
     * 输入SQL查询语句，返回结果集的二维组形式，并刷新到表格中
     */
    public void Update_sele_table() {
        // TODO Auto-generated method stub
//		统计数量
        int count = 0;

        Object[] objects = new Object[]{"序号", "ISBN", "书名", "购买数量", "标价", "折扣", "应付", "收取", "找零", "下单时间"};
        String sql2 = "SELECT ISBN,bookname,out_num,mark_price,discount,sum,receive,`return`,time from book_out;";
        ResultSet set = null;
        try {
            set = ConnectionManager.query(sql2, new Object[]{});
            while (set.next()) {
                count++;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Object[][] objects2 = null;
        try {
            objects2 = ConnectionManager.getSetArrays(set);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        dingdan_nums.setText(Integer.toString(count));
        TableModel dataModel = new DefaultTableModel(objects2, objects);
        table.setModel(dataModel);
        System.out.println("订单表格刷新!");
    }


    /**
     * 根据输入框的参数查询返回相应的结果到表格
     *
     * @param sql
     */
    public void get_find_result(String sql) {
        // TODO Auto-generated method stub
        Object[][] a = new Object[][]{};
//				查询结果：
        try {
            ResultSet set = ConnectionManager.query(sql, new Object[]{});
//					如果结果集不为空
            if (set.next()) {
//						将结果转为二维数组
                a = ConnectionManager.getSetArrays(set);
//						将数组反馈到表格
                TableModel dataModel = new DefaultTableModel(a, o1);
                table_1.setModel(dataModel);
            } else {
                TableModel dataModel = new DefaultTableModel(new Object[][]{}, o1);
                table_1.setModel(dataModel);

                mainFrame.statusWarn("没有查询到结果。", 5);
            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}

