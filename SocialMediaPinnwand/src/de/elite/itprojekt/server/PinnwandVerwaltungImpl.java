package de.elite.itprojekt.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.elite.itprojekt.server.db.NutzerMapper;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;

public class PinnwandVerwaltungImpl extends RemoteServiceServlet implements PinnwandVerwaltung {
	
	
	@Override
	public void init() throws IllegalArgumentException {
		NutzerMapper.nutzerMapper();
	}

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated method stub
		
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

}
