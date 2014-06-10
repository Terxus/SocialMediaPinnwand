package de.elite.itprojekt.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.elite.itprojekt.server.db.AbonnementMapper;
import de.elite.itprojekt.server.db.BeitragMapper;
import de.elite.itprojekt.server.db.KommentarMapper;
import de.elite.itprojekt.server.db.LikeMapper;
import de.elite.itprojekt.server.db.NutzerMapper;
import de.elite.itprojekt.shared.ReportGenerator;

public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ReportGeneratorImpl() throws IllegalArgumentException {
		
	}
	
	public void init() throws IllegalArgumentException {

	}

	@Override
	public void setNutzer() {
		// TODO Auto-generated method stub
	}

	@Override
	public void nutzerReport() {
		// TODO Auto-generated method stub
	}

	@Override
	public void likeReport() {
		// TODO Auto-generated method stub
	}

	@Override
	public void kommentarReport() {
		// TODO Auto-generated method stub
	}

	@Override
	public void likeReportSortiert() {
		// TODO Auto-generated method stub
	}

	@Override
	public void kommentarReportSortiert() {
		// TODO Auto-generated method stub
	}
	/////BALBLALBALFB

	@Override
	public int zaehleAlleNutzer() {
		return NutzerMapper.nutzerMapper().zaehleNutzer();
		
	}

	@Override
	public int zaehleAlleKommentare() {
		// TODO Auto-generated method stub
		return KommentarMapper.kommentarMapper().zaehleKommentare();
	}

	@Override
	public int zaehleAlleLikes() {
		// TODO Auto-generated method stub
		return LikeMapper.likeMapper().zaehleLikes();
	}

	@Override
	public int zaehleAlleBeitraege() {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().zaehleBeitraege();
	}

	@Override
	public int zaehleAlleAbonnements() {
		// TODO Auto-generated method stub
		return AbonnementMapper.abonnementMapper().zaehleAbonnements();
	}

}
