package de.elite.itprojekt.client;

import java.sql.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NutzerAsync {

	void getID(AsyncCallback<Integer> callback);

	void getVorname(AsyncCallback<String> callback);

	void getNachname(AsyncCallback<String> callback);

	void getPasswort(AsyncCallback<String> callback);

	void getNickname(AsyncCallback<String> callback);

	void getEMail(AsyncCallback<String> callback);

	void getErstellzeitpunkt(AsyncCallback<Date> callback);

	void setID(int ID, AsyncCallback<Void> callback);

	void setVorname(String Vorname, AsyncCallback<Void> callback);

	void setNachname(String Nachname, AsyncCallback<Void> callback);

	void setNickname(String Nickname, AsyncCallback<Void> callback);

	void setErstellzeitpunkt(Date Erstellzeitpunkt, AsyncCallback<Void> callback);

	void setPasswort(String Passwort, AsyncCallback<Void> callback);

	void setEMail(String EMail, AsyncCallback<Void> callback);

}
