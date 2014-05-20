package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Pinnwand;
import de.elite.itprojekt.shared.bo.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Pinnwand</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
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
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
	 * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @see pMapper()
	 */
	private static PinnwandMapper pMapper = null;
	
	/**
	 * Gesch�tzer Konstruktor - verhindert die M�glichkeit, mit <ode>new</code>
	 * neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected PinnwandMapper() {
	}
	
	/**
	 * Diese statisce Methode kann aufgerufen werden durch 
	 * <code>PinnwandMapper.pMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem sie daf�r sorgt, dass nur eine einzige 
	 * Instanz von <code>PinnwandMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> PinnwandMapper sollte nicht mittels <code>new</code>
	 * instanziiert werden, sondern stets durch Aufruf dieser statischen Methode.
	 *  @return DAS <code>PinnwandMapper</code>-Objekt.
	 *  @see pMapper
	 */
	public static PinnwandMapper pMapper() {
		if (pMapper == null) {
			pMapper = new PinnwandMapper();
		}
		
		return pMapper;
	}
	
	/**
	 * Einf�gen eines <code>Pinnwand</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	 * berichtig.
	 * 
	 * @param p das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 * <code>ID</code>.
	 */
	
	public Pinnwand erstellen (Pinnwand p){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/*
			 * Zun�chst schauen wir nach, welches der mmentan h�chste 
			 * Prim�rsch�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
					+ "FROM pinnwand ");
			
			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * p erh�lt den bisher maximalen, un um 1 inkremetierten
				 * Prim�rschl�ssel.
				 */
				p.setID(rs.getInt("maxid")+1);
				
			stmt = con.createStatement();
			
			//Jetzt erst erfolgt die tats�chliche Einf�geoperation
			stmt.executeUpdate("INSERT INTO textbeitrag (ID, Datum, Uhrzeit, NutzerID, AbonnementID" + "VALUES ("
			+ p.getID() + "," + p.getErstellzeitpunkt() + ","+ p.getNutzerID() + "," + p.getAbonnementID() +"')");
				
				}
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			/*
			 * R�ckgabe, des vtl. korrigierten Leistungsbringung.
			 * 
			 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
			 * Objekte �ergeben werden, w�re die NApassung des Pinnwand-Objekts auch 
			 * ohne diese esplizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
			 * explizite R�ckgabe von t ist eher ein Stilmittel, um zu signalisieren, 
			 * dass sich das Objekt evtl. im Laufer der Methode ver�ndert hat.
			 */
			return p;
	}
	

	/**
	 * L�schen der Daten eines <code>Pinnwand</code>-Objekts aus der Datenbank.
	 * 
	 * @param p auf der DB zu l�schende "objekt"
	 */

	public void l�schenT(Pinnwand p){
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
	 * wird genau ein Objekt zur�ckgegeben.
	 * 
	 *  @param ID Prim�rschl�sselattribut (->DB)
	 *  @return Pinnwand-Objekt, das dem �bergebenen Schl�ssel entspricht, null bei 
	 *  nicht vohandenem DB-Tupel.
	 */
	
	public Pinnwand findeDurchID(int id) {
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		try {
			//Leeeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			//Statement ausf�llen und als Query an die B schicken
			ResultSet rs = stmt.executeQuery("SELECT ID, Datum, Uhrzeit, NutzerID, AbonnementID FROM pinnwand "
					+ "WHERE ID=" + id + " ORDER BY Datum");
			
			/*
			 * Da ID PRim�rschl�sse ist, kann max. nur ein Tupel zur�ckgegeben werden. pr�f, ob ein ergebnis vorliegt.
			 */
			if (rs.next()) {
				//Ergebnis-Tupel in Objekt umwandeln
				Pinnwand p = new Pinnwand();
				p.setID(rs.getInt("ID"));
				p.setErstellzeitpunkt(rs.getDate("Erstellzeitpunkt"));
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
