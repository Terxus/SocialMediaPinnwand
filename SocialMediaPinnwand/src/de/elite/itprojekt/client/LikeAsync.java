package de.elite.itprojekt.client;

import java.sql.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LikeAsync {

	void getID(AsyncCallback<Integer> callback);

	void getNutzerID(AsyncCallback<Integer> callback);

	void getBeitragID(AsyncCallback<Integer> callback);

	void getErstellzeitpunkt(AsyncCallback<Date> callback);

	void setID(int ID, AsyncCallback<Void> callback);

	void setNutzerID(int NutzerID, AsyncCallback<Void> callback);

	void setBeitragID(int BeitragID, AsyncCallback<Void> callback);

	void setErstellzeitpunkt (Date Erstellzeitpunkt, AsyncCallback<Void> callback);


}
