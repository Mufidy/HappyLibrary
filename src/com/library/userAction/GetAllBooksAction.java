package com.library.userAction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Book;
import com.opensymphony.xwork2.Action;
class ABook{
	int bid;
	String bookname;
	String author;
	String ownername;
	String press;
	String category;
	int bookstatus;
	
	public int getBookstatus() {
		return bookstatus;
	}
	public void setBookstatus(int bookstatus) {
		this.bookstatus = bookstatus;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}

public class GetAllBooksAction implements Action {
	List<Book> allBooks = new ArrayList<Book>();
	List<ABook> bookList = new ArrayList<ABook>();
	int BookNum;

	public List<Book> getAllBooks() {
		return allBooks;
	}

	public void setAllBooks(List<Book> allBooks) {
		this.allBooks = allBooks;
	}

	
	public List<ABook> getBookList() {
		return bookList;
	}

	public void setBookList(List<ABook> bookList) {
		this.bookList = bookList;
	}

	public int getBookNum() {
		return BookNum;
	}

	public void setBookNum(int bookNum) {
		BookNum = bookNum;
	}

	@Override
	public String execute() throws Exception {
		String msg = null;
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String pagestr = request.getParameter("p");
		int page = Integer.parseInt(pagestr);
		String searchstr =new String(request.getParameter("searchInfo").getBytes("iso-8859-1"),"utf-8");
		JSONObject jsonObj = JSONObject.fromObject(searchstr);
		String keyname = jsonObj.getString("k");
		String s = jsonObj.getString("s");
		int searchid = Integer.parseInt(s);
	
		Actions actions = new Actions();
		if(searchid==0){
			allBooks = actions.getAllBooks();
		}
		else{
			allBooks = actions.searchBook(keyname, searchid);
		}
		
		int num = allBooks.size();
		BookNum = num;
		System.out.println(num);
		if(num > page*10){
			for(int i = (page-1)*10; (i<(page-1)*10+10)&&(i<num); i++){
				Book bookTemp = allBooks.get(i);
				ABook abook = new ABook();
				abook.setAuthor(bookTemp.getAuthor());
				abook.setBid(bookTemp.getBid());
				abook.setBookname(bookTemp.getBookname());
				abook.setCategory(actions.getCategory(bookTemp.getCategory().getCid()).getCategoryname());
				abook.setOwnername(actions.getUserDetail(bookTemp.getUser().getUid()).getUsername());
				abook.setPress(bookTemp.getPress());
				abook.setBookstatus(bookTemp.getBookstatus());
				//System.out.println(abook.getAuthor()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				bookList.add(abook);
			}
			msg = "success";
		}else{
			for(int i = (page-1)*10; (i<(page-1)*10+10)&&(i<num); i++){
				Book bookTemp = allBooks.get(i);
				ABook abook = new ABook();
				abook.setAuthor(bookTemp.getAuthor());
				abook.setBid(bookTemp.getBid());
				abook.setBookname(bookTemp.getBookname());
				//abook.setCategory(actions.get)
				abook.setCategory(actions.getCategory(bookTemp.getCategory().getCid()).getCategoryname());
				abook.setOwnername(actions.getUserDetail(bookTemp.getUser().getUid()).getUsername());
				abook.setPress(bookTemp.getPress());
				abook.setBookstatus(bookTemp.getBookstatus());
				bookList.add(abook);
			}
			msg = "success";
		}
		System.out.println("get All Books Successfully!");
		return msg;
	}

}
