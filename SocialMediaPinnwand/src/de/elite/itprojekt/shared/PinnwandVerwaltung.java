package de.elite.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("pinnwandVerwaltung") //Shortcut name
public interface PinnwandVerwaltung extends RemoteService {
	
	String sayHello(String name);

}
