//package htl.diplomarbeit.ksm.model;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleBooleanProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
////public class Auftrag {
//	
//	private final SimpleIntegerProperty auftrags_nr;
//	private final SimpleIntegerProperty auftrag_kunden_nr;
//	private final SimpleStringProperty datum;
//	private final SimpleStringProperty lieferdatum;
//	private final SimpleBooleanProperty stromanschluss;
//	private final SimpleBooleanProperty wasseranschluss;
//	private final SimpleBooleanProperty demontage;
//	private final SimpleBooleanProperty montage;
//	private final SimpleStringProperty preis_netto;
//	private final SimpleStringProperty gesamtpreis;
//	private final SimpleStringProperty erst_anzahlung;
//	private final SimpleStringProperty zweit_anzahlung;
//	private final SimpleIntegerProperty erst_anzahlung_prozent;
//	private final SimpleIntegerProperty zweit_anzahlung_prozent;
//	
//	public Auftrag(int auftrags_nr, int auftrag_kunden_nr, String datum, String lieferdatum, boolean stromanschluss, boolean wasseranschluss, boolean demontage, boolean montage, String preis_netto, String gesamtpreis, String erst_anzahlung, String zweit_anzahlung, int erst_anzahlung_prozent, int zweit_anzahlung_prozent ) {
//		this.auftrags_nr = new SimpleIntegerProperty(auftrags_nr);
//		this.auftrag_kunden_nr = new SimpleIntegerProperty(auftrag_kunden_nr);
//		this.datum = new SimpleStringProperty(datum);
//		this.lieferdatum = new SimpleStringProperty(lieferdatum);
//		this.stromanschluss = new SimpleBooleanProperty(stromanschluss);
//		this.wasseranschluss = new SimpleBooleanProperty(wasseranschluss);
//		this.demontage = new SimpleBooleanProperty(demontage);
//		this.montage = new SimpleBooleanProperty(montage);
//		this.preis_netto = new SimpleStringProperty(preis_netto);
//		this.gesamtpreis = new SimpleStringProperty(gesamtpreis);
//		this.erst_anzahlung = new SimpleStringProperty(erst_anzahlung);
//		this.zweit_anzahlung = new SimpleStringProperty(zweit_anzahlung);
//		this.erst_anzahlung_prozent = new SimpleIntegerProperty(erst_anzahlung_prozent);
//		this.zweit_anzahlung_prozent = new SimpleIntegerProperty(zweit_anzahlung_prozent);
//	}
////	String edition, String sichtseiten, String arbeitsplatte, String regale, String nischenrückwand, String backrohr, String Kühlen,
////	String gefrieren, String dampfgarer, String amatur, String muellsystem, String steckdosen, String front, String griff, String rollo,
////	String kochfeld, String geschirrspueler, String mikrowelle, String spuele, String beleuchtung, boolean stromanschluss, boolean wasseranschluss,
////	String korpus, String glassschrank, String arbeitshöhe, String ladenausfuehrung, String dunstabzug, String filter, String waermeschublade, String sonstige,
////	double gesamtpreis, double erstanzahlung, double zweitanzahlung, int erstanzprozent, int zweitanzprozent
//	
//	public static String dateFormatter() {
//		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//		Date date = new Date();
//		String t = dateFormat.format(date);
//		return t;
//	}
////	
////	public ObservableValue<Number> customerIdProperty() {
////		return customerId;
////	}
////	public ObservableValue<Number> orderIdProperty() {
////		return orderId;
////	}
////
////	public StringProperty dateProperty() {
////		return date;
////	}
////
////	public String getFirstName() {
////		return firstName.get();
////	}
////
////	public void setFirstName(String firstName) {
////		this.firstName.set(firstName);
////	}
////
////	public StringProperty firstNameProperty() {
////		return firstName;
////	}
////
////	public String getLastName() {
////		return lastName.get();
////	}
////
////	public void setLastName(String lastName) {
////		this.lastName.set(lastName);
////	}
////
////	public StringProperty lastNameProperty() {
////		return lastName;
////	}
////
////	public String getDeliveryAddress() {
////		return deliveryAddress.get();
////	}
////
////	public void setDeliveryAddress(String street) {
////		this.deliveryAddress.set(street);
////	}
////
////	public StringProperty deliveryAddressProperty() {
////		return deliveryAddress;
////	}
////
////	public String getEmail() {
////		return eMail.get();
////	}
////
////	public void setEmail(String eMail) {
////		this.eMail.set(eMail);
////	}
////
////	public StringProperty eMailProperty() {
////		return eMail;
////	}
////
////	public String getPhoneNumber() {
////		return phoneNumber.get();
////	}
////
////	public void setPhoneNumber(String phoneNumber) {
////		this.phoneNumber.set(phoneNumber);
////	}
////
////	public StringProperty phoneNumberProperty() {
////		return phoneNumber;
////	}
//
//}
