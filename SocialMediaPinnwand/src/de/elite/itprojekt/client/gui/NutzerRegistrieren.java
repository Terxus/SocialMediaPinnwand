package de.elite.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;


public class NutzerRegistrieren {

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
	
	
	
	
}
