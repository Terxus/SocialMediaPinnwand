package de.elite.itprojekt.client.gui;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;



public class BeitragErstellen {
	

	
	private Timestamp aktuellesDatum;
	private Nutzer nutzer;
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class); // Proxy aufbauen für pinnwandverwaltung
	

	//Nutzerobjekt per ID von Cookie holen
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
	
	

	public void beitragAnzeigen(final Beitrag beitrag, final Nutzer nutzer) {
		
		//Widgets erzeugen für Beitrag

		this.eingeloggterUser = new Label(nutzer.getVorname() + " " + nutzer.getNachname());
		this.kommentieren = new PushButton("Kommentieren");
		this.bearbeiten = new PushButton("Bearbeiten");
		this.loeschen = new Button();
		this.textBeitrag = new Label(beitrag.getText());
		this.datumsAnzeige = new Label(beitrag.getErstellZeitpunkt().toString());
		this.like = new PushButton("Like");
		this.anzahlLikes = new Label("2");
		
		//CSS Bezeichner
		this.loeschen.setStylePrimaryName("Loeschen");
		this.eingeloggterUser.setStylePrimaryName("NutzerName");
		this.datumsAnzeige.setStylePrimaryName("Date");
		
		beitragsGrid.setStyleName("panel flexTable");
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
		
		//ClickHandler müssen für jedes Beitragobjekt gelten, darum müssen sie hier definiert werden
		
		//Beitrag löschen
		
		loeschen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				service.textBeitragLoeschen(beitrag, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Fehler beim loeschen!");
							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("Textbeitrag wurde geloescht!");
								beitragsGrid.removeFromParent();
							}
						});
			}

		});
		
		//Beitrag bearbeiten
		
		bearbeiten.addClickHandler(new ClickHandler () {
			@Override
			public void onClick(ClickEvent event) {
				
				final Button speichern = new Button("Fertig");
				final TextBox newBeitrag = new TextBox();
				newBeitrag.setText(textBeitrag.getText());
				beitragsGrid.setWidget(1, 0, newBeitrag);
				beitragsGrid.setWidget(1, 1, speichern);
				
					//Bearbeiteter Text Speichern und in Label zurückverwandeln (*Magic*)
						speichern.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								// TODO Auto-generated method stub
								beitrag.setText(newBeitrag.getText());
								beitrag.setErstellZeitpunkt(aktuellesDatum = new Timestamp(System.currentTimeMillis()));

								service.textBeitragBearbeiten(beitrag, new AsyncCallback<Beitrag>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void onSuccess(Beitrag result) {
										// TODO Auto-generated method stub
										Label beitrag = new Label(newBeitrag.getText());
										
										beitragsGrid.setWidget(1, 0, beitrag);
										speichern.setVisible(false);
									}
									
								});
							}
						});
			}
		});
	}
	
	public void beitragHinzufuegen() {
		
		tArea.setVisibleLines(2);
		tArea.setPixelSize(473, 15);
		this.addBeitrag = new Button("Hinzufuegen");
		
		//ClickHandler für neuen Beitrag
		
		this.addBeitrag.addClickHandler(new addBeitragClickHandler());
		this.tArea.addClickHandler(new getNutzerClickHandler());
		
		
		this.vPanelAddBeitrag.add(tArea);
		this.vPanelAddBeitrag.add(addBeitrag);
		
		RootPanel.get("neuer_Beitrag").add(vPanelAddBeitrag);
	}
	
	
	
	
	
	//ClickHandler der den Beitrag hinzufügt
	private class addBeitragClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			
			if (tArea.getText().isEmpty()) {
				Window.alert("Bitte Text eingeben!");
			}
			else {
			addBeitragAsync(getNutzer(), tArea.getText());
			tArea.setText(null);
			}
		}
	}
	//ClickHandler muss da sein, damit das Nutzer objekt geholt wird.
	
	private class getNutzerClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			holeNutzer();
		}
	}
	
	
	public void addBeitragAsync(Nutzer nutzer, String textBeitrag) {
		
		Beitrag beitrag = new Beitrag();
		beitrag.setNutzerId(nutzer.getID());
		beitrag.setText(textBeitrag);
		beitrag.setErstellZeitpunkt(aktuellesDatum = new Timestamp(System.currentTimeMillis()));
		

		service.textBeitragErstellen(beitrag, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("War wohl n fehler");
				
			}

			@Override
			public void onSuccess(Void result) {
				neuerBeitragAnzeigen(getNutzer());
			}
			
		});
		
				
		
	}
	
	public void neuerBeitragAnzeigen(Nutzer nutzer) {
		zeigeAlleBeitraege(nutzer);
	}
	
	public void zeigeAlleBeitraege(Nutzer nutzer) {
		
		int id = nutzer.getID();
		final Nutzer n = nutzer;
		
		service.findeAlleUserBeitraege(id, new AsyncCallback<ArrayList<Beitrag>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("hmm2");
				
			}

			@Override
			public void onSuccess(ArrayList<Beitrag> result) {
				
				for (Beitrag b : result) {
					BeitragErstellen erstelle = new BeitragErstellen();
					erstelle.beitragAnzeigen(b,n);
				}
				
			}
			
		});
		
	}
}