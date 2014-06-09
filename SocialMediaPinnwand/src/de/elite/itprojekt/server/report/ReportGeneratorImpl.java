package de.elite.itprojekt.server.report;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.elite.itprojekt.server.PinnwandVerwaltungImpl;
import de.elite.itprojekt.server.db.*;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.ReportGenerator;

import de.elite.itprojekt.shared.bo.*;
import de.elite.itprojekt.shared.report.*;


@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PinnwandVerwaltung administration = null;
	
	public ReportGeneratorImpl() throws IllegalArgumentException {
		
		
	}
	
	
	
	protected PinnwandVerwaltung getPinnwandverwaltung() {
		return this.administration;
	}
	
	public void init() throws IllegalArgumentException {
		 PinnwandVerwaltungImpl a = new PinnwandVerwaltungImpl();
		    a.init();
		    this.administration = a;

	}


	


	
	
// Erstellen des Reoprts aller Nutzer
	
	public String erstelleReportVonNutzer(Nutzer nutzer) {
		
		if (this.getPinnwandverwaltung() == null) return null;
		
		ReportVonAllenNutzern result = new ReportVonAllenNutzern();
		 
		
		// Überschrift des Reports
		result.setTitle("Nutzerreport");
		
	// Setzen des Datums der Erstellung
		
		result.setCreated(new Date());
		
		CompositeParagraph header = new CompositeParagraph();
		
		// Vor- und Nachname hinzufügen
		
		  header.addSubParagraph(new SimpleParagraph(nutzer.getNachname() + ", "
			        + nutzer.getVorname()));
		  
		  
		
		  Row headline = new Row();

		  headline.addColumn(new Column("Anzahl Beitraege"));
		  headline.addColumn(new Column("Anzahl Abonnements"));
		  headline.addColumn(new Column("Anzahl Kommentare"));
		  

		    // HinzufÃ¼gen der Kopfzeile
		    result.addRow(headline);

		    /*
		     * Nun werden sämtliche Konten des Kunden ausgelesen und deren Kto.-Nr. und
		     * Kontostand sukzessive in die Tabelle eingetragen.
		     */
		    ArrayList<Beitrag> beitraege  = this.administration.findeAlleBeitraege();

		    for (Beitrag beitrag : beitraege) {
		      // Eine leere Zeile anlegen.
		      Row accountRow = new Row();

		      // Erste Spalte: Name hinzufügen
		      accountRow.addColumn(new Column(Beitrag.getNutzer().getNachname()));

		      /*Zweite Spalte: Inhalt hinzufügen
		      accountRow.addColumn(new Column(Beitrag.getInhalt()));
*/
		      
		      
		   
		      accountRow.addColumn(new Column(String.valueOf(beitrag.getLikeList().size())));

		      accountRow.addColumn(new Column(String.valueOf(Beitrag.getKommentarListe().size())));

		      result.addRow(accountRow); 
		    }

		    HTMLReportWriter writer = new HTMLReportWriter();
		    writer.process(result);
		    /*
		     * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
		     */
		    return writer.getReportText();
		  }



	@Override
	public void setPinnwand(Pinnwand p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public ReportVonAllenNutzern erstelleReportVonAllenNutzern(Nutzer n)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ReportVonAllenNutzern erstelleReportVonAllenNutzern()
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	



		
	

	
	}

