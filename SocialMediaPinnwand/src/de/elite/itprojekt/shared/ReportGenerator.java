package de.elite.itprojekt.shared;



import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;
import de.elite.itprojekt.shared.report.ReportVonAllenNutzern;

/**
 * <p>
 * Synchrone Schnittstelle f�r eine RPC-f�hige Klasse zur Erstellung von
 * Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wir
 * das Paar {@link BankAdministration} und {@lBankAdministrationImplImpl}. Zu
 * technischen Erl�uterung etwa bzgl. GWT RPC bzw. {@link RemoteServiceServlet}
 * siehe {@link BankAdministration} undBankAdministrationImpltungImpl}.
 * </p>
 * <p>
 * Ein ReportGenerator bietet die M�glichkeit, eine Menge von Berichten
 * (Reports) zu erstellen, die Menge von Daten bzgl. bestimmter Sachverhalte des
 * Systems zweckspezifisch darstellen.
 * </p>
 * <p>
 * Die Klasse bietet eine Reihe von <code>create...</code>-Methoden, mit deren
 * Hilfe die Reports erstellt werden k�nnen. Jede dieser Methoden besitzt eine
 * dem Anwendungsfall entsprechende Parameterliste. Diese Parameter ben�tigt der
 * der Generator, um den Report erstellen zu k�nnen.
 * </p>
 * <p> 
 * Bei neu hinzukommenden Bedarfen an Berichten, kann diese Klasse auf einfache
 * Weise erweitert werden. Hierzu k�nnen zus�tzliche <code>create...</code>
 * -Methoden implementiert werden. Die bestehenden Methoden bleiben davon
 * unbeeinflusst, so dass bestehende Programmlogik nicht ver�ndert werden muss.
 * </p>
 * 
 * @author thies
 */
@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

  /**
   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
   * RPC zus�tzlich zum No Argument Constructor der implementierenden Klasse
   *BankAdministrationImpltungImpl} notwendig. Bitte diese Methode direkt nach der
   * Instantiierung aufrufen.
   * 
   * @throws IllegalArgumentException
   */
  public void init() throws IllegalArgumentException;

  /**
   * Setzen der zugeordneten Bank.
   * 
   * @para Bank-Objekt
   * @throws IllegalArgumentException
   */
  public void setPinnwand(Pinnwand p) throws IllegalArgumentException;

  /**
   * Erstellen eines <code>AllAccountsOfCustomerReport</code>-Reports. Dieser
   * Report-Typ stellt s�mtliche Konten eines Kunden dar.
   * 
   * @param c eine Referenz auf das Kundenobjekt bzgl. dessen der Report
   *          erstellt werden soll.
   * @return das fertige Reportobjekt
   * @throws IllegalArgumentException
   * @see AllAccountsOfCustomerReport
   */
  public abstract ReportVonAllenNutzern erstelleReportVonAllenNutzern(
      Nutzer n) throws IllegalArgumentException;

  /**
   * Erstellen eines <code>AllAccountsOfAllCustomersReport</code>-Reports.
   * Dieser Report-Typ stellt s�mtliche Konten aller Kunden dar.
   * 
   * @return das fertige Reportobjekt
   * @throws IllegalArgumentException
   * @see AllAccountsOfAllCustomersReport
   */
  public abstract ReportVonAllenNutzern erstelleReportVonAllenNutzern()
      throws IllegalArgumentException;
}