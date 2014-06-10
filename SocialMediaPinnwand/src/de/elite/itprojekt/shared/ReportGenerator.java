package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.elite.itprojekt.shared.bo.Nutzer;


@RemoteServiceRelativePath("reportGenerator")
public interface ReportGenerator extends RemoteService {
	 /**
	   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC zus√§tzlich zum No Argument Constructor der implementierenden Klasse
	   *BankAdministrationImpltungImpl} notwendig. Bitte diese Methode direkt nach der
	   * Instantiierung aufrufen.
	   * 
	   * @throws IllegalArgumentException
	   */
	  public void init() throws IllegalArgumentException;
	  
	  public void setNutzer();
	  
	  public void nutzerReport();
	  
	  public void likeReport();
	  
	  public void kommentarReport();
	  
	  public void likeReportSortiert();
	  
	  public void kommentarReportSortiert();
	  
	  
	  
	  //Global
	  public int zaehleAlleNutzer();
	  public int zaehleAlleKommentare();
	  public int zaehleAlleLikes();
	  public int zaehleAlleBeitraege();
	  public int zaehleAlleAbonnements();
	  public ArrayList<Nutzer> zeigeAlleNutzer();
	  
	  
	  //Nutzer
	  public Nutzer getUserByNickname(String nickname);
	  
	  //Aboz‰hlen eines Nutzers
	  public int zaehleAlleAbonnementsPerNutzer(Nutzer nutzer);
	  
	  //Beitr‰ge eines Nutzers
	  public int zaehleBeitraegePerNutzer(Nutzer nutzer);
	  
	  //Likes eines Nutzers
	  public int zaehleLikesPerNutzer(Nutzer nutzer);
}
