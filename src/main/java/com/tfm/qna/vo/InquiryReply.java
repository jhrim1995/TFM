package com.tfm.qna.vo;

import java.sql.Timestamp;

public class InquiryReply {
	
	private int no;
	private int iNo;
	private String replyContent;
	private String replyWriter;
	private Timestamp regDate;
	
	public InquiryReply() {}
	public InquiryReply(int iNo, String replyContent, String replyWriter) {
		this.iNo = iNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
	}
	public InquiryReply(int no, int iNo, String replyContent, String replyWriter, Timestamp regDate) {
		this.no = no;
		this.iNo = iNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
		this.regDate = regDate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getiNo() {
		return iNo;
	}
	public void setiNo(int iNo) {
		this.iNo = iNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyWriter() {
		return replyWriter;
	}
	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}
