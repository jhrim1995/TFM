package com.tfm.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tfm.bbs.dao.DBManager;
import com.tfm.bbs.vo.Member;

public class MemberDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	// 회원 가입할때 아이디 중복 확인
	public boolean overlapIDCheck(String id) {
				
		String overlapSql = "SELECT m_id FROM members WHERE m_id = ?";

		boolean result = false;
				
		try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(overlapSql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
					
				if(rs.next()) {
					result = true;
				}
				
			} catch(Exception e) {				
				e.printStackTrace();			
			} finally {		
				DBManager.close(conn, pstmt, rs);
			}		
			
		return result;
	}
	
	
	// 가입 하기 (Member 테이블에 정보 넣기)
	public void insertMember(Member member) {
		String sqlInsert = "INSERT INTO members(m_id, pass, email, m_name, nickname, birthday, gender, foreignyn, telecom, phone,  m_date)"
				         + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, member.getM_id());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getM_name());
			pstmt.setString(5, member.getNickname());
			pstmt.setString(6, member.getBirthday());
			pstmt.setString(7, member.getGender());
			pstmt.setString(8, member.getForeignyn());
			pstmt.setString(9, member.getTelecom());
			pstmt.setString(10, member.getPhone());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();	
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 로그인 시 아이디/ 비번만 가져와 비교할 때 사용
	public Member getIdPass(String id, String pass) {
			
			String sqlQuery = "select m_id, pass from members where m_id = ? ";
			Member member = new Member();
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sqlQuery);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getString("pass"));
					member.setM_id(rs.getString("m_id"));
					member.setPass(rs.getString("pass"));
				}
			}catch(Exception e){
				e.printStackTrace();	
			}finally {
				DBManager.close(conn, pstmt);
			}
			
			return member;
		}

	
	
}
