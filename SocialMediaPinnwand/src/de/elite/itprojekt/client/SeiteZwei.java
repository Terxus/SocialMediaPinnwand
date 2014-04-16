package de.elite.itprojekt.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class SeiteZwei extends Composite {
	
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	public SeiteZwei() {
		initWidget(this.hPanel);
		//Alles was in dieser Klasse passiert unterliegt dem horizontalen Panel
		
		FlexTable ft = new FlexTable();
		//neues Flextable Object erstellen.
		ft.setBorderWidth(1);
		//Die Tabelle bekommt einen Rand.
		
		Label text1 = new Label("1");
		Label text2 = new Label("2");
		Label text3 = new Label("3");
		Label text4 = new Label("4");
		//Vier Label Objects erstellen
		
		ft.setWidget(0, 0, text1);
		ft.setWidget(0, 1, text2);
		ft.setWidget(1, 0, text3);
		ft.setWidget(1, 1, text4);
		//Diese Labels dem Flextable zuordnen
		
		
		hPanel.add(ft);
		//Die FlexTable die die Labels als Inhalt haben werden dadurch angezeigt.
	}

}
