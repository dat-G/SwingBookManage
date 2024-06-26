package com.neau.bookManage.frame;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.neau.bookManage.dataTemplate.svg;
import com.neau.bookManage.jdbc.ConnectionManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.awt.Color;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class queryPanel extends JPanel {
	private JTextField jt_text;
	private JTable table;
	private JTextField jt_text2;
	private JTable table_1;
	private JTextField jt_isbn;
	private JLabel jl_result; 
	private Object[] o1;
	private JLabel jl2;
	private Object[] headrObjects;

	/**
	 * Create the panel.
	 */
	public queryPanel() throws IOException {
		try {
			UIManager.setLookAndFeel( new FlatIntelliJLaf() );
			UIManager.put( "TabbedPane.showTabSeparators", true );
		} catch( Exception ex ) {
			System.err.println( "Failed to initialize LaF" );
		}
		setBorder(new TitledBorder(null, "\u4FE1\u606F\u67E5\u8BE2\u6A21\u5757", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 24, 690, 495);
		add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("库存查询", svg.getSVGIcon("/svg/search.svg","#1296DB",20,20), panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setLayout(null);
		panel_5_1.setBounds(0, 0, 685, 490);
		panel_1.add(panel_5_1);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setBounds(0, 22, 685, 43);
		panel_5_1.add(panel_6_1);
		
		jt_text2 = new JTextField();
//		jt_text2.setFont(new Font("宋体", Font.BOLD, 20));
		jt_text2.setColumns(52);
		panel_6_1.add(jt_text2);
		
		JButton jb_find = new JButton("查询");
		jb_find.setIcon(svg.getSVGIcon("/svg/search.svg","#1296DB",20,20));
		
//		jb_find.setFont(new Font("宋体", Font.BOLD, 20));
		panel_6_1.add(jb_find);
		
		JPanel panel_7_1 = new JPanel();
		panel_7_1.setBounds(0, 67, 685, 43);
		panel_5_1.add(panel_7_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("查询方式；");
//		lblNewLabel_6_1.setFont(new Font("宋体", Font.BOLD, 18));
		panel_7_1.add(lblNewLabel_6_1);
		
		JRadioButton jrb1_isbn = new JRadioButton("ISBN");
		jrb1_isbn.setSelected(true);
//		jrb1_isbn.setFont(new Font("宋体", Font.BOLD, 18));
		panel_7_1.add(jrb1_isbn);
		
		JRadioButton jrb2_name = new JRadioButton("书名");
//		jrb2_name.setFont(new Font("宋体", Font.BOLD, 18));
		panel_7_1.add(jrb2_name);

//		按钮组
		ButtonGroup bGroup=new ButtonGroup();
		bGroup.add(jrb2_name);
		bGroup.add(jrb1_isbn);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(0, 148, 685, 310);
		panel_5_1.add(scrollPane_1_1);
		
		headrObjects=new Object[]{"序号","ISBN","书名","作者","数量","标价"};
		Object [][] numObjects= {
		};
		
		table_1 = new JTable(numObjects,headrObjects);
		scrollPane_1_1.setViewportView(table_1);
		
		JLabel lblNewLabel_7_1 = new JLabel("查询结果：");
//		lblNewLabel_7_1.setFont(new Font("宋体", Font.PLAIN, 17));
		lblNewLabel_7_1.setBounds(2, 120, 86, 18);
		panel_5_1.add(lblNewLabel_7_1);
		
		jl2 = new JLabel("");
//		jl2.setFont(new Font("宋体", Font.PLAIN, 16));
		jl2.setForeground(Color.RED);
		 jl2.setBounds(123, 111, 183, 27);
		panel_5_1.add(jl2);
		jb_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				获取文本框数据
				String text=jt_text2.getText();
				if (text.equals("")) {
					JOptionPane.showMessageDialog(null, "请先输入要查询的内容！","提示",JOptionPane.INFORMATION_MESSAGE);
				}else {
//					判断所选择的查询方法
					int isselect=0;
					if (jrb1_isbn.isSelected()) {
						isselect=1;
					}
					if (jrb2_name.isSelected()) {
						isselect=2;
					}
//					按ISBN查询结果
					if (isselect==1) {
						String sql="select ISBN,bookname,author,num,mark_price from book_stack where ISBN like '"+text+"%';";
						get_result1(sql);
						
					}
//					按书名查询
					if (isselect==2) {
						String sql="select ISBN,bookname,author,num,mark_price from book_stack where bookname like '"+text+"%';";
						get_result1(sql);
					}
					
				}
			}

		});
		
	}
		
	/**
	 * 传入SQL语句，查询结果集，并显示到表格中
	 * @param sql
	 */
	public void get_result(String sql) {

		Object[][] a=new Object[][] {};
//		查询结果：
		try {
			ResultSet set= ConnectionManager.query(sql, new Object[] {});
//			如果结果集不为空
			if (set.next()) {
//				将结果转为二维数组
				a=ConnectionManager.getSetArrays(set);
//				将数组反馈到表格
				TableModel dataModel = new DefaultTableModel(a,o1);
				table.setModel(dataModel);
				jl_result.setText("");
			}else {
				TableModel dataModel = new DefaultTableModel(new Object[][] {},o1);
				table.setModel(dataModel);
				jl_result.setText("没有查询到结果");
				System.out.println("没有查询到结果!!!");
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void get_result1(String sql) {
		// TODO Auto-generated method stub
		Object[][] a=new Object[][] {};
//		查询结果：
		try {
			ResultSet set=ConnectionManager.query(sql, new Object[] {});
//			如果结果集不为空
			if (set.next()) {
//				将结果转为二维数组
				a=ConnectionManager.getSetArrays(set);
//				将数组反馈到表格
				TableModel dataModel = new DefaultTableModel(a,headrObjects);
				table_1.setModel(dataModel);
				jl2.setText("");
			}else {
				TableModel dataModel = new DefaultTableModel(new Object[][] {},headrObjects);
				table_1.setModel(dataModel);
				
				jl2.setText("没有查询到结果");
				System.out.println("没有查询到结果!!!");
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
