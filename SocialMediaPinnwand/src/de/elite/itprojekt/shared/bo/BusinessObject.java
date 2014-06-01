package de.elite.itprojekt.shared.bo;

import java.io.Serializable;
import java.sql.Date;

public abstract class BusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date erstellZeitpunkt;
	private String StringerstellZeitpunkt;
	private String Datum;
	
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

	public Date getErstellZeitpunkt() {
		return erstellZeitpunkt;
	}

	public void setErstellZeitpunkt(Date erstellZeitpunkt) {
		this.erstellZeitpunkt = erstellZeitpunkt;
	}

	public void setDatum(String datums) {
		this.Datum = datums;

	}

	public String getDatum(String datums) {
		return Datum;

	}

	public String getStringerstellZeitpunkt() {
		return StringerstellZeitpunkt;
	}

	public void setStringerstellZeitpunkt(String stringerstellZeitpunkt) {
		StringerstellZeitpunkt = stringerstellZeitpunkt;
	}


}
