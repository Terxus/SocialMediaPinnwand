package de.elite.itprojekt.server.db;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Verwaltung einer Verbindung zur Datenbank. <p>
 * <b>Vorteil:</b> Sehr einfacer Vebindungsaufbau zur Datenbanl. <p>
 * <b>Nachteil:<b> Durch dieSingleton-Eigenschaft der Klasse kann nu auf eine 
 * fest vorgegebene Datenbankk zugegriffen werden. <p>
 * @author Yen Nguyen, in Anlehnung an Thies
 *
 */
public class DBConnection {
	/**
	 * Die Klasse DBConnection wird nur einmal instanziiert. Man spricht heierbei
	 * vnn einem sogenannten <b>Singleton</b>.<p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für 
	 * sämtliche eventuellen Instanzen dieser Klassse vorhanden. Sie speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see AbonnementMapper()
	 * @see KommentarMapper()
	 * @see LikeMapper()
	 * @see NutzerMapper()
	 * @see PinnwandMapper()
	 * @see TextbeitragMapper()
	 */
	private static Connection con = null;
	
	/**
	 * Die URL, mit deren Hilfe die Datenbank angesprochen wird. In einer
	 * professionellen Applikation würde diese Zeichenkette aus einer
	 * Konfiguratonsdatei eingelese oder über einen Parameter von außen
	 * mitgegeben, um bei einer Veränderung dieser URL nicht die gesamte 
	 * Software neu komiieren zu müssen.
	 */
	private static String url ="";
	/**
	 * Diese statische Methode kann aufgerufen werden durch 
	 * <code>DB_COnnection.connection()</code>. Sie erstellt die
	 * Singleton-Eigenschaft sicher, indem sie dafür sorgt, dass nur eine einzige 
	 * Instanz von <code>DBConnection</code> existiert. <p>
	 * 
	 * <b>Fazit:</b> DBConnection sollte nicht mittels <code>new</code>
	 * instanziiert werden, sondern stets durch Aufruf dieser statischen Methde.<p>
	 * 
	 * <b>Nachtei:<b> Bei Zusammenbruch der Verbindung zur Datenbank - dies kann z.B.
	 * durch ein unabsichtiges Herunterfahren der Datenbank ausgelöst werden -
	 * wird keine neue Verbindung aufgebaut, so dass die in einem solchen Fall die 
	 * gesamte Software neu zu starten ist, In einer robusten Lösung würde man hier 
	 * die Klasse dahingehend modifizieren, dass bei einer nicht mehr funktionsfähigen 
	 * Verbindung stets versucht würde, eine neue Verbindung aufzubauen. Dies würde
	 * allerdings ebenfalls den Rahmen dieses Projekts sprengen.
	 * 
	 * @return DAS <code>DBConnection</code>-Objekt.
	 * @see con
	 */
	public static Connection connection() {
		// Wenn es bisher keine Connection zur DB gab, ...
		if ( con == null) {
			try {
				// Ersteinmal muss der passende SB-Treiber geladen werden
				DriverManager.registerDriver(new AppEngingeDriver());
				
				/*
				 * Dann erst kann uns der DriveManager eine Verbindung mit den oben 
				 * in der Variable url angegebenen Verbindungsinformation aufbauen.
				 * 
				 * Diese Verbindung wird dann in der statischen Variable von 
				 * abgespeichert und fortan verwendet.
				 */
				con = DriverManager.getConnection(url);
			}
			catch (SQLException e1) {
				con = null;
				e1.printStackTrace();
			}
		}
		// Zurückgeben der Verbindung
		return con;
	}
}
