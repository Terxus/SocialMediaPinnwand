package de.elite.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.elite.itprojekt.shared.ReportGenerator;
import de.elite.itprojekt.shared.ReportGeneratorAsync;



public class Report {
	
	ReportGeneratorAsync report = GWT.create(ReportGenerator.class); // Proxy aufbauen f�r pinnwandverwaltung


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
	private Label beitraege;
	private Label anzahlBeitraege;
	private Label abonnements;
	private Label anzahlAbonnements;
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
		this.registrierteNutzer = new Label("Anzahl Nutzer:");
		this.anzahlKommentare = new Label("Anzahl Kommentare:");
		this.anzahlLikes = new Label("Anzahl Likes:");
		this.anzahlBeitraege = new Label("Anzahl Beitraege");
		this.anzahlAbonnements = new Label("Anzahl Abos");
		
		this.beitraege = new Label();
		this.abonnements = new Label();
		this.registrierteNutzerLabel = new Label();
		this.anzahlKommentareLabel = new Label();
		this.anzahlLikesLabel = new Label();
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
		
		globaleStatistik.setWidget(3, 0, anzahlBeitraege);
		globaleStatistik.setWidget(3, 1, beitraege);
		globaleStatistik.setWidget(4, 0, anzahlAbonnements);
		globaleStatistik.setWidget(4, 1, abonnements);
		
		decPanel.add(auswahlTabelle);
		decPanelGlobal.add(globaleStatistik);
		
		
		
		this.vPanel.add(reportTable);
		this.vPanel.add(decPanel);
		this.vPanel.add(globStatLabel);
		this.vPanel.add(decPanelGlobal);
		
		RootPanel.get("Navigator").add(vPanel);
		

	}
	//Obere Beitragsliste
	private int aboInt = 2; //Hier die abonnentenzahl reinschreiben
	private int beitragInt = 5; //Hier die Beitragszahl reinschreiben
	private int likesInt = 23; //Hier die Likeanzahl reinschreiben
	
	VerticalPanel vPanelRep = new VerticalPanel();
	VerticalPanel vPanelDetailRep = new VerticalPanel();
	private FlexTable repTable = new FlexTable();
	private Label nutzerLabel = new Label("Nutzer" + " " + "hat:");
	private Label aboLabelrep = new Label("Abonnenten:" + " " + aboInt);
	private Label beitragLabelrep = new Label("Beitraege:" + " " + beitragInt);
	private Label likesLabelrep = new Label("Likes:" + " " + likesInt);
	
	//Untere Beitragsliste
	
	private FlexTable detailTable = new FlexTable();
	private Label beitragLabeldetail = new Label("Beitrag");
	private Label kommentarLabeldetail = new Label("Kommentare");
	private Label likesLabeldetail = new Label("Likes");
	
	
	
	public void getData() {
		
		//Alle Nutzer z�hlen
		
		report.zaehleAlleNutzer(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Integer result) {
				registrierteNutzerLabel.setText(result.toString());
				
			}
			
		});
		//Alle Kommentare z�hlen
		report.zaehleAlleKommentare(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Integer result) {
				anzahlKommentareLabel.setText(result.toString());
				
			}
			
		});
		//Alle Likes z�hlen
		report.zaehleAlleLikes(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Integer result) {
				anzahlLikesLabel.setText(result.toString());
				
			}
			
		});
		//Alle Beitr�ge z�hlen
		report.zaehleAlleBeitraege(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Integer result) {
			beitraege.setText(result.toString());
				
			}
			
		});
		//Alle Abonnements z�hlen
		report.zaehleAlleAbonnements(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Integer result) {
				abonnements.setText(result.toString());
				
			}
			
		});
		
		
		//NUTZER
		
		//Obere Beitragsliste
		repTable.setWidget(0, 0, nutzerLabel);
		repTable.setWidget(1, 0, aboLabelrep);
		repTable.setWidget(2, 0, beitragLabelrep);
		repTable.setWidget(3, 0, likesLabelrep);
		
		vPanelRep.add(repTable);
		RootPanel.get("Beitrag").add(vPanelRep);
		
		//Untere Beitragsliste
		
		
		detailTable.setWidget(0, 1, beitragLabeldetail);
		detailTable.setWidget(0, 2, kommentarLabeldetail);
		detailTable.setWidget(0, 3, likesLabeldetail);
		
		vPanelDetailRep.add(detailTable);
		
		RootPanel.get("Report").add(vPanelDetailRep);
		
	}
	
	
	private class ReportClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		getData();
		}
	}
}
	
	
	
	
	
