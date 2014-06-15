package de.elite.itprojekt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Kommentar;
import de.elite.itprojekt.shared.bo.Like;
import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;

/**
 * <p>
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung von Pinnwänden.
 * </p>
 * <p>
 * <b>Frage:</b> Warum werden diese Methoden nicht als Teil der Klassen
 * {@link Abonnement}, {@link Beitrag}, {@link Kommentar}, {@link Like},
 * {@link Nutzer}, {@link Pinnwand} oder {@link Textbeitrag}
 * implementiert?<br>
 * <b>Antwort:</b> Z.B. das Löschen eines Nutzers erfordert Kenntnisse über die
 * Verflechtung eines Nutzers mit dessen Pinnwand-Objekten. Um die Klasse <code>Nutzer</code>
 * bzw. <code>Pinnwand</code> nicht zu stark an andere Klassen zu koppeln, wird
 * das Wissen darüber, wie einzelne "Daten"-Objekte koexistieren, in der
 * vorliegenden Klasse gekapselt.
 * </p>
 * <p>
 * Natürlich existieren Frameworks wie etwa Hibernate, die dies auf eine andere
 * Weise realisieren. Wir haben jedoch ganz bewusst auf deren Nutzung
 * verzichtet, um in diesem kleinen Demoprojekt den Blick auf das Wesentliche
 * nicht unnötig zu verstellen.
 * </p>
 * <p>
 * <code>@RemoteServiceRelativePath("pinnwandVerwaltung")</code> ist bei der
 * Adressierung des aus der zugehörigen Impl-Klasse entstehenden
 * Servlet-Kompilats behilflich. Es gibt im Wesentlichen einen Teil der URL des
 * Servlets an.
 * </p>
 * 
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @author In Anlehnung an Prof. Dr. Thies
 */

@RemoteServiceRelativePath("pinnwandVerwaltung") //Shortcut Name
public interface PinnwandVerwaltung extends RemoteService {
	
	//Nutzer Methoden
	
	 /**
	   * Alle Nutzer anzeigen.
	   * 
	   * @return ArrayList<Nutzer>
	   */
	public ArrayList<Nutzer> zeigeAlleNutzer();
	
	 /**
	   * Einen Nutzer anlegen.
	   * 
	   * @return void
	   */
	
	public void nutzerAnlegen(Nutzer nutzer);
	
	 /**
	   * Einen Nutzer editieren.
	   * 
	   * @param nutzer
	   * @return void
	   */
	
	public void nutzerEditieren(Nutzer nutzer);
	
	 /**
	   * Einen Nutzer löschen.
	   * 
	   * @param nutzer
	   * @return void
	   */
	
	public void nutzerLoeschen(Nutzer nutzer);
	
	 /**
	   * Einen Nutzer bei Login überprüfen.
	   * 
	   * @param nutzerName
	   * @param passWort
	   * @return Nutzer
	   */
	
	public Nutzer loginCheck(String nutzerName, String passWort);
	
	 /**
	   * Einen Nutzer suchen.
	   * 
	   * @param id
	   * @return Nutzer
	   */
	
	public Nutzer sucheNutzerID(int id);
	
	 /**
	   * Einen Nutzer updaten.
	   * 
	   * @param nutzer
	   * @return Nutzer
	   */
	
	public Nutzer updateNutzer(Nutzer nutzer);
	
	 /**
	   * Den Nicknamen eines Nutzer suchen.
	   * 
	   * @param nickName
	   * @return boolean
	   */
	
	public boolean sucheNickName(String nickName);
	
	//Von Domi
	 /**
	   * Einen Nutzer nach Nicknamen suchen.
	   * 
	   * @param nickName
	   * @return Nutzer
	   */
	
	public Nutzer getNutzerAnhandNickname(String nickName);
	
	 /**
	   * Alle Pinnwände anzeigen.
	   * 
	   * @return ArrayList<Pinnwand>
	   */
	
	public ArrayList<Pinnwand> zeigeAllePinnwaende();
	
	//Beitrag
	
	 /**
	   * Einen Beitrag erstellen.
	   * 
	   * @param beitrag
	   * @return void
	   */
	
	public void textBeitragErstellen(Beitrag beitrag);
	
	 /**
	   * Alle Beiträge anzeigen.
	   * 
	   * @param id
	   * @return ArrayList<Beitrag>
	   */
	
	public ArrayList<Beitrag> findeAlleUserBeitraege(int id);
	
	 /**
	   * Einen Beitrag löschen.
	   * 
	   * @param Beitrag
	   * @return void
	   */
	
	public void textBeitragLoeschen(Beitrag beitrag);
	
	 /**
	   * Einen Beitrag bearbeiten.
	   * 
	   * @param beitrag
	   * @return Beitrag
	   */
	
	public Beitrag textBeitragBearbeiten(Beitrag beitrag);
	
	 /**
	   * Alle Beiträge per Pinnwand anzeigen.
	   * 
	   * @param id
	   * @return ArrayList<Beitrag>
	   */
	
	public ArrayList<Beitrag> sucheBeitragPerPinnwand(int id);
	
	 /**
	   * Alle Beiträge per Pinnwand anzeigen.
	   * 
	   * @param id
	   * @return ArrayList<Beitrag>
	   */
	
	public ArrayList<Beitrag> alleBeitraegePerPinnwand(int id);
	
	//Abo
	
	 /**
	   * Alle Abonnements eines Nutzers anzeigen.
	   * 
	   * @param id
	   * @return ArrayList<Abonnement>
	   */
	
	public ArrayList<Abonnement> zeigeAlleAbosPerNutzer(int id);
	
	//Abo von Domi
	
	 /**
	   * Ein Abonnement abschließen.
	   * 
	   * @param AbonnentID
	   * @param PinnwandID
	   * @return Abonnement
	   */
	
	public Abonnement abonnementAnlegen(int AbonnentID, int PinnwandID);
	
	 /**
	   * Ein Abonnement löschen.
	   * 
	   * @param a
	   * @return void
	   */
	
	public void abonnementLoeschen(Abonnement a);
	
	//Kommentar
	
	 /**
	   * Ein Kommentar erstellen.
	   * 
	   * @param kommentar
	   * @return void
	   */
	
	public void kommentarErstellen(Kommentar kommentar);
	
	 /**
	   * Alle Kommentare ausgeben.
	   * 
	   * @param id
	   * @return ArrayList<Kommentar>
	   */
	
	public ArrayList<Kommentar> findeAlleKommentare(int id);
	
	 /**
	   * Ein Kommentar löschen.
	   * 
	   * @param kommentar
	   * @return void
	   */
	
	public void kommentarLoeschen(Kommentar kommentar);
	
	 /**
	   * Ein Kommentar bearbeiten.
	   * 
	   * @param kommentar
	   * @return Kommentar
	   */
	
	public Kommentar kommentarBearbeiten(Kommentar kommentar);
	
	//Like
	
	 /**
	   * Einen Beitrag liken.
	   * 
	   * @param like
	   * @param beitrag
	   * @return void
	   */
	
	public void likeAnlegen(Like like, Beitrag beitrag);
	
	 /**
	   * Einen Beitrag auf Likes überprüfen.
	   * 
	   * @param nutzer
	   * @param beitrag
	   * @return boolean
	   */
	
	public boolean likeCheck(Nutzer nutzer, Beitrag beitrag);
	
	 /**
	   * Ein Like von einem Beitrag löschen.
	   * 
	   * @param beitrag
	   * @return void
	   */
	
	public void likeLoeschen(Beitrag beitrag);
	
	 /**
	   * Ein Like suchen.
	   * 
	   * @param id
	   * @return Like
	   */
	
	public Like likeSuchen(int id);
	
	 /**
	   * Likes zählen.
	   * 
	   * @param beitrag
	   * @return int
	   */
	
	public int likeZaehlen(Beitrag beitrag);
	
	 /**
	   * Ein einzelnes Like löschen.
	   * 
	   * @param beitrag
	   * @param nutzer
	   * @return void
	   */
	
	public void einzelnesLikeLoeschen(Beitrag beitrag, Nutzer nutzer);
	

}
