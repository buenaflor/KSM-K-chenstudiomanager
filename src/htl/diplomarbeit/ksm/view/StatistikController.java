package htl.diplomarbeit.ksm.view;

import htl.diplomarbeit.ksm.model.Person;
import htl.diplomarbeit.ksm.model.Rechnungsuebersicht;
import htl.diplomarbeit.ksm.util.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class StatistikController {
	@FXML
	LineChart<String, Number> umsatzChart;
	@FXML
	LineChart<String, Number> neukundenChart;
	XYChart.Series<String, Number> umsatz = new XYChart.Series<String, Number>();
	XYChart.Series<String, Number> neukunden = new XYChart.Series<String, Number>();
	double[] umsatzsumme = new double[13];
	int[] neukundensumme = new int[13];
	String[] monat = new String[13];

	@FXML
	public void initialize() {

		monat[1] = "Jan";
		monat[2] = "Feb";
		monat[3] = "Mär";
		monat[4] = "Apr";
		monat[5] = "Mai";
		monat[6] = "Jun";
		monat[7] = "Jul";
		monat[8] = "Aug";
		monat[9] = "Sep";
		monat[10] = "Okt";
		monat[11] = "Nov";
		monat[12] = "Dez";

		for (Rechnungsuebersicht rechnung : DatabaseConnection.getFromDatabaseRechnung()) {
			String[] temp = rechnung.getDatum().split("\\.");
			umsatzsumme[Integer.parseInt(temp[1])] += Double.parseDouble(rechnung.getNettobetrag());
		}
		for (Person kunde : DatabaseConnection.getFromDatabaseKunde()) {
			String[] temp = kunde.getDate().split("\\.");
			neukundensumme[Integer.parseInt(temp[1])] += 1;
		}

		for (int i = 1; i < umsatzsumme.length; i++) {
			umsatz.getData().add(new XYChart.Data<String, Number>(monat[i], umsatzsumme[i]));
			neukunden.getData().add(new XYChart.Data<String, Number>(monat[i], neukundensumme[i]));
		}

		neukundenChart.getData().add(neukunden);		
		umsatzChart.getData().add(umsatz);
		umsatz.setName("Umsatz in €");
		neukunden.setName("Anzahl der Kunden");
	}
}
