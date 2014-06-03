package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.elite.itprojekt.shared.bo.Nutzer;


public class NutzerMapper {
	
	private static NutzerMapper nutzerMapper = null;

	/**
	 * Protected constructor to prevent to generate a new object of UserMapper
	 * (hidden from all)
	 */
	protected NutzerMapper() {
	}

	/**
	 * Static method to generate exact one object of UserMapper
	 * 
	 * @return The static instance of UserMapper
	 */
	public static NutzerMapper nutzerMapper() {
		if (nutzerMapper == null) {
			nutzerMapper = new NutzerMapper();
		}
		return nutzerMapper;
	}

	
	public Nutzer loginCheckerNutzer(String nutzerName, String passWort) {
		Connection con = DBConnection.connection();
		Nutzer n = new Nutzer();
		try {

			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM Nutzer WHERE Nickname='"
							+ nutzerName + "' AND Passwort='" + passWort
							+ "'");

			while (rs.next()) {
				n.setID(rs.getInt("Nutzer_ID"));
				n.setNickname(rs.getString("Nickname"));
				n.setPassWort(rs.getString("Passwort"));
				n.setVorname(rs.getString("Vorname"));
				n.setNachname(rs.getString("Nachname"));
				n.seteMail(rs.getString("Email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	//Alle Nutzer holen
	
	public ArrayList<Nutzer> sucheAlleNutzer(){

		//Aufbau der DBVerbindung
		Connection con = DBConnection.connection();
		ArrayList <Nutzer> alleNutzer = new ArrayList<Nutzer>();

		//Versuch der Abfrage
		try{
			Statement stmt = con.createStatement();
			//Suche alle Nutzer
			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzer");

			while (rs.next()) {
				// Ergebnis in Nutzer- Objekt umwandeln
		        Nutzer n = new Nutzer();
		        n.setID(rs.getInt("Nutzer_ID"));
		        n.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        n.setVorname(rs.getString("Vorname"));
		        n.setNachname(rs.getString("Nachname"));
		        n.setPassWort(rs.getString("Passwort"));
		        n.seteMail(rs.getString("Email"));
		        n.setNickname(rs.getString("Nickname"));
		        /*
		        //Verweis auf PinnwandMapper um zugehörige Pinnwand herauszufinden
		        n.setPinnwand(PinnwandMapper.pinnwandMapper().getPinnwandByNutzer(rs.getInt("nutzer_ID")));
		        //Verweis auf AbonnementMapper um zugehörige Abos herauszufinden
		        n.setAbonnementListe(AboMapper.aboMapper().getAboByNutzer(rs.getInt("nutzer_ID")));	
		        */

		        //NutzerObjekte der ArrayList hinzufügen
		        alleNutzer.add(n);
		      }
			return alleNutzer;
		}

	    catch (SQLException e) {
	    		e.printStackTrace();
	    }
	//Falls keines gefunden leeres Objekt
	return alleNutzer;
	}
	
	//Nutzer per ID holen
	
	
	public Nutzer sucheNutzerID(int id){

		//Aufbau der DBVerbindung
		Connection con = DBConnection.connection();

		//Versuch der Abfrage
		try {
			Statement stmt = con.createStatement();
			//Suche alle Felder der Nutzertabelle anhand von ID
			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzer WHERE Nutzer_ID=" + id );

		    //Maximal ein Rückgabewert da Id Primärschlüssel
			if (rs.next()) {
		        // Ergebnis in Nutzer- Objekt umwandeln
		        Nutzer n = new Nutzer();
		        n.setID(rs.getInt("Nutzer_ID"));
		        n.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        n.setVorname(rs.getString("Vorname"));
		        n.setNachname(rs.getString("Nachname"));
		        n.setPassWort(rs.getString("Passwort"));
		        n.seteMail(rs.getString("Email"));
		        n.setNickname(rs.getString("Nickname"));

		        return n;
		      }
		}

	    catch (SQLException e) {
    		e.printStackTrace();
    		return null;
	    }
	//Falls keines gefunden leere Liste
	return null;
	}
	
	
	//Nutzer updaten
	
	public Nutzer updateNutzer(Nutzer n){
		//Aufbau der DBVerbindung
		Connection con = DBConnection.connection();

		//Versuch der Abfrage
	    try {
	      Statement stmt = con.createStatement();
	      
	      stmt.executeUpdate("UPDATE Nutzer " + "SET Vorname= \"" + n.getVorname() + "\", " + "Nachname=\"" + n.getNachname() + "\", " + "Email= \"" + n.geteMail() + "\",     " + "Passwort= \"" + n.getPassWort() + "\",                                  " + "Nickname= \""  + n.getNickname() +  "\"" + "WHERE Nutzer_ID=" + n.getID());

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    // Zurückgeben des aktuellen Nutzerobjektes
	    return sucheNutzerID(n.getID());
	}
	
	//Nutzer löschen
	
	public void nutzerLoeschen(Nutzer n){
		Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      stmt.executeUpdate("DELETE FROM Nutzer WHERE Nutzer_ID=" + n.getID());
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    } 
	}
	
	//Nutzer registrieren
	
	 public Nutzer nutzerAnlegen(Nutzer n) {
		 System.out.println("user müsste jetzt angelegt werden");
		    Connection con = DBConnection.connection();
		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT MAX(Nutzer_ID) AS maxid "
		          + "FROM Nutzer ");
		      if (rs.next()) {
		        
		        n.setID(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		      stmt.executeUpdate ("INSERT INTO Nutzer " + "VALUES (" + n.getID() + ", '"
						+ n.getVorname() + "', '" + n.getNachname() + "', '" + n.getNickname() + "','" + n.getPassWort()
						+ "','" + n.geteMail() + "','" + n.getErstellZeitpunkt()  + "')");
		           }
		      
		      //Dem registrierten Nutzer eine Pinnwand zuordnen

		      stmt.executeUpdate ("INSERT INTO Pinnwand " + "VALUES (" + n.getID() + ", '"
						+ n.getErstellZeitpunkt() + "', '" + n.getID() + "', '" + "12" + "')");
		      
		      
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return sucheNutzerID(n.getID());
		    }
}
	
	
	
	
	
	
	


