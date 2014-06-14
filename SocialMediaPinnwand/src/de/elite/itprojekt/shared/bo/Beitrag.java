package de.elite.itprojekt.shared.bo;

import java.util.Comparator;

/**
 * Diese Klasse bildet die Beiträge ab, die ein Nutzer auf seiner Pinnwand erscheinen lasse kann.
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class Beitrag extends Textbeitrag {


	public Beitrag() {
	}

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ein Beitrag besteht aus folgenden Werten:
	 * Die NutzerID: Ein Beitrag enthält die NutzerID, das ist die ID von dem jeweiligen Nutzer der einen Beitrag erstellt hat.
	 * Die LikeID: Ein Beitrag kann geliked werden, um diese Beziehung herzustellen benötigt er auch eine unique LikeID.
	 * Eine Likeanzahl und eine Kommentaranzahl - für Reports.
	 */
	
	private int nutzerId, likeId, likeAnzahl, kommentarAnzahl;
	
	/**
	 * Und letztendlich die Verbindung zwischen Pinnwand und Beitrag.
	 */
	
	private Pinnwand pinnwand;

	/**
	 * Auslesen der NutzerID des Beitrags.
	 * @return Die NutzerID des Beitrags.
	 */
	
	public int getNutzerId() {
		return nutzerId;
	}
	
	/**
	 * Anlegen der NutzerID.
	 * @param nutzerId
	 */
	
	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}
	
	/**
	 * Auslesen der LikeID.
	 * @return LikeID eines Beitrags.
	 */
	
	public int getLikeId() {
		return likeId;
	}
	
	/**
	 * Setzen der LikeID eines Beitrags.
	 * @param likeId
	 */
	
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	
	/**
	 * Ausgabe der Pinnwand.
	 * @return Pinnwandzuordnung des Beitrags wird ausgegeben.
	 */
	
	public Pinnwand getPinnwand() {
		return pinnwand;
	}
	
	/**
	 * Zuordnung des Beitrags.
	 * @param pinnwand
	 */
	
	public void setPinnwand(Pinnwand pinnwand) {
		this.pinnwand = pinnwand;
	}
	
	/**
	 * Anzahl der Likes zu einem Beitrag.
	 * @return Gesamtanzahl der Likes zu einem Beitrag als Ganzzahl.
	 */
	
	public int getLikeAnzahl() {
		return likeAnzahl;
	}
	
	/**
	 * Setzen der Gesamtanzahl der Likes.
	 * @param likeAnzahl
	 */
	
	public void setLikeAnzahl(int likeAnzahl) {
		this.likeAnzahl = likeAnzahl;
	}
	
	/**
	 * Anzahl der Kommentare zu einem Beitrag als Ganzzahl.
	 * @return Gesamtanzahl der Kommentare zu einem Beitrag als Ganzzahl.
	 */
	
	public int getKommentarAnzahl() {
		return kommentarAnzahl;
	}
	
	/**
	 * Setzen der Gesamtzahl der Kommentare.
	 * @param kommentarAnzahl
	 */
	
	public void setKommentarAnzahl(int kommentarAnzahl) {
		this.kommentarAnzahl = kommentarAnzahl;
	}
	
	/**
	 * Diese Methode vergleicht jeweils unterschiedliche Beiträge, und die Gesamtanzahl der Likes.
	 * @return
	 */
	
	public static Comparator<Beitrag> sortiereLikes() {
		return new Comparator<Beitrag>() {

			@Override
			public int compare(Beitrag b1, Beitrag b2) {
				return Integer.compare(b2.getLikeAnzahl(), b1.getLikeAnzahl());
			}
		};
	}
	
	/**
	 * Diese Methode vergleicht jeweils unterschiedliche Beiträge, und die Gesamtanzahl der Kommentare.
	 * @return
	 */
	
	public static Comparator<Beitrag> sortiereKommentare() {
		return new Comparator<Beitrag>() {

			@Override
			public int compare(Beitrag b1, Beitrag b2) {
				return Integer.compare(b2.getKommentarAnzahl(),
						b1.getKommentarAnzahl());
			}
		};
	}
}
