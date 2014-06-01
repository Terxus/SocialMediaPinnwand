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
		        b.setErstellZeitpunkt(rs.getDate("Datum"));
		        b.setText((rs.getString("Text")));

		        //Aufruf des KommentarMappers um alle zum Beitrag gehörigen Kommentare als ArrayList zuzuweisen
		        //b.setKommentarListe(KommentarMapper.kommentarMapper().getKommentarByBeitrag(rs.getInt("beitrag_ID")));

		        //Aufruf des LikeMappers um alle zum Beitrag gehörigen Likes als ArrayList zuzuweisen
		        //b.setLikeListe(LikeMapper.likeMapper().getLikesByBeitrag(rs.getInt("beitrag_ID")));			       

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
	
	
	
	
	
}