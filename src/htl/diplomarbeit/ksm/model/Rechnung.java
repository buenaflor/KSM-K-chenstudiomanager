package htl.diplomarbeit.ksm.model;

import htl.diplomarbeit.ksm.view.RechnungsController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Rechnung {
	
	RechnungsController ctrl = new RechnungsController();
	private final SimpleIntegerProperty rechnung_id;
	private final SimpleIntegerProperty kunden_id;
	private final SimpleStringProperty datum;
	private final SimpleStringProperty lieferdatum;
	private final SimpleStringProperty nettobetrag;
	private final SimpleStringProperty bruttobetrag;
	final ObservableList<Rechnungsposition> rechnungspositionen;
	
	public Rechnung(int rechnungs_nr, int kunden_nr, String datum, String lieferdatum, String nettobetrag, String bruttobetrag){
		this.rechnung_id = new SimpleIntegerProperty(rechnungs_nr);
		this.kunden_id = new SimpleIntegerProperty(kunden_nr);
		this.datum = new SimpleStringProperty(datum);
		this.lieferdatum = new SimpleStringProperty(lieferdatum);
		this.nettobetrag = new SimpleStringProperty(nettobetrag);
		this.bruttobetrag = new SimpleStringProperty(bruttobetrag);
		rechnungspositionen = ctrl.getRechnungspositionen();
	}
	
	
	
}
