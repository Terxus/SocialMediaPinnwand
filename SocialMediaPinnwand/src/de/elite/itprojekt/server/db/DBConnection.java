package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

public class DBConnection {
	private static Connection con = null;
	private static String dbHost = "localhost"; // Hostname
	private static String dbPort = "3306";      // Port -- Standard: 3306
	private static String dbName = "db10455335-itprojekt";   // Datenbankname
	private static String dbUser = "root";     // Datenbankuser
	private static String dbPass = "";      // Datenbankpasswort
	
	//Getter und Setter
	public static String getDbHost() {
		return dbHost;
	}
	public static void setDbHost(String dbHost) {
		DBConnection.dbHost = dbHost;
	}
	public static String getDbPort() {
		return dbPort;
	}
	public static void setDbPort(String dbPort) {
		DBConnection.dbPort = dbPort;
	}
	public static String getDbUser() {
		return dbUser;
	}
	public static void setDbUser(String dbUser) {
		DBConnection.dbUser = dbUser;
	}
	public static String getDbPass() {
		return dbPass;
	}
	public static void setDbPass(String dbPass) {
		DBConnection.dbPass = dbPass;
	}
	public static String getDbName() {
		return dbName;
	}
	public static void setDbName(String dbName) {
		DBConnection.dbName = dbName;
	}
	//Ende Getter und Setter
	
	//Start der Connection
	
	public static Connection connection() {
		if (con == null) { //Checken ob bereits eine Verbindung besteht
	    try {
	    	DriverManager.registerDriver(new AppEngineDriver()); // Create driver from GWT-Package
	    	
	    	
	        //Class.forName("com.mysql.jdbc.Driver"); // Datenbanktreiber f�r JDBC Schnittstellen laden.
	 
	        // Verbindung zur JDBC-Datenbank herstellen.
	        con = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+ dbPort+"/"+dbName+"?"+"user="+dbUser+"&"+"password="+dbPass);

	    } catch (SQLException error) { //Wenn ein Fehler auftritt -> Verbidung trennen
	    	con = null;
	        System.out.println("Verbindung nicht moglich");
	        System.out.println("SQLException: " + error.getMessage());
	        System.out.println("SQLState: " + error.getSQLState());
	        System.out.println("VendorError: " + error.getErrorCode());
	    }
	  }
		return con;
	}
	
	//Wird wegen Google ben�tigt
	
	public static void closeAll(ResultSet rs, Statement stmt, Connection con) throws Exception {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			} 
			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Connection close Fehler!" + e.toString());
		}
	}
}