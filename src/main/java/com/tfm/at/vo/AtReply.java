package com.tfm.at.vo;

import java.sql.*;

public class AtReply {
	
	private int c_no;
	private int at_no;
	private String m_id;
	private String c_con;
	private Timestamp c_date;
	
	public AtReply() {}
	public AtReply(int at_no, String m_id, String c_con) {
		this.at_no = at_no;
		this.m_id = m_id;
		this.c_con = c_con;
	}
	public AtReply(int c_no, int at_no, String m_id, String c_con, Timestamp c_date) {
		this.c_no = c_no;
		this.at_no = at_no;
		this.m_id = m_id;
		this.c_con = c_con;
		this.c_date = c_date;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
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
	public String getC_con() {
		return c_con;
	}
	public void setC_con(String c_con) {
		this.c_con = c_con;
	}
	public Timestamp getC_date() {
		return c_date;
	}
	public void setC_date(Timestamp c_date) {
		this.c_date = c_date;
	}
	
}
