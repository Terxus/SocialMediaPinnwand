package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.elite.itprojekt.shared.bo.*;


public class LikeMapper {
	
	private static LikeMapper likeMapper = null;

	/**
	 * Protected constructor to prevent to generate a new object of BeitragMapper
	 * (hidden from all)
	 */
	protected LikeMapper() {
	}

	/**
	 * Static method to generate exact one object of UserMapper
	 * 
	 * @return The static instance of BeitragMapper
	 */
	public static LikeMapper likeMapper() {
		if (likeMapper == null) {
			likeMapper = new LikeMapper();
		}
		return likeMapper;
	}
	
	 public ArrayList<Like> findeDurchId(int id){
		 
			Connection con = DBConnection.connection();
			ArrayList <Like> likeListe= new ArrayList<Like>();

			try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM Like WHERE Beitrag_ID=" +id);

				while (rs.next()) {

			        Like l = new Like();
			        l.setID(rs.getInt("Like_ID"));
			        l.setErstellZeitpunkt(rs.getTimestamp("Datum"));
			        l.setNutzer(NutzerMapper.nutzerMapper().sucheNutzerID(rs.getInt("Nutzer_ID")));
			        
			        //Mit Beitrag verknüpfen?!


			        likeListe.add(l);

			      }
				return likeListe;
			}

		    catch (SQLException e) {
		    		e.printStackTrace();
		    }
			return likeListe;
		 }
	 
	 //Like anlegen
	 	 
	 
		public Like anlegen(Like like, Beitrag beitrag){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			int maxid = 0;

			//Versuch der Abfrage
			try{
				Statement stmt = con.createStatement();

		     
		      ResultSet rs = stmt.executeQuery("SELECT MAX(Like_ID) AS maxid " 
		      + "FROM Like ");

		      
		      if (rs.next()) {
			      
		    	  	maxid=rs.getInt("maxid");
			        like.setID(rs.getInt("maxid") + 1);

			        stmt = con.createStatement();
			        
			        stmt.executeUpdate("INSERT INTO Like (Like_ID, Nutzer_ID, Beitrag_ID, Datum) "
			            + "VALUES (" + like.getID() + ",'" + like.getPinnwandId() + "','" + beitrag.getID() + "','" + ", CURRENT_TIMESTAMP ,  '" +"')");
					
		      	}
		    }

		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return findeEinzelneDurchID(maxid+1);
		}
	 
	 //Einzelnes Like finden
		
		public Like findeEinzelneDurchID(int id){

			Connection con = DBConnection.connection();


			try{
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("SELECT * FROM Like WHERE Like_ID=" + id );


				if (rs.next()) {
			
					Like l = new Like();
			        l.setID(rs.getInt("Like_ID"));
			        l.setErstellZeitpunkt(rs.getTimestamp("Datum"));
			        l.setNutzer(NutzerMapper.nutzerMapper().sucheNutzerID(rs.getInt("Nutzer_ID")));

			        return l;
				}
			}

		    catch (SQLException e) {
	    		e.printStackTrace();
	    		return null;
		    }

			return null;

		}

}