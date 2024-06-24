package com.lys.bms.frame;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.lys.bms.jdbc.ConnectionManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lys.bms.dataTemplate.*;

public class libManage extends JPanel {
    private static JTextField jt_isbn;
    private static JTextField jt_name;
    private static JTextField jt_author;
    private static JTextField jt_inprice;
    private static JTextField jt_outprice;
    private static JSpinner jt_num;
    private JTextField textField_5;
    private static JTable table_bookstock;
    private static JTable table_in_book;
    private static JTextField jt_get_all = new JTextField();
    private static JTextField jt_get_sum = new JTextField();
    static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

    /**
     * Create the panel：图书信息管理模块，增删改查
     *
     * @throws SQLException
     */
    public libManage() throws SQLException, IOException {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("TabbedPane.showTabSeparators", true);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
//        setBackground(new Color(175, 238, 238));
        setBorder(new TitledBorder(null, "\u56FE\u4E66\u7BA1\u7406\u6A21\u5757", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        setLayout(null);

//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 24, 690, 495);
        add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("信息编辑", svg.getSVGIcon("/svg/card-list.svg", "#2F4F4F", 25, 25), panel, null);
        panel.setLayout(null);

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(new GridLayout(1, 4, 20, 0));
        panel_3.setBounds(10, 20, 650, 30);
        panel.add(panel_3);

        JLabel lblNewLabel_1 = new JLabel("ISBN: ");
//        lblNewLabel_1.setFont(new Font(lblNewLabel_1.getFont().getFontName(), Font.PLAIN, 19));
//        lblNewLabel_1.setBounds(30, 6, 91, 28);
        panel_3.add(lblNewLabel_1);

        jt_isbn = new JTextField();

//		动态监听文本款内容

        Document jt_isbn_dom = jt_isbn.getDocument();
        jt_isbn_dom.addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                isbn.dynISBNcomplete(jt_isbn);
                checkBook();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                isbn.dynISBNcomplete(jt_isbn);
                checkBook();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                isbn.dynISBNcomplete(jt_isbn);
                checkBook();
            }

        }); // ISBN 修改事件侦听

//        jt_isbn.setFont(new Font("宋体", Font.BOLD, 18));
        jt_isbn.setColumns(25);
        jt_isbn.setBounds(131, 10, 171, 21);
        panel_3.add(jt_isbn);

        JLabel lblNewLabel_1_1 = new JLabel("书名：");
        lblNewLabel_1_1.setBounds(352, 6, 75, 28);
        panel_3.add(lblNewLabel_1_1);

        jt_name = new JTextField();
        jt_name.setColumns(25);
        jt_name.setBounds(420, 10, 171, 21);
        panel_3.add(jt_name);

        JPanel panel_3_1 = new JPanel();
        panel_3_1.setLayout(new GridLayout(1, 4, 20, 0));
        panel_3_1.setBounds(10, 60, 650, 30);
        panel.add(panel_3_1);

        JLabel lblNewLabel_2 = new JLabel("作者: ");
        lblNewLabel_2.setBounds(70, 6, 89, 28);
        panel_3_1.add(lblNewLabel_2);

        jt_author = new JTextField();
        jt_author.setColumns(25);
        jt_author.setBounds(131, 10, 171, 21);
        panel_3_1.add(jt_author);
        JLabel lblNewLabel_2_1 = new JLabel("");
//        lblNewLabel_2.setBounds(70, 6, 89, 28);
        panel_3_1.add(lblNewLabel_2_1);

        JLabel lblNewLabel_2_2 = new JLabel("");
        lblNewLabel_2.setBounds(70, 6, 89, 28);
        panel_3_1.add(lblNewLabel_2_2);

        JPanel panel_3_6 = new JPanel();
        panel_3_6.setLayout(new GridLayout(1, 4, 20, 0));
        panel_3_6.setBounds(10, 100, 650, 30);
        panel.add(panel_3_6);

        JLabel lblNewLabel_1_1_1 = new JLabel("进价: ");
        lblNewLabel_1_1_1.setBounds(352, 6, 75, 28);
        panel_3_6.add(lblNewLabel_1_1_1);

        jt_inprice = new JTextField();
//      jt_inprice.setFont(new Font("宋体", Font.BOLD, 18));
        jt_inprice.setColumns(25);
//        jt_inprice.setBounds(420, 10, 171, 21);
        panel_3_6.add(jt_inprice);
        JLabel lblNewLabel_3_12 = new JLabel("售价: ");
//        lblNewLabel_2.setBounds(70, 6, 89, 28);
        panel_3_6.add(lblNewLabel_3_12);
        jt_outprice = new JTextField("");
//        lblNewLabel_2.setBounds(70, 6, 89, 28);
        panel_3_6.add(jt_outprice);
        JPanel panel_3_2 = new JPanel();
        panel_3_2.setLayout(new GridLayout(1, 4, 20, 0));
        panel_3_2.setBounds(10, 140, 650, 30);
        panel.add(panel_3_2);

        JLabel lblNewLabel_3 = new JLabel("数量：");
        lblNewLabel_3.setBounds(68, 6, 91, 28);
        panel_3_2.add(lblNewLabel_3);
        jt_num = new JSpinner();
        ((SpinnerNumberModel) jt_num.getModel()).setMinimum(0); // 下限设置
        JSpinner.NumberEditor jt_num_editor = new JSpinner.NumberEditor(jt_num);
        jt_num.setEditor(jt_num_editor);
//        jt_num.setColumns(25);
        jt_num.setBounds(131, 10, 171, 21);
        panel_3_2.add(jt_num);

        JLabel lblNewLabel_1_2 = new JLabel("时间：");
        lblNewLabel_1_2.setBounds(352, 6, 75, 28);
        panel_3_2.add(lblNewLabel_1_2);
        textField_5 = new JTextField();
        textField_5.setEnabled(false);
        textField_5.setText("自动获取");
        textField_5.setColumns(25);
        textField_5.setBounds(419, 8, 171, 25);
        panel_3_2.add(textField_5);

        JPanel panel_3_3 = new JPanel();
        panel_3_3.setLayout(new GridLayout(1, 4, 40, 50));
        panel_3_3.setBounds(10, 180, 650, 50);
        panel.add(panel_3_3);

        JButton btnNewButton = new JButton("重置");
        btnNewButton.setIcon(svg.getSVGIcon("/svg/arrow-clockwise.svg", "#2F4F4F", 25, 25));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jt_author.setText("");
                jt_inprice.setText("");
                jt_outprice.setText("");
                jt_isbn.setText("");
                jt_name.setText("");
                jt_num.setValue(0);
            }
        });
//        btnNewButton.setFont(new Font("宋体", Font.BOLD, 18));
        btnNewButton.setBounds(31, 312, 113, 60);
        panel_3_3.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("添加新书");
        btnNewButton_1.setIcon(svg.getSVGIcon("/svg/check-square.svg", "#2F4F4F", 25, 25));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				获取基本信息
                String bookname = jt_name.getText();
                String author = jt_author.getText();
                String inprice = jt_inprice.getText();
                String outprice = jt_outprice.getText();
                String num = jt_num.getValue().toString();
                String isbn_str = jt_isbn.getText();
//				获取时间
                String time = ConnectionManager.gettime();
                if (bookname.equals("") || author.equals("") || inprice.equals("") || outprice.equals("") || num.equals("") || isbn_str.equals(""))
                    JOptionPane.showMessageDialog(null, "字段不能为空。", "错误", JOptionPane.ERROR_MESSAGE);
                if (!isbn.isValidISBN(isbn_str)) {
                    JOptionPane.showMessageDialog(null, "ISBN号不符合规则。", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Integer.parseInt(num) <= 0) {
                    JOptionPane.showMessageDialog(null, "数量不能为空。", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    if (Double.parseDouble(inprice) <= 0 || Double.parseDouble(inprice) <= 0) {
                        JOptionPane.showMessageDialog(null, "价格不合法。", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e2) {
                    JOptionPane.showMessageDialog(null, "价格不合法。", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

//				判断当前要添加的书籍是否存在书库中
                boolean exist = false;
                int booknum1 = 0;
                String sqlString = "select * from book_stack where ISBN=?";
                try {
                    ResultSet set = ConnectionManager.query(sqlString, new Object[]{isbn_str});
                    while (set.next()) {
//						书已经存在
                        exist = true;
                        booknum1 = set.getInt("num");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
//				书已经存在
//				更新数量
                if (exist) {
                    int booknum2 = booknum1 + Integer.parseInt(jt_num.getValue().toString());
//					更新表数据
                    String sql6 = "update book_stack set num=? where ISBN=?";
//					String sql7="insert to new_book_in set num=? where ISBN=?";
                    try {
                        int n = ConnectionManager.Update(sql6, new Object[]{booknum2, isbn_str});
                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "图书已存在，更新数量成功！原有" + booknum1 + ",现有" + booknum2 +"本。", "提示", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "更新数量错误！", "警告", JOptionPane.WARNING_MESSAGE);
                        }

                        String sql1 = "insert into new_book_in values(?,?,?,?,?,?,?,?);";
                        try {
                            int m = ConnectionManager.Update(sql1, new Object[]{null, isbn_str, bookname, author, Double.parseDouble(inprice), Integer.parseInt(num), time, Double.parseDouble(outprice)});
                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "添加新书成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        update_table_items();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
//					刷新表格数据

                } else {
//					插入新书表
                    String sql1 = "insert into new_book_in values(?,?,?,?,?,?,?,?);";
                    try {
                        int n = ConnectionManager.Update(sql1, new Object[]{null, isbn_str, bookname, author, Double.parseDouble(inprice), Integer.parseInt(num), time, Double.parseDouble(outprice)});
                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "添加新书成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

                        }

                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

//					计算标价
                    double mark_price = Double.parseDouble(outprice);
//					插入库存表
                    String sql2 = "insert into book_stack values(?,?,?,?,?)";
                    try {
                        int m = ConnectionManager.Update(sql2, new Object[]{isbn_str, bookname, author, num, mark_price});
                        if (m > 0) {
                            JOptionPane.showMessageDialog(null,"插入成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "插入发生错误！", "警告", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    update_table_items();


                }
//				
//				
            }
        });
//        btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 18));
        btnNewButton_1.setBounds(167, 312, 147, 30);
        panel_3_3.add(btnNewButton_1);

//        JLabel lblNewLabel_4 = new JLabel("图书信息");
//        lblNewLabel_4.setFont(new Font(lblNewLabel_4.getFont().getFontName(), Font.PLAIN, 21));
//        lblNewLabel_4.setBounds(200, 20, 203, 24);
//        panel.add(lblNewLabel_4);
        JButton btnNewButton_1_1 = new JButton("删除图书");
        btnNewButton_1_1.setIcon(svg.getSVGIcon("/svg/trash.svg", "#2F4F4F", 25, 25));
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				根据ISBN码删除图书
                String isbnString = jt_isbn.getText();
                String sql = "delete from new_book_in where ISBN=?";
                String sql1 = "delete from book_stack where ISBN=?";
                try {
                    int n = ConnectionManager.Update(sql, new Object[]{isbnString});
                    if (n > 0) {
                        JOptionPane.showInternalMessageDialog(null, isbnString + "图书已经删除！", "提示：", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showInternalMessageDialog(null, "操作不成功！", "提示：", JOptionPane.INFORMATION_MESSAGE);

                    }
                    int m = ConnectionManager.Update(sql1, new Object[]{isbnString});
                    if (m > 0) {
                        JOptionPane.showMessageDialog(null, "从库存中已经删除！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "从库存中删除失败！", "警告", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

//				刷新数据
                update_table_items();


            }
        });
//        btnNewButton_1_1.setFont(new Font("宋体", Font.BOLD, 18));
        btnNewButton_1_1.setBounds(504, 312, 138, 30);
        panel_3_3.add(btnNewButton_1_1);

        JButton btnNewButton_2 = new JButton("修改图书");
        btnNewButton_2.setIcon(svg.getSVGIcon("/svg/pencil-square.svg", "#2F4F4F", 25, 25));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				获取要修改的信息
                String bookname = jt_name.getText();
                String author = jt_author.getText();
                String in_price = jt_inprice.getText();
                String out_price = jt_outprice.getText();
                String num = jt_num.getValue().toString();
                String isbnString = jt_isbn.getText();
//				保存修改的信息根据
                String sql = "UPDATE new_book_in SET bookname=?,author=?,cost_price=?,mark_price=?,num=? where ISBN=?";
//				执行
                try {
//					返回结果
                    int n = ConnectionManager.Update(sql, new Object[]{bookname, author, in_price, out_price, num, isbnString});
                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "图书信息修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "图书信息修改失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
//					

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

//				修改库存表
                double markprice = Double.parseDouble(in_price) * mainFrame.inprice_add;//计算标价
                String sqlString = "update book_stack set bookname=?,author=?,num=?,markprice=? where ISBN=?";
//				执行
                try {
                    int m = ConnectionManager.Update(sqlString, new Object[]{bookname, author, num, markprice, isbnString});
                    if (m > 0) {
                        System.out.println("t2信息修改成功！");
                    } else {
                        System.out.println("t2修改发生error！");
                    }
//					刷新数据
                    update_table_items();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
//        btnNewButton_2.setFont(new Font("宋体", Font.BOLD, 18));
        btnNewButton_2.setBounds(337, 312, 138, 30);
        panel_3_3.add(btnNewButton_2);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("进书记录", svg.getSVGIcon("/svg/bag.svg", "#2F4F4F", 20, 20), panel_2, null);
        panel_2.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 42, 685, 415);
        panel_2.add(scrollPane_1);
        Object[][] objects2 = null;
        Object[] objects = null;
        objects = new Object[]{"序号", "ISBN", "书名", "作者", "进价", "标价", "数量", "入库时间"};
        String sql1 = "SELECT ISBN,bookname,author,cost_price,mark_price,num,time,mark_price FROM new_book_in;";
        ResultSet set = ConnectionManager.query(sql1, new Object[]{}); // 计数 int
        int count = 0;
        while (set.next()) {
            count++;
        }
        jt_get_all.setText(Integer.toString(count));
        objects2 = ConnectionManager.getSetArrays(set);
//		初始化
        table_in_book = new JTable(objects2, objects);
        table_in_book.setFont(new Font("宋体", Font.BOLD, 13));
        scrollPane_1.setViewportView(table_in_book);

        JLabel lblNewLabel_5 = new JLabel("总计：");
//        lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_5.setBounds(536, 10, 58, 28);
        panel_2.add(lblNewLabel_5);

        jt_get_all.setEditable(false);
        jt_get_all.setBounds(578, 10, 97, 28);
        panel_2.add(jt_get_all);
        jt_get_all.setColumns(10);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("库存记录", svg.getSVGIcon("/svg/house.svg", "#2F4F4F", 20, 20), panel_1, null);
        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 42, 685, 415);
        panel_1.add(scrollPane);
//		库存记录
        Object[] a = {"序号", "ISBN", "书名", "作者", "数量", "标价"};
        String sql2 = "select * from book_stack";
        ResultSet set1 = ConnectionManager.query(sql2, new Object[]{});
//		计数
        int count1 = 0;
        while (set1.next()) {
            count1++;
        }
        jt_get_sum.setText(Integer.toString(count1));
        Object[][] objects3 = ConnectionManager.getSetArrays(set1);

        jt_get_sum.setEditable(false);
        jt_get_sum.setColumns(10);
        jt_get_sum.setBounds(578, 10, 97, 28);
        panel_1.add(jt_get_sum);

        table_bookstock = new JTable(objects3, a);
//        table_bookstock.setFont(new Font("宋体", Font.BOLD, 13));
        scrollPane.setViewportView(table_bookstock);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(28, 14, 58, 15);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_5_1 = new JLabel("总计：");
//        lblNewLabel_5_1.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_5_1.setBounds(536, 10, 58, 28);
        panel_1.add(lblNewLabel_5_1);
    }

    /**
     * 获取ISBN框内容并查询
     */
    public static void checkBook() {
//		获取ISBN框的内容
        String str_isbn = jt_isbn.getText();
//		查询该ISBN是否存在
        String sql = "select * from new_book_in where ISBN=?;";
        try {
            ResultSet set = ConnectionManager.query(sql, new Object[]{str_isbn});
            boolean a = false;
            while (set.next()) {
//					不为空，获取部分数据
                String name = set.getString("bookname");
                String author = set.getString("author");
                int num = set.getInt("num");
                double inprice = set.getDouble("cost_price");
                double outprice = set.getDouble("mark_price");
//                System.out.println(name + author + num + inprice);
                jt_author.setText(author);
                jt_name.setText(name);
                jt_inprice.setText(Double.toString(inprice));
                jt_outprice.setText(Double.toString(outprice));
                jt_num.setValue(num);
                a = true;

            }
            if (a != true) {
                jt_author.setText("");
                jt_inprice.setText("");
                jt_name.setText("");
                jt_num.setValue(0);
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    /**
     * 刷新库存表格数据
     */
    public static void UpdateBookStackTable() {
//		刷新库存
        Object[] a = {"序号", "ISBN", "书名", "作者", "数量", "标价"};
        String sql3 = "select * from book_stack";
        ResultSet set1 = null;
        try {
            set1 = ConnectionManager.query(sql3, new Object[]{});
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Object[][] objects3 = null;
        try {
            objects3 = ConnectionManager.getSetArrays(set1);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        TableModel dataModel1 = new DefaultTableModel(objects3, a);
        table_bookstock.setModel(dataModel1);
//        JOptionPane.showMessageDialog(null, "库存表格更新成功。", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 刷新进书表
     */
    public static void UpdateNewBookInTable() {
        Object[] objects = {"序号", "ISBN", "书名", "作者", "进价", "标价", "数量", "入库时间"};
        String sql2 = "SELECT ISBN,bookname,author,cost_price,mark_price,num,time FROM new_book_in;";
        ResultSet set = null;
        try {
            set = ConnectionManager.query(sql2, new Object[]{});
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
//		刷新新书表表格数据
        TableModel dataModel = new DefaultTableModel(objects2, objects);
        table_in_book.setModel(dataModel);
        System.out.println("进书表格刷新!");
    }

    /**
     * 统计结果集数目
     *
     * @throws SQLException
     */
    public static int getItems(String sqlString) throws SQLException {
//		查询结果
        int count = 0;
        ResultSet set = ConnectionManager.query(sqlString, new Object[]{});
        while (set.next()) {
            count++;
        }
        return count;
    }

    /**
     * 刷新表格并且更新统计
     */
    public static void update_table_items() {
//		刷新新书表记录
        UpdateNewBookInTable();
        try {
            int count1 = getItems("select * from new_book_in");
            jt_get_all.setText(Integer.toString(count1));
            System.out.println("统计1更新完毕");
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
//		刷新库存表记录
        UpdateBookStackTable();
        try {
            int count2 = getItems("select * from book_stack;");
            jt_get_sum.setText(Integer.toString(count2));
            System.out.println("统计2更新完毕");
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
