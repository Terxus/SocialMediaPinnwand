package de.elite.itprojekt.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;



public class Beitrag extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private Label eingeloggterUser;
	private PushButton kommentieren;
	private PushButton bearbeiten;
	private Button loeschen;
	private Label textBeitrag;
	private Label datumsAnzeige;
	private PushButton like;
	private Label anzahlLikes;
	FlexTable beitragsGrid = new FlexTable();
	
	
	
	//Für neuen Beitraghinzufügen
	
	private TextArea tArea = new TextArea();
	
	public Beitrag(int id) {
		initWidget(this.tArea);
		tArea.setVisibleLines(2);
		tArea.setPixelSize(475, 15);
	}



	public Beitrag() {
		initWidget(this.vPanel);
		
		//Widgets erzeugen für Beitrag

		this.eingeloggterUser = new Label("Gustav Gans");
		this.kommentieren = new PushButton("Kommentieren");
		this.bearbeiten = new PushButton("Bearbeiten");
		this.loeschen = new Button();
		this.textBeitrag = new Label("Methode die dann die Textbeitraege holen soll! Methode die dann die Textbeitraege holen soll! Methode die dann die Textbeitraege holen soll!");
		this.datumsAnzeige = new Label("24.06.2014 um 12:00 Uhr");
		this.like = new PushButton("like");
		this.anzahlLikes = new Label("2");
		
		//CSS Bezeichner
		this.loeschen.setStylePrimaryName("Loeschen");
		
		
		beitragsGrid.setWidget(0, 0, eingeloggterUser);
		beitragsGrid.setWidget(0, 2, kommentieren);
		beitragsGrid.setWidget(0, 3, bearbeiten);
		beitragsGrid.setWidget(0, 4, loeschen);
		beitragsGrid.setWidget(1, 0, textBeitrag);
		beitragsGrid.setWidget(2, 0, datumsAnzeige);
		beitragsGrid.setWidget(2, 3, like);
		beitragsGrid.setWidget(2, 4, anzahlLikes);
		
		
		
		
		
		this.vPanel.add(beitragsGrid);
		
		
		

	}
}