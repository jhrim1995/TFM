package com.tfm.qna.vo;

import java.sql.Timestamp;

public class Inquiry {

	private int no;
	private String title;
	private String writer;
	private String content;
	private Timestamp regDate;
	private int readCount;
	private String pass;
	
	public Inquiry() {}
	public Inquiry(int no, String title, String writer, String content, 
			Timestamp regDate, int readCount, String pass) {
		
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.readCount = readCount;
		this.pass = pass;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
		
}
