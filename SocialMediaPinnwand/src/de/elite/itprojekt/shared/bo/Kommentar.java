package de.elite.itprojekt.shared.bo;
import java.io.Serializable;
import java.sql.Date;

public class Kommentar implements Serializable {

	private int ID;
	private int NutzerID;
	private int BeitragID;
	private String Text;
	private Date Erstellzeitpunkt;
	
	private static final long serialVersionUID = 1L;
	
	public int getID() {
		return this.ID;
	}
	
	public int getNutzerID() {
		return this.NutzerID;
	}
	
	public int getBeitragID() {
		return this.BeitragID;
	}
	
	public String getText() {
		return this.Text;
	}
	
	public Date getErstellzeitpunkt() {
		return this.Erstellzeitpunkt;
	}
	
	
	public void setID( int ID) {
		this.ID = ID;
	}
	
	public void setNutzerID(int NutzerID) {
		this.NutzerID = NutzerID;
	}
	
	public void setBeitragID(int BeitragID) {
		this.BeitragID = BeitragID;
	}
	
	public void setText(String Text) {
		this.Text = Text;
	}
	
	public void setErstellzeitpunkt(Date Erstellzeitpunkt){
		this.Erstellzeitpunkt = Erstellzeitpunkt;
	}
	

}
