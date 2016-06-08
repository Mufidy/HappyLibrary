package com.library.userAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Apply;
import com.opensymphony.xwork2.Action;

public class GetCheckApplyAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String msg = "";
		Actions actions = new Actions();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Object objsss = session.getAttribute("uid");
		if(objsss==null){
			
		}else{
			int uid = (int) session.getAttribute("uid");
			ArrayList<Apply> applyList = actions.getCheckApply(uid);
			int count = applyList.size();
			if(count==0){
				msg  = "{\"count\":"+count+"}";
			}else{
				msg += "{\"count\":"+count+",\"apply\":[";
				for(int i=0;i<count-1;i++){
					Apply a = applyList.get(i);
					String username = actions.getUserDetail(a.getUserByOwnerid().getUid()).getUsername();
					String bookname = actions.getBookDetail(a.getBook().getBid()).getBookname();
					String status = a.getStatus();
					String msgTemp = "{\"username\":\""+username+"\",\"bookname\":\""+bookname+"\",\"status\":\""+status+"\"},";
					msg += msgTemp;
				}
				Apply a = applyList.get(count-1);
				String username = actions.getUserDetail(a.getUserByOwnerid().getUid()).getUsername();
				String bookname = actions.getBookDetail(a.getBook().getBid()).getBookname();
				String status = a.getStatus();
				String msgTemp = "{\"username\":\""+username+"\",\"bookname\":\""+bookname+"\",\"status\":\""+status+"\"}";
				msg += msgTemp;
				msg += "]}";
			}
			
		}
		System.out.println(msg);
		ServletActionContext.getResponse().setContentType("text/xml;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}

}
