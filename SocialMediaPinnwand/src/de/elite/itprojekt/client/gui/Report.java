package de.elite.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.elite.itprojekt.client.SocialMediaPinnwand;
import de.elite.itprojekt.shared.ReportGenerator;
import de.elite.itprojekt.shared.ReportGeneratorAsync;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Nutzer;



public class Report {
	
	ReportGeneratorAsync report = GWT.create(ReportGenerator.class); // Proxy aufbauen für pinnwandverwaltung
	Logger logger = SocialMediaPinnwand.getLogger();

	private VerticalPanel vPanel = new VerticalPanel();
	private Label nutzerName;
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

//Suggestbox
	
 	final MultiWordSuggestOracle orakel = new MultiWordSuggestOracle();
 	final SuggestBox vBox = new SuggestBox(orakel);
 
	
 	
 	
 	//Konstruktor
 	
	public Report() {
		logger.severe("Report Generator einsatzbereit");
		report.zeigeAlleNutzer(new AsyncCallback<ArrayList<Nutzer>>() {
			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Fehler beim laden der Nutzer");

			}

			@Override
			public void onSuccess(ArrayList<Nutzer> result) {


				for (Nutzer n : result) {
					orakel.add(n.getVorname() + " " + n.getNachname() + "     [ "
							+ n.getNickname() + " ]");
				}
			}
		});
	}
 	
 	
 	
 	
 	

	public void reportNavigation() {
		

		this.nutzerName = new Label("Benutzer");
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
		this.anzahlBeitraege = new Label("Anzahl Beitraege:");
		this.anzahlAbonnements = new Label("Anzahl Abos:");
		
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
		@SuppressWarnings("deprecation")
		DateTimeFormat dateFormat = DateTimeFormat.getShortDateFormat();
		dateBoxVon.setFormat(new DateBox.DefaultFormat(dateFormat));
		dateBoxBis.setFormat(new DateBox.DefaultFormat(dateFormat));

		
		
		reportTable.setWidget(0, 0, nutzerName);
		reportTable.setWidget(1, 0, vBox);
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
	
	VerticalPanel vPanelRep = new VerticalPanel();
	VerticalPanel vPanelDetailRep = new VerticalPanel();
	private FlexTable repTable = new FlexTable();
	private Label nutzerLabel = new Label();
	private Label aboLabelrep = new Label();
	private Label beitragLabelrep = new Label();
	private Label likesLabelrep = new Label();
	private Label kommentarLabelrep = new Label();
	
	//Untere Beitragsliste
	
	private FlexTable detailTable = new FlexTable();
	private Label beitragLabeldetail = new Label("Beitrag");
	private Label kommentarLabeldetail = new Label("Kommentare");
	private Label likesLabeldetail = new Label("Likes");
	
	
	
	public void getData() {
		
		//Alle Nutzer zählen
		
		report.zaehleAlleNutzer(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Fehler beim zählen der Nutzer");
				
			}

			@Override
			public void onSuccess(Integer result) {
				registrierteNutzerLabel.setText(result.toString());
				
			}
			
		});
		//Alle Kommentare zählen
		report.zaehleAlleKommentare(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Fehler bei der Kommentarzählung");
				
			}

			@Override
			public void onSuccess(Integer result) {
				anzahlKommentareLabel.setText(result.toString());
				
			}
			
		});
		//Alle Likes zählen
		report.zaehleAlleLikes(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Fehler beim zählen der Likes");
				
			}

			@Override
			public void onSuccess(Integer result) {
				anzahlLikesLabel.setText(result.toString());
				
			}
			
		});
		//Alle Beiträge zählen
		report.zaehleAlleBeitraege(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Fehler beim zählen der Beiträge");
				
			}

			@Override
			public void onSuccess(Integer result) {
			beitraege.setText(result.toString());
				
			}
			
		});
		//Alle Abonnements zählen
		report.zaehleAlleAbonnements(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Fehler beim zählen der Abonnements");
				
			}

			@Override
			public void onSuccess(Integer result) {
				abonnements.setText(result.toString());
				
			}
			
		});
		
		
		//METHODIK
		
		if (this.vBox.getText().isEmpty()) {
			Window.alert("Report ohne Nutzer nicht möglich");
		} 
		else {
		
		String s = this.vBox.getText();
		s = s.substring(s.indexOf("[")+2,s.indexOf(" ]"));
		final String nickname = s;
		
		report.getUserByNickname(nickname, new AsyncCallback<Nutzer>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Fehler bei der Nutzersuche");
				
			}
			@Override
			public void onSuccess(Nutzer nutzer) {
				nutzerLabel.setText(nutzer.getVorname() + " " + nutzer.getNachname());
				
				
				
				//Abonnenten eines Nutzers als Zahl
				
				report.zaehleAlleAbonnementsPerNutzer(nutzer, new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Integer abos) {
						aboLabelrep.setText("Abonnenten:" + " " + abos);
						
					}
					
				});
				//Beiträge eines Nutzer als Zahl
				
				report.zaehleBeitraegePerNutzer(nutzer, new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Integer beitr) {
						
						beitragLabelrep.setText("Beitraege:" + " " + beitr);
					}
					
				});
				//Likes vergeben
				report.zaehleLikesPerNutzer(nutzer, new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Integer lks) {
						likesLabelrep.setText("Likes:" + " " + lks);
						
					}
					
				});
				//Kommentare geschrieben
				report.zaehleKommentarePerNutzer(nutzer, new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Integer kmt) {
						kommentarLabelrep.setText("Kommentare:" + " " + kmt);
						
					}
					
				});
				
				
				
				//Wenn likes ausgewählt ist, sortiert nach Likes in einem Zeitraum
				 
				if (radioButtonLikes.getValue() == true) {
					
					
				
				report.alleBeitraegeEinesNutzersNachLikes(nutzer, "2014-06-02 12:12:01.000000", "2014-06-10 12:12:01.000000", 0, new AsyncCallback<ArrayList<Beitrag>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ArrayList<Beitrag> likeBeitrag) {
						int i = 0;
						for (Beitrag b : likeBeitrag) {
							
							beitragLabeldetail = new Label("Beitrag:" + " " + b.getText());
							kommentarLabeldetail = new Label("Kommentare:" + " " + String.valueOf(b.getKommentarAnzahl()));
							likesLabeldetail = new Label(String.valueOf("Likes:" + " " + b.getLikeAnzahl()));
							
							
							detailTable.setWidget(i, 1, beitragLabeldetail);
							detailTable.setWidget(i, 2, kommentarLabeldetail);
							detailTable.setWidget(i, 3, likesLabeldetail);
							i++;
							
							System.out.println("Beitrag:" + " " + b.getText() + " " + "Kommentare:" + " " + b.getKommentarAnzahl() + " " + "Likes:" + " " + b.getLikeAnzahl());
						}
						
					}
					
				});
				
				}
				else {
				
				//Wenn Kommentare ausgewählt ist, sortiert nach Kommentare in einem Zeitraum
				
				report.alleBeitraegeEinesNutzersNachKommentare(nutzer, "2014-06-02", "2014-06-11", 1, new AsyncCallback<ArrayList<Beitrag>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ArrayList<Beitrag> kommentarBeitrag) {
						int i = 0;
						for (Beitrag b : kommentarBeitrag) {
							
							beitragLabeldetail = new Label("Beitrag:" + " " + b.getText());
							kommentarLabeldetail = new Label("Kommentare:" + " " + String.valueOf(b.getKommentarAnzahl()));
							likesLabeldetail = new Label(String.valueOf("Likes:" + " " + b.getLikeAnzahl()));
							
							
							detailTable.setWidget(i, 1, beitragLabeldetail);
							detailTable.setWidget(i, 2, kommentarLabeldetail);
							detailTable.setWidget(i, 3, likesLabeldetail);
							System.out.println("Beitrag:" + " " + b.getText() + " " + "Kommentare:" + " " + b.getKommentarAnzahl() + " " + "Likes:" + " " + b.getLikeAnzahl());
							i++;
						}
						
					}
					
				});
				
				}
				
				
				
				
				
			}
			
		});
		
		
		
		
		//NUTZER ANZEIGE
		
		//Obere Beitragsliste
		repTable.setWidget(0, 0, nutzerLabel);
		repTable.setWidget(1, 0, aboLabelrep);
		repTable.setWidget(2, 0, beitragLabelrep);
		repTable.setWidget(3, 0, likesLabelrep);
		repTable.setWidget(4, 0, kommentarLabelrep);
		
		vPanelRep.add(repTable);
		RootPanel.get("Beitrag").add(vPanelRep);
		
		//Untere Beitragsliste
		
		
		detailTable.setWidget(0, 1, beitragLabeldetail);
		detailTable.setWidget(0, 2, kommentarLabeldetail);
		detailTable.setWidget(0, 3, likesLabeldetail);
		
		vPanelDetailRep.add(detailTable);
		
		RootPanel.get("Report").add(vPanelDetailRep);
		logger.severe("Report erstellt!");
	}
		
}
	
	private class ReportClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		getData();
		}
	}
}

	
	
	
	
	
