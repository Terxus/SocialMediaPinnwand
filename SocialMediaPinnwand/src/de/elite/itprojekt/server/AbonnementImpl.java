package de.elite.itprojekt.server;

import java.sql.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.elite.itprojekt.client.Abonnement;

public class AbonnementImpl extends RemoteServiceServlet implements Abonnement{

	private int ID;
	private Date Erstellzeitpunkt;
	private int NutzerID;
	private int PinnwandID;
	
	public AbonnementImpl() {
		super();
	}
	private static final long serialVersionUID = 1L;
	
	public int getID() {
		return this.ID;
		
	}
	
	public Date getErstellzeitpunkt() {
		return this.Erstellzeitpunkt;
	}
	
	
	public int getNutzerID() {
		return this.NutzerID;
	}
	
	public int getPinnwandID() {
		return this.PinnwandID;
		}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setErstellzeitpunkt(Date Erstellzeitpunkt) {
		this.Erstellzeitpunkt = Erstellzeitpunkt;
	}
	
	public void setNutzerID(int NutzerID) {
		this.NutzerID = NutzerID;
	}
	
	public void setPinnwandID(int PinnwandID) {
		this.PinnwandID = PinnwandID;
	}
}
