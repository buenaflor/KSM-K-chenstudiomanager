package htl.diplomarbeit.ksm.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NachKalkulation {

	private final StringProperty bestelldatum;
	private final StringProperty lieferant;
	private final StringProperty ware;
	private final IntegerProperty netto_einkauf;
	
	public NachKalkulation(){
		this(null, null, null, 0);
	}
	public NachKalkulation(String bestelldatum, String lieferant, String ware, int netto_einkauf) {
		this.lieferant = new SimpleStringProperty(lieferant);
		this.bestelldatum = new SimpleStringProperty(bestelldatum);
		this.ware = new SimpleStringProperty(ware);
		this.netto_einkauf = new SimpleIntegerProperty(netto_einkauf);
	}
}
