package com.library.teacherAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.opensymphony.xwork2.Action;

public class ChangePwdAction implements Action {
	
	@Override
	public String execute() throws Exception {
		String msg =null;
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String strRegisterJson = request.getParameter("changeInfo");
		JSONObject jsonObj = JSONObject.fromObject(strRegisterJson);	
		int uid = jsonObj.getInt("uid");
		String password = jsonObj.getString("pwd");
		
		Actions actions = new Actions();
		boolean flag = actions.changeStuPwd(uid, password);
		if(flag==true){
			msg = "修改成功~";
		}else{
			msg = "可能由于网络原因修改失败，请重试。";
		}
		ServletActionContext.getResponse().setContentType("text/xml;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}

}
