package com.library.material;

/**
 * Apply entity. @author MyEclipse Persistence Tools
 */

public class Apply implements java.io.Serializable {

	// Fields

	private Integer id;
	private User userByOwnerid;
	private Book book;
	private User userByUid;
	private String status;

	// Constructors

	/** default constructor */
	public Apply() {
	}

	/** full constructor */
	public Apply(User userByOwnerid, Book book, User userByUid, String status) {
		this.userByOwnerid = userByOwnerid;
		this.book = book;
		this.userByUid = userByUid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserByOwnerid() {
		return this.userByOwnerid;
	}

	public void setUserByOwnerid(User userByOwnerid) {
		this.userByOwnerid = userByOwnerid;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUserByUid() {
		return this.userByUid;
	}

	public void setUserByUid(User userByUid) {
		this.userByUid = userByUid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}