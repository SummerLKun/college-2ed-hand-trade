package com.soft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.soft.connection.Conn;

public class UserDao {
	
	//登陆函数，登录时验证用户名和密码
	public List<String> login(String username, String password){
		boolean tt = false;
		List<String> list = new ArrayList<String>();
		int userid = 0;
		String name = null;
		String passwd = null;
		String tel = null;
		String email = null;
		String sql = "SELECT * FROM users WHERE username=? AND `password`=?;";
		Connection conn = Conn.getConnect();
		PreparedStatement ps = null;
		ResultSet rs = null; 
		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				tt = true;
				System.out.println(username+"登陆成功");
				System.out.println("--------------------------");
				userid = rs.getInt("userid");
				name = rs.getString("username");
				passwd = rs.getString("password");
				tel = rs.getString("tel");
				email = rs.getString("email");
				list.add(userid+"");
				list.add(username);
				list.add(passwd);
				list.add(tel);
				list.add(email);
				list.add("true");//true和false用于判断登录是否成功
//				System.out.println("********!@#$%$%");
//				System.out.println("list: "+list);
//				System.out.println("rs.getInt():"+rs.getInt("userid"));
//				System.out.println("userid: "+userid+" username: "+username+" password: "+passwd+" tel: "+tel+" email: "+email);
			}else {
				System.out.println(username+"登录失败");
				System.out.println("--------------------------");
				list.add("false");
			}
		}catch(SQLException e){
			e.toString();
		}finally{
			Conn.closeConnection();
		}
		
		return list;
	}
	
	//查询userid，判断注册时sno是否重复；
	public boolean checkId(String sno){
		boolean tt = false;
		String sql = "SELECT * FROM users WHERE userid=?";
		Connection conn = Conn.getConnect();
		PreparedStatement ps = null;
		ResultSet rs = null; 
		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, sno);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				System.out.println("in UserDao/checkId"+sno+"用户已存在，不可注册false");
				tt=false;
			}else{
				System.out.println("in UserDao/checkId"+sno+"用户不存在,可以注册true");
				tt=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			Conn.closeConnection();
		}
		return tt;
	}
	
	//向users表插入数据；
	public boolean insertIntoUsers(String userid,String username,String password,String tel,String email){
//		int userid = Integer.parseInt(map.get("userid").toString().trim());
//		String username = map.get("username").toString();
//		String password = map.get("password").toString();
//		String tel = map.get("tel").toString();
//		String email = map.get("email").toString();
		System.out.println("*****************in function UserDao/insertIntoUsers()");
		System.out.println("userid:"+userid+"username:"+username+"password:"+password+"tel:"+tel+"email"+email);
		boolean tt = false;
		String sql = "INSERT INTO users(userid,username,password,tel,email)VALUES("+userid+",'"+username+"','"+password+"','"+tel+"','"+email+"')";
		Connection conn = Conn.getConnect();
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate(sql);
			if (i==1) {
				tt=true;
			}else {
				tt=false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tt;
	}
}
