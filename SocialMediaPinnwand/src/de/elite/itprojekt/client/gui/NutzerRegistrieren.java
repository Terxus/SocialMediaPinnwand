package de.elite.itprojekt.client.gui;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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

import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Nutzer;


public class NutzerRegistrieren {
	
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class);

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
	private Hyperlink loginLink = new Hyperlink("Zurueck zum Login", false, "Login");
	
	//Datumsfunktion
	
	private Timestamp aktuellesDatum;

  
  
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
	
	//So ist die zu zuweisung komischerweise nich mehr deprecated
	
	loginLink.addDomHandler(new LoginLinkClickHandler(), ClickEvent.getType());
	button.addClickHandler(new registerClickHandler());
	
	
	decPanelRegister.add(tableFlex);
	  
	  
	RootPanel.get("Beitrag").clear();
	RootPanel.get("Navigator").clear();
	RootPanel.get("Beitrag").add(decPanelRegister);
	  
  }
  
  private class LoginLinkClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		RootPanel.get("Beitrag").clear();
		NutzerLogin login = new NutzerLogin();
		
		login.loadLoginView();
	}
  }
  
  private class registerClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		neuenNutzerRegistrieren();
	}
  }
  
  
  
  //Die Registration
  public void neuenNutzerRegistrieren() {

//Von Domi
	  final String s1 = usertextBox.getText();
		String s2 = passwordTextBox.getText();
		String s3 = passwordTextBox_1.getText();
		String s4 = emailtextBox.getText();
		String s5 = vntextBox.getText();
		String s6 = nntextBox.getText();
		if (s2.equals(s3)) {


			Nutzer n = new Nutzer();
			n.setNickname(usertextBox.getText());
			n.setPassWort(passwordTextBox.getText());
			n.setVorname(vntextBox.getText());
			n.seteMail(emailtextBox.getText());
	        n.setNachname(nntextBox.getText());
	        n.setErstellZeitpunkt(aktuellesDatum = new Timestamp(System.currentTimeMillis()));


			service.nutzerAnlegen(n, new AsyncCallback<Void>() {

				@Override
				public void onSuccess(Void result) {
					RootPanel.get("Beitrag").clear();
					System.out.println("User angelegt: " +s1);
					NutzerLogin login = new NutzerLogin();

					login.loadLoginView();

				}

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("upps");
				}
			});
		}
	  //Ende von Domi
	  
	  
	  
  }
}
