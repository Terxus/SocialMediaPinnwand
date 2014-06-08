package de.elite.itprojekt.shared.bo;

public class Abonnement extends BusinessObject {

	/**
	 * @author Benjamin Auwärter
	 */
	
	public Abonnement() {}
	
	private static final long serialVersionUID = 1L;
	private int nutzerId, pinnwandId;
	private Nutzer nutzer;
	private Pinnwand pinnwand;
	
	public int getNutzerId() {
		return nutzerId;
	}
	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}
	public int getPinnwandId() {
		return pinnwandId;
	}
	public void setPinnwandId(int pinnwandId) {
		this.pinnwandId = pinnwandId;
	}
	public Nutzer getNutzer() {
		return nutzer;
	}
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
	public Pinnwand getPinnwand() {
		return pinnwand;
	}
	public void setPinnwand(Pinnwand pinnwand) {
		this.pinnwand = pinnwand;
	}
	public String pinnwanduserstring() {
		return nutzer.getNickname();
	}
}
