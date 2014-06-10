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

public class AccountEdit {
	
	private Nutzer nutzer;
	
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);
	Logger logger = SocialMediaPinnwand.getLogger();
	
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
	private Button bestaetigenButton;
	FlexTable editUser = new FlexTable();
	
	//Textboxen für changes
	
	private TextBox vorNameTextBox = new TextBox();
	private TextBox nachNameTextBox = new TextBox();
	private TextBox nickNameTextBox = new TextBox();
	private TextBox passWortTextBox = new TextBox();
	private TextBox eMailTextBox = new TextBox();
	
	//Account Loeschen?
	
	
	private Label accountDelLabel = new Label("Account Loeschen");
	private PushButton deleteAccountButton = new PushButton(new Image("images/loeschen.png"));
	

	public void editNutzer() {
		
		//Widgets erzeugen für Beitrag

		this.Vorname = new Label("");
		this.Nachname = new Label("");
		this.Nickname = new Label("");
		this.Passwort = new Label("");
		this.Email = new Label("");
		this.bestaetigenButton = new Button("Bestaetigen");
		
		this.bestaetigenButton.setEnabled(false);

		
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
		editUser.setWidget(6, 0, bestaetigenButton);
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
		this.bestaetigenButton.addClickHandler(new okButtonClickHandler());
		this.deleteAccountButton.addClickHandler(new deleteAccountButtonClickHandler());
	}
	//Vorname 
	private class infoClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			nutzerDatenAendern();
			bestaetigenButton.setEnabled(true);
		}
	}
	//Umwandlungen der Labels zu Textboxen
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
	}

	//Bestätige Ergebnisse
	
	private class okButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			updateNutzer();
		}
	}
	//Erstelle neues Nutzerobjekt
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
				bestaetigenButton.setEnabled(false);
				Window.alert("Nutzerdaten geaendert!");
			}
		});
	}
	
	//Nutzer Löschen
	private class deleteAccountButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			nutzerLoeschen();
		}
	}
	
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