package de.elite.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

import de.elite.itprojekt.client.SocialMediaPinnwand;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Nutzer;



public class Navigation {

	Logger logger = SocialMediaPinnwand.getLogger();
	
	private Nutzer nutzer = null;
	private int nutzerID = Integer.valueOf(Cookies.getCookie("gp5cookie"));

	//Hole Aktuellen eingeloggten Nutzer per Cookie anhand der ID

	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class); // Proxy aufbauen für Pinnwandverwaltung


	//Methode die den Nutzer holt


	public void holeNutzer() {
		service.sucheNutzerID(Integer.valueOf(Cookies.getCookie("gp5cookie")),
				new AsyncCallback<Nutzer>() {
					@Override
					public void onFailure(Throwable caught) {
						logger.severe("Fehler bei der Nutzererkennung");
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

	 	final MultiWordSuggestOracle orakel = new MultiWordSuggestOracle();
	 	final SuggestBox vBox = new SuggestBox(orakel);


	/*
	 * Code
	 */
	
	
	
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
					logger.severe("Fehler beim auslesen der Abonnements!");
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


		public void abonnierteNutzerAnzeigen(final Abonnement abo) {

			// Zeile wirft gelegentlich "Uncaught exception escaped" > wenn in Abonnement Tabelle User keine Pinnwand hat
			this.aboNutzer = new Label(abo.getPinnwand().getNutzer().getVorname() + " " + abo.getPinnwand().getNutzer().getNachname());

			abonnierteNutzerAnzeigen.getFlexCellFormatter().setWidth(0, 0, "120");
			abonnierteNutzerAnzeigen.setStylePrimaryName("abonnierteUserAnzeigenTabelle");
			abonnierteNutzerAnzeigen.setWidget(0, 0, aboNutzer);
			abonnierteNutzerAnzeigen.setWidget(0, 1, aboLoeschen);

			this.vPanel.add(abonnierteNutzerAnzeigen);
			RootPanel.get("Navigator").add(vPanel);
			
			aboLoeschen.addClickHandler(new ClickHandler(){
				@Override
			public void onClick(ClickEvent event) {
					
						
							service.abonnementLoeschen(abo, new AsyncCallback<Void>(){
								public void onFailure(Throwable error) {
									
									Window.alert("Fail");
									logger.severe("Abonnement konnte nicht geloescht werden!");
						
								}
								@Override
								public void onSuccess(Void result) {
									Window.alert("Du hast den Nutzer: " +abo.getPinnwand().getNutzer().getNickname() +" deabonniert!");
									abonnierteNutzerAnzeigen.removeFromParent();
									NutzerLogin nl = new NutzerLogin();
									nl.refreshBeitraege();
									logger.severe("Abo von" +abo.getPinnwand().getNutzer().getNickname() + "geloescht");
								}
						    });	
		
						}
					});

			        
	
				};

		public Navigation() {


			// ABOOO SUCHE 

			service.zeigeAlleNutzer(new AsyncCallback<ArrayList<Nutzer>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(ArrayList<Nutzer> result) {


					for (Nutzer n : result) {
						orakel.add(n.getVorname() + " " + n.getNachname() + "     [ "
								+ n.getNickname() + " ]");

					}
				}
			});
			}
		
	
		
		
		private class pinnwandSucheClickHandler implements ClickHandler {
			@Override
			public void onClick(ClickEvent event) {
				abonnieren();
			}
			
			}
		
		//Abos werden angelegt
		
	public void abonnieren() {	
	
			
				if (vBox.getText().isEmpty()) {
					Window.alert("Bitte einen Suchbegriff eingeben");
				} else  {

					String s = vBox.getText();
					s = s.substring(s.indexOf("[")+2,s.indexOf(" ]"));
					final String s2 = s;
					System.out.println("Substring: " + s);
					vBox.setText("");

					service.getNutzerAnhandNickname(s, new AsyncCallback<Nutzer>() {

						@Override
						public void onFailure(Throwable caught) {
							logger.severe("Nutzer nicht gefunden!");

						}

						@Override
						public void onSuccess(Nutzer result) {
								// komischerweise geht nutzer.getID() hier nicht
								// ziehe aktuellen User aus dem Cookiewert
							if (nutzerID == result.getID()) {
								Window.alert("Man kann sich nicht selbst abbonieren!");
								logger.severe("Nutzer versuchte sich selbst zu Abonnieren!");
								vBox.setText("");
							} else if (nutzerID != result.getID()) {
								service.abonnementAnlegen(nutzerID,
										result.getID(),
										new AsyncCallback<Abonnement>() {

											@Override
											public void onFailure(
													Throwable caught) {
												// TODO Auto-generated
												// method stub
												logger.severe("Abonnement konnte nicht angelegt werden!");
											}

											@Override
											public void onSuccess(
													Abonnement result) {
												Window.alert("Du hast den User: " +s2 +" abonniert!");
												vBox.setText("");
												NutzerLogin nl = new NutzerLogin();
												nl.refreshNavi();
												logger.severe(s2 + " " + "wurde erfolgreich Abonniert!");
												
											}

										});

							}
						}
					});
				}
			}
	}