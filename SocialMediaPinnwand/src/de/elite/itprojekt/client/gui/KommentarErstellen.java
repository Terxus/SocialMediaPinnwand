package de.elite.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
		
		//TEST
		
		
		
		

	}
	
	
	
	private VerticalPanel vPanelk = new VerticalPanel();
	private Label kommentarNutzer;
	private PushButton bearbeitenk;
	private Label textBeitragk;
	private Label datumsAnzeigek;
	private Button loeschenk;
	
	
	
	
	public void add(final Kommentar k) {
		
		kommentarNutzer = new Label(k.getNutzer().getVorname() + " " + k.getNutzer().getNachname());
		bearbeitenk = new PushButton("Bearbeiten");
		textBeitragk = new Label(k.getText());
		datumsAnzeigek = new Label(k.getErstellZeitpunkt().toString());
		loeschenk = new Button();
		
		//Design
		//kommentarFlexTable.addStyleName("Kommentar");
		loeschenk.setStylePrimaryName("Loeschen");
		kommentarNutzer.setStylePrimaryName("NutzerName");
		datumsAnzeigek.setStylePrimaryName("Date");
		textBeitragk.setStylePrimaryName("umBruch");
		
		kommentarFlexTable.setStylePrimaryName("Kommentar");
		
		//Dem FlexTable zuordnen
		
		kommentarFlexTable.setWidget(0, 0, kommentarNutzer);
		kommentarFlexTable.setWidget(0, 1, bearbeitenk);
		kommentarFlexTable.setWidget(0, 2, loeschenk);
		kommentarFlexTable.setWidget(1, 0, textBeitragk);
		kommentarFlexTable.setWidget(2, 0, datumsAnzeigek);
		
		vPanelk.add(kommentarFlexTable);

		vPanel.add(vPanelk);
		
		RootPanel.get().add(vPanel);
		
		
		
		
		//Kommentar loeschen
		loeschenk.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("Dieses Kommentar hat den Inhalt:" + " " + k.getText());
				
			}
			
		});
		
		//Kommentar bearbeiten
		bearbeitenk.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(k.getText() + " " + "soll nun bearbeitet werden.");
				
			}
			
		});
		
		
		
		
		
		
		
		/*
		
		
		System.out.println("");
		System.out.println("---Kommentar von Textbeitrag ID:" + " " + k.getID());
		System.out.println(k.getNutzer().getVorname() + " " + k.getNutzer().getNachname());
		System.out.println(k.getText());
		System.out.println(k.getErstellZeitpunkt().toString());
		System.out.println("---Ende Kommentar-------------------------------");
		*/
	}
	
	
}