package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Kommentar;
import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;
import de.elite.itprojekt.shared.bo.Like;




@RemoteServiceRelativePath("pinnwandVerwaltung") //Shortcut Name
public interface PinnwandVerwaltung extends RemoteService {
	
	//Nutzer Methoden
	public ArrayList<Nutzer> zeigeAlleNutzer();
	public void nutzerAnlegen(Nutzer nutzer);
	public void nutzerEditieren(Nutzer nutzer);
	public void nutzerLoeschen(Nutzer nutzer);
	public Nutzer loginCheck(String nutzerName, String passWort);
	public Nutzer sucheNutzerID(int id);
	public Nutzer updateNutzer(Nutzer nutzer);
	public Nutzer getNutzerAnhandNickname(String nickname);
	
	//Pinnwand
	public ArrayList<Pinnwand> zeigeAllePinnwaende();
	public ArrayList<Beitrag> sucheBeitragPerPinnwand(int id);
	
	//Beitrag
	public void textBeitragErstellen(Beitrag beitrag);
	public ArrayList<Beitrag> findeAlleUserBeitraege(int id);
	public void textBeitragLoeschen(Beitrag beitrag);
	public Beitrag textBeitragBearbeiten(Beitrag beitrag);
	
	//Abo
	public ArrayList<Abonnement> zeigeAlleAbosPerNutzer(int id);
	public Abonnement abonnementAnlegen(int AbonnentID, int PinnwandID);
	public void abonnementLoeschen(Abonnement a);
	
	//Kommentar
	public void kommentarErstellen(Kommentar kommentar);
	public ArrayList<Kommentar> findeAlleKommentare(int id);

	//Likes
	
	public Like insertLike(Like like);
	public void saveLike(Like like) throws IllegalArgumentException;
	public void deleteLike(Like like) throws IllegalArgumentException;
	public Boolean checkIfLiked (Nutzer n, Beitrag b);
	
	
	
	
	

}
