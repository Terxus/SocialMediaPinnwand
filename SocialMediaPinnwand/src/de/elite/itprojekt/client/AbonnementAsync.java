package de.elite.itprojekt.client;

import java.sql.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AbonnementAsync {

	void getErstellzeitpunkt(AsyncCallback<Date> callback);

	void getID(AsyncCallback<Integer> callback);

	void getNutzerID(AsyncCallback<Integer> callback);

	void getPinnwandID(AsyncCallback<Integer> callback);

	void setErstellzeitpunkt(Date Erstellzeitpunkt, AsyncCallback<Void> callback);

	void setID(int ID, AsyncCallback<Void> callback);

	void setNutzerID(int NutzerID, AsyncCallback<Void> callback);

	void setPinnwandID(int Pinnwand, AsyncCallback<Void> callback);

}
