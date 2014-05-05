package de.elite.itprojekt.server.db;

import java.sql.SQLException;

import com.google.cloud.sql.jdbc.Connection;
import com.google.cloud.sql.jdbc.ResultSet;
import com.google.cloud.sql.jdbc.Statement;

import de.elite.itprojekt.shared.bo.Textbeitrag;

/**
 * Mapper-Klassen, die <code>Like</code>-Objekte auf eine relationale 
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügng 
 * gestelllt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und 
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
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
		 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
		 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
		 * einzige Instanz dieser Klasse.
		 * 
		 * @see lMapper()
		 */
		private static LikeMapper lMapper = null;
		
		/**
		 * Geschützer Konstruktor - verhindert die Möglichkeit, mit <ode>new</code>
		 * neue Instanzen dieser Klasse zu erzeugen.
		 */
		protected LikeMapper() {
		}
		
		/**
		 * Diese statisce Methode kann aufgerufen werden durch 
		 * <code>LikeMapper.tMapper()</code>. Sie stellt die
		 * Singleton-Eigenschaft sicher, indem sie dafür sorgt, dass nur eine einzige 
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
		 * Einfügen eines <code>Like</code>-Objekts in die Datenbank. Dabei wird
		 * auch der Primärschlüssel des Übergebenen Objekts geprüft und ggf.
		 * berichtig.
		 * 
		 * @param l das zu speichernde Objekt
		 * @return das bereits Übergebene Objekt, jedoch mit ggf. korrigierter
		 * <code>ID</code>.
		 */
		
		public Like erstellen (Like l){
			Connection con = DBConnection.connection();
			
			try {
				Statement stmt = con.createStatement();
				
				/*
				 * Zunächst schauen wir nach, welches der mmentan höchste 
				 * Primärschüsselwert ist.
				 */
				ResultSet rs = stmt.executeQuery("SELECT MAX(id AS maxid"
						+ "FROM like ");
				
				// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
				if (rs.next()) {
					/*
					 * l erhält den bisher maximalen, un um 1 inkremetierten
					 * Primärschlüssel.
					 */
					l.setID(rs.getInt("maxid")+1);
					
				stmt = con.createStatement();
				
				//Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO textbeitrag (ID, Text, Datum, Uhrzeit, NutzerID" + "VALUES ("
				+ l.getID() + "," + l.getDatum() + "," + l.getUhrzeit() + "," + l.getNutzerID()+ "," + l.getBeitragsID() +
				"')");
					
					}
				}
				catch (SQLException e2) {
					e2.printStackTrace();
				}
			
				/*
				 * Rückgabe, des vtl. korrigierten Leistungsbringung.
				 * 
				 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
				 * Objekte üergeben werden, wäre die Anpassung des Like-Objekts auch 
				 * ohne diese esplizite Rückgabe außerhalb dieser Methode sichtbar. Die
				 * explizite Rückgabe von t ist eher ein Stilmittel, um zu signalisieren, 
				 * dass sich das Objekt evtl. im Laufer der Methode verändert hat.
				 */
				return l;
		}
				
		
		/**
		 * Löschen der Daten eines <code>Like</code>-Objekts aus der Datenbank.
		 * 
		 * @param l auf der DB zu löschende "objekt"
		 */

		public void löschen(Like l){
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