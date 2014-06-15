package de.elite.itprojekt.client.gui;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.elite.itprojekt.client.SocialMediaPinnwand;
import de.elite.itprojekt.shared.ReportGenerator;
import de.elite.itprojekt.shared.ReportGeneratorAsync;
import de.elite.itprojekt.shared.bo.Nutzer;

/**
 * Diese Klasse stellt das Grundgerüst des Reportgenerators dar.
 * Hier wird die grafische Darstellung des Reportgenerators definiert und
 * die globalen Nutzerstatistiken ermittelt.
 * Desweiteren ist die Klasse zuständig für die getroffene Auswahl der Reportart und ruft die 
 * dementsprechenden Methoden für die Reportgenerierung in den Klassen {@link NutzerReport}
 * und {@link BeitragReport} auf.
 * 
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class Report {

	ReportGeneratorAsync report = GWT.create(ReportGenerator.class); // Proxy aufbauen für Pinnwandverwaltung
																
	/**
	 * Ein kleiner Logger der die System.out's in den Client bringen soll. Wird benutzt um die Errors auszulesen.
	 */

	Logger logger = SocialMediaPinnwand.getLogger();

	private VerticalPanel vPanel = new VerticalPanel();
	private Button reportButton;
	private Label zeitraumVon;
	private DateBox dateBoxVon;
	private Label zeitraumBis;
	private DateBox dateBoxBis;
	private Date datumVon = new Date();
	private Date datumBis = new Date();
	private FlexTable reportTable;
	private FlexTable userSuche;
	private DecoratorPanel decPanel;
	private FlexTable auswahlTabelle;
	private RadioButton radioButtonNutzer;
	private RadioButton radioButtonBeitrag;
	private RadioButton radioButtonIndi;

	private Label reportArt;
	private Label NutzerSort;
	private Label lblSortierart;
	private Label BeitraegeSort;
	private Label IndiSort;
	private Label NutzerSuche;
	ListBox lb = new ListBox();

	// Globale Nutzerstatistik

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

	// Suggestbox

	final MultiWordSuggestOracle orakel = new MultiWordSuggestOracle();
	final SuggestBox vBox = new SuggestBox(orakel);

	/**
	 * Hier werden zunächst alle Nutzerobjekte geladen. Dies wird für unsere Nutzersuche benötigt um 
	 * die SuggestBox zu befüllen.
	 * 
	 */

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
					orakel.add(n.getVorname() + " " + n.getNachname()
							+ "     [ " + n.getNickname() + " ]");
				}
			}
		});
	}

	public void reportNavigation() {

		this.reportButton = new Button("Report");
		this.zeitraumVon = new Label("Von");
		this.zeitraumBis = new Label("Bis");
		this.lblSortierart = new Label("Sortieren nach:");
		this.dateBoxVon = new DateBox();
		this.dateBoxBis = new DateBox();
		this.reportTable = new FlexTable();
		this.userSuche = new FlexTable();
		this.decPanel = new DecoratorPanel();
		this.auswahlTabelle = new FlexTable();
		this.reportArt = new Label("Reportart:");
		this.NutzerSort = new Label("Nutzer");
		this.BeitraegeSort = new Label("Beiträge");
		this.IndiSort = new Label("Individuell");
		this.radioButtonNutzer = new RadioButton("g");
		this.radioButtonBeitrag = new RadioButton("g");
		this.radioButtonIndi = new RadioButton("g");
		this.NutzerSuche = new Label("Nutzer Suchen:");

		// Global
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

		reportTable.setWidget(1, 0, zeitraumVon);
		reportTable.setWidget(2, 0, dateBoxVon);
		reportTable.setWidget(3, 0, zeitraumBis);
		reportTable.setWidget(4, 0, dateBoxBis);
		reportTable.setWidget(5, 0, lblSortierart);
		reportTable.setWidget(5, 1, lb);
		userSuche.setWidget(0, 0, NutzerSuche);
		userSuche.setWidget(0, 1, vBox);

		// In dem Decoratorpanel ist einen neue FlexTable

		auswahlTabelle.setWidget(0, 0, reportArt);
		auswahlTabelle.setWidget(1, 0, NutzerSort);
		auswahlTabelle.setWidget(1, 1, BeitraegeSort);
		auswahlTabelle.setWidget(1, 2, IndiSort);
		auswahlTabelle.setWidget(2, 0, radioButtonNutzer);
		auswahlTabelle.setWidget(2, 1, radioButtonBeitrag);
		auswahlTabelle.setWidget(2, 2, radioButtonIndi);

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

		this.vPanel.add(decPanel);
		this.vPanel.add(reportTable);
		this.vPanel.add(userSuche);
		this.vPanel.add(reportButton);
		this.vPanel.add(globStatLabel);
		this.vPanel.add(decPanelGlobal);

		RootPanel.get("Navigator").add(vPanel);
		reportTable.setVisible(false);
		userSuche.setVisible(false);
		reportButton.setVisible(false);

		lb.addItem("Kommentare");
		lb.addItem("Likes");
		lb.setVisibleItemCount(2);

		globleStatistikenausgeben();
		
		/**
		 * Abhängig vom Klick auf die Radiobuttons der jeweiligen Reportart werden verschiedene Widgets 
		 * entwerder ein- oder ausgeblendet.
		 * 
		 */

		radioButtonBeitrag.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				reportTable.setVisible(true);
				reportButton.setVisible(true);
				lb.setVisible(true);
				lblSortierart.setVisible(true);
				userSuche.setVisible(false);

			}

		});

		radioButtonNutzer.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				reportTable.setVisible(true);
				reportButton.setVisible(true);
				lb.setVisible(true);
				lblSortierart.setVisible(true);
				userSuche.setVisible(false);
			}

		});

		radioButtonIndi.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				reportTable.setVisible(false);
				userSuche.setVisible(true);
				reportButton.setVisible(true);

			}

		});

		/**
		 * Der ValueChangeHandler erkennt wenn ein Datum aus den DateBoxen neu ausgewählt oder geändert wird.
		 * 
		 */
		
		dateBoxVon.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				datumVon = event.getValue();

			}
		});
		dateBoxBis.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				datumBis = event.getValue();
			}
		});
		
		/**
		 * Abhängig vom Klick auf die Radiobuttons der jeweiligen Reportart und dem Sortierungskriterium
		 * (Likes oder Kommentare) wird die entsprechende Methode in der Klasse {@link NutzerReport}
		 * oder {@link BeitragReport} aufgerufen.
		 * 
		 */

		reportButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
			      RootPanel.get("Beitrag").clear();
			      BeitragReport.detailTable.clear();
			      NutzerReport.detailTable.clear();

				if (radioButtonBeitrag.getValue() && lb.getSelectedIndex() == 1) {
					BeitragReport.sortiereBeitraegeAnhandLikes(datumVon,
							datumBis);

				} else if (radioButtonBeitrag.getValue()
						&& lb.getSelectedIndex() == 0) {
					BeitragReport.sortiereBeitraegeAnhandKommentare(datumVon,
							datumBis);
				}

				else if (radioButtonNutzer.getValue()
						&& lb.getSelectedIndex() == 0) {
					NutzerReport.sortiereNutzerAnhandKommentare(datumVon,
							datumBis);

				} else if (radioButtonNutzer.getValue()
						&& lb.getSelectedIndex() == 1) {
					NutzerReport.sortiereNutzerAnhandLikes(datumVon, datumBis);

				} else if (radioButtonIndi.getValue() == true) {
					userSuchen();
					System.out.println("funktioniert!");
				}

				else {
					Window.alert("Bitte wählen Sie einen Report Typ aus");
				}

			}

		});

	}
	
	/**
	 * Diese Methode gibt die globalen Nutzerstatistiken auf dem Bildschirm aus.
	 * Die Angaben beinhalten die gesamten registrierten Nutzer, Anzahl der Kommentare, Anzahl der Likes,
	 * Anzahl der Beiträge und Anzahl der Abonnements.
	 * @param void
	 * 
	 */

	// Zuerst einmal die Globalen Statisiken ausgeben
	public void globleStatistikenausgeben() {

		// Alle Nutzer zählen

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
		// Alle Kommentare zählen
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
		// Alle Likes zählen
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
		// Alle Beiträge zählen
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
		// Alle Abonnements zählen
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

		// Datum aus dem DateBox auslesen
		dateBoxVon.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				datumVon = event.getValue();

			}
		});
		dateBoxBis.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				datumBis = event.getValue();
			}
		});
		;

	}
	
	/**
	 * Diese Methode übergibt den eingegeben String in der SuggestBox "vBox" und ruft die Methode 
	 * zeigeStatistikeinzelnerNutzer() in der Klasse {@link NutzerReport} auf. 
	 * Sie wird benötigt wenn der Nutzer sich einen <b>individuellen Nutzerreport</b> ausgeben lassen will.
	 * @param void
	 * 
	 */

	public void userSuchen() {

		// Sucht den User aus der Datenbank

		if (this.vBox.getText().isEmpty()) {
			Window.alert("Report ohne Nutzer nicht möglich");
		} else {

			String s = this.vBox.getText();
			s = s.substring(s.indexOf("[") + 2, s.indexOf(" ]"));
			final String nickname = s;
			NutzerReport.zeigeStatistikeinzelnerNutzer(nickname);

		}
	};
}
