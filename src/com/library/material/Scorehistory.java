package com.library.material;

/**
 * Scorehistory entity. @author MyEclipse Persistence Tools
 */

public class Scorehistory implements java.io.Serializable {

	// Fields

	private Integer sid;
	private User user;
	private Long scoredate;
	private Integer score;
	private String reason;

	// Constructors

	/** default constructor */
	public Scorehistory() {
	}

	/** full constructor */
	public Scorehistory(User user, Long scoredate, Integer score, String reason) {
		this.user = user;
		this.scoredate = scoredate;
		this.score = score;
		this.reason = reason;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getScoredate() {
		return this.scoredate;
	}

	public void setScoredate(Long scoredate) {
		this.scoredate = scoredate;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}