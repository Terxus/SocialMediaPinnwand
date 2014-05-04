package de.elite.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;



public class AboNavigation extends Composite {
	
	//Composite: GWT Klasse: Widgets Composed out of other Widgets -> Wird eventuell nich benötigt lt. Thies?! <- Überprüfen
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private HorizontalPanel hPanel1 = new HorizontalPanel();
	
	
	//Muss deklariert werden, da auf die Textbox sonst nicht zugegriffen werden kann von ausserhalb der methode onModuleLoad()
	private static Label AbonnierteUser;
	
	//Static damit die Methode anzeigen von AboSuche ausgeführt werden kann.
	
	private Label ZuAbonnierendeUser;
	//Selbes Problem wie oberhalb mit dem Labelobject
	private PushButton aboLoeschen;
	
	private PushButton aboAddButton;
	
	//Diese Objekte müssen ausserhalb deklariert Werden, da sonst nicht auf die ClickHandler zugegriffen werden kann.
	
	
	public AboNavigation() {
		initWidget(this.vPanel);
		
		//////////////////ABOSUCHE
		
		hPanel.setBorderWidth(1);
	
		//Die Abonnierten User anzeigen
		
		AboNavigation.AbonnierteUser = new Label("Gundel Gaukeley");
		hPanel.add(AbonnierteUser);
		
		vPanel.add(hPanel);
		
		//////////////////ENDE ABOSUCHE
		
		
		////////////////////ABO LÖSCHEN
		
		hPanel1.setBorderWidth(1);
		
		//Die Abosuchmethode aufrufen, die noch nicht abonnierte Anzeigen soll
		this.ZuAbonnierendeUser = new Label("Dagobert Duck");
		hPanel1.add(ZuAbonnierendeUser);
		
		
		
		this.aboLoeschen = new PushButton(new Image("socialmediapinnwand/gwt/clean/images/loeschen.png"));
		hPanel1.add(aboLoeschen);
		
		aboLoeschen.addClickHandler(new AboDeleteClickandler());
		
		vPanel.add(hPanel1);
		
		/////////////////////ENDE ABO LÖSCHEN
		
		///TESTESTESTSETSET
		this.aboAddButton = new PushButton(new Image("socialmediapinnwand/gwt/clean/images/hinzufuegen.png"));
		hPanel.add(aboAddButton);
		
		aboAddButton.addClickHandler(new ButtonClickHandler());
		/// ENDE TESTEESTS

	}
	
	public static void anzeigen(String text) {
		AbonnierteUser.setText(text);
	}
	//Static damit die Methode anzeigen von AboSuche ausgeführt werden kann.

	//// TETESTESTSETSE
	private class ButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			AbonnierteUser.setText("Donald Duck");

		}
	}
	
	private class AboDeleteClickandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			hPanel1.setVisible(false);
		}
	}
	
	///ENDE TESTSETSETS
}
