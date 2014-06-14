package de.elite.itprojekt.shared.bo;

public class Abonnement extends BusinessObject {

	/**
	 * Diese Klasse bildet die Abonnements ab. Ein Abonnemnt ist eine Beziehung zwischen
	 * einer Pinnwand und einer anderen. Ein Nutzer erhält alle Beiträge des Nutzers, den
	 * er Abonniert hat.
	 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
	 * @version 1.0
	 */
	
	public Abonnement() {}
	
	private static final long serialVersionUID = 1L;
	private int nutzerId, pinnwandId;
	private Nutzer nutzer;
	private Pinnwand pinnwand;
	
	/**
	 * Auslesen der ID eines Abonnements.
	 * @return Die ID als Ganzzahl eines Abonnements.
	 */
	
	public int getNutzerId() {
		return nutzerId;
	}
	
	/**
	 * Anlegen einer NutzerID des Abonnements.
	 * @param nutzerId
	 */
	
	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}
	
	/**
	 * Auslesen der PinnwandwandID.
	 * @return Die PinnwandID ist in unserem Fall identisch mit der Nutzerid, da sie bei der Nutzerregistration gesetzt wird.
	 */
	
	public int getPinnwandId() {
		return pinnwandId;
	}
	
	/**
	 * Anlegen einer PinnwandID.
	 * @param pinnwandId
	 */
	
	public void setPinnwandId(int pinnwandId) {
		this.pinnwandId = pinnwandId;
	}
	
	/**
	 * Auslesen des Nutzerobjekts mit der Abonnementenbeziehung.
	 * @return Der jeweilige Nutzer der einem Abonnements zugeordnet ist.
	 */
	
	public Nutzer getNutzer() {
		return nutzer;
	}
	
	/**
	 * Der Nutzer wird mit seinen Attributen einem Abonnements zugeordnet.
	 * @param nutzer
	 */
	
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
	
	/**
	 * Auslesen des Pinnwandobjekts mit den Nutzerinformationen.
	 * @return Die Pinnwand die Informationen über den Nutzer enthält.
	 */
	
	public Pinnwand getPinnwand() {
		return pinnwand;
	}
	
	/**
	 * Zuordnung eines Abonnements zu einer Pinnwand.
	 * @param pinnwand
	 */
	
	public void setPinnwand(Pinnwand pinnwand) {
		this.pinnwand = pinnwand;
	}
	
	/**
	 * Wiedergabe des jeweiligen Pinnwandbesitzers.
	 * @return Ausgeben des Nicknamens als ein Stringobjekt.
	 */
	
	public String pinnwanduserstring() {
		return nutzer.getNickname();
	}
}
