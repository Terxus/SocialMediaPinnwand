package de.elite.itprojekt.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class BusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Timestamp erstellZeitpunkt;

	private int id = 0;

	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "Pinnwandnutzer";
	}
	public void equals() {
		
	}

	public Timestamp getErstellZeitpunkt() {
		return erstellZeitpunkt;
	}

	public void setErstellZeitpunkt(Timestamp erstellZeitpunkt) {
		this.erstellZeitpunkt = erstellZeitpunkt;
	}
}
