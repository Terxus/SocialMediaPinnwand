package de.elite.itprojekt.server;

import com.google.gwt.user.client.ui.Composite;

import de.elite.itprojekt.shared.Nutzer;

public class ConnectionClass extends Composite {
	
	//Hole Nutzer von shared
	private Nutzer nutzer;
	public String getNutzer() {
		this.nutzer = new Nutzer();
		return nutzer.toString();
	}

}
