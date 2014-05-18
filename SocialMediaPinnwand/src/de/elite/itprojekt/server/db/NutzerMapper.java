package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.elite.itprojekt.shared.bo.Nutzer;


public class NutzerMapper {
	
	private static NutzerMapper nutzerMapper = null;

	/**
	 * Protected constructor to prevent to generate a new object of UserMapper
	 * (hidden from all)
	 */
	protected NutzerMapper() {
	}

	/**
	 * Static method to generate exact one object of UserMapper
	 * 
	 * @return The static instance of UserMapper
	 */
	public static NutzerMapper nutzerMapper() {
		if (nutzerMapper == null) {
			nutzerMapper = new NutzerMapper();
		}
		return nutzerMapper;
	}

	
	public static Nutzer loginCheckerNutzer(String nutzerName, String passWort) {
		Connection con = DBConnection.connection();
		Nutzer n = new Nutzer();
		try {

			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM Nutzer WHERE Nickname='"
							+ nutzerName + "' AND Passwort='" + passWort
							+ "'");

			while (rs.next()) {
				n.setID(rs.getInt("Nutzer_ID"));
				n.setNickname(rs.getString("Nickname"));
				n.setPassWort(rs.getString("Passwort"));
				n.setVorname(rs.getString("Vorname"));
				n.setNachname(rs.getString("Nachname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	
	
	
	
	
	
	
	
	
	

}
