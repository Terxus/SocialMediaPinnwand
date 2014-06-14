package de.elite.itprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.elite.itprojekt.shared.bo.*;

/**
 * Diese Klasse bildet die Likeobjekte auf eine relationale Datenbank ab
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 *
 */
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
	/**
	 * Diese Methode gibt die Likes eines Beitrags in einer Liste zurück
	 * @param id Eindeutiger Identifikator eines Beitrags in der Datenbank
	 * @return Like
	 */
	 public ArrayList<Like> findeDurchId(int id){
		 
			Connection con = DBConnection.connection();
			ArrayList <Like> likeListe= new ArrayList<Like>();

			try {
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM Likee WHERE Beitrag_ID=" +id);

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
	 
	 
	 	 
	 	/**
	 	 * Legt einen neuen Like in der Datenbank an
	 	 * @param like neu erstellter Like
	 	 * @param beitrag Beitrag der geliked wurde
	 	 */
		public void anlegen(Like like, Beitrag beitrag){
			
			//Aufbau der DBVerbindung
			
			
			Connection con = DBConnection.connection();
			int maxid = 0;

			//Versuch der Abfrage
			try{
				Statement stmt = con.createStatement();

		     
		      ResultSet rs = stmt.executeQuery("SELECT MAX(Like_ID) AS maxid " 
		      + "FROM Likee ");

		      
		      if (rs.next()) {
			      
		    	  	maxid=rs.getInt("maxid");
			        like.setID(rs.getInt("maxid") + 1);

			        stmt = con.createStatement();
			        
			        stmt.executeUpdate("INSERT INTO Likee (Like_ID, Nutzer_ID, Beitrag_ID, Datum) "
				            + "VALUES (" + like.getID() + ",'" + like.getPinnwandId() + "','" + beitrag.getID() + "','" + like.getErstellZeitpunkt()  + "')");
					
		      	}
		    }

		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		}
		
		//Checken ob beitrag schon von Nutzer geliked wurde - Noch nicht implementiert
		/**
		 * Diese Methode überprüft, ob ein Beitrag schon von einem Nutzer geliked wurde
		 * @param n Nutzer, dessen "LikeStatus" überprüft werden soll
		 * @param b Der entsprechende Beitrag, dessen likes geprüft werden
		 * @return True/false, je nachdem ob schon geliked wurde oder nicht
		 */
		public boolean likeUeberpruefung(Nutzer n, Beitrag b) {
			Connection con = DBConnection.connection();
			try {
				Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery("SELECT * FROM Likee WHERE Nutzer_ID="+ n.getID() +" AND Beitrag_ID=" + b.getID());
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
		/**
		 * Diese Methode löscht alle likes eines Beitrags
		 * @param beitrag Beitrag, dessen likes gelöscht werden sollen
		 */
	 	public void loeschen(Beitrag beitrag) {
	 		
	 		Connection con = DBConnection.connection();
	 			Statement stmt = null;

	 			try {
	 				stmt = con.createStatement();

	 				stmt.executeUpdate("DELETE FROM Likee "
	 						+ "WHERE Beitrag_ID=" + beitrag.getID());
	 				
	 				
	 			} catch (SQLException e2) {
	 				e2.printStackTrace();
	 			}
	 			
	 			return;
	 		}
		
		
		
	
		/**
		 * Diese Methode gibt ein einzelnes Like anhand der ID zur�ck
		 * @param id Eindeutiger Identifikator eines likes in der Datenbank
		 * @return like
		 */
		public Like findeEinzelneDurchID(int id){

			Connection con = DBConnection.connection();


			try{
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("SELECT * FROM Likee WHERE Like_ID=" + id );


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
		
		
		/**
		 * Diese Methode gibt zurück, wie oft ein Beitrag geliked wurde
		 * @param beitrag Beitrag, dessen likes gezählt werden sollen
		 * @return Anzahl der likes
		 */
		public int zaehleAlleLikesProBeitrag(Beitrag beitrag) {
			
			int id = beitrag.getID();
			
			int count = -1;
		
			Connection con = DBConnection.connection();

	
			try{
				Statement stmt = con.createStatement();
				//Suche alle Likes zu einem Beitrag
				ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Likee WHERE Beitrag_ID=" + id);


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
		  * Diese Methode löscht ein einzelnes like eines Beitrags
		  * @param beitrag Beitrag, dessen like gelöscht werden soll
		  * @param nutzer Nutzer, der den like abgegeben hat
		  */
		public void einzelnesLikeLoeschen(Beitrag beitrag, Nutzer nutzer) {
			Connection con = DBConnection.connection();
			try {
				Statement state = con.createStatement();

				state.executeUpdate("DELETE FROM Likee WHERE Beitrag_ID=" + beitrag.getID() + " AND Nutzer_ID=" + nutzer.getID());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		/**
		 * Wenn ein Beitrag gel�scht wird, löscht diese Methode automatisch alle zugehörigen likes
		 * @param id Eindeutiger Identifikator eines Beitrags in der Datenbank
		 */
	 	public void likesVonBeitragLoeschen(int id) {

	 		Connection con = DBConnection.connection();
 			Statement stmt = null;

 			try {
 				stmt = con.createStatement();

 				stmt.executeUpdate("DELETE FROM Likee "
 						+ "WHERE Beitrag_ID=" + id);


 			} catch (SQLException e2) {
 				e2.printStackTrace();
 			}
 			
 			return;
 		}
		
		 
			/**
			 * Diese Methode gibt die Anzahl aller likes zur�ck
			 * @return Anzahl der likes
			 */
			 public int zaehleLikes(){
				 int count = -1;
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Likee");

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
			  * Die Methode gibt die Anzahl der likes zurück, die ein Nutzer abgegeben hat
			  * @param nutzer Nutzer, dessen likes gezählt werden sollen
			  * @return Anzahl der likes
			  */
			 public int zaehleLikesPerNutzer(Nutzer nutzer){
				 int count = -1;
				Connection con = DBConnection.connection();

				try {
					Statement stmt = con.createStatement();

					ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Likee WHERE Nutzer_ID=" + nutzer.getID());

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