package de.elite.itprojekt.shared.bo;

public class Nutzer extends BusinessObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vorName, nachName, nickName, passWort,eMail;
	
	
	
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
}
