package de.elite.itprojekt.client;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.server.ConnectionClass;



public class AboUser extends Composite {
	
	//Composite: GWT Klasse: Widgets Composed out of other Widgets
	
	//private TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Unit.EM);
	
	private VerticalPanel vPanel = new VerticalPanel();
	
	
	private Label derUser;
	//Muss deklariert werden, da auf die Textbox sonst nicht zugegriffen werden kann von ausserhalb der methode onModuleLoad()
	
	private ConnectionClass connection = new ConnectionClass();
	//Ruft die Allgemeine ConnectionClasse auf
	

	public AboUser() {
		initWidget(this.vPanel);
		
		this.derUser = new Label();
		this.derUser.setText(connection.getNutzer());
		
		vPanel.add(derUser);
	}

	//Der Clickhandler der Benutzt wird von den Buttons
}