package com.jspstudy.ch06.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jspstudy.ch06.vo.Board;

public class BoardDao01 {

	private static final String USER = "hr";
	private static final String PASS = "hr";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BoardDao01() {
		//1. 접속들라이버 로딩
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
	}
	
	// 하나 하나의 기능은 메소드
	// 전체 게시 글 리스트를 DB 테이블에서 읽어와서 반환하는 메서드
	public ArrayList<Board> boardList() {
		
		String sqlBoardList = "SELECT * FROM jspbbs ORDER BY no DESC";
		ArrayList<Board> bList = null;
		
		try {			
			// 2. DB에 연결
			conn = DriverManager.getConnection(URL, USER, PASS);
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(sqlBoardList);
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			rs = pstmt.executeQuery();
			
			// 여러 개의 Board 객체를 담을 ArrayList를 사용
			bList = new ArrayList<>();
			
			// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
			while(rs.next()) {
				Board b = new Board();
				b.setNo(rs.getInt("no"));
				b.setTitle(rs.getString(2));
				b.setWriter(rs.getString("writer"));
				b.setRegDate(rs.getTimestamp("reg_date"));
				b.setReadCount(rs.getInt("read_count"));
				
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
		
	
}
