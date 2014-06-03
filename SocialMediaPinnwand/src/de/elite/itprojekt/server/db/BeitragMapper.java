package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.elite.itprojekt.shared.bo.*;


public class BeitragMapper {
	
	private static BeitragMapper beitragMapper = null;

	/**
	 * Protected constructor to prevent to generate a new object of BeitragMapper
	 * (hidden from all)
	 */
	protected BeitragMapper() {
	}

	/**
	 * Static method to generate exact one object of UserMapper
	 * 
	 * @return The static instance of BeitragMapper
	 */
	public static BeitragMapper beitragMapper() {
		if (beitragMapper == null) {
			beitragMapper = new BeitragMapper();
		}
		return beitragMapper;
	}
	
	 public ArrayList<Beitrag> getBeitragByPinnwand(int id){

		//Aufbau der DBVerbindung
		Connection con = DBConnection.connection();
		ArrayList <Beitrag> beitragListe= new ArrayList<Beitrag>();

		//Versuch der Abfrage
		try{
			Statement stmt = con.createStatement();
			//Suche alle Beitrag
			ResultSet rs = stmt.executeQuery("SELECT * FROM Beitrag WHERE Pinnwand_ID="+id);

			while (rs.next()) {
		        // Ergebnis in Beitrag- Objekt umwandeln
		        Beitrag b = new Beitrag();
		        b.setID(rs.getInt("Beitrag_ID"));
		        b.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        b.setText((rs.getString("Text")));
		       

		        //BeitragObjekte der ArrayList hinzufügen
		        beitragListe.add(b);
		      }
			return beitragListe;
		}

	    catch (SQLException e) {
	    		e.printStackTrace();
	    }
	//Falls keines gefunden leere Liste
	return beitragListe;
	}
	
	//Beitrag in DB einfügen
	 
	 public void textBeitragErstellen(Beitrag textBeitrag){
		 
		Connection con = DBConnection.connection();
	
		try{
			Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT MAX(Beitrag_ID) AS maxid " 
	      + "FROM Beitrag ");
	      if (rs.next()) {
	    	  
	    	  textBeitrag.setID(rs.getInt("maxid") + 1);
	    	  
	    	  stmt = con.createStatement();

		        stmt.executeUpdate("INSERT INTO Beitrag (Beitrag_ID, Nutzer_ID, Like_ID, Text, Datum) "
		            + "VALUES (" + textBeitrag.getID() + ",'" + textBeitrag.getNutzerId() + "','"  + "12" + "','" + textBeitrag.getText() + "','" + textBeitrag.getErstellZeitpunkt() +"')");

		        System.out.println(textBeitrag.getErstellZeitpunkt());
		        
	      }
	    }

	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	}
	 /*
	 //Beitrag per ID um Beitrag eines Nutzers anzuzeigen
	 public Beitrag getBeitragById(int id){

		//Aufbau der DBVerbindung
		Connection con = DBConnection.connection();

		//Versuch der Abfrage
		try{
			Statement stmt = con.createStatement();
			//Suche alle Felder der Beitragtabelle anhand von ID
			ResultSet rs = stmt.executeQuery("SELECT * FROM Beitrag WHERE Beitrag_ID=" + id );

			//Maximal ein Rï¿½ckgabewert da Id Primï¿½rschlï¿½ssel
			if (rs.next()) {
		        // Ergebnis in Beitrag- Objekt umwandeln
		        Beitrag b = new Beitrag();
		        b.setID(rs.getInt("beitrag_ID"));
		        b.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        b.setText(rs.getString("Text"));
		        b.setPinnwand(PinnwandMapper.pinnwandMapper().getPinnwandById(rs.getInt("Pinnwand_ID")));

		        //Aufruf des KommentarMappers um alle zum Beitrag gehï¿½rigen Kommentare als ArrayList zuzuweisen
		        b.setKommentarListe(KommentarMapper.kommentarMapper().getKommentarByBeitrag(rs.getInt("beitrag_ID")));

		        //Aufruf des LikeMappers um alle zum Beitrag gehï¿½rigen Likes als ArrayList zuzuweisen
		        b.setLikeList(LikeMapper.likeMapper().getLikeByBeitrag(rs.getInt("beitrag_ID")));			       

		        //BeitragObjekt zurï¿½ckgeben
		        return b;
			}
		}

	    catch (SQLException e) {
    		e.printStackTrace();
    		return null;
	    }
	//Falls keines gefunden leere Liste
	return null;
	}
	 
	*/
	
	
}