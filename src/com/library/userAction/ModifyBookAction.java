package com.library.userAction;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Book;
import com.library.material.Category;
import com.opensymphony.xwork2.Action;

public class ModifyBookAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String strJson = request.getParameter("bookInfo");
		System.out.println(strJson);
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		int bid = jsonObj.getInt("bid");
		String bookname = jsonObj.getString("bookname");
		String author = jsonObj.getString("author");
		int categoryid = jsonObj.getInt("category");
		String press = jsonObj.getString("press");
		Actions actions = new Actions();
		
		Book book = actions.getBookDetail(bid);
		book.setAuthor(author);
		book.setBookname(bookname);
		Category category = new Category();
		category.setCid(categoryid);
		book.setCategory(category);
		book.setPress(press);
		//book.setBookstatus(1);
		
		if(actions.modifyBook(bid,book)){
			msg = "success";
		}else{
			msg = "fail";
		}
		
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}

}
