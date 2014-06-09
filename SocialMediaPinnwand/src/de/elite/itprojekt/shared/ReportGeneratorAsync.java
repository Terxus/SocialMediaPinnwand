package de.elite.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.elite.itprojekt.shared.bo.Nutzer;
import de.elite.itprojekt.shared.bo.Pinnwand;
import de.elite.itprojekt.shared.report.ReportVonAllenNutzern;


public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void erstelleReportVonAllenNutzern(AsyncCallback<ReportVonAllenNutzern> Callback);
	void erstelleReportVonAllenNutzern(Nutzer n, AsyncCallback<ReportVonAllenNutzern> Callback);
	
	void setPinnwand (Pinnwand p, AsyncCallback<Void> Callback);
}
