package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.client.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Abonnement</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukten in Objekte umgewandelt werden.
 * <p>
 * @author Yen Nguyen, in Anlehnung an Thies
 *
 */

public class AbonnementMapper {
	
	/**
	 * Die Klasse AbonnementMapper wird nur einmal instanziiert. Man spricht hierbei 
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see aMapper()
	 */
private static AbonnementMapper aMapper = null;
	
	/**
	 * Geschützer Konstruktor - verhindert die Möglichkeit, mit <ode>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected AbonnementMapper() {
	}
	
	/**
	 * Diese statisce Methode kann aufgerufen werden durch 
	 * <code>AbonnementMapper.aMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem sie dafür sorgt, dass nur eine einzige 
	 * Instanz von <code>AbonnementMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> AbonnementMapper sollte nicht mittels <code>new</code>
	 * instanziiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 *  @return DAS <code>AbonnementMapper</code>-Objekt.
	 *  @see tMapper
	 */
	public static AbonnementMapper aMapper() {
		if (aMapper == null) {
			aMapper = new AbonnementMapper();
		}
		
		return aMapper;
	}
	
	/**
	 * Einfügen eines <code>Abonnement</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des Übergebenen Objekts geprüft und ggf.
	 * berichtig.
	 * 
	 * @param a das zu speichernde Objekt
	 * @return das bereits Übergebene Objekt, jedoch mit ggf. korrigierter
	 * <code>ID</code>.
	 */
	
	public Abonnement erstellen (Abonnement a){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/*
			 * Zunächst schauen wir nach, welches der mmentan höchste 
			 * Primärschüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
					+ "FROM abonnement ");
			
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * a erhält den bisher maximalen, un um 1 inkremetierten
				 * Primärschlüssel.
				 */
				a.setID(rs.getInt("maxid")+1);
				
			stmt = con.createStatement();
			
			//Jetzt erst erfolgt die tatsächliche Einfügeoperation
			stmt.executeUpdate("INSERT INTO abonnement (ID, Erstellzeitpunkt , NutzerID, PinnwandID" + "VALUES ("
			+ a.getID() + "," + a.getErstellzeitpunkt() + "," + a.getNutzerID() + "," + a.getPinnwandID() + "')");
				
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			/*
			 * Rückgabe, des vtl. korrigierten Leistungsbringung.
			 * 
			 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
			 * Objekte üergeben werden, wäre die NApassung des Abonnement-Objekts auch 
			 * ohne diese esplizite Rückgabe außerhalb dieser Methode sichtbar. Die
			 * explizite Rückgabe von t ist eher ein Stilmittel, um zu signalisieren, 
			 * dass sich das Objekt evtl. im Laufer der Methode verändert hat.
			 */
			return a;
	}
			
	/**
	 * Löschen der Daten eines <code>Abonnement</code>-Objekts aus der Datenbank.
	 * 
	 * @param a auf der DB zu löschende "objekt"
	 */

	public void löschenT(Abonnement a){
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM abonnement " + "WHERE id=" + a.getID());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Suchen eines Abonnements mit vorgegebener Abonnement ID. Da diese eindeutig ist,
	 * wird genau ein Objekt zurückgegeben.
	 * 
	 *  @param ID Primärschlüsselattribut (->DB)
	 *  @return Abonnement-Objekt, das dem übergebenen Schlüssel entspricht, null bei 
	 *  nicht vohandenem DB-Tupel.
	 */
	
	public Abonnement findeDurchID(int id) {
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try {
			//Leeeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			//Statement ausfüllen und als Query an die B schicken
			ResultSet rs = stmt.executeQuery("SELECT ID, Datum, Uhrzeit, NutzerID, PinnwandID "
					+ "WHERE ID=" + id + " ORDER BY Erstellzeitpunkt");
			
			/*
			 * Da ID PRimärschlüsse ist, kann max. nur ein Tupel zurückgegeben werden. prüf, ob ein ergebnis vorliegt.
			 */
			if (rs.next()) {
				//Ergebnis-Tupel in Objekt umwandeln
				Abonnement a = new Abonnement();
				a.setID(rs.getInt("ID"));
				a.setErstellzeitpunkt(rs.getDate("Erstellzeitpunkt"));
				a.setNutzerID(rs.getInt("NutzerID"));
				a.setPinnwandID(rs.getInt ("PinnwandID"));
				return a;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
			
		}
		return null;
	}
	
				
}


