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

/**
 * Diese Klasse ist fast für die gesamte Pinnwandkommunikation zuständig. In ihr werden Beiträge erstellt,
 * Beiträge kommentiert, Beiträge geliked, selbige gelöscht und auch bearbeitet.
 * @author Maik Piskors, Benjamin Auwärter, Dominik Liebscher, Raphael Abdalla, Yen Nguyen
 * @version 1.0
 */

public class BeitragErstellen {

	public BeitragErstellen() {
		// Konstruktor des Todes

	}
	
	/**
	 * Ein kleiner Logger der die System.out's in den Client bringen soll. Wird benutzt um die Errors auszulesen.
	 */

	Logger logger = SocialMediaPinnwand.getLogger();
	
	/**
	 * Wir Nutzen ein Timestampobjekt als Datum für Kommentare, Likes, Beiträge.
	 */
	
	private Timestamp aktuellesDatum;
	
	/**
	 * Ein Nutzer welcher Aktuell eingeloggt ist.
	 */
	
	private static Nutzer nutzer;
	
	/**
	 * <b>service</b> heißt unsere Proxyreferenz, welche mit dem Server interagiert.
	 */
	
	PinnwandVerwaltungAsync service = GWT.create(PinnwandVerwaltung.class); 
	
	/**
	 * Hier wird der aktuell eingeloggte Nutzer festgelegt.
	 * @param nutzer
	 */
	
	public void setNutzer(Nutzer nutzer) {
		BeitragErstellen.nutzer = nutzer;
		logger.severe("Nutzerobjekt zu Nutzer mit der ID:" + " "
				+ BeitragErstellen.nutzer.getID() + " " + "gesetzt.");
	}

	/**
	 * Diese Methode durchsucht den Clienten nach einem Cookie Namens: <b>gp5cookie</b>
	 * In diesem Cookie ist die eindeutige ID gespeichert, die einem Nutzer zuordenbar ist.
	 * Nachdem die ID ausgelesen ist, wird auf der Server bzw. in der Datenbank nach einem Nutzer-Tupel gesucht 
	 * mit dieser ID, und das Nutzerobjekt erstellt. Danach wird das Nutzerobjekt zurückgegeben.
	 */
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
	
	/**
	 * Diese Methode gibt den aktuell eingeloggten Nutzer zurück.
	 * @return Der eingeloggte Nutzer.
	 */

	public Nutzer getNutzer() {
		return BeitragErstellen.nutzer;
	}
	
	/*
	 * Hier folgen einige Designanordnungen für Kommentare, Beiträge und Likes.
	 */

	/**
	 * Die Gesamte Beitragsstruktur ist einem VerticalPanel zugeordnet.
	 * Sämtliche Inhalte sind in einem Flextable enthalten.
	 * Wie zum Beispiel das Design der Beiträge und Kommentare.
	 */
	
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

	/**
	 * Hier werden die Widgets erstellt um einen neuen Beitrag hinzuzufügen.
	 */

	private VerticalPanel vPanelAddBeitrag = new VerticalPanel();
	private TextArea tArea = new TextArea();
	private Button addBeitrag;

	/**
	 * Widgets für Kommentare anlegen.
	 */
	
	private TextArea tAreak;
	private Button addKommentar;

	/**
	 * Widgets um Kommentare auszugeben.
	 */
	
	private VerticalPanel vPanelk = new VerticalPanel();
	private Label kommentarNutzer;
	private PushButton bearbeitenk;
	private Button loeschenk;
	private Label textBeitragk;
	private Label datumsAnzeigek;
	FlexTable kommentarFlexTable = new FlexTable();

	/**
	 * In dieser Methode werden nur die Beiträge von Nutzern angezeigt, welche man Abonniert hat. Diese Unterteilung ist
	 * notwendig, da man fremde Beiträge von eigenen Unterscheiden muss. Fremde Beiträge sollen nicht von anderen Nutzern
	 * gelöscht oder bearbeitet werden. Nur Kommentiert und Geliked werden. Ein solcher fremder Beitrag besteht einem FlexTable
	 * mit Informationen wie: Beitragsschreiber, Text, Erstelldatum, Likes, und zusätzliche Kommentare zu dem Beitrag.
	 * @param beitrag
	 * @param nutzer
	 */

	public void beitragAnzeigenVonAbo(final Beitrag beitrag, final Nutzer nutzer) {

		/**
		 * Widgets für Abobeitrag.
		 */

		this.eingeloggterUser = new Label(nutzer.getVorname() + " "
				+ nutzer.getNachname());
		this.textBeitrag = new Label(beitrag.getText());
		this.datumsAnzeige = new Label(beitrag.getErstellZeitpunkt().toString());
		this.kommentieren = new PushButton("Kommentieren");
		this.like = new PushButton("Like");
		this.anzahlLikes = new Label();

		/**
		 * CSS Definitionen.
		 */
		this.eingeloggterUser.setStylePrimaryName("NutzerName");
		this.datumsAnzeige.setStylePrimaryName("Date");
		this.textBeitrag.setStylePrimaryName("umBruch");

		/**
		 * Widgets werden dem FlexTable zugeordnet.
		 */

		beitragsGrid.setStyleName("panel flexTable");

		beitragsGrid.setWidget(0, 0, eingeloggterUser);
		beitragsGrid.setWidget(0, 2, kommentieren);
		beitragsGrid.setWidget(1, 0, textBeitrag);
		beitragsGrid.setWidget(2, 0, datumsAnzeige);
		beitragsGrid.setWidget(2, 2, like);
		beitragsGrid.setWidget(2, 3, anzahlLikes);

		/**
		 * Likes des jeweiligen Beitrags anzeigen.
		 */

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

		/**
		 * Fremder Beitrag kommentieren.
		 */

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

				/**
				 * Bei Klick in das TextAreafeld wird der aktuelle Nutzer geholt
				 */
				
				tAreak.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						holeNutzer();

					}

				});

				/**
				 * Kommentar soll erstellt werden.
				 */
				
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

		
		/**
		 * Um einen Beitrag nicht doppelt Liken zu können, muss zuerst überprüft werden, ob dieser von 
		 * dem aktuell eingeloggten Nutzer schon geliked wurde. Wenn dieser schon geliked wurde, dann
		 * wird dem aktuell eingeloggten Nutzer die Möglichkeit gegeben, den Status zu De-Liken.
		 */

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

					/**
					 * Der Status wurde noch nicht geliked, also kann der aktuelle Nutzer den Status liken.
					 */
					
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
		
		/**
		 * Das VerticalPanel wird dem RootPanel zugeordnet.
		 */
		
		this.vPanel.add(beitragsGrid);
		RootPanel.get("Beitrag").add(vPanel);

	}
	
	/**
	 * Diese Methode zeigt eigene Beiträge an. Diese Methode ist äquivalent zu der Methode 
	 * <b>beitragAnzeigenVonAbo(final Beitrag beitrag, final Nutzer nutzer)</b>
	 * Mit der Ausnahme, dass man eigene Beiträge löschen und bearbeiten kann.
	 * @param beitrag
	 * @param nutzer
	 */

	public void beitragAnzeigen(final Beitrag beitrag, final Nutzer nutzer) {

		/**
		 * Erzeugung der Widgets für eigene Beiträge
		 */

		this.eingeloggterUser = new Label(nutzer.getVorname() + " "
				+ nutzer.getNachname());
		this.kommentieren = new PushButton("Kommentieren");
		this.bearbeiten = new PushButton("Bearbeiten");
		this.loeschen = new Button();
		this.textBeitrag = new Label(beitrag.getText());
		this.datumsAnzeige = new Label(beitrag.getErstellZeitpunkt().toString().substring(0, 19));
		this.like = new PushButton("Like");
		this.anzahlLikes = new Label();

		/**
		 * CSS Definitionen
		 */
		
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

		/**
		 * Likes des jeweiligen Beitrags werden angezeigt.
		 */

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
		
		/**
		 * Die ClickHandler müssen für jedes Beitragsobjekt gelten, darum müssen sie hier 
		 * definiert werden.
		 */

		/**
		 * ClickHandler der einen Beitrag löscht.
		 */

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

		/**
		 * ClickHandler der einen Beitrag bearbeiten lässt.
		 */

		bearbeiten.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				final Button speichern = new Button("Fertig");
				final TextBox newBeitrag = new TextBox();
				newBeitrag.setText(textBeitrag.getText());
				beitragsGrid.setWidget(1, 0, newBeitrag);
				beitragsGrid.setWidget(1, 1, speichern);
				
				/**
				 * Durch einen ClickHandler der dem Label zugeordnet ist, lässt sich dieser Text in ein TextField an der richtigen
				 * stelle im FlexTable umwandeln. In dieses TextField wird danach der aktuelle Text geschrieben, welcher sich 
				 * dann verändern lasst. Bei Klick wird zusätzlich ein Button im TextField angezeigt, welcher dann den neuen Text
				 * ausließt und in die Datenbank speichert. Wenn der Datenbankeintrag erfolgreich war, wird das TextField
				 * mit dem neuen Text wieder in ein Label umgewandelt.
				 */
				
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

		/**
		 * Hier wird ein Kommentar dem Beitrag hinzugefügt.
		 */

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

				/**
				 * ClickHandler der das neue Kommentar speichert.
				 */
				
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

		/**
		 * Um einen Beitrag nicht doppelt Liken zu können, muss zuerst überprüft werden, ob dieser von 
		 * dem aktuell eingeloggten Nutzer schon geliked wurde. Wenn dieser schon geliked wurde, dann
		 * wird dem aktuell eingeloggten Nutzer die Möglichkeit gegeben, den Status zu De-Liken.
		 */

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
					
					/**
					 * Der Status wurde noch nicht geliked, also kann der aktuelle Nutzer den Status liken.
					 */
					
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
	
	/**
	 * Diese Methode hat die selbe Logik wie die der Beiträge.
	 * Eigene Kommentare werden anhand des aktuell eingeloggten Nutzers
	 * angezeigt. Wenn ein Kommentar nicht von dem aktuell eingeloggten Nutzer stammt, dann
	 * aendert sich die darstellung. Denn dann kann man die Kommentare nicht bearbeiten und loeschen.
	 * 
	 * @param beitrag
	 */
	
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
									.toString().substring(0, 19));
							loeschenk = new Button();

							/**
							 * CSS Definitionen
							 */
							
							loeschenk.setStylePrimaryName("Loeschen");
							kommentarNutzer.setStylePrimaryName("NutzerName");
							datumsAnzeigek.setStylePrimaryName("Date");
							textBeitragk.setStylePrimaryName("umBruch");

							kommentarFlexTable.setStylePrimaryName("Kommentar");

							/**
							 * Zuordnung der Widgets in ein FlexTable.
							 */

							kommentarFlexTable.setWidget(1, 0, kommentarNutzer);
							kommentarFlexTable.setWidget(1, 1, bearbeitenk);
							kommentarFlexTable.setWidget(1, 2, loeschenk);
							kommentarFlexTable.setWidget(2, 0,textBeitragk);
							kommentarFlexTable.setWidget(3, 0,datumsAnzeigek);

							/**
							 * Um die Methodik zu vereinfachen, wurde hier geschaut ob der aktuelle
							 * Nutzer die selbe ID besitzt wie der Ersteller der Kommentare. Je nach Ergebnis werden die
							 * Eigenschaften der Widgets auf Visible true/false gesetzt.
							 */

							if (getNutzer().getID() != k.getNutzer().getID()) {
								bearbeitenk.setVisible(false);
								loeschenk.setVisible(false);

							}
							vPanelk.add(kommentarFlexTable);

							vPanel.add(vPanelk);

							/**
							 * ClickHandler um ein Kommentar zu löschen
							 */
							
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

							/**
							 * ClickHandler um eigenes Kommentar zu bearbeiten.
							 */
							
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
									
									/**
									 * Durch einen ClickHandler der dem Label zugeordnet ist, lässt sich dieser Text in ein TextField an der richtigen
									 * stelle im FlexTable umwandeln. In dieses TextField wird danach der aktuelle Text geschrieben, welcher sich 
									 * dann verändern lasst. Bei Klick wird zusätzlich ein Button im TextField angezeigt, welcher dann den neuen Text
									 * ausließt und in die Datenbank speichert. Wenn der Datenbankeintrag erfolgreich war, wird das TextField
									 * mit dem neuen Text wieder in ein Label umgewandelt.
									 */
									
									speichern.addClickHandler(new ClickHandler() {
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

	/**
	 * In dieser Methode werden alle Abonnemnts des aktuell eingeloggten Nutzers aus der Datenbank
	 * in einer ArrayList als Abonnements gespeichert. Jedesmal wenn ein Abonnement gefunden wurde, wird
	 * dabei die Methode <b>getBeitraeVonAbo(abo)</b> aufgerufen.
	 * @param nutzer
	 */
	

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

	/**
	 * In dieser Methode werden die IDs der Nutzer, die der aktuell eingeloggter Nutzer abonniert hat, ausgelesen. 
	 * Mit diesen IDs werden dann die Beiträge in der Datenbank, in einer ArrayList gespeichert. Danach werden alle
	 * Beiträge in die Methode <b>beitragAnzeigenVonAbo(Beitrag, Nutzer)</b> übergeben. Dadurch werden alle Beiträge
	 * der Abonnements angezeigt, und ebenso die Kommentare durch den Aufruf <b>KommentareAuslesen(Beitrag)</b>
	 * @param abo
	 */
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

	/**
	 * Diese Methode erstellt in dem HTML-DIV Element <b>neuer_Beitrag</b> eine TextArea und einen Button um einen 
	 * neuen Beitrag hinzuzufügen. Dem Button wird dann ein ClickHandler zugeordnet.
	 */

	public void beitragHinzufuegen() {

		tArea.setVisibleLines(2);
		tArea.setPixelSize(473, 15);
		this.addBeitrag = new Button("Hinzufuegen");

		this.addBeitrag.addClickHandler(new addBeitragClickHandler());
		this.tArea.addClickHandler(new getNutzerClickHandler());

		this.vPanelAddBeitrag.add(tArea);
		this.vPanelAddBeitrag.add(addBeitrag);

		RootPanel.get("neuer_Beitrag").add(vPanelAddBeitrag);
	}

	/**
	 * Dies ist der ClickHandler welcher einen neuen Beitrag hinzufügt. Inhalt dieses ClickHandler ist eine kleine
	 * Überprüfung ob der Inhalt der TextArea befüllt oder leer ist. 
	 */
	
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

	/**
	 * Um jedoch sicherzugehen, dass ein Nutzer existiert wurde dieser ClickHandler erschaffen. Er dient legidlich 
	 * darum, sicherzustellen, dass der aktuelle Nutzer angelegt ist. Der ClickHandler befindet sich auf der TextArea.
	 */

	private class getNutzerClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			holeNutzer();
		}
	}

	/**
	 * Diese Methode schreibt nun tatsächlich einen neuen Beitrag in die Datenbank durch aufruf des Proxys.
	 * Mit den Informationen wie NutzerID, Text und Erstellzeitpunkt, wird ein neues Objekt vom Typ Beitrag erstellt.
	 * Bei erfolgreichem Einfügen in die Datenbank, wird die Methode <b>zeigeAlleBeitraege(Nutzer)</b> aufgerufen.
	 * @param nutzer
	 * @param textBeitrag
	 */
	
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
	
	/**
	 * Mann kann sagen, dass diese Methode die "main" Methode dieser Klasse ist. In ihr werden alle Methoden aufgerufen,
	 * die die Kommentare und Beiträge anzeigen, seien es die eigenen Beiträge/Kommentare oder die fremden.
	 * @param nutzer
	 */

	public void zeigeAlleBeitraege(Nutzer nutzer) {

		RootPanel.get("Beitrag").clear();
		int id = nutzer.getID();
		final Nutzer n = nutzer;

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