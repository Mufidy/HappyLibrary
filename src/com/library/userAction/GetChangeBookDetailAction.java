package com.library.userAction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Book;
import com.opensymphony.xwork2.Action;

public class GetChangeBookDetailAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String bidstr = request.getParameter("bid");
		int bid = Integer.parseInt(bidstr);
		Actions actions = new Actions();
		Book book = actions.getBookDetail(bid);
		
		String bookname = book.getBookname();
		String author = book.getAuthor();
		String press = book.getPress();
		String type = actions.getCategory(book.getCategory().getCid()).getCategoryname();
		
		String msg = "{\"bookname\":\""+bookname+"\",\"author\":\""+author+"\",\"press\":\""+press+"\",\"type\":\""+type+"\"}";
		
		ServletActionContext.getResponse().setContentType("text/xml;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);		
		return null;
	}

}
