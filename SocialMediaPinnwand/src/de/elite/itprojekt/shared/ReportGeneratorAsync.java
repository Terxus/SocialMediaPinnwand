package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;


public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void zaehleAlleNutzer(AsyncCallback<Integer> callback);

	void zaehleAlleKommentare(AsyncCallback<Integer> callback);

	void zaehleAlleLikes(AsyncCallback<Integer> callback);

	void zaehleAlleBeitraege(AsyncCallback<Integer> callback);

	void zaehleAlleAbonnements(AsyncCallback<Integer> callback);

	void zeigeAlleNutzer(AsyncCallback<ArrayList<Nutzer>> callback);

	void getUserByNickname(String nickname, AsyncCallback<Nutzer> callback);

	void zaehleAlleAbonnementsPerNutzer(Nutzer nutzer, AsyncCallback<Integer> callback);

	void zaehleBeitraegePerNutzer(Nutzer nutzer, AsyncCallback<Integer> callback);

	void zaehleLikesPerNutzer(Nutzer nutzer, AsyncCallback<Integer> callback);

	void zaehleKommentarePerNutzer(Nutzer nutzer, AsyncCallback<Integer> callback);

	void alleNutzerNachZeitraum(String von, String bis, AsyncCallback<ArrayList<Nutzer>> callback);
	
	void alleBeitraegeNachZeitraum(String von, String bis, AsyncCallback<ArrayList<Beitrag>> callback);

}
