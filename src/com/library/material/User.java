package com.library.material;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String username;
	private String password;
	private Integer score;
	private String email;
	private Set borrows = new HashSet(0);
	private Set comments = new HashSet(0);
	private Set appliesForUid = new HashSet(0);
	private Set scorehistories = new HashSet(0);
	private Set appliesForOwnerid = new HashSet(0);
	private Set books = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer uid) {
		this.uid = uid;
	}

	/** full constructor */
	public User(Integer uid, String username, String password, Integer score,
			String email, Set borrows, Set comments, Set appliesForUid,
			Set scorehistories, Set appliesForOwnerid, Set books) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.score = score;
		this.email = email;
		this.borrows = borrows;
		this.comments = comments;
		this.appliesForUid = appliesForUid;
		this.scorehistories = scorehistories;
		this.appliesForOwnerid = appliesForOwnerid;
		this.books = books;
	}

	// Property accessors

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set getBorrows() {
		return this.borrows;
	}

	public void setBorrows(Set borrows) {
		this.borrows = borrows;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getAppliesForUid() {
		return this.appliesForUid;
	}

	public void setAppliesForUid(Set appliesForUid) {
		this.appliesForUid = appliesForUid;
	}

	public Set getScorehistories() {
		return this.scorehistories;
	}

	public void setScorehistories(Set scorehistories) {
		this.scorehistories = scorehistories;
	}

	public Set getAppliesForOwnerid() {
		return this.appliesForOwnerid;
	}

	public void setAppliesForOwnerid(Set appliesForOwnerid) {
		this.appliesForOwnerid = appliesForOwnerid;
	}

	public Set getBooks() {
		return this.books;
	}

	public void setBooks(Set books) {
		this.books = books;
	}

}