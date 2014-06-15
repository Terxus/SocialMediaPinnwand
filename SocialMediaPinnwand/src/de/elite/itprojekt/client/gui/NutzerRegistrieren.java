package de.elite.itprojekt.client.gui;

import java.sql.Timestamp;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.elite.itprojekt.client.SocialMediaPinnwand;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Nutzer;

/**
 * In dieser Klasse kann sich ein Nutzer im System registrieren lassen. Die
 * Angaben eines Benutzernamens, Vornamen, Nachnamen, Email-Adresee und Passwort 
 * sind erforderlich um sich registrieren zu können.
 * Der Benutzername ist ein eindeutiges Erkennungsmerkmal, d.h. er darf nich doppelt 
 * vorkommen. Sollte der Nickname bereits vergeben sein weist das System den Nutzer darauf hin.
 * 
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla,
 *         Yen Nguyen
 * @version 1.0
 */

public class NutzerRegistrieren {

	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);

	Logger logger = SocialMediaPinnwand.getLogger();

	private FlexTable tableFlex = new FlexTable();
	private DecoratorPanel decPanelRegister = new DecoratorPanel();
	private TextBox usertextBox = new TextBox();
	private Label lblUsername = new Label("Benutzername");
	private Label lblPassword = new Label("Passwort");
	private Label lblConfirmPassword = new Label("Passwort wiederholen");
	private Label lblEmail = new Label("Email");
	private Label lblVorname = new Label("Vorname");
	private Label lblNachname = new Label("Nachname");
	private Button button = new Button("Registrieren");
	private PasswordTextBox passwordTextBox = new PasswordTextBox();
	private PasswordTextBox passwordTextBox_1 = new PasswordTextBox();
	private TextBox emailtextBox = new TextBox();
	private TextBox vntextBox = new TextBox();
	private TextBox nntextBox = new TextBox();
	private Hyperlink loginLink = new Hyperlink("Zurueck zum Login", false,
			"Login");

	/**
	 * Wir Nutzen ein Timestampobjekt als Datum für Kommentare, Likes, Beiträge.
	 */

	private Timestamp aktuellesDatum;

	public NutzerRegistrieren() {
		logger.severe("Nutzerregistration geladen");
	}

	/**
	 * Anordnung der Widgets mittels einer FlexTable.
	 */

	public void nutzerRegistrieren() {

		tableFlex.setWidget(0, 0, lblUsername);
		tableFlex.setWidget(1, 0, lblPassword);
		tableFlex.setWidget(2, 0, lblConfirmPassword);
		tableFlex.setWidget(3, 0, lblEmail);
		tableFlex.setWidget(4, 0, lblVorname);
		tableFlex.setWidget(5, 0, lblNachname);
		tableFlex.setWidget(6, 1, button);
		tableFlex.setWidget(0, 1, usertextBox);
		tableFlex.setWidget(1, 1, passwordTextBox);
		tableFlex.setWidget(2, 1, passwordTextBox_1);
		tableFlex.setWidget(3, 1, emailtextBox);
		tableFlex.setWidget(4, 1, vntextBox);
		tableFlex.setWidget(5, 1, nntextBox);
		tableFlex.setWidget(7, 0, loginLink);

		loginLink.addDomHandler(new LoginLinkClickHandler(),
				ClickEvent.getType());
		button.addClickHandler(new registerClickHandler());

		decPanelRegister.add(tableFlex);
		RootPanel.get("Beitrag").clear();
		RootPanel.get("Navigator").clear();
		RootPanel.get("Beitrag").add(decPanelRegister);

	}

	/**
	 * Mit einem Klick auf den Link "Neuen Nutzer registrieren" im
	 * Anmeldebildschirm öffnet sich die Registriermaske.
	 */

	private class LoginLinkClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("Beitrag").clear();
			NutzerLogin login = new NutzerLogin();

			login.loadLoginView();
		}
	}

	/**
	 * Mit einem Klick auf den Button "Registrieren" wird die Methode
	 * neuenNutzerRegistrieren(); aufgerufen.
	 */

	private class registerClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			neuenNutzerRegistrieren();
		}
	}

	/**
	 * In dieser Methode wird die Registrierung des Nutzers ins System
	 * vorgenommen. die Methode liest die Daten aus die der Nutzer eingegeben
	 * hat und speichert diese in ein neues Nutzerobjekt. Dabei wird verglichen ob ein Nutzername bereits
	 * im System schon existiert. Der Nutzer wird daraufhin informaiert.
	 * Bei der Eingabe des Passworts findet ebenfalls eine Überprüfung statt. Sollte das Passwort
	 * nicht übereinstimmen wird eine Fehlermeldung auf dem Bildschirm ausgegeben.
	 * Eine weitere Anweisung prüft ob der Nutzer auch wirklich alle Felder ausgefüllt hat.
	 * 
	 * @param void
	 */

	public void neuenNutzerRegistrieren() {

		final String s1 = usertextBox.getText();
		String s2 = passwordTextBox.getText();
		String s3 = passwordTextBox_1.getText();
		
			
			if(usertextBox.getText().isEmpty() || passwordTextBox.getText().isEmpty()
					|| vntextBox.getText().isEmpty() || nntextBox.getText().isEmpty()
					|| emailtextBox.getText().isEmpty()) {
			
			Window.alert("Bitte füllen Sie alle Felder aus!");}
			
			else {
				if (s2.equals(s3)) {
			

			final Nutzer n = new Nutzer();
			n.setNickname(usertextBox.getText());
			n.setPassWort(passwordTextBox.getText());
			n.setVorname(vntextBox.getText());
			n.seteMail(emailtextBox.getText());
			n.setNachname(nntextBox.getText());
			n.setErstellZeitpunkt(aktuellesDatum = new Timestamp(System
					.currentTimeMillis()));

			service.sucheNickName(usertextBox.getText(),
					new AsyncCallback<Boolean>() {

						@Override
						public void onFailure(Throwable caught) {
							logger.severe("Fehler beim Auslesen der Nicknames");

						}

						@Override
						public void onSuccess(Boolean result) {

							if (result == true) {
								usertextBox
										.setStylePrimaryName("nickNameVorhanden");
								usertextBox
										.setText("Nutzer bereits vorhanden!");

							}

							else {

								service.nutzerAnlegen(n,
										new AsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												RootPanel.get("Beitrag")
														.clear();
												System.out
														.println("User angelegt: "
																+ s1);
												NutzerLogin login = new NutzerLogin();

												login.loadLoginView();
												logger.severe("Neuen Nutzer registriert!");

											}

											@Override
											public void onFailure(
													Throwable caught) {
												logger.severe("Fehler beim Registrieren eines neuen Nutzers");
											
											}
										});
							
							}
						
						}

					});
		}

		else {
			Window.alert("Das von Ihnen eingegebene Passwort stimmt nicht überein.\nVersuchen Sie es erneut!");
		}

	}
}}
