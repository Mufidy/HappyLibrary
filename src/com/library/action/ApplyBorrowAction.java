package com.library.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Book;
import com.opensymphony.xwork2.Action;

public class ApplyBorrowAction implements Action {
	Book book = new Book();
	@Override
	public String execute() throws Exception {
		System.out.println("apply start");
		String msg = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String bidStr = request.getParameter("bid");
		int bid = Integer.parseInt(bidStr);
		Actions actions = new Actions();
		book = actions.getBookDetail(bid);
		int ownerID = book.getUser().getUid();
		int uid = (int) session.getAttribute("uid");
		if(ownerID == uid){
			msg = "抱歉，自己不能借阅自己的书哦~";
		}else{
			int state = actions.applyBorrow(uid, book);
			switch(state){
			case 0:
				msg = "未知错误";
				break;
			case 1:
				msg = "1";
				break;
			case 2:
				msg = "抱歉，该书已经被借出！";
				break;
			case 3:
				msg = "抱歉，该书已经被预定！";
				break;
			case 4:
				msg = "抱歉，您已超过申请借阅上限！\n同时只能借阅（包括申请）两本书哦~";
				break;
			default:
				msg = "ERROR";
			}
			
			
		}
		
		System.out.println(msg);
		ServletActionContext.getResponse().setContentType("text/xml;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

}
