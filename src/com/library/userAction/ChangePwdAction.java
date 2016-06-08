package com.library.userAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.opensymphony.xwork2.Action;

public class ChangePwdAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		Object objsss = session.getAttribute("uid");
		if(objsss==null){
			return "Fail";
		}
		
		int uid = (int) session.getAttribute("uid");
		String strJson = request.getParameter("changeInfo");
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		String oldpsw = jsonObj.getString("oldpsw");
		String newpsw = jsonObj.getString("newpsw");
		Actions actions = new Actions();
		boolean flag = actions.setPassword(uid, oldpsw, newpsw);
		if(flag == true){
			session.setAttribute("username", null);
			session.setAttribute("uid", null);
			msg = "Success";
		}else{
			msg = "Fail";
		}
		//ServletActionContext.getResponse().setContentType("text/xml;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}

}
