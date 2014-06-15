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

	/**
	 * Diese Klasse ist die Implementierungsklasse des Interfaces ReportGenerator. Enthalten sind alle Methoden die von der GUI benötigt werden. Diese Klasse enthält die entsprechende Applikationslogik und greift auf die verschiedenen Mapper zu.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * 
	 *
	 */

	private static final long serialVersionUID = 1L;
	
	public ReportGeneratorImpl() throws IllegalArgumentException {
		
	}
	
	public void init() throws IllegalArgumentException {

	}
	
	/**
	 * Diese Methode liefert alle Nutzer im System. Alle Tupel die in der DB <b>Nutzer</b> enthalten sind,
	 * werden gezählt und als Ganzzahl zurückgeliefert.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return int
	 * 
	 */

	@Override
	public int zaehleAlleNutzer() throws IllegalArgumentException {
		return NutzerMapper.nutzerMapper().zaehleNutzer();
	}

	/**
	 * Diese Methode liefert alle Kommentare im System. Alle Tupel die in der DB <b>Kommentare</b> enthalten sind,
	 * werden gezählt und als Ganzzahl zurückgeliefert.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return int
	 * 
	 */
	
	@Override
	public int zaehleAlleKommentare() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return KommentarMapper.kommentarMapper().zaehleKommentare();
	}

	/**
	 * Diese Methode liefert alle Likes im System. Alle Tupel die in der DB <b>Likee</b> enthalten sind,
	 * werden gezählt und als Ganzzahl zurückgeliefert.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return int
	 * 
	 */
	
	@Override
	public int zaehleAlleLikes() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return LikeMapper.likeMapper().zaehleLikes();
	}
	
	/**
	 * Diese Methode liefert alle Beiträge im System. Alle Tupel die in der DB <b>Beitrag</b> enthalten sind,
	 * werden gezählt und als Ganzzahl zurückgeliefert.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return int
	 * 
	 */

	@Override
	public int zaehleAlleBeitraege() {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().zaehleBeitraege();
	}
	
	/**
	 * Diese Methode liefert alle Abonnements im System. Alle Tupel die in der DB <b>Abonnement</b> enthalten sind,
	 * werden gezählt und als Ganzzahl zurückgeliefert.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return int
	 * 
	 */

	@Override
	public int zaehleAlleAbonnements() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return AbonnementMapper.abonnementMapper().zaehleAbonnements();
	}
	
	/**
	 * Diese Methode gibt alle registrierten Nutzer in einer Liste aus. Sie wird im Report benötigt für die Suggestbox.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return ArrayList<Nutzer>
	 */

	@Override
	public ArrayList<Nutzer> zeigeAlleNutzer() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return NutzerMapper.nutzerMapper().sucheAlleNutzer();
	}
	
	/**
	 * Diese Methode gibt ein Nutzer-Objekt anhand seines Nicknamens zurück.
	 * werden gezählt und als Ganzzahl zurückgeliefert.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return Nutzer
	 * 
	 */

	@Override
	public Nutzer getUserByNickname(String nickname) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return NutzerMapper.nutzerMapper().getNutzerAnhandNickname(nickname);
	}

	/**
	 * Diese Methode zählt alle Abonnements eines Nutzers und gibt sie als Ganzzahl zurück.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return int
	 * 
	 */
	
	@Override
	public int zaehleAlleAbonnementsPerNutzer(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return AbonnementMapper.abonnementMapper().zaehleAbonnementsPerNutzer(nutzer);
	}
	
	/**
	 * Diese Methode zählt alle Beiträge eines Nutzers und gibt sie als Ganzzahl zurück.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return int
	 * 
	 */

	@Override
	public int zaehleBeitraegePerNutzer(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().zaehleBeitraegePerNutzer(nutzer);
	}
	
	/**
	 * Diese Methode zählt alle Likes eines Nutzers und gibt sie als Ganzzahl zurück.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return int
	 * 
	 */

	@Override
	public int zaehleLikesPerNutzer(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return LikeMapper.likeMapper().zaehleLikesPerNutzer(nutzer);
	}
	
	/**
	 * Diese Methode zählt alle Kommentare eines Nutzers und gibt sie als Ganzzahl zurück.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return int
	 * 
	 */

	@Override
	public int zaehleKommentarePerNutzer(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return KommentarMapper.kommentarMapper().zaehleKommentarePerNutzer(nutzer);
	}
	
	/**
	 * Diese Methode gibt alle Nutzer zwischen zwei bestimmen Daten zurück.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return ArrayList<Nutzer>
	 * 
	 */

	@Override
	public ArrayList<Nutzer> alleNutzerNachZeitraum(String von, String bis) throws IllegalArgumentException {
		return NutzerMapper.nutzerMapper().alleNutzerNachZeitraum(von, bis);
	}
	
	/**
	 * Diese Methode gibt alle Beiträge zwischen zwei bestimmten Daten zurück.
	 * 
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @return ArrayList<Nutzer>
	 * 
	 */
	
	@Override
	public ArrayList<Beitrag> alleBeitraegeNachZeitraum(String von, String bis) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return BeitragMapper.beitragMapper().alleBeitraegeNachZeitraum(von, bis);
	}

}
