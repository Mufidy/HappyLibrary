package com.library.teacherAction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.library.hibernate.Actions;
import com.library.material.Book;
import com.library.material.Borrow;
import com.library.material.Scorehistory;
import com.library.material.User;
import com.opensymphony.xwork2.Action;

class Student{
	int studentID;
	String StudentName;
	int score;
	List<MyHaveBook> myHaveBookList = new ArrayList<MyHaveBook>();
	List<MyBorrowBook> myBorrowBookList = new ArrayList<MyBorrowBook>();
	List<Scorehistory> scoreHistoryList = new ArrayList<Scorehistory>();
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<MyHaveBook> getMyHaveBookList() {
		return myHaveBookList;
	}
	public void setMyHaveBookList(List<MyHaveBook> myHaveBookList) {
		this.myHaveBookList = myHaveBookList;
	}
	public List<MyBorrowBook> getMyBorrowBookList() {
		return myBorrowBookList;
	}
	public void setMyBorrowBookList(List<MyBorrowBook> myBorrowBookList) {
		this.myBorrowBookList = myBorrowBookList;
	}
	public List<Scorehistory> getScoreHistoryList() {
		return scoreHistoryList;
	}
	public void setScoreHistoryList(List<Scorehistory> scoreHistoryList) {
		this.scoreHistoryList = scoreHistoryList;
	}
	
}

class MyHaveBook{
	public MyHaveBook(){
		
	}
	int myHaveBookID;
	String myHaveBookName;
	public int getMyHaveBookID() {
		return myHaveBookID;
	}
	public void setMyHaveBookID(int myHaveBookID) {
		this.myHaveBookID = myHaveBookID;
	}
	public String getMyHaveBookName() {
		return myHaveBookName;
	}
	public void setMyHaveBookName(String myHaveBookName) {
		this.myHaveBookName = myHaveBookName;
	}

}

class MyBorrowBook{
	public MyBorrowBook(){
		
	}
	int borrowID;
	String borrowUserName;
	String borrowBookName;
	String borrowDate;
	int borrowStatus;
	String borrowReturnDate;
	public int getBorrowID() {
		return borrowID;
	}
	public void setBorrowID(int borrowID) {
		this.borrowID = borrowID;
	}
	public String getBorrowUserName() {
		return borrowUserName;
	}
	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}
	public String getBorrowBookName() {
		return borrowBookName;
	}
	public void setBorrowBookName(String borrowBookName) {
		this.borrowBookName = borrowBookName;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	public int getBorrowStatus() {
		return borrowStatus;
	}
	public void setBorrowStatus(int borrowStatus) {
		this.borrowStatus = borrowStatus;
	}
	public String getBorrowReturnDate() {
		return borrowReturnDate;
	}
	public void setBorrowReturnDate(String borrowReturnDate) {
		this.borrowReturnDate = borrowReturnDate;
	}
	
	
}

public class GetClassInfoAction implements Action {
	List<Student> studentList = new ArrayList<Student>();
	
	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getParameter("s");
		int sort = Integer.parseInt(str);
		Actions actions = new Actions();
		List<User> userList = actions.getAllUsers(sort);
		for(int i=0; i<userList.size(); i++){
			User user = userList.get(i);
			Student student = new Student();
			student.setStudentID(user.getUid());
			student.setStudentName(user.getUsername());
			//System.out.println(user.getScore()+"*************************************************************");
			student.setScore(user.getScore());
			
			//next List<myHaveBook>
			List<Book> myBook = actions.getMyBook(user.getUid());
			List<MyHaveBook> myHaveBookList = new ArrayList<MyHaveBook>();
			for(int j=0;j<myBook.size();j++){
				Book book = myBook.get(j);
				MyHaveBook myHaveBook = new MyHaveBook();
				int myHaveBookID = ((user.getUid())%20140000)*1000+book.getBid();
				myHaveBook.setMyHaveBookID(myHaveBookID);
				myHaveBook.setMyHaveBookName(book.getBookname());
				myHaveBookList.add(myHaveBook);
			}
			student.setMyHaveBookList(myHaveBookList);
			
			//next List<MyBorrowBook>
			List<Borrow> userBorrows = actions.getUserBorrows(user.getUid());
			List<MyBorrowBook> myBorrowBookList = new ArrayList<MyBorrowBook>();
			for(int k=0;k<userBorrows.size();k++){
				Borrow borrow = userBorrows.get(k);
				MyBorrowBook myBorrowBook = new MyBorrowBook();
				int borrowID = ((user.getUid())%20140000)*1000+borrow.getId();
				myBorrowBook.setBorrowID(borrowID);
				long intBorrowDate = borrow.getBorrowdate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String borrowDate = sdf.format(new Date(intBorrowDate*1000L));
				myBorrowBook.setBorrowDate(borrowDate);
				myBorrowBook.setBorrowStatus(borrow.getBorrowstatus());
				if(borrow.getBorrowstatus() == 2){
					long intBorrowReturn = borrow.getReturndate2();
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					String borrowReturnDate = sdf1.format(new Date(intBorrowReturn*1000L));
					myBorrowBook.setBorrowReturnDate(borrowReturnDate);
				}
				Book book = actions.getBookDetail(borrow.getBook().getBid());
				myBorrowBook.setBorrowBookName(book.getBookname());
				User user2 = actions.getUserDetail(book.getUser().getUid());
				myBorrowBook.setBorrowUserName(user2.getUsername());
				myBorrowBookList.add(myBorrowBook);
			}
			student.setMyBorrowBookList(myBorrowBookList);
			
			//next List<Scorehistory>
			List<Scorehistory> scoreHistoryList = actions.getScoreHistory(user.getUid());
			student.setScoreHistoryList(scoreHistoryList);
			
			studentList.add(student);
		}
		
		return "Success";
	}

}
