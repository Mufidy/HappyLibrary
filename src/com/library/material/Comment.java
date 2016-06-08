package com.library.material;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Book book;
	private User user;
	private String comment;
	private Integer star;
	private Long commentdate;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** full constructor */
	public Comment(Book book, User user, String comment, Integer star,
			Long commentdate) {
		this.book = book;
		this.user = user;
		this.comment = comment;
		this.star = star;
		this.commentdate = commentdate;
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

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getStar() {
		return this.star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Long getCommentdate() {
		return this.commentdate;
	}

	public void setCommentdate(Long commentdate) {
		this.commentdate = commentdate;
	}

}