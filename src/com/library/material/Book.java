package com.library.material;

import java.util.HashSet;
import java.util.Set;

/**
 * Book entity. @author MyEclipse Persistence Tools
 */

public class Book implements java.io.Serializable {

	// Fields

	private Integer bid;
	private Category category;
	private User user;
	private String bookname;
	private String press;
	private String author;
	private Integer bookstatus;
	private Set comments = new HashSet(0);
	private Set borrows = new HashSet(0);
	private Set applies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Book() {
	}

	/** full constructor */
	public Book(Category category, User user, String bookname, String press,
			String author, Integer bookstatus, Set comments, Set borrows,
			Set applies) {
		this.category = category;
		this.user = user;
		this.bookname = bookname;
		this.press = press;
		this.author = author;
		this.bookstatus = bookstatus;
		this.comments = comments;
		this.borrows = borrows;
		this.applies = applies;
	}

	// Property accessors

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getPress() {
		return this.press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getBookstatus() {
		return this.bookstatus;
	}

	public void setBookstatus(Integer bookstatus) {
		this.bookstatus = bookstatus;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getBorrows() {
		return this.borrows;
	}

	public void setBorrows(Set borrows) {
		this.borrows = borrows;
	}

	public Set getApplies() {
		return this.applies;
	}

	public void setApplies(Set applies) {
		this.applies = applies;
	}

}