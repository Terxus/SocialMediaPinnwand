package de.elite.itprojekt.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Report, der alle Konten alle Kunden darstellt.
 * Die Klasse tr#gt keine weiteren Attribute- und Methoden-Implementierungen,
 * da alles Notwendige schon in den Superklassen vorliegt. Ihre Existenz ist 
 * dennoch wichtig, um bestimmte Typen von Reports deklarieren und mit ihnen 
 * objektorientiert umgehen zu können.
 * 
 * @author in Anlehnung Thies
 */

public class ReportVonAllenNutzern 
extends CompositeReport 
implements Serializable {
	
	

  private static final long serialVersionUID = 1L;

public void addRow(Row headline) {
	// TODO Auto-generated method stub
	
}

public Vector<Row> getRows() {
	// TODO Auto-generated method stub
	return null;
}

}
		
	
	


