package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.elite.itprojekt.shared.bo.*;


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
	 //Kommentar anlegen
	 
	 
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

		        System.out.println(kommentar.getErstellZeitpunkt());
		        
	      }
	    }

	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	}
	 
	//Kommentar löschen
	 
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
	 
	 	//Kommentar bearbeiten
	 	
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
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}