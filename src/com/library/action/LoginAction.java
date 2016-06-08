package com.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.User;
import com.opensymphony.xwork2.Action;

public class LoginAction implements Action {
	private User user = new User();
	@Override
	public String execute() throws Exception {
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String strLoginJson = request.getParameter("loginInfo");
		JSONObject jsonObj = JSONObject.fromObject(strLoginJson);
		int uid  = jsonObj.getInt("uid");
		user.setUid(uid);
		System.out.println("startuid");
		user.setPassword(jsonObj.getString("password"));
		Actions actions = new Actions();
		if(actions.checkUser(user)){
			user = actions.getUserDetail(user.getUid());
			session.setAttribute("uid", user.getUid()); 
			session.setAttribute("username", user.getUsername());
			msg = "SUCCESS";
		}
		else{
			msg = "ERROR";
		}
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
