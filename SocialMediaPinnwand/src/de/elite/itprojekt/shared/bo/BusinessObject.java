package de.elite.itprojekt.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * <p>
 * Die Klasse <code>BusinessObject</code> stellt die Basisklasse aller in diesem
 * Projekt für die Umsetzung des Fachkonzepts relevanten Klassen dar.
 * </p>
 * <p>
 * Zentrales Merkmal ist, dass jedes <code>BusinessObject</code> eine Nummer
 * besitzt, die man in einer relationalen Datenbank auch als Primärschlüssel
 * bezeichnen würde. Fernen ist jedes <code>BusinessObject</code> als
 * {@link Serializable} gekennzeichnet. Durch diese Eigenschaft kann jedes
 * <code>BusinessObject</code> automatisch in eine textuelle Form überführt und
 * z.B. zwischen Client und Server transportiert werden. Bei GWT RPC ist diese
 * textuelle Notation in JSON (siehe http://www.json.org/) kodiert.
 * </p>
 * 
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @author In Anlehung an Prof. Dr. Thies
 * @version 1.0
 */


public abstract class BusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Die eindeutige Identifikationsnummer einer Instanz dieser Klasse.
	 */

	private int id = 0;
	
	/**
	 * Bei dem anlegen eines Objektes wird der jeweilige Zeitpunkt in einem Timestamp festgehalten.
	 * Timestamp Beispiel: 2014-06-13 14:23:38.000000
	 */
	
	private Timestamp erstellZeitpunkt;
	
	/**
	 * Auslesen der ID
	 */
	
	public int getID() {
		return this.id;
	}

	/**
	 * Setzen der ID
	 */
	
	public void setID(int id) {
		this.id = id;
	}
	 
	/**
	 * Auslesen des Erstellzeitpunkts im Timestampformat
	 */
	
	public Timestamp getErstellZeitpunkt() {
		return erstellZeitpunkt;
	}
	
	/**
	 * Setzen des Erstellzeitpunktes
	 */
	
	public void setErstellZeitpunkt(Timestamp erstellZeitpunkt) {
		this.erstellZeitpunkt = erstellZeitpunkt;
	}
	
	/**
	 * Ausgabe der toString methode: Pinnwandnutzer
	 */
	
	public String toString() {
		return "Pinnwandnutzer";
	}
}
