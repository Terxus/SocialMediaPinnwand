package de.elite.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SocialMediaPinnwand implements EntryPoint {
	/**
	 * This is the entry point method.
	 * 
	 * 
	 */
	
	public void onModuleLoad() {
		
		
		
		
		
		//Baut den Header auf -> Muss wegen Login zukünftig getrennt werden

		 
		    MenuBar headerMenu = new MenuBar();
		    
			//ClickListener
			Command home = new Command() {
			     public void execute() {
			       Window.alert("HomeButton");
			     }
			};
			//Report Listener
			Command report = new Command() {
			     public void execute() {
			       Window.alert("ReportGenerator");
			     }
			};
			//Logout Listener
			Command logout = new Command() {
			     public void execute() {
			       Window.alert("Logout");
			     }
			};
		   
		    headerMenu.addItem("Home", home);
		    headerMenu.addItem("ReportGenerator", report);
		    headerMenu.addItem("Logout", logout);

		
		
		
		
		/*Login login = new Login();
		
		RootPanel.get("Login").add(login);*/
		
		

			Navigation navi = new Navigation();
			Beitrag newBeitrag = new Beitrag(2);
			Beitrag Beitrag = new Beitrag();
			Kommentar Kommentar = new Kommentar();
		// Die Klasse SeiteEins stellt die Komplette seite dar. Ein Objekt dieser Klasse wird erzeugt in der Variablen "seite".
			
			RootPanel.get("neuer_Beitrag").add(newBeitrag);
			RootPanel.get("Beitrag").add(Beitrag);
			RootPanel.get("Kommentar").add(Kommentar);
			RootPanel.get("Navigator").add(navi);
			RootPanel.get("Header").add(headerMenu);
		//Hier wird die Seite, ein Objekt der Klasse SeiteEins, in der Rootpage ausgegeben
	}
}

