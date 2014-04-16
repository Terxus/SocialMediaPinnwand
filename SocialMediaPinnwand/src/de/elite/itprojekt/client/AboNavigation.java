package de.elite.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;



public class AboNavigation extends Composite {
	
	//Composite: GWT Klasse: Widgets Composed out of other Widgets
	
	private VerticalPanel vPanel = new VerticalPanel();
	
	
	//Muss deklariert werden, da auf die Textbox sonst nicht zugegriffen werden kann von ausserhalb der methode onModuleLoad()
	private static Label AbonnierteUser;
	
	//Static damit die Methode anzeigen von AboSuche ausgef�hrt werden kann.
	
	private Label ZuAbonnierendeUser;
	//Selbes Problem wie oberhalb mit dem Labelobject
	private Button aboLoeschen;
	
	private Button testButton;
	
	
	public AboNavigation() {
		initWidget(this.vPanel);
		
		//////////////////ABOSUCHE
		
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setBorderWidth(2);
	
		//Die Abonnierten User anzeigen
		
		AboNavigation.AbonnierteUser = new Label("Gundel Gaukeley");
		hPanel.add(AbonnierteUser);
		
		vPanel.add(hPanel);
		
		//////////////////ENDE ABOSUCHE
		
		
		////////////////////ABO L�SCHEN
		
		HorizontalPanel hPanel1 = new HorizontalPanel();
		hPanel1.setBorderWidth(2);
		
		//Die Abosuchmethode aufrufen, die noch nicht abonnierte Anzeigen soll
		this.ZuAbonnierendeUser = new Label("Dagobert Duck");
		hPanel1.add(ZuAbonnierendeUser);
		
		
		this.aboLoeschen = new Button("AboDel");
		hPanel1.add(aboLoeschen);
		
		vPanel.add(hPanel1);
		
		/////////////////////ENDE ABO L�SCHEN
		
		///TESTESTESTSETSET
		this.testButton = new Button("AboAdd");
		hPanel.add(testButton);
		
		testButton.addClickHandler(new ButtonClickHandler());
		/// ENDE TESTEESTS

	}
	
	public static void anzeigen(String text) {
		AbonnierteUser.setText(text);
	}
	//Static damit die Methode anzeigen von AboSuche ausgef�hrt werden kann.

	//// TETESTESTSETSE
	private class ButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			AbonnierteUser.setText("Donald Duck");

		}
	}
	///ENDE TESTSETSETS
}