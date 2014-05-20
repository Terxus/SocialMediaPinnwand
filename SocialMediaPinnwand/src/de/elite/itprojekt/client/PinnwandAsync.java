package de.elite.itprojekt.client;

import java.sql.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PinnwandAsync {

	void getID(AsyncCallback<Integer> callback);

	void getErstellzeitpunkt(AsyncCallback<Date> callback);

	void getNutzerID(AsyncCallback<Integer> callback);

	void getAbonnementID(AsyncCallback<Integer> callback);

	void setID(int ID, AsyncCallback<Void> callback);

	void setErstellzeitpunkt(Date Erstellzeitpunkt, AsyncCallback<Void> callback);

	void setNutzerID(int NutzerID, AsyncCallback<Void> callback);

	void setAbonnementID(int AbonementID, AsyncCallback<Void> callback);

}
