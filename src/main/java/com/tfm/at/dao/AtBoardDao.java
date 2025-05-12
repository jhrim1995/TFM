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
				b.setP_date(rs.getTimestamp("p_date"));
				b.setViews(rs.getInt("views"));
				b.setFv(rs.getInt("fv"));
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
	
	public ArrayList<AtBoard> boardList() {
		String atBoardList = "SELECT * FROM article ORDER BY at_no DESC";
		ArrayList<AtBoard> boardList = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(atBoardList);
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<AtBoard>();
			while(rs.next()) {
				AtBoard b = new AtBoard();
				b.setAt_no(rs.getInt("at_no"));
				b.setM_id(rs.getString("m_id"));
				b.setTitle(rs.getString("title"));
				b.setContent(rs.getString("content"));
				b.setP_date(rs.getTimestamp("p_date"));
				b.setViews(rs.getInt("views"));
				b.setFv(rs.getInt("fv"));
				
				boardList.add(b);
			}
		} catch (SQLException e) {
			System.out.println("AtBoardDao - boardList() - SQLException");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {}
		}
		
		return boardList;
	} // end boardList();
	
	
}
