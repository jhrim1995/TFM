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

import com.tfm.bbs.dao.DBManager;
import com.tfm.qna.vo.Faq;

public class FaqDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static DataSource ds;
	
	public FaqDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
			
		}catch(NamingException e) {
			System.out.println("FaqDao() : NamingException");
			e.printStackTrace();
		}
	}
	
	public int getFaqCount(String type, String keyword) {
		String sqlFaq = "SELECT COUNT(*) FROM FaqSQL "
				+ " WHERE " + type + " LIKE '%' || ? || '%'";
		int count = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlFaq);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(SQLException e) {
			System.out.println("FaqDao - getFaqCount() : SQLExeption");
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	public ArrayList<Faq> searchList(
			String type, String keyword, int startRow, int endRow) {
		
		String sqlfaqList = "SELECT * FROM ( "
				+ "	SELECT ROWNUM num, sub.* FROM "
				+ " (SELECT * FROM FaqSQL WHERE " + type
				+ "	LIKE ? ORDER BY no DESC) sub) "
				+ "	WHERE num >= ? AND num <= ?";
		ArrayList<Faq> fList = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlfaqList);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			fList = new ArrayList<>();
			while(rs.next()) {
				Faq f = new Faq();
				f.setNo(rs.getInt("no"));
				f.setTitle(rs.getString("title"));
				f.setWriter(rs.getString("writer"));
				f.setContent(rs.getString("content"));
				f.setReadCount(rs.getInt("read_count"));
				
				fList.add(f);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return fList;
	}
	
	public int getFaqCount() {
		String sqlFaq = "SELECT COUNT(*) FROM FaqSQL";
		int count = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlFaq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			}catch(SQLException e) {
				System.out.println("FaqDao - getFaqCount() : SQLExeption");
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	public ArrayList<Faq> faqList(int startRow, int endRow) {
		String sqlfaqList = "SELECT * FROM (SELECT ROWNUM num, sub.* "
				+ "	FROM (SELECT * FROM FaqSQL ORDER BY no DESC) sub) "
				+ "	WHERE num >= ? AND num <= ?";
		ArrayList<Faq> fList = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sqlfaqList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			fList = new ArrayList<>();
			
			while(rs.next()) {
				Faq f = new Faq();
				f.setNo(rs.getInt("no"));
				f.setTitle(rs.getString("title"));
				f.setWriter(rs.getString("writer"));
				f.setContent(rs.getString("content"));
				f.setReadCount(rs.getInt("read_count"));
				
				fList.add(f);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			return fList;
		}
	
	public Faq getFaq(int no, boolean state) {
		String sqlFaqList = "SELECT * FROM FaqSQL WHERE no=?";
		String countSql = "UPDATE FaqSQL SET read_count = read_count + 1 WHERE no=?";
		Faq f = null;
		
		try {
			conn = DBManager.getConnection();
			DBManager.setAutoCommit(conn, false);
			
			if(state) {
				pstmt = conn.prepareStatement(countSql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			}
			
			pstmt = conn.prepareStatement(sqlFaqList);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				f = new Faq();
				f.setNo(rs.getInt("no"));
				f.setTitle(rs.getString("title"));
				f.setWriter(rs.getString("writer"));
				f.setContent(rs.getString("content"));
				f.setReadCount(rs.getInt("read_count"));
				
			}
			
			DBManager.commit(conn);
			
		}catch(SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();
		
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return f;
	}
	
	public void insertFaq(Faq f) {
		String insertFaq = "INSERT INTO FaqSQL(no, title, writer, content, read_count)"
				+ " VALUES(FaqSQL_seq.NEXTVAL, ?, ?, ?, 0)";
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(insertFaq);
			pstmt.setString(1, f.getTitle());
			pstmt.setString(2, f.getWriter());
			pstmt.setString(3, f.getContent());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public boolean isPassCheck(int no, String pass) {
		boolean isPass = false;
		String sqlPass = "SELECT pass FROM FaqSQL WHERE no=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlPass);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isPass = rs.getString(1).equals(pass);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return isPass;
	}
	
	public void updateFaq(Faq faq) {
		String sqlUpdate = "UPDATE FaqSQL set title=?, writer=?, content=? WHERE no=?";
		
		try {
			conn=DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlUpdate);
			pstmt.setString(1, faq.getTitle());
			pstmt.setString(2, faq.getWriter());
			pstmt.setString(3, faq.getContent());
			pstmt.setInt(4, faq.getNo());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public void deleteFaq(int no) {
		String sqlDelete = "DELETE FROM FaqSQL WHERE no=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	
}
