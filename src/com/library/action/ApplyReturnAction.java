package com.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.opensymphony.xwork2.Action;

public class ApplyReturnAction implements Action {
	@Override
	public String execute() throws Exception {
		System.out.println("apply start");
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String bidStr = request.getParameter("bid");
		int bid = Integer.parseInt(bidStr);
		Actions actions = new Actions();
		int uid = (int) session.getAttribute("uid");
		int flag = actions.applyReturn(uid, bid);
		if(flag == 1){
			msg = "���뻹��ɹ�����ȴ��Է�ȷ��~";
		}else{
			msg = "δ֪���������ԣ�";
		}
		ServletActionContext.getResponse().setContentType("text/xml;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}

}
