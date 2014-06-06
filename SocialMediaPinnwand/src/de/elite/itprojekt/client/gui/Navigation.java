package de.elite.itprojekt.client.gui;

import java.util.ArrayList;

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
import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Nutzer;




public class Navigation {
	
	private Nutzer nutzer = null;
	
	//Hole Aktuellen eingeloggten Nutzer per Cookie anhand der ID
	
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class); // Proxy aufbauen für pinnwandverwaltung
	

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
		
		if (this.nutzer.getVorname().isEmpty()) {
			
			this.name.setText(this.nutzer.getNickname() + " " + this.nutzer.getNachname());
		}
		else {
			this.name.setText(this.nutzer.getVorname() + " " + this.nutzer.getNachname());
		}
	}

	//Ende Nutzer holen
	
	
private VerticalPanel vPanel = new VerticalPanel();
private Label name = new Label("");
private Label aboNutzer;
private PushButton aboLoeschen = new PushButton(new Image("images/loeschen.png"));
private PushButton pinnwandSucheButton = new PushButton(new Image("images/hinzufuegen.png"));
private FlexTable nutzerTabelle = new FlexTable();
private FlexTable suchTabelle = new FlexTable();
private FlexTable abonnierteNutzerAnzeigen = new FlexTable();
private FlexTable sucheResultatTabelle = new FlexTable();



private OrakelBox orakel = new OrakelBox();
private SuggestBox vBox = new SuggestBox(orakel.schlageNutzerVor());



//Hier die Abos auslesen per Nutzer
	


public void addNavigation(Nutzer nutzer) {
	
	//holeNutzer();
		this.name = new Label(nutzer.getVorname() + " " + nutzer.getNachname());
		
		
		nutzerTabelle.setWidget(0, 0, name);
		
		suchTabelle.setWidget(1, 0, vBox);
		suchTabelle.setWidget(1, 1, pinnwandSucheButton);
		suchTabelle.setStylePrimaryName("suchTabelle");
		

		
		//Clickhandler den Buttons adden

		this.pinnwandSucheButton.addClickHandler(new pinnwandSucheClickHandler());
		
		
		//Styles
		this.name.setStylePrimaryName("NutzerName");

		

		
		//Tabellen dem Vertikalen Panel hinzufügen
		vPanel.add(nutzerTabelle);
		vPanel.add(suchTabelle);
		vPanel.add(sucheResultatTabelle);
		
		
		
		RootPanel.get("Navigator").add(vPanel);
		
		
	}

	//Abonnierte Nutzer anzeigen
	public void getAbonnierteNutzerListe(Nutzer nutzer) {
		int id = nutzer.getID();
		
		service.zeigeAlleAbosPerNutzer(id, new AsyncCallback<ArrayList<Abonnement>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("Error in Navigation!");
			}

			@Override
			public void onSuccess(ArrayList<Abonnement> result) {
					
					for (Abonnement abo :result) {
						Navigation navi = new Navigation();
						navi.abonnierteNutzerAnzeigen(abo);
					}
				}

		});
	}

	
	public void abonnierteNutzerAnzeigen(Abonnement abo) {
		
		this.aboNutzer = new Label(abo.getPinnwand().getNutzer().getVorname() + " " + abo.getPinnwand().getNutzer().getNachname());
		
		abonnierteNutzerAnzeigen.getFlexCellFormatter().setWidth(0, 0, "120");
		abonnierteNutzerAnzeigen.setStylePrimaryName("abonnierteUserAnzeigenTabelle");
		abonnierteNutzerAnzeigen.setWidget(0, 0, aboNutzer);
		abonnierteNutzerAnzeigen.setWidget(0, 1, aboLoeschen);
		
		this.vPanel.add(abonnierteNutzerAnzeigen);
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
	
	public void nutzerAbonnieren(String name) {
		System.out.println("In dieser Methode soll ein Nutzer mit dem Namen");
		System.out.println(name);
		System.out.println("Überprüft werden, und sichergestellt werden, dass dieser Existiert");
		System.out.println("Dann soll die Abonnenmentbeziehung in die DB geschrieben werden");
		
		//Am Ende dann
		Window.alert(name + " " + "abonniert!");
		vBox.setText(null);
	}
	
	
	
}