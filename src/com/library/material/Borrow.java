package com.library.material;

/**
 * Borrow entity. @author MyEclipse Persistence Tools
 */

public class Borrow implements java.io.Serializable {

	// Fields

	private Integer id;
	private Book book;
	private User user;
	private Integer borrowstatus;
	private Long borrowdate;
	private Long returndate;
	private Long returndate2;

	// Constructors

	/** default constructor */
	public Borrow() {
	}

	/** full constructor */
	public Borrow(Book book, User user, Integer borrowstatus, Long borrowdate,
			Long returndate, Long returndate2) {
		this.book = book;
		this.user = user;
		this.borrowstatus = borrowstatus;
		this.borrowdate = borrowdate;
		this.returndate = returndate;
		this.returndate2 = returndate2;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getBorrowstatus() {
		return this.borrowstatus;
	}

	public void setBorrowstatus(Integer borrowstatus) {
		this.borrowstatus = borrowstatus;
	}

	public Long getBorrowdate() {
		return this.borrowdate;
	}

	public void setBorrowdate(Long borrowdate) {
		this.borrowdate = borrowdate;
	}

	public Long getReturndate() {
		return this.returndate;
	}

	public void setReturndate(Long returndate) {
		this.returndate = returndate;
	}

	public Long getReturndate2() {
		return this.returndate2;
	}

	public void setReturndate2(Long returndate2) {
		this.returndate2 = returndate2;
	}

}