package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;


@RemoteServiceRelativePath("pinnwandVerwaltung") //Shortcut name
public interface PinnwandVerwaltung extends RemoteService {
	
	//Nutzer Methoden
	
	ArrayList<Nutzer> zeigeAlleNutzer();
	void nutzerAnlegen(Nutzer nutzer);
	void nutzerEditieren(Nutzer nutzer);
	void nutzerLoeschen(Nutzer nutzer);
	
	//Pinnwand
	ArrayList<Pinnwand> zeigeAllePinnwaende();
	
	//Beitrag
	void textBeitragErstellen(Beitrag beitrag);
	Nutzer loginCheck(String nutzerName, String passWort);
	Nutzer sucheNutzerID(int id);
	Nutzer updateNutzer(Nutzer nutzer);
	
	public ArrayList<Beitrag> findeAlleBeitraege() throws IllegalArgumentException;	

}
