
package com.wisent.wr.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	private static Connection con= null; 
	private static String url = "jdbc:oracle:thin:@10.143.15.2:1521:zjmon02";
	//protected static ThreadLocal<Connection> local;
	
	public static Connection createConnection(){  
		  try {
			  if(con==null||con.isClosed()){
				  Class.forName("oracle.jdbc.driver.OracleDriver");
		          con = DriverManager.getConnection(url, "wisentsoft", "zjitc123");		         
			  }
			  return con;
		  } catch (ClassNotFoundException e) {
		  // Logger.getLogger(this.getClass()).error(e.getMessage());
		   return null;
		  } catch (SQLException e) {
		  // Logger.getLogger(this.getClass()).error(e.getMessage());
		   return null;
		  }
		 } 

	public static void releaseConnection(){  
		  if (con!=null)
		   try {
		    con.close();
		   } catch (SQLException e) {
			   
		    //Logger.getLogger(this.getClass()).error(e.getMessage());
		   }
		 }


}
