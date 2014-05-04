package de.elite.itprojekt.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	private Button like;
	
	private ConnectionClass connection = new ConnectionClass();
	//Ruft die Allgemeine ConnectionKlasse auf
	

	public Beitraege() {
		initWidget(this.vPanel);
		


		/*Benutzername holen f�r die eigenen geschriebenen Beitr�ge*/
		
		this.eingeloggterUser = new Label();
		eingeloggterUser.setText(connection.getNutzer());
		hPanel.add(eingeloggterUser);
		
		
		/*Button f�rs Kommentieren*/
		
		this.kommentieren = new PushButton("Kommentieren");
		this.kommentieren.setStylePrimaryName("KommentierenBeitrag"); /*CSS Klasse f�r Button -> hmmm */
		hPanel.add(kommentieren);
		
		/*Button f�rs Bearbeiten*/
		
		this.bearbeiten = new PushButton("Bearbeiten");
		this.bearbeiten.setStylePrimaryName("BearbeitenLoeschenBeitrag"); /*CSS Klasse f�r Button -> hmmm */
		hPanel.add(bearbeiten);
		
		/*Button f�rs L�schen*/
		
		this.loeschen = new Button();
		this.loeschen.setStylePrimaryName("Loeschen");
		hPanel.add(loeschen);
		
		vPanel.add(hPanel);
		
		/*Label f�r Textbeitrag*/
		
		this.textBeitrag = new Label("Heute war ich gut!");
		this.textBeitrag.setStyleName("TextBeitrag");
		vPanel.add(textBeitrag);

		
		/*Button f�r Like*/
		
		this.like = new Button();
		this.like.setStylePrimaryName("LikeButton");
	}

}