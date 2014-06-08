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
import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Kommentar;
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
	private FlexTable beitragsGrid = new FlexTable();
	
	//Neuer Beitrag
	
	private VerticalPanel vPanelAddBeitrag = new VerticalPanel();
	private TextArea tArea = new TextArea();
	private Button addBeitrag;
	
	//Kommentare anlegen
	private TextArea tAreak;
	private Button addKommentar;
	
	//Kommentare ausgeben
	private VerticalPanel vPanelk = new VerticalPanel();
	private Label kommentarNutzer;
	private PushButton bearbeitenk;
	private Button loeschenk;
	private Label textBeitragk;
	private Label datumsAnzeigek;
	FlexTable kommentarFlexTable = new FlexTable();
	
	

	
	
	//Für neuen Beitraghinzufügen
	
	
	
	public void beitragAnzeigenVonAbo(final Beitrag beitrag, final Nutzer nutzer) {
		
		//Widgets für AboBeitrag
		
		this.eingeloggterUser = new Label(nutzer.getVorname() + " " + nutzer.getNachname());
		this.textBeitrag = new Label(beitrag.getText());
		this.datumsAnzeige = new Label(beitrag.getErstellZeitpunkt().toString());
		this.kommentieren = new PushButton("Kommentieren");
		
		//CSS Bezeichner
		this.eingeloggterUser.setStylePrimaryName("NutzerName");
		this.datumsAnzeige.setStylePrimaryName("Date");
		this.textBeitrag.setStylePrimaryName("umBruch");
		
		//Dem FlexTable zuordnen
		
		beitragsGrid.setStyleName("panel flexTable");
		
		beitragsGrid.setWidget(0, 0, eingeloggterUser);
		beitragsGrid.setWidget(0, 2, kommentieren);
		beitragsGrid.setWidget(1, 0, textBeitrag);
		beitragsGrid.setWidget(2, 0, datumsAnzeige);
		
		
		// Fremden Beitrag kommentieren
		
		kommentieren.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				addKommentar = new Button("Hinzufuegen");
				
				tAreak = new TextArea();
				tAreak.setVisibleLines(2);
				tAreak.setPixelSize(200, 100);
				
				beitragsGrid.setWidget(4, 0, tAreak);
				beitragsGrid.setWidget(5, 0, addKommentar);
				
				//Bei Klick in das TextAreafeld wird der aktuelle Nutzer geholt
				tAreak.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						holeNutzer();
						
					}
					
				});
					
					//Kommentar speichern
					addKommentar.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							if (tAreak.getValue().isEmpty()) {
								Window.alert("Bitte Text eingeben!");
							}
							else {
								final Kommentar kommentar = new Kommentar();
								kommentar.setText(tAreak.getText());
								kommentar.setErstellZeitpunkt(aktuellesDatum = new Timestamp(System.currentTimeMillis()));
								kommentar.setNutzer(getNutzer());
								kommentar.setBeitrag(beitrag);
								
								System.out.println(kommentar.getNutzerId());
								System.out.println(kommentar.getBeitrag().getText());
								
								service.kommentarErstellen(kommentar, new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										System.out.println("War wohl n fehler");
										
									}

									@Override
									public void onSuccess(Void result) {
										tAreak.setVisible(false);
										addKommentar.setVisible(false);
										
										kommentareAuslesen(beitrag);
										
									}
									
								});
		
								
							}
							
						}
						
					});
			}
			
		});
		
		
		this.vPanel.add(beitragsGrid);
		RootPanel.get("Beitrag").add(vPanel);
		
		
		
	}
	
	
	
	
	
	

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
		this.textBeitrag.setStylePrimaryName("umBruch");
		
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
		
		//Kommentar hinzufügen
		
		kommentieren.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				addKommentar = new Button("Hinzufuegen");
				
				tAreak = new TextArea();
				tAreak.setVisibleLines(2);
				tAreak.setPixelSize(200, 100);
				
				beitragsGrid.setWidget(4, 0, tAreak);
				beitragsGrid.setWidget(5, 0, addKommentar);
					
					//Kommentar speichern
					addKommentar.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							if (tAreak.getValue().isEmpty()) {
								Window.alert("Bitte Text eingeben!");
							}
							else {
								final Kommentar kommentar = new Kommentar();
								kommentar.setText(tAreak.getText());
								kommentar.setErstellZeitpunkt(aktuellesDatum = new Timestamp(System.currentTimeMillis()));
								kommentar.setNutzer(nutzer);
								kommentar.setBeitrag(beitrag);
								
								System.out.println(kommentar.getBeitrag().getText());
								
								service.kommentarErstellen(kommentar, new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										System.out.println("War wohl n fehler");
										
									}

									@Override
									public void onSuccess(Void result) {
										tAreak.setVisible(false);
										addKommentar.setVisible(false);
										
										kommentareAuslesen(beitrag);
										
									}
									
								});
		
								
							}
							
						}
						
					});
			}
			
		});
		
		//Liken
		like.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				anzahlLikes.setText("100");
				
			}
			
		});
		
	}
	
	
	//TEST
	
	public void doSomething(Abonnement abo) {
		
		int id = abo.getID();
		
		service.sucheBeitragPerPinnwand(id, new AsyncCallback<ArrayList<Beitrag>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<Beitrag> result) {
				for (Beitrag b : result) {
					System.out.println(b.getKommentare());
				}
				
			}
			
		});
		
		
	}
	
	
	
	
	//TESTING

	public void kommentareAuslesen(Beitrag beitrag) {

		int id = beitrag.getID();

		service.findeAlleKommentare(id, new AsyncCallback<ArrayList<Kommentar>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<Kommentar> result) {
				for (final Kommentar k : result) {
					
					//BeitragErstellen kommiAdden = new BeitragErstellen();
					//kommiAdden.kommentareAnzeigen(k);
					

					kommentarNutzer = new Label(k.getNutzer().getVorname() + " " + k.getNutzer().getNachname());
					bearbeitenk = new PushButton("Bearbeiten");
					textBeitragk = new Label(k.getText());
					datumsAnzeigek = new Label(k.getErstellZeitpunkt().toString());
					loeschenk = new Button();
					
					//Design
					//kommentarFlexTable.addStyleName("Kommentar");
					loeschenk.setStylePrimaryName("Loeschen");
					kommentarNutzer.setStylePrimaryName("NutzerName");
					datumsAnzeigek.setStylePrimaryName("Date");
					textBeitragk.setStylePrimaryName("umBruch");
					
					kommentarFlexTable.setStylePrimaryName("Kommentar");
					
					//Dem FlexTable zuordnen
					
					kommentarFlexTable.setWidget(0, 0, kommentarNutzer);
					kommentarFlexTable.setWidget(0, 1, bearbeitenk);
					kommentarFlexTable.setWidget(0, 2, loeschenk);
					kommentarFlexTable.setWidget(1, 0, textBeitragk);
					kommentarFlexTable.setWidget(2, 0, datumsAnzeigek);
					
					vPanelk.add(kommentarFlexTable);

					vPanel.add(vPanelk);
					
					
					
					
					//Kommentar loeschen
					loeschenk.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							System.out.println("Dieses Kommentar hat den Inhalt:" + " " + k.getText());
							
						}
						
					});
					
					//Kommentar bearbeiten
					bearbeitenk.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							System.out.println(k.getText() + " " + "soll nun bearbeitet werden.");
							
						}
						
					});
					
					
					
					
					
					
					
					/*
					
					
					System.out.println("");
					System.out.println("---Kommentar von Textbeitrag ID:" + " " + k.getID());
					System.out.println(k.getNutzer().getVorname() + " " + k.getNutzer().getNachname());
					System.out.println(k.getText());
					System.out.println(k.getErstellZeitpunkt().toString());
					System.out.println("---Ende Kommentar-------------------------------");
					*/

				}
				
			}
			
		});

	}
	
	
	public void kommentareAnzeigen(Kommentar k) {
		
		kommentarNutzer = new Label(k.getNutzer().getVorname() + " " + k.getNutzer().getNachname());
		bearbeitenk = new PushButton("Bearbeiten");
		textBeitragk = new Label(k.getText());
		datumsAnzeigek = new Label(k.getErstellZeitpunkt().toString());
		loeschenk = new Button();
		
		//Design
		//kommentarFlexTable.addStyleName("Kommentar");
		loeschenk.setStylePrimaryName("Loeschen");
		kommentarNutzer.setStylePrimaryName("NutzerName");
		datumsAnzeigek.setStylePrimaryName("Date");
		textBeitragk.setStylePrimaryName("umBruch");
		
		kommentarFlexTable.setStylePrimaryName("Kommentar");
		
		//Dem FlexTable zuordnen
		
		kommentarFlexTable.setWidget(0, 0, kommentarNutzer);
		kommentarFlexTable.setWidget(0, 1, bearbeitenk);
		kommentarFlexTable.setWidget(0, 2, loeschenk);
		kommentarFlexTable.setWidget(1, 0, textBeitragk);
		kommentarFlexTable.setWidget(2, 0, datumsAnzeigek);
		
		vPanelk.add(kommentarFlexTable);

		vPanel.add(vPanelk);
		
		
		System.out.println("");
		System.out.println("---Kommentar von Textbeitrag ID:" + " " + k.getID());
		System.out.println(k.getNutzer().getVorname() + " " + k.getNutzer().getNachname());
		System.out.println(k.getText());
		System.out.println(k.getErstellZeitpunkt().toString());
		System.out.println("---Ende Kommentar-------------------------------");
		
		
	}

	//ABOBEITRÄGE
	
	public void abonnementBeitraegeAnzeigen(Nutzer nutzer) {
		
		
		
	//Ersmal alle abo's rausziehen
		
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
						getBeitraeVonAbo(abo);
					}
				}

		});
	}
	//Dann die Beiträge von den Abonnenten rausziehen
	public void getBeitraeVonAbo(final Abonnement abo) {
		
		int id = abo.getPinnwand().getNutzer().getID();

		
		service.sucheBeitragPerPinnwand(id, new AsyncCallback<ArrayList<Beitrag>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<Beitrag> result) {
				
				for (Beitrag b: result) {
					System.out.println("");
					System.out.println("Er schrieb:");
					System.out.println(abo.getPinnwand().getNutzer().getVorname());
					System.out.println(b.getText());
					
					BeitragErstellen erstelle = new BeitragErstellen();
					erstelle.beitragAnzeigenVonAbo(b,abo.getPinnwand().getNutzer());
				}
				
			}
			
		});
		
		
	}

	

	
	//END OF ABOBEITRÄGE
	
	
	
	//END OF TESTING
	
	
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
		RootPanel.get("Beitrag").clear();
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
					erstelle.kommentareAuslesen(b);
				}
				
			}
			
		});
		
	}
}