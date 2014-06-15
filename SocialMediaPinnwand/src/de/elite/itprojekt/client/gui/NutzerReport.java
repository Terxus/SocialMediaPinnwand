package de.elite.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.elite.itprojekt.shared.ReportGenerator;
import de.elite.itprojekt.shared.ReportGeneratorAsync;
import de.elite.itprojekt.shared.bo.Nutzer;

/**
 * Diese Klasse erstellt die Reports für alle registrierten Nutzer im System.
 * Nutzer können individuell nach Zeitraum des jeweiligen Registrierungsdatums
 * gefiltert werden. Zusätzlich ist es möglich die Nutzer nach Anzahl Ihrer
 * geschriebenen Kommentare oder vergeben Likes zu sortieren.
 * 
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla,
 *         Yen Nguyen
 * @version 1.0
 */


public class NutzerReport extends VerticalPanel{
	
	 //Obere Beitragsliste
	
		static VerticalPanel vPanelRep = new VerticalPanel();
		static VerticalPanel vPanelDetailRep = new VerticalPanel();
		
		//Untere Beitragsliste
		
		static FlexTable detailTable = new FlexTable();
		private static Label nutzerLabeldetail = new Label("Nutzer");
		private static Label kommentarLabeldetail = new Label("Kommentare");
		private static Label likesLabeldetail = new Label("Likes");

		private static Label nutzerLabelheader = new Label("Nutzer");
		private static Label kommentarLabelheader = new Label("Kommentare");
		private static Label likesLabelheader = new Label("Likes");
	

	
	 static ReportGeneratorAsync report = GWT.create(ReportGenerator.class);
	 
	 	/**
		 * Diese Methode sortiert die Anzahl der vergebenen Likes aller Nutzer in absteigender Reihenfolge.
		 * Die Parameter <b>datumVon</b> und <b>datumBis</b> kennzeichnen den Zeitraum. 
		 * Die Methode zeigt nur die Nutzer an die sich innerhalb dieses Zeitraums registriert haben.
		 * 
		 * @param datumVon
		 * @param datumBis
		 * 
		 */
	
	public static void sortiereNutzerAnhandLikes(Date datumVon, Date datumBis) {
		
		DateTimeFormat simpleDateFormat = DateTimeFormat.getFormat("yyyy-MM-dd' 'HH:mm:ss");
		String von = simpleDateFormat.format(datumVon);
		String bis = simpleDateFormat.format(datumBis);
		String zero = ".000000";
		von.concat(zero);
		bis.concat(zero);
		vPanelRep.add(detailTable);
		RootPanel.get("Beitrag").add(vPanelRep);
		report.alleNutzerNachZeitraum(von, bis, new AsyncCallback<ArrayList<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ArrayList<Nutzer> nutzer) {
				
				
				
						// Sortieralgorithmus für Likes
						Collections.sort(nutzer, Nutzer.sortiereLikes());
				
				int i = 0;
				for (Nutzer n : nutzer) {
					
					nutzerLabeldetail = new Label(n.getVorname() +" " +n.getNachname());
					kommentarLabeldetail = new Label(String.valueOf(n.getKommentarAnzahl()));
					likesLabeldetail = new Label(String.valueOf(n.getLikeAnzahl()));
					
					detailTable.setWidth("400px");
					detailTable.getFlexCellFormatter().setWidth(1,0,"60%");  
					detailTable.getFlexCellFormatter().setWidth(1,1,"30%"); 
					detailTable.getFlexCellFormatter().setWidth(1,2,"10%"); 

					detailTable.setWidget(0, 0, nutzerLabelheader);
					detailTable.setWidget(0, 1, kommentarLabelheader);
					detailTable.setWidget(0, 2, likesLabelheader);
					
					detailTable.setWidget(i+1, 0, nutzerLabeldetail);
					detailTable.setWidget(i+1, 1, kommentarLabeldetail);
					detailTable.setWidget(i+1, 2, likesLabeldetail);
					
					//Styles
					detailTable.getRowFormatter().addStyleName(0, "detailTableHeader");
					detailTable.setStylePrimaryName("detailTable");
					
					i++;

					System.out.println("Nutzer:" + " " + n.getVorname() +" " +n.getNachname() + " " + "Kommentare:" + " " + n.getKommentarAnzahl() + " " + "Likes:" + " " + n.getLikeAnzahl());
				}

			}

		});

		}
	
	/**
	 * Diese Methode sortiert die Anzahl der Kommentare aller Nutzer in absteigender Reihenfolge.
	 * Die Parameter <b>datumVon</b> und <b>datumBis</b> kennzeichnen den Zeitraum. 
	 * Die Methode zeigt nur die Nutzer an die sich innerhalb dieses Zeitraums registriert haben.
	 * 
	 * @param datumVon
	 * @param datumBis
	 * 
	 */

	public static void sortiereNutzerAnhandKommentare(Date datumVon,
			Date datumBis) {
		DateTimeFormat simpleDateFormat = DateTimeFormat.getFormat("yyyy-MM-dd' 'HH:mm:ss");
		String von = simpleDateFormat.format(datumVon);
		String bis = simpleDateFormat.format(datumBis);
		String zero = ".000000";
		von.concat(zero);
		bis.concat(zero);
		
		System.out.println("Von: " +von);
		System.out.println("Bis: " +bis);
		vPanelRep.add(detailTable);
		RootPanel.get("Beitrag").add(vPanelRep);
		
		report.alleNutzerNachZeitraum(von, bis, new AsyncCallback<ArrayList<Nutzer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ArrayList<Nutzer> nutzer) {
				

				//Sortieralgorithmus für Kommentare
				Collections.sort(nutzer, Nutzer.sortiereKommentare());
				
				int i = 0;
				for (Nutzer n : nutzer) {

					
					nutzerLabeldetail = new Label(n.getVorname() +" " +n.getNachname());
					kommentarLabeldetail = new Label(String.valueOf(n.getKommentarAnzahl()));
					likesLabeldetail = new Label(String.valueOf(n.getLikeAnzahl()));
					
					detailTable.setWidth("400px");
					detailTable.getFlexCellFormatter().setWidth(1,0,"60%");  
					detailTable.getFlexCellFormatter().setWidth(1,1,"30%"); 
					detailTable.getFlexCellFormatter().setWidth(1,2,"10%");


					detailTable.setWidget(0, 0, nutzerLabelheader);
					detailTable.setWidget(0, 1, kommentarLabelheader);
					detailTable.setWidget(0, 2, likesLabelheader);
					
					detailTable.setWidget(i+1, 0, nutzerLabeldetail);
					detailTable.setWidget(i+1, 1, kommentarLabeldetail);
					detailTable.setWidget(i+1, 2, likesLabeldetail);
					
					//Styles
					detailTable.getRowFormatter().addStyleName(0, "detailTableHeader");
					detailTable.setStylePrimaryName("detailTable");
					
					i++;

					System.out.println("Nutzer:" + " " + n.getVorname() +" " +n.getNachname() + " " + "Kommentare:" + " " + n.getKommentarAnzahl() + " " + "Likes:" + " " + n.getLikeAnzahl());
				}

			}

		});

		}

	
	/**
	 * Diese Methode zeigt die Anzahl der Abonnenten, geschriebene Beiträge, geschriebene Kommentare, 
	 * und vergebenen Likes eines einzelnen Nutzers an.
	 * @param nickname
	 * 
	 */
	
	public static void zeigeStatistikeinzelnerNutzer(String nickname)	{
	
		report.getUserByNickname(nickname, new AsyncCallback<Nutzer>() {
			
			// Obere Beitragsliste

			VerticalPanel vPanelRep = new VerticalPanel();
			VerticalPanel vPanelDetailRep = new VerticalPanel();
			Label nutzerLabel = new Label();
			Label aboLabelrep = new Label();
			Label beitragLabelrep = new Label();
			Label likesLabelrep = new Label();
			Label kommentarLabelrep = new Label();

			@Override
			public void onFailure(Throwable caught) {


			}

			@Override
			public void onSuccess(Nutzer nutzer) {
				nutzerLabel.setText("Statistiken für: "
						+ nutzer.getVorname() + " " + nutzer.getNachname());

				// Abonnenten eines Nutzers als Zahl

				report.zaehleAlleAbonnementsPerNutzer(nutzer,
						new AsyncCallback<Integer>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(Integer abos) {
								aboLabelrep.setText("Abonnenten:" + " "
										+ abos);

							}

						});
				// Beiträge eines Nutzer als Zahl

				report.zaehleBeitraegePerNutzer(nutzer,
						new AsyncCallback<Integer>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(Integer beitr) {

								beitragLabelrep.setText("Beitraege:" + " "
										+ beitr);
							}

						});
				// Likes vergeben
				report.zaehleLikesPerNutzer(nutzer,
						new AsyncCallback<Integer>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(Integer lks) {
								likesLabelrep.setText("Likes:" + " " + lks);

							}

						});
				// Kommentare geschrieben
				report.zaehleKommentarePerNutzer(nutzer,
						new AsyncCallback<Integer>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(Integer kmt) {
								kommentarLabelrep.setText("Kommentare:"
										+ " " + kmt);

					}

				});
				
		
		//NUTZER ANZEIGE


		detailTable.setWidget(0, 0, nutzerLabel);
		detailTable.setWidget(1, 0, aboLabelrep);
		detailTable.setWidget(2, 0, beitragLabelrep);
		detailTable.setWidget(3, 0, kommentarLabelrep);
		detailTable.setWidget(4, 0, likesLabelrep);
		detailTable.setStylePrimaryName("detailTable");
		
		detailTable.setWidth("400px");

		detailTable.getRowFormatter().addStyleName(0, "detailTableHeader");
		detailTable.setStylePrimaryName("detailTable");
		vPanelRep.add(detailTable);
		RootPanel.get("Beitrag").add(vPanelRep);


		RootPanel.get("Report").add(vPanelDetailRep);
	
	}




});
	

	}
}
	
		
		
		
	

