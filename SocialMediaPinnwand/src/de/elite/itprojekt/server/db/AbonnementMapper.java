package de.elite.itprojekt.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Nutzer;


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

	//Einzelnes Abo anzeigen
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


	//Abonnements in einer Liste zurückgeben
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
	
	//Abo anlegen (Übernommen von Domi)
	
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
	
	 //REPORT
	//Abo's zählen
		
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
		 
		 //Abonnenten Zählen per Nutzer
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