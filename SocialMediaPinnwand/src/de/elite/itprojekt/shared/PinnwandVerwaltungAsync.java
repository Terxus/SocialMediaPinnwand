package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Kommentar;
import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;
import de.elite.itprojekt.shared.bo.Like;

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
	
	void getNutzerAnhandNickname(String nickname, AsyncCallback<Nutzer> callback);
	
	void abonnementAnlegen(int AbonnentID, int PinnwandID,AsyncCallback<Abonnement> callback);
	
	void abonnementLoeschen(Abonnement a, AsyncCallback<Void> callback);
	
	void saveLike(Like like, AsyncCallback<Void> callback);
	
	void insertLike(Like like, AsyncCallback<Like> callback);
	
	void deleteLike(Like like, AsyncCallback<Void> callback);
	
	void findeAlleKommentare(int id, AsyncCallback<ArrayList<Kommentar>> callback);
	
	void sucheBeitragPerPinnwand(int id, AsyncCallback<ArrayList<Beitrag>> callback);
	
	public void checkIfLiked (Nutzer n, Beitrag b, AsyncCallback<Boolean> callbackBoolean);

	
	
	
}
