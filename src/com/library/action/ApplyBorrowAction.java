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
			msg = "��Ǹ���Լ����ܽ����Լ�����Ŷ~";
		}else{
			int state = actions.applyBorrow(uid, book);
			switch(state){
			case 0:
				msg = "δ֪����";
				break;
			case 1:
				msg = "1";
				break;
			case 2:
				msg = "��Ǹ�������Ѿ��������";
				break;
			case 3:
				msg = "��Ǹ�������Ѿ���Ԥ����";
				break;
			case 4:
				msg = "��Ǹ�����ѳ�������������ޣ�\nͬʱֻ�ܽ��ģ��������룩������Ŷ~";
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
