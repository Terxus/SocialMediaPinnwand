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
	 	 
	 
		public void anlegen(Like like, Beitrag beitrag){
			
			//Aufbau der DBVerbindung
			
			
			Connection con = DBConnection.connection();
			int maxid = 0;

			//Versuch der Abfrage
			try{
				Statement stmt = con.createStatement();

		     
		      ResultSet rs = stmt.executeQuery("SELECT MAX(Like_ID) AS maxid " 
		      + "FROM `like` ");

		      
		      if (rs.next()) {
			      
		    	  	maxid=rs.getInt("maxid");
			        like.setID(rs.getInt("maxid") + 1);

			        stmt = con.createStatement();
			        
			        stmt.executeUpdate("INSERT INTO `Like` (Like_ID, Nutzer_ID, Beitrag_ID, Datum) "
				            + "VALUES (" + like.getID() + ",'" + like.getPinnwandId() + "','" + beitrag.getID() + "','" + like.getErstellZeitpunkt()  + "')");
					
		      	}
		    }

		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		}
		
		//Checken ob beitrag schon von Nutzer geliked wurde - Noch nicht implementiert
		
		public boolean likeUeberpruefung(Nutzer n, Beitrag b) {
			Connection con = DBConnection.connection();
			try {
				Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery("SELECT * FROM `like` WHERE Nutzer_ID="+ n.getID() +" AND Beitrag_ID=" + b.getID());
				 if(rs.next() == true) {
					 return true;

				 } else return false;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		}
		//Likes von Beitrag löschen Sinn?
	 
	 	public void loeschen(Beitrag beitrag) {
	 		
	 		Connection con = DBConnection.connection();
	 			Statement stmt = null;

	 			try {
	 				stmt = con.createStatement();

	 				stmt.executeUpdate("DELETE FROM `Like` "
	 						+ "WHERE Beitrag_ID=" + beitrag.getID());
	 				
	 				
	 			} catch (SQLException e2) {
	 				e2.printStackTrace();
	 			}
	 			
	 			return;
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
		//Alle Likes je Beitrag 
		
		
		public int zaehleAlleLikesProBeitrag(Beitrag beitrag) {
			
			int id = beitrag.getID();
			
			int count = -1;
		
			Connection con = DBConnection.connection();

	
			try{
				Statement stmt = con.createStatement();
				//Suche alle Likes zu einem Beitrag
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `like` WHERE Beitrag_ID=" + id);


				while (rs.next()) {
			        count=rs.getInt(1);
			      }

			}

		    catch (SQLException e) {
		    		e.printStackTrace();
		    }

			return count;
		}
		
		 
		public void einzelnesLikeLoeschen(Beitrag beitrag, Nutzer nutzer) {
			Connection con = DBConnection.connection();
			try {
				Statement state = con.createStatement();

				state.executeUpdate("DELETE FROM `Like` WHERE Beitrag_ID=" + beitrag.getID() + " AND Nutzer_ID=" + nutzer.getID());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		 //REPORT
		//Likes zählen
			
			 public int zaehleLikes(){
				 int count = -1;
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `Like`");

					while (rs.next()) {
				        count=rs.getInt(1);
				      }

				}

			    catch (SQLException e) {
			    		e.printStackTrace();
			    }
				return count;

			 }
			 
			 //REPORT Likes per Nutzer zählen
			 
			 public int zaehleLikesPerNutzer(Nutzer nutzer){
				 int count = -1;
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM `Like` WHERE Nutzer_ID=" + nutzer.getID());

					while (rs.next()) {
				        count=rs.getInt(1);
				      }

				}

			    catch (SQLException e) {
			    		e.printStackTrace();
			    }
				return count;

			 }

}