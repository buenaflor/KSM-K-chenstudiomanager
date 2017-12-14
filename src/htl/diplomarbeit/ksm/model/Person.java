package htl.diplomarbeit.ksm.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Person {

	private final StringProperty firstName;
	private final StringProperty lastName;

	private final ObservableList<String> list = FXCollections.observableArrayList("Männlich", "Weiblich");
	private final StringProperty street;
	private final StringProperty postalCode;
	private final StringProperty city;
	private final StringProperty birthday;
	private final StringProperty gender;
	private final StringProperty eMail;
	private final StringProperty date;
	private final StringProperty phoneNumber;
	private final IntegerProperty customerId;

	public Person(int customerId, String gender, String firstName, String lastName, String street, String city, String postalCode, String birthday, String eMail, String phoneNumber, String date) {
		this.customerId = new SimpleIntegerProperty(customerId);
		this.gender = new SimpleStringProperty(gender);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.street = new SimpleStringProperty(street);
		this.city = new SimpleStringProperty(city);
		this.postalCode = new SimpleStringProperty(postalCode);
		this.birthday = new SimpleStringProperty(birthday);
		this.eMail = new SimpleStringProperty(eMail);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.date = new SimpleStringProperty(date);
	}
	
	public static String dateFormatter() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
		Date date = new Date();
		String t = dateFormat.format(date);
		return t;
	}

	public String getDate(){
		return date.get();
	}
	
	public int getCustomerId(){
		return customerId.get();
	}
	public ObservableValue<Number> customerIdProperty() {
		return customerId;
	}

	public StringProperty dateProperty() {
		return date;
	}

	public ObservableList<String> getList() {
		return list;
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender.set(gender);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public StringProperty streetProperty() {
		return street;
	}

	public String getPostalCode() {
		return postalCode.get();
	}

	public void setPostalCode(String postalCode) {
		this.postalCode.set(postalCode);
	}

	public StringProperty postalCodeProperty() {
		return postalCode;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public StringProperty cityProperty() {
		return city;
	}

	public String getBirthday() {
		return birthday.get();
	}

	public void setBirthday(String birthday) {
		this.birthday.set(birthday);
	}

	public StringProperty birthdayProperty() {
		return birthday;
	}

	public String getEmail() {
		return eMail.get();
	}

	public void setEmail(String eMail) {
		this.eMail.set(eMail);
	}

	public StringProperty eMailProperty() {
		return eMail;
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}

	public StringProperty phoneNumberProperty() {
		return phoneNumber;
	}

}
