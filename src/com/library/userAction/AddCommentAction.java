package com.library.userAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.opensymphony.xwork2.Action;

public class AddCommentAction implements Action {

	@Override
	public String execute() throws Exception {
		String msg = null;
		// TODO Auto-generated method stub		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String uidstr = session.getAttribute("uid").toString();
		int uid = Integer.parseInt(uidstr);
		
		String strJson = request.getParameter("commentInfo");		
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		int bid = jsonObj.getInt("bid");
		String comment = jsonObj.getString("comment");
		
		Actions actions = new Actions();
		Boolean flag = actions.addComment(uid, bid, 0, comment);
		if(flag==true){
			msg = "addSuccess";
		}else{
			msg = "addFail";
		}
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}

}
