package de.elite.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.shared.Nutzer;



public class SeiteEins extends Composite {
	
	//Composite: GWT Klasse: Widgets Composed out of other Widgets
	
	//private TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Unit.EM);
	
	private VerticalPanel vPanel = new VerticalPanel();
	
	
	private TextBox tBoxH;
	//Muss deklariert werden, da auf die Textbox sonst nicht zugegriffen werden kann von ausserhalb der methode onModuleLoad()
	private Label textLabel;
	//Selbes Problem wie oberhalb mit dem Labelobject
	private Nutzer bleh = new Nutzer();
	
	private Button AboSucheButton;
	
	
	public SeiteEins() {
		initWidget(this.vPanel);
		//Alles was in dieser Klasse angezeigt wird, startet mit diesem Panel
		//tabPanel.setHeight("500");
		//tabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);
		//tabPanel.add(new HTML("home"), "Home");
		//tabPanel.add(new HTML("Reports"), "Reports");
		
		
		
		//Funktion outsourcen
		this.textLabel = new Label(bleh.toString());
		vPanel.add(textLabel);
		
		
		
		//Button hinzufügen
		this.AboSucheButton = new Button("Suche");
		this.AboSucheButton.addClickHandler(new ButtonClickHandler());
		vPanel.add(AboSucheButton);
		
		//Textfeld hinzufügen
		this.tBoxH = new TextBox();
		vPanel.add(tBoxH);
		
		
		
		
		
		
	}
	
	
	private class ButtonClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			String derText = tBoxH.getText();
			textLabel.setText(derText);
			
		}
	}
	//Der Clickhandler der Benutzt wird von den Buttons
	

}