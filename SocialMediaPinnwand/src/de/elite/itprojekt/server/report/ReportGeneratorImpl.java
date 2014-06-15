package de.elite.itprojekt.server.report;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.elite.itprojekt.server.db.AbonnementMapper;
import de.elite.itprojekt.server.db.BeitragMapper;
import de.elite.itprojekt.server.db.KommentarMapper;
import de.elite.itprojekt.server.db.LikeMapper;
import de.elite.itprojekt.server.db.NutzerMapper;
import de.elite.itprojekt.shared.ReportGenerator;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;

public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {


	private static final long serialVersionUID = 1L;
	
	public ReportGeneratorImpl() throws IllegalArgumentException {
		
	}
	
	public void init() throws IllegalArgumentException {

	}

	@Override
	public int zaehleAlleNutzer() throws IllegalArgumentException {
		return NutzerMapper.nutzerMapper().zaehleNutzer();
	}

	@Override
	public int zaehleAlleKommentare() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return KommentarMapper.kommentarMapper().zaehleKommentare();
	}

	@Override
	public int zaehleAlleLikes() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return LikeMapper.likeMapper().zaehleLikes();
	}

	@Override
	public int zaehleAlleBeitraege() {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().zaehleBeitraege();
	}

	@Override
	public int zaehleAlleAbonnements() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return AbonnementMapper.abonnementMapper().zaehleAbonnements();
	}

	@Override
	public ArrayList<Nutzer> zeigeAlleNutzer() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return NutzerMapper.nutzerMapper().sucheAlleNutzer();
	}

	@Override
	public Nutzer getUserByNickname(String nickname) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return NutzerMapper.nutzerMapper().getNutzerAnhandNickname(nickname);
	}

	@Override
	public int zaehleAlleAbonnementsPerNutzer(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return AbonnementMapper.abonnementMapper().zaehleAbonnementsPerNutzer(nutzer);
	}

	@Override
	public int zaehleBeitraegePerNutzer(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().zaehleBeitraegePerNutzer(nutzer);
	}

	@Override
	public int zaehleLikesPerNutzer(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return LikeMapper.likeMapper().zaehleLikesPerNutzer(nutzer);
	}

	@Override
	public int zaehleKommentarePerNutzer(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return KommentarMapper.kommentarMapper().zaehleKommentarePerNutzer(nutzer);
	}



	@Override
	public ArrayList<Nutzer> alleNutzerNachZeitraum(String von, String bis) throws IllegalArgumentException {
		return NutzerMapper.nutzerMapper().alleNutzerNachZeitraum(von, bis);
	}
	
	@Override
	public ArrayList<Beitrag> alleBeitraegeNachZeitraum(String von, String bis) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().alleBeitraegeNachZeitraum(von, bis);
	}

}
