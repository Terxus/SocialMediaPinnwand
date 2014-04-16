package de.elite.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;



public class AboSuche extends Composite {
	
	//Composite: GWT Klasse: Widgets Composed out of other Widgets
	
	//private TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Unit.EM);
	
	private VerticalPanel vPanel = new VerticalPanel();
	
	
	private TextBox tBoxH;
	//Muss deklariert werden, da auf die Textbox sonst nicht zugegriffen werden kann von ausserhalb der methode onModuleLoad()
	
	private Button AboSucheButton;
	
	private  AboNavigation AboNav = new AboNavigation();
	

	public AboSuche() {
		initWidget(this.vPanel);
		
		
		HorizontalPanel hPanel = new HorizontalPanel();

			
		//Textbox hinzufügen
		this.tBoxH = new TextBox();
		hPanel.add(tBoxH);
		//Die Textbox soll der Klasse AboNavigation den Eintrag setzen, geht aber nicht?!
		
		//Button der die suche einleitet erstellen
		this.AboSucheButton = new Button("Setze");
		this.AboSucheButton.addClickHandler(new ButtonClickHandler());
		//Clickhandler hinzufügen
		hPanel.add(AboSucheButton);
		
		vPanel.add(hPanel);
	}
	private class ButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			String text = tBoxH.getText();
			AboNav.anzeigen(text);
		}

	}
	//Der Clickhandler der Benutzt wird von den Buttons
}