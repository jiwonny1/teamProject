package com.project.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonJDBCUtil {
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@192.168.18.6:1521:xe";
	private static final String USER = "project1";
	private static final String PASSWORD = "project1";	
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("[오류발생]  : " +e. getMessage());
		}
		return null;
	}
	
	public static void close(Connection conn, Statement stmt, 
			ResultSet rs) {
		try {
			if (rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(conn, stmt);
	}
	
	public static void close(Connection conn, Statement stmt) {
		try {
			if (stmt != null) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
