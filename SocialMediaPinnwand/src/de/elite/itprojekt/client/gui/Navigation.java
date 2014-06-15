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

/**
 * Diese Klasse ist für die Navigation zuständig. Alle Pinnwandelemente wie Abo's und die Nutzersuche sind
 * hier enthalten.
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class Navigation {
	
	/**
	 * Ein kleiner Logger der die System.out's in den Client bringen soll. Wird benutzt um die Errors auszulesen.
	 * Aurufbar z.B. durch: logger.severe("Beispieltext");
	 */

	Logger logger = SocialMediaPinnwand.getLogger();
	
	/**
	 * Instanzierung eines Nutzers.
	 */
	
	private Nutzer nutzer = null;
	
	/**
	 * Auslesen der NutzerID durch das Cookie
	 */
	
	private int nutzerID = Integer.valueOf(Cookies.getCookie("gp5cookie"));
	
	/**
	 * <b>service</b> heißt unsere Proxyreferenz, welche mit dem Server interagiert.
	 */

	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);


   /**
    * Diese Methode ließt sicht den aktuell eingeloggten Nutzer aus der Datenbank anhand der Cookies.
    */


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
	
	/**
	 * Diese Methode setzt den aktuell eingeloggten Nutzer.
	 * @param nutzer
	 */
	
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	/**
	 * Hier ist der aufbau des Design für unsere Navigation 
	 */

		private VerticalPanel vPanel = new VerticalPanel();
		private Label name = new Label("");
		private Label aboNutzer;
		
		/**
		 * Zwei PushButtons wurden gewählt, da man in ihnen besonders einfach Bilder 
		 * hinzufügen kann.
		 */
		
	 	private PushButton aboLoeschen = new PushButton(new Image("images/loeschen.png"));
	 	private PushButton pinnwandSucheButton = new PushButton(new Image("images/hinzufuegen.png"));
	 
	 	private FlexTable nutzerTabelle = new FlexTable();
	 	private FlexTable suchTabelle = new FlexTable();
	
	 	private FlexTable abonnierteNutzerAnzeigen = new FlexTable();
	 	
	 	/**
	 	 * Eine Besonderheit ist unsere Suggestbox, die Aufgrund von Anfangsbuchstaben
	 	 * Nutzerobjekte anzeigt.
	 	 */

	 	final MultiWordSuggestOracle orakel = new MultiWordSuggestOracle();
	 	final SuggestBox vBox = new SuggestBox(orakel);


	/**
	 * In dieser Methode wird anhand des aktuell eingeloggten Nutzers, der Name der Pinnwand
	 * angezeigt. Danach werden zwei Tabellen dem VerticalPanel zugeordnet.
	 * 1. nutzerTabelle: Diese Tabelle stellt lediglich den aktuellen Nutzer in einem Label dar.
	 * 2. suchTabelle: In dieser Tabelle
	 * @param nutzer
	 */
	
	public void addNavigation(Nutzer nutzer) {

		this.name = new Label(nutzer.getVorname() + " " + nutzer.getNachname());


		nutzerTabelle.setWidget(0, 0, name);

		suchTabelle.setWidget(1, 0, vBox);
		suchTabelle.setWidget(1, 1, pinnwandSucheButton);
		suchTabelle.setStylePrimaryName("suchTabelle");



		/**
		 * Dem Button einen ClickHandler zuordnen
		 */

		this.pinnwandSucheButton.addClickHandler(new pinnwandSucheClickHandler());

		/**
		 * Der Nutzer soll in einem bestimmen Style angezeigt werden.
		 */
				this.name.setStylePrimaryName("NutzerName");

				/**
				 * Beide FlexTables dem VericalPanel zuordnen
				 */
				
				vPanel.add(nutzerTabelle);
				vPanel.add(suchTabelle);
				
				/**
				 * Das Panel dem RootPanel zuordnen
				 */

				RootPanel.get("Navigator").add(vPanel);


			}
	
	/**
	 * In dieser Methode wird anhand des aktuell eingeloggten Nutzer die Abonnements von diesem angezeigt.
	 * @param nutzer
	 */
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
					
					/**
					 * Für jedes Abonnement Tupel das gefunden wurde, soll ein neues FlexTable
					 * mit diesen Informationen erstellt werden. 
					 */

						for (Abonnement abo :result) {
							Navigation navi = new Navigation();
							navi.abonnierteNutzerAnzeigen(abo);
						}
					}

			});
		}

		/**
		 * Diese Methode wird aufgerufen von der Methode <b>getAbonnierteNutzerListe(Nutzer)</b>
		 * Sie Formatiert die Informationen des Abonnements in ein FlexTable.
		 * Ein Abonnement kann darin auch gelöscht werden.
		 * @param abo
		 */

		public void abonnierteNutzerAnzeigen(final Abonnement abo) {

			// Zeile wirft gelegentlich "Uncaught exception escaped" > wenn in Abonnement Tabelle User keine Pinnwand hat
			this.aboNutzer = new Label(abo.getPinnwand().getNutzer().getVorname() + " " + abo.getPinnwand().getNutzer().getNachname());

			abonnierteNutzerAnzeigen.getFlexCellFormatter().setWidth(0, 0, "120");
			abonnierteNutzerAnzeigen.setStylePrimaryName("abonnierteUserAnzeigenTabelle");
			abonnierteNutzerAnzeigen.setWidget(0, 0, aboNutzer);
			abonnierteNutzerAnzeigen.setWidget(0, 1, aboLoeschen);

			this.vPanel.add(abonnierteNutzerAnzeigen);
			RootPanel.get("Navigator").add(vPanel);
			
			/**
			 * Bei einem Klick auf der aboLoeschen Button kann ein Abonnement gelöscht werden.
			 */
			
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
				
				/**
				 * In diesem Konstruktor werden alle Nutzer in die Suggestbox geschrieben.
				 */

		public Navigation() {


			// ABOOO SUCHE 

			service.zeigeAlleNutzer(new AsyncCallback<ArrayList<Nutzer>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(ArrayList<Nutzer> result) {
					/**
					 * Für jeden Nutzer der in der Datenbank existiert, soll ein Eintrag in der SuggestBox entstehen.
					 */

					for (Nutzer n : result) {
						orakel.add(n.getVorname() + " " + n.getNachname() + "     [ "
								+ n.getNickname() + " ]");

					}
				}
			});
			}
		
	
		/**
		 * Mit diesem ClickHandler kann ein Nutzer abonniert werden.
		 *
		 */
		
		private class pinnwandSucheClickHandler implements ClickHandler {
			@Override
			public void onClick(ClickEvent event) {
				abonnieren();
			}
			
		}
		
		/**
		 * Diese Methode legt letztendlich das Abonnement an. Dabei gibt es einige exeptions z.B. wenn man sich
		 * selbst Abonnieren will.
		 */
		
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