package de.elite.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
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


public class NutzerLogin {

	  
	  //Startpage <b>HOME</b>
		private DecoratorPanel decPanel = new DecoratorPanel();
		private TextBox userNameBox = new TextBox();
		private TextBox emailBox = new TextBox();
		private PasswordTextBox passwordBox = new PasswordTextBox();
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
		  

		//FlexTable für Login
		
		loginFlex.setWidget(1, 1, userNameBox);
		loginFlex.setWidget(2, 1, emailBox);
		loginFlex.setWidget(3, 1, passwordBox);
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
		
		userNameBox.setFocus(true);
		
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

	  private void loadPinnwand() {
		  
		TopLevelNavigation topLevelNavi = new TopLevelNavigation();   
		Navigation navi = new Navigation();
		Beitrag beitrag = new Beitrag();
		Kommentar kommentar = new Kommentar();
		  
		  
	    final Label welcomeMsg = new Label("welcome");
	    welcomeMsg.addStyleName("welcomeMsg");
	    RootPanel.get("Navigator").clear();
	    
	    topLevelNavi.addTopLevelNavi();
	    navi.addNavigation();
	    beitrag.beitragAnzeigen();
	    beitrag.beitragHinzufuegen();
	    kommentar.addKommentar();
	    
	    RootPanel.get("Header").add(welcomeMsg);
		Window.alert("New page loaded");
		
		
		
		
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
			login();
		}
	  }
	  private void login() {
		  loadPinnwand();
	  }
	

}
