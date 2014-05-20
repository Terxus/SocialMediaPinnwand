package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.client.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Abonnement</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
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
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	 * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see aMapper()
	 */
private static AbonnementMapper aMapper = null;
	
	/**
	 * Gesch�tzer Konstruktor - verhindert die M�glichkeit, mit <ode>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected AbonnementMapper() {
	}
	
	/**
	 * Diese statisce Methode kann aufgerufen werden durch 
	 * <code>AbonnementMapper.aMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem sie daf�r sorgt, dass nur eine einzige 
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
	 * Einf�gen eines <code>Abonnement</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	 * berichtig.
	 * 
	 * @param a das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 * <code>ID</code>.
	 */
	
	public Abonnement erstellen (Abonnement a){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/*
			 * Zun�chst schauen wir nach, welches der mmentan h�chste 
			 * Prim�rsch�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
					+ "FROM abonnement ");
			
			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * a erh�lt den bisher maximalen, un um 1 inkremetierten
				 * Prim�rschl�ssel.
				 */
				a.setID(rs.getInt("maxid")+1);
				
			stmt = con.createStatement();
			
			//Jetzt erst erfolgt die tats�chliche Einf�geoperation
			stmt.executeUpdate("INSERT INTO abonnement (ID, Erstellzeitpunkt , NutzerID, PinnwandID" + "VALUES ("
			+ a.getID() + "," + a.getErstellzeitpunkt() + "," + a.getNutzerID() + "," + a.getPinnwandID() + "')");
				
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			/*
			 * R�ckgabe, des vtl. korrigierten Leistungsbringung.
			 * 
			 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
			 * Objekte �ergeben werden, w�re die NApassung des Abonnement-Objekts auch 
			 * ohne diese esplizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
			 * explizite R�ckgabe von t ist eher ein Stilmittel, um zu signalisieren, 
			 * dass sich das Objekt evtl. im Laufer der Methode ver�ndert hat.
			 */
			return a;
	}
			
	/**
	 * L�schen der Daten eines <code>Abonnement</code>-Objekts aus der Datenbank.
	 * 
	 * @param a auf der DB zu l�schende "objekt"
	 */

	public void l�schenT(Abonnement a){
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
	 * wird genau ein Objekt zur�ckgegeben.
	 * 
	 *  @param ID Prim�rschl�sselattribut (->DB)
	 *  @return Abonnement-Objekt, das dem �bergebenen Schl�ssel entspricht, null bei 
	 *  nicht vohandenem DB-Tupel.
	 */
	
	public Abonnement findeDurchID(int id) {
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try {
			//Leeeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			//Statement ausf�llen und als Query an die B schicken
			ResultSet rs = stmt.executeQuery("SELECT ID, Datum, Uhrzeit, NutzerID, PinnwandID "
					+ "WHERE ID=" + id + " ORDER BY Erstellzeitpunkt");
			
			/*
			 * Da ID PRim�rschl�sse ist, kann max. nur ein Tupel zur�ckgegeben werden. pr�f, ob ein ergebnis vorliegt.
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


