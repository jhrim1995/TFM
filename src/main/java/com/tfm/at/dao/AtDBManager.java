package com.tfm.at.dao;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

public class AtDBManager {
	private static DataSource DS = null;
	private static Connection CONN = null;
	private AtDBManager() {}
	static {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			DS = (DataSource) envContext.lookup("jdbc/bbsDBPool");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			DS.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CONN;
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setAutoCommit(Connection conn, boolean isAutoCommit) {
		try {
			if(conn != null ) conn.setAutoCommit(isAutoCommit);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null) conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null) conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
