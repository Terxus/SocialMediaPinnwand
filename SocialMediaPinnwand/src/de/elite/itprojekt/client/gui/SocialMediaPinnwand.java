package de.elite.itprojekt.client.gui;

import com.google.gwt.core.client.EntryPoint;

public class SocialMediaPinnwand implements EntryPoint{
  public void onModuleLoad() {
	NutzerLogin startSeite = new NutzerLogin();
	startSeite.loadLoginView();
  }
}
