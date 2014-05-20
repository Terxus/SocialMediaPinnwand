package de.elite.itprojekt.client;
import java.sql.Date;

import com.google.gwt.user.client.rpc.RemoteService;


public interface Nutzer extends RemoteService {
	
	public int getID();
	public String getVorname();
	public String getNachname();
	public String getNickname();
	public String getPasswort();
	public String getEMail();
	public Date getErstellzeitpunkt();
		
		public void setID(int ID);
		public void setVorname(String Vorname);
		public void setNachname(String Nachname);
		public void setNickname(String Nickname);
		public void setPasswort(String Passwort);
		public void setEMail(String EMail);
		public void setErstellzeitpunkt(Date Erstellzeitpunkt);
		
}
