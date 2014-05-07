package de.elite.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


//import de.tigertech.appnavigation.client.loginview.LoginFooter;
//import de.tigertech.appnavigation.client.loginview.LoginHeader;


public class Login extends Composite {
	
	
private String password = "test";
private String user = "test";
	
private FlexTable flexTable = new FlexTable();
private DecoratorPanel decPanel = new DecoratorPanel();
	
private String passwortBezeichner = "Passwort:";
private String nutzerNameBezeichner = "Nutzer:";
private String ueberschrift = "Login zur Pinnwand";

private VerticalPanel loginPanel = new VerticalPanel();
private Button loginButton = new Button("Login");
private PasswordTextBox passwortFeld = new PasswordTextBox();
private TextBox nutzerNameFeld = new TextBox();
    
    
   public Login () {
	   initWidget(this.loginPanel);
	   
	   FlexCellFormatter zellenFormatierer = flexTable.getFlexCellFormatter();
	   
	   
	   
       // Add a title to the form
       flexTable.setHTML(0, 0, this.ueberschrift);
       zellenFormatierer.setColSpan(0, 0, 2);
       zellenFormatierer.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
       
       // Add username and password fields
       nutzerNameFeld.setWidth("150px");
       passwortFeld.setWidth("150px");
       flexTable.setHTML(1, 0, this.nutzerNameBezeichner);
       flexTable.setWidget(1, 1, nutzerNameFeld);
       flexTable.setHTML(2, 0, passwortBezeichner);
       flexTable.setWidget(2, 1, passwortFeld);
       
       
       //Add the loginbutton to the form
       flexTable.setWidget(3, 0, loginButton);
       //ClickHandler dem Button hinzufügen
       loginButton.addClickHandler(new LoginClickHandler());
       zellenFormatierer.setColSpan(3, 0, 2);
       zellenFormatierer.setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
       
    // Wrap the content in a DecoratorPanel
       decPanel.setWidget(flexTable);
       
       
      // this.loginPanel.setWidth(windowWidth/2 + "px");
      // this.loginPanel.setHeight(windowHeight*0.6 + "px");      
      // this.loginPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
      // this.loginPanell.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
      // this.loginPanel.add(logo);
     //  this.loginPanel.add(secondoHeadline);
       this.loginPanel.add(decPanel);
       

	   
   }
   //ClickHandler für den Button
	private class LoginClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			checkUser();
		}
	}

	
	//Methode überprüft Nutzer/Vorhandensein von Nutzer/Password
	 
	
public void checkUser() {
		if ((nutzerNameFeld.getValue() == "") || (passwortFeld.getValue() == "")) {
			System.out.println("Bitte Benutzername und Passwort eingeben!");
			return;
		}
		else if ((nutzerNameFeld.getText()).equals(user) && (passwortFeld.getText()).equals(password)) {
			System.out.println("Erfolgreich eingeloggt!");
		}
		else {
			System.out.println("Falscher Benutzer/Passwort");
			return;
		}
	}
}