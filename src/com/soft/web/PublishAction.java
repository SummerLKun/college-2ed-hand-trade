package com.soft.web;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.soft.dao.GoodsDao;

import net.sf.json.JSONObject;

public class PublishAction extends ActionSupport implements ServletRequestAware{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String result;
	private HttpSession session;
	private File image;//上传文件
	private String imageFileName; //文件名称
    private String imageContentType; //文件类型
	private String imgURL;

	//添加变量
	public String name;
	public String detail;
	public String price;
	public String type;
	public String tel;

	public String userid;

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String execute(){
		try{
			//上传文件图片
			System.out.println("in class PublishAction");
			String realpath = ServletActionContext.getServletContext().getRealPath("/images");
			System.out.println("in PublishAction/execute realpath: "+realpath);
			if (image != null) {
	            File savefile = new File(new File(realpath), imageFileName);
	            if (!savefile.getParentFile().exists())
	                savefile.getParentFile().mkdirs();
	            FileUtils.copyFile(image, savefile);
	            ActionContext.getContext().put("message", "文件上传成功");
	        }

			imgURL = "http://localhost:8080/CurriculumDesign2016/images/"+imageFileName;
//			imgURL = imageFileName+"\\"+imageFileName;
//			System.out.println("in class PublishAction imgURL = "+imgURL.replaceAll("\\\\", "\\\\\\\\"));
			//测试图片地址
//			System.out.println("in class PublishAction imgURL = "+imgURL);

			Map<String,Object> map = new HashMap<String,Object>();
			userid = (String)ActionContext.getContext().getSession().get("userid");
//			System.out.println("userid:%%%%%%"+userid);// 测试获取的session
//			Map<String,Object> map = new HashMap<String,Object>();
//			name = request.getParameter("name");
//			detail = request.getParameter("detail");
//			price = request.getParameter("price");
//			type = request.getParameter("type");
//			tel = request.getParameter("tel");

			System.out.println("in class PublishAction 插入的数据是：name:"+name+" imgURL:"+imgURL+" detail:"+detail+" price:"+price+" type:"+type+" tel:"+tel+"userid"+userid);
			GoodsDao goodsdao = new GoodsDao();
			boolean tt =goodsdao.insertIntoGoods(name,imgURL,detail,price,type,tel,userid);
			if (tt) {
				System.out.println("n class PublishAction 插入成功");
			} else {
				System.out.println("n class PublishAction 插入fail");

			}
//			JSONObject json = JSONObject.fromObject(map);
//			result = json.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

}
