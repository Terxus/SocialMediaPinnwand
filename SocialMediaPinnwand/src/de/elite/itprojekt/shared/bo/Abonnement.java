package de.elite.itprojekt.shared.bo;

public class Abonnement extends BusinessObject {

	/**
	 * @author Benjamin Auwärter
	 */
	private static final long serialVersionUID = 1L;
	private int nutzerId, pinnwandId;
	
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
}
