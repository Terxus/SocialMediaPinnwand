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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Timestamp aktuellesDatum; //Von Domi

	@Override
	public ArrayList<Nutzer> zeigeAlleNutzer() {
		// TODO Auto-generated method stub
		return NutzerMapper.nutzerMapper().sucheAlleNutzer();
	}
	@Override
	public void nutzerAnlegen(Nutzer nutzer) {
		NutzerMapper.nutzerMapper().nutzerAnlegen(nutzer);
	}
	@Override
	public void nutzerEditieren(Nutzer nutzer) {
		// TODO Auto-generated method stub
	}
	@Override
	public void nutzerLoeschen(Nutzer nutzer) {
		NutzerMapper.nutzerMapper().nutzerLoeschen(nutzer);
	}
	@Override
	public ArrayList<Pinnwand> zeigeAllePinnwaende() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void textBeitragErstellen(Beitrag beitrag) {
		BeitragMapper.beitragMapper().textBeitragErstellen(beitrag);
	}
	@Override
	public Nutzer loginCheck(String nutzerName, String passWort) {
		return NutzerMapper.nutzerMapper().loginCheckerNutzer(nutzerName, passWort);
	}

	@Override
	public Nutzer sucheNutzerID(int id) {
		return NutzerMapper.nutzerMapper().sucheNutzerID(id);
	}
	@Override
	public Nutzer updateNutzer(Nutzer nutzer) {
		return NutzerMapper.nutzerMapper().updateNutzer(nutzer);
	}
	@Override
	public ArrayList<Beitrag> findeAlleUserBeitraege(int id) {
		return BeitragMapper.beitragMapper().findeAlleUserBeitraege(id);
	}
	@Override
	public void textBeitragLoeschen(Beitrag beitrag) {
		BeitragMapper.beitragMapper().textBeitragLoeschen(beitrag);
	}
	@Override
	public Beitrag textBeitragBearbeiten(Beitrag beitrag) {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().updateBeitrag(beitrag);
	}
	@Override
	public ArrayList<Abonnement> zeigeAlleAbosPerNutzer(int id) {
		// TODO Auto-generated method stub
		return AbonnementMapper.abonnementMapper().getAboByNutzer(id);
	}
	@Override
	public void kommentarErstellen(Kommentar kommentar) {
		KommentarMapper.kommentarMapper().kommentarErstellen(kommentar);
		
	}
	@Override
	public ArrayList<Kommentar> findeAlleKommentare(int id) {
		// TODO Auto-generated method stub
		return KommentarMapper.kommentarMapper().findeDurchId(id);
	}
	@Override
	public ArrayList<Beitrag> sucheBeitragPerPinnwand(int id) {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().getBeitragByPinnwand(id);
	}
	
	//Von Domi
	
	@Override
	public Nutzer getNutzerAnhandNickname(String nickname) {
		return NutzerMapper.nutzerMapper().getNutzerAnhandNickname(nickname);
	}
	@Override
	public Nutzer getUserByNickname(String nickname) {
		return NutzerMapper.nutzerMapper().findByNickname(nickname);
	}

	@Override
	public Abonnement abonnementAnlegen(int AbonnentID, int PinnwandID) {
		Abonnement a = new Abonnement();
		a.setNutzerId(AbonnentID);
		a.setPinnwandId(PinnwandID);
		a.setErstellZeitpunkt(aktuellesDatum = new Timestamp(System.currentTimeMillis()));
		return AbonnementMapper.abonnementMapper().abonnementAnlegen(a);
	}

	public void abonnementLoeschen(Abonnement a) throws IllegalArgumentException{
		AbonnementMapper.abonnementMapper().abonnementLoeschen(a);
	}
	@Override
	public ArrayList<Beitrag> alleBeitraegePerPinnwand(int id) {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().sucheBeitragPerPinnwand(id);
	}
	@Override
	public void likeAnlegen(Like like, Beitrag beitrag) {
		// TODO Auto-generated method stub
		LikeMapper.likeMapper().anlegen(like, beitrag);
	}
	@Override
	public void kommentarLoeschen(Kommentar kommentar) {
		KommentarMapper.kommentarMapper().kommentarLoeschen(kommentar);
	}
	@Override
	public Kommentar kommentarBearbeiten(Kommentar kommentar) {
		// TODO Auto-generated method stub
		return KommentarMapper.kommentarMapper().kommentarBearbeiten(kommentar);
	}
	@Override
	public boolean likeCheck(Nutzer nutzer, Beitrag beitrag) {
		// TODO Auto-generated method stub
		return LikeMapper.likeMapper().likeUeberpruefung(nutzer, beitrag);
	}
	@Override
	public void likeLoeschen(Beitrag beitrag) {
		LikeMapper.likeMapper().loeschen(beitrag);
	}
	@Override
	public Like likeSuchen(int id) {
		return LikeMapper.likeMapper().findeEinzelneDurchID(id);
	}
	@Override
	public int likeZaehlen(Beitrag beitrag) {
		// TODO Auto-generated method stub
		return LikeMapper.likeMapper().zaehleAlleLikesProBeitrag(beitrag);
	}
}
