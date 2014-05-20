package de.elite.itprojekt.client;
import java.sql.Date;

import com.google.gwt.user.client.rpc.RemoteService;

public interface Textbeitrag extends RemoteService {

	public int getID();
	public int getNutzerID();
	public int getLikeID();
	public String getText();
	public Date getErstellzeitpunkt();
	
		public void setID(int ID);
		public void setNutzerID(int NutzerID);
		public void setLikeID( int LikeID);
		public void setText (String Text);
		public void setErstellzeitpunkt (Date Erstellzeitpunkt);
}
