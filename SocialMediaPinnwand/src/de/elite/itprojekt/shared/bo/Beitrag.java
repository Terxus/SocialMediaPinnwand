package de.elite.itprojekt.shared.bo;

import java.util.ArrayList;

public class Beitrag extends Textbeitrag {

	public Beitrag() {}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nutzerId;
	
	private ArrayList<Like> likes;
	
	public ArrayList<Like> getLikes() {
		return likes;
	}
	public void setLikes(ArrayList<Like> likes) {
		this.likes = likes;
	}
	public ArrayList<Kommentar> getKommentare() {
		return kommentare;
	}
	public void setKommentare(ArrayList<Kommentar> kommentare) {
		this.kommentare = kommentare;
	}
	private ArrayList<Kommentar> kommentare;
	
	public int getNutzerId() {
		return nutzerId;
	}
	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}
}
