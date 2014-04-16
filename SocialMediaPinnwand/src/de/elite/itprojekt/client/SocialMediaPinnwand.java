package de.elite.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SocialMediaPinnwand implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		AboUser AboUser = new AboUser();
		AboSuche AboSuche = new AboSuche();
		AboNavigation AboNavi = new AboNavigation();
		// Die Klasse SeiteEins stellt die Komplette seite dar. Ein Objekt dieser Klasse wird erzeugt in der Variablen "seite".
		RootPanel.get("AboUser").add(AboUser);
		RootPanel.get("AboSuche").add(AboSuche);
		RootPanel.get("AboNav").add(AboNavi);
		//Hier wird die Seite, ein Objekt der Klasse SeiteEins, in der Rootpage ausgegeben
	}
}

