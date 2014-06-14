package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.elite.itprojekt.shared.bo.*;

/**
 * Diese Klasse bildet die Beitragsobjekte auf eine relationale Datenbank ab
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 *
 */
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

	/**
	 * Diese Methode gibt alle Beitrage, die zu einer Pinnwand gehören, anhand der Nutzer ID in einer Liste aus
	 * @param id Eindeutiger Identifikator des Nutzers in der Datenbank
	 * @return Liste der Beiträge
	 */
	 public ArrayList<Beitrag> getBeitragByPinnwand(int id) {

		Connection con = DBConnection.connection();
		ArrayList <Beitrag> beitragListe= new ArrayList<Beitrag>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Beitrag WHERE Nutzer_ID="+id + " ORDER BY Datum DESC");

			while (rs.next()) {
		        Beitrag b = new Beitrag();
		        b.setID(rs.getInt("Beitrag_ID"));
		        b.setErstellZeitpunkt(rs.getTimestamp("Datum"));
		        b.setText((rs.getString("Text")));

		        beitragListe.add(b);
		      }
			return beitragListe;
		}

	    catch (SQLException e) {
	    		e.printStackTrace();
	    }
	return beitragListe;
	}


	 /**
	  * Diese Methode speichert einen neuen Textbeitrag in der Datenbank
	  * @param textBeitrag Neuer Beitrag, der abgespeichert werden soll (Beitragsobjekt)
	  */
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
		        		+ "VALUES (" + textBeitrag.getID() + ",'" + textBeitrag.getNutzerId() + "','"  + textBeitrag.getID() + "','" + textBeitrag.getText() + "','" + textBeitrag.getErstellZeitpunkt() +"')");
  
	      }
	    }

	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	}

	 /**
	  * Diese Methode gibt alle Beiträge eines Nutzers anhand der ID in einer Liste aus
	  * @param id Eindeutiger Identifikator des Nutzers in der Datenbank
	  * @return Liste der Beiträge
	  */
	 public ArrayList<Beitrag> findeAlleUserBeitraege(int id) {
		 Connection con = DBConnection.connection();
			Statement stmt = null;
			ResultSet rs = null;

			ArrayList<Beitrag> result = new ArrayList<Beitrag>();

			try {
				stmt = con.createStatement();

				rs = stmt.executeQuery("SELECT * FROM Beitrag " + "WHERE Nutzer_ID =" + id + " ORDER BY Datum DESC");

				while (rs.next()) {
					Beitrag beitrag = new Beitrag();
					beitrag.setID(rs.getInt("Beitrag_ID"));
					beitrag.setText(rs.getString("Text"));
					beitrag.setErstellZeitpunkt(rs.getTimestamp("Datum"));

					result.add(beitrag);
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			return result;
		}
	 	/**
	 	 * Diese Methode löscht einen Beitrag aus der Datenbank
	 	 * @param beitrag Der Beitrag, der gelöscht werden soll
	 	 */
	 	public void textBeitragLoeschen(Beitrag beitrag) {

	 		Connection con = DBConnection.connection();
	 			Statement stmt = null;

	 			try {
	 				stmt = con.createStatement();

	 				stmt.executeUpdate("DELETE FROM Beitrag "
	 						+ "WHERE Beitrag_ID=" + beitrag.getID());

	 				KommentarMapper.kommentarMapper().autoKommentarLoeschen(beitrag.getID());
	 				LikeMapper.likeMapper().likesVonBeitragLoeschen(beitrag.getID());


	 			} catch (SQLException e2) {
	 				e2.printStackTrace();
	 			}

	 			return;
	 		}

	 	/**
	 	 * Diese Methode aktualisiert einen Beitragsdatensatz in der Datenbank
	 	 * @param beitrag Neue Version des Beitrags
	 	 * @return den eben aktualisierten Beitrag
	 	 */
		public Beitrag updateBeitrag(Beitrag beitrag){

			Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE Beitrag SET Text=\"" + beitrag.getText() + "\" WHERE Beitrag_ID= " + beitrag.getID());


		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return sucheBeitragID(beitrag.getID());
		}



		/**
		 * Diese Methode gibt einen einzelnen Beitrag anhand der ID aus.
		 * @param id Eindeutiger Identifikator des Beitrags in der Datenbank
		 * @return Beitrag
		 */
		public Beitrag sucheBeitragID(int id){

			Connection con = DBConnection.connection();

			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Beitrag WHERE Beitrag_ID=" + id );

				if (rs.next()) {
			        Beitrag b = new Beitrag();
			        b.setID(rs.getInt("Beitrag_ID"));
			        b.setErstellZeitpunkt(rs.getTimestamp("Datum"));
			        b.setText(rs.getString("Text"));

			        return b;
				}
			}

		    catch (SQLException e) {
	    		e.printStackTrace();
	    		return null;
		    }
		return null;
		}

		/**
		 * Diese Methode gibt alle Beitrage, die zu einer Pinnwand gehören, anhand der Nutzer ID in einer Liste aus
		 * @param id Eindeutiger Identifikator des Nutzers in der Datenbank
		 * @return Liste der Beiträge
		 */
		public ArrayList<Beitrag> sucheBeitragPerPinnwand(int id){


			Connection con = DBConnection.connection();
			ArrayList <Beitrag> beitragListe= new ArrayList<Beitrag>();


			try{
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("SELECT * FROM Beitrag WHERE Nutzer_ID="+id + " ORDER BY Datum DESC");

				while (rs.next()) {

			        Beitrag b = new Beitrag();
			        b.setID(rs.getInt("Beitrag_ID"));
			        b.setErstellZeitpunkt(rs.getTimestamp("Datum"));
			        b.setText((rs.getString("Text")));
			        b.setLikeId((rs.getInt("Like_ID")));
			        b.setPinnwand(PinnwandMapper.pinnwandMapper().suchePinnwandID(rs.getInt("Nutzer_ID")));


			        beitragListe.add(b);
			      }
				return beitragListe;
			}

		    catch (SQLException e) {
		    		e.printStackTrace();
		    }
		return beitragListe;
		}

			/**
			 * Diese Methode gibt die gesamte Anzahl der in der Datenbank gespeicherten Beiträge zur�ck
			 * @return Anzahl der Beiträge 
			 */
			 public int zaehleBeitraege(){
				 int count = -1;
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `Beitrag`");

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
			  * Diese Methode gibt die Anzahl der verfassten Beitr�ge eines Nutzers aus
			  * @param nutzer Nutzer, dessen Beitragsanzahl ermittelt werden soll
			  * @return Anzahl der Beiträge  
			  */
			 public int zaehleBeitraegePerNutzer(Nutzer nutzer){
				 int count = -1;
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `Beitrag` WHERE Nutzer_ID=" + nutzer.getID());

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
			  * Diese Methode gibt alle Beiträge eines Nutzers aus, sortiert nach Likes und Datum
			  * @param nutzer Nutzer, dessen Beiträge ausgegeben werden sollen
			  * @param von frühester Erstellungszeitpunkt
			  * @param bis spätester Erstellungszeitpunkt
			  * @param sortierung
			  * @return Sortierte Liste der Beiträge
			  */
			 public ArrayList<Beitrag> alleBeitraegeEinesNutzersNachLikes(Nutzer nutzer, String von, String bis, int sortierung) {
					//Aufbau der DBVerbindung
					Connection con = DBConnection.connection();
					ArrayList <Beitrag> beitraege= new ArrayList<Beitrag>();
					//Versuch der Abfrage
					try{
						Statement stmt = con.createStatement();
						String sql = "SELECT * from Beitrag WHERE Nutzer_ID =" + nutzer.getID() + " AND Datum between '" + von + "' AND '" + bis + "'";
						ResultSet rs = stmt.executeQuery(sql);

						while (rs.next()) {
							// Ergebnis in Beitrag- Objekt umwandeln
					        Beitrag b = new Beitrag();
					        b.setID(rs.getInt("Beitrag_ID"));
					        b.setErstellZeitpunkt(rs.getTimestamp("Datum"));
					        b.setText(rs.getString("Text"));
					        b.setLikeAnzahl(LikeMapper.likeMapper().zaehleAlleLikesProBeitrag(b));
					        b.setKommentarAnzahl(KommentarMapper.kommentarMapper().zaehleAlleKommentareProBeitrag(b));

					        beitraege.add(b);
						}
						return beitraege;		
					}
					   catch (SQLException e) {
				    		e.printStackTrace();
				    		return null;
					    }				
				}
			 /**
			  * Diese Methode gibt alle Beiträge eines Nutzers aus, sortiert nach Kommentare und Datum
			  * @param nutzer Nutzer, dessen Beiträge ausgegeben werden sollen
			  * @param von frühester Erstellzeitpunkt
			  * @param bis spätester Erstellzeitpunkt
			  * @param sortierung
			  * @return
			  */
			 public ArrayList<Beitrag> alleBeitraegeEinesNutzersNachKommentare(Nutzer nutzer, String von, String bis, int sortierung) {
					//Aufbau der DBVerbindung
					Connection con = DBConnection.connection();
					ArrayList <Beitrag> beitraege= new ArrayList<Beitrag>();
					//Versuch der Abfrage
					try{
						Statement stmt = con.createStatement();
						String sql = "SELECT * from Beitrag WHERE Nutzer_ID =" + nutzer.getID() + " AND Datum between '" + von + "' AND '" + bis + "'";
						ResultSet rs = stmt.executeQuery(sql);

						while (rs.next()) {
							// Ergebnis in Beitrag- Objekt umwandeln
					        Beitrag b = new Beitrag();
					        b.setID(rs.getInt("Beitrag_ID"));
					        b.setErstellZeitpunkt(rs.getTimestamp("Datum"));
					        b.setText(rs.getString("Text"));
					        b.setLikeAnzahl(LikeMapper.likeMapper().zaehleAlleLikesProBeitrag(b));
					        b.setKommentarAnzahl(KommentarMapper.kommentarMapper().zaehleAlleKommentareProBeitrag(b));

					        beitraege.add(b);
						}
						return beitraege;		
					}
					   catch (SQLException e) {
				    		e.printStackTrace();
				    		return null;
					    }				
				}
			 
			 
			 /**
		      * Diese Methode gibt alle Beiträge aus die innerhalb eines bestimmten Zeitpunkt erstellt wurden.
			  * @param String von, String bis
			  * @return Liste der Beiträge
			  */
			 public ArrayList<Beitrag> alleBeitraegeNachZeitraum(String von, String bis) {
					//Aufbau der DBVerbindung
					Connection con = DBConnection.connection();
					ArrayList <Beitrag> beitraege= new ArrayList<Beitrag>();
					//Versuch der Abfrage
					try{
						Statement stmt = con.createStatement();
						String sql = "SELECT * from Beitrag WHERE Datum between '" + von + "' AND '" + bis + "'";
						ResultSet rs = stmt.executeQuery(sql);

						while (rs.next()) {
					        Beitrag b = new Beitrag();
					        b.setID(rs.getInt("Beitrag_ID"));
					        b.setErstellZeitpunkt(rs.getTimestamp("Datum"));
					        b.setText(rs.getString("Text"));
					        b.setLikeAnzahl(LikeMapper.likeMapper().zaehleAlleLikesProBeitrag(b));
					        b.setKommentarAnzahl(KommentarMapper.kommentarMapper().zaehleAlleKommentareProBeitrag(b));

					        beitraege.add(b);
						}
						return beitraege;		
					}
					   catch (SQLException e) {
				    		e.printStackTrace();
				    		return null;
					    }				
				}


}