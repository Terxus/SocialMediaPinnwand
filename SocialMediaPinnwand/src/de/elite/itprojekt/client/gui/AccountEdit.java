package de.elite.itprojekt.client.gui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.client.SocialMediaPinnwand;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Nutzer;

/**
 * Diese Klasse stellt die Kontoverwaltung des Nutzers dar.
 * Der eingeloggte Nutzer kann hier seine Daten, sprich seinen Vor- und Nachnamen, Passwort und E-Mail Adresse
 * ändern. Einzig der Nickname kann nicht geändert werden da er in unserem System ein eindeutiges
 * Erkennungsmerkmal darstellt.
 * Eine weitere Funktion der Klasse ist, dass man hier seinen Account löschen kann. Es wird nicht nur der 
 * Nutzer gelöscht sondern alle zusammenhängenden Referenzen mit diesem. Diese wären die Pinnwand, Beiträge,
 * Kommentare, Likes und sämtliche Abonnementbeziehungen.
 * 
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class AccountEdit {
	
	/**
	 * Ein Nutzer welcher Aktuell eingeloggt ist.
	 */
	
	private Nutzer nutzer;

	/**
	 * <b>service</b> heißt unsere Proxyreferenz, welche mit dem Server interagiert.
	 */
	
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);
	
	/**
	 * Ein kleiner Logger der die System.out's in den Client bringen soll. Wird benutzt um die Errors auszulesen.
	 */
	
	Logger logger = SocialMediaPinnwand.getLogger();

	public AccountEdit() {
		logger.severe("Kontoansicht geladen");
	}

	/**
	 * Diese Methode durchsucht den Clienten nach einem Cookie Namens: <b>gp5cookie</b>
	 * In diesem Cookie ist die eindeutige ID gespeichert, die einem Nutzer zuordenbar ist.
	 * Nachdem die ID ausgelesen ist, wird auf der Server bzw. in der Datenbank nach einem Nutzer-Tupel gesucht 
	 * mit dieser ID, und das Nutzerobjekt erstellt. Danach wird das Nutzerobjekt zurückgegeben.
	 */

	public void holeNutzer() {
		service.sucheNutzerID(Integer.valueOf(Cookies.getCookie("gp5cookie")), new AsyncCallback<Nutzer>() {
			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Nutzer konnte nicht ausgelesen werden.");
			}
			@Override
			public void onSuccess(Nutzer result) {
				setNutzer(result);
			}
		});
	}
	
	/**
	 * Hier wird der aktuell eingeloggte Nutzer festgelegt.
	 * @param eingeloggterNutzer
	 */

	public void setNutzer(Nutzer eingeloggterNutzer) {
		this.nutzer = eingeloggterNutzer;
		this.Vorname.setText(this.nutzer.getVorname());
		this.Nachname.setText(this.nutzer.getNachname());
		this.Nickname.setText(this.nutzer.getNickname());
		this.Passwort.setText(this.nutzer.getPassWort());
		this.Email.setText(this.nutzer.geteMail());
	}

	private VerticalPanel vPanel = new VerticalPanel();
	private Label Vorname;
	private Label VornamenLabel = new Label("Vorname:");
	private Label Nachname;
	private Label NachnamenLabel = new Label("Nachname:");
	private Label Nickname;
	private Label NicknamenLabel = new Label("Nickname:");
	private Label Passwort;
	private Label PasswortLabel = new Label("Passwort:");
	private Label Email;
	private Label EmailLabel = new Label("Email:");
	private Button bestaetigenLoeschenButton;
	private Button bestaetigenEditierenButton;
	FlexTable editUser = new FlexTable();

	private TextBox vorNameTextBox = new TextBox();
	private TextBox nachNameTextBox = new TextBox();
	private TextBox nickNameTextBox = new TextBox();
	private TextBox passWortTextBox = new TextBox();
	private TextBox eMailTextBox = new TextBox();

	private Label accountDelLabel = new Label("Account Loeschen");
	private PushButton deleteAccountButton = new PushButton(new Image("images/loeschen.png"));
	
	/**
	 * Erzeugung der Widgets
     *
	 */

	public void editNutzer() {

		//Widgets erzeugen für Beitrag

		this.Vorname = new Label("");
		this.Nachname = new Label("");
		this.Nickname = new Label("");
		this.Passwort = new Label("");
		this.Email = new Label("");
		this.bestaetigenLoeschenButton = new Button("Bestätigen");
		this.bestaetigenEditierenButton = new Button("Bestätigen");

		this.bestaetigenLoeschenButton.setVisible(false);
		this.bestaetigenEditierenButton.setVisible(false);

		editUser.setWidget(0, 0, VornamenLabel);
		editUser.setWidget(0, 1, Vorname);
		editUser.setWidget(1, 0, NachnamenLabel);
		editUser.setWidget(1, 1, Nachname);
		editUser.setWidget(2, 0, NicknamenLabel);
		editUser.setWidget(2, 1, Nickname);
		editUser.setWidget(3, 0, PasswortLabel);
		editUser.setWidget(3, 1, Passwort);
		editUser.setWidget(4, 0, EmailLabel);
		editUser.setWidget(4, 1, Email);
		editUser.setWidget(6, 0, bestaetigenLoeschenButton);
		editUser.setWidget(7, 0, bestaetigenEditierenButton);
		editUser.setWidget(6, 1, accountDelLabel);
		editUser.setWidget(6, 2, deleteAccountButton);


		this.vPanel.add(editUser);

		//Styles
		this.VornamenLabel.setStylePrimaryName("editUser");
		this.NachnamenLabel.setStylePrimaryName("editUser");
		this.NicknamenLabel.setStylePrimaryName("editUser");
		this.PasswortLabel.setStylePrimaryName("editUser");
		this.EmailLabel.setStylePrimaryName("editUser");

		RootPanel.get("Beitrag").add(vPanel);
		holeNutzer();

		this.Vorname.addClickHandler(new infoClickHandler());
		this.Nachname.addClickHandler(new infoClickHandler());
		this.Nickname.addClickHandler(new infoClickHandler());
		this.Email.addClickHandler(new infoClickHandler());
		this.Passwort.addClickHandler(new infoClickHandler());
		this.bestaetigenEditierenButton.addClickHandler(new okButtonClickHandler());
		this.bestaetigenLoeschenButton.addClickHandler(new bestaetigenLoeschenButton());
		this.deleteAccountButton.addClickHandler(new deleteAccountButtonClickHandler());
	}

	/**
	 * Mit einem Klick auf ein beliebiges Label in der Kontoverwaltung wird die Methode
	 *  {@link nutzerDatenAendern} aufgerufen.
	 * 
	 */
	
	private class infoClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			bestaetigenEditierenButton.setVisible(true);
			bestaetigenLoeschenButton.setVisible(false);
			accountDelLabel.setVisible(false);
			deleteAccountButton.setVisible(false);		
			nutzerDatenAendern();

		}
	}
	
	/**
	 * Diese Methode sorgt für die Umwandlungen der Labels in Eingabefelder.
	 * Mit einem Klick auf ein Label erscheinen Eingabefelder in denen die Daten geändert werden können
	 * @param void
	 */

	public void nutzerDatenAendern() {
		vorNameTextBox.setText(this.Vorname.getText().toString()); //Hier wird der alte Name in die Textbox geschrieben
		editUser.setWidget(0, 1, vorNameTextBox); //Das Label an der Position 0, 1 wird mit der Textbox überschrieben
		nachNameTextBox.setText(this.Nachname.getText().toString());
		editUser.setWidget(1, 1, nachNameTextBox);
		nickNameTextBox.setText(this.Nickname.getText().toString());
		editUser.setWidget(2, 1, nickNameTextBox);
		nickNameTextBox.setReadOnly(true);
		passWortTextBox.setText(this.Passwort.getText().toString());
		editUser.setWidget(3, 1, passWortTextBox);
		eMailTextBox.setText(this.Email.getText().toString()); 
		editUser.setWidget(4, 1, eMailTextBox);
		accountDelLabel.setVisible(false);
		deleteAccountButton.setVisible(false);		
	}

	//Bestätige Ergebnisse

	private class okButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			updateNutzer();
		}
	}

	/**
	 * Diese Methode liest die geänderten Daten aus und setzt die Daten in ein neues Nutzerobjekt ein.
	 * Anschließend wird die Methode {@link updateNutzerinDB} aufgerufen
	 * 
	 * @param void
	 */
	
	public void updateNutzer() {

		if (passWortTextBox.getValue().isEmpty()) {
			Window.alert("Passwort darf nicht leer sein!");
		}
		else {
		Nutzer updatedNutzer = new Nutzer();
		updatedNutzer.setID(nutzer.getID());
		updatedNutzer.setVorname(vorNameTextBox.getText());
		updatedNutzer.setNachname(nachNameTextBox.getText());
		updatedNutzer.setNickname(nickNameTextBox.getText());
		updatedNutzer.setPassWort(passWortTextBox.getText());
		updatedNutzer.seteMail(eMailTextBox.getText());
		updateNutzerinDB(updatedNutzer);

		}

	}
	
	/**
	 * Diese Methode schreibt dann letztendlich die Werte aus dem aktualisierten Nutzerobjekt in die Datenbank.
	 * 
	 * @param neuerNutzer
	 */
	
	public void updateNutzerinDB(Nutzer neuerNutzer) {

		service.updateNutzer(neuerNutzer, new AsyncCallback<Nutzer>() {
			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Nutzer konnte nicht aktualisiert werden!");
			}
			@Override
			public void onSuccess(Nutzer result) {

				//Textboxen wieder in Labels verwandeln mit dem aktuellen Text

				Vorname.setText(vorNameTextBox.getText().toString());
				Nachname.setText(nachNameTextBox.getText().toString());
				Nickname.setText(nickNameTextBox.getText().toString());
				Passwort.setText(passWortTextBox.getText().toString());
				Email.setText(eMailTextBox.getText().toString());
				editUser.setWidget(0, 1, Vorname);
				editUser.setWidget(1, 1, Nachname);
				editUser.setWidget(2, 1, Nickname);
				editUser.setWidget(3, 1, Passwort);
				editUser.setWidget(4, 1, Email);
				bestaetigenEditierenButton.setVisible(false);
				Window.alert("Nutzerdaten geaendert!");
				accountDelLabel.setVisible(true);
				deleteAccountButton.setVisible(true);
			}
		});
	}

	private class deleteAccountButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			Window.alert("Bitte klicken Sie auf bestätigen wenn Sie ihren Account löschen wollen!");
			bestaetigenLoeschenButton.setVisible(true);
			bestaetigenEditierenButton.setVisible(false);
		}
	}

	private class bestaetigenLoeschenButton implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
		nutzerLoeschen();
		}
	}

	/**
	 * Diese Methode löscht den Nutzer aus dem System.
	 * 
	 * @param void
	 */
	
	public void nutzerLoeschen() {
		service.nutzerLoeschen(nutzer, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Nutzer konnte nicht gelöscht werden.");

			}
			@Override
			public void onSuccess(Void result) {
				logger.severe("Nutzer gelöscht!");
				logout();
			}
		});
	}

	/**
	 * Nachdem der Nutzer bestätigt hat seinen Account zu löschen wird er automatisch ausgeloggt.
	 * Sein Cookie wird gelöscht und er wird zum Anmeldebildschirm weitergeleitet.
	 * 
	 * @param neuerNutzer
	 */
	public void logout() {
	 Window.alert("Account gelöscht!");
   	 Cookies.removeCookie("gp5cookie");
     RootPanel.get("Navigator").clear();
     RootPanel.get("Beitrag").clear();
     RootPanel.get("neuer_Beitrag").clear();
     RootPanel.get("Kommentar").clear();
     RootPanel.get("Header").clear();
     NutzerLogin nl = new NutzerLogin();
     nl.loadLoginView();
	}
	
	
}