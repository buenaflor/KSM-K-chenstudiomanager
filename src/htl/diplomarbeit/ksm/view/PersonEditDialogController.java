package htl.diplomarbeit.ksm.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

import htl.diplomarbeit.ksm.model.Person;
import htl.diplomarbeit.ksm.util.DatabaseConnection;
import htl.diplomarbeit.ksm.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author KSM
 */
public class PersonEditDialogController {
	@FXML
	private ComboBox<String> genderBox;
	
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField birthdayField;
	
	@FXML
	private TextField eMail;
	@FXML
	private TextField phoneNumber;
	
	private Stage dialogStage;
	public PersonOverviewController overviewController;
	private Person person;
	private boolean okClicked = false;
	public boolean isEditBtn;
	private static int count = DatabaseConnection.getHighestCustomerId();

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		 
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
	
		genderBox.setItems(person.getList());
		genderBox.setValue(person.getGender());
		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		postalCodeField.setText(person.getPostalCode());
		cityField.setText(person.getCity());
		birthdayField.setText(person.getBirthday());
		eMail.setText(person.getEmail());
		phoneNumber.setText(person.getPhoneNumber());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			person.setGender(genderBox.getValue());
			person.setFirstName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setCity(cityField.getText());
			person.setPostalCode(postalCodeField.getText());
			person.setBirthday(birthdayField.getText());
			person.setEmail(eMail.getText());
			person.setPhoneNumber(phoneNumber.getText());
			
			count += 1;
			
			try {
				if(overviewController.isEditBtn() != true){
				DatabaseConnection.instertInDatabaseKunde(count, genderBox.getValue(), firstNameField.getText(), lastNameField.getText(), streetField.getText(), cityField.getText(), postalCodeField.getText(), birthdayField.getText(), eMail.getText(), phoneNumber.getText(), Person.dateFormatter());
				} else {
					DatabaseConnection.updateInDatabaseKunde(person.getCustomerId(), genderBox.getValue(), firstNameField.getText(), lastNameField.getText(), streetField.getText(), cityField.getText(), postalCodeField.getText(), birthdayField.getText(), eMail.getText(), phoneNumber.getText());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			okClicked = true;
			dialogStage.close();
		}
	}

	
	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "Kein gültiger Vorname!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "Kein gültiger Nachname!\n";
		}
		if (streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "Keine gültige Straße!\n";
		}

		if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
			errorMessage += "Keine gültige Postleitzahl!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gültige Postleitzahl (Geben Sie bitte Zahlen ein)!\n";
			}
		}

		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "Keine gültige Stadt!\n";
		}

		if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
			errorMessage += "Kein gültiges Geburtstagsdatum!\n";
		} else {
			if (!DateUtil.validDate(birthdayField.getText())) {
				errorMessage += "Kein gültiges Geburtstagsdatum. Benutzen Sie dieses Format: dd.mm.yyyy!\n";
			}
		}
		
		if (phoneNumber.getText() == null || phoneNumber.getText().length() == 0) {
			errorMessage += "Keine gültige Telefonnummer!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gültige Telefonnummer (Geben Sie bitte Zahlen ein)!\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}
