package de.elite.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;



public class BeitragErstellen {
	
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class); // Proxy aufbauen für pinnwandverwaltung
	//Nutzerobjekt per ID von Cookie holen
	private Nutzer nutzer;
	
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
		System.out.println("Nutzerobjekt zu Nutzer mit der ID:" + " " + this.nutzer.getID() + " " + "gesetzt.");
	}
	
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
	
	public Nutzer getNutzer() {
		return this.nutzer;
	}
	
	
	private VerticalPanel vPanel = new VerticalPanel();
	private Label eingeloggterUser;
	private PushButton kommentieren;
	private PushButton bearbeiten;
	private Button loeschen;
	private Label textBeitrag;
	private Label datumsAnzeige;
	private PushButton like;
	private Label anzahlLikes;
	FlexTable beitragsGrid = new FlexTable();
	
	//Neuer Beitrag
	
	private VerticalPanel vPanelAddBeitrag = new VerticalPanel();
	private TextArea tArea = new TextArea();
	private Button addBeitrag;
	

	
	
	//Für neuen Beitraghinzufügen
	
	

	public void beitragAnzeigen() {
		
		//Widgets erzeugen für Beitrag

		this.eingeloggterUser = new Label("Gustav Gans");
		this.kommentieren = new PushButton("Kommentieren");
		this.bearbeiten = new PushButton("Bearbeiten");
		this.loeschen = new Button();
		this.textBeitrag = new Label("Methode die dann die Textbeitraege holen soll! Methode die dann die Textbeitraege holen soll! Methode die dann die Textbeitraege holen soll!");
		this.datumsAnzeige = new Label("24.06.2014 um 12:00 Uhr");
		this.like = new PushButton("Like");
		this.anzahlLikes = new Label("2");
		
		//CSS Bezeichner
		this.loeschen.setStylePrimaryName("Loeschen");
		this.eingeloggterUser.setStylePrimaryName("NutzerName");
		this.datumsAnzeige.setStylePrimaryName("Date");
		
		
		beitragsGrid.setWidget(0, 0, eingeloggterUser);
		beitragsGrid.setWidget(0, 2, kommentieren);
		beitragsGrid.setWidget(0, 3, bearbeiten);
		beitragsGrid.setWidget(0, 4, loeschen);
		beitragsGrid.setWidget(1, 0, textBeitrag);
		beitragsGrid.setWidget(2, 0, datumsAnzeige);
		beitragsGrid.setWidget(2, 3, like);
		beitragsGrid.setWidget(2, 4, anzahlLikes);
		
		
		
		
		
		this.vPanel.add(beitragsGrid);
		
		RootPanel.get("Beitrag").add(vPanel);
		

	}
	
	public void beitragHinzufuegen() {
		
		tArea.setVisibleLines(2);
		tArea.setPixelSize(473, 15);
		this.addBeitrag = new Button("Hinzufuegen");
		
		//ClickHandler für neuen Beitrag
		
		this.addBeitrag.addClickHandler(new addBeitragClickHandler());
		
		this.vPanelAddBeitrag.add(tArea);
		this.vPanelAddBeitrag.add(addBeitrag);
		
		RootPanel.get("neuer_Beitrag").add(vPanelAddBeitrag);
	}
	
	private class addBeitragClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			holeNutzer();
			addBeitragAsync(getNutzer(), tArea.getText());
		}
	}
	
	public void addBeitragAsync(Nutzer nutzer, String textBeitrag) {
		
		Beitrag beitrag = new Beitrag();
		beitrag.setNutzerId(nutzer.getID());
		beitrag.setText(textBeitrag);
		beitrag.setDatum("23-3-2333");
		System.out.println(beitrag.getNutzerId() + " " + beitrag.getText());
		
		
		service.textBeitragErstellen(beitrag, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("War wohl n fehler");
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				System.out.println("Success!");
				
			}
			
		});
		
				
		
	}
	
	
	
	
	
}