package de.elite.itprojekt.client.gui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
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
		       Window.alert("HomeButton");
		     }
		};
		//Edit account listener
		Command editNutzer = new Command() {
		     public void execute() {
		       Window.alert("Edit User");
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
	    headerMenu.addItem("Account Editieren", editNutzer);
	    headerMenu.addItem("ReportGenerator", report);
	    headerMenu.addItem("Logout", logout);
		
		RootPanel.get("Header").add(headerMenu);
		

	}
}