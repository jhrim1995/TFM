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
	
	// 로그인 시
	public int loginCheck(String id, String pass) {
					
			String checkIdSql = "SELECT m_id FROM members WHERE m_id = ?";

			int checkNumber = -1;  // 아이디가 없을 때
					
			try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(checkIdSql);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
						
					if(rs.next()) {
						checkNumber = 0;  // 아이디는 존재 하고 비밀번호가 틀릴 때
					}
					
					String checkpassSql = "SELECT m_id FROM members WHERE m_id = ? and pass = ?";
					
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(checkpassSql);
					pstmt.setString(1, id);
					pstmt.setString(2, pass);
					rs = pstmt.executeQuery();
						
					if(rs.next()) {
						checkNumber = 1; // 비밀번호까지 맞을 때
					}
					
					
				} catch(Exception e) {				
					e.printStackTrace();			
				} finally {		
					DBManager.close(conn, pstmt, rs);
				}		
				
			return checkNumber;
		}
	
	
		// 내 정보보기에서 내 정보 가져오기
		public Member myProfileAll(String id) {
			
				Member m = new Member();
						
				String myProfileSql = "SELECT m_id, pass, email, m_name, nickname, birthday, gender, foreignyn, telecom, phone FROM members WHERE m_id = ?";
				
				try {
						conn = DBManager.getConnection();
						pstmt = conn.prepareStatement(myProfileSql);
						pstmt.setString(1, id);
						rs = pstmt.executeQuery();
							
						if(rs.next()) {
							m.setM_id(rs.getString("m_id"));
							m.setPass(rs.getString("pass"));
							m.setEmail(rs.getString("email"));
							m.setM_name(rs.getString("m_name"));
							m.setNickname(rs.getString("nickname"));
							m.setBirthday(rs.getString("birthday"));
							m.setGender(rs.getString("gender"));
							m.setForeignyn(rs.getString("foreignyn"));
							m.setTelecom(rs.getString("telecom"));
							m.setPhone(rs.getString("phone"));
						}
						
					} catch(Exception e) {				
						e.printStackTrace();			
					} finally {		
						DBManager.close(conn, pstmt, rs);
					}		
					
				return m;
			}
	
		
		// 내 정보 수정하기
		public void updateProfile(Member member) {
								
				String updateProfileSql = "update members set pass = ?, email = ?, m_name = ?, nickname = ?, telecom = ? , phone = ?, M_date = sysdate   WHERE m_id = ?";
						
				try {
							conn = DBManager.getConnection();
							pstmt = conn.prepareStatement(updateProfileSql);
							pstmt.setString(1, member.getPass());
							pstmt.setString(2, member.getEmail());
							pstmt.setString(3, member.getM_name());
							pstmt.setString(4, member.getNickname());
							pstmt.setString(5, member.getTelecom());								
							pstmt.setString(6, member.getPhone());								
							pstmt.setString(7, member.getM_id());	
							
							pstmt.executeUpdate();
							
								
				} catch(Exception e) {				
						e.printStackTrace();			
				} finally {		
						DBManager.close(conn, pstmt);
				}		
							
		}
		
		// 비밀번호 찾아오기
		public String getPassword(String id) {
										
			String passSql = "select pass from members where m_id = ?";
			String pass = "";
								
			try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(passSql);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
									
					if(rs.next()) {
						pass = rs.getString("pass");
					}
															
				} catch(Exception e) {				
						e.printStackTrace();			
				} finally {		
						DBManager.close(conn, pstmt, rs);
				}	
			
			return pass;
									
		}
		
		// 회원 탈퇴
		public void cancleMembership(String id) {
												
					String passSql = "delete from members where m_id = ?";
										
					try {
							conn = DBManager.getConnection();
							pstmt = conn.prepareStatement(passSql);
							pstmt.setString(1, id);
							pstmt.executeUpdate();
											
																	
						} catch(Exception e) {				
								e.printStackTrace();			
						} finally {		
								DBManager.close(conn, pstmt);
						}	
											
		}
		
		
		// 아이디 찾기
		public ArrayList<String> searchIdList(String name, String telecom, String phone) {
														
			String findIdsSql = "select m_id from members where m_name = ? and telecom = ? and phone = ?";
			ArrayList<String> idList = new ArrayList<String>();
										
			try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(findIdsSql);
					pstmt.setString(1, name);
					pstmt.setString(2, telecom);
					pstmt.setString(3, phone);
					rs = pstmt.executeQuery();
									
					while(rs.next()) {
						String id = "";
						id = rs.getString("m_id");
						
						idList.add(id);
					}
																				
				} catch(Exception e) {				
					e.printStackTrace();			
				} finally {		
					DBManager.close(conn, pstmt);
				}	
			
			return idList;
													
		}
		
		// 비밀번호 찾아오기
		public String getPassword(String id, String name, String telecom, String phone) {
												
			String passSql = "select pass from members where m_id = ? and m_name = ? and telecom = ? and phone = ?";
			String pass = "";
										
			try {
					conn = DBManager.getConnection();
					pstmt = conn.prepareStatement(passSql);
					pstmt.setString(1, id);
					pstmt.setString(2, name);
					pstmt.setString(3, telecom);
					pstmt.setString(4, phone);
					rs = pstmt.executeQuery();
											
					if(rs.next()) {
						pass = rs.getString("pass");
					}
																	
				} catch(Exception e) {				
					e.printStackTrace();			
				} finally {		
					DBManager.close(conn, pstmt, rs);
				}	
					
			return pass;
											
		}
		
	
}
