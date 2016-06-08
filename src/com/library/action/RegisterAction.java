package com.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.User;
import com.opensymphony.xwork2.Action;

public class RegisterAction implements Action {
	private User user = new User();

	public String execute() throws Exception {
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String strLoginJson = request.getParameter("registerInfo");
		JSONObject jsonObj = JSONObject.fromObject(strLoginJson);
		user.setUid(jsonObj.getInt("uid"));
		user.setPassword(jsonObj.getString("password"));
		user.setUsername(jsonObj.getString("username"));
		user.setScore(100);
		Actions actions = new Actions();
		if(actions.addUser(user)){
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
