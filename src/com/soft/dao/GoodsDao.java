package com.soft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.soft.bean.GoodsBean;
import com.soft.connection.Conn;

public class GoodsDao {
	//向goods表插入数据
	public boolean insertIntoGoods(String name, String img, String detail, String price, String type, String tel, String userid) {
		// TODO Auto-generated method stub
//		System.out.println("GoodsDao/insertIntoGoods------name:"+name+" img:"+img+" detail:"+detail+" price:"+price+" type:"+type+" tel:"+tel+" email:"+qq+"userid:"+userid);
		boolean tt = false;
		String sql = "INSERT INTO goods(name,img,detail,price,type,tel,userid) VALUES('"+name+"','"+img+"','"+detail+"','"+price+"','"+type+"','"+tel+"','"+userid+"')";
		Connection conn = Conn.getConnect();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();
//			System.out.println("in GoodsDao i: "+i);
			if (i==1) {
				tt=true;
			}else {
				tt=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tt;
	}
	public ArrayList<GoodsBean> loadAll(){
		ArrayList<GoodsBean> goodsDB = new ArrayList<GoodsBean>();
//		String name;
//		String img;
//		String detail;
//		String price;
//		String type;
//		String tel;
		
		String sql="SELECT NAME,img,detail,price,type,tel FROM goods";
		Connection conn = Conn.getConnect();
		PreparedStatement ps = null;
		ResultSet rs = null; 
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		
			while(rs.next()){
				GoodsBean gb = new GoodsBean();
				gb.setName(rs.getString("name"));
				gb.setImg(rs.getString("img"));
				gb.setDetail(rs.getString("detail"));
				gb.setPrice(rs.getString("price"));
				gb.setType(rs.getString("type"));
				gb.setTel(rs.getString("tel"));
				goodsDB.add(gb);
				System.out.println("in GoodsDao/loadAll: name:"+rs.getString("name")+" img:"+rs.getString("img")+" detail:"+rs.getString("detail")+" price:"+rs.getString("price")+" type:"+rs.getString("type")+" tel:"+rs.getString("tel"));
			}
//			System.out.println("in GoodsDao/loadAll list:"+goodsDB.get(0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodsDB;
}
}
