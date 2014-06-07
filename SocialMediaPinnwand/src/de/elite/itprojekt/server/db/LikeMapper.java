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
	 * Protected constructor to prevent to generate a new object of LikeMapper
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
	 
	 /*
		 * @see 	insertLike(Like like):	Speichert einen Like in der Datenbank
		 * @param	Objekt von Typ Like
		 * @return 	Gespeichertes Objekt vom Typ Like
		 */ 
		public Like insertLike(Like like){
			
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			int maxid = 0;
							
			//Versuch der Abfrage
			try{
				Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(Like_ID) AS maxid "
		          + "FROM `Like`");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		    	  
		    	  	maxid=rs.getInt("maxid");
			        like.setID(rs.getInt("maxid") + 1);
		
			        stmt = con.createStatement();
		
			        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
			        
			       
		  stmt.executeUpdate("INSERT INTO `Like` (Like_ID, Nutzer_ID, Beitrag_ID, Datum) "
			    			           + "VALUES ('"+like.getID()+"', '"+like.getNutzer().getID()+"', '"+ like.getBeitrag().getID() +"', CURRENT_TIMESTAMP);");
			        

			      
		      }
		    }
			
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return getLikeByID(maxid+1);
		}
		
		/*
		 * @see 	deleteLike(Like like): Löscht einen Like aus der Datenbank
		 * @param 	Objekt von Typ Like
		 * @return 		-
		 */
		public void deleteLike(Like like){
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			
			//Versuch der Abfrage
		    try {
		      Statement stmt = con.createStatement();
		     
		      stmt.executeUpdate("DELETE FROM `Like` WHERE `Nutzer_ID`=" + like.getNutzer().getID() + " AND `Beitrag_ID`=" + like.getBeitrag().getID());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    } 
		}
		
		public boolean checIfLiked(Nutzer n, Beitrag b) {
			Connection con = DBConnection.connection();
			try {
				Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery("SELECT * FROM `Like` WHERE Nutzer_ID="+ n.getID() +" AND Beitrag_ID=" + b.getID());
				 if(rs.next() == true) {
					 return false;
					
				 } else return true;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		 /*
		 * @see 	getLikeById(int id): Sucht Like anhand von ID
		 * @param 	Like ID
		 * @return 	Like Objekt
		 */
		 public Like getLikeByID(int id){
			
			//Aufbau der DBVerbindung
			Connection con = DBConnection.connection();
			
			//Versuch der Abfrage
			try{
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM `like` WHERE Like_ID=" + id );
				
				//Maximal ein Rückgabewert da Id Primärschlüssel
				if (rs.next()) {
			        // Ergebnis in Like- Objekt umwandeln
			        Like l = new Like();
			        l.setID(rs.getInt("Like_ID"));
			        l.setErstellZeitpunkt(rs.getTimestamp("Datum"));
			        l.setNutzer(NutzerMapper.nutzerMapper().sucheNutzerID(rs.getInt("nutzer_ID")));
			        l.setBeitrag(BeitragMapper.beitragMapper().sucheBeitragID(rs.getInt("beitrag_ID")));
			             
			        
			        //LikeObjekt zurückgeben
			        return l;
				}
			}
			
		    catch (SQLException e) {
	    		e.printStackTrace();
	    		return null;
		    }
		
		return null;
		}
		 
		 /*
			 * @see 	getLikeByBeitrag(int id): Sucht alle Likes die zu einem Beitrag gehören
			 * @param	Beitrag ID
			 * @return 	ArrayList mit Like Objekten
			 */
			 public ArrayList<Like> getLikeByBeitrag(int id){
				//Aufbau der DBVerbindung
				Connection con = DBConnection.connection();
				ArrayList <Like> likeListe= new ArrayList<Like>();
				
				//Versuch der Abfrage
				try{
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery("SELECT * FROM `Like` WHERE Beitrag_ID="+id);

					while (rs.next()) {
				        Like l = new Like();
				        l.setID(rs.getInt("Like_ID"));
				        l.setErstellZeitpunkt(rs.getTimestamp("Datum"));
				        l.setNutzer(NutzerMapper.nutzerMapper().sucheNutzerID(rs.getInt("Nutzer_ID")));
				        l.setBeitrag(BeitragMapper.beitragMapper().sucheBeitragID(rs.getInt("Beitrag_ID")));
				      
				        likeListe.add(l);
				       
				      }
					return likeListe;
				}
				
			    catch (SQLException e) {
			    		e.printStackTrace();
			    }
				return likeListe;
			 }
			 
			 
			 // Ist Like schon vorhanden?
			 public boolean checkIfLiked(Nutzer n, Beitrag b) {
					Connection con = DBConnection.connection();
					try {
						Statement stmt = con.createStatement();
						 ResultSet rs = stmt.executeQuery("SELECT * FROM `Like` WHERE Nutzer_ID="+ n.getID() +" AND Beitrag_ID=" + b.getID());
						 if(rs.next() == true) {
							 return false;
							
						 } else return true;
					}
					catch (SQLException e) {
						e.printStackTrace();
						return false;
					}
					
				}
			 
		
}