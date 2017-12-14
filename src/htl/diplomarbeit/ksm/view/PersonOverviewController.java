package htl.diplomarbeit.ksm.view;

import java.util.ArrayList;
import java.util.List;

import htl.diplomarbeit.ksm.MainApp;
import htl.diplomarbeit.ksm.model.Person;
import htl.diplomarbeit.ksm.util.DatabaseConnection;
import htl.diplomarbeit.ksm.util.DateUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class PersonOverviewController {
	@FXML
	TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;
	@FXML
	private TableColumn<Person, String> dateColumn;
	@FXML
	private TableColumn<Person, Number> customerIdColumn;
	@FXML
	private TableColumn<Person, String> phoneNumberColumn;
	@FXML
	private TableColumn<Person, String> eMailColumn;

	@FXML
	private TextField filterField;

	@FXML
	private Label genderLabel;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	@FXML
	private Label eMailLabel;
	@FXML
	private Label phoneNumberLabel;
	private boolean isEditBtn = true;

	// Reference to the main application.
	private MainApp mainApp;

	
	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */

	public PersonOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {
		
		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty());
		phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
		eMailColumn.setCellValueFactory(cellData -> cellData.getValue().eMailProperty());

		// Clear person details.
		showPersonDetails(null);

		// Listen for selection changes and show the person details when
		// changed.
		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));

		// Click event if row is clicked twice
		personTable.setRowFactory(rowevent -> {
			TableRow<Person> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					isEditBtn = true;
					Person rowData = row.getItem();
					boolean okClicked = mainApp.showPersonEditDialog(rowData);
					if (okClicked) {
						showPersonDetails(rowData);
					}
				}
			});
			return row;
		});
		
	}

	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person
	 *            the person or null
	 */
	private void showPersonDetails(Person person) {
		if (person != null) {
			// Fill the labels with info from the person object.

			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());

			genderLabel.setText(person.getGender());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(person.getPostalCode());
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(person.getBirthday());
			eMailLabel.setText(person.getEmail());
			phoneNumberLabel.setText(person.getPhoneNumber());

			// birthdayLabel.setText(...);
		} else {
			// Person is null, remove all the text.
			genderLabel.setText("");
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
			eMailLabel.setText("");
			phoneNumberLabel.setText("");
		}
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("Keine Auswahl");
			alert.setHeaderText("Kein Kunde wurde ausgewählt");
			alert.setContentText("Bitte wählen Sie einen Kunden in der Tabelle aus");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewPerson() {
		isEditBtn = false;
		//DatabaseConnection.getHighestCustomerId() is the customer id
		Person tempPerson = new Person(DatabaseConnection.getHighestCustomerId(), null, null, null, null, null, null, null, null, null, null);
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			personTable.setItems(DatabaseConnection.getFromDatabaseKunde());
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
		isEditBtn = true;
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}
	public boolean isEditBtn(){
		return isEditBtn;
	}

	/*
	 * * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		// Add observable list data to the table

		try {
			personTable.setItems(DatabaseConnection.getFromDatabaseKunde());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setItems(TextField filterField) throws Exception {
		// display all
		// data).
		FilteredList<Person> filteredData = new FilteredList<>(DatabaseConnection.tempList, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with
				// filter
				// text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (person.getLastName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Person> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView
		// comparator.
		sortedData.comparatorProperty().bind(personTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		personTable.setItems(sortedData);
	}

	// 1. Wrap the ObservableList in a FilteredList (initially

}
