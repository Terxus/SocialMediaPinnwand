package de.elite.itprojekt.shared.bo;

/**
 * Realisierung einer exemplarischen Klasse die Likes zu Beiträgen beschreibt.
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class Like extends BusinessObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Ein Like hat die Informationen: PinnwandID und eine NutzerID.
	 */
	
	private int pinnwandId, nutzerId;
	
	/**
	 * Ein Like benötigt ebenfalls die Informationen eines Nutzers.
	 */
	
	private Nutzer nutzer;
	
	/**
	 * Auslesen der ID einer Pinnwand.
	 * @return Die ID einer Pinnwand.
	 */
	
	public int getPinnwandId() {
		return pinnwandId;
	}
	
	/**
	 * Setzen einer PinnwandID.
	 * @param pinnwandId
	 */
	
	public void setPinnwandId(int pinnwandId) {
		this.pinnwandId = pinnwandId;
	}
	
	/**
	 * Auslesen der ID einer Pinnwand.
	 * @return Die ID eines Nutzers.
	 */
	
	public int getNutzerId() {
		return nutzerId;
	}
	
	/**
	 * Setzen der NutzerID.
	 * @param nutzerId
	 */
	
	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}
	
	/**
	 * Ausgabe der Nutzerobjekts.
	 * @return Der Nutzer der eine Likebeziehung mit einem Beitrag eingeht.
	 */
	
	public Nutzer getNutzer() {
		return nutzer;
	}
	
	/**
	 * Zuordnung eines Nutzers zu einem Likes.
	 * @param nutzer
	 */
	
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
}
