package com.soft.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.soft.dao.UserDao;

import net.sf.json.JSONObject;

public class RegistAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String sno;
	public String userid;
	public String username;
	public String password;
	public String tel;
	public String email;
	
	private HttpServletRequest request;
	private String result; 
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
	public String execute(){
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			sno = request.getParameter("sno");
			System.out.println("**********ajax: sno:  "+sno);//测试regist.html发Ajax传过来的sno
			UserDao db = new UserDao();
			boolean tt = db.checkId(sno+"");
			if (tt) {
				map.put("url", "main.jsp");
				map.put("flag", true);
			}else {
				map.put("flag", false);
			}
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String insertIntoDBUsers(){
		try {
			System.out.println("*****************in function RegistAction/insertIntoDBUsers()");
//			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> rsmap = new HashMap<String,Object>();
			userid = request.getParameter("userid");
			username = request.getParameter("username");
			password = request.getParameter("password");
			tel = request.getParameter("tel");
			email = request.getParameter("email");
//			map.put("userid", userid);
//			map.put("username", username);
//			map.put("password", password);
//			map.put("tel", tel);
//			map.put("email", email);
//			System.out.println("*************ajax:"+map);
			UserDao db = new UserDao();
			boolean tt = db.insertIntoUsers(userid, username, password, tel, email);
			if (tt) {
				rsmap.put("url", "main.jsp");
				rsmap.put("flag", true);
			}else {
				rsmap.put("flag", false);
			}
			JSONObject json = JSONObject.fromObject(rsmap);
			result = json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
