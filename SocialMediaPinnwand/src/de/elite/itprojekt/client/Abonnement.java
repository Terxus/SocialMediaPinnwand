package de.elite.itprojekt.client;
import java.sql.Date;


import com.google.gwt.user.client.rpc.RemoteService;

public interface Abonnement extends RemoteService {
	
	public int getID();
	public Date getErstellzeitpunkt();
	public int getNutzerID();
	public int getPinnwandID();
	
		public void setID (int ID);
		public void setErstellzeitpunkt(Date Erstellzeitpunkt);
		public void setNutzerID(int NutzerID);
		public void setPinnwandID(int PinnwandID);
		
		
}
