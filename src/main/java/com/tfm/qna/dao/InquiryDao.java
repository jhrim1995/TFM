package com.tfm.qna.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tfm.qna.vo.Faq;
import com.tfm.qna.vo.Inquiry;

public class InquiryDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static DataSource ds;
	
	public InquiryDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
			
		}catch(NamingException e) {
			System.out.println("InquiryDao() : NamingException");
			e.printStackTrace();
		}
	}
	
	public int getInquiryCount(String type, String keyword) {
		String sqlInquiry = "SELECT COUNT(*) FROM InquirySQL "
				+ " WHERE " + type + " LIKE '%' || ? || '%'";
		int count = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInquiry);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(SQLException e) {
			System.out.println("InquiryDao - getInquiryCount() : SQLExeption");
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	public ArrayList<Inquiry> searchList(
			String type, String keyword, int startRow, int endRow) {
		
		String sqlinquiryList = "SELECT * FROM ( "
				+ "	SELECT ROWNUM num, sub.* FROM "
				+ " (SELECT * FROM InquirySQL WHERE " + type
				+ "	LIKE ? ORDER BY no DESC) sub) "
				+ "	WHERE num >= ? AND num <= ?";
		ArrayList<Inquiry> iList = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlinquiryList);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			iList = new ArrayList<>();
			while(rs.next()) {
				Inquiry i = new Inquiry();
				i.setNo(rs.getInt("no"));
				i.setTitle(rs.getString("title"));
				i.setWriter(rs.getString("writer"));
				i.setContent(rs.getString("content"));
				i.setRegDate(rs.getTimestamp("reg_date"));
				i.setReadCount(rs.getInt("read_count"));
				i.setPass(rs.getString("pass"));
				
				iList.add(i);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return iList;
	}
	
	public int getInquiryCount() {
		String sqlInquiry = "SELECT COUNT(*) FROM InquirySQL";
		int count = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInquiry);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			}catch(SQLException e) {
				System.out.println("InquiryDao - getInquiryCount() : SQLExeption");
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	public ArrayList<Inquiry> inquiryList(int startRow, int endRow) {
		String sqlinquiryList = "SELECT * FROM (SELECT ROWNUM num, sub.* "
				+ "	FROM (SELECT * FROM InquirySQL ORDER BY no DESC) sub) "
				+ "	WHERE num >= ? AND num <= ?";
		ArrayList<Inquiry> iList = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sqlinquiryList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			iList = new ArrayList<>();
			
			while(rs.next()) {
				Inquiry i = new Inquiry();
				i.setNo(rs.getInt("no"));
				i.setTitle(rs.getString("title"));
				i.setWriter(rs.getString("writer"));
				i.setContent(rs.getString("content"));
				i.setRegDate(rs.getTimestamp("reg_date"));
				i.setReadCount(rs.getInt("read_count"));
				i.setPass(rs.getString("pass"));
				
				iList.add(i);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			return iList;
		}

	public Inquiry getInquiry(int no, boolean state) {
		String sqlInquiryList = "SELECT * FROM InquirySQL WHERE no=?";
		String countSql = "UPDATE InquirySQL SET read_count = read_count + 1 WHERE no=?";
		Inquiry i = null;
		
		try {
			conn = DBManager.getConnection();
			DBManager.setAutoCommit(conn, false);
			
			if(state) {
				pstmt = conn.prepareStatement(countSql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			}
			
			pstmt = conn.prepareStatement(sqlInquiryList);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				i = new Inquiry();
				i.setNo(rs.getInt("no"));
				i.setTitle(rs.getString("title"));
				i.setWriter(rs.getString("writer"));
				i.setContent(rs.getString("content"));
				i.setRegDate(rs.getTimestamp("reg_date"));
				i.setReadCount(rs.getInt("read_count"));
				i.setPass(rs.getString("pass"));
				}
			
			DBManager.commit(conn);
			
		}catch(SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return i;
	}
	
	public void insertInquiry(Inquiry i) {
		String insertInquiry = "INSERT INTO InquirySQL(no, title, writer, content,"
				+ " reg_date, read_count, pass)"
				+ " VALUES(InquirySQL_seq.NEXTVAL, ?, ?, ?, SYSDATE, 0, ?)";
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(insertInquiry);
			pstmt.setString(1, i.getTitle());
			pstmt.setString(2, i.getWriter());
			pstmt.setString(3, i.getContent());
			pstmt.setString(4, i.getPass());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			DBManager.close(conn, pstmt);
		}
	}	
	
	
}
