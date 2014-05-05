package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Kommentar</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukten in Objekte umgewandelt werden.
 * <p>
 * @author Yen Nguyen, in Anlehnung an Thies
 *
 */

public class KommentarMapper {
	/**
	 * Die Klasse KommentarMapper wird nur einmal instanziiert. Man spricht hierbei 
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	 * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see kMapper()
	 */
	private static KommentarMapper kMapper = null;
	
	/**
	 * Gesch�tzer Konstruktor - verhindert die M�glichkeit, mit <ode>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected KommentarMapper() {
	}
	
	/**
	 * Diese statisce Methode kann aufgerufen werden durch 
	 * <code>KommentarMapper.kMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem sie daf�r sorgt, dass nur eine einzige 
	 * Instanz von <code>KommentarMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> KommentarMapper sollte nicht mittels <code>new</code>
	 * instanziiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 *  @return DAS <code>KommentarMapper</code>-Objekt.
	 *  @see kMapper
	 */
	public static KommentarMapper kMapper() {
		if (kMapper == null) {
			kMapper = new KommentarMapper();
		}
		
		return kMapper;
	}
	
	/**
	 * Einf�gen eines <code>Kommentar</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	 * berichtig.
	 * 
	 * @param k das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 * <code>ID</code>.
	 */
	
	public Kommentar erstellen (Kommentar k){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/*
			 * Zun�chst schauen wir nach, welches der mmentan h�chste 
			 * Prim�rsch�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
					+ "FROM kommentar ");
			
			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * k erh�lt den bisher maximalen, un um 1 inkremetierten
				 * Prim�rschl�ssel.
				 */
				k.setID(rs.getInt("maxid")+1);
				
			stmt = con.createStatement();
			
			//Jetzt erst erfolgt die tats�chliche Einf�geoperation
			stmt.executeUpdate("INSERT INTO kommentar (ID, Text, Datum, Uhrzeit, NutzerID, BeitragsID, LikeID" + "VALUES ("
			+ k.getID() + "," + k.getText() + "," + k.getDatum() + "," + k.getUhrzeit() + "," + k.getNutzerID()+ "," + k.getBeitragsID()
			+ "," + k.getLikeID() + "')");
				
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			/*
			 * R�ckgabe, des vtl. korrigierten Leistungsbringung.
			 * 
			 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
			 * Objekte �ergeben werden, w�re die Anpassung des Kommentar-Objekts auch 
			 * ohne diese esplizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
			 * explizite R�ckgabe von t ist eher ein Stilmittel, um zu signalisieren, 
			 * dass sich das Objekt evtl. im Laufer der Methode ver�ndert hat.
			 */
			return k;
	}
			
	/** Wiederholtes Schreiben eines Objekts in die Datanbank.
	 * 
	 * @param k das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
	 */
	
	public Kommentar bearbeiten(Kommentar k){
		Connection c = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE Kommentar " + "SET Text= '" + k.getText() + "' " + "WHERE id=" + k.getID());
								
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		// um Analoge zu insert(Textbeitrag k) zu wahren, geben wir k zur�ck
		return k;
	}
	
	/**
	 * L�schen der Daten eines <code>Kommentar</code>-Objekts aus der Datenbank.
	 * 
	 * @param k auf der DB zu l�schende "objekt"
	 */

	public void l�schen(Kommentar k){
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM kommentar " + "WHERE id=" + k.getID());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Suchen eines Kommentars mit vorgegebener Kommentar ID. Da diese eindeutig ist,
	 * wird genau ein Objekt zur�ckgegeben.
	 * 
	 *  @param ID Prim�rschl�sselattribut (->DB)
	 *  @return Kommentar-Objekt, das dem �bergebenen Schl�ssel entspricht, null bei 
	 *  nicht vohandenem DB-Tupel.
	 */
	
	public Kommentar findeDurchID(int id) {
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try {
			//Leeeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			//Statement ausf�llen und als Query an die B schicken
			ResultSet rs = stmt.executeQuery("SELECT ID, Text, Datum, Uhrzeit, NutzerID, BeitragsID, LikeID FROM kommentar "
					+ "WHERE ID=" + id + " ORDER BY Datum");
			
			/*
			 * Da ID PRim�rschl�sse ist, kann max. nur ein Tupel zur�ckgegeben werden. pr�f, ob ein ergebnis vorliegt.
			 */
			if (rs.next()) {
				//Ergebnis-Tupel in Objekt umwandeln
				Kommentar k = new Kommentar();
				k.setID(rs.getInt("ID"));
				k.setText(rs.getString ("Text"));
				k.setDatum(rs.getDate("Datum"));
				k.setUhrzeit(rs.getTime("Uhrzeit"));
				k.setNutzerID(rs.getInt("NutzerID"));
				k.setBeitragsID(rs.getInt("BeitragsID"));
				k.setLikeID(rs.getInt("LikeID"));
				return k;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
			
		}
		return null;
	}

}
