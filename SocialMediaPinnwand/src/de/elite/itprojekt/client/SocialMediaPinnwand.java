package de.elite.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;

import de.elite.itprojekt.client.gui.NutzerLogin;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Nutzer;

public class SocialMediaPinnwand implements EntryPoint {
	
	public final Nutzer n = new Nutzer();
	
	/*
	 *
	 * Remote proxy call
	 */
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);
	
	
  public void onModuleLoad() {
	  
	  String cookie = Cookies.getCookie("gp5cookie");
	  
	  if(cookie == null) { // Wenn noch kein Cookie existiert loginseite Laden
	  
		  NutzerLogin startSeite = new NutzerLogin();
		  startSeite.loadLoginView();
		  System.out.println("Cookie ist nicht da");
	  }
	  else { // Wenn schon ein Cookie mit dem Namen gp5cookie existiert dann soll direkt die Pinnwand geladen werden.
		  
		  NutzerLogin startSeite = new NutzerLogin();
		  startSeite.loadPinnwand();
		  System.out.println("Cookie ist da" + " " + Cookies.getCookie("gp5cookie"));
	  }
  }
}