package de.elite.itprojekt.server;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.elite.itprojekt.server.db.AbonnementMapper;
import de.elite.itprojekt.server.db.BeitragMapper;
import de.elite.itprojekt.server.db.KommentarMapper;
import de.elite.itprojekt.server.db.LikeMapper;
import de.elite.itprojekt.server.db.NutzerMapper;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Kommentar;
import de.elite.itprojekt.shared.bo.Like;
import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;

public class PinnwandVerwaltungImpl extends RemoteServiceServlet implements PinnwandVerwaltung {
	
	/**
	 * Diese Klasse ist die Implementierungsklasse des Interfaces PinnwandVerwaltung. Enthalten sind alle Methoden die von der GUI benötigt werden. Diese Klasse enthält die entsprechende Applikationslogik und greift auf die verschiedenen Mapper zu.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * 
	 *
	 */

	private static final long serialVersionUID = 1L;
	Timestamp aktuellesDatum; 

	/**
	 * Diese Methode liefert alle Nutzer im System. Die Nutzer-Objekte werden als ArrayList zurückgegeben. 
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return ArrayList<Nutzer>
	 * 
	 */
	@Override
	public ArrayList<Nutzer> zeigeAlleNutzer() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return NutzerMapper.nutzerMapper().sucheAlleNutzer();
	}
	/**
	 * Diese Methode legt einen neuen Nutzer im System an.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Nutzer nutzer
	 * @return void
	 * 
	 */
	@Override
	public void nutzerAnlegen(Nutzer nutzer) throws IllegalArgumentException {
		NutzerMapper.nutzerMapper().nutzerAnlegen(nutzer);
	}
	/**
	 * Diese Methode erlaubt es einen bestehenden Nutzer zu editieren. (Vorname, Nachname, Email, Passwort) 
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Nutzer nutzer
	 * @return void
	 * 
	 */
	@Override
	public void nutzerEditieren(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
	}
	/**
	 * Diese Methode löscht einen bestehenden Nutzer komplett aus dem System.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Nutzer nutzer
	 * @return void
	 * 
	 */
	@Override
	public void nutzerLoeschen(Nutzer nutzer) throws IllegalArgumentException {
		NutzerMapper.nutzerMapper().nutzerLoeschen(nutzer);
	}
	/**
	 * Diese Methode liefert alle existierenden Pinnwände. Die Pinnwand-Objekte werden als ArrayList zurückgegeben. 
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return ArrayList<Pinnwand>
	 * 
	 */
	@Override
	public ArrayList<Pinnwand> zeigeAllePinnwaende() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Durch den Aufruf dieser Methode lästt sich ein neuer Textbeitrag auf der Pinnwand erstellen.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Beitrag beitrag
	 * @return void
	 * 
	 */
	@Override
	public void textBeitragErstellen(Beitrag beitrag) throws IllegalArgumentException {
		BeitragMapper.beitragMapper().textBeitragErstellen(beitrag);
	}
	/**
	 * Diese Methode überprüft beim Anmelden ob der eingegebene Nutzername und das zugehörtige Passwort korrekt sind.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param String nutzerName, String passWort
	 * @return Nutzer
	 * 
	 */
	@Override
	public Nutzer loginCheck(String nutzerName, String passWort) throws IllegalArgumentException {
		return NutzerMapper.nutzerMapper().loginCheckerNutzer(nutzerName, passWort);
	}
	/**
	 * Diese Methode sucht einen Nutzer anhand einer bestimmten ID und gibt das Nutzerobjekt anschließend zurück.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param int id
	 * @return Nutzer
	 * 
	 */
	@Override
	public Nutzer sucheNutzerID(int id) throws IllegalArgumentException {
		return NutzerMapper.nutzerMapper().sucheNutzerID(id);
	}
	/**
	 * Diese Methode erlaubt es einen bestehenden Nutzer zu editieren. (Vorname, Nachname, Email, Passwort) 
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Nutzer nutzer
	 * @return Nutzer
	 * 
	 */
	@Override
	public Nutzer updateNutzer(Nutzer nutzer) throws IllegalArgumentException {
		return NutzerMapper.nutzerMapper().updateNutzer(nutzer);
	}
	/**
	 * Diese Methode liefert alle existierenden Beiträge eines bestimmten Nutzers im System. Die Beiträge-Objekte werden als ArrayList zurückgegeben.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param int id
	 * @return ArrayList<Beitrag>
	 * 
	 */
	@Override
	public ArrayList<Beitrag> findeAlleUserBeitraege(int id) throws IllegalArgumentException {
		return BeitragMapper.beitragMapper().findeAlleUserBeitraege(id);
	}
	/**
	 * Diese Methode löscht einen einzelnen Beitrag aus der Datenbank.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Beitrag beitrag
	 * @return void
	 * 
	 */
	@Override
	public void textBeitragLoeschen(Beitrag beitrag) throws IllegalArgumentException {
		BeitragMapper.beitragMapper().textBeitragLoeschen(beitrag);
	}
	/**
	 * Diese Methode erlaubt es einen bestimmten Beitrag zu editieren und anschließend in die Datenbank zu speichern.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Beitrag beitrag
	 * @return Beitrag
	 * 
	 */
	@Override
	public Beitrag textBeitragBearbeiten(Beitrag beitrag) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().updateBeitrag(beitrag);
	}
	/**
	 * Diese Methode liefert alle existierenden Abonnementbeziehungen eines bestimmten Nutzers im System. Die Abonnement-Objekte werden als ArrayList zurückgegeben.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param int id
	 * @return ArrayList<Abonnement> 
	 * 
	 */
	@Override
	public ArrayList<Abonnement> zeigeAlleAbosPerNutzer(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return AbonnementMapper.abonnementMapper().getAboByNutzer(id);
	}
	/**
	 * Diese Methode erlaubt es ein Kommentar zu erstellen und in der Datenbank abzuspeichern.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Kommentar kommentar
	 * @return void 
	 * 
	 */
	@Override
	public void kommentarErstellen(Kommentar kommentar) throws IllegalArgumentException {
		KommentarMapper.kommentarMapper().kommentarErstellen(kommentar);
		
	}
	/**
	 * Diese Methode liefert alle existierenden Kommentare zu einem bestimmten Beitrag. Die Kommentar-Objekte werden als ArrayList zurückgegeben.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param int id
	 * @return ArrayList<Kommentar> 
	 * 
	 */
	@Override
	public ArrayList<Kommentar> findeAlleKommentare(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return KommentarMapper.kommentarMapper().findeDurchId(id);
	}
	/**
	 * Diese Methode liefert alle existierenden Beiträge zu einer bestimmten Pinnwand. Die Beitrag-Objekte werden als ArrayList zurückgegeben.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param int id
	 * @return ArrayList<Beitrag> 
	 * 
	 */
	@Override
	public ArrayList<Beitrag> sucheBeitragPerPinnwand(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().getBeitragByPinnwand(id);
	}
	/**
	 * Diese Methode sucht nach einem bestimmten Nutznamen in der Datenbank. Sie wird benötigt um Nutzer abonnieren zu können
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param String nickname
	 * @return Nutzer
	 * 
	 */
	@Override
	public Nutzer getNutzerAnhandNickname(String nickname) throws IllegalArgumentException {
		return NutzerMapper.nutzerMapper().getNutzerAnhandNickname(nickname);
	}
	/**
	 * Diese Methode legt eine Abonnementbeziehung an.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param int AbonnentID, int PinnwandID
	 * @return Abonnement
	 * 
	 */
	@Override
	public Abonnement abonnementAnlegen(int AbonnentID, int PinnwandID) throws IllegalArgumentException {
		Abonnement a = new Abonnement();
		a.setNutzerId(AbonnentID);
		a.setPinnwandId(PinnwandID);
		a.setErstellZeitpunkt(aktuellesDatum = new Timestamp(System.currentTimeMillis()));
		return AbonnementMapper.abonnementMapper().abonnementAnlegen(a);
	}
	/**
	 * Diese Methode löscht eine Abonnementbeziehung.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Abonnement a
	 * @return void
	 * 
	 */
	public void abonnementLoeschen(Abonnement a) throws IllegalArgumentException {
		AbonnementMapper.abonnementMapper().abonnementLoeschen(a);
	}
	/**
	 * Diese Methode liefert alle existierenden Beiträge zu einer bestimmten Pinnwand. Die Beitrag-Objekte werden als ArrayList zurückgegeben.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param int id
	 * @return ArrayList<Beitrag> 
	 * 
	 */
	@Override
	public ArrayList<Beitrag> alleBeitraegePerPinnwand(int id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().sucheBeitragPerPinnwand(id);
	}
	@Override
	/**
	 * Diese Methode lässt den Nutzer einen bestimmten Beitrag liken.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Like like, Beitrag beitrag
	 * @return void
	 * 
	 */
	public void likeAnlegen(Like like, Beitrag beitrag) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		LikeMapper.likeMapper().anlegen(like, beitrag);
	}
	/**
	 * Diese Methode löscht einen bestimmten Kommentar aus der Datenbank.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Kommentar kommentar
	 * @return void
	 * 
	 */
	@Override
	public void kommentarLoeschen(Kommentar kommentar) throws IllegalArgumentException {
		KommentarMapper.kommentarMapper().kommentarLoeschen(kommentar);
	}
	/**
	 * Diese Methode ermöglicht es einen bestimmten Kommentar zu editieren und in der Datenbank zu speichern.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Kommentar kommentar
	 * @return Kommentar
	 * 
	 */
	@Override
	public Kommentar kommentarBearbeiten(Kommentar kommentar) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return KommentarMapper.kommentarMapper().kommentarBearbeiten(kommentar);
	}
	/**
	 * Diese Methode überprüft ob ein Nutzer Beiträge geliked hat oder nicht. Dies ist wichtig da festgelegt werden muss ob der Beitrag noch geliked werden kann oder deliked.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Nutzer nutzer, Beitrag beitrag
	 * @return boolean
	 * 
	 */
	@Override
	public boolean likeCheck(Nutzer nutzer, Beitrag beitrag) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return LikeMapper.likeMapper().likeUeberpruefung(nutzer, beitrag);
	}
	/**
	 * Diese Methode löscht einen Like zu einem bestimmten Beitrag aus der Datenbank.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Beitrag beitrag
	 * @return void
	 * 
	 */
	@Override
	public void likeLoeschen(Beitrag beitrag) throws IllegalArgumentException {
		LikeMapper.likeMapper().loeschen(beitrag);
	}
	/**
	 * Diese Methode sucht einen Like anhand einer ID.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param int id
	 * @return Like
	 * 
	 */
	@Override
	public Like likeSuchen(int id) throws IllegalArgumentException {
		return LikeMapper.likeMapper().findeEinzelneDurchID(id);
	}
	/**
	 * Diese Methode ermttelt wieviele Likes ein bestimmter Beitrag erhalten hat.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Beitrag beitrag
	 * @return int
	 * 
	 */
	@Override
	public int likeZaehlen(Beitrag beitrag) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return LikeMapper.likeMapper().zaehleAlleLikesProBeitrag(beitrag);
	}
	/**
	 * Diese Methode lässt einen Like zu einem bestimmten Beitrag löschen.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param Beitrag beitrag, Nutzer nutzer
	 * @return void
	 * 
	 */
	@Override
	public void einzelnesLikeLoeschen(Beitrag beitrag, Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		LikeMapper.likeMapper().einzelnesLikeLoeschen(beitrag, nutzer);
	}
	@Override
	/**
	 * Diese Methode sucht nach einem bestimmten Nutznamen in der Datenbank.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @param String nickName
	 * @return boolean
	 * 
	 */
	public boolean sucheNickName(String nickName) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return NutzerMapper.nutzerMapper().sucheNickName(nickName);
	}
}
