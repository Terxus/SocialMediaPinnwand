package de.elite.itprojekt.client.gui;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.elite.itprojekt.client.SocialMediaPinnwand;
import de.elite.itprojekt.shared.PinnwandVerwaltung;
import de.elite.itprojekt.shared.PinnwandVerwaltungAsync;
import de.elite.itprojekt.shared.bo.Abonnement;
import de.elite.itprojekt.shared.bo.Beitrag;
import de.elite.itprojekt.shared.bo.Kommentar;
import de.elite.itprojekt.shared.bo.Like;
import de.elite.itprojekt.shared.bo.Nutzer;

public class BeitragErstellen {

	public BeitragErstellen() {
		// Konstruktor des Todes

	}

	Logger logger = SocialMediaPinnwand.getLogger();
	private Timestamp aktuellesDatum;
	private static Nutzer nutzer;
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class); // Proxy
																			// aufbauen
																			// für
																			// Pinnwandverwaltung

	// Nutzerobjekt per ID von Cookie holen
	public void setNutzer(Nutzer nutzer) {
		BeitragErstellen.nutzer = nutzer;
		logger.severe("Nutzerobjekt zu Nutzer mit der ID:" + " "
				+ BeitragErstellen.nutzer.getID() + " " + "gesetzt.");
	}

	public void holeNutzer() {
		service.sucheNutzerID(Integer.valueOf(Cookies.getCookie("gp5cookie")),
				new AsyncCallback<Nutzer>() {
					@Override
					public void onFailure(Throwable caught) {
						logger.severe("Fehler bei der Nutzererkennung");
					}

					@Override
					public void onSuccess(Nutzer result) {
						setNutzer(result);
					}
				});
	}

	public Nutzer getNutzer() {
		return BeitragErstellen.nutzer;
	}

	private VerticalPanel vPanel = new VerticalPanel();
	private Label eingeloggterUser;
	private PushButton kommentieren;
	private PushButton bearbeiten;
	private Button loeschen;
	private Label textBeitrag;
	private Label datumsAnzeige;
	private PushButton like;
	private PushButton delike = new PushButton("Delike");
	private Label anzahlLikes;
	private FlexTable beitragsGrid = new FlexTable();

	// Neuer Beitrag

	private VerticalPanel vPanelAddBeitrag = new VerticalPanel();
	private TextArea tArea = new TextArea();
	private Button addBeitrag;

	// Kommentare anlegen
	private TextArea tAreak;
	private Button addKommentar;

	// Kommentare ausgeben
	private VerticalPanel vPanelk = new VerticalPanel();
	private Label kommentarNutzer;
	private PushButton bearbeitenk;
	private Button loeschenk;
	private Label textBeitragk;
	private Label datumsAnzeigek;
	FlexTable kommentarFlexTable = new FlexTable();

	// Fuer neuen Beitraghinzufuegen

	public void beitragAnzeigenVonAbo(final Beitrag beitrag, final Nutzer nutzer) {

		// Widgets fuer AboBeitrag

		this.eingeloggterUser = new Label(nutzer.getVorname() + " "
				+ nutzer.getNachname());
		this.textBeitrag = new Label(beitrag.getText());
		this.datumsAnzeige = new Label(beitrag.getErstellZeitpunkt().toString());
		this.kommentieren = new PushButton("Kommentieren");
		this.like = new PushButton("Like");
		this.anzahlLikes = new Label();

		// CSS Bezeichner
		this.eingeloggterUser.setStylePrimaryName("NutzerName");
		this.datumsAnzeige.setStylePrimaryName("Date");
		this.textBeitrag.setStylePrimaryName("umBruch");

		// Dem FlexTable zuordnen

		beitragsGrid.setStyleName("panel flexTable");

		beitragsGrid.setWidget(0, 0, eingeloggterUser);
		beitragsGrid.setWidget(0, 2, kommentieren);
		beitragsGrid.setWidget(1, 0, textBeitrag);
		beitragsGrid.setWidget(2, 0, datumsAnzeige);
		beitragsGrid.setWidget(2, 2, like);
		beitragsGrid.setWidget(2, 3, anzahlLikes);

		// Likes des jeweiligen Beitrags anzeigen

		service.likeZaehlen(beitrag, new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Fehler bei Likeszaehlung");

			}

			@Override
			public void onSuccess(Integer result) {
				anzahlLikes.setText(result.toString());

			}

		});

		// Fremden Beitrag kommentieren

		kommentieren.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				addKommentar = new Button("Hinzufuegen");

				tAreak = new TextArea();
				tAreak.setVisibleLines(2);
				tAreak.setPixelSize(200, 100);

				beitragsGrid.setWidget(4, 0, tAreak);
				beitragsGrid.setWidget(5, 0, addKommentar);

				// Bei Klick in das TextAreafeld wird der aktuelle Nutzer geholt
				tAreak.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						holeNutzer();

					}

				});

				// Kommentar speichern
				addKommentar.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						if (tAreak.getValue().isEmpty()) {
							Window.alert("Bitte Text eingeben!");
						} else {
							final Kommentar kommentar = new Kommentar();
							kommentar.setText(tAreak.getText());
							kommentar
									.setErstellZeitpunkt(aktuellesDatum = new Timestamp(
											System.currentTimeMillis()));
							kommentar.setNutzer(getNutzer());
							kommentar.setBeitrag(beitrag);

							service.kommentarErstellen(kommentar,
									new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											logger.severe("Fehler bei Kommentarerstellung!");

										}

										@Override
										public void onSuccess(Void result) {
											tAreak.setVisible(false);
											addKommentar.setVisible(false);
											logger.severe("Kommentar bei Beitrag:"
													+ " "
													+ kommentar.getBeitrag()
															.getText()
													+ " "
													+ "und der ID:"
													+ " "
													+ kommentar.getNutzerId()
													+ "erstellt");
										      RootPanel.get("Navigator").clear();
										      RootPanel.get("Kommentar").clear();
										      RootPanel.get("Beitrag").clear();
										      RootPanel.get("neuer_Beitrag").clear();
											NutzerLogin login = new NutzerLogin();
											login.refreshPinnwand();

										}

									});

						}

					}

				});
			}

		});

		// DAVOR ERST UEBERPRUEFEN OB SCHON GELIKED IST VON DEM EINGELOGGTEN
		// NUTZER!

		service.likeCheck(getNutzer(), beitrag, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Likecheck fehlgeschlagen");

			}

			@Override
			public void onSuccess(Boolean result) {

				if (result == true) {

					beitragsGrid.setWidget(2, 2, delike);

					delike.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {

							service.einzelnesLikeLoeschen(beitrag, getNutzer(),
									new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											logger.severe("Fehler bei Delike!");

										}

										@Override
										public void onSuccess(Void result) {
											service.likeZaehlen(
													beitrag,
													new AsyncCallback<Integer>() {

														@Override
														public void onFailure(
																Throwable caught) {
															logger.severe("Fehler bei Likezaehlung");

														}

														@Override
														public void onSuccess(
																Integer result) {
															anzahlLikes
																	.setText(result
																			.toString());

															NutzerLogin nl = new NutzerLogin();
															nl.refreshBeitraege();

														}

													});

										}

									});

						}

					});

				} else {

					// Liken
					like.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {

							Like lke = new Like();
							lke.setNutzerId(getNutzer().getID());
							lke.setNutzer(getNutzer());
							lke.setErstellZeitpunkt(aktuellesDatum = new Timestamp(
									System.currentTimeMillis()));
							lke.setPinnwandId(getNutzer().getID());

							service.likeAnlegen(lke, beitrag,
									new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											logger.severe("Like konnte nicht erstellt werden");

										}

										@Override
										public void onSuccess(Void result) {
											beitragsGrid
													.setWidget(2, 2, delike);

											service.likeZaehlen(
													beitrag,
													new AsyncCallback<Integer>() {

														@Override
														public void onFailure(
																Throwable caught) {
															// TODO
															// Auto-generated
															// method stub

														}

														@Override
														public void onSuccess(
																Integer result) {
															anzahlLikes
																	.setText(result
																			.toString());

															NutzerLogin nl = new NutzerLogin();
															nl.refreshBeitraege();

														}

													});

										}

									});

						}

					});

				}

			}

		});

		this.vPanel.add(beitragsGrid);
		RootPanel.get("Beitrag").add(vPanel);

	}

	public void beitragAnzeigen(final Beitrag beitrag, final Nutzer nutzer) {

		// Widgets erzeugen fuer Beitrag

		this.eingeloggterUser = new Label(nutzer.getVorname() + " "
				+ nutzer.getNachname());
		this.kommentieren = new PushButton("Kommentieren");
		this.bearbeiten = new PushButton("Bearbeiten");
		this.loeschen = new Button();
		this.textBeitrag = new Label(beitrag.getText());
		this.datumsAnzeige = new Label(beitrag.getErstellZeitpunkt().toString());
		this.like = new PushButton("Like");
		this.anzahlLikes = new Label();

		// CSS Bezeichner
		this.loeschen.setStylePrimaryName("Loeschen");
		this.eingeloggterUser.setStylePrimaryName("NutzerName");
		this.datumsAnzeige.setStylePrimaryName("Date");
		this.textBeitrag.setStylePrimaryName("umBruch");

		beitragsGrid.setStyleName("panel flexTable");

		beitragsGrid.setWidget(0, 0, eingeloggterUser);
		beitragsGrid.setWidget(0, 2, kommentieren);
		beitragsGrid.setWidget(0, 3, bearbeiten);
		beitragsGrid.setWidget(0, 4, loeschen);
		beitragsGrid.setWidget(1, 0, textBeitrag);
		beitragsGrid.setWidget(2, 0, datumsAnzeige);
		beitragsGrid.setWidget(2, 3, like);
		beitragsGrid.setWidget(2, 4, anzahlLikes);

		// Likes des Beitrags anzeigen

		service.likeZaehlen(beitrag, new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Likes konnten nicht generiert werden");

			}

			@Override
			public void onSuccess(Integer result) {
				anzahlLikes.setText(result.toString());

			}

		});

		this.vPanel.add(beitragsGrid);
		RootPanel.get("Beitrag").add(vPanel);

		// ClickHandler muessen fuer jedes Beitragobjekt gelten, darum muessen sie
		// hier definiert werden

		// Beitrag loeschen

		loeschen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				service.textBeitragLoeschen(beitrag, new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						logger.severe("Fehler beim Loeschen!");
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Textbeitrag wurde geloescht!");
						beitragsGrid.removeFromParent();
						kommentarFlexTable.removeFromParent();
						
						NutzerLogin nl = new NutzerLogin();
						nl.refreshBeitraege();
					}
				});
			}

		});

		// Beitrag bearbeiten

		bearbeiten.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				final Button speichern = new Button("Fertig");
				final TextBox newBeitrag = new TextBox();
				newBeitrag.setText(textBeitrag.getText());
				beitragsGrid.setWidget(1, 0, newBeitrag);
				beitragsGrid.setWidget(1, 1, speichern);

				// Bearbeiteter Text Speichern und in Label zurueckverwandeln
				// (*Magic*)
				speichern.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						beitrag.setText(newBeitrag.getText());
						beitrag.setErstellZeitpunkt(aktuellesDatum = new Timestamp(
								System.currentTimeMillis()));

						service.textBeitragBearbeiten(beitrag,
								new AsyncCallback<Beitrag>() {

									@Override
									public void onFailure(Throwable caught) {
										logger.severe("Konnte Beitrag nicht bearbeiten");

									}

									@Override
									public void onSuccess(Beitrag result) {
										// TODO Auto-generated method stub
										Label beitrag = new Label(newBeitrag
												.getText());
										beitrag.setStylePrimaryName("umBruch");

										beitragsGrid.setWidget(1, 0, beitrag);
										speichern.setVisible(false);
									}

								});
					}
				});
			}
		});

		// Kommentar hinzufuegen

		kommentieren.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				addKommentar = new Button("Hinzufuegen");

				tAreak = new TextArea();
				tAreak.setVisibleLines(2);
				tAreak.setPixelSize(200, 100);

				beitragsGrid.setWidget(4, 0, tAreak);
				beitragsGrid.setWidget(5, 0, addKommentar);

				// Kommentar speichern
				addKommentar.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						if (tAreak.getValue().isEmpty()) {
							Window.alert("Bitte Text eingeben!");
						} else {
							final Kommentar kommentar = new Kommentar();
							kommentar.setText(tAreak.getText());
							kommentar
									.setErstellZeitpunkt(aktuellesDatum = new Timestamp(
											System.currentTimeMillis()));
							kommentar.setNutzer(nutzer);
							kommentar.setBeitrag(beitrag);

							service.kommentarErstellen(kommentar,
									new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											logger.severe("Kommentar konnte nicht hinzugefuegt werden");

										}

										@Override
										public void onSuccess(Void result) {
											tAreak.setVisible(false);
											addKommentar.setVisible(false);

											// KommentareAuslesen(beitrag);								
										      RootPanel.get("Navigator").clear();
										      RootPanel.get("Kommentar").clear();
										      RootPanel.get("Beitrag").clear();
										      RootPanel.get("neuer_Beitrag").clear();
											NutzerLogin login = new NutzerLogin();
											login.refreshPinnwand();

										}

									});

						}

					}

				});
			}

		});

		// DAVOR ERST UEBERPRUEFEN OB SCHON GELIKED IST VON DEM EINGELOGGTEN
		// NUTZER!

		service.likeCheck(getNutzer(), beitrag, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Likecheck nicht erfolgreich!");

			}

			@Override
			public void onSuccess(Boolean result) {

				if (result == true) {

					beitragsGrid.setWidget(2, 3, delike);

					delike.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {

							service.einzelnesLikeLoeschen(beitrag, getNutzer(),
									new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											logger.severe("Error bei Delike!");

										}

										@Override
										public void onSuccess(Void result) {
											service.likeZaehlen(
													beitrag,
													new AsyncCallback<Integer>() {

														@Override
														public void onFailure(
																Throwable caught) {
															logger.severe("Error beim zaehlen der Likes");

														}

														@Override
														public void onSuccess(
																Integer result) {
															anzahlLikes
																	.setText(result
																			.toString());
															NutzerLogin nl = new NutzerLogin();
															nl.refreshBeitraege();

														}

													});

										}

									});

						}

					});

				} else {

					// Liken
					like.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {

							Like lke = new Like();
							lke.setNutzerId(nutzer.getID());
							lke.setNutzer(nutzer);
							lke.setErstellZeitpunkt(aktuellesDatum = new Timestamp(
									System.currentTimeMillis()));
							lke.setPinnwandId(nutzer.getID());

							service.likeAnlegen(lke, beitrag,
									new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											logger.severe("Like konnte nicht hinzugefuegt werden");

										}

										@Override
										public void onSuccess(Void result) {
											beitragsGrid
													.setWidget(2, 3, delike);

											service.likeZaehlen(
													beitrag,
													new AsyncCallback<Integer>() {

														@Override
														public void onFailure(
																Throwable caught) {
															logger.severe("Like konnte nicht gezaehlt werden");

														}

														@Override
														public void onSuccess(
																Integer result) {
															anzahlLikes
																	.setText(result
																			.toString());
															NutzerLogin nl = new NutzerLogin();
															nl.refreshBeitraege();

														}

													});

										}

									});

						}

					});

				}

			}

		});

	}

	// Eigene Kommentare werden anhand des aktuell eingeloggten Nutzers
	// angezeigt.
	// Wenn ein Kommentar nicht von dem aktuell eingeloggten Nutzer stammt, dann
	// aendert sich die darstellung.
	// Denn dann kann man die Kommentare nicht bearbeiten und loeschen.
	public void KommentareAuslesen(final Beitrag beitrag) {

		int id = beitrag.getID();

		service.findeAlleKommentare(id,
				new AsyncCallback<ArrayList<Kommentar>>() {

					@Override
					public void onFailure(Throwable caught) {
						logger.severe("Eigene Kommentare wurden nicht gefunden");

					}

					@Override
					public void onSuccess(ArrayList<Kommentar> result) {

						for (final Kommentar k : result) {

							final FlexTable kommentarFlexTable = new FlexTable();

							kommentarNutzer = new Label(k.getNutzer()
									.getVorname()
									+ " "
									+ k.getNutzer().getNachname());
							bearbeitenk = new PushButton("Bearbeiten");
							textBeitragk = new Label(k.getText());
							datumsAnzeigek = new Label(k.getErstellZeitpunkt()
									.toString());
							loeschenk = new Button();

							// Design
							loeschenk.setStylePrimaryName("Loeschen");
							kommentarNutzer.setStylePrimaryName("NutzerName");
							datumsAnzeigek.setStylePrimaryName("Date");
							textBeitragk.setStylePrimaryName("umBruch");

							kommentarFlexTable.setStylePrimaryName("Kommentar");
							// kmt.setStylePrimaryName("Kommentar");

							// Dem FlexTable zuordnen

							kommentarFlexTable.setWidget(1, 0, kommentarNutzer);
							kommentarFlexTable.setWidget(1, 1, bearbeitenk);
							kommentarFlexTable.setWidget(1, 2, loeschenk);
							kommentarFlexTable.setWidget(2, 0,textBeitragk);
							kommentarFlexTable.setWidget(3, 0,datumsAnzeigek);


							if (getNutzer().getID() != k.getNutzer().getID()) {
								bearbeitenk.setVisible(false);
								loeschenk.setVisible(false);

							}
							vPanelk.add(kommentarFlexTable);

							vPanel.add(vPanelk);

							// Kommentar loeschen
							loeschenk.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {

									service.kommentarLoeschen(k,
											new AsyncCallback<Void>() {
												@Override
												public void onFailure(
														Throwable caught) {
													Window.alert("Fehler beim loeschen!");
													logger.severe("Fehler beim loeschen!");
												}

												@Override
												public void onSuccess(
														Void result) {
													Window.alert("Kommentar wurde geloescht!");
													kommentarFlexTable
															.removeFromParent();
													NutzerLogin nl = new NutzerLogin();
													nl.refreshBeitraege();
													logger.severe("Kommentar geloescht!");
												}
											});

								}

							});

							// Kommentar bearbeiten
							bearbeitenk.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {

									final Button speichern = new Button(
											"Fertig");
									final TextBox newKommentar = new TextBox();
									newKommentar.setText(k.getText());
									kommentarFlexTable.setWidget(1, 0,
											newKommentar);
									kommentarFlexTable.setWidget(1, 1,
											speichern);
									// Bearbeiteter Text Speichern und in Label
									// zurueckverwandeln (*Magic*)
									speichern
											.addClickHandler(new ClickHandler() {
												@Override
												public void onClick(
														ClickEvent event) {
													// TODO Auto-generated
													// method stub
													k.setText(newKommentar
															.getText());
													k.setErstellZeitpunkt(aktuellesDatum = new Timestamp(
															System.currentTimeMillis()));
													service.kommentarBearbeiten(
															k,
															new AsyncCallback<Kommentar>() {

																@Override
																public void onFailure(
																		Throwable caught) {
																	logger.severe("Fehler beim bearbeiten");

																}

																@Override
																public void onSuccess(
																		Kommentar result) {
																	// TODO
																	// Auto-generated
																	// method
																	// stub
																	Label kommentar = new Label(
																			newKommentar
																					.getText());
																	kommentar
																			.setStylePrimaryName("umBruch");

																	kommentarFlexTable
																			.setWidget(
																					1,
																					0,
																					kommentar);
																	speichern
																			.setVisible(false);
																}

															});
												}
											});
								}

							});

						}

					}

				});

	}

	// Abobeitraege anzeigen
	public void abonnementBeitraegeAnzeigen(Nutzer nutzer) {

		// Ersmal alle abo's rausziehen

		int id = nutzer.getID();

		service.zeigeAlleAbosPerNutzer(id,
				new AsyncCallback<ArrayList<Abonnement>>() {

					@Override
					public void onFailure(Throwable caught) {
						logger.severe("Fehler bei der Darstellung der Abos");
					}

					@Override
					public void onSuccess(ArrayList<Abonnement> result) {

						for (Abonnement abo : result) {
							getBeitraeVonAbo(abo);
						}
					}

				});
	}

	// Dann die Beitraege von den Abonnenten rausziehen
	public void getBeitraeVonAbo(final Abonnement abo) {

		int id = abo.getPinnwand().getNutzer().getID();

		service.sucheBeitragPerPinnwand(id,
				new AsyncCallback<ArrayList<Beitrag>>() {

					@Override
					public void onFailure(Throwable caught) {
						logger.severe("Beiträge nicht gefunden!");

					}

					@Override
					public void onSuccess(ArrayList<Beitrag> result) {

						for (Beitrag b : result) {
							BeitragErstellen erstelle = new BeitragErstellen();
							erstelle.beitragAnzeigenVonAbo(b, abo.getPinnwand()
									.getNutzer());

							erstelle.KommentareAuslesen(b);
						}

					}

				});

	}

	// END OF ABOBEITRAEGE

	public void beitragHinzufuegen() {

		tArea.setVisibleLines(2);
		tArea.setPixelSize(473, 15);
		this.addBeitrag = new Button("Hinzufuegen");

		// ClickHandler fuer neuen Beitrag

		this.addBeitrag.addClickHandler(new addBeitragClickHandler());
		this.tArea.addClickHandler(new getNutzerClickHandler());

		this.vPanelAddBeitrag.add(tArea);
		this.vPanelAddBeitrag.add(addBeitrag);

		RootPanel.get("neuer_Beitrag").add(vPanelAddBeitrag);
	}

	// ClickHandler der den Beitrag hinzufuegt
	private class addBeitragClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {

			if (tArea.getText().isEmpty()) {
				Window.alert("Bitte Text eingeben!");
			} else {
				addBeitragAsync(getNutzer(), tArea.getText());
				tArea.setText(null);
			}
		}
	}

	// ClickHandler muss da sein, damit das Nutzer objekt geholt wird.

	private class getNutzerClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			holeNutzer();
		}
	}

	public void addBeitragAsync(Nutzer nutzer, String textBeitrag) {

		Beitrag beitrag = new Beitrag();
		beitrag.setNutzerId(nutzer.getID());
		beitrag.setText(textBeitrag);
		beitrag.setErstellZeitpunkt(aktuellesDatum = new Timestamp(System
				.currentTimeMillis()));

		service.textBeitragErstellen(beitrag, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				logger.severe("Beitrag konnte nicht erstellt werden!");

			}

			@Override
			public void onSuccess(Void result) {
				zeigeAlleBeitraege(getNutzer());
			}

		});
	}

	public void zeigeAlleBeitraege(Nutzer nutzer) {

		RootPanel.get("Beitrag").clear();
		int id = nutzer.getID();
		final Nutzer n = nutzer;

		// Ließt fremde Kommentare aus
		abonnementBeitraegeAnzeigen(n);

		service.findeAlleUserBeitraege(id,
				new AsyncCallback<ArrayList<Beitrag>>() {

					@Override
					public void onFailure(Throwable caught) {
						logger.severe("Beitraege nicht gefunden!");

					}

					@Override
					public void onSuccess(ArrayList<Beitrag> result) {

						for (Beitrag b : result) {
							BeitragErstellen erstelle = new BeitragErstellen();
							erstelle.beitragAnzeigen(b, n);
							erstelle.KommentareAuslesen(b);
						}

					}

				});

	}

}