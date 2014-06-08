package de.elite.itprojekt.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.shared.bo.Kommentar;




public class KommentarErstellen {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private Label eingeloggterUser;
	private PushButton bearbeiten;
	private Button loeschen;
	private Label textBeitrag;
	private Label datumsAnzeige;
	FlexTable kommentarFlexTable = new FlexTable();
	
	
	
	
	


	public void addKommentar(Kommentar kommentar) {
		
		//Widgets erzeugen für Beitrag

		this.eingeloggterUser = new Label(kommentar.getNutzer().getVorname() + " " + kommentar.getNutzer().getNachname());
		this.bearbeiten = new PushButton("Bearbeiten");
		this.loeschen = new Button();
		this.textBeitrag = new Label(kommentar.getText());
		this.datumsAnzeige = new Label(kommentar.getErstellZeitpunkt().toString());
		
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
		
		
		
		
		kommentarFlexTable.addStyleName("Kommentar");
		this.vPanel.add(kommentarFlexTable);
		
		RootPanel.get("Kommentar").add(vPanel);
		
		

	}
}