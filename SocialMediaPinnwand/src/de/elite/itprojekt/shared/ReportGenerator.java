package de.elite.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


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
	  
	  public void setNutzer();
	  
	  public void nutzerReport();
	  
	  public void likeReport();
	  
	  public void kommentarReport();
	  
	  public void likeReportSortiert();
	  
	  public void kommentarReportSortiert();
	  
	  
	  
	  //
	  public int zaehleAlleNutzer();
	  public int zaehleAlleKommentare();
	  public int zaehleAlleLikes();
	  public int zaehleAlleBeitraege();
	  public int zaehleAlleAbonnements();
	  

}
