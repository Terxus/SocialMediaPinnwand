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
	
	 public ArrayList<Beitrag> getBeitragByPinnwand(int id) {

		Connection con = DBConnection.connection();
		ArrayList <Beitrag> beitragListe= new ArrayList<Beitrag>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Beitrag WHERE Nutzer_ID="+id + " ORDER BY Datum DESC");
			
			
		//hier eventl. mit Pinnwand verknüpfen?!

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
		        		+ "VALUES (" + textBeitrag.getID() + ",'" + textBeitrag.getNutzerId() + "','"  + textBeitrag.getID() + "','" + textBeitrag.getText() + "','" + textBeitrag.getErstellZeitpunkt() +"')");
  
	      }
	    }

	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	}
	 //Alle Beiträge zu nutzer anzeigen
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
	 	
	 	public void textBeitragLoeschen(Beitrag beitrag) {
	 		
	 		Connection con = DBConnection.connection();
	 			Statement stmt = null;

	 			try {
	 				stmt = con.createStatement();

	 				stmt.executeUpdate("DELETE FROM Beitrag "
	 						+ "WHERE Beitrag_ID=" + beitrag.getID());
	 				
	 				KommentarMapper.kommentarMapper().autoKommentarLoeschen(beitrag.getID());
	 				

	 			} catch (SQLException e2) {
	 				e2.printStackTrace();
	 			}
	 			
	 			return;
	 		}
	 
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
	
		
		//Beitrag per ID aus der DB holen
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
		
		//Alle Beitraege die zu einer Pinnwand gehören (Abobeziehung)
		
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
				        //Likes in Array
				        //b.setLikeList(LikeMapper.likeMapper().findeDurchId(rs.getInt("Beitrag_ID")));

				        //Kommentare zu jeweiligen Beitrag im Array
				        b.setKommentarListe(KommentarMapper.kommentarMapper().findeDurchId(rs.getInt("Beitrag_ID")));

				        			       

				        beitragListe.add(b);
				      }
					return beitragListe;
				}

			    catch (SQLException e) {
			    		e.printStackTrace();
			    }
			return beitragListe;
			}
		 //REPORT
		//Beiträge zählen
			
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
			 
			 //REPORT Beiträge per Nutzer zählen
			 
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
			 
			 //Beiträge sortiert nach Likes und Datum
			 
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
			 //Beiträge sortiert nach Kommentare und Datum
			 
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
		
	
}