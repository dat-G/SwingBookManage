package com.lys.bms.frame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.Document;


import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.lys.bms.jdbc.ConnectionManager;


import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class libManage extends JPanel {
    private static JTextField jt_isbn;
    private static JTextField jt_name;
    private static JTextField jt_author;
    private static JTextField jt_inprice;
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
    public libManage() throws SQLException {
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
            UIManager.put( "TabbedPane.showTabSeparators", true );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
//        setBackground(new Color(175, 238, 238));
        setBorder(new TitledBorder(null, "\u56FE\u4E66\u7BA1\u7406\u6A21\u5757", TitledBorder.LEADING, TitledBorder.TOP,
                null, null));
        setLayout(null);

//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 24, 690, 426);
        add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("信息编辑", new ImageIcon(libManage.class.getResource("/img/管理.png")), panel, null);
        panel.setLayout(null);

        JPanel panel_3 = new JPanel();
        panel_3.setLayout(new GridLayout(1,4,20,0));
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
                dynISBNcomplete();
                checkBook();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                dynISBNcomplete();
                checkBook();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                dynISBNcomplete();
                checkBook();
            }

            public void dynISBNcomplete() {
//                System.out.println("Changed.");
                int caretShift = 0;
//                System.out.println(caretPos);
                String fieldText = jt_isbn.getText();
                String tmpFieldText = fieldText.replaceAll("x", "X").replaceAll("[^0-9X]+", ""); // 过滤非数字字符
                if (tmpFieldText.length() > 10) {
                    tmpFieldText = tmpFieldText.substring(0, 10);
                } // 长度过滤
                int fieldLen = tmpFieldText.length();
                StringBuffer dashStr = new StringBuffer(tmpFieldText);
                if (fieldLen >= 1) {
                    dashStr.insert(1, "-");
                    caretShift++;
                }
                if (fieldLen >= 5) {
                    dashStr.insert(6, "-");
                    caretShift++;
                }
                if (fieldLen >= 9) {
                    dashStr.insert(11, "-");
                    caretShift++;
                }
                tmpFieldText = dashStr.toString();
                if (!fieldText.equals(tmpFieldText)) {
                    String finalTmpFieldText = tmpFieldText;
                    int caretDashTweak = 0;
                    if (finalTmpFieldText.endsWith("-")) caretDashTweak = 1;
                    int finalCaretPos = fieldLen + caretShift - caretDashTweak;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            jt_isbn.setText(finalTmpFieldText);
                            jt_isbn.requestFocus();
                            jt_isbn.setCaretPosition(finalCaretPos);
                        }
                    }).start();
                }
            }

            ;
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
        panel_3_1.setLayout(new GridLayout(1,4,20,0));
        panel_3_1.setBounds(10, 60, 650, 30);
        panel.add(panel_3_1);

        JLabel lblNewLabel_2 = new JLabel("作者: ");
        lblNewLabel_2.setBounds(70, 6, 89, 28);
        panel_3_1.add(lblNewLabel_2);

        jt_author = new JTextField();
        jt_author.setColumns(25);
        jt_author.setBounds(131, 10, 171, 21);
        panel_3_1.add(jt_author);

        JLabel lblNewLabel_1_1_1 = new JLabel("进价: ");
        lblNewLabel_1_1_1.setBounds(352, 6, 75, 28);
        panel_3_1.add(lblNewLabel_1_1_1);

        jt_inprice = new JTextField();
//        jt_inprice.setFont(new Font("宋体", Font.BOLD, 18));
        jt_inprice.setColumns(25);
        jt_inprice.setBounds(420, 10, 171, 21);
        panel_3_1.add(jt_inprice);

        JPanel panel_3_2 = new JPanel();
        panel_3_2.setLayout(new GridLayout(1,4,20,0));
        panel_3_2.setBounds(10, 100, 650, 30);
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
        panel_3_3.setLayout(new GridLayout(1,4,20,0));
        panel_3_3.setBounds(10, 140, 650, 30);
        panel.add(panel_3_3);

        JButton btnNewButton = new JButton("重置");
        btnNewButton.setIcon(new ImageIcon(libManage.class.getResource("/img/重置.png")));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jt_author.setText("");
                jt_inprice.setText("");
                jt_isbn.setText("");
                jt_name.setText("");
                jt_num.setValue(0);
            }
        });
//        btnNewButton.setFont(new Font("宋体", Font.BOLD, 18));
        btnNewButton.setBounds(31, 312, 113, 30);
        panel_3_3.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("添加新书");
        btnNewButton_1.setIcon(new ImageIcon(libManage.class.getResource("/img/添加 (3).png")));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				获取基本信息
                String bookname = jt_name.getText();
                String author = jt_author.getText();
                String price = jt_inprice.getText();
                String num = jt_num.getValue().toString();
                String isbn = jt_isbn.getText();
//				获取时间
                String time = ConnectionManager.gettime();
                if (bookname.equals("") || author.equals("") || price.equals("") || num.equals("") || isbn.equals(""))
                    JOptionPane.showMessageDialog(null, "字段不能为空。", "错误", JOptionPane.ERROR_MESSAGE);
                if (!checkISBN(isbn)) {
                    JOptionPane.showMessageDialog(null, "ISBN号不符合规则。", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Integer.parseInt(num) <= 0) {
                    JOptionPane.showMessageDialog(null, "数量不能为空。", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    if (Double.parseDouble(price) <= 0) {
                        JOptionPane.showMessageDialog(null, "价格不合法。", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e2) {
                    JOptionPane.showMessageDialog(null, "价格不合法。", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

//				判断当前要添加的书籍是否存在书库中
                boolean exsit = false;
                int booknum1 = 0;
                String sqlString = "select * from book_stack where ISBN=?";
                try {
                    ResultSet set = ConnectionManager.query(sqlString, new Object[]{isbn});
                    while (set.next()) {
//						书已经存在
                        exsit = true;
                        booknum1 = set.getInt("num");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
//				书已经存在
//				更新数量
                if (exsit) {
                    int booknum2 = booknum1 + Integer.parseInt(jt_num.getValue().toString());
//					更新表数据
                    String sql6 = "update book_stack set num=? where ISBN=?";
//					String sql7="insert to new_book_in set num=? where ISBN=?";
                    try {
                        int n = ConnectionManager.Update(sql6, new Object[]{booknum2, isbn});
                        if (n > 0) {
                            System.out.println("图书已存在，更新数量成功！原有" + booknum1 + ",现有" + booknum2);
                        } else {
                            System.out.println("更新数量错误！");
                        }

                        String sql1 = "insert into new_book_in values(?,?,?,?,?,?,?);";
                        try {
                            int m = ConnectionManager.Update(sql1, new Object[]{null, isbn, bookname, author, Double.parseDouble(price), Integer.parseInt(num), time});
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
                    String sql1 = "insert into new_book_in values(?,?,?,?,?,?,?);";
                    try {
                        int n = ConnectionManager.Update(sql1, new Object[]{null, isbn, bookname, author, Double.parseDouble(price), Integer.parseInt(num), time});
                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "添加新书成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

                        }

                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

//					计算标价
                    double markprice = Double.parseDouble(price) * mainFrame.inprice_add;
//					插入库存表
                    String sql2 = "insert into book_stack values(?,?,?,?,?)";
                    try {
                        int m = ConnectionManager.Update(sql2, new Object[]{isbn, bookname, author, num, markprice});
                        if (m > 0) {
                            System.out.println("插入t2成功！");
                        } else {
                            System.out.println("插入t2发生错误！");
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
        btnNewButton_1_1.setIcon(new ImageIcon(libManage.class.getResource("/img/删 除.png")));
        btnNewButton_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				根据ISBN码删除图书
                String isbnString = jt_isbn.getText();
                String sql = "delete from new_book_in where ISBN=?";
                String sql1 = "delete from book_stack where ISBN=?";
                try {
                    int n = ConnectionManager.Update(sql, new Object[]{isbnString});
                    if (n > 0) {
                        JOptionPane.showInternalMessageDialog(null, isbnString + "图书已经删除！", "提示：",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showInternalMessageDialog(null, "操作不成功！", "提示：", JOptionPane.INFORMATION_MESSAGE);

                    }
                    int m = ConnectionManager.Update(sql1, new Object[]{isbnString});
                    if (m > 0) {
                        System.out.println("从库存中已经删除！");
                    } else {
                        System.out.println("从库存中删除失败！");
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
        btnNewButton_2.setIcon(new ImageIcon(libManage.class.getResource("/img/修改 (2).png")));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				获取要修改的信息
                String bookname = jt_name.getText();
                String author = jt_author.getText();
                String in_price = jt_inprice.getText();
                String num = jt_num.getValue().toString();
                String isbnString = jt_isbn.getText();
//				保存修改的信息根据
                String sql = "UPDATE new_book_in SET bookname=?,author=?,price=?,num=? where ISBN=?";
//				执行
                try {
//					返回结果
                    int n = ConnectionManager.Update(sql, new Object[]{bookname, author, in_price, num, isbnString});
                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "t1图书信息修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
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
        tabbedPane.addTab("进书记录", new ImageIcon(libManage.class.getResource("/img/买入.png")), panel_2, null);
        panel_2.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 42, 685, 355);
        panel_2.add(scrollPane_1);
        Object[][] objects2 = null;
        Object[] objects = null;
        objects = new Object[]{"序号", "ISBN", "书名", "作者", "进价", "数量", "入库时间"};
        String
                sql1 = "SELECT ISBN,bookname,author,price,num,time FROM new_book_in;";
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
        lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_5.setBounds(536, 9, 58, 23);
        panel_2.add(lblNewLabel_5);

        jt_get_all.setEditable(false);
        jt_get_all.setBounds(578, 9, 97, 21);
        panel_2.add(jt_get_all);
        jt_get_all.setColumns(10);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("库存记录", new ImageIcon(libManage.class.getResource("/img/库存2.png")), panel_1, null);
        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 42, 685, 345);
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
        jt_get_sum.setBounds(578, 10, 97, 21);
        panel_1.add(jt_get_sum);

        table_bookstock = new JTable(objects3, a);
        table_bookstock.setFont(new Font("宋体", Font.BOLD, 13));
        scrollPane.setViewportView(table_bookstock);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(28, 14, 58, 15);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_5_1 = new JLabel("总计：");
        lblNewLabel_5_1.setFont(new Font("宋体", Font.BOLD, 16));
        lblNewLabel_5_1.setBounds(536, 10, 58, 23);
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
                double inprice = set.getDouble("price");
                System.out.println(name + author + num + inprice);
                jt_author.setText(author);
                jt_name.setText(name);
                jt_inprice.setText(Double.toString(inprice));
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

    public static boolean checkISBN(String code) { // ISBN 规则检查
        String tmpCode = code.replaceAll("x", "X").replaceAll("[^0-9X]+", "");
        char checkDigit = tmpCode.charAt(tmpCode.length() - 1);
        String numString = tmpCode.substring(0, tmpCode.length() - 1);
        if (numString.length() > 9) return false; // 长度错误
        Integer ISBNnum;
        try {
            ISBNnum = Integer.parseInt(numString);
        } catch (NumberFormatException e) { // 字符错误
            return false;
        }
        Integer ISBNCheckSum = 0;
        for (int i = 2; i <= 10; i++) {
            ISBNCheckSum += (ISBNnum % 10) * i;
            ISBNnum /= 10;
        }
        if (11 - ISBNCheckSum % 11 == (int) checkDigit - (int) '0')
            return true;
        else if ((11 - ISBNCheckSum % 11 == 10) && (checkDigit == 'X'))
            return true;
        else return false;
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
        System.out.println("库存表格刷新!");
    }

    /**
     * 刷新进书表
     */
    public static void UpdateNewBookInTable() {
        Object[] objects = {"序号", "ISBN", "书名", "作者", "进价", "数量", "入库时间"};
        String sql2 = "SELECT ISBN,bookname,author,price,num,time FROM new_book_in;";
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
