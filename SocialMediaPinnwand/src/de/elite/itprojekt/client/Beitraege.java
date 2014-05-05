package de.elite.itprojekt.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.server.ConnectionClass;



public class Beitraege extends Composite {
	
	//Composite: GWT Klasse: Widgets Composed out of other Widgets
	
	//private TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Unit.EM);
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private Label eingeloggterUser;
	private PushButton kommentieren;
	private PushButton bearbeiten;
	private Button loeschen;
	private Label textBeitrag;
	private Label datumsAnzeige;
	private HorizontalPanel likePanel = new HorizontalPanel();
	private PushButton like;
	private Label anzahlLikes;
	
	private ConnectionClass connection = new ConnectionClass();
	//Ruft die Allgemeine ConnectionKlasse auf
	

	public Beitraege() {
		initWidget(this.vPanel);
		


		/*Benutzername holen für die eigenen geschriebenen Beiträge*/
		
		this.eingeloggterUser = new Label();
		eingeloggterUser.setText(connection.getNutzer());
		hPanel.add(eingeloggterUser);
		
		
		/*Button fürs Kommentieren*/
		
		this.kommentieren = new PushButton("Kommentieren");
		this.kommentieren.setStylePrimaryName("KommentierenBeitrag"); /*CSS Klasse für Button -> hmmm */
		hPanel.add(kommentieren);
		
		/*Button fürs Bearbeiten*/
		
		this.bearbeiten = new PushButton("Bearbeiten");
		this.bearbeiten.setStylePrimaryName("BearbeitenLoeschenBeitrag"); /*CSS Klasse für Button -> hmmm */
		hPanel.add(bearbeiten);
		
		/*Button fürs Löschen*/
		
		this.loeschen = new Button();
		this.loeschen.setStylePrimaryName("Loeschen");
		hPanel.add(loeschen);
		
		vPanel.add(hPanel);
		
		/*Label für Textbeitrag*/
		
		this.textBeitrag = new Label("Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! Heute war ich gut! ");
		this.textBeitrag.setStyleName("TextBeitrag");
		vPanel.add(textBeitrag);
		
		/*Datumsanzeige im Label*/
		 this.datumsAnzeige = new Label("01.01.2014 um 14:15 Uhr");
		 this.datumsAnzeige.setStylePrimaryName("Date");
		 likePanel.add(datumsAnzeige);

		
		/*Button für Like*/
		
		this.like = new PushButton("like");
		this.like.setStylePrimaryName("LikeButton");
		likePanel.add(like);
		
		/*Anzahl der Likes in einem Label*/
		
		this.anzahlLikes = new Label("15");
		this.anzahlLikes.setStylePrimaryName("LikeCounter");
		likePanel.add(anzahlLikes);
		
		vPanel.add(likePanel);

		
	}

}