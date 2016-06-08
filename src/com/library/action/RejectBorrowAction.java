package com.library.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.opensymphony.xwork2.Action;

public class RejectBorrowAction implements Action {

	public String execute() throws Exception {
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String bidStr = request.getParameter("bid");
		int bid = Integer.parseInt(bidStr);
		Actions actions = new Actions();
		if(actions.rejectBorrow(bid)){
			msg = "成功拒绝对方的借阅请求~";
		}
		else{
			msg = "出错啦，请重试~";
		}
		ServletActionContext.getResponse().setContentType("text/xml;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;	
	}

}
