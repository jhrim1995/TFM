package com.jsplist.js.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.jsplist.js.vo.FreeList;
import com.jsplist.js.vo.Reply;



public class FreeListDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public void deleteReply(Reply reply) {
		
		String deleteReply = "delete from fr_reply  where c_no=?";
				
		try {			
			// 2. DB에 연결
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(deleteReply);			
			pstmt.setInt(1, reply.getCno());				
			
			// 4. 쿼리를 발행하여 댓글을 테이블에 저장
			pstmt.executeUpdate();
			System.out.println("reply삭제");
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {			
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			DBManager.close(conn, pstmt);
		}
	}
	
	public void updateReply(Reply reply) {
		
		String updateReply = "update fr_reply set c_con=?, reg_date=SYSDATE where c_no=?";
				
		try {			
			// 2. DB에 연결
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(updateReply);	
			pstmt.setString(1, reply.getCcon());			
			pstmt.setInt(2, reply.getCno());				
			
			// 4. 쿼리를 발행하여 댓글을 테이블에 저장
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {			
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			DBManager.close(conn, pstmt);
		}
	}
	
	
	public void addReply(Reply reply) {
		
		String insertReply = "INSERT INTO reply "
				+ " VALUES(reply_seq.NEXTVAL, ?, ?, ?, SYSDATE)";
				
		try {			
			// 2. DB에 연결
			conn = DBManager.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(insertReply);
			pstmt.setInt(1, reply.getFno());			
			pstmt.setString(2, reply.getMid());			
			pstmt.setString(3, reply.getCcon());				
			
			// 4. 쿼리를 발행하여 댓글을 테이블에 저장
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {			
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			DBManager.close(conn, pstmt);
		}
	}
	
	
	public ArrayList<FreeList> searchList(
			String type, String keyword, int startRow, int endRow) {
		
		String searchList = "SELECT * FROM ( "
				+ "    SELECT ROWNUM num, sub.* FROM "
				+ "        (SELECT * FROM freelist WHERE " + type 
				+ "			LIKE ? ORDER BY fno DESC) sub) "
				+ " WHERE num >= ? AND num <= ?";
		ArrayList<FreeList> fList = null;
		
		try {			
			// 2. DB에 연결
			conn = DBManager.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(searchList);
			pstmt.setString(1,  "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			rs = pstmt.executeQuery();
			
			// 여러 개의 Board 객체를 담을 ArrayList를 사용
			fList = new ArrayList<>();
			
			// 5. 쿼리를 실행 결과를 while 반복하면서 Board 객체에 담고 List 담는다.
			while(rs.next()) {
				FreeList f = new FreeList();
				f.setNo(rs.getInt("fno"));
				f.setTitle(rs.getString("ftitle"));
				f.setId(rs.getString("fid"));
				f.setDate(rs.getTimestamp("fdate"));
				f.setView(rs.getInt("fview"));
				f.setContent(rs.getString("fcontent"));
				
				fList.add(f);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return fList;
	}
	
	
	public int getfreelistCount(String type, String keyword) {
		String sqlBoard = "SELECT COUNT(*) FROM freelist "
				+ " where " + type + " LIKE '%'|| ? || '%'";
		int count = 0;
		
		try {
			conn = DBManager.getConnection();		
			pstmt = conn.prepareStatement(sqlBoard);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt(1);
			}			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;		
	}
	
	
	public ArrayList<Reply> getreplyList(int no){
		String replyListsql = "select * from fr_reply where fno=?";
		ArrayList<Reply> replyList = null;
		
		try {
			conn = DBManager.getConnection();
			System.out.println("db연결");
			DBManager.setAutoCommit(conn, true);
			
			
			pstmt = conn.prepareStatement(replyListsql);
			pstmt.setInt(1,no);
			rs = pstmt.executeQuery();
			
			replyList = new ArrayList<>();
			
			while(rs.next()) {
				Reply r = new Reply();
				r.setCno(rs.getInt("c_no"));
				r.setFno(rs.getInt("fno"));
				r.setMid(rs.getString("M_id"));
				r.setCcon(rs.getString("c_con"));
				r.setDate(rs.getTimestamp("fdate")); 
				replyList.add(r);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn,pstmt,rs);
		}
		return replyList;
	}
	
	
	public HashMap<String, Integer> getRecommend(int no, String strThank) {
		
	
		String addRecommendSql = "UPDATE freelist set fgood = fgood + 1 "
					+ "	WHERE fno = ?";
			
		String selectResultSql = "SELECT fgood FROM freelist "
					+ " WHERE fno = ?";
				
		HashMap<String, Integer> map = null;
		
		try {			
			// 2. DB에 연결
			conn = DBManager.getConnection();
			
			// 트랜잭션 시작
			DBManager.setAutoCommit(conn, false);
			
			if(strThank.equals("commend")) { // 추천 요청이면
				pstmt = conn.prepareStatement(addRecommendSql);
				
			}
					
			pstmt.setInt(1, no);			
			
			// 쿼리를 발행해서 추천/땡큐를 갱신한다.
			pstmt.executeUpdate();
			
			// 새로 갱신된 추천/땡큐를 읽어오기
			pstmt = conn.prepareStatement(selectResultSql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
						 
			if(rs.next()) {
				map = new HashMap<>();
				map.put("recommend", rs.getInt(1));
			}
			
			// 작업이 완료되면 트랜잭션 종료
			DBManager.commit(conn);
			
		} catch(SQLException e) {
			DBManager.rollback(conn);
			e.printStackTrace();
			
		} finally {
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			DBManager.close(conn, pstmt, rs);
		}
		
		return map;
	}
	
	
	//게시글 리스트 
	public ArrayList<FreeList> freeList(int startRow , int endRow){
		String freeListsql ="SELECT * FROM (SELECT ROWNUM num, sub.* "
				+ " FROM (SELECT * FROM freelist ORDER BY fno desc) sub) "
				+ " WHERE num >= ? AND num <= ?";
		ArrayList<FreeList> fList = null;
		
		try {
			conn = DBManager.getConnection();
			System.out.println("db연결");
			pstmt = conn.prepareStatement(freeListsql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			fList = new ArrayList<>();
			
			while(rs.next()) {
				FreeList fl = new FreeList();
				fl.setNo(rs.getInt("fno"));
				fl.setId(rs.getString("fid"));
				fl.setTitle(rs.getString("ftitle"));
				fl.setContent(rs.getString("fcontent"));
				fl.setDate(rs.getTimestamp("fdate")); 
				fl.setView(rs.getInt("fview"));
				fl.setGood(rs.getInt("fgood"));
				fl.setFile(rs.getString("ffile"));
				
				fList.add(fl);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn,pstmt,rs);
		}
		return fList;
	}
	
	public FreeList getfreeList(int no ){
		String freeListsql = "select * from freelist where fno=?";
		FreeList f = null;
		
		try {
			conn = DBManager.getConnection();
			System.out.println("db연결");
			DBManager.setAutoCommit(conn, true);
			
			
			pstmt = conn.prepareStatement(freeListsql);
			pstmt.setInt(1,no);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				f = new FreeList();
				f.setNo(rs.getInt("fno"));
				f.setId(rs.getString("fid"));
				f.setTitle(rs.getString("ftitle"));
				f.setContent(rs.getString("fcontent"));
				f.setDate(rs.getTimestamp("fdate")); 
				f.setView(rs.getInt("fview"));
				f.setGood(rs.getInt("fgood"));
				f.setFile(rs.getString("ffile"));
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn,pstmt,rs);
		}
		return f;
	}
	
	
	//게시글 정보보기 
	public FreeList getfreeList(int no , boolean state){
		String freeListsql = "select * from freelist where fno=?";
		String countsql = "update freelist set fview = fview + 1 where fno=?";
		FreeList f = null;
		
		try {
			conn = DBManager.getConnection();
			System.out.println("db연결");
			DBManager.setAutoCommit(conn, true);
			
			if(state) {
				pstmt = conn.prepareStatement(countsql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			}
			
			pstmt = conn.prepareStatement(freeListsql);  
			pstmt.setInt(1,no);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				f = new FreeList();
				f.setNo(rs.getInt("fno"));
				f.setId(rs.getString("fid"));
				f.setTitle(rs.getString("ftitle"));
				f.setContent(rs.getString("fcontent"));
				f.setDate(rs.getTimestamp("fdate")); 
				f.setView(rs.getInt("fview"));
				f.setGood(rs.getInt("fgood"));
				f.setFile(rs.getString("ffile"));
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(conn,pstmt,rs);
		}
		return f;
	}
	
	
	public int getfreelistCount() {
		String sqlBoard = "SELECT COUNT(*) FROM freelist";
		int count = 0;
		
		try {
			conn = DBManager.getConnection();		
			pstmt = conn.prepareStatement(sqlBoard);			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt(1);
			}			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;		
	}
	
	public void insertfreelist(FreeList fl) {
		
		String insertfreelist = "INSERT INTO freelist(fno, fid ,ftitle,fcontent,ffile,fdate) "
				+ " VALUES(freelist_seq.NEXTVAL, ?, ?, ?,?,SYSDATE)"; 
				
		try {			
			// 2. DB에 연결
			conn = DBManager.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(insertfreelist);
			pstmt.setString(1, fl.getId());			
			pstmt.setString(2, fl.getTitle());						
			pstmt.setString(3, fl.getContent());					
			pstmt.setString(4, fl.getFile());			
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {			
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			DBManager.close(conn, pstmt);
		}
	}
	
	public void updatefreelist(FreeList fl) {
		
		// 파일이 업로드 되었을 때 - 업로드된 파일로 치환
		String fileUpload = fl.getFile() != null ? ", ffile=?" : "";
		// 파일이 업로드 되지 않았을 때 기존 값을 유지 - 바꿀 필요가 없음
		String updateFreeList = "UPDATE freelist SET fid=?, ftitle=?, fcontent=?, "
				+ " fdate=SYSDATE" + fileUpload +  " WHERE fno=?";
				
		try {			
			// 2. DB에 연결
			conn = DBManager.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(updateFreeList);
			pstmt.setString(1, fl.getId());			
			pstmt.setString(2, fl.getTitle());			
			pstmt.setString(3, fl.getContent());			
		
			
			if(fl.getFile() != null) {
				pstmt.setString(4, fl.getFile());	
				pstmt.setInt(5, fl.getNo());
			} else {
				pstmt.setInt(4, fl.getNo());
			}
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			DBManager.close(conn, pstmt);
		}
	}
	
	public void deletefreelist(int no) {
		
		String deletefreelist = "DELETE FROM freelist WHERE fno=?";
				
		try {			
			// 2. DB에 연결
			conn = DBManager.getConnection();
			
			// 3. DB에 SQL 쿼리를 발행하는 객체를 활성화된 커넥션으로부터 구한다.
			// PreparedStatement
			pstmt = conn.prepareStatement(deletefreelist);			
			pstmt.setInt(1, no);
			
			// 4. 쿼리를 발행하여 SELECT한 결과를 ResultSet 객체로 받는다.
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			// 6. DB 작업에 사용한 객체는 처음 가져온 역순으로 닫다.
			DBManager.close(conn, pstmt);
		}
	}
	
}
