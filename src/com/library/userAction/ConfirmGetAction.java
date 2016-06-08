package com.library.userAction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.opensymphony.xwork2.Action;

public class ConfirmGetAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String bidStr = request.getParameter("bid");
		int bid = Integer.parseInt(bidStr);
		Actions actions = new Actions();
		if(actions.confirmGet(bid)){
			msg = "�ѳɹ�ȷ���յ��鼮~";
		}
		else{
			msg = "��������������~";
		}
		ServletActionContext.getResponse().setContentType("text/xml;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}

}
