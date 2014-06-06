package de.elite.itprojekt.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;




public class KommentarErstellen {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private Label eingeloggterUser;
	private PushButton bearbeiten;
	private Button loeschen;
	private Label textBeitrag;
	private Label datumsAnzeige;
	FlexTable kommentarFlexTable = new FlexTable();
	
	
	
	
	


	public void addKommentar(Nutzer nutzer, Beitrag beitrag) {
		
		//Widgets erzeugen für Beitrag

		this.eingeloggterUser = new Label("Donald Duck");
		this.bearbeiten = new PushButton("Bearbeiten");
		this.loeschen = new Button();
		this.textBeitrag = new Label("Methode die dann die Kommentare holen soll! Methode die dann die Kommentare holen soll! Methode die dann die Kommentare holen soll! Methode die dann die Kommentare holen soll! Methode die dann die Kommentare holen soll! Methode die dann die Kommentare holen soll! Methode die dann die Kommentare holen soll! Methode die dann die Kommentare holen soll! ");
		this.datumsAnzeige = new Label("24.06.2014 um 12:00 Uhr");
		
		//CSS Bezeichner
		this.loeschen.setStylePrimaryName("Loeschen");
		this.eingeloggterUser.setStylePrimaryName("NutzerName");
		this.datumsAnzeige.setStylePrimaryName("Date");
		this.textBeitrag.setStylePrimaryName("umBruch");
		
		kommentarFlexTable.setStyleName("panelk flexTablek");
		
		kommentarFlexTable.setWidget(0, 0, eingeloggterUser);
		kommentarFlexTable.setWidget(0, 1, bearbeiten);
		kommentarFlexTable.setWidget(0, 2, loeschen);
		kommentarFlexTable.setWidget(1, 0, textBeitrag);
		kommentarFlexTable.setWidget(2, 0, datumsAnzeige);
		
		
		
		
		
		this.vPanel.add(kommentarFlexTable);
		
		RootPanel.get("Kommentar").add(vPanel);
		
		
		

	}
}