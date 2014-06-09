package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;

public interface PinnwandVerwaltungAsync {

	void textBeitragErstellen(Beitrag beitrag, AsyncCallback<Void> callback);

	void zeigeAllePinnwaende(AsyncCallback<ArrayList<Pinnwand>> callback);

	void nutzerAnlegen(Nutzer nutzer, AsyncCallback<Void> callback);

	void nutzerEditieren(Nutzer nutzer, AsyncCallback<Void> callback);

	void nutzerLoeschen(Nutzer nutzer, AsyncCallback<Void> callback);

	void zeigeAlleNutzer(AsyncCallback<ArrayList<Nutzer>> callback);
	
	void loginCheck(String nutzerName, String passWort, AsyncCallback<Nutzer> callback);
	
	void sucheNutzerID(int id, AsyncCallback<Nutzer> callback);

	void updateNutzer(Nutzer nutzer, AsyncCallback<Nutzer> callback);
	
	void findeAlleBeitraege(AsyncCallback<ArrayList<Beitrag>> callback);

}
