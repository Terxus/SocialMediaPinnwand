package de.elite.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.shared.bo.Nutzer;




public class Navigation {
	
	private Nutzer nutzer = new Nutzer();
	
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
	
private VerticalPanel vPanel = new VerticalPanel();
private Label name = new Label(nutzer.toString());
private TextBox aendereNameTextBox = new TextBox();
private TextBox pinnwandSucheTextBox = new TextBox();
private PushButton pinnwandSucheButton = new PushButton("Suchen");
private PushButton pinnwandAbonnierenButton = new PushButton(new Image("socialmediapinnwand/gwt/clean/images/hinzufuegen.png"));
private PushButton pinnwandDeabonnierenButton = new PushButton(new Image("socialmediapinnwand/gwt/clean/images/loeschen.png"));
private FlexTable nutzerTabelle = new FlexTable();
private FlexTable suchTabelle = new FlexTable();
private FlexTable abonnierteUserAnzeigen = new FlexTable();
private FlexTable sucheResultatTabelle = new FlexTable();



//Dummy Namen zum Test

private Label dummyName = new Label("Gundel Gaukeley");
private Label dummyName2 = new Label("Dagobert Duck");



	


public void addNavigation() {
		
		abonnierteUserAnzeigen.getFlexCellFormatter().setWidth(0, 0, "120");
		sucheResultatTabelle.getFlexCellFormatter().setWidth(0, 0, "120");
		
		
		nutzerTabelle.setWidget(0, 0, name);
		
		suchTabelle.setWidget(1, 0, pinnwandSucheTextBox);
		suchTabelle.setWidget(1, 1, pinnwandSucheButton);
		suchTabelle.setStylePrimaryName("suchTabelle");
		
		//Tabelle die alle Abonnierten User anzeigen kann
		abonnierteUserAnzeigen.setWidget(0, 0, dummyName);
		abonnierteUserAnzeigen.setWidget(0, 1, pinnwandDeabonnierenButton);
		abonnierteUserAnzeigen.setStylePrimaryName("abonnierteUserAnzeigenTabelle");
		
		//Dies ist die Tabelle die alle User anzeigt die gesucht wurden.
		sucheResultatTabelle.setWidget(0, 0, dummyName2);
		sucheResultatTabelle.setWidget(0, 1, pinnwandAbonnierenButton);
		sucheResultatTabelle.setStylePrimaryName("sucheResultatTabelle");
		
		//Clickhandler den Buttons adden

		this.name.addClickHandler(new changeLabelToTextboxClickHandler());

		this.pinnwandSucheButton.addClickHandler(new pinnwandSucheClickHandler());
		this.pinnwandAbonnierenButton.addClickHandler(new pinnwandAbonnierenClickHandler());
		this.pinnwandDeabonnierenButton.addClickHandler(new pinnwandDeabonnierenClickHandler());
		
		
		//Styles
		this.name.setStylePrimaryName("NutzerName");

		

		
		//Tabellen dem Vertikalen Panel hinzufügen
		vPanel.add(nutzerTabelle);
		vPanel.add(suchTabelle);
		vPanel.add(abonnierteUserAnzeigen);
		vPanel.add(sucheResultatTabelle);
		
		
		
		RootPanel.get("Navigator").add(vPanel);
		
		
	}
	
	
	//Methoden die das Label in TextBox umwandelt und den alten Namen in die TextBox reinschreiben
	
	public void changeLabel() {
		aendereNameTextBox.setText(this.name.getText().toString()); //Hier wird der alte Name in die Textbox geschrieben
		PushButton editNameButton = new PushButton("Speichern"); //Hier wird der Button zum Editieren erstellt
		editNameButton.addClickHandler(new changeNameClickHandler()); //Hier wird dem Button einen Clickhandler zugeteilt
		
		
		nutzerTabelle.setWidget(0, 1, editNameButton); //Der Button wird in die Tabelle auf Position 0, 1 eingefügt
		nutzerTabelle.setWidget(0, 0, aendereNameTextBox); //Das Label an der Position 0, 0 wird mit der Textbox überschrieben
	}
	
	public void changeName() { 
		String geanderterName = aendereNameTextBox.getText(); //Der Neue Name wird in einem String gespeichert
		this.name.setText(geanderterName); //Der neue Name wird in dem Label "name" gespeichert.
		nutzerTabelle.setWidget(0, 0, name); //Die Textbox wird wieder mit dem Label überschrieben.
		nutzerTabelle.clearCell(0, 1); //Der Button wird wieder versteckt.
	}
	
	
	
	//Die ClickHandler
	
	private class changeLabelToTextboxClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			changeLabel();
		}
	}
	
	private class changeNameClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			changeName();
		}
	}
	
	private class pinnwandSucheClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			pinnwandSucheTextBox.setText("Suche wurde eingeleitet");
		}
	}
	private class pinnwandAbonnierenClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			dummyName2.setText("Wurde Abonniert");
		}
	}
	private class pinnwandDeabonnierenClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			dummyName.setText("Wurde Deabonniert");
		}
	}
	
	
	
	
}