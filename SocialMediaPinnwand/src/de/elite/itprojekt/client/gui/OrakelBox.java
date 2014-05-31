package de.elite.itprojekt.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Nutzer;

public class OrakelBox {
	
	private Nutzer nutzer;
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);
	
	
	
	public MultiWordSuggestOracle schlageNutzerVor() {
		
		service.zeigeAlleNutzer(new AsyncCallback<ArrayList<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("lohl es geht nicht");
				
			}

			@Override
			public void onSuccess(ArrayList<Nutzer> result) {
				// TODO Auto-generated method stub
				System.out.println("OrakelBox.java -> Success Methode");
				
			}
			
	});
		
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		
		//Hier alle nutzer holen
		
		oracle.add("Gustav Gans");
		oracle.add("Donald Duck");
		oracle.add("Pep Gardiola");
		
	
		return oracle;
	}

}
