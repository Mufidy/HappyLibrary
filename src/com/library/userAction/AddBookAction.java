package com.library.userAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Book;
import com.library.material.Category;
import com.opensymphony.xwork2.Action;

public class AddBookAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String strJson = request.getParameter("bookInfo");
		System.out.println(strJson);
		JSONObject jsonObj = JSONObject.fromObject(strJson);
		int uid = (int) session.getAttribute("uid");
		String bookname = jsonObj.getString("bookname");
		String author = jsonObj.getString("author");
		int categoryid = jsonObj.getInt("category");
		String press = jsonObj.getString("press");
		Book book = new Book();
		book.setAuthor(author);
		book.setBookname(bookname);
		Category category = new Category();
		category.setCid(categoryid);
		book.setCategory(category);
		book.setPress(press);
		book.setBookstatus(1);
		
		Actions actions = new Actions();
		if(actions.addBook(uid, book)){
			msg = "Success";
		}else{
			msg = "Fail";
		}
		
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}

}
