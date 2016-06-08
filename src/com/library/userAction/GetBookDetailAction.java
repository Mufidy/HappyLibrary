package com.library.userAction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Book;
import com.library.material.Borrow;
import com.library.material.Comment;
import com.library.material.User;
import com.opensymphony.xwork2.Action;

class CommentShow{
	int commentID;
	String comment;
	String name;
	String date;
	public CommentShow(){
		
	}
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
public class GetBookDetailAction implements Action {
	Book book = new Book();
	User owner = new User();
	String borrowusername = "";
	String borrowdate = "";
	String applyflag = "";
	public String getBorrowusername() {
		return borrowusername;
	}

	public void setBorrowusername(String borrowusername) {
		this.borrowusername = borrowusername;
	}

	public String getBorrowdate() {
		return borrowdate;
	}

	public void setBorrowdate(String borrowdate) {
		this.borrowdate = borrowdate;
	}

	List<Comment> listComment = new ArrayList<Comment>();
	List<CommentShow> listCommentShow = new ArrayList<CommentShow>();
	String booktype;
	
	public String getBooktype() {
		return booktype;
	}

	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}

	public List<CommentShow> getListCommentShow() {
		return listCommentShow;
	}

	public void setListCommentShow(List<CommentShow> listCommentShow) {
		this.listCommentShow = listCommentShow;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Comment> getListComment() {
		return listComment;
	}

	public void setListComment(List<Comment> listComment) {
		this.listComment = listComment;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String bidstr = request.getParameter("bid");
		int bid = Integer.parseInt(bidstr);
		
		Actions actions = new Actions();
		book = actions.getBookDetail(bid);
		int uid = book.getUser().getUid();
		listComment = actions.getComment(bid);
		owner = actions.getUserDetail(uid);
		
		for (int i=0;i<listComment.size();i++){
			Comment comment = listComment.get(i);
			CommentShow commentShow = new CommentShow();
			User userTemp = actions.getUserDetail(comment.getUser().getUid());
			commentShow.setName(userTemp.getUsername());
			commentShow.setCommentID(comment.getId());
			commentShow.setComment(comment.getComment());
			long timestamp = comment.getCommentdate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date(timestamp*1000L));
			commentShow.setDate(date);
			listCommentShow.add(commentShow);
		}
		
		booktype = actions.getCategory(book.getCategory().getCid()).getCategoryname();
		if(book.getBookstatus()==2){
			List<Borrow> bo = actions.getSpecialBorrow(bid, 1);
			if(bo.size()>0){
				Borrow b = bo.get(0);
				borrowusername = actions.getUserDetail( b.getUser().getUid()).getUsername();
				long date = b.getBorrowdate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				borrowdate = sdf.format(new Date(date*1000L));
			}
		}else if(book.getBookstatus()==3){
			User user = actions.getApplyUseer(bid);
			borrowusername = user.getUsername();
		}
		
		System.out.println("getSuccessfully  owner"+owner.getUsername());
		return "getSuccess";
	}

}
