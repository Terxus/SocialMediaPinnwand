package de.elite.itprojekt.server;

import java.sql.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.elite.itprojekt.client.Pinnwand;

public class PinnwandImpl extends RemoteServiceServlet implements Pinnwand{

	private int ID;
	private Date Erstellzeitpunkt;
	private int NutzerID;
	private int AbonnementID;
	
	public PinnwandImpl() {
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
	
	public int getAbonnementID() {
		return this.AbonnementID;
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
	
	public void setAbonnementID(int AbonnementID) {
		this.AbonnementID = AbonnementID;
	}


}
