package de.elite.itprojekt.shared.bo;

public class Kommentar extends Textbeitrag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nutzerId;
	
	public int getNutzerId() {
		return nutzerId;
	}
	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}

}
