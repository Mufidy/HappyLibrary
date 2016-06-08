package com.library.teacherAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Teacher;
import com.opensymphony.xwork2.Action;

public class TeacherLoginAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String strLoginJson = request.getParameter("loginInfo");
		
		JSONObject jsonObj = JSONObject.fromObject(strLoginJson);	
		int teacherid = jsonObj.getInt("tid");
		String password = jsonObj.getString("password");
		Teacher teacher = new Teacher();
		teacher.setId(teacherid);
		teacher.setPassword(password);
		
		Actions actions = new Actions();
		Boolean flag = actions.checkTeacher(teacher);
		
		if(flag == true){
			msg = "loginSuccess";
			session.setAttribute("uid", "88888888"); 
			session.setAttribute("username", "teacher");
		}else{
			msg = "loginFail";
		}
		
		ServletActionContext.getResponse().getWriter().print(msg);		
		return null;
	}

}
