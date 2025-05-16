package com.tfm.vo;

import java.sql.Timestamp;

public class AtBoard {
	
	private int at_no;
	private String m_id;
	private String title;
	private String content;
	private String pass;
	private Timestamp w_date;
	private int views;
	private int recm;
	
	public AtBoard() {}
	public AtBoard(int at_no, String m_id, String title, String content, String pass, Timestamp w_date, int views) {
		this.at_no = at_no;
		this.m_id = m_id;
		this.title = title;
		this.content = content;
		this.pass = pass;
		this.w_date = w_date;
		this.views = views;
	}
	public int getAt_no() {
		return at_no;
	}
	public void setAt_no(int at_no) {
		this.at_no = at_no;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Timestamp getW_date() {
		return w_date;
	}
	public void setW_date(Timestamp w_date) {
		this.w_date = w_date;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getRecm() {
		return recm;
	}
	public void setRecm(int recm) {
		this.recm = recm;
	}
	
}
