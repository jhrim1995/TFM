package com.jspreview.mh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tfm.bbs.dao.DBManager;
import com.tfm.vo.RvBoard;
import com.tfm.vo.RvReply;

public class RvBoardDao {
	
	private Connection conn;
	
	private PreparedStatement pstmt;
	
	private ResultSet rs;
	
	private static DataSource ds;
	
	public RvBoardDao() {
		try {
			Context initContext = new InitialContext();
			
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			
			ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
		} catch(NamingException e) {
			System.out.println("RvBoardDao() : NamingException");
			e.printStackTrace();
		}
	}
	
	
	// DB를 읽어와 ArrayList로 반환하는 메서드
	// 한 페이지에 보여 질 게시글 리스트 요청시 호출되는 메서드
	public ArrayList<RvBoard> boardList(int startRow, int endRow){
		
		String sqlBoardList = "SELECT * FROM (SELECT ROWNUM rnum, "
				+ " r_no, m_id, title, content, p_date, views, file1, area, recommend FROM "
				+ "(SELECT * FROM reviewbbs ORDER BY r_no DESC)) "
				+ " WHERE rnum >= ? AND rnum <= ? ";
		ArrayList<RvBoard> boardList = null;
		
	 try {
		conn = DBManager.getConnection();
		
		pstmt = conn.prepareStatement(sqlBoardList);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, endRow);
		
		rs = pstmt.executeQuery();
		
			
		if(rs.next()) {
			boardList = new ArrayList<RvBoard>();
			
			do {
			RvBoard board = new RvBoard();
			
			board.setR_no(rs.getInt("r_no"));
			board.setM_id(rs.getString("m_id"));
			board.setTitle(rs.getString("title"));
			board.setContent(rs.getString("content"));
			board.setP_date(rs.getTimestamp("p_date"));
			board.setViews(rs.getInt("views"));
			board.setFile1(rs.getString("file1"));
			board.setArea(rs.getString("area"));
			board.setRecommend(rs.getInt("recommend"));
			
			boardList.add(board);
			
			} while(rs.next());
		}
		
	} catch(Exception e) {
		e.printStackTrace();
	
	} finally {
		DBManager.close(conn, pstmt, rs);
	}
	return boardList;
	
	}	
	
	
	//게시글 no.에 해당하는 글 DB에서 읽어와 Board 객체로 반환하는 메서드
	public RvBoard getBoard(int r_no, boolean state) {
		String boardSql = "SELECT * FROM reviewbbs WHERE r_no=?";
		String countSql = "UPDATE reviewbbs set views = views + 1 "
				+ " WHERE r_no = ?";
		RvBoard board = null;
		
		try {
			conn = DBManager.getConnection();
			DBManager.setAutoCommit(conn, false);
			
			if(state) {
				pstmt = conn.prepareStatement(countSql);
				pstmt.setInt(1, r_no);
				pstmt.executeUpdate();
			}
			pstmt = conn.prepareStatement(boardSql);
			pstmt.setInt(1, r_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new RvBoard();
				
				board.setR_no(rs.getInt("r_no"));
				board.setM_id(rs.getString("m_id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setP_date(rs.getTimestamp("p_date"));
				board.setViews(rs.getInt("views"));
				board.setFile1(rs.getString("file1"));
				board.setArea(rs.getString("area"));
				board.setRecommend(rs.getInt("recommend"));
								
				DBManager.commit(conn);
			}
		} catch(Exception e) {
			DBManager.rollback(conn);
			System.out.println("RvBoardDao - getBoard(r_no, state)");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return board;
	}
	
	
	
	//게시글 DB에 등록하는 메서드
	public void insertBoard(RvBoard board) {
		
		String sqlInsert = "INSERT INTO reviewbbs(r_no,m_id,title,content,p_date,area) "
				+ "VALUES(reviewbbs_seq.NEXTVAL,?,?,?,SYSDATE,?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			
			pstmt.setString(1, board.getM_id());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			
			pstmt.setString(4, board.getArea());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	//게시글 수정, 게시글 삭제시 아이디 입력을 체크하는 메서드
	
	public boolean isM_idCheck(int r_no, String m_id) {
		boolean isM_id = false;
		String sqlM_id = "SELECT m_id FROM reviewbbs WHERE r_no=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlM_id);
			pstmt.setInt(1, r_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isM_id = rs.getString(1).equals(m_id);
			}
		} catch(SQLException e) {
			System.out.println("RvBoardDao - isM_idCheck() : SQLException");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return isM_id;
	}
	
	//게시글 수정 요청시 호출되는 메서드
	//수정 폼에서 수정하기 버튼이 클릭되면 게시글을 DB에 수정하는 메서드
	public void rvUpdateBoard(RvBoard board) {
		
		String fileUpdate = board.getFile1() !=null ? ", file1=?" : "";
		String sqlUpdate = "UPDATE reviewbbs set m_id=?, title=?, content=?, "
				+ " p_date=SYSDATE , area=? " + fileUpdate + " WHERE r_no=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlUpdate);
			
			pstmt.setString(1, board.getM_id());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getArea());
			
			
		if(board.getFile1() != null) {
			pstmt.setString(5, board.getFile1());
			pstmt.setInt(6, board.getR_no());
		} else {
			pstmt.setInt(6, board.getR_no());
		}
			pstmt.executeUpdate();
			
		} catch(Exception e) {						
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	
	// 게시글 삭제 요청 시 호출되는 메서드
	// r_no에 해당 하는 게시글을 DB에서 삭제하는 메서드
	public void deleteBoard(int r_no) {
		
		String sqlDelete = "DELETE FROM reviewbbs WHERE r_no=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, r_no);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	
	//전체 게시글 수를 계산하기 위해 호출되는 메서드-페이징처리
	public int getBoardCount() {
		
		String sqlCount = "SELECT COUNT(*) FROM reviewbbs";
		int count = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	
	// 검색어에 해당하는 게시글 수를 계산하기 위해 호출되는 메서드
	// DB 테이블에서 해당 검색어가 포함된 게시글의 수를 반환하는 메서드
	public int getBoardCount(String type, String keyword) {
		System.out.println(type + " - " + keyword);
		
		String sqlCount = "SELECT COUNT(*) FROM reviewbbs WHERE "
				+ type + " LIKE '%' || ? || '%'";
		
		int count = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt,rs);
		}
		return count;
	}
	
	
	
	// 제목, ID, 내용, 지역에서 검색어가 포함된 게시글 검색 시 호출되는 메서드
	// 요청한 페이지에 해당하는 검색 결과를 DB에서 읽어와 반환하는 메서드
	public ArrayList<RvBoard> searchList(
			String type, String keyword, int startRow, int endRow){
		
		String sqlSerchList = "SELECT * FROM (SELECT ROWNUM rnum, r_no, title,"
				+ " m_id, content, p_date, views, area, file1, recommend FROM "
				+ " (SELECT * FROM reviewbbs WHERE " + type + " LIKE ? "
				+ " ORDER BY r_no DESC)) WHERE rnum >= ? AND rnum <= ? ";
		
		ArrayList<RvBoard> boardList = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlSerchList);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				boardList = new ArrayList<RvBoard>();
				
				do {
					RvBoard board = new RvBoard();
					board.setR_no(rs.getInt("r_no"));
					board.setTitle(rs.getString("title"));
					board.setM_id(rs.getString("m_id"));
					board.setContent(rs.getString("content"));
					board.setP_date(rs.getTimestamp("p_date"));
					board.setViews(rs.getInt("views"));
					board.setArea(rs.getString("area"));
					board.setFile1(rs.getString("file1"));
					board.setRecommend(rs.getInt("recommend"));
					
					boardList.add(board);
				} while(rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
	
	// 특정 게시글에 해당하는 댓글 리스트를 반환하는 메서드
	public ArrayList<RvReply> getReplyList(int r_No){
		
		String replyListSql = "SELECT * FROM rv_reply WHERE r_no = ?"
				+ " ORDER BY c_no DESC";
		
		RvReply reply = null;
		ArrayList<RvReply> replyList = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(replyListSql);
			pstmt.setInt(1, r_No);
			rs = pstmt.executeQuery();
			
			replyList = new ArrayList<RvReply>();
			
			while(rs.next()) {
				
				reply = new RvReply();
				reply.setC_no(rs.getInt("c_no"));
				reply.setR_no(rs.getInt("r_no"));
				reply.setC_con(rs.getString("c_con"));
				reply.setM_id(rs.getString("m_id"));
				reply.setC_date(rs.getTimestamp("c_date"));
				replyList.add(reply);
			}
		} catch(Exception e) {
			System.out.println("RvBoardDao - replyList(no)");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
				return replyList;
	}
	
	
	
	// 게시글 상세 보기에서 추천 요청을 처리하는 메서드
	//추천의 횟수를 review 테이블에서 1 증가 시킨다.
	public HashMap<String, Integer> getRecommend(int r_no){
		
		HashMap<String, Integer> map = null;
		
		String addRecommendSql = "UPDATE reviewbbs set "
				+ " recommend = recommend + 1 WHERE r_no=?";
		
		String selectResultSql = "SELECT recommend FROM reviewbbs WHERE r_no=?";
		
		try {
			conn = DBManager.getConnection();
			DBManager.setAutoCommit(conn, false);
			
		//	if(strThank.equals("commend")) {
				pstmt = conn.prepareStatement(addRecommendSql);
				
		//	} 
			pstmt.setInt(1, r_no);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement(selectResultSql);
			pstmt.setInt(1, r_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				map = new HashMap<String, Integer>();
				map.put("recommend", rs.getInt(1));
				//map.put("recommend", rs.getInt("recommend"));
			}
			DBManager.commit(conn);
		} catch(Exception e) {
			DBManager.rollback(conn);
			System.out.println("RvBoardDao - getRecommend(r_no)");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return map;
	}
	
	
	
	// 게시글에 댓글 쓰기 요청시 호출되는 메서드 
	// 댓글 쓰기 요청이 들어오면 RvReplyWriteAction 클래스에서 호출되어 댓글을 DB에 추가하는 메서드
	public void addReply(RvReply reply) {
		
		String replyInsertSql = "INSERT INTO reply"
				+ " VALUES(rv_reply_seq.NEXTVAL, ? , ?, ?, SYSDATE)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(replyInsertSql);
			
			pstmt.setInt(1, reply.getR_no());
			pstmt.setString(2, reply.getM_id());
			pstmt.setString(3, reply.getC_con());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("RvBoardDao - addReply(rv_reply)");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	
	// 댓글 수정 메서드 RvReplyUpdateAction 클래스에서 호출
	public void updateReply(RvReply reply) {
		
		String replyUpdateSql = "UPDATE rv_reply SET c_con=?,"
				+ " c_date=SYSDATE WHERE c_no=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(replyUpdateSql);
			
			pstmt.setString(1, reply.getC_con());
			pstmt.setInt(2, reply.getC_no());
			
			pstmt.execute();
		} catch(Exception e) {
			System.out.println("RvBoardDao - updateReply(reply)");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	
	// 댓글 삭제 요청시 호출되는 메서드
	// RvReplyDeleteAction 클래서에서 호출
	public void deleteReply(RvReply reply) {
		
		String replyDeleteSql = "DELETE FROM rv_reply WHERE no = ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(replyDeleteSql);
			
			pstmt.setInt(1, reply.getC_no());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("RvBoardDao - deleteReply(reply)");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}
