package com.service;

class StatusEnvoye{
	
	private String userName;
	private String datePub;
	private String contentText;
	private int nbrLike;
	private boolean like;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDatePub() {
		return datePub;
	}
	public void setDatePub(String datePub) {
		this.datePub = datePub;
	}
	public String getContentText() {
		return contentText;
	}
	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
	public int getNbrLike() {
		return nbrLike;
	}
	public void setNbrLike(int nbrLike) {
		this.nbrLike = nbrLike;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	
	
}