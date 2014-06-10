package de.elite.itprojekt.client.gui;


import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;





public class TopLevelNavigation {

	FlexTable kommentarFlexTable = new FlexTable();
	
	

	public void addTopLevelNavi() {
		
	    MenuBar headerMenu = new MenuBar();
	    
		//ClickListener
		Command home = new Command() {
		     public void execute() {
		    	 
			      RootPanel.get("Navigator").clear();
			      RootPanel.get("neuer_Beitrag").clear();
			      RootPanel.get("Kommentar").clear();
			      RootPanel.get("Beitrag").clear();
			      RootPanel.get("Footer").clear();
			      RootPanel.get("Report").clear();
			      
		    	 NutzerLogin login = new NutzerLogin();
		    	 login.refreshPinnwand();
		     }
		};
		//Edit account listener
		Command editNutzer = new Command() {
		     public void execute() {
			      RootPanel.get("Navigator").clear();
			      RootPanel.get("neuer_Beitrag").clear();
			      RootPanel.get("Kommentar").clear();
			      RootPanel.get("Beitrag").clear();
			      RootPanel.get("Footer").clear();
			      RootPanel.get("Report").clear();
		      
		      
		      AccountEdit editNutzer = new AccountEdit();
		      editNutzer.editNutzer();
		     }
		};
		//Report Listener
		Command report = new Command() {
		     public void execute() {
		    	 
		    	 //Erstmal alles aufr�umen :P
			      RootPanel.get("Navigator").clear();
			      RootPanel.get("neuer_Beitrag").clear();
			      RootPanel.get("Kommentar").clear();
			      RootPanel.get("Beitrag").clear();
			      RootPanel.get("Footer").clear();
			      RootPanel.get("Report").clear();
			      
			      Report rep = new Report();
			      rep.reportNavigation();
		     }
		};
		//Logout Listener
		Command logout = new Command() {
		     public void execute() {
		    	 
		    	 Cookies.removeCookie("gp5cookie");
		    	 
			      RootPanel.get("Navigator").clear();
			      RootPanel.get("Beitrag").clear();
			      RootPanel.get("neuer_Beitrag").clear();
			      RootPanel.get("Kommentar").clear();
			      RootPanel.get("Header").clear();
			      RootPanel.get("Footer").clear();
			      RootPanel.get("Report").clear();
			      
			      NutzerLogin nl = new NutzerLogin();
			      nl.loadLoginView();
		     }
		};
	   
	    headerMenu.addItem("Pinnwand", home);
	    headerMenu.addItem("Konto", editNutzer);
	    headerMenu.addItem("Reports", report);
	    headerMenu.addItem("Logout", logout);
		
		RootPanel.get("Header").add(headerMenu);
		

	}
}