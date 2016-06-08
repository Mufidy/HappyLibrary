package com.library.userAction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Book;
import com.library.material.Borrow;
import com.library.material.User;
import com.opensymphony.xwork2.Action;

class myBook{
	public myBook(){
		
	}
	int myBid;
	String myBookName;
	String borrowedUserName;
	String borrowedDate;
	
	public int getMyBid() {
		return myBid;
	}
	public void setMyBid(int myBid) {
		this.myBid = myBid;
	}
	public String getMyBookName() {
		return myBookName;
	}
	public void setMyBookName(String myBookName) {
		this.myBookName = myBookName;
	}
	public String getBorrowedUserName() {
		return borrowedUserName;
	}
	public void setBorrowedUserName(String borrowedUserName) {
		this.borrowedUserName = borrowedUserName;
	}
	public String getBorrowedDate() {
		return borrowedDate;
	}
	public void setBorrowedDate(String borrowedDate) {
		this.borrowedDate = borrowedDate;
	}
	
	
}

public class GetUserInfoAction implements Action {
	List<myBook> myBookList = new ArrayList<myBook>();
	List<myBook> myBorrowList = new ArrayList<myBook>();
	User user = new User();
	int bookNum;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public List<myBook> getMyBookList() {
		return myBookList;
	}

	public void setMyBookList(List<myBook> myBookList) {
		this.myBookList = myBookList;
	}

	public List<myBook> getMyBorrowList() {
		return myBorrowList;
	}

	public void setMyBorrowList(List<myBook> myBorrowList) {
		this.myBorrowList = myBorrowList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		Object objsss = session.getAttribute("uid");
		if(objsss==null){
			return "Fail";
		}
		
		int uid = (int) session.getAttribute("uid");
		Actions actions = new Actions();
		user = actions.getUserDetail(uid);
		
		List<Book> myBook = actions.getMyBook(uid);
		List<Book> myBorrow = actions.getMyBorrow(uid);		
		bookNum = myBook.size();
		for (int i=0;i<bookNum;i++){
			Book b = myBook.get(i);
			myBook myBookTemp = new myBook();
			myBookTemp.setMyBid(b.getBid());
			myBookTemp.setMyBookName(b.getBookname());
			List<Borrow> borrows = actions.getOneBorrow(b.getBid());
			if(borrows.size()>0){
				Borrow borrow = borrows.get(0);
				User userTemp = actions.getUserDetail(borrow.getUser().getUid());
				myBookTemp.setBorrowedUserName(userTemp.getUsername());
				long timestamp = borrow.getBorrowdate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(new Date(timestamp*1000L));
				myBookTemp.setBorrowedDate(date);
			}else{
				myBookTemp.setBorrowedUserName("null");
				myBookTemp.setBorrowedDate("");
			}
			myBookList.add(myBookTemp);
		}
		
		for(int i=0;i<myBorrow.size();i++){
			Book b = myBorrow.get(i);
			myBook myBookTemp = new myBook();
			myBookTemp.setMyBid(b.getBid());
			myBookTemp.setMyBookName(b.getBookname());
			List<Borrow> borrows = actions.getOneBorrow(b.getBid());
			if(borrows.size()>0){
				Borrow borrow = borrows.get(0);
				User userTemp = actions.getUserDetail(b.getUser().getUid());
				myBookTemp.setBorrowedUserName(userTemp.getUsername());
				long timestamp = borrow.getBorrowdate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(new Date(timestamp*1000L));
				myBookTemp.setBorrowedDate(date);
			}else{
				myBookTemp.setBorrowedUserName("null");
				myBookTemp.setBorrowedDate("");
			}
			myBorrowList.add(myBookTemp);
		}
		
		return "Success";
	}

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

}
