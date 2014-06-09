package de.elite.itprojekt.shared.bo;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Beitrag extends Textbeitrag  implements IsSerializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nutzerId;
	private static ArrayList<Kommentar> kommentarListe;
	private ArrayList<Like> likeList;
	private static Nutzer nutzer;
	
	public int getNutzerId() {
		return nutzerId;
	}
	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}
	public ArrayList<Like> getLikeList() {
		return likeList;
	}


	public void setLikeList(ArrayList<Like> likeList) {
		this.likeList = likeList;
	}


	public static ArrayList<Kommentar> getKommentarListe() {
		return kommentarListe;
	}

	public void setKommentarListe(ArrayList<Kommentar> kommentarListe) {
		Beitrag.kommentarListe = kommentarListe;
	}


	public static Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		Beitrag.nutzer = nutzer;
	}


}
