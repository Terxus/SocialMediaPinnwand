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
		//Aufbau der DBVerbindung

		Connection con = DBConnection.connection();
		int maxid = 0;

		//Versuch der Abfrage
		try{
			Statement stmt = con.createStatement();


	      ResultSet rs = stmt.executeQuery("SELECT MAX(Beitrag_ID) AS maxid "
	          + "FROM Beitrag ");


	      if (rs.next()) {

	    	  	System.out.println(rs.getInt("maxid"));
	    	  	maxid=rs.getInt("maxid");
	    	  	textBeitrag.setID(rs.getInt("maxid") + 1);
		        System.out.print(rs.getInt("maxid") +1);
		        stmt = con.createStatement();
		        System.out.println("after con create");

		        System.out.println(textBeitrag.getID() + textBeitrag.getText());
		        System.out.println(textBeitrag.getNutzerId());
		        stmt.executeUpdate("INSERT INTO Beitrag (Beitrag_ID, Text, Nutzer_ID) "
		            + "VALUES (" + textBeitrag.getID() + ",'"  + textBeitrag.getText() + "','" + textBeitrag.getNutzerId() +"')");
		        System.out.println("after exe");
	      }
	    }

	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	   // return getBeitragById(maxid+1);
	}
	
	
	
}