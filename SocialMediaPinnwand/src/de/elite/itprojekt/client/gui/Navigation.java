package de.elite.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Nutzer;




public class Navigation {
	
	private Nutzer nutzer = null;
	
	//Hole Aktuellen eingeloggten Nutzer per Cookie anhand der ID
	
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class); // Proxy aufbauen f�r pinnwandverwaltung
	

	//Methode die den Nutzer holt
	
	public void holeNutzer() {
		service.sucheNutzerID(Integer.valueOf(Cookies.getCookie("gp5cookie")), new AsyncCallback<Nutzer>() {
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Fehler");
			}
			@Override
			public void onSuccess(Nutzer result) {
				setNutzer(result);
			}
		});
	}
	
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
		this.name.setText(this.nutzer.getVorname() + " " + this.nutzer.getNachname());
	}

	//Ende Nutzer holen
	
	
private VerticalPanel vPanel = new VerticalPanel();
private Label name = new Label("");
private PushButton pinnwandSucheButton = new PushButton(new Image("images/hinzufuegen.png"));
private PushButton pinnwandDeabonnierenButton = new PushButton(new Image("images/loeschen.png"));
private FlexTable nutzerTabelle = new FlexTable();
private FlexTable suchTabelle = new FlexTable();
private FlexTable abonnierteUserAnzeigen = new FlexTable();
private FlexTable sucheResultatTabelle = new FlexTable();



private OrakelBox orakel = new OrakelBox();
private SuggestBox vBox = new SuggestBox(orakel.schlageNutzerVor());




//Dummy Namen zum Test

private Label dummyName = new Label("Gundel Gaukeley");



	


public void addNavigation() {
	
	holeNutzer();

		abonnierteUserAnzeigen.getFlexCellFormatter().setWidth(0, 0, "120");
		sucheResultatTabelle.getFlexCellFormatter().setWidth(0, 0, "120");
		
		
		nutzerTabelle.setWidget(0, 0, name);
		
		suchTabelle.setWidget(1, 0, vBox);
		suchTabelle.setWidget(1, 1, pinnwandSucheButton);
		suchTabelle.setStylePrimaryName("suchTabelle");
		
		//Tabelle die alle Abonnierten User anzeigen kann
		abonnierteUserAnzeigen.setWidget(0, 0, dummyName);
		abonnierteUserAnzeigen.setWidget(0, 1, pinnwandDeabonnierenButton);
		abonnierteUserAnzeigen.setStylePrimaryName("abonnierteUserAnzeigenTabelle");
		

		
		//Clickhandler den Buttons adden

		this.pinnwandSucheButton.addClickHandler(new pinnwandSucheClickHandler());
		this.pinnwandDeabonnierenButton.addClickHandler(new pinnwandDeabonnierenClickHandler());
		
		
		//Styles
		this.name.setStylePrimaryName("NutzerName");

		

		
		//Tabellen dem Vertikalen Panel hinzuf�gen
		vPanel.add(nutzerTabelle);
		vPanel.add(suchTabelle);
		vPanel.add(abonnierteUserAnzeigen);
		vPanel.add(sucheResultatTabelle);
		
		
		
		RootPanel.get("Navigator").add(vPanel);
		
		
	}
	
	
	
	//Die ClickHandler
	

	
	private class pinnwandSucheClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			
			if (vBox.getText().isEmpty()) {
				
			}
			else {
				nutzerAbonnieren(vBox.getText());
			}
		}
	}
	private class pinnwandDeabonnierenClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			dummyName.setText("Wurde Deabonniert");
		}
	}
	
	public void nutzerAbonnieren(String name) {
		System.out.println("In dieser Methode soll ein Nutzer mit dem Namen");
		System.out.println(name);
		System.out.println("�berpr�ft werden, und sichergestellt werden, dass dieser Existiert");
		System.out.println("Dann soll die Abonnenmentbeziehung in die DB geschrieben werden");
		
		//Am Ende dann
		Window.alert(name + " " + "abonniert!");
		vBox.setText(null);
	}
	
	
	
}