package com.library.userAction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Apply;
import com.library.material.Book;
import com.library.material.Borrow;
import com.opensymphony.xwork2.Action;

public class GetPushMsgAction implements Action {

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!GetPushMsgAction!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		int flag = 0;
		String msg = null;
		Actions actions = new Actions();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Object objsss = session.getAttribute("uid");
		if(objsss==null){
			flag = 0;
			msg = "{\"flag\":"+flag+"}";
		}else{
			int uid = (int) session.getAttribute("uid");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!GetPushMsgAction!!!!!!!uid!!!!!!!!!!!!!!!!!!!!!"+uid);
			if(uid == 888888){
				flag = 0;
				msg = "{\"flag\":"+flag+"}";
			}else{
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!GetPushMsgAction!!!!!!!uid!=888888!!!!!!!!!!!!!!!!!!!!!");
				List<Apply> applyList = actions.getMsgApply(uid);
				List<Borrow> borrowList = actions.getMsgReturn(uid);
				msg = "{\"flag\":1,\"applynum\":"+applyList.size()+",\"apply\":[";
				String msgTemp = "";
				if(applyList.size()==1){
					for(int i=0;i<applyList.size();i++){
						Apply apply = applyList.get(i);
						int bid = apply.getBook().getBid();
						Book bb = actions.getBookDetail(apply.getBook().getBid());
						String bookname = bb.getBookname();
						String username = actions.getUserDetail(apply.getUserByUid().getUid()).getUsername();
						String status = apply.getStatus();
						msgTemp = "{\"bid\":\""+bid+"\",\"bookname\":\""+bookname+"\",\"username\":\""+username+"\",\"status\":\""+status+"\"}";
						msg += msgTemp;
					}
				}else if(applyList.size()>1){
					for(int i=0;i<applyList.size()-1;i++){
						Apply apply = applyList.get(i);
						int bid = apply.getBook().getBid();
						String bookname = actions.getBookDetail(apply.getBook().getBid()).getBookname();
						String username = actions.getUserDetail(apply.getUserByUid().getUid()).getUsername();
						String status = apply.getStatus();
						msgTemp = "{\"bid\":\""+bid+"\",\"bookname\":\""+bookname+"\",\"username\":\""+username+"\",\"status\":\""+status+"\"},";
						msg += msgTemp;
					}
					Apply apply = applyList.get(applyList.size()-1);
					int bid = apply.getBook().getBid();
					String bookname = actions.getBookDetail(apply.getBook().getBid()).getBookname();
					String username = actions.getUserDetail(apply.getUserByUid().getUid()).getUsername();
					String status = apply.getStatus();
					msgTemp = "{\"bid\":\""+bid+"\",\"bookname\":\""+bookname+"\",\"username\":\""+username+"\",\"status\":\""+status+"\"}";
					msg += msgTemp;
				}	
				msg += "],\"borrownum\":"+borrowList.size()+",\"borrow\":[";
				
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!GetPushMsgAction!!!!!!!applyList success!!!!!!!!borrowList.size:"+borrowList.size());
				
				if(borrowList.size()==1){
					for(int i=0;i<borrowList.size();i++){
						Borrow borrow = borrowList.get(i);
						int borrowid = borrow.getId();
						
						Book book_1_a = actions.getBookDetail(borrow.getBook().getBid());
						String bookname = book_1_a.getBookname();
						/*------debug point-------*/
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!GetPushMsgAction!!!!!!!getBookname!!!!!!!"+bookname);
						
						String username = actions.getUserDetail(book_1_a.getUser().getUid()).getUsername();
						long returndateint = borrow.getReturndate();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String returndate = sdf.format(new Date(returndateint*1000L));
						msgTemp = "{\"borrowid\":\""+borrowid+"\",\"bookname\":\""+bookname+"\",\"username\":\""+username+"\",\"returndate\":\""+returndate+"\"}";
						msg += msgTemp;
					}
				}else if(borrowList.size()>1){
					for(int i=0;i<borrowList.size()-1;i++){
						Borrow borrow = borrowList.get(i);
						int borrowid = borrow.getId();
						
						Book book_a = actions.getBookDetail(borrow.getBook().getBid());
						String bookname = book_a.getBookname();
						/*------debug point-------*/
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!GetPushMsgAction!!!!!!!getBookname!!!!!!!"+bookname);
						
						String username = actions.getUserDetail(book_a.getUser().getUid()).getUsername();
						/*------debug point-------*/
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!GetPushMsgAction!!!!!!!getUsername!!!!!!!"+username);
						long returndateint = borrow.getReturndate();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String returndate = sdf.format(new Date(returndateint*1000L));
						msgTemp = "{\"borrowid\":\""+borrowid+"\",\"bookname\":\""+bookname+"\",\"username\":\""+username+"\",\"returndate\":\""+returndate+"\"},";
						msg += msgTemp;
					}
					/*------debug point-------*/
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!GetPushMsgAction!!!!!!!first for success!!!!!!!");
					
					Borrow borrow = borrowList.get(borrowList.size()-1);
					int borrowid = borrow.getId();
					
					Book book_b = actions.getBookDetail(borrow.getBook().getBid());
					String bookname = book_b.getBookname();
					String username = actions.getUserDetail(book_b.getUser().getUid()).getUsername();
					long returndateint = borrow.getReturndate();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String returndate = sdf.format(new Date(returndateint*1000L));
					msgTemp = "{\"borrowid\":\""+borrowid+"\",\"bookname\":\""+bookname+"\",\"username\":\""+username+"\",\"returndate\":\""+returndate+"\"}";
					msg += msgTemp;
				}
				
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!GetPushMsgAction!!!!!!!borrowList success!!!!!!!!!!!!!!");
				
				msg +="]}";
			}	
		}
		System.out.println(msg);
		ServletActionContext.getResponse().setContentType("text/xml;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(msg);
		return null;
	}

}
