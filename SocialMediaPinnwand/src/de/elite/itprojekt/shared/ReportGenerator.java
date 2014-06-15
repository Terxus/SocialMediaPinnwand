package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;

/**
* <p>
* Synchrone Schnittstelle für eine RPC-fähige Klasse zur Erstellung von
* Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wir
* das Paar {@link PinnwandVerwaltung} und {@PinnwandVerwaltungImpl}. Zu
* technischen Erläuterung etwa bzgl. GWT RPC bzw. {@link RemoteServiceServlet}
* siehe {@link PinnwandVerwaltung} und PinnwandVerwaltungImpl}.
* </p>
* <p>
* Ein ReportGenerator bietet die Möglichkeit, eine Menge von Berichten
* (Reports) zu erstellen, die Menge von Daten bzgl. bestimmter Sachverhalte des
* Systems zweckspezifisch darstellen.
* </p>
* <p>
* Die Klasse bietet eine Reihe von <code>create...</code>-Methoden, mit deren
* Hilfe die Reports erstellt werden können. Jede dieser Methoden besitzt eine
* dem Anwendungsfall entsprechende Parameterliste. Diese Parameter benötigt der
* der Generator, um den Report erstellen zu können.
* </p>
* <p> 
* Bei neu hinzukommenden Bedarfen an Berichten, kann diese Klasse auf einfache
* Weise erweitert werden. Hierzu können zusätzliche <code>create...</code>
* -Methoden implementiert werden. Die bestehenden Methoden bleiben davon
* unbeeinflusst, so dass bestehende Programmlogik nicht verändert werden muss.
* </p>
* 
* @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
* @author In Anlehnung an Prof. Dr. Thies
*/

@RemoteServiceRelativePath("reportGenerator")
public interface ReportGenerator extends RemoteService {

	  public void init() throws IllegalArgumentException;
	  
		/**
		 * Diese Methode liefert alle Nutzer im System. Alle Tupel die in der DB <b>Nutzer</b> enthalten sind,
		 * werden gezählt und als Ganzzahl zurückgeliefert.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return int
		 * 
		 */
	  
	  public int zaehleAlleNutzer();
	  
		/**
		 * Diese Methode liefert alle Kommentare im System. Alle Tupel die in der DB <b>Kommentare</b> enthalten sind,
		 * werden gezählt und als Ganzzahl zurückgeliefert.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return int
		 * 
		 */
	  
	  public int zaehleAlleKommentare();
	  
		/**
		 * Diese Methode liefert alle Likes im System. Alle Tupel die in der DB <b>Likee</b> enthalten sind,
		 * werden gezählt und als Ganzzahl zurückgeliefert.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return int
		 * 
		 */
	  
	  public int zaehleAlleLikes();
	  
		/**
		 * Diese Methode liefert alle Beiträge im System. Alle Tupel die in der DB <b>Beitrag</b> enthalten sind,
		 * werden gezählt und als Ganzzahl zurückgeliefert.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return int
		 * 
		 */
	  
	  public int zaehleAlleBeitraege();
	  
		/**
		 * Diese Methode liefert alle Abonnements im System. Alle Tupel die in der DB <b>Abonnement</b> enthalten sind,
		 * werden gezählt und als Ganzzahl zurückgeliefert.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return int
		 * 
		 */
	  
	  public int zaehleAlleAbonnements();
	  
		/**
		 * Diese Methode gibt alle registrierten Nutzer in einer Liste aus. Sie wird im Report benötigt für die Suggestbox.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return ArrayList<Nutzer>
		 */
	  
	  public ArrayList<Nutzer> zeigeAlleNutzer();
	  
		/**
		 * Diese Methode gibt ein Nutzer-Objekt anhand seines Nicknamens zurück.
		 * werden gezählt und als Ganzzahl zurückgeliefert.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return Nutzer
		 * 
		 */

	  public Nutzer getUserByNickname(String nickname);
	  
	  /**
		 * Diese Methode zählt alle Abonnements eines Nutzers und gibt sie als Ganzzahl zurück.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return int
		 * 
		 */
	  
	  public int zaehleAlleAbonnementsPerNutzer(Nutzer nutzer);
	  
		/**
		 * Diese Methode zählt alle Beiträge eines Nutzers und gibt sie als Ganzzahl zurück.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return int
		 * 
		 */
	  
	  public int zaehleBeitraegePerNutzer(Nutzer nutzer);
	  
		/**
		 * Diese Methode zählt alle Likes eines Nutzers und gibt sie als Ganzzahl zurück.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return int
		 * 
		 */
	  
	  public int zaehleLikesPerNutzer(Nutzer nutzer);
	  
		/**
		 * Diese Methode zählt alle Kommentare eines Nutzers und gibt sie als Ganzzahl zurück.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return int
		 * 
		 */

	  public int zaehleKommentarePerNutzer(Nutzer nutzer);
	  
		/**
		 * Diese Methode gibt alle Nutzer zwischen zwei bestimmen Daten zurück.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return ArrayList<Nutzer>
		 * 
		 */
	  
	  ArrayList<Beitrag> alleBeitraegeNachZeitraum(String von, String bis);
	  
		/**
		 * Diese Methode gibt alle Beiträge zwischen zwei bestimmten Daten zurück.
		 * 
		 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
		 * @return ArrayList<Nutzer>
		 * 
		 */
	  
	  ArrayList<Nutzer> alleNutzerNachZeitraum(String von, String bis);
}
