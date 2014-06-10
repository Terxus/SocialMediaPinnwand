package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Kommentar;
import de.elite.itprojekt.shared.bo.Like;
import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;


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
	public boolean sucheNickName(String nickName);
	
	//Von Domi
	public Nutzer getNutzerAnhandNickname(String nickname);
	public Nutzer getUserByNickname(String nickname);
	
	//Pinnwand
	public ArrayList<Pinnwand> zeigeAllePinnwaende();
	
	//Beitrag
	public void textBeitragErstellen(Beitrag beitrag);
	public ArrayList<Beitrag> findeAlleUserBeitraege(int id);
	public void textBeitragLoeschen(Beitrag beitrag);
	public Beitrag textBeitragBearbeiten(Beitrag beitrag);
	public ArrayList<Beitrag> sucheBeitragPerPinnwand(int id);
	
	public ArrayList<Beitrag> alleBeitraegePerPinnwand(int id);
	
	//Abo
	public ArrayList<Abonnement> zeigeAlleAbosPerNutzer(int id);
	
	//Abo von Domi
	public Abonnement abonnementAnlegen(int AbonnentID, int PinnwandID);
	public void abonnementLoeschen(Abonnement a);
	
	//Kommentar
	public void kommentarErstellen(Kommentar kommentar);
	public ArrayList<Kommentar> findeAlleKommentare(int id);
	public void kommentarLoeschen(Kommentar kommentar);
	public Kommentar kommentarBearbeiten(Kommentar kommentar);
	
	//Like
	public void likeAnlegen(Like like, Beitrag beitrag);
	public boolean likeCheck(Nutzer nutzer, Beitrag beitrag);
	public void likeLoeschen(Beitrag beitrag);
	public Like likeSuchen(int id);
	public int likeZaehlen(Beitrag beitrag);
	public void einzelnesLikeLoeschen(Beitrag beitrag, Nutzer nutzer);
	

}
