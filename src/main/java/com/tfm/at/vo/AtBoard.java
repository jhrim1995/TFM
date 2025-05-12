package com.tfm.at.vo;

import java.sql.Timestamp;

public class AtBoard {
	//at_no, m_id, title, content, p_date, views, fv
	private int at_no;
	private String m_id;
	private String title;
	private String content;
	private Timestamp p_date;
	private int views;
	private int fv;
	
	public AtBoard() {}
	public AtBoard(int at_no, String m_id, String title, String content, Timestamp p_date, int views, int fv) {
		this.at_no = at_no;
		this.m_id = m_id;
		this.title = title;
		this.content = content;
		this.p_date = p_date;
		this.views = views;
		this.fv = fv;
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
	public Timestamp getP_date() {
		return p_date;
	}
	public void setP_date(Timestamp p_date) {
		this.p_date = p_date;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getFv() {
		return fv;
	}
	public void setFv(int fv) {
		this.fv = fv;
	}
	
}
