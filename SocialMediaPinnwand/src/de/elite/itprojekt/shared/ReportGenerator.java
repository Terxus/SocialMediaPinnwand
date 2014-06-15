package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;


@RemoteServiceRelativePath("reportGenerator")
public interface ReportGenerator extends RemoteService {
	 /**
	   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
	   *BankAdministrationImpltungImpl} notwendig. Bitte diese Methode direkt nach der
	   * Instantiierung aufrufen.
	   * 
	   * @throws IllegalArgumentException
	   */
	  public void init() throws IllegalArgumentException;

	  //Global
	  public int zaehleAlleNutzer();
	  public int zaehleAlleKommentare();
	  public int zaehleAlleLikes();
	  public int zaehleAlleBeitraege();
	  public int zaehleAlleAbonnements();
	  public ArrayList<Nutzer> zeigeAlleNutzer();
	  
	  
	  //Nutzer
	  public Nutzer getUserByNickname(String nickname);
	  
	  //Abozählen eines Nutzers
	  public int zaehleAlleAbonnementsPerNutzer(Nutzer nutzer);
	  
	  //Beiträge eines Nutzers
	  public int zaehleBeitraegePerNutzer(Nutzer nutzer);
	  
	  //Likes eines Nutzers
	  public int zaehleLikesPerNutzer(Nutzer nutzer);
	  
	  //Kommentar eines Nutzers
	  public int zaehleKommentarePerNutzer(Nutzer nutzer);
	  
	  
	  
	  ArrayList<Beitrag> alleBeitraegeNachZeitraum(String von, String bis);
	  ArrayList<Nutzer> alleNutzerNachZeitraum(String von, String bis);
}
