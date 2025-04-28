package com.jspstudy.ch06.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

import com.jspstudy.ch06.vo.Board;

public class BoardDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public BoardDao() {
		//1. DBCP 자원을 찾는다.
		try {
			Context initContext = new InitialContext();			
			Context envContext = (Context) initContext.lookup("java:/comp/env");			
			ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
		} catch (NamingException e) {			
			e.printStackTrace();
		}
	}

	// 전체 게시글 수를 읽어오는 메서드 - paging 처리에 사용
	public int getBoardCount() {
		String sqlBoard = "SELECT COUNT(*) FROM jspbbs";
		int count = 0;
		
		try {
			conn = ds.getConnection();		
			pstmt = conn.prepareStatement(sqlBoard);			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt(1);
			}			
		} catch(SQLException e) {
			System.out.println("BoardDao - getBoardCount() : SQLException");
			e.printStackTrace();
			
		} finally {
			try {				
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();				
				if(conn != null) conn.close();
			} catch(SQLException e) {}
		}
		return count;		
	}	
	
	public ArrayList<Board> boardList(int startRow, int endRow) {
		
		String sqlBoardList = "SELECT * FROM (SELECT ROWNUM num, sub.* "
				+ "    FROM (SELECT * FROM jspbbs ORDER BY no DESC) sub) "
				+ " WHERE num >= ? AND num <= ?";
		ArrayList<Board> bList = null;
		
		try {			
			// 2. DB에 연결
			conn = ds.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(sqlBoardList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			rs = pstmt.executeQuery();
			
			// 여러 개의 Board 객체를 담을 ArrayList를 사용
			bList = new ArrayList<>();
			
			// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
			while(rs.next()) {
				Board b = new Board();
				b.setNo(rs.getInt("no"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setRegDate(rs.getTimestamp("reg_date"));
				b.setReadCount(rs.getInt("read_count"));
				b.setPass(rs.getString("pass"));
				b.setFile1(rs.getString("file1"));
				
				bList.add(b);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bList;
	}
	
	
	// 게시 글 상세보기
	// 게시 글 번호에 해당하는 하나의 게시 글을 DB 테이블에서 읽어와 반환하는 메서드
	public Board getBoard(int no) {
		
		String sqlBoardList = "SELECT * FROM jspbbs WHERE no=?";
		Board b = null;
		
		try {			
			// 2. DB에 연결
			conn = ds.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(sqlBoardList);
			pstmt.setInt(1, no);			
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			rs = pstmt.executeQuery();
			
			// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
			if(rs.next()) {
				b = new Board();
				b.setNo(rs.getInt("no"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setContent(rs.getString("content"));
				b.setRegDate(rs.getTimestamp("reg_date"));
				b.setReadCount(rs.getInt("read_count"));
				b.setPass(rs.getString("pass"));
				b.setFile1(rs.getString("file1"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return b;
	}	

	// 게시 글 하나를 받아서 DB에 저장하는 메서드
	public void insertBoard(Board b) {
		
		String insertBoard = "INSERT INTO jspbbs(no, title, writer, content, "
				+ " reg_date, read_count, pass, file1) "
				+ " VALUES(jspbbs_seq.NEXTVAL, ?, ?, ?, SYSDATE, 0, ?, ?)";
				
		try {			
			// 2. DB에 연결
			conn = ds.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(insertBoard);
			pstmt.setString(1, b.getTitle());			
			pstmt.setString(2, b.getWriter());			
			pstmt.setString(3, b.getContent());			
			pstmt.setString(4, b.getPass());			
			pstmt.setString(5, b.getFile1());			
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			try {				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 게시 글 수정, 삭제시 게시 글 비밀번호가 맞는지 체크하는 메서드
	public boolean isPassCheck(int no, String pass) {
		
		String sqlBoardList = "SELECT pass FROM jspbbs WHERE no=?";
		boolean isPass = false;
		
		try {			
			// 2. DB에 연결
			conn = ds.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(sqlBoardList);
			pstmt.setInt(1, no);			
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			rs = pstmt.executeQuery();
			
			// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
			if(rs.next()) {
				isPass = rs.getString(1).equals(pass);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isPass;
	} // end isPassCheck()	

	// 게시 글의 내용을 수정하는 정보를 받아서 DB에서 수정하는 메서드
	public void updateBoard(Board b) {
		
		// 파일이 업로드 되었을 때 - 업로드된 파일로 치환
		String fileUpload = b.getFile1() != null ? ", file1=?" : "";
		// 파일이 업로드 되지 않았을 때 기존 값을 유지 - 바꿀 필요가 없음
		String updateBoard = "UPDATE jspbbs SET title=?, writer=?, content=?, "
				+ " reg_date=SYSDATE" + fileUpload +  " WHERE no=?";
				
		try {			
			// 2. DB에 연결
			conn = ds.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(updateBoard);
			pstmt.setString(1, b.getTitle());			
			pstmt.setString(2, b.getWriter());			
			pstmt.setString(3, b.getContent());	
			
			if(b.getFile1() != null) {
				pstmt.setString(4, b.getFile1());	
				pstmt.setInt(5, b.getNo());
			} else {
				pstmt.setInt(4, b.getNo());
			}
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			try {				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// no에 해당하는 게시 글을 DB에서 삭제하는 메서드
	public void deleteBoard(int no) {
		
		String deleteBoard = "DELETE FROM jspbbs WHERE no=?";
				
		try {			
			// 2. DB에 연결
			conn = ds.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(deleteBoard);			
			pstmt.setInt(1, no);
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			try {				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}	
	
}
