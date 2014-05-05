package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Pinnwand</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukten in Objekte umgewandelt werden.
 * <p>
 * @author Yen Nguyen, in Anlehnung an Thies
 *
 */

public class PinnwandMapper {
	/**
	 * Die Klasse PinnwandMapper wird nur einmal instanziiert. Man spricht hierbei 
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see pMapper()
	 */
	private static PinnwandMapper pMapper = null;
	
	/**
	 * Geschützer Konstruktor - verhindert die Möglichkeit, mit <ode>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected PinnwandMapper() {
	}
	
	/**
	 * Diese statisce Methode kann aufgerufen werden durch 
	 * <code>PinnwandMapper.pMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem sie dafür sorgt, dass nur eine einzige 
	 * Instanz von <code>PinnwandMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> PinnwandMapper sollte nicht mittels <code>new</code>
	 * instanziiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 *  @return DAS <code>PinnwandMapper</code>-Objekt.
	 *  @see pMapper
	 */
	public static PinnwandMapper aMapper() {
		if (aMapper == null) {
			aMapper = new PinnwandMapper();
		}
		
		return aMapper;
	}
	
	/**
	 * Einfügen eines <code>Pinnwand</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des Übergebenen Objekts geprüft und ggf.
	 * berichtig.
	 * 
	 * @param p das zu speichernde Objekt
	 * @return das bereits Übergebene Objekt, jedoch mit ggf. korrigierter
	 * <code>ID</code>.
	 */
	
	public Pinnwand erstellen (Pinnwand p){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/*
			 * Zunächst schauen wir nach, welches der mmentan höchste 
			 * Primärschüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
					+ "FROM pinnwand ");
			
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * p erhält den bisher maximalen, un um 1 inkremetierten
				 * Primärschlüssel.
				 */
				p.setID(rs.getInt("maxid")+1);
				
			stmt = con.createStatement();
			
			//Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO textbeitrag (ID, Datum, Uhrzeit, NutzerID, AbonnementID" + "VALUES ("
			+ p.getID() + "," + p.getDatum() + "," + p.getUhrzeit() + "," + p.getNutzerID() + "," + p.getAbonnementID() +"')");
				
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			/*
			 * Rückgabe, des vtl. korrigierten Leistungsbringung.
			 * 
			 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
			 * Objekte üergeben werden, wäre die NApassung des Pinnwand-Objekts auch 
			 * ohne diese esplizite Rückgabe außerhalb dieser Methode sichtbar. Die
			 * explizite Rückgabe von t ist eher ein Stilmittel, um zu signalisieren, 
			 * dass sich das Objekt evtl. im Laufer der Methode verändert hat.
			 */
			return p;
	}
	

	/**
	 * Löschen der Daten eines <code>Pinnwand</code>-Objekts aus der Datenbank.
	 * 
	 * @param p auf der DB zu löschende "objekt"
	 */

	public void löschenT(Pinnwand p){
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM pinnwand " + "WHERE id=" + p.getID());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Suchen eines Pinnwands mit vorgegebener Pinnwand ID. Da diese eindeutig ist,
	 * wird genau ein Objekt zurückgegeben.
	 * 
	 *  @param ID Primärschlüsselattribut (->DB)
	 *  @return Pinnwand-Objekt, das dem übergebenen Schlüssel entspricht, null bei 
	 *  nicht vohandenem DB-Tupel.
	 */
	
	public Pinnwand findeDurchID(int id) {
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try {
			//Leeeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			//Statement ausfüllen und als Query an die B schicken
			ResultSet rs = stmt.executeQuery("SELECT ID, Datum, Uhrzeit, NutzerID, AbonnementID FROM pinnwand "
					+ "WHERE ID=" + id + " ORDER BY Datum");
			
			/*
			 * Da ID PRimärschlüsse ist, kann max. nur ein Tupel zurückgegeben werden. prüf, ob ein ergebnis vorliegt.
			 */
			if (rs.next()) {
				//Ergebnis-Tupel in Objekt umwandeln
				Pinnwand p = new Pinnwand();
				p.setID(rs.getInt("ID"));
				p.setDatum(rs.getDate("Datum"));
				p.setUhrzeit(rs.getTime("Uhrzeit"));
				p.setNutzerID(rs.getInt("NutzerID"));
				p.setAbonnementID(rs.getInt ("AbonnementID"));
				return p;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
			
		}
		return null;
	}
	
				
}
