package com.soft.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.soft.dao.UserDao;

import net.sf.json.JSONObject;

public class LoginAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username;
	public String password;
	
	private HttpServletRequest request;
	private String result;
	private HttpSession session; 
	
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
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public String execute() {
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			username = request.getParameter("username");
			password = request.getParameter("password");
			System.out.println("********ajax:  "+username+":"+password);//测试index.jsp发Ajax来的数据
			session = ServletActionContext.getRequest().getSession();
			UserDao db = new UserDao();
			List<String> user = db.login(username,password);
//			System.out.println("in LoginAction/execute：list.get(5) = "+user.get(5));
			if (user.get(user.size()-1)=="true") {
				map.put("url", "main.jsp");
				map.put("flag", "1");
				map.put("userid", user.get(0));
				map.put("username", user.get(1));
				//当登录成功的时候后，将user的数据存入session备用				
				ActionContext.getContext().getSession().put("userid", user.get(0));
				ActionContext.getContext().getSession().put("username", user.get(1));
				ActionContext.getContext().getSession().put("password", user.get(2));
				ActionContext.getContext().getSession().put("tel", user.get(3));
				ActionContext.getContext().getSession().put("email", user.get(4));
				//测试session值
//				System.out.println("session:"+session.getValue("email"));
			} else {
				map.put("flag", "0");
			}
			//将map对象转换成 json类型
			JSONObject json = JSONObject.fromObject(map);
			//给result赋值，传递给页面
			result = json.toString();
//			System.out.println("result: "+result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}




}
