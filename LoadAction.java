package com.soft.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.soft.bean.GoodsBean;
import com.soft.dao.GoodsDao;

import net.sf.json.JSONArray;

public class LoadAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		GoodsDao db = new GoodsDao();
		ArrayList<GoodsBean> goodslist = db.loadAll();
		//将map对象转换成 json类型
		JSONArray json = JSONArray.fromObject(goodslist);
		//给result赋值，传递给页面
		result = json.toString();
		System.out.println("in LoadAction/execute: result: "+result);
		return SUCCESS;
	}
}
