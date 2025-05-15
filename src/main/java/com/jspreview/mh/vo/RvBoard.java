package com.jspreview.mh.vo;

import java.sql.Timestamp;

public class RvBoard {
	
	private int r_no;
	private String m_id;
	private String title;
	private String content;
	private Timestamp p_date;
	private int readCount;
	private int views;
	private String file1;
	private String area;
	private int recommend;
		
	public RvBoard() {}

	public RvBoard(int r_no, String m_id, String title, String content, Timestamp p_date,
			int readCount,int views, String file1, String area, int recommend) {
		this.r_no = r_no;
		this.m_id = m_id;
		this.title = title;
		this.content = content;
		this.p_date = p_date;
		this.readCount = readCount;
		this.views = views;
		this.file1 = file1;
		this.area = area;
		this.recommend = recommend;
	}

	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
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

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	

	
	

	


}
