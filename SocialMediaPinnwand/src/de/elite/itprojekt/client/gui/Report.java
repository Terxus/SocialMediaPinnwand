package de.elite.itprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;



public class Report {


	private VerticalPanel vPanel = new VerticalPanel();
	private Label nutzerName;
	private TextBox nutzerSuche;
	private Button reportButton;
	private Label zeitraumVon;
	private DateBox dateBoxVon;
	private Label zeitraumBis;
	private DateBox dateBoxBis;
	private FlexTable reportTable;
	private DecoratorPanel decPanel;
	private FlexTable auswahlTabelle;
	private RadioButton radioButtonLikes;
	private RadioButton radioButtonKommentare;
	private Label sortiertNach;
	private Label likeSort;
	private Label kommentarSort;
	
	//Globale Nutzerstatistik
	
	private Label registrierteNutzer;
	private Label anzahlKommentare;
	private Label anzahlLikes;
	private Label registrierteNutzerLabel;
	private Label anzahlKommentareLabel;
	private Label anzahlLikesLabel;
	private FlexTable globaleStatistik;
	private DecoratorPanel decPanelGlobal;
	private Label globStatLabel;


 
	

	public void reportNavigation() {
		
		RootPanel.get("Navigator").clear();
		RootPanel.get("neuer_Beitrag").clear();
		RootPanel.get("Beitrag").clear();
		RootPanel.get("Kommentar").clear();
		
		

		this.nutzerName = new Label("Benutzer");
		this.nutzerSuche = new TextBox();
		this.reportButton = new Button("Report");
		this.zeitraumVon = new Label("Von");
		this.zeitraumBis = new Label("Bis");
		this.dateBoxVon = new DateBox();
		this.dateBoxBis = new DateBox();
		this.reportTable = new FlexTable();
		this.decPanel = new DecoratorPanel();
		this.auswahlTabelle = new FlexTable();
		this.sortiertNach = new Label("Sortiert nach:");
		this.likeSort = new Label("Likes");
		this.kommentarSort = new Label("Kommentare");
		this.radioButtonLikes = new RadioButton("g");
		this.radioButtonKommentare = new RadioButton("g");
		
		reportButton.addClickHandler(new ReportClickHandler());
		
		//Global
		this.registrierteNutzer = new Label("Registrierte Nutzer:");
		this.anzahlKommentare = new Label("Kommentare Gesamt:");
		this.anzahlLikes = new Label("Likes Gesamt:");
		this.registrierteNutzerLabel = new Label("2");
		this.anzahlKommentareLabel = new Label("5");
		this.anzahlLikesLabel = new Label("44");
		this.globaleStatistik = new FlexTable();
		this.decPanelGlobal = new DecoratorPanel();
		this.globStatLabel = new Label("Globale Nutzerstatistiken");
		
		//radiobutton nach likes sortieren vordefiniert
		radioButtonLikes.setValue(true);
		
		//Formatierung des Datums
		DateTimeFormat dateFormat = DateTimeFormat.getShortDateFormat();
		dateBoxVon.setFormat(new DateBox.DefaultFormat(dateFormat));
		dateBoxBis.setFormat(new DateBox.DefaultFormat(dateFormat));

		
		
		reportTable.setWidget(0, 0, nutzerName);
		reportTable.setWidget(1, 0, nutzerSuche);
		reportTable.setWidget(2, 0, zeitraumVon);
		reportTable.setWidget(3, 0, dateBoxVon);
		reportTable.setWidget(4, 0, zeitraumBis);
		reportTable.setWidget(5, 0, dateBoxBis);
		reportTable.setWidget(6, 0, sortiertNach);
		

		//In dem Decoratorpanel ist einen neue FlexTable
		
		auswahlTabelle.setWidget(0, 0, likeSort);
		auswahlTabelle.setWidget(0, 1, kommentarSort);
		auswahlTabelle.setWidget(1, 0, radioButtonLikes);
		auswahlTabelle.setWidget(1, 1, radioButtonKommentare);
		auswahlTabelle.setWidget(3, 0, reportButton);
		
		globaleStatistik.setWidget(0, 0, registrierteNutzer);
		globaleStatistik.setWidget(0, 1, registrierteNutzerLabel);
		globaleStatistik.setWidget(1, 0, anzahlKommentare);
		globaleStatistik.setWidget(1, 1, anzahlKommentareLabel);
		globaleStatistik.setWidget(2, 0, anzahlLikes);
		globaleStatistik.setWidget(2, 1, anzahlLikesLabel);
		
		decPanel.add(auswahlTabelle);
		decPanelGlobal.add(globaleStatistik);
		
		
		
		this.vPanel.add(reportTable);
		this.vPanel.add(decPanel);
		this.vPanel.add(globStatLabel);
		this.vPanel.add(decPanelGlobal);
		
		RootPanel.get("Navigator").add(vPanel);
		

	}
	
	public void getData() {
		BeitragErstellen brt = new BeitragErstellen();
		
		brt.beitragHinzufuegen();
	}
	
	
	private class ReportClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		getData();
	}
	
	}
}
	
	
	
	
	
