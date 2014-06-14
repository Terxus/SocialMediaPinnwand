package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;

/**
 * Diese Klasse bildet die Nutzerobjekte auf eine relationale Datenbank ab
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 *
 */
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

	/**
	 * Diese Methode meldet den Nutzer an
	 * @param nutzerName Name des Nutzers, der sich anmelden möchte
	 * @param passWort eingegebenes Passwort
	 * @return Nutzer
	 */
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
	
	/**
	 * Diese Methode gibt alle registrierten Nutzer in einer Liste aus
	 * @return Liste aller Nutzer
	 */
	public ArrayList<Nutzer> sucheAlleNutzer(){

		Connection con = DBConnection.connection();
		ArrayList <Nutzer> alleNutzer = new ArrayList<Nutzer>();

		try {
			Statement stmt = con.createStatement();
			//Suche alle Nutzer
			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzer");

			while (rs.next()) {
				
		        Nutzer n = new Nutzer();
		        n.setID(rs.getInt("Nutzer_ID"));
		        n.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        n.setVorname(rs.getString("Vorname"));
		        n.setNachname(rs.getString("Nachname"));
		        n.setPassWort(rs.getString("Passwort"));
		        n.seteMail(rs.getString("Email"));
		        n.setNickname(rs.getString("Nickname"));

		        
		        //Pinnwand und Abonnement?! :D

		        //NutzerObjekte der ArrayList hinzufÃ¼gen
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
	
	/**
	 * Diese Methode gibt einen Nutzer anhand der ID zurück
	 * @param id Eindeutiger Identifikator eines Nutzers
	 * @return Nutzer
	 */
	public Nutzer sucheNutzerID(int id){


		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzer WHERE Nutzer_ID=" + id );

			if (rs.next()) {
				
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
	return null;
	}
	
	//Die ID des Nutzer zurÃ¼ckgeben
	/**
	 * Diese Methode gibt die ID eines Nutzers zurück
	 * @param id Eindeutiger Identifikator eines Nutzers in der Datenbank
	 * @return Nutzer ID
	 */
	public int sucheNutzerReturnID(int id){


		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Nutzer WHERE Nutzer_ID=" + id );

			if (rs.next()) {
				
		        Nutzer n = new Nutzer();
		        n.setID(rs.getInt("Nutzer_ID"));
		        n.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        n.setVorname(rs.getString("Vorname"));
		        n.setNachname(rs.getString("Nachname"));
		        n.setPassWort(rs.getString("Passwort"));
		        n.seteMail(rs.getString("Email"));
		        n.setNickname(rs.getString("Nickname"));

		        return n.getID();
		      }
		}

	    catch (SQLException e) {
    		e.printStackTrace();
    		return 0;
	    }
	return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Diese Methode aktualisiert einen Nutzerdatensatz in der Datenbank
	 * @param n Nutzer, dessen Datensatz aktualisiert werden soll
	 * @return Nutzer ID
	 */
	public Nutzer updateNutzer(Nutzer n){

		Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      
	      stmt.executeUpdate("UPDATE Nutzer " + "SET Vorname= \"" + n.getVorname() + "\", " + "Nachname=\"" + n.getNachname() + "\", " + "Email= \"" + n.geteMail() + "\",     " + "Passwort= \"" + n.getPassWort() + "\",                                  " + "Nickname= \""  + n.getNickname() +  "\"" + "WHERE Nutzer_ID=" + n.getID());

	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return sucheNutzerID(n.getID());
	}
	
	
	/**
	 * Diese Methode löscht einen Nutzer in der Datenbank
	 * @param n Nutzer, der gelöscht werden soll
	 */
	public void nutzerLoeschen(Nutzer n){
		Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      stmt.executeUpdate("DELETE FROM Nutzer WHERE Nutzer_ID=" + n.getID());
	      
	    //  PinnwandMapper.pinnwandMapper().loeschen(n);
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    } 
	}
	
	
	/**
	 * Diese Methode speichert einen neuen Nutzer in der Datenbank und ordnet ihm eine Pinnwand zu
	 * @param n Neuer Nutzer
	 * @return Nutzer ID
	 */
	 public Nutzer nutzerAnlegen(Nutzer n) {
		 System.out.println("user mÃ¼sste jetzt angelegt werden");
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
						+ n.getErstellZeitpunkt() + "', '" + n.getID() + "', '" + n.getID() + "')");
		      
		      
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return sucheNutzerID(n.getID());
	}
	 
	 /**
	  * Diese Methode gibt den Nutzer anhand seines Nicknames aus
	  * @param nickname Nickname eines Nutzers
	  * @return Nutzer
	  */
	 public Nutzer getNutzerAnhandNickname(String nickname) {
		    Connection con = DBConnection.connection();

		    try {

		      Statement stmt = con.createStatement();


		      ResultSet rs = stmt

		          .executeQuery("SELECT * FROM `Nutzer` WHERE `Nickname` = '"+nickname+"'");


		      if (rs.next()) {

		        Nutzer n = new Nutzer();
		        n.setID(rs.getInt("Nutzer_ID"));
		        n.setVorname(rs.getString("Vorname"));
		        n.setNachname(rs.getString("Nachname"));
		        n.setNickname(rs.getString("Nickname"));
		        n.seteMail(rs.getString("Email"));

		        return n;
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		    }

		    return null;
		  }


	 /**
	  * Diese Methode gibt einen Nutzer anhand des nicknames zurück
	  * @param nickname übergebener nickname
	  * @return Nutzer
	  */
	 public Nutzer sucheNickname(String nickname) {
		    Connection con = DBConnection.connection();

		    try {

		      Statement stmt = con.createStatement();


		      ResultSet rs = stmt

		          .executeQuery("SELECT * FROM `Nutzer` WHERE `Nickname` = '"+nickname+"'");


		      if (rs.next()) {

		        Nutzer n = new Nutzer();
		        n.setID(rs.getInt("Nutzer_ID"));
		        n.setVorname(rs.getString("Vorname"));
		        n.setNachname(rs.getString("Nachname"));
		        n.setNickname(rs.getString("Nickname"));
		        n.seteMail(rs.getString("Email"));

		        return n;
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		    }

	return null;
	}
	 	/**
	 	 * Diese Methode überprüft, ob ein nickname bereits in der Datenbank gespeichert ist 
	 	 * @param nickName übergebener nickname
	 	 * @return true/false, je nachdem ob der nickname schon vergeben wurde oder nicht
	 	 */
		public boolean sucheNickName(String nickName) {
			Connection con = DBConnection.connection();
			try {
				Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery("SELECT * FROM `Nutzer` WHERE `Nickname` = '"+nickName+"'");
				 if(rs.next() == true) {
					 return true;

				 } else return false;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		}
	 
	 
	
		/**
		 * Diese Methode gibt die Anzahl aller registrierten Nutzer zurück
		 * @return Anzahl aller Nutzer
		 */
		 public int zaehleNutzer(){
			 int count = -1;
			Connection con = DBConnection.connection();

			try {
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM nutzer");

				while (rs.next()) {
			        count=rs.getInt(1);
			      }

			}

		    catch (SQLException e) {
		    		e.printStackTrace();
		    }
			return count;

		 }
		
	 
	 
	 
	 
	 
	 

}
