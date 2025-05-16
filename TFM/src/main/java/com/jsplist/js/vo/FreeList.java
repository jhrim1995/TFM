package com.jsplist.js.vo;

import java.sql.Timestamp;

public class FreeList {
	private	int no;
	private String id;
	private String title;
	private String content;
	private Timestamp date;
	private int view;
	private int good;
	private String file;
	
	
	public FreeList() {}
	
	public FreeList(int no, String id, String title, String content, Timestamp date, int view, int good, String file) {
		super();
		this.no = no;
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
		this.view = view;
		this.good = good;
		this.file = file;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	
	
}
