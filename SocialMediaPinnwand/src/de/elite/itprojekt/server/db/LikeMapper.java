package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Like;
import de.elite.itprojekt.shared.bo.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Like</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukten in Objekte umgewandelt werden.
 * <p>
 * @author Yen Nguyen, in Anlehnung an Thies
 *
 */
public class LikeMapper {

	/**
		 * Die Klasse LikeMapper wird nur einmal instanziiert. Man spricht hierbei 
		 * von einem sogenannten <b>Singleton</b>.
		 * <p>
		 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
		 * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
		 * einzige Instanz dieser Klasse.
		 * 
		 * @see lMapper()
		 */
		private static LikeMapper lMapper = null;
		
		/**
		 * Gesch�tzer Konstruktor - verhindert die M�glichkeit, mit <ode>new</code>
		 * neue Instanzen dieser Klasse zu erzeugen.
		 */
		protected LikeMapper() {
		}
		
		/**
		 * Diese statisce Methode kann aufgerufen werden durch 
		 * <code>LikeMapper.tMapper()</code>. Sie stellt die
		 * Singleton-Eigenschaft sicher, indem sie daf�r sorgt, dass nur eine einzige 
		 * Instanz von <code>LikeMapper</code> existiert.
		 * <p>
		 * 
		 * <b>Fazit:</b> LikeMapper sollte nicht mittels <code>new</code>
		 * instanziiert werden, sondern stets durch Aufruf dieser statischen Methode.
		 *  @return DAS <code>LikeMapper</code>-Objekt.
		 *  @see lMapper
		 */
		public static LikeMapper lMapper() {
			if (lMapper == null) {
				lMapper = new LikeMapper();
			}
			
			return lMapper;
		}
		
		/**
		 * Einf�gen eines <code>Like</code>-Objekts in die Datenbank. Dabei wird
		 * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
		 * berichtig.
		 * 
		 * @param l das zu speichernde Objekt
		 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
		 * <code>ID</code>.
		 */
		
		public Like erstellen (Like l){
			Connection con = DBConnection.connection();
			
			try {
				Statement stmt = con.createStatement();
				
				/*
				 * Zun�chst schauen wir nach, welches der mmentan h�chste 
				 * Prim�rsch�sselwert ist.
				 */
				ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
						+ "FROM like ");
				
				// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
				if (rs.next()) {
					/*
					 * l erh�lt den bisher maximalen, un um 1 inkremetierten
					 * Prim�rschl�ssel.
					 */
					l.setID(rs.getInt("maxid")+1);
					
				stmt = con.createStatement();
				
				//Jetzt erst erfolgt die tats�chliche Einf�geoperation
				stmt.executeUpdate("INSERT INTO textbeitrag (ID, Text, Erstellzeitpunkt, NutzerID" + "VALUES ("
				+ l.getID() + "," + l.getErstellzeitpunkt() + "," + l.getNutzerID()+ "," + l.getBeitragID() +
				"')");
					
					}
				}
				catch (SQLException e2) {
					e2.printStackTrace();
				}
			
				/*
				 * R�ckgabe, des vtl. korrigierten Leistungsbringung.
				 * 
				 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
				 * Objekte �ergeben werden, w�re die Anpassung des Like-Objekts auch 
				 * ohne diese esplizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
				 * explizite R�ckgabe von t ist eher ein Stilmittel, um zu signalisieren, 
				 * dass sich das Objekt evtl. im Laufer der Methode ver�ndert hat.
				 */
				return l;
		}
				
		
		/**
		 * L�schen der Daten eines <code>Like</code>-Objekts aus der Datenbank.
		 * 
		 * @param l auf der DB zu l�schende "objekt"
		 */

		public void l�schen(Like l){
			Connection con =DBConnection.connection();
			
			try {
				Statement stmt = con.createStatement();
				
				stmt.executeUpdate("DELETE FROM like " + "WHERE id=" + l.getID());
				
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
}