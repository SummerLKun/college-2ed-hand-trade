package com.soft.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	// 定义MySQL数据库的连接地址
		public static final String url = "jdbc:mysql://localhost:3306/keshe2016" ;
		// MySQL数据库的连接用户名
		public static final String user = "root" ;
		// MySQL数据库的连接密码
		public static final String password = "000000lk" ;
		private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
		static {// 通过静态方法加载数据库驱动
			try {
				Class.forName("com.mysql.jdbc.Driver");// 加载数据库驱动
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public static Connection getConnect(){
			Connection con=null;
				try {
					con=DriverManager.getConnection(url,user, password);
					System.out.println("——--->>数据库连接成功！");
					System.out.println("-----------------------------------");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return con;
		}
		public static boolean closeConnection() {// 关闭数据库连接的方法
			boolean isClosed = true;
			Connection conn = threadLocal.get();// 从线程中获得数据库连接
			threadLocal.set(null);// 清空线程中的数据库连接
			if (conn != null) {// 数据库连接可用
				try {
					conn.close();// 关闭数据库连接
				} catch (SQLException e) {
					isClosed = false;
					e.printStackTrace();
				}
			}
			return isClosed;
		}	
}
