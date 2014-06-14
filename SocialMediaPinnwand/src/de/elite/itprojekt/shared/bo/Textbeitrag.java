package de.elite.itprojekt.shared.bo;

/**
 * Ein Textbeitrag ist die Oberklasse von den Klassen Beitrag und Kommentar. Da beide Klasse den selben Inhalt (Text) haben, 
 * war es Sinnvoll diese Oberklasse zu schaffen.
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class Textbeitrag extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ein Textbeitrag besteht aus einem Text.
	 */
	
	private String text;
	
	/**
	 * Ausgabe des Texts als Stringobjekt.
	 * @return Ausgabe des Texts eines Kommentars oder eines Beitrags.
	 */

	public String getText() {
		return text;
	}
	
	/**
	 * Setzen des Texts als String.
	 * @param text
	 */

	public void setText(String text) {
		this.text = text;
	}
}
