package htl.diplomarbeit.ksm.model;

import htl.diplomarbeit.ksm.view.RechnungsController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public class Rechnungsuebersicht {
	private final IntegerProperty rechnung_id;
	private final IntegerProperty kunden_id;
	private final StringProperty datum;
	private final StringProperty lieferdatum;
	private final StringProperty nettobetrag;
	private final StringProperty bruttobetrag;
	private final StringProperty vorname;
	private final StringProperty nachname;
	private final StringProperty telefon;
	private final StringProperty email;
	
	public Rechnungsuebersicht(String datum, String lieferdatum, int rechnungs_nr, int kunden_nr, String vorname, String nachname, String telefon, String email, String nettobetrag, String bruttobetrag){
		this.rechnung_id = new SimpleIntegerProperty(rechnungs_nr);
		this.kunden_id = new SimpleIntegerProperty(kunden_nr);
		this.datum = new SimpleStringProperty(datum);
		this.lieferdatum = new SimpleStringProperty(lieferdatum);
		this.nettobetrag = new SimpleStringProperty(nettobetrag);
		this.bruttobetrag = new SimpleStringProperty(bruttobetrag);
		this.vorname = new SimpleStringProperty(vorname);
		this.nachname = new SimpleStringProperty(nachname);
		this.telefon = new SimpleStringProperty(telefon);
		this.email = new SimpleStringProperty(email);
	}
	public String getVorname() {
        return vorname.get();
    }
    public void setVorname(String fName) {
        vorname.set(fName);
    }
   
	public StringProperty vornameProperty() {
		return vorname;
	}
	
	public Number getKundenId() {
        return kunden_id.get();
    }
	
	public Number getRechnungsId() {
        return rechnung_id.get();
    }
	public String getBruttobetrag() {
        return bruttobetrag.get();
    }
	public String getNettobetrag() {
        return nettobetrag.get();
    }
	
	public String getDatum() {
        return datum.get();
    }
	public String getLieferdatum() {
        return lieferdatum.get();
    }
	
	public StringProperty nachnameProperty() {
		return nachname;
	}
	public ObservableValue<Number> kundennrPropery() {
		return kunden_id;
	}
	public ObservableValue<Number> rechnungsnrProperty() {
		return rechnung_id;
	}
	public StringProperty telefonProperty() {
		return telefon;
	}
	public StringProperty emailProperty() {
		return email;
	}
	public StringProperty datumProperty() {
		return datum;
	}
	public StringProperty lieferdatumProperty() {
		return lieferdatum;
	}
	public StringProperty bruttoProperty() {
		return bruttobetrag;
	}
	
    public String getNachname() {
        return nachname.get();
    }
    public void setLastName(String fName) {
        nachname.set(fName);
    }
   
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String fName) {
        email.set(fName);
    }

	
}
