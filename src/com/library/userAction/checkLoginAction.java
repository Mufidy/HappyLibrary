package com.library.userAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class checkLoginAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		int flag = 0;
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Object objsss = session.getAttribute("uid");
		if(objsss==null){
			flag = 0;
			msg = "{\"flag\":"+flag+"}";
		}else{
			flag = 1;
			int uid = (int) session.getAttribute("uid");
			String username = (String) session.getAttribute("username");
			
			msg = "{\"flag\":"+flag+",\"username\":\""+username+"\",\"uid\":"+uid+"}";
			
		}
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}

}
