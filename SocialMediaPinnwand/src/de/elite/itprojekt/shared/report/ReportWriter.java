package de.elite.itprojekt.shared.report;


public abstract class ReportWriter {
	
	/**
	 * <p>
	 * Diese Klasse wird benötigt, um auf dem Client die ihm vom Server zur
	 * Verfügung gestellten <code>Report</code>-Objekte in ein menschenlesbares
	 * Format zu überführen.
	 * </p>
	 * <p>
	 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
	 * das Zielformat überführten Information wird den Subklassen überlassen. In
	 * dieser Klasse werden die Signaturen der Methoden deklariert, die für die
	 * Prozessierung der Quellinformation zuständig sind.
	 * </p>
	 * 
	 * @author angelehnt an Prof. Thies
	 */
	

	  /**
	   * Übersetzen eines <code>ReportVonAllenNutzer</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   */

	public abstract void process (ReportVonAllenNutzern r);
	


}