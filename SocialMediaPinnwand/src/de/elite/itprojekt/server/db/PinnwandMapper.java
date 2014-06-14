package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.elite.itprojekt.shared.bo.*;

/**
 * Diese Klasse bildet die Pinnwandobjekte auf eine relationale Datenbank ab
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 *
 */
public class PinnwandMapper {
	
	private static PinnwandMapper pinnwandMapper = null;

	/**
	 * Protected constructor to prevent to generate a new object of BeitragMapper
	 * (hidden from all)
	 */
	protected PinnwandMapper() {
	}

	/**
	 * Static method to generate exact one object of UserMapper
	 * 
	 * @return The static instance of BeitragMapper
	 */
	public static PinnwandMapper pinnwandMapper() {
		if (pinnwandMapper == null) {
			pinnwandMapper = new PinnwandMapper();
		}
		return pinnwandMapper;
	}
	
	
	
	/**
	 * Diese Methode gibt eine einzelne Pinnwand anhand der ID zurück
	 * @param id Eindeutiger Identifikator einer Pinnwand in der Datenbank
	 * @return Pinnwand
	 */
	public Pinnwand suchePinnwandID(int id){


		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Pinnwand WHERE Pinnwand_ID=" + id );

			if (rs.next()) {
				
				Pinnwand pn = new Pinnwand();
				pn.setAbonnementId(rs.getInt("Abonnement_ID"));
				pn.setID(rs.getInt("Pinnwand_ID"));
				pn.setErstellZeitpunkt(rs.getTimestamp("Datum"));
				pn.setNutzerId(rs.getInt("Nutzer_ID"));
				
				pn.setNutzer(NutzerMapper.nutzerMapper().sucheNutzerID(rs.getInt("Nutzer_ID")));

		        return pn;
		      }
		}

	    catch (SQLException e) {
    		e.printStackTrace();
    		return null;
	    }
	return null;
	}
	/**
	 * Diese Methode löscht eine Pinnwand in der Datenbank
	 * @param n Eindeutiger Identifikator eines Nutzers in der Datenbank
	 */
	public void loeschen(Nutzer n) {
			Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();
		      stmt.executeUpdate("DELETE FROM Pinnwand WHERE Nutzer_ID=" + n.getID());
		      
		      PinnwandMapper.pinnwandMapper().loeschen(n);
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    } 
		}
	

	
}