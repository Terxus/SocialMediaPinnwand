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
}