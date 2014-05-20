package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Textbeitrag</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukten in Objekte umgewandelt werden.
 * <p>
 * @author Yen Nguyen, in Anlehnung an Thies
 *
 */

public class TextbeitragMapper {
	
	/**
	 * Die Klasse TextbeitragMapper wird nur einmal instanziiert. Man spricht hierbei 
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see tMapper()
	 */
	private static TextbeitragMapper tMapper = null;
	
	/**
	 * Geschützer Konstruktor - verhindert die Möglichkeit, mit <ode>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected TextbeitragMapper() {
	}
	
	/**
	 * Diese statisce Methode kann aufgerufen werden durch 
	 * <code>TextbeitragMapper.tMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem sie dafür sorgt, dass nur eine einzige 
	 * Instanz von <code>TextbreitragMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> TextbeitragMapper sollte nicht mittels <code>new</code>
	 * instanziiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 *  @return DAS <code>TextbeitragMapper</code>-Objekt.
	 *  @see tMapper
	 */
	public static TextbeitragMapper tMapper() {
		if (tMapper == null) {
			tMapper = new TextbeitragMapper();
		}
		
		return tMapper;
	}
	
	/**
	 * Einfügen eines <code>Textbeitrag</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des Übergebenen Objekts geprüft und ggf.
	 * berichtig.
	 * 
	 * @param t das zu speichernde Objekt
	 * @return das bereits Übergebene Objekt, jedoch mit ggf. korrigierter
	 * <code>ID</code>.
	 */
	
	public Textbeitrag erstellen (Textbeitrag t){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/*
			 * Zunächst schauen wir nach, welches der mmentan höchste 
			 * Primärschüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
					+ "FROM textbeitrag ");
			
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * t erhält den bisher maximalen, un um 1 inkremetierten
				 * Primärschlüssel.
				 */
				t.setID(rs.getInt("maxid")+1);
				
			stmt = con.createStatement();
			
			//Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO textbeitrag (ID, Text, Erstellzeitpunkt, Like, NutzerID" + "VALUES ("
			+ t.getID() + "," + t.getText() + "," + t.getErstellzeitpunkt() + "," + t.getLikeID() + "," + t.getNutzerID()+"')");
				
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			/*
			 * Rückgabe, des vtl. korrigierten Leistungsbringung.
			 * 
			 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
			 * Objekte üergeben werden, wäre die Anpassung des Textbeitrag-Objekts auch 
			 * ohne diese esplizite Rückgabe außerhalb dieser Methode sichtbar. Die
			 * explizite Rückgabe von t ist eher ein Stilmittel, um zu signalisieren, 
			 * dass sich das Objekt evtl. im Laufer der Methode verändert hat.
			 */
			return t;
	}
			
	/** Wiederholtes Schreiben eines Objekts in die Datanbank.
	 * 
	 * @param t das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	
	public Textbeitrag bearbeiten(Textbeitrag t){
		Connection c = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE textbeitrag " + "SET Text= '" + t.getText() + "' " + "WHERE id=" + t.getID());
								
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		// um Analoge zu insert(Textbeitrag t) zu wahren, geben wir t zurück
		return t;
	}
	
	/**
	 * Löschen der Daten eines <code>Textbeitrag</code>-Objekts aus der Datenbank.
	 * 
	 * @param t auf der DB zu löschende "objekt"
	 */

	public void löschen(Textbeitrag t){
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM textbeitrag " + "WHERE id=" + t.getID());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Suchen eines Textbeitrags mit vorgegebener Textbeitrag ID. Da diese eindeutig ist,
	 * wird genau ein Objekt zurückgegeben.
	 * 
	 *  @param ID Primärschlüsselattribut (->DB)
	 *  @return Textbeitrag-Objekt, das dem übergebenen Schlüssel entspricht, null bei 
	 *  nicht vohandenem DB-Tupel.
	 */
	
	public Textbeitrag findeDurchID(int id) {
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try {
			//Leeeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			//Statement ausfüllen und als Query an die B schicken
			ResultSet rs = stmt.executeQuery("SELECT ID, Text, Datum, Uhrzeit, NutzerID FROM textbeitrag "
					+ "WHERE ID=" + id + " ORDER BY Datum");
			
			/*
			 * Da ID PRimärschlüsse ist, kann max. nur ein Tupel zurückgegeben werden. prüf, ob ein ergebnis vorliegt.
			 */
			if (rs.next()) {
				//Ergebnis-Tupel in Objekt umwandeln
				Textbeitrag t = new Textbeitrag();
				t.setID(rs.getInt("ID"));
				t.setText(rs.getString ("Text"));
				t.setErstellzeitpunkt(rs.getDate("Erstellzeitpunkt"));
				t.setLikeID(rs.getInt("LikeID"));
				t.setNutzerID(rs.getInt("NutzerID"));
				return t;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
			
		}
		return null;
	}
	
				
}