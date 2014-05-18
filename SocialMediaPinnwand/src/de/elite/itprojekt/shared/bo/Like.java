package de.elite.itprojekt.shared.bo;

public class Like extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pinnwandId, nutzerId;
	
	public int getPinnwandId() {
		return pinnwandId;
	}
	public void setPinnwandId(int pinnwandId) {
		this.pinnwandId = pinnwandId;
	}
	public int getNutzerId() {
		return nutzerId;
	}
	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}
	

	
}
