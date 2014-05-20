package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Nutzer</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukten in Objekte umgewandelt werden.
 * <p>
 * @author Yen Nguyen, in Anlehnung an Thies
 *
 */

public class NutzerMapper {

	/**
	 * Die Klasse NutzerMapper wird nur einmal instanziiert. Man spricht hierbei 
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	 * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see nMapper()
	 */
	private static NutzerMapper nMapper = null;
	
	/**
	 * Gesch�tzer Konstruktor - verhindert die M�glichkeit, mit <ode>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected NutzerMapper() {
	}
	
	/**
	 * Diese statisce Methode kann aufgerufen werden durch 
	 * <code>NutzerMapper.nMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem sie daf�r sorgt, dass nur eine einzige 
	 * Instanz von <code>NutzerMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> NutzerMapper sollte nicht mittels <code>new</code>
	 * instanziiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 *  @return DAS <code>NutzerMapper</code>-Objekt.
	 *  @see nMapper
	 */
	public static NutzerMapper nMapper() {
		if (nMapper == null) {
			nMapper = new NutzerMapper();
		}
		
		return nMapper;
	}
	
	/**
	 * Einf�gen eines <code>Nutzer</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	 * berichtig.
	 * 
	 * @param n das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 * <code>ID</code>.
	 */
	
	public Nutzer erstellen (Nutzer n){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/*
			 * Zun�chst schauen wir nach, welches der mmentan h�chste 
			 * Prim�rsch�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
					+ "FROM nutzer ");
			
			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * n erh�lt den bisher maximalen, un um 1 inkremetierten
				 * Prim�rschl�ssel.
				 */
				n.setID(rs.getInt("maxid")+1);
				
			stmt = con.createStatement();
			
			//Jetzt erst erfolgt die tats�chliche Einf�geoperation
			stmt.executeUpdate("INSERT INTO nutzer (ID, Nickname, Passwort, Email, Vorname, Nachname, Erstellzeitpunkt" + "VALUES ("
			+ n.getID() + "," + n.getNickname() + "," + n.getPasswort() + "," + n.getEMail() + "," + n.getVorname() + "," 
			+ n.getVorname() + "," + n.getErstellzeitpunkt() + "," + "')");
				
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			/*
			 * R�ckgabe, des vtl. korrigierten Leistungsbringung.
			 * 
			 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
			 * Objekte �ergeben werden, w�re die Anpassung des Nutzer-Objekts auch 
			 * ohne diese esplizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
			 * explizite R�ckgabe von t ist eher ein Stilmittel, um zu signalisieren, 
			 * dass sich das Objekt evtl. im Laufer der Methode ver�ndert hat.
			 */
			return n;
	}
			
	/**
	 * L�schen der Daten eines <code>Nutzer</code>-Objekts aus der Datenbank.
	 * 
	 * @param n auf der DB zu l�schende "objekt"
	 */

	public void l�schen(Nutzer n){
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM nutzer " + "WHERE id=" + n.getID());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Suchen eines Nutzers mit vorgegebener Nutzer ID. Da diese eindeutig ist,
	 * wird genau ein Objekt zur�ckgegeben.
	 * 
	 *  @param ID Prim�rschl�sselattribut (->DB)
	 *  @return Nutzer-Objekt, das dem �bergebenen Schl�ssel entspricht, null bei 
	 *  nicht vohandenem DB-Tupel.
	 */
	
	public Nutzer findeDurchID(int id) {
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try {
			//Leeeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			//Statement ausf�llen und als Query an die B schicken
			ResultSet rs = stmt.executeQuery("SELECT ID, Nickname, Passwort, Email, Datum, Uhrzeit, Vorname, Nachname "
					+ "FROM nutzer "
					+ "WHERE ID=" + id + " ORDER BY Datum");
			
			/*
			 * Da ID PRim�rschl�sse ist, kann max. nur ein Tupel zur�ckgegeben werden. pr�f, ob ein ergebnis vorliegt.
			 */
			if (rs.next()) {
				//Ergebnis-Tupel in Objekt umwandeln
				Nutzer n = new Nutzer();
				n.setID(rs.getInt("ID"));
				n.setNickname(rs.getString ("Nickname"));
				n.setPasswort(rs.getString ("Passwort"));
				n.setEMail(rs.getString ("EMail"));
				n.setErstellzeitpunkt(rs.getDate("Erstellzeitpunkt"));
				n.setVorname(rs.getString ("Vorname"));
				n.setNachname(rs.getString ("Nachname"));
				return n;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
			
		}
		return null;
	}
	
				
}
