package htl.diplomarbeit.ksm.view;

import htl.diplomarbeit.ksm.MainApp;
import htl.diplomarbeit.ksm.model.Auftragsuebersicht;
import htl.diplomarbeit.ksm.model.Person;
import htl.diplomarbeit.ksm.util.DatabaseConnection;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StammdatenController {

	@FXML
	private TableColumn<Person, Number> kundennrColumn;

	@FXML
	private TableColumn<Person, String> vornameColumn;

	@FXML
	private TableColumn<Person, String> nachnameColumn;
    @FXML
    private TextField filterField;
	@FXML
	private TableView<Person> sortedTable;
	public RechnungsController rechnungController;
	public AuftragsController auftragController;
	static boolean checkWindow;
	Stage stage;
	String vorname, nachname, strasse, plz, ort, geschlecht;
	int kundennr;
	
	MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}

	@FXML
	public void initialize() {
		vornameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		nachnameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		kundennrColumn.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty());

		sortedTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {	
			vorname = newValue.getFirstName();
			nachname = newValue.getLastName();
			strasse = newValue.getStreet();
			plz = newValue.getPostalCode();
			ort = newValue.getCity();
			kundennr = newValue.getCustomerId();
			geschlecht = newValue.getGender();
		});

		sortedTable.setRowFactory(rowevent -> {
			TableRow<Person> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
				{
					if(checkWindow){
						rechnungController.vornameField.setText(vorname);
						rechnungController.nachnameField.setText(nachname);
						rechnungController.strasseField.setText(strasse);
						rechnungController.ortField.setText(ort);
						rechnungController.plzField.setText(plz);
						rechnungController.kundennrField.setText(String.valueOf(kundennr));
						rechnungController.anredeBox.setValue(geschlecht);
						stage.close();
						System.out.println("Rechnung");
					}
					else if (!checkWindow){
						auftragController.vornameField.setText(vorname);
						auftragController.nachnameField.setText(nachname);
						auftragController.strasseField.setText(strasse);
						auftragController.ortField.setText(ort);
						auftragController.plzField.setText(plz);
						auftragController.kundennrField.setText(String.valueOf(kundennr));
						auftragController.anredeBox.setValue(geschlecht);
						stage.close();
						System.out.println("Auftrag");
					}
					
				}
			});			
			return row;
		});
		
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
		sortedData.comparatorProperty().bind(sortedTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		sortedTable.setItems(sortedData);
	}
	
	public void setDialogStage(Stage stage){
		this.stage = stage;
	}

}
