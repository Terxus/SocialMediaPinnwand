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
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.shared.ReportGenerator;
import de.elite.itprojekt.shared.ReportGeneratorAsync;
import de.elite.itprojekt.shared.bo.Beitrag;

public class BeitragReport extends SplitLayoutPanel {

	 // Obere Beitragsliste

	static VerticalPanel vPanelRep = new VerticalPanel();
	VerticalPanel vPanelDetailRep = new VerticalPanel();

	// Untere Beitragsliste

	static FlexTable detailTable = new FlexTable();
	private static Label beitragLabeldetail = new Label("Beitrag");
	private static Label kommentarLabeldetail = new Label("Kommentare");
	private static Label likesLabeldetail = new Label("Likes");
	private static Label beitragLabelheader = new Label("Beitrag");
	private static Label kommentarLabelheader = new Label("Kommentare");
	private static Label likesLabelheader = new Label("Likes");

	static ReportGeneratorAsync report = GWT.create(ReportGenerator.class);

	public static void sortiereBeitraegeAnhandLikes(Date datumVon, Date datumBis) {

		DateTimeFormat simpleDateFormat = DateTimeFormat
				.getFormat("yyyy-MM-dd' 'HH:mm:ss");
		String von = simpleDateFormat.format(datumVon);
		String bis = simpleDateFormat.format(datumBis);
		String zero = ".000000";
		von.concat(zero);
		bis.concat(zero);
		vPanelRep.add(detailTable);
		RootPanel.get("Beitrag").add(vPanelRep);

		report.alleBeitraegeNachZeitraum(von, bis,
				new AsyncCallback<ArrayList<Beitrag>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ArrayList<Beitrag> likeBeitrag) {

						// Sortieralgorihmus für Likes
						Collections.sort(likeBeitrag, Beitrag.sortiereLikes());

						int i = 0;
						for (Beitrag b : likeBeitrag) {

							beitragLabelheader = new Label("Beitrag");
							kommentarLabelheader = new Label("Kommentare");
							likesLabelheader = new Label("Likes");
							
							beitragLabeldetail = new Label(b.getText());
							kommentarLabeldetail = new Label(String.valueOf(b.getKommentarAnzahl()));
							likesLabeldetail = new Label(String.valueOf(b.getLikeAnzahl()));
							
							detailTable.getFlexCellFormatter().setWidth(1,0,"70%");  
							detailTable.getFlexCellFormatter().setWidth(1,1,"20%"); 
							detailTable.getFlexCellFormatter().setWidth(1,2,"10%"); 

							detailTable.setWidget(0, 0, beitragLabelheader);
							detailTable.setWidget(0, 1, kommentarLabelheader);
							detailTable.setWidget(0, 2, likesLabelheader);
							
							detailTable.setWidget(i+1, 0, beitragLabeldetail);
							detailTable.setWidget(i+1, 1, kommentarLabeldetail);
							detailTable.setWidget(i+1, 2, likesLabeldetail);
							
							//Styles
							detailTable.getRowFormatter().addStyleName(0, "detailTableHeader");
							detailTable.setStylePrimaryName("detailTable");
							
	
							
							i++;

							System.out.println("Beitrag:" + " " + b.getText()
									+ " " + "Kommentare:" + " "
									+ b.getKommentarAnzahl() + " " + "Likes:"
									+ " " + b.getLikeAnzahl());
						}

					}

				});

	}

	public static void sortiereBeitraegeAnhandKommentare(Date datumVon,
			Date datumBis) {
		DateTimeFormat simpleDateFormat = DateTimeFormat
				.getFormat("yyyy-MM-dd' 'HH:mm:ss");
		String von = simpleDateFormat.format(datumVon);
		String bis = simpleDateFormat.format(datumBis);
		String zero = ".000000";
		von.concat(zero);
		bis.concat(zero);
		vPanelRep.add(detailTable);
		RootPanel.get("Beitrag").add(vPanelRep);

		report.alleBeitraegeNachZeitraum(von, bis,
				new AsyncCallback<ArrayList<Beitrag>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ArrayList<Beitrag> likeBeitrag) {

						// Sortieralgorihmus für Kommentare
						Collections.sort(likeBeitrag,
								Beitrag.sortiereKommentare());

						int i = 0;
						for (Beitrag b : likeBeitrag) {
							
							beitragLabelheader = new Label("Beitrag");
							kommentarLabelheader = new Label("Kommentare");
							likesLabelheader = new Label("Likes");
							
							beitragLabeldetail = new Label(b.getText());
							kommentarLabeldetail = new Label(String.valueOf(b.getKommentarAnzahl()));
							likesLabeldetail = new Label(String.valueOf(b.getLikeAnzahl()));
							
							detailTable.getFlexCellFormatter().setWidth(1,0,"70%");  
							detailTable.getFlexCellFormatter().setWidth(1,1,"20%"); 
							detailTable.getFlexCellFormatter().setWidth(1,2,"10%"); 

							detailTable.setWidget(0, 0, beitragLabelheader);
							detailTable.setWidget(0, 1, kommentarLabelheader);
							detailTable.setWidget(0, 2, likesLabelheader);
							
							detailTable.setWidget(i+1, 0, beitragLabeldetail);
							detailTable.setWidget(i+1, 1, kommentarLabeldetail);
							detailTable.setWidget(i+1, 2, likesLabeldetail);
							
							//Styles
							detailTable.getRowFormatter().addStyleName(0, "detailTableHeader");
							detailTable.setStylePrimaryName("detailTable");
						
							i++;

							System.out.println("Beitrag:" + " " + b.getText()
									+ " " + "Kommentare:" + " "
									+ b.getKommentarAnzahl() + " " + "Likes:"
									+ " " + b.getLikeAnzahl());
						}

					}

				});

	}

}
