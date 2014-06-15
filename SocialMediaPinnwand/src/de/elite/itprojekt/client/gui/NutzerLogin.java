package de.elite.itprojekt.client.gui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.client.SocialMediaPinnwand;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Nutzer;

/**
 * Diese Klasse ermöglicht einem registrierten Nutzer einen Login zu seiner Pinnwand.
 * Sollte ein Nutzer nicht dem System angehören, dann hat er die Möglichkeit, sich anhand
 * eines Hyperlinks zu registrieren. Dafür ist dann die Klasse <b>NutzerRegistrieren</b> 
 * zuständig.
 * 
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class NutzerLogin {
	
	/**
	 * <b>service</b> heißt unsere Proxyreferenz, welche mit dem Server interagiert.
	 */
	
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);
	
	/**
	 * Ein kleiner Logger der die System.out's in den Client bringen soll. Wird benutzt um die Errors auszulesen.
	 * Aurufbar z.B. durch: logger.severe("Beispieltext");
	 */
	
	Logger logger = SocialMediaPinnwand.getLogger();

	/**
	 * Instanzierung eines Nutzers.
	 */
	
	private Nutzer nutzer;
	
	/**
	 * Methode die den aktuellen Nutzer ausließt.
	 * @return Nutzer
	 */
	
	public Nutzer getNutzer() {
		return this.nutzer;
	}
	
	/**
	 * Methode die den aktuellen Nutzer setzt.
	 * @param nutzer
	 */
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
	

	/**
	 * Hier befindet sich das Design des Loginfensters.
	 * Das erste was ein Nutzer sieht, wenn er kein Cookie besitzt,
	 * ist dieses FlexTable, welches zu einer schöneren Darstellung in 
	 * einem DecoratorPanel eingeschlossen ist.
	 */
	
		private DecoratorPanel decPanel = new DecoratorPanel();
		private TextBox nutzerNameBox = new TextBox();
		private PasswordTextBox passwortBox = new PasswordTextBox();
		private Button loginButton = new Button("Login");
		private Label errorLabel = new Label();
		private HTML successHTML = new HTML();
		private Button closeButton = new Button("Close");
		private FlexTable loginFlex = new FlexTable();
		private Label nutzerName = new Label("Nutzer:");
		private Label passwort = new Label("Passwort:");
		private Hyperlink registerLink = new Hyperlink("Neuen Nutzer registrieren", false,"Register");
		
		
		private Label willKommenLabel = new Label();

/**
 * Diese Methode nutzt die oben instanzierten Widgets, und ordnet sie in dem FlexTable <b>loginFlex</b> an.
 */
	  public void loadLoginView() {
			
		/**
		 * Anordnung der Widgets in der FlexTable.
		 */
		
		loginFlex.setWidget(0, 1, nutzerNameBox);
		loginFlex.setWidget(1, 1, passwortBox);
		loginFlex.setWidget(0, 0, nutzerName);
		loginFlex.setWidget(1, 0, passwort);
		loginFlex.setWidget(2, 0, loginButton);
		loginFlex.setWidget(2, 1, registerLink);
		loginFlex.setWidget(3, 0, errorLabel);
		
		/**
		 * Hier wird das FlexTable in ein DecoratorPanel gepackt.
		 */
		
		decPanel.add(loginFlex);
		
		
		/**
		 * Das Loginfenster (DecoratorPanel) wird dem DIV-Element <b>Navigator</b> zugeordnet.
		 */
		
		RootPanel.get("Navigator").add(decPanel);
		
		/**
		 * Bei Aufruf ist der Mauscursor direkt in der Box, in der der Nutzername reingeschrieben
		 * werden soll.
		 */
		
		nutzerNameBox.setFocus(true);
		
		/**
		 * Dem Errorlabel wird ein Stylenamen hinzugefügt, welches in der CSS Datei definiert ist.
		 */
		
		errorLabel.setStyleName("errorLabel");
		errorLabel.setVisible(false);
		
		
		closeButton.getElement().setId("closeButton");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(successHTML);
		dialogVPanel.add(closeButton);

		//CloseClickHandler zu der Dialogbox adden
		
		closeButton.addClickHandler(new CloseButtonClickHandler());
		
		/**
		 * ClickHandler der auf den Hyperlink zur Registration reagiert.
		 */
		
		registerLink.addDomHandler(new RegistrierClickHandler(), ClickEvent.getType());
		
		/**
		 * ClickHandler der auf den Button Login reagiert.
		 */
		
		loginButton.addClickHandler(new LoginClickHandler());

		/**
		 * In der TextBox befindet sich ein ClickHandler welches bei Drücken der Entertaste
		 * reagiert. Dies erleichert das Einloggen.
		 */
		
		passwortBox.addKeyPressHandler(new KeyPressHandler() {
 			public void onKeyPress(KeyPressEvent event) {
 				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
 					checkValues();
 				}
 			}
 		});
		
	  }

	  /**
	   * Bei Aufruf dieser Methode wird zuerst das Cookie ausgelesen. Wenn die ID des Nutzer gefunden wurde, wird
	   * darauf ein Nutzerobjekt erstellt. Wenn das Objekt erstellt wurde, wird die Pinnwand aufgebaut - mit
	   * sämtlichen Inhalten: Navigation, Abonnements, und die Beiträge+Kommentare der Pinnwand.
	   */
	  public void loadPinnwand() {
		  
		  
			service.sucheNutzerID(Integer.valueOf(Cookies.getCookie("gp5cookie")), new AsyncCallback<Nutzer>() {
				@Override
				public void onFailure(Throwable caught) {
					logger.severe("Fehler bei der Nutzererkennung");
				}
				@Override
				public void onSuccess(Nutzer result) {
					setNutzer(result);
					
					TopLevelNavigation topLevelNavi = new TopLevelNavigation();
					BeitragErstellen beitrag = new BeitragErstellen();
					Navigation navi = new Navigation();
					
					topLevelNavi.addTopLevelNavi();
					navi.addNavigation(result);
					navi.getAbonnierteNutzerListe(result);
					
					//Zeigt alle Beiträge an
					beitrag.setNutzer(result);
					beitrag.zeigeAlleBeitraege(result);
					beitrag.beitragHinzufuegen();
	
					logger.severe("Pinnwand geladen!");
					
				}
			});
	  }
	  /**
	   * Diese Methode wurde geschaffen um die Pinnwand neu zu laden. Eine Art <b>refresh</b> Funktion.
	   */
	  public void refreshPinnwand() {
		  
			service.sucheNutzerID(Integer.valueOf(Cookies.getCookie("gp5cookie")), new AsyncCallback<Nutzer>() {
				@Override
				public void onFailure(Throwable caught) {
					logger.severe("Pinnwand konnte nicht refreshed werden");
				}
				@Override
				public void onSuccess(Nutzer result) {
					setNutzer(result);
	
					
					BeitragErstellen beitrag = new BeitragErstellen();
					Navigation navi = new Navigation();
					

					navi.addNavigation(result);
					navi.getAbonnierteNutzerListe(result);
					
					//Alle Beiträge anzeigen
					beitrag.zeigeAlleBeitraege(result);
					beitrag.beitragHinzufuegen();
					logger.severe("Pinnwand neu geladen!");

					
				}
			});		  
	  }
	  
	  
	  
	  //Navigationsrefresh
	  /**
	   * Um nicht jedesmal den gesamten Inhalt der Pinnwand neu zu laden wurde diese Methode geschaffen.
	   * Sie lädt nur die Navigation (DIV-Element: <b>Navigator</b>) neu, zum Beispiel wenn ein neuer Abonnement hinzugefügt oder gelöscht wurde.
	   */
	  
	  public void refreshNavi() {
		  
			service.sucheNutzerID(Integer.valueOf(Cookies.getCookie("gp5cookie")), new AsyncCallback<Nutzer>() {
				@Override
				public void onFailure(Throwable caught) {
					logger.severe("Navigation konnte nicht geladen werden");
				}
				@Override
				public void onSuccess(Nutzer result) {
					setNutzer(result);
					RootPanel.get("Navigator").clear();
					
					
					Navigation navi = new Navigation();
					

					navi.addNavigation(result);
					navi.getAbonnierteNutzerListe(result);
					
					refreshBeitraege();
					
				}
			});
		  
	  }
	  
	  /**
	   * Äquivalent zu oberen Methode, lädt diese Methode nur die Beiträge neu. Also das DIV-Element: <b>Beitrag</b>.
	   */
	  public void refreshBeitraege() {
		  
			service.sucheNutzerID(Integer.valueOf(Cookies.getCookie("gp5cookie")), new AsyncCallback<Nutzer>() {
				@Override
				public void onFailure(Throwable caught) {
					logger.severe("Beiträge konnten nicht geladen werden");
				}
				@Override
				public void onSuccess(Nutzer result) {
					setNutzer(result);
					RootPanel.get("Beitrag").clear();
					
					BeitragErstellen beitrag = new BeitragErstellen();
					beitrag.zeigeAlleBeitraege(result);
				}
			});
		  
	  }
	  

	 /**
	  * ClickHandler ermöglicht das anzeigen des Buttons: Login
	  *
	  */
	  
	  private class CloseButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			loginButton.setEnabled(true);
			loginButton.setFocus(true);
		}
	  }
	  /**
	   * Dieser ClickHandler erstellt ein neues Objekt der Klasse <b>NutzerRegistrieren</b>,
	   * und ruft danach die Methode <b>nutzerRegistrieren()</b> auf.
	   *
	   */
	  
	  private class RegistrierClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			NutzerRegistrieren register = new NutzerRegistrieren();
			register.nutzerRegistrieren();
		}
	  }
	  
	  /**
	   * Dieser ClickHandler ruft die Methode checkValues() auf,
	   * die die Eingabe des Nutzer überprüft.
	   *
	   */
	  
	  private class LoginClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			checkValues();
		}
	  }
	  

	  /**
	   * Diese Methode überprüft ob in der Textbox <b>Nutzer</b> und in der Textbox <b>Passwort</b>
	   * sich Einträge befinden. Sollte dies der Fall sein wird die Methode <b>checkUser()</b> aufgerufen.
	   */
	  
	  private void checkValues() {
				if ((nutzerNameBox.getValue() == "") || (passwortBox.getValue() == "")) {
					Window.alert("Bitte Benutzername und Passwort eingeben!");
				}
				else {
					checkUser();
				}
	  }
	  
	  /**
	   * Diese Methode überprüft ob sich Nutzer mit dem angegeben Namen und Passwort in der Datenbank befinden.
	   * Bei Erfolg wird eine Willkommensnachricht im Footer erscheinen und die Methode <b>login()</b> aufgerufen.
	   */
	  
	  public void checkUser() {		  
			service.loginCheck(nutzerNameBox.getText(), passwortBox.getText(), new AsyncCallback<Nutzer>() {

				@Override
				public void onSuccess(Nutzer result) {
					if (result.getID() != 0) {
						Cookies.setCookie("gp5cookie", String.valueOf(result.getID()));
						RootPanel.get("Navigator").clear(true);
						RootPanel.get().clear();
						login();

	
						willKommenLabel.setText("Willkommen" + " " + result.getVorname() + "!");
						RootPanel.get("Footer").add(willKommenLabel);
					} 
					else {
						Window.alert("Benutzer oder Passwort inkorrekt");
					}
				}
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert("Keine DB-Verbindung");
					logger.severe("Datenbankverbindung fehlerhaft!");
				}
			});

		}
	  
	  /**
	   * Wenn der Nutzer vorhanden ist, dann wird die Pinnwand geladen.
	   */

	  private void login() {
		  loadPinnwand();
	  }
}
