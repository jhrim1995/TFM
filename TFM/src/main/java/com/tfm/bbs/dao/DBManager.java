package com.tfm.bbs.dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class DBManager {
		private static DataSource DS = null;
		private static Connection CONN = null;
		
		private DBManager() { }
		
		static {	 
			try {					
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				DS  = (DataSource) envContext.lookup("jdbc/bbsDBPool");
			} catch(NamingException e) {			
				System.out.println("BoardDao() : NamingException");
				e.printStackTrace();			
			} 
		}
		
		// DB 작업에 사용하는 커넥션 객체
		public static Connection getConnection() {
			try {
				CONN = DS.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return CONN;
		}
		
		
		// DB 작업에 사용된 객체 해제하는 메소드1
		public static void close(Connection conn, PreparedStatement pstmt) {
				
			if(conn != null)
				try {
					if(pstmt != null) pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
		}
		
		// DB 작업에 사용된 객체 해제하는 메소드2
		public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
			
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();	
					if(conn != null) conn.close();	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		
		// Transaction 시작
		public static void setAutoCommit(Connection conn, boolean isAutoCommit) {
				try {
					if(conn != null) conn.setAutoCommit(isAutoCommit);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		// Transaction 종료
		public static void commit(Connection conn) {
			try {
				if(conn != null) conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// Transaction 롤백
		public static void rollback(Connection conn) {
			try {
				if(conn != null) conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
