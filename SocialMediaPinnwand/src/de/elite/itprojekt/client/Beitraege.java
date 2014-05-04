package de.elite.itprojekt.client;

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
	
	private ConnectionClass connection = new ConnectionClass();
	//Ruft die Allgemeine ConnectionKlasse auf
	

	public Beitraege() {
		initWidget(this.vPanel);
		
		/*Benutzername holen f�r die eigenen geschriebenen Beitr�ge*/
		
		this.eingeloggterUser = new Label();
		eingeloggterUser.setText(connection.getNutzer());
		vPanel.add(eingeloggterUser);
		
		
		/*Button f�rs Kommentieren*/
		/*
		 * 
		 * 
		this.kommentieren = new PushButton("Kommentieren");
		hPanel.add(kommentieren); */
		
		
		
		
		vPanel.add(hPanel);

	}

}