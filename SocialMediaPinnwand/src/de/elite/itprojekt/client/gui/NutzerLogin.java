package de.elite.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Nutzer;


public class NutzerLogin {
	
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);
	
	  
	  //Startpage <b>HOME</b>
		private DecoratorPanel decPanel = new DecoratorPanel();
		private TextBox nutzerNameBox = new TextBox();
		private TextBox emailBox = new TextBox();
		private PasswordTextBox passwortBox = new PasswordTextBox();
		private Button loginButton = new Button("Login");
		private Label errorLabel = new Label();
		private DialogBox dialogBox = new DialogBox();
		private HTML successHTML = new HTML();
		private Button closeButton = new Button("Close");
		private FlexTable loginFlex = new FlexTable();
		private Label nutzerName = new Label("Nutzer:");
		private Label email = new Label("Email:");
		private Label passwort = new Label("Passwort:");
		private Hyperlink registerLink = new Hyperlink("Neuen Nutzer registrieren", false,"Register");

	  public void loadLoginView() {
			
		  //Erstmal alles aufräumen da! :D <- Dafür eine Methode schreiben, da sonst etwas hässlich im Code


		//FlexTable für Login
		
		loginFlex.setWidget(1, 1, nutzerNameBox);
		loginFlex.setWidget(2, 1, emailBox);
		loginFlex.setWidget(3, 1, passwortBox);
		loginFlex.setWidget(1, 0, nutzerName);
		loginFlex.setWidget(2, 0, email);
		loginFlex.setWidget(3, 0, passwort);
		loginFlex.setWidget(4, 0, loginButton);
		loginFlex.setWidget(4, 1, registerLink);
		loginFlex.setWidget(5, 0, errorLabel);
		
		decPanel.add(loginFlex);
		
		
		// HTML element hinzufügen
		
		RootPanel.get("Navigator").add(decPanel);
		
		// cursor in die input box
		
		nutzerNameBox.setFocus(true);
		
		//style 
		
		errorLabel.setStyleName("errorLabel");
		errorLabel.setVisible(false);
		
		
		//Popupbox
		dialogBox.setText("Login vollbracht! yay!");
		dialogBox.setAnimationEnabled(true);
		
		closeButton.getElement().setId("closeButton");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(successHTML);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		//CloseClickHandler zu der Dialogbox adden
		
		closeButton.addClickHandler(new CloseButtonClickHandler());
		registerLink.addDomHandler(new RegistrierClickHandler(), ClickEvent.getType());
		loginButton.addClickHandler(new LoginClickHandler());
		


	  }

	  public void loadPinnwand() {
		  
		TopLevelNavigation topLevelNavi = new TopLevelNavigation();
		Navigation navi = new Navigation();
		Beitrag beitrag = new Beitrag();
		Kommentar kommentar = new Kommentar();
		  
		/*  
	    final Label welcomeMsg = new Label("welcome");
	    welcomeMsg.addStyleName("welcomeMsg");*/
		
	    
	    topLevelNavi.addTopLevelNavi();
	    navi.addNavigation();
	    beitrag.beitragAnzeigen();
	    beitrag.beitragHinzufuegen();
	    kommentar.addKommentar();
	    
	    /*RootPanel.get("Header").add(welcomeMsg);
		Window.alert("New page loaded");*/
	  }
	  
	  public void refreshPinnwand() {

		  
		  //Contentobjekte erzeugen
			Navigation navi = new Navigation();
			Beitrag beitrag = new Beitrag();
			Kommentar kommentar = new Kommentar();
			
			//Contentobjektmethode zum aufbauen aufrufen
			
			navi.addNavigation();
			beitrag.beitragAnzeigen();
			beitrag.beitragHinzufuegen();
			kommentar.addKommentar();
		  
	  }

	  // ClickHandler #1
	  private class CloseButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			dialogBox.hide();
			loginButton.setEnabled(true);
			loginButton.setFocus(true);
		}
	  }
	  // ClickHandler der neuen User hinzufügen soll
	  
	  private class RegistrierClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			NutzerRegistrieren register = new NutzerRegistrieren();
			register.nutzerRegistrieren();
		}
	  }
	  
	  // ClickHandler der einen Login ermöglichen soll
	  private class LoginClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			checkValues();
		}
	  }
	  
	  
	  
	  
	  
	  private void checkValues() {
				if ((nutzerNameBox.getValue() == "") || (passwortBox.getValue() == "")) {
					System.out.println("Bitte Benutzername und Passwort eingeben!");
				}
				else {
					checkUser();
				}
	  }
	  public void checkUser() {
		  
			System.out.println("Jetzt sollte eigentlich das Async geladen werden");
		  
			service.loginCheck(nutzerNameBox.getText(), passwortBox.getText(), new AsyncCallback<Nutzer>() {
				
				@Override
				public void onSuccess(Nutzer result) {
					if (result.getID() != 0) {
						RootPanel.get("Navigator").clear(true);
						Navigation navi = new Navigation();
						navi.setNutzer(result);
						RootPanel.get().clear();
						login();
						Cookies.setCookie("gp5cookie", result.getVorname() + " " + result.getNachname());
						System.out.println("User loggad in undso!");
					} 
					else {
						Window.alert("Benutzer oder Passwort inkorrekt");
					}
				}
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert("Keine DB-Verbindung");
				}
			});

		}
			
	

	  
	  private void login() {
		  loadPinnwand();
	  }
}
