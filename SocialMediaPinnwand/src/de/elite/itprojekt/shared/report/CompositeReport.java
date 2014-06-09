package de.elite.itprojekt.shared.report;

import java.io.Serializable;
import java.util.*;

/**
 * Ein zusammengesetzter Report. Dieser Report kann aus einer Menge von 
 * Teil-Reports (vgl. Attribut <code>subReports</code>) bestehen.
 */
public abstract class CompositeReport 
	extends ReportShared 
	implements Serializable {

	/**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
	 * Die Menge der Teil-Reports.
	 */
	private Vector<ReportShared> subReports = new Vector<ReportShared>();

	/**
	 * Hinzuf�gen eines Teil-Reports.
	 * @param r der hinzuzuf�gende Teil-Report.
	 */
	public void addSubReport(ReportShared r) {
		this.subReports.addElement(r);
	}

	/**
	 * Entfernen eines Teil-Reports.
	 * @param r der zu entfernende Teil-Report.
	 */
	public void removeSubReport(ReportShared r) {
		this.subReports.removeElement(r);
	}

	/**
	 * Auslesen der Anzahl von Teil-Reports.
	 * @return int Anzahl der Teil-Reports.
	 */
	public int getNumSubReports() {
		return this.subReports.size();
	}

	/**
	 * Auslesen eines einzelnen Teil-Reports.
	 * @param i Position des Teilreports. Bei n Elementen l�uft der Index i von 0
	 * bis n-1.
	 * 
	 * @return Position des Teil-Reports.
	 */	
	public ReportShared getSubReportAt(int i) {
		return this.subReports.elementAt(i);
	}
}