package de.elite.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void setNutzer(AsyncCallback<Void> callback);

	void nutzerReport(AsyncCallback<Void> callback);

	void likeReport(AsyncCallback<Void> callback);

	void kommentarReport(AsyncCallback<Void> callback);

	void likeReportSortiert(AsyncCallback<Void> callback);

	void kommentarReportSortiert(AsyncCallback<Void> callback);

}
