package de.elite.itprojekt.client;

import java.sql.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface KommentarAsync {


	void getID(AsyncCallback<Integer> callback);

	void getNutzerID(AsyncCallback<Integer> callback);

	void getBeitragID(AsyncCallback<Integer> callback);

	void getText(AsyncCallback<String> callback);

	void getErstellzeitpunkt(AsyncCallback<Date> callback);

	void setID(int ID, AsyncCallback<Void> callback);

	void setNutzerID(int NutzerID, AsyncCallback<Void> callback);

	void setBeitragID(int BeitragID, AsyncCallback<Void> callback);

	void setText(String Text, AsyncCallback<Void> callback);

	void setErstellzeitpunkt(Date Erstellzeitpunkt, AsyncCallback<Void> callback);

}
