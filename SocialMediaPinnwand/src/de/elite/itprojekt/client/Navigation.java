package de.elite.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;




public class Navigation extends Composite {
	
	
private VerticalPanel vPanel = new VerticalPanel();
private Label name = new Label("Gustav Gans");
private TextBox pinnwandSucheTextBox = new TextBox();
private PushButton pinnwandSucheButton = new PushButton("Suchen");
private PushButton pinnwandAbonnierenButton = new PushButton(new Image("socialmediapinnwand/gwt/clean/images/hinzufuegen.png"));
private PushButton pinnwandDeabonnierenButton = new PushButton(new Image("socialmediapinnwand/gwt/clean/images/loeschen.png"));
private FlexTable suchTabelle = new FlexTable();
private FlexTable abonnierteUserAnzeigen = new FlexTable();
private FlexTable sucheResultatTabelle = new FlexTable();

//Dummy Namen zum Test

private Label dummyName = new Label("Gundel Gaukeley");
private Label dummyName2 = new Label("Dagobert Duck");

	
	public Navigation() {
		initWidget(this.vPanel);

		
		abonnierteUserAnzeigen.getFlexCellFormatter().setWidth(0, 0, "120");
		sucheResultatTabelle.getFlexCellFormatter().setWidth(0, 0, "120");
		
		
		suchTabelle.setWidget(0, 0, name);
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

		this.pinnwandSucheButton.addClickHandler(new pinnwandSucheClickHandler());
		this.pinnwandAbonnierenButton.addClickHandler(new pinnwandAbonnierenClickHandler());
		this.pinnwandDeabonnierenButton.addClickHandler(new pinnwandDeabonnierenClickHandler());
		
		
		//Styles
		this.name.setStylePrimaryName("NutzerName");

		

		
		//Tabellen dem Vertikalen Panel hinzufügen
		vPanel.add(suchTabelle);
		vPanel.add(abonnierteUserAnzeigen);
		vPanel.add(sucheResultatTabelle);
	}
	//Die ClickHandler
	
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
