package de.elite.itprojekt.shared.bo;

/**
 * Diese Klasse bildet die Kommentare als Objekte in unserem System ab. Kommentare können nur zu Beiträge geschrieben werden, und 
 * nicht geliked werden.
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class Kommentar extends Textbeitrag {

	/**
	 * 
	 */
	public Kommentar() {}
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ein Kommentar besitzt die NutzerID des Kommentators und die BeitragID des Beitragerstellers.
	 */
	
	private int nutzerId, beitragId;
	
	/**
	 * Zusätlich benötigt man die Informationen der Objekte eines Nutzers, und diese des Beitrags.
	 */
	
	private Nutzer nutzer;
	private Beitrag beitrag;
	
	/**
	 * Ausgabe der NutzerID.
	 * @return Die NutzerID als Ganzzahl.
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
	 * Ausgabe des Nutzerobjekts
	 * @return Das Objekt des Nutzers.
	 */
	
	public Nutzer getNutzer() {
		return nutzer;
	}
	
	/**
	 * Zuordnung des Nutzers zu einem Kommentar
	 * @param nutzer
	 */
	
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
	
	/**
	 * Ausgabe des Beitragobjekts.
	 * @return Die Informationen des jeweiligen Beitrags.
	 */
	
	public Beitrag getBeitrag() {
		return beitrag;
	}
	
	/**
	 * Zuordnung eines Beitrags.
	 * @param beitrag
	 */
	
	public void setBeitrag(Beitrag beitrag) {
		this.beitrag = beitrag;
	}
	
	/**
	 * Ausgabe der BeitragID.
	 * @return Die BeitragID eines Beitrags.
	 */
	
	public int getBeitragId() {
		return beitragId;
	}
	
	/**
	 * Zuordnung nur für die BeitragID.
	 * @param beitragId
	 */
	
	public void setBeitragId(int beitragId) {
		this.beitragId = beitragId;
	}
	
	/**
	 * toString Methode die alle Daten eines Kommentare ausgeben soll.
	 */
	
	public String toString() {
		return super.toString() + " " + this.getID() + " " + this.nutzerId + " " + this.beitragId + " " + this.nutzer.getNachname() + " " + this.beitrag.getText();
		
	}
	
	

}
