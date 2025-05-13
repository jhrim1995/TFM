package com.tfm.at.dao;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import com.tfm.at.vo.*;

public class AtBoardDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static DataSource ds;
	
	public ArrayList<AtBoard> searchList(String type, String keyword, int startRow, int endRow) {
		String searchList = "SELECT * FROM (SELECT ROWNUM num, sa.* FROM (SELECT * FROM article WHERE " + type + " LIKE ? ORDER BY at_no DESC ) sa ) WHERE num >= ? AND num <= ?";
		
		ArrayList<AtBoard> boardList = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(searchList);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<AtBoard>();
				do {
				AtBoard b = new AtBoard();
				b.setAt_no(rs.getInt("at_no"));
				b.setM_id(rs.getString("m_id"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setPass(rs.getString("pass"));
				b.setW_date(rs.getTimestamp("w_date"));
				b.setViews(rs.getInt("views"));
				
				boardList.add(b);
			} while(rs.next()); }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException se) {}
		}
		return boardList;
	} // end searchList();
	
	public int getBoardCount(String type, String keyword) {
		String boardCount = "SELECT COUNT(*) FROM article WHERE "
				+ type + " LIKE '%' || ? || '%'";
		int count = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(boardCount);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
		return count;
	} // end getBoardCount(String type, String keyword);
	
	public int getBoardCount() {
		String boardCount = "SELECT COUNT(*) FROM article";
		int count = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(boardCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
		return count;
	} // end getBoardCount();
	
	public void deleteBoard(int at_no) {
		String deleteBoard = "DELETE FROM article WHERE at_no=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(deleteBoard); 
			pstmt.setInt(1, at_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
	} // end deleteBoard(int at_no);
	
	public void updateBoard(AtBoard b) {
		String updateBoard = "UPDATE article set m_id=?, title=?, content=?, w_date=SYSDATE, WHERE at_no=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(updateBoard); 
			pstmt.setString(1, b.getM_id());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());
			pstmt.setInt(4, b.getAt_no());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("AtBoardDao-updateBoard():SQLException");
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
	} // updateBoard(AtBoard b);
	
	public boolean isPassCheck(int at_no, String pass) {
		boolean isPass = false;
		String bPass = "SELECT pass FROM article WHERE at_no=?";
		try {
			conn=ds.getConnection();
			pstmt = conn.prepareStatement(bPass);
			pstmt.setInt(1, at_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isPass = rs.getString(1).equals(pass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {}
		}
		return isPass;
	}
	
	public void insertBoard(AtBoard b) {
		String insertBoard = "INSERT INTO article (at_no, m_id, title, content, pass, w_date, views) VALUES (article_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE, 0)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(insertBoard); 
			pstmt.setString(1, b.getM_id());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());
			pstmt.setString(4, b.getPass());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("AtBoardDao-insertBoard():SQLException");
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
	} // end insertBoard(AtBoard board);
	
	public AtBoard atGetBoard(int at_no) {
		
		String atBoard = "SELECT * FROM article WHERE at_no=?";
		AtBoard b = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(atBoard);
			pstmt.setInt(1, at_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				b = new AtBoard();
				b.setAt_no(rs.getInt("at_no"));
				b.setM_id(rs.getString("m_id"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setPass(rs.getString("pass"));
				b.setW_date(rs.getTimestamp("w_date"));
				b.setViews(rs.getInt("views"));
			}
		} catch (SQLException e) {
			System.out.println("AtBoardDao - atGetBoard() - SQLException");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
		return b;
	} // end AtBoardDao();
	
	public AtBoardDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
		} catch (NamingException e) {
			System.out.println("AtBoardDao() : NamingException");
			e.printStackTrace();
		}
	}
	
	public ArrayList<AtBoard> boardList(int startRow, int endRow) {
		String atBoardList = "SELECT * FROM (SELECT ROWNUM num, sa.* FROM (SELECT * FROM article ORDER BY at_no DESC)sa) WHERE num >= ? AND num <= ?";
		
		ArrayList<AtBoard> boardList = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(atBoardList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<AtBoard>();
				do {
				AtBoard b = new AtBoard();
				b.setAt_no(rs.getInt("at_no"));
				b.setM_id(rs.getString("m_id"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setPass(rs.getString("pass"));
				b.setW_date(rs.getTimestamp("w_date"));
				b.setViews(rs.getInt("views"));
				
				boardList.add(b);
			} while(rs.next()); }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException se) {}
		}
		return boardList;
	} // end boardList();
	
}
