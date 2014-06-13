package de.elite.itprojekt.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Nutzer;

/**
 * Diese Klasse bildet die Abonnementobjekte auf eine relationale Datenbank ab
 * @author Maik
 *
 */
public class AbonnementMapper {

	private static AbonnementMapper abonnementMapper = null;

	/**
	 * Protected constructor to prevent to generate a new object of BeitragMapper
	 * (hidden from all)
	 */
	protected AbonnementMapper() {
	}

	/**
	 * Static method to generate exact one object of UserMapper
	 * 
	 * @return The static instance of BeitragMapper
	 */
	public static AbonnementMapper abonnementMapper() {
		if (abonnementMapper == null) {
			abonnementMapper = new AbonnementMapper();
		}
		return abonnementMapper;
	}

	/**
	 * Durch den Aufruf dieser Methode wird ein einzelnes Abonnement anhand der ID ausgegeben
	 * @param id Eindeutiger Identifikator eines Abonnements in der Datenbank
	 * @return Abonnement Objekt
	 */
	public Abonnement getAboById(int id){

		Connection con = DBConnection.connection();


		try{
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Abonnement WHERE Abonnement_ID=" + id );


			if (rs.next()) {
		
				Abonnement a = new Abonnement();
		        a.setID(rs.getInt("Abonnement_ID"));
		        a.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        a.setNutzer(NutzerMapper.nutzerMapper().sucheNutzerID(rs.getInt("Nutzer_ID")));
		        a.setPinnwand(PinnwandMapper.pinnwandMapper().suchePinnwandID(rs.getInt("Pinnwand_ID")));

		        return a;
			}
		}

	    catch (SQLException e) {
    		e.printStackTrace();
    		return null;
	    }

		return null;

	}


	
	/**
	 * Diese Methode gibt alle Abonnements eines Nutzern in einer Liste zurück
	 * @param id Eindeutiger Identifikator des Nutzers in der Datenbank
	 * @return Liste der Abonnements
	 */
	public ArrayList<Abonnement> getAboByNutzer(int id){

		Connection con = DBConnection.connection();
		ArrayList <Abonnement> aboListe= new ArrayList<Abonnement>();

		//Versuch der Abfrage
		try{
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Abonnement WHERE Nutzer_ID="+id);

			while (rs.next()) {
	
				Abonnement a = new Abonnement();
		        a.setID(rs.getInt("Abonnement_ID"));
		        a.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        a.setNutzer(NutzerMapper.nutzerMapper().sucheNutzerID(rs.getInt("Nutzer_ID")));
		        a.setPinnwand(PinnwandMapper.pinnwandMapper().suchePinnwandID(rs.getInt("Pinnwand_ID")));
	
		        aboListe.add(a);

		      }
			return aboListe;
		}

	    catch (SQLException e) {
	    		e.printStackTrace();
	    }
		
		return aboListe;

	}
	
	/**
	 * Diese Methode speichert ein neu angelegtes Abonnement in der Datenbank
	 * @param a angelegtes Abonnement (neues Abo Objekt)
	 * @return Das eben abgespeicherte Abo-Objekt
	 */
	public Abonnement abonnementAnlegen(Abonnement a){
		//Aufbau der DBVerbindung
		Connection con = DBConnection.connection();
		int maxid = 0;

		//Versuch der Abfrage
		try{
			Statement stmt = con.createStatement();

	     
	      ResultSet rs = stmt.executeQuery("SELECT MAX(Abonnement_ID) AS maxid "
	          + "FROM Abonnement ");

	      
	      if (rs.next()) {
		      
	    	  	maxid=rs.getInt("maxid");
		        a.setID(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        stmt.executeUpdate("INSERT INTO Abonnement (Abonnement_ID, Datum, Nutzer_ID, Pinnwand_ID) "
			            + "VALUES (" + a.getID() + ", CURRENT_TIMESTAMP ,  '" + a.getNutzerId() + "'  ,'"
			            + a.getPinnwandId() +"')");

	      	}
	    }

	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return getAboById(maxid+1);
	}

	
	/**
	 * Durch diese Methode wird ein Abonnement aus der Datenbank gelöscht
	 * @param a Das Abonnement, das gelöscht werden soll
	 */
	public void abonnementLoeschen(Abonnement a){

		Connection con = DBConnection.connection();


	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM Abonnement WHERE Abonnement_ID=" + a.getID());
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    } 
	}
	

		/**
		 * Diese Methode gibt die Anzahl aller Abonnements zurück
		 * @return Anzahl Abonnements (numerischer Wert)
		 */
		 public int zaehleAbonnements(){
			 int count = -1;
			Connection con = DBConnection.connection();

			try {
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `Abonnement`");

				while (rs.next()) {
			        count=rs.getInt(1);
			      }

			}

		    catch (SQLException e) {
		    		e.printStackTrace();
		    }
			return count;

		 }
		 
		
		 /**
		  * Diese Methode gibt die Anzahl der Abonnements eines Nutzers zurück
		  * @param nutzer Nutzer, dessen Abonnements gezählt werden sollen
		  * @return Anzahl Abonnements (numerischer Wert)
		  */
		 public int zaehleAbonnementsPerNutzer(Nutzer nutzer){
			 int count = -1;
			Connection con = DBConnection.connection();

			try {
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `Abonnement` WHERE Nutzer_ID=" +nutzer.getID());

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