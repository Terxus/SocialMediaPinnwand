package de.elite.itprojekt.client;


import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;

import de.elite.itprojekt.client.gui.NutzerLogin;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Nutzer;

/**
 * Entry-Point-Klasse des Projekts <b>SocialMediaPinnwand</b>.
 * @autor Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class SocialMediaPinnwand implements EntryPoint {
	
	/**
	 * Logger für Clientseitige Ausgabe von Informationen
	 */
	
	private static final String LOGGER_NAME = "Social Media";
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	public static Logger getLogger() {
		    return log;
		  }
	
	public final Nutzer n = new Nutzer();
	
	/**
	 * <b>service</b> heißt unsere Proxyreferenz, welche mit dem Server interagiert.
	 */
	
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);
	
	  /**
	   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	   * zusichert, benötigen wir eine Methode
	   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	   * <code>main()</code>-Methode normaler Java-Applikationen.
	   */
	
  public void onModuleLoad() {
	  
	  String cookie = Cookies.getCookie("gp5cookie");
	  
	  if(cookie == null) {
		  
		  /**
		   * Wenn noch kein Cookie mit dem Namen <b>gp5cookie</b> im Browser des Anwenders existiert,
		   * dann soll die Loginseite geladen werden.
		   */
	  
		  NutzerLogin startSeite = new NutzerLogin();
		  startSeite.loadLoginView();
		  System.out.println("Cookie ist nicht vorhanden");
	  }
	  else {
		  
		  /**
		   * Wenn ein Cookie mit dem Namen <b>gp5cookie</b> vorhanden ist, dann soll die Pinnwand geladen werden.
		   * In der Klasse NutzerLogin existiert dann eine weitere Nutzerüberprüfungsinstanz.
		   */
		  
		  NutzerLogin startSeite = new NutzerLogin();
		  startSeite.loadPinnwand();
		  System.out.println("Cookie mit der ID:" + " " + Cookies.getCookie("gp5cookie") + " " + "ist vorhanden");
	  }
  }
}