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

	//Get the Nutzer
	
	private Nutzer nutzer;
	
	public Nutzer getNutzer() {
		return this.nutzer;
	}
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
	

	
	  //Startpage <b>HOME</b>
		private DecoratorPanel decPanel = new DecoratorPanel();
		private TextBox nutzerNameBox = new TextBox();
		private PasswordTextBox passwortBox = new PasswordTextBox();
		private Button loginButton = new Button("Login");
		private Label errorLabel = new Label();
		private DialogBox dialogBox = new DialogBox();
		private HTML successHTML = new HTML();
		private Button closeButton = new Button("Close");
		private FlexTable loginFlex = new FlexTable();
		private Label nutzerName = new Label("Nutzer:");
		private Label passwort = new Label("Passwort:");
		private Hyperlink registerLink = new Hyperlink("Neuen Nutzer registrieren", false,"Register");
		
		
		private Label willKommenLabel = new Label();


	  public void loadLoginView() {
			
		//FlexTable für Login
		
		loginFlex.setWidget(0, 1, nutzerNameBox);
		loginFlex.setWidget(1, 1, passwortBox);
		loginFlex.setWidget(0, 0, nutzerName);
		loginFlex.setWidget(1, 0, passwort);
		loginFlex.setWidget(2, 0, loginButton);
		loginFlex.setWidget(2, 1, registerLink);
		loginFlex.setWidget(3, 0, errorLabel);
		
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
		  
		  
			service.sucheNutzerID(Integer.valueOf(Cookies.getCookie("gp5cookie")), new AsyncCallback<Nutzer>() {
				@Override
				public void onFailure(Throwable caught) {
					System.out.println("Fehler");
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
					
					//Zeigt nur eigene an
					beitrag.zeigeAlleBeitraege(result);
					beitrag.beitragHinzufuegen();
					
					
					//Zeigt abobeiträge an
					beitrag.abonnementBeitraegeAnzeigen(result);
					

		//			PinnwandDarstellung pnd = new PinnwandDarstellung();
		//			pnd.zeigeAlleBeitraege(result);
					
				}
			});
		  

		//KommentarErstellen kommentar = new KommentarErstellen();
		  
		/*  
	    final Label welcomeMsg = new Label("welcome");
	    welcomeMsg.addStyleName("welcomeMsg");*/
		
	    
	 
	    //beitrag.beitragAnzeigen();
	    
	    
	    //kommentar.addKommentar();
	    
	    /*RootPanel.get("Header").add(welcomeMsg);
		Window.alert("New page loaded");*/
	  }
	  
	  public void refreshPinnwand() {
		  
			service.sucheNutzerID(Integer.valueOf(Cookies.getCookie("gp5cookie")), new AsyncCallback<Nutzer>() {
				@Override
				public void onFailure(Throwable caught) {
					System.out.println("Fehler");
				}
				@Override
				public void onSuccess(Nutzer result) {
					setNutzer(result);
					/*
					//Contentobjekte Erzeugen
					Navigation navi = new Navigation();
					BeitragErstellen beitrag = new BeitragErstellen();
					
					//Beiträge Anzeigen lassen per User
					beitrag.zeigeAlleBeitraege(result);
					navi.addNavigation(result);
					navi.getAbonnierteNutzerListe(result);
					beitrag.beitragHinzufuegen();
					*/
					
					BeitragErstellen beitrag = new BeitragErstellen();
					Navigation navi = new Navigation();
					

					navi.addNavigation(result);
					navi.getAbonnierteNutzerListe(result);
					
					//Zeigt nur eigene an
					beitrag.zeigeAlleBeitraege(result);
					beitrag.beitragHinzufuegen();
					
					
					//Zeigt abobeiträge an
					beitrag.abonnementBeitraegeAnzeigen(result);
					
				}
			});

		  /*
		  //Contentobjekte erzeugen
			Navigation navi = new Navigation();
			BeitragErstellen beitrag = new BeitragErstellen();
			
			
			//Beiträge wieder anzeigen
			
			beitrag.zeigeAlleBeitraege(holeNutzer());
			
			
			//KommentarErstellen kommentar = new KommentarErstellen();
			
			//Contentobjektmethode zum aufbauen aufrufen
			
			navi.addNavigation();
			//beitrag.beitragAnzeigen();
			beitrag.beitragHinzufuegen();
			//kommentar.addKommentar();
			 * 
			 */
		  
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
					Window.alert("Bitte Benutzername und Passwort eingeben!");
				}
				else {
					checkUser();
				}
	  }
	  
	  public void checkUser() {		  
			service.loginCheck(nutzerNameBox.getText(), passwortBox.getText(), new AsyncCallback<Nutzer>() {
				
				@Override
				public void onSuccess(Nutzer result) {
					if (result.getID() != 0) {
						Cookies.setCookie("gp5cookie", String.valueOf(result.getID()));
						RootPanel.get("Navigator").clear(true);
						RootPanel.get().clear();
						login();

						
						
						//Willkommensnachricht
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
				}
			});

		}

	  private void login() {
		  loadPinnwand();
	  }
}
