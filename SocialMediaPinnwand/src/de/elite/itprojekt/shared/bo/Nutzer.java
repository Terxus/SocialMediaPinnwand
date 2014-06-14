package de.elite.itprojekt.shared.bo;

import java.util.Comparator;

/**
 * Realisierung einer exemplarischen Nutzerklasse. Diese Klasse enhält sämtliche Attribute eines Nutzers, die in unserem Pinnwandsystem
 * benötigt werden.
 * 
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @author In Anlehung an Prof. Dr. Thies
 * @version 1.0
 */

public class Nutzer extends BusinessObject implements Comparator<Nutzer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ein Nutzer hat einen Vornamen, Nachnamen, Nicknamen, Passwort und eine Email.
	 * Des Weiteren werden für den Reportgenerator Informationen gebraucht, wie die Anzahl der Likes eines Nutzers,
	 * und die Anzahl der Kommentare eines Nutzers.
	 */
	
	private String vorName, nachName, nickName, passWort, eMail;
	private int likeAnzahl, kommentarAnzahl;
	
	/**
	 * Ausgeben des Passworts.
	 * @return Das Passwort des jeweiligen Nutzers.
	 */
	
	public String getPassWort() {
		return passWort;
	}
	
	/**
	 * Setzen des Passworts.
	 * @param passWort
	 */
	
	public void setPassWort(String passWort) {
		this.passWort = passWort;
	}
	
	/**
	 * Ausgeben des Vornamens.
	 * @return Der Vorname des jeweiligen Nutzers.
	 */
	
	public String getVorname() {
		return vorName;
	}
	
	/**
	 * Setzen des Vornamens.
	 * @param vorName
	 */
	
	public void setVorname(String vorName) {
		this.vorName = vorName;
	}
	
	/**
	 * Ausgeben des Nachnamens.
	 * @return Der Nachname des jeweiligen Nutzers.
	 */
	
	public String getNachname() {
		return nachName;
	}
	
	/**
	 * Setzen des Nachnamens.
	 * @param nachName
	 */
	
	public void setNachname(String nachName) {
		this.nachName = nachName;
	}
	
	/**
	 * Ausgeben des Nicknamens.
	 * @return Der Nickname des jeweiligen Nutzers.
	 */
	
	public String getNickname() {
		return nickName;
	}
	
	/**
	 * Setzen des Nicknamens. Funktioniert nur bei der Registration eines neuen Nutzers, da dieser Wert unser Primärschlüssel darstellt.
	 * @param nickName
	 */
	
	public void setNickname(String nickName) {
		this.nickName = nickName;
	}
	
	/**
	 * toString Methode soll die Ausgabe aller Informationen eines Nutzer ermöglichen.
	 */
	
	public String toString() {
		    return super.toString() + " " + this.getID() + " " + this.nickName + " " + this.vorName + " " + this.nachName + " " + this.eMail;
	}
	
	/**
	 * Ausgabe der Emailadresse.
	 * @return Der Emailadresse des jeweiligen Nutzers.
	 */
	
	public String geteMail() {
		return eMail;
	}
	
	/**
	 * Setzen der Emailadresse.
	 * @param eMail
	 */
	
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	/**
	 * Ausgabe der Likes eines Nutzers.
	 * @return Der Likes als Ganzzahl des jeweiligen Nutzers.
	 */
	
	public int getLikeAnzahl() {
		return likeAnzahl;
	}
	
	/**
	 * Setzen der Likeanzahl.
	 * @param likeAnzahl
	 */
	
	public void setLikeAnzahl(int likeAnzahl) {
		this.likeAnzahl = likeAnzahl;
	}
	
	/**
	 * Ausgabe der Kommentare eines Nutzers.
	 * @return Die Kommentare eines Nutzers als Ganzzahl.
	 */
	
	public int getKommentarAnzahl() {
		return kommentarAnzahl;
	}
	
	/**
	 * Setzen der Kommentarzahl.
	 * @param kommentarAnzahl
	 */
	
	public void setKommentarAnzahl(int kommentarAnzahl) {
		this.kommentarAnzahl = kommentarAnzahl;
	}
	
	/**
	 * Diese Methode vergleicht jeweils unterschiedliche Nutzer, und die Gesamtanzahl der Likes.
	 * @return 
	 */
	
	public static Comparator<Nutzer> sortiereLikes() {
		return new Comparator<Nutzer>() {

			@Override
			public int compare(Nutzer n1, Nutzer n2) {
				return Integer.compare(n2.getLikeAnzahl(), n1.getLikeAnzahl());
			}
		};
	}
	
	/**
	 * Diese Methode vergleicht jeweils unterschiedliche Nutzer, und die Gesamtzahl der Kommentare.
	 * @return
	 */
	  
	  public static Comparator<Nutzer> sortiereKommentare() {
	        return new Comparator<Nutzer>() {

				@Override
				public int compare(Nutzer n1, Nutzer n2) {
					     return Integer.compare(n2.getKommentarAnzahl(), n1.getKommentarAnzahl());
					}
				};
	  }
	        
	        
	@Override
	public int compare(Nutzer o1, Nutzer o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
		