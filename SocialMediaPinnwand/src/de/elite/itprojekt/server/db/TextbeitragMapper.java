package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Textbeitrag</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
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
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	 * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see tMapper()
	 */
	private static TextbeitragMapper tMapper = null;
	
	/**
	 * Gesch�tzer Konstruktor - verhindert die M�glichkeit, mit <ode>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected TextbeitragMapper() {
	}
	
	/**
	 * Diese statisce Methode kann aufgerufen werden durch 
	 * <code>TextbeitragMapper.tMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem sie daf�r sorgt, dass nur eine einzige 
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
	 * Einf�gen eines <code>Textbeitrag</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	 * berichtig.
	 * 
	 * @param t das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 * <code>ID</code>.
	 */
	
	public Textbeitrag erstellen (Textbeitrag t){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/*
			 * Zun�chst schauen wir nach, welches der mmentan h�chste 
			 * Prim�rsch�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
					+ "FROM textbeitrag ");
			
			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * t erh�lt den bisher maximalen, un um 1 inkremetierten
				 * Prim�rschl�ssel.
				 */
				t.setID(rs.getInt("maxid")+1);
				
			stmt = con.createStatement();
			
			//Jetzt erst erfolgt die tats�chliche Einf�geoperation
			stmt.executeUpdate("INSERT INTO textbeitrag (ID, Text, Datum, Uhrzeit, NutzerID" + "VALUES ("
			+ t.getID() + "," + t.getText() + "," + t.getDatum() + "," + t.getUhrzeit() + "," + t.getNutzerID()+"')");
				
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			/*
			 * R�ckgabe, des vtl. korrigierten Leistungsbringung.
			 * 
			 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
			 * Objekte �ergeben werden, w�re die Anpassung des Textbeitrag-Objekts auch 
			 * ohne diese esplizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
			 * explizite R�ckgabe von t ist eher ein Stilmittel, um zu signalisieren, 
			 * dass sich das Objekt evtl. im Laufer der Methode ver�ndert hat.
			 */
			return t;
	}
			
	/** Wiederholtes Schreiben eines Objekts in die Datanbank.
	 * 
	 * @param t das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
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
		
		// um Analoge zu insert(Textbeitrag t) zu wahren, geben wir t zur�ck
		return t;
	}
	
	/**
	 * L�schen der Daten eines <code>Textbeitrag</code>-Objekts aus der Datenbank.
	 * 
	 * @param t auf der DB zu l�schende "objekt"
	 */

	public void l�schen(Textbeitrag t){
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
	 * wird genau ein Objekt zur�ckgegeben.
	 * 
	 *  @param ID Prim�rschl�sselattribut (->DB)
	 *  @return Textbeitrag-Objekt, das dem �bergebenen Schl�ssel entspricht, null bei 
	 *  nicht vohandenem DB-Tupel.
	 */
	
	public Textbeitrag findeDurchID(int id) {
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try {
			//Leeeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			//Statement ausf�llen und als Query an die B schicken
			ResultSet rs = stmt.executeQuery("SELECT ID, Text, Datum, Uhrzeit, NutzerID FROM textbeitrag "
					+ "WHERE ID=" + id + " ORDER BY Datum");
			
			/*
			 * Da ID PRim�rschl�sse ist, kann max. nur ein Tupel zur�ckgegeben werden. pr�f, ob ein ergebnis vorliegt.
			 */
			if (rs.next()) {
				//Ergebnis-Tupel in Objekt umwandeln
				Textbeitrag t = new Textbeitrag();
				t.setID(rs.getInt("ID"));
				t.setText(rs.getString ("Text"));
				t.setDatum(rs.getDate("Datum"));
				t.setUhrzeit(rs.getTime("Uhrzeit"));
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