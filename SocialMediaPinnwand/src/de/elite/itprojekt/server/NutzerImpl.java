package de.elite.itprojekt.server;

import java.sql.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.elite.itprojekt.client.Nutzer;

public class NutzerImpl extends RemoteServiceServlet implements Nutzer{

	private int ID;
	private String Vorname;
	private String Nachname;
	private String Nickname;
	private String EMail;
	private String Passwort;
	private Date Erstellzeitpunkt;

	public NutzerImpl() {
		super();
		}
	
private static final long serialVersionUID = 1L;
	
	
	public int getID() {
		return this.ID;
	}
	
	public String getVorname() {
		return this.Vorname;
	}
	
	public String getNachname() {
		return this.Nachname;
	}
	
	public String getNickname() {
		return this.Nickname;
	}
	
	public String getPasswort() {
		return this.Passwort;
	}
	
	public String getEMail() {
		return this.EMail;
	}
	
	public Date getErstellzeitpunkt() {
		return this.Erstellzeitpunkt;
	}
	

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setVorname(String Vorname) {
		this.Vorname = Vorname;
	}
	
	public void setNachname(String Nachname) {
		this.Nachname = Nachname;
	}
	
	public void setNickname(String Nickname) {
		this.Nickname = Nickname;
	}
	
	public void setPasswort(String Passwort) {
		this.Passwort = Passwort;
	}
	
	public void setEMail(String EMail) {
		this.EMail = EMail;
	}
	
	public void setErstellzeitpunkt(Date Erstellzeitpunkt) {
		this.Erstellzeitpunkt = Erstellzeitpunkt;
	}
	

}
