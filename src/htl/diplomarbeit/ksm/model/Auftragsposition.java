package htl.diplomarbeit.ksm.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Auftragsposition {
	private final SimpleIntegerProperty auftrag_id;
	private final SimpleIntegerProperty produkt_id;
	private final SimpleIntegerProperty pos_id;

	public Auftragsposition(int auftrag_id, int produkt_id, int pos_id) {
		this.auftrag_id = new SimpleIntegerProperty(auftrag_id);
		this.produkt_id = new SimpleIntegerProperty(produkt_id);
		this.pos_id = new SimpleIntegerProperty(pos_id);
	}

	public Integer getAuftragID() {
		return auftrag_id.get();
	}

	public Integer getProduktID() {
		return produkt_id.get();
	}

	public Integer getPosID() {
		return pos_id.get();
	}
}
