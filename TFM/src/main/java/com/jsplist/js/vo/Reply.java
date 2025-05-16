package com.jsplist.js.vo;

import java.sql.Timestamp;

public class Reply {
	private int cno;
	private int fno;
	private String mid;
	private String ccon;
	private Timestamp date;
	
	public Reply() {}
	
	public Reply(int cno, int fno, String mid, String ccon, Timestamp date) {
		super();
		this.cno = cno;
		this.fno = fno;
		this.mid = mid;
		this.ccon = ccon;
		this.date = date;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getCcon() {
		return ccon;
	}
	public void setCcon(String ccon) {
		this.ccon = ccon;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
}
