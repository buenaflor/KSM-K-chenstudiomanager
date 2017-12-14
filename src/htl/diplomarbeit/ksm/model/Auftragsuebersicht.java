package htl.diplomarbeit.ksm.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Auftragsuebersicht {
	private final StringProperty datum;
	private final StringProperty termin;
	private final IntegerProperty auftrag_id;
	private final IntegerProperty kunden_id;
	private final StringProperty vorname;
	private final StringProperty nachname;
	private final StringProperty telefon;
	private final StringProperty email;
	private final StringProperty lieferanschrift;
	private final StringProperty gesamtpreis;
	private final StringProperty preis_netto;
	private final StringProperty erst_anzahlung;
	private final StringProperty zweit_anzahlung;
	private final IntegerProperty erst_anzahlung_prozent;
	private final IntegerProperty zweit_anzahlung_prozent;
	private final BooleanProperty montage;
	private final BooleanProperty demontage;
	private final BooleanProperty wasseranschluss;
	private final BooleanProperty stromanschluss;

	public Auftragsuebersicht(String datum, String termin, int auftrags_nr, int kunden_nr, String vorname,
			String nachname, String telefon, String email, String lieferanschrift, String gesamtpreis,
			String preis_netto, String erst_anzahlung, String zweit_anzahlung, int erst_anzahlung_prozent,
			int zweit_anzahlung_prozent, boolean montage, boolean demontage, boolean wasseranschluss,
			boolean stromanschluss) {
		this.datum = new SimpleStringProperty(datum);
		this.termin = new SimpleStringProperty(termin);
		this.auftrag_id = new SimpleIntegerProperty(auftrags_nr);
		this.kunden_id = new SimpleIntegerProperty(kunden_nr);
		this.vorname = new SimpleStringProperty(vorname);
		this.nachname = new SimpleStringProperty(nachname);
		this.telefon = new SimpleStringProperty(telefon);
		this.email = new SimpleStringProperty(email);
		this.lieferanschrift = new SimpleStringProperty(lieferanschrift);
		this.gesamtpreis = new SimpleStringProperty(gesamtpreis);
		this.preis_netto = new SimpleStringProperty(preis_netto);
		this.erst_anzahlung = new SimpleStringProperty(erst_anzahlung);
		this.zweit_anzahlung = new SimpleStringProperty(zweit_anzahlung);
		this.erst_anzahlung_prozent = new SimpleIntegerProperty(erst_anzahlung_prozent);
		this.zweit_anzahlung_prozent = new SimpleIntegerProperty(zweit_anzahlung_prozent);
		this.montage = new SimpleBooleanProperty(montage);
		this.demontage = new SimpleBooleanProperty(demontage);
		this.wasseranschluss = new SimpleBooleanProperty(wasseranschluss);
		this.stromanschluss = new SimpleBooleanProperty(stromanschluss);
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

	public String getPreisnetto() {
		return preis_netto.get();
	}

	public String getErstAnzahlung() {
		return erst_anzahlung.get();
	}

	public String getZweitAnzahlung() {
		return zweit_anzahlung.get();
	}

	public int getErstAnzahlungProzent() {
		return erst_anzahlung_prozent.get();
	}

	public int getZweitAnzahlungProzent() {
		return zweit_anzahlung_prozent.get();
	}

	public boolean getMontage() {
		return montage.get();
	}

	public boolean getDemontage() {
		return demontage.get();
	}

	public boolean getWasseranschluss() {
		return wasseranschluss.get();
	}

	public boolean getStromanschluss() {
		return stromanschluss.get();
	}

	public int getKundenId() {
		return kunden_id.get();
	}

	public int getAuftragsId() {
		return auftrag_id.get();
	}

	public String getDatum() {
		return datum.get();
	}

	public String getTermin() {
		return termin.get();
	}

	public String getLieferanschrift() {
		return lieferanschrift.get();
	}

	public String getGesamtpreis() {
		return gesamtpreis.get();
	}

	public StringProperty nachnameProperty() {
		return nachname;
	}

	public ObservableValue<Number> kundennrPropery() {
		return kunden_id;
	}

	public ObservableValue<Number> auftragsnrProperty() {
		return auftrag_id;
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

	public StringProperty terminProperty() {
		return termin;
	}

	public StringProperty lieferanschriftProperty() {
		return lieferanschrift;
	}

	public StringProperty gesamtpreisProperty() {
		return gesamtpreis;
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
