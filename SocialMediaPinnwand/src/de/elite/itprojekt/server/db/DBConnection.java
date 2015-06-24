package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.rdbms.AppEngineDriver;

/**
 * Verwalten einer Verbindung zur Datenbank.<p>
 * <b>Vorteil:</b> Sehr einfacher Verbindungsaufbau zur Datenbank.<p>
 * <b>Nachteil:</b> Durch die Singleton-Eigenschaft der Klasse kann nur auf eine
 * fest vorgegebene Datenbank zugegriffen werden.<p>
 * In der Praxis kommen die meisten Anwendungen mit einer einzigen Datenbank 
 * aus. Eine flexiblere Variante für mehrere gleichzeitige Datenbank-Verbindungen
 * wäre sicherlich leistungsfähiger. Dies würde allerdings den Rahmen dieses 
 * Projekts sprengen bzw. die Software unnötig verkomplizieren, da dies für diesen
 * Anwendungsfall nicht erforderlich ist.
 * 
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @author In Anlehung an Prof. Dr. Thies
 */

public class DBConnection {
	
	/**
	 * Die Klasse DBConnection wird nur einmal instantiiert. Man spricht hierbei
	 * von einem sogenannten <b>Singleton</b>.<p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see NutzerMapper.nutzerMapper()
	 */
	
	private static Connection con = null;
	
	/**
	 * Die URL, mit deren Hilfe die Datenbank angesprochen wird. In einer 
	 * professionellen Applikation würden diese Zeichenketten aus einer 
	 * Konfigurationsdatei eingelesen oder über einen Parameter von außen 
	 * mitgegeben, um bei einer Veränderung dieser URL nicht die gesamte 
	 * Software neu komilieren zu müssen.
	 */
	
	private static String dbHost = "localhost"; // Hostname
	private static String dbPort = "3306";      // Port -- Standard: 3306
	private static String dbName = "gruppe5";   // Datenbankname
	private static String dbUser = "root";     // Datenbankuser
	private static String dbPass = "";      // Datenbankpasswort

	/**
	 * Gibt die IP oder den DNS des Datenbankservers zurück.
	 * @return dbHost
	 */
	public static String getDbHost() {
		return dbHost;
	}
	/**
	 * Setzt die IP oder den DNS des Datenbankservers.
	 * @param dbHost
	 */
	public static void setDbHost(String dbHost) {
		DBConnection.dbHost = dbHost;
	}
	/**
	 * Gibt den Port des Datenbankservers zurück.
	 * @return dbPort
	 */
	public static String getDbPort() {
		return dbPort;
	}
	/**
	 * Setzt den Port des Datenbankservers.
	 * @param dbPort
	 */
	public static void setDbPort(String dbPort) {
		DBConnection.dbPort = dbPort;
	}
	/**
	 * Gibt den Datenbankbenutzer zurück.
	 * @return dbUser
	 */
	public static String getDbUser() {
		return dbUser;
	}
	/**
	 * Setzt den Datenbankbenutzer.
	 * @param dbUser
	 */
	public static void setDbUser(String dbUser) {
		DBConnection.dbUser = dbUser;
	}
	/**
	 * Gibt das Datenbankpasswort zurück.
	 * @return dbPass
	 */
	public static String getDbPass() {
		return dbPass;
	}
	/**
	 * Setzt das Datenbankpasswort.
	 * @param dbPass
	 */
	public static void setDbPass(String dbPass) {
		DBConnection.dbPass = dbPass;
	}
	/**
	 * Gibt den Namen den Datenbank zurück.
	 * @return dbName
	 */
	public static String getDbName() {
		return dbName;
	}
	/**
	 * Setzt den Datenbanknamen.
	 * @param dbName
	 */
	public static void setDbName(String dbName) {
		DBConnection.dbName = dbName;
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch 
	 * <code>DBConnection.connection()</code>. Sie stellt die 
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
	 * Instanz von <code>DBConnection</code> existiert.<p>
	 * 
	 * <b>Fazit:</b> DBConnection sollte nicht mittels <code>new</code> 
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.<p>
	 * 
	 * <b>Nachteil:</b> Bei Zusammenbruch der Verbindung zur Datenbank - dies kann
	 * z.B. durch ein unbeabsichtigtes Herunterfahren der Datenbank ausgelöst 
	 * werden - wird keine neue Verbindung aufgebaut, so dass die in einem solchen
	 * Fall die gesamte Software neu zu starten ist. In einer robusten Lösung 
	 * würde man hier die Klasse dahingehend modifizieren, dass bei einer nicht
	 * mehr funktionsfähigen Verbindung stets versucht würde, eine neue Verbindung
	 * aufzubauen. Dies würde allerdings ebenfalls den Rahmen dieses Projekts 
	 * sprengen.
	 * 
	 * @return DAS <code>DBConnection</code>-Objekt.
	 * @see con
	 */

	public static Connection connection() {
		// Wenn es bisher keine Conncetion zur DB gab, ... 
		if (con == null) {
	    try {
	    	// Ersteinmal muss der passende DB-Treiber geladen werden
	    	DriverManager.registerDriver(new AppEngineDriver()); // Create driver from GWT-Package
	    	
			/*
			 * Dann erst kann uns der DriverManager eine Verbindung mit den oben
			 * in der Variable url angegebenen Verbindungsinformationen aufbauen.
			 * 
			 * Diese Verbindung wird dann in der statischen Variable con 
			 * abgespeichert und fortan verwendet.
			 */

	        //Class.forName("com.mysql.jdbc.Driver"); // Datenbanktreiber für JDBC Schnittstellen laden.

	        // Verbindung zur JDBC-Datenbank herstellen.
	        con = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+ dbPort+"/"+dbName+"?"+"user="+dbUser+"&"+"password="+dbPass);

	     //   con = DriverManager.getConnection("jdbc:google:rdbms://smp-itp:elite/db", "root", "");


	    } catch (SQLException error) { //Wenn ein Fehler auftritt -> Verbidung trennen
	    	con = null;
	        System.out.println("Verbindung nicht moglich");
	        System.out.println("SQLException: " + error.getMessage());
	        System.out.println("SQLState: " + error.getSQLState());
	        System.out.println("VendorError: " + error.getErrorCode());
	    }
	  }
		// Zurückgegeben der Verbindung
		return con;
	}
}