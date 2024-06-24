# [点我远程调试](http://liyansheng.top/remote_help/)
> 期末数据库课程设计， **图书进销信息管理系统** 
> 
> 直接将数据库文件导入就可以快速建表

### 编码：
GBK

### 开发环境
jdk12+MySQL8.0

### 推荐用IDEA运行项目

### 补充
UI美化（引用当前系统的风格）
在每个有组件的文件的构造函数中加入以下代码即可。

```java
		try {
			UIManager.setLookAndFeel(   UIManager.getSystemLookAndFeelClassName()); 
		} catch (Exception e) {
			// TODO: handle exception
		}
```

`可能遇到的问题：`
java.sql.SQLException: Operation not allowed for a result set of type ResultSet.TYPE_FORWARD_ONLY.

```java
java.sql.SQLNonTransientConnectionException: CLIENT_PLUGIN_AUTH is required
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:110)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:97)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:89)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:63)
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:73)
```
修复：

```
PreparedStatement pst = conn.prepareStatement(sql);
ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY
```



> 
> 关于“删除”会报错问题
```
Exception in thread"AWT-EventOueue-0" java.lang.RuntimeException Create breakpoint
at javax.swing.JOptionPane.createInternalFrame(JOptionPane.java:1510)
JOptionPane:
parentComponent does not have
at javax.swing.JOptionPane.showInternaloptionDialog(JOptionPane.java:1286)at javax.swing.JoptionPane.showInternalMessageDialog(JOptionPane.java:1099)at javax.swing.JOptionPane.showInternalMessageDialog(JOptionPane.java:1073)
at com.wym.bms.frame.Books_panel$4.actionPerformed(Books_panel.java:299) <4 internal lines>
at javax.swing.plaf.basic.BasicButtonlistener.mouseReleased(BasicButtonListener.java:252) <31 internal lines>
```
 :exclamation: 升级你的JDK版本，低版本不支持这个用法

