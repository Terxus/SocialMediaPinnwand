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

			ResultSet rs = stmt.executeQuery("SELECT * FROM Kommentar WHERE Beitrag_ID="+id);

			while (rs.next()) {
				Kommentar k = new Kommentar();
		        k.setID(rs.getInt("Kommentar_ID"));
		        k.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        k.setText(rs.getString("Text"));
		        k.setNutzer(NutzerMapper.nutzerMapper().sucheNutzerID(rs.getInt("Nutzer_ID")));
		        
		        //Mit Beitrag verkn�pfen?! :D

		        kommentarListe.add(k);
		      }
			return kommentarListe;
		}

	    catch (SQLException e) {
	    		e.printStackTrace();
	    }
		return kommentarListe;
	 }
}