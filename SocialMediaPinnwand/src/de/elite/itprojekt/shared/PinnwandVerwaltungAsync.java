package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Kommentar;
import de.elite.itprojekt.shared.bo.Like;
import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;

public interface PinnwandVerwaltungAsync {

	void textBeitragErstellen(Beitrag beitrag, AsyncCallback<Void> callback);
	
	void kommentarErstellen(Kommentar kommentar, AsyncCallback<Void> callback);

	void zeigeAllePinnwaende(AsyncCallback<ArrayList<Pinnwand>> callback);

	void nutzerAnlegen(Nutzer nutzer, AsyncCallback<Void> callback);

	void nutzerEditieren(Nutzer nutzer, AsyncCallback<Void> callback);

	void nutzerLoeschen(Nutzer nutzer, AsyncCallback<Void> callback);

	void zeigeAlleNutzer(AsyncCallback<ArrayList<Nutzer>> callback);
	
	void loginCheck(String nutzerName, String passWort, AsyncCallback<Nutzer> callback);
	
	void sucheNutzerID(int id, AsyncCallback<Nutzer> callback);

	void updateNutzer(Nutzer nutzer, AsyncCallback<Nutzer> callback);

	void findeAlleUserBeitraege(int id, AsyncCallback<ArrayList<Beitrag>> callback);

	void textBeitragLoeschen(Beitrag beitrag, AsyncCallback<Void> asyncCallback);
	
	void textBeitragBearbeiten(Beitrag beitrag, AsyncCallback<Beitrag> asyncCallback);

	void zeigeAlleAbosPerNutzer(int id, AsyncCallback<ArrayList<Abonnement>> callback);

	void findeAlleKommentare(int id, AsyncCallback<ArrayList<Kommentar>> callback);
	
	void sucheBeitragPerPinnwand(int id, AsyncCallback<ArrayList<Beitrag>> callback);
	
	//Von Domi
	
	void getNutzerAnhandNickname(String nickname, AsyncCallback<Nutzer> callback);

	void abonnementAnlegen(int AbonnentID, int PinnwandID,AsyncCallback<Abonnement> callback);

	void getUserByNickname(String nickname, AsyncCallback<Nutzer> callback);

	void abonnementLoeschen(Abonnement a, AsyncCallback<Void> callback);
	
	//

	void alleBeitraegePerPinnwand(int id, AsyncCallback<ArrayList<Beitrag>> callback);

	void likeAnlegen(Like like, Beitrag beitrag, AsyncCallback<Void> callback);

	void kommentarLoeschen(Kommentar kommentar, AsyncCallback<Void> callback);

	void kommentarBearbeiten(Kommentar kommentar, AsyncCallback<Kommentar> callback);

	void likeCheck(Nutzer nutzer, Beitrag beitrag, AsyncCallback<Boolean> callback);

	void likeLoeschen(Beitrag beitrag, AsyncCallback<Void> callback);

	void likeSuchen(int id, AsyncCallback<Like> callback);

	void likeZaehlen(Beitrag beitrag, AsyncCallback<Integer> callback);

	void einzelnesLikeLoeschen(Beitrag beitrag, Nutzer nutzer, AsyncCallback<Void> callback);

}
