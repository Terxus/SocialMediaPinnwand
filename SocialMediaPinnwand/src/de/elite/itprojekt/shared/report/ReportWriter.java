package de.elite.itprojekt.shared.report;


public abstract class ReportWriter {
	
	/**
	 * <p>
	 * Diese Klasse wird ben�tigt, um auf dem Client die ihm vom Server zur
	 * Verf�gung gestellten <code>Report</code>-Objekte in ein menschenlesbares
	 * Format zu �berf�hren.
	 * </p>
	 * <p>
	 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
	 * das Zielformat �berf�hrten Information wird den Subklassen �berlassen. In
	 * dieser Klasse werden die Signaturen der Methoden deklariert, die f�r die
	 * Prozessierung der Quellinformation zust�ndig sind.
	 * </p>
	 * 
	 * @author angelehnt an Prof. Thies
	 */
	

	  /**
	   * �bersetzen eines <code>ReportVonAllenNutzer</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu �bersetzende Report
	   */

	public abstract void process (ReportVonAllenNutzern r);
	


}