package de.elite.itprojekt.shared.bo;

/**
 * Diese Klasse bildet die Pinnwände als Objekte in unserem System ab. Ein Nutzer der sich registriert bekommt automatisch eine Pinnwand
 * zugewiesen. Die PinnwandID und die NutzerID sind identisch.
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class Pinnwand extends BusinessObject {

	public Pinnwand() {}
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Eine Pinnwand besitzt eine NutzerID und eine AbonnementID.
	 */
	
	private int nutzerId, abonnementId;
	
	/**
	 * Zusätzlich wird der Pinnwand ein Nutzerobjekt gegeben.
	 */
	
	private Nutzer nutzer;
	
	/**
	 * Ausgabe der NutzerID.
	 * @return Die NutzerID zu der jeweiligen Pinnwand.
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
	 * Ausgabe der AbonnementID.
	 * @return Die AbonnementID als Ganzzahl.
	 */
	
	public int getAbonnementId() {
		return abonnementId;
	}
	
	/**
	 * Setzen der AbonnementID.
	 * @param abonnementId
	 */
	
	public void setAbonnementId(int abonnementId) {
		this.abonnementId = abonnementId;
	}
	
	/**
	 * Ausgabe des jeweiligen Nutzerobjekts.
	 * @return Der Nutzer dem die Pinnwand gehört.
	 */
	
	public Nutzer getNutzer() {
		return nutzer;
	}
	
	/**
	 * Zuordnung des Nutzers zu einer Pinnwand.
	 * @param nutzer
	 */
	
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
}
