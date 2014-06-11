package de.elite.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

import de.elite.itprojekt.client.SocialMediaPinnwand;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Nutzer;

public class OrakelBox {

	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);
	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	Logger logger = SocialMediaPinnwand.getLogger();

	// Durchsucht die Datenbank nach allen Nutzern
	
	
	public void findeAlleNutzer(ArrayList<Nutzer> nutzer) {
		for (Nutzer n : nutzer) {
			oracle.add(n.getVorname() + " " + n.getNachname());
		}
	}

	public MultiWordSuggestOracle schlageNutzerVor() {

		service.zeigeAlleNutzer(new AsyncCallback<ArrayList<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Nutzer nicht gefunden!");

			}

			@Override
			public void onSuccess(ArrayList<Nutzer> result) {
				findeAlleNutzer(result);

			}

		});
		return oracle;

	}

}
