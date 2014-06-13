package de.elite.itprojekt.shared.bo;

import java.util.Comparator;

public class Nutzer extends BusinessObject implements Comparator<Nutzer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vorName, nachName, nickName, passWort,eMail;
	private int likeAnzahl, kommentarAnzahl;
	
	
	public String getPassWort() {
		return passWort;
	}
	public void setPassWort(String passWort) {
		this.passWort = passWort;
	}
	public String getVorname() {
		return vorName;
	}
	public void setVorname(String vorname) {
		this.vorName = vorname;
	}
	public String getNachname() {
		return nachName;
	}
	public void setNachname(String nachname) {
		this.nachName = nachname;
	}
	public String getNickname() {
		return nickName;
	}
	public void setNickname(String nickname) {
		this.nickName = nickname;
	}
	public String toString() {
		    return super.toString() + " " + this.getID() + " " + this.nickName + " " + this.vorName + " " + this.nachName + " " + this.eMail;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
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
	
	public static Comparator<Nutzer> sortiereLikes() {
		return new Comparator<Nutzer>() {

			@Override
			public int compare(Nutzer n1, Nutzer n2) {
				return Integer.compare(n2.getLikeAnzahl(), n1.getLikeAnzahl());
			}
		};
	}
	  
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
		