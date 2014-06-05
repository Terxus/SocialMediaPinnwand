package de.elite.itprojekt.shared.bo;

public class Pinnwand extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nutzerId, abonnementId;
	public int getNutzerId() {
		return nutzerId;
	}
	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}
	public int getAbonnementId() {
		return abonnementId;
	}
	public void setAbonnementId(int abonnementId) {
		this.abonnementId = abonnementId;
	}
}
