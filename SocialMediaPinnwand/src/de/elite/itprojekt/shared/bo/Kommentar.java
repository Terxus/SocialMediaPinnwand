package de.elite.itprojekt.shared.bo;

public class Kommentar extends Textbeitrag {

	/**
	 * 
	 */
	public Kommentar() {}
	
	private static final long serialVersionUID = 1L;
	private int nutzerId, beitragId;
	private Nutzer nutzer;
	private Beitrag beitrag;
	
	public int getNutzerId() {
		return nutzerId;
	}
	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}
	public Nutzer getNutzer() {
		return nutzer;
	}
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
	public Beitrag getBeitrag() {
		return beitrag;
	}
	public void setBeitrag(Beitrag beitrag) {
		this.beitrag = beitrag;
	}
	public int getBeitragId() {
		return beitragId;
	}
	public void setBeitragId(int beitragId) {
		this.beitragId = beitragId;
	}
	
	public String toString() {
		return super.toString() + " " + this.getID() + " " + this.nutzerId + " " + this.beitragId + " " + this.nutzer.getNachname() + " " + this.beitrag.getText();
		
	}
	
	

}
