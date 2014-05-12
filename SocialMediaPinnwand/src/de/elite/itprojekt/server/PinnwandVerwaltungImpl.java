package de.elite.itprojekt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.elite.itprojekt.shared.PinnwandVerwaltung;

public class PinnwandVerwaltungImpl extends RemoteServiceServlet implements PinnwandVerwaltung {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//Die Methoden
	@Override
	public String sayHello(String name) {
		String greeting = "Hello " + name;
		return greeting;
	}


}
