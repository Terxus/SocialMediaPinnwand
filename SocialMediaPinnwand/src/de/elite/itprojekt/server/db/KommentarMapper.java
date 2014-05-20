package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Kommentar;
import de.elite.itprojekt.shared.bo.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Kommentar</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
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
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see kMapper()
	 */
	private static KommentarMapper kMapper = null;
	
	/**
	 * Geschützer Konstruktor - verhindert die Möglichkeit, mit <ode>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected KommentarMapper() {
	}
	
	/**
	 * Diese statisce Methode kann aufgerufen werden durch 
	 * <code>KommentarMapper.kMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem sie dafür sorgt, dass nur eine einzige 
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
	 * Einfügen eines <code>Kommentar</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des Übergebenen Objekts geprüft und ggf.
	 * berichtig.
	 * 
	 * @param k das zu speichernde Objekt
	 * @return das bereits Übergebene Objekt, jedoch mit ggf. korrigierter
	 * <code>ID</code>.
	 */
	
	public Kommentar erstellen (Kommentar k){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/*
			 * Zunächst schauen wir nach, welches der mmentan höchste 
			 * Primärschüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
					+ "FROM kommentar ");
			
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * k erhält den bisher maximalen, un um 1 inkremetierten
				 * Primärschlüssel.
				 */
				k.setID(rs.getInt("maxid")+1);
				
			stmt = con.createStatement();
			
			//Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO kommentar (ID, Text, Erstellzeitpunkt, NutzerID, BeitragsID, LikeID" + "VALUES ("
			+ k.getID() + "," + k.getText() + "," + k.getErstellzeitpunkt() + "," + "," + k.getNutzerID()+ "," + k.getBeitragID()
			+ "," + "')");
				
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			/*
			 * Rückgabe, des vtl. korrigierten Leistungsbringung.
			 * 
			 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
			 * Objekte üergeben werden, wäre die Anpassung des Kommentar-Objekts auch 
			 * ohne diese esplizite Rückgabe außerhalb dieser Methode sichtbar. Die
			 * explizite Rückgabe von t ist eher ein Stilmittel, um zu signalisieren, 
			 * dass sich das Objekt evtl. im Laufer der Methode verändert hat.
			 */
			return k;
	}
			
	/** Wiederholtes Schreiben eines Objekts in die Datanbank.
	 * 
	 * @param k das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
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
		
		// um Analoge zu insert(Textbeitrag k) zu wahren, geben wir k zurück
		return k;
	}
	
	/**
	 * Löschen der Daten eines <code>Kommentar</code>-Objekts aus der Datenbank.
	 * 
	 * @param k auf der DB zu löschende "objekt"
	 */

	public void löschen(Kommentar k){
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
	 * wird genau ein Objekt zurückgegeben.
	 * 
	 *  @param ID Primärschlüsselattribut (->DB)
	 *  @return Kommentar-Objekt, das dem übergebenen Schlüssel entspricht, null bei 
	 *  nicht vohandenem DB-Tupel.
	 */
	
	public Kommentar findeDurchID(int id) {
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try {
			//Leeeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			//Statement ausfüllen und als Query an die B schicken
			ResultSet rs = stmt.executeQuery("SELECT ID, Text, Datum, Uhrzeit, NutzerID, BeitragsID, LikeID FROM kommentar "
					+ "WHERE ID=" + id + " ORDER BY Datum");
			
			/*
			 * Da ID PRimärschlüsse ist, kann max. nur ein Tupel zurückgegeben werden. prüf, ob ein ergebnis vorliegt.
			 */
			if (rs.next()) {
				//Ergebnis-Tupel in Objekt umwandeln
				Kommentar k = new Kommentar();
				k.setID(rs.getInt("ID"));
				k.setText(rs.getString ("Text"));
				k.setErstellzeitpunkt(rs.getDate("Erstellzeitpunkt"));
				k.setNutzerID(rs.getInt("NutzerID"));
				k.setBeitragID(rs.getInt("BeitragID"));
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
