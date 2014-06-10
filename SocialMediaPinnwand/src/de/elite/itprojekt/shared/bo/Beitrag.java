package de.elite.itprojekt.shared.bo;

import java.util.ArrayList;


public class Beitrag extends Textbeitrag {

	public Beitrag() {}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nutzerId, likeId, likeAnzahl, kommentarAnzahl;
	private Pinnwand pinnwand;
	
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
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public Pinnwand getPinnwand() {
		return pinnwand;
	}
	public void setPinnwand(Pinnwand pinnwand) {
		this.pinnwand = pinnwand;
	}
	public void setKommentarListe(ArrayList<Kommentar> findeDurchId) {
		// TODO Auto-generated method stub
		
	}
	public void setLikeList(ArrayList<Like> findeDurchId) {
		// TODO Auto-generated method stub
		
	}
	public int getLikeAnzahl() {
		return likeAnzahl;
	}
	public void setLikeAnzahl(int likeAnzahl) {
		this.likeAnzahl = likeAnzahl;
	}
	public int getKommentarAnzahl() {
		return kommentarAnzahl;
	}
	public void setKommentarAnzahl(int kommentarAnzahl) {
		this.kommentarAnzahl = kommentarAnzahl;
	}
}
