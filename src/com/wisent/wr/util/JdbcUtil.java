package com.wisent.wr.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil extends ConnectionFactory {
	public static void close(Connection con){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt){
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs, Statement stmt){
		close(rs);
		close(stmt);
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection con){
		close(rs);
		close(stmt);
		close(con);
	}
	
	public static void close(Statement stmt, Connection con){
		close(stmt);
		close(con);
	}
	

}
