package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.elite.itprojekt.shared.bo.*;


/**
 * Diese Klasse bildet die Kommentarobjekte auf eine relationale Datenbank ab
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 *
 */
public class KommentarMapper {

	private static KommentarMapper kommentarMapper = null;

	/**
	 * Protected constructor to prevent to generate a new object of BeitragMapper
	 * (hidden from all)
	 */
	protected KommentarMapper() {
	}

	/**
	 * Static method to generate exact one object of UserMapper
	 * 
	 * @return The static instance of BeitragMapper
	 */
	public static KommentarMapper kommentarMapper() {
		if (kommentarMapper == null) {
			kommentarMapper = new KommentarMapper();
		}
		return kommentarMapper;
	}

	/**
	 * Durch den Aufruf dieser Methode wird ein einzelner Kommentar anhand der ID ausgegeben
	 * @param id Eindeutiger Identifikator eines Kommentares in der Datenbank
	 * @return Kommentar 
	 */
	 public Kommentar findeEinzelneDurchId(int id){
			Connection con = DBConnection.connection();

			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Kommentar WHERE Kommentar_ID=" + id );


				if (rs.next()) {
			        Kommentar k = new Kommentar();
			        k.setID(rs.getInt("Kommentar_ID"));
			        k.setErstellZeitpunkt(rs.getTimestamp("Datum"));
			        k.setText(rs.getString("Text"));
			        k.setNutzer(NutzerMapper.nutzerMapper().sucheNutzerID(rs.getInt("Nutzer_ID")));

			        return k;
				}
			}

		    catch (SQLException e) {
	    		e.printStackTrace();
	    		return null;
		    }
			return null;

		 }
	 /**
		 * Diese Methode gibt alle Kommentare eines Beitrags in einer Liste zurück
		 * @param id Eindeutiger Identifikator des Beitrags in der Datenbank
		 * @return Liste der Kommentare
		 */
	 public ArrayList<Kommentar> findeDurchId(int id){
		Connection con = DBConnection.connection();
		ArrayList <Kommentar> kommentarListe= new ArrayList<Kommentar>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM Kommentar " + "WHERE Beitrag_ID =" + id + " ORDER BY Datum DESC");

			while (rs.next()) {
				Kommentar k = new Kommentar();
		        k.setID(rs.getInt("Kommentar_ID"));
		        k.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        k.setText(rs.getString("Text"));
		        k.setNutzer(NutzerMapper.nutzerMapper().sucheNutzerID(rs.getInt("Nutzer_ID")));
		        k.setBeitrag(BeitragMapper.beitragMapper().sucheBeitragID(rs.getInt("Beitrag_ID")));



		        kommentarListe.add(k);
		      }
			return kommentarListe;
		}

	    catch (SQLException e) {
	    		e.printStackTrace();
	    }
		return kommentarListe;
	 }


	 /**
		 * Diese Methode speichert einen neu angelegten Kommentar in der Datenbank
		 * @param kommentar angelegter neuer Kommentar (neues Kommentar Objekt)
		 * @return Den eben abgespeicherten Kommentar 
		 */
	 public void kommentarErstellen(Kommentar kommentar){

		Connection con = DBConnection.connection();

		try{
			Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT MAX(Kommentar_ID) AS maxid " 
	      + "FROM Kommentar ");
	      if (rs.next()) {

	    	  kommentar.setID(rs.getInt("maxid") + 1);

	    	  stmt = con.createStatement();

		        stmt.executeUpdate("INSERT INTO Kommentar (Kommentar_ID, Nutzer_ID, Beitrag_ID, Text, Datum) "
			            + "VALUES (" + kommentar.getID() + ",'" + kommentar.getNutzer().getID() + "','"  + kommentar.getBeitrag().getID() + "','" + kommentar.getText() + "','" + kommentar.getErstellZeitpunkt() +"')");

	      }
	    }

	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	}


	 /**
		 * Durch diese Methode wird ein Kommentar aus der Datenbank gelöscht
		 * @param kommentar Der Kommentar, der gelöscht werden soll
		 */
	 	public void kommentarLoeschen(Kommentar kommentar) {

	 		Connection con = DBConnection.connection();
	 			Statement stmt = null;

	 			try {
	 				stmt = con.createStatement();

	 				stmt.executeUpdate("DELETE FROM Kommentar "
	 						+ "WHERE Kommentar_ID=" + kommentar.getID());

	 			} catch (SQLException e2) {
	 				e2.printStackTrace();
	 			}

	 			return;
	 		}


	 	/**
	 	 * Diese Methode aktualisiert einen Kommentardatensatz in der Datenbank
	 	 * @param kommentar neue Version des Kommentars
	 	 * @return den eben aktualisierten Kommentar
	 	 */
	public Kommentar kommentarBearbeiten(Kommentar kommentar){

			Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE Kommentar SET Text=\"" + kommentar.getText() + "\" WHERE Kommentar_ID= " + kommentar.getID());


		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return findeEinzelneDurchId(kommentar.getID());
		}



	/**
	 * Wenn ein Beitrag gelöscht wird, löscht diese Methode alle zugehörigen Kommentare
	 * @param id Eindeutiger Identifikator des Beitrags in der Datenbank
	 */
 	public void autoKommentarLoeschen(int id) {
 		
 		Connection con = DBConnection.connection();
 			Statement stmt = null;

 			try {
 				stmt = con.createStatement();

 				stmt.executeUpdate("DELETE FROM Kommentar "
 						+ "WHERE Beitrag_ID=" + id);

 			} catch (SQLException e2) {
 				e2.printStackTrace();
 			}
 			
 			return;
 		}
 	/**
 	 * Diese Methode zählt die Anzahl der Kommentare, die einem Beitrag zugehören
 	 * @param beitrag Beitrag, dessen Kommentare gezählt werden sollen
 	 * @return Anzahl der Kommentare des Beitrags 
 	 */
	public int zaehleAlleKommentareProBeitrag(Beitrag beitrag) {

		int id = beitrag.getID();

		int count = -1;

		Connection con = DBConnection.connection();


		try{
			Statement stmt = con.createStatement();
			//Suche alle Likes zu einem Beitrag
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `Kommentar` WHERE Beitrag_ID=" + id);


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
 	 * Diese Methode gibt die Anzahl aller Kommentare zurück
 	 * @return Anzahl der Kommentare 
 	 */
	 public int zaehleKommentare(){
		 int count = -1;
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Kommentar");

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
	  * Diese Methode gibt die Anzahl der Kommentare zurück, die ein Nutzer verfasst hat
	  * @param nutzer Nutzer, dessen Kommentare gez�hlt werden sollen
	  * @return Anzahl der Kommentare
	  */
	 public int zaehleKommentarePerNutzer(Nutzer nutzer){
		 int count = -1;
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `Kommentar` WHERE Nutzer_ID=" + nutzer.getID());

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