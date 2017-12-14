package htl.diplomarbeit.ksm.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import htl.diplomarbeit.ksm.model.Person;
import htl.diplomarbeit.ksm.model.Rechnungsuebersicht;
import htl.diplomarbeit.ksm.MainApp;
import htl.diplomarbeit.ksm.model.Auftragsuebersicht;
import htl.diplomarbeit.ksm.util.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AuftragsController {

	Stage dialogStage;

	@FXML
	private TabPane auftragTabPane;

	@FXML
	private Tab eingabeTab;
	@FXML
	private Tab uebersichtTab;
	@FXML
	private TableView<Auftragsuebersicht> auftragsTable;
	public ObservableList<Auftragsuebersicht> auftragsData = FXCollections.observableArrayList();
	@FXML
	private TableColumn<Auftragsuebersicht, String> firstNameColumn;
	@FXML
	private TableColumn<Auftragsuebersicht, String> lastNameColumn;
	@FXML
	private TableColumn<Auftragsuebersicht, String> dateColumn;
	@FXML
	private TableColumn<Auftragsuebersicht, String> terminColumn;
	@FXML
	private TableColumn<Auftragsuebersicht, Number> customerIdColumn;
	@FXML
	private TableColumn<Auftragsuebersicht, Number> orderIdColumn;
	@FXML
	private TableColumn<Auftragsuebersicht, String> phoneNumberColumn;
	@FXML
	private TableColumn<Auftragsuebersicht, String> eMailColumn;
	@FXML
	private TableColumn<Auftragsuebersicht, String> deliveryAddressColumn;
	@FXML
	private TableColumn<Auftragsuebersicht, String> priceColumn;

	@FXML
	private TextArea positionField_30;

	@FXML
	private TextField positionField_1;

	@FXML
	private TextField positionField_4;

	@FXML
	private TextField positionField_7;

	@FXML
	private TextField positionField_10;

	@FXML
	private TextField positionField_13;

	@FXML
	private TextField positionField_16;

	@FXML
	private TextField positionField_19;

	@FXML
	private TextField positionField_22;

	@FXML
	private TextField positionField_25;

	@FXML
	private TextField positionField_27;

	@FXML
	private TextField positionField_28;

	@FXML
	private TextField positionField_29;

	@FXML
	private TextField positionField_2;

	@FXML
	private TextField positionField_5;

	@FXML
	private TextField positionField_11;

	@FXML
	private TextField positionField_14;

	@FXML
	private TextField positionField_17;

	@FXML
	private TextField positionField_20;

	@FXML
	private TextField positionField_23;

	@FXML
	private TextField positionField_26;

	@FXML
	private TextField positionField_3;

	@FXML
	private TextField positionField_6;

	@FXML
	private TextField positionField_9;

	@FXML
	private TextField positionField_15;

	@FXML
	private TextField positionField_18;

	@FXML
	private TextField positionField_21;

	@FXML
	private TextField positionField_24;

	@FXML
	private TextField positionField_8;

	@FXML
	private TextField positionField_12;

	@FXML
	private DatePicker datumPicker;

	@FXML
	private DatePicker lieferdatumPicker;

	@FXML
	private RadioButton demontageRadioButton;

	@FXML
	private RadioButton montageRadioButton;

	@FXML
	private RadioButton stromanschlussRadioButton;

	@FXML
	private RadioButton wasseranschlussRadioButton;

	@FXML
	public TextField kundennrField;

	@FXML
	private TextField ersteAnzahlungFieldProzent;

	@FXML
	private TextField ersteAnzahlungField;

	@FXML
	private TextField zweiteAnzahlungFieldProzent;

	@FXML
	private TextField zweiteAnzahlungField;

	@FXML
	private TextField nettoPreisField;

	@FXML
	private Label gesamtPreisLabel;

	@FXML
	private Label umsatzsteuerBetragLabel;

	@FXML
	private Label erstAnzahlungLabel;

	@FXML
	private Label zweitAnzahlungLabel;

	@FXML
	private Label auftragsIdLabel;

	@FXML
	public TextField vornameField;

	@FXML
	public TextField nachnameField;

	@FXML
	public TextField strasseField;

	@FXML
	public TextField plzField;

	@FXML
	public TextField ortField;

	@FXML
	private TextField lieferanschriftField;

	@FXML
	public ComboBox<String> anredeBox;

	@FXML
	private Label restzahlungLabel;

	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	private static int count = DatabaseConnection.getHighestAuftragsId();

	List<TextField> myList = new ArrayList<TextField>();

	String datum, termin, vorname, nachname, geschlecht, strasse, plz, stadt, lieferanschrift, preis_netto, gesamtpreis,
			erst_zahlung, zweit_zahlung;
	int kunden_id, auftrag_id, erst_anzahlung_prozent, zweit_anzahlung_prozent;
	Boolean stromanschluss, wasseranschluss, demontage, montage;

	MainApp mainApp;

	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub
		this.dialogStage = dialogStage;
	}

	@FXML
	public void initialize() {
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().datumProperty());
		terminColumn.setCellValueFactory(cellData -> cellData.getValue().terminProperty());
		customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().kundennrPropery());
		orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().auftragsnrProperty());
		phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().telefonProperty());
		eMailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		deliveryAddressColumn.setCellValueFactory(cellData -> cellData.getValue().lieferanschriftProperty());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().gesamtpreisProperty());

		StammdatenController.checkWindow = false;
		
		
		count += 1;
		auftragsIdLabel.setText(String.valueOf(count));

		nettoPreisField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				try {
					if (ke.getCode().equals(KeyCode.ENTER)) {
						setGesamtpreis();
					}

				} catch (Exception e) {
					e.getMessage();
				}
			}
		});

		myList.add(positionField_1);
		myList.add(positionField_2);
		myList.add(positionField_3);
		myList.add(positionField_4);
		myList.add(positionField_5);
		myList.add(positionField_6);
		myList.add(positionField_7);
		myList.add(positionField_8);
		myList.add(positionField_9);
		myList.add(positionField_10);
		myList.add(positionField_11);
		myList.add(positionField_12);
		myList.add(positionField_13);
		myList.add(positionField_14);
		myList.add(positionField_15);
		myList.add(positionField_16);
		myList.add(positionField_17);
		myList.add(positionField_18);
		myList.add(positionField_19);
		myList.add(positionField_20);
		myList.add(positionField_21);
		myList.add(positionField_22);
		myList.add(positionField_23);
		myList.add(positionField_24);
		myList.add(positionField_25);
		myList.add(positionField_26);
		myList.add(positionField_27);
		myList.add(positionField_28);
		myList.add(positionField_29);

		// Nimmt die Werte von der geklickten Reihe
		auftragsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

			vorname = newValue.getVorname();
			nachname = newValue.getNachname();
			datum = newValue.getDatum();
			termin = newValue.getTermin();
			kunden_id = (int) newValue.getKundenId();
			auftrag_id = (int) newValue.getAuftragsId();
			lieferanschrift = newValue.getLieferanschrift();
			gesamtpreis = newValue.getGesamtpreis();
			System.out.println(kunden_id);
			for (Person kunde : DatabaseConnection.tempList) {
				if (kunde.getCustomerId() == kunden_id) {
					geschlecht = kunde.getGender();
					strasse = kunde.getStreet();
					plz = kunde.getPostalCode();
					stadt = kunde.getCity();
				}
			}
			for (Auftragsuebersicht auftrag : DatabaseConnection.tempList2) {
				if (auftrag.getAuftragsId() == auftrag_id) {
					preis_netto = auftrag.getPreisnetto();
					erst_zahlung = auftrag.getErstAnzahlung();
					zweit_zahlung = auftrag.getZweitAnzahlung();
					erst_anzahlung_prozent = auftrag.getErstAnzahlungProzent();
					zweit_anzahlung_prozent = auftrag.getZweitAnzahlungProzent();
					montage = auftrag.getMontage();
					demontage = auftrag.getDemontage();
					wasseranschluss = auftrag.getWasseranschluss();
					stromanschluss = auftrag.getStromanschluss();
				}
			}
		});

		auftragsTable.setRowFactory(rowevent -> {
			TableRow<Auftragsuebersicht> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					System.out.println("klick klick");
					auftragTabPane.getSelectionModel().select(eingabeTab);
					kundennrField.setText(Integer.toString(kunden_id));
					auftragsIdLabel.setText(Integer.toString(auftrag_id));
					vornameField.setText(vorname);
					nachnameField.setText(nachname);
					strasseField.setText(strasse);
					plzField.setText(plz);
					ortField.setText(stadt);
					anredeBox.setValue(geschlecht);
					datumPicker.setValue(LocalDate.parse(datum, dateFormat));
					lieferdatumPicker.setValue(LocalDate.parse(termin, dateFormat));
					lieferanschriftField.setText(lieferanschrift);
					nettoPreisField.setText(preis_netto);
					gesamtPreisLabel.setText(gesamtpreis);
					erstAnzahlungLabel.setText(erst_zahlung);
					zweitAnzahlungLabel.setText(zweit_zahlung);
					ersteAnzahlungFieldProzent.setText(String.valueOf(erst_anzahlung_prozent));
					zweiteAnzahlungFieldProzent.setText(String.valueOf(zweit_anzahlung_prozent));
					montageRadioButton.setSelected(montage);
					demontageRadioButton.setSelected(demontage);
					wasseranschlussRadioButton.setSelected(wasseranschluss);
					stromanschlussRadioButton.setSelected(stromanschluss);
					int counter = 1;
					for (TextField position : myList) {
						position.setText(DatabaseConnection.getFromDatabaseAuftragsposition(auftrag_id, counter));
						counter++;
					}
					positionField_30.setText(DatabaseConnection.getFromDatabaseAuftragsposition(auftrag_id, 30));
					restzahlungLabel.setText(String
							.valueOf(Double.parseDouble(gesamtPreisLabel.getText().replace(',', '.'))
									- (Double.parseDouble(erstAnzahlungLabel.getText().replace(',', '.'))
											+ Double.parseDouble(zweitAnzahlungLabel.getText().replace(',', '.'))))
							.replace('.', ','));
				}
			});
			return row;
		});

		auftragsTable.setItems(DatabaseConnection.getFromDatabaseAuftrag());
	}

	@FXML
	public void handleOk() {
		String datum = datumPicker.getValue().format(dateFormat);
		String termin = lieferdatumPicker.getValue().format(dateFormat);

		BigDecimal preis_netto = new BigDecimal(nettoPreisField.getText().replace(',', '.'));
		BigDecimal gesamtpreis = new BigDecimal(gesamtPreisLabel.getText().replace(',', '.'));
		// BigDecimal erst_anzahlung = new
		// BigDecimal(ersteAnzahlungField.getText().replace(',','.'));
		// BigDecimal zweit_anzahlung = new
		// BigDecimal(zweiteAnzahlungField.getText().replace(',','.'));
		BigDecimal erst_anzahlung = new BigDecimal(erstAnzahlungLabel.getText().replace(',', '.'));
		BigDecimal zweit_anzahlung = new BigDecimal(zweitAnzahlungLabel.getText().replace(',', '.'));

		DatabaseConnection.insertInDatabaseAuftrag(count, Integer.parseInt(kundennrField.getText()), datum, termin,
				lieferanschriftField.getText(), stromanschlussRadioButton.isSelected(),
				wasseranschlussRadioButton.isSelected(), demontageRadioButton.isSelected(),
				montageRadioButton.isSelected(), preis_netto, gesamtpreis, erst_anzahlung, zweit_anzahlung,
				Integer.parseInt(ersteAnzahlungFieldProzent.getText()),
				Integer.parseInt(zweiteAnzahlungFieldProzent.getText()));

		int counter = 1;
		for (TextField position : myList) {
			System.out.println("Position " + counter + " " + position.getId());

			DatabaseConnection.instertInDatabaseAuftragsposition(count, counter, counter, position.getText());
			counter++;
		}
		// Positionsnummer 30: Sonstiges (Sonderfall)
		DatabaseConnection.instertInDatabaseAuftragsposition(count, 30, 30, positionField_30.getText());
		auftragsTable.setItems(DatabaseConnection.getFromDatabaseAuftrag());
	}

	public void setGesamtpreis() {
		double nettoPreis = Double.parseDouble(nettoPreisField.getText());
		double umsatzsteuerBetrag = Double.parseDouble(nettoPreisField.getText().replace(',', '.')) * 0.2;

		umsatzsteuerBetragLabel.setText(String.valueOf(umsatzsteuerBetrag).replace('.', ','));
		gesamtPreisLabel.setText(String.valueOf(nettoPreis * 1.2).replace('.', ','));
	}

	@FXML
	public void setAnzahlung1(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			double anzahlungsPreis = Double.parseDouble(ersteAnzahlungFieldProzent.getText()) / 100
					* Double.parseDouble(gesamtPreisLabel.getText().replace(',', '.'));
			erstAnzahlungLabel.setText(String.valueOf(anzahlungsPreis).replace('.', ','));
		}
	}

	@FXML
	public void setAnzahlung2(KeyEvent event) {

		if (event.getCode().equals(KeyCode.ENTER)) {
			double anzahlungsPreis = Double.parseDouble(zweiteAnzahlungFieldProzent.getText()) / 100
					* Double.parseDouble(gesamtPreisLabel.getText().replace(',', '.'));
			zweitAnzahlungLabel.setText(String.valueOf(anzahlungsPreis).replace('.', ','));

			restzahlungLabel.setText(String
					.valueOf(Double.parseDouble(gesamtPreisLabel.getText().replace(',', '.'))
							- (Double.parseDouble(erstAnzahlungLabel.getText().replace(',', '.'))
									+ Double.parseDouble(zweitAnzahlungLabel.getText().replace(',', '.'))))
					.replace('.', ','));

		}

	}
	

    @FXML
    void onFelderLeeren(ActionEvent event) {
    	kundennrField.setText(null);
    	anredeBox.setValue(null);
    	vornameField.setText(null);
    	nachnameField.setText(null);
    	strasseField.setText(null);
    	plzField.setText(null);
    	ortField.setText(null);
    }
	
    @FXML
    void onAlleFelderLeeren(ActionEvent event) {
    	kundennrField.setText(null);
    	anredeBox.setValue(null);
    	vornameField.setText(null);
    	nachnameField.setText(null);
    	strasseField.setText(null);
    	plzField.setText(null);
    	ortField.setText(null);
    	
    	for (TextField field : myList) {
			field.setText(null);
		}
    	positionField_30.setText(null);
    	
    	datumPicker.setValue(null);
    	lieferdatumPicker.setValue(null);
    	lieferanschriftField.setText(null);
    	
    	nettoPreisField.setText(null);
    	umsatzsteuerBetragLabel.setText("0,00");
    	gesamtPreisLabel.setText("0,00");
    	ersteAnzahlungFieldProzent.setText(null);
    	zweiteAnzahlungFieldProzent.setText(null);
    	erstAnzahlungLabel.setText("0,00");
    	zweitAnzahlungLabel.setText("0,00");
    	restzahlungLabel.setText("0,00");
    	
    	montageRadioButton.setSelected(false);
    	demontageRadioButton.setSelected(false);
    	wasseranschlussRadioButton.setSelected(false);
    	stromanschlussRadioButton.setSelected(false);
    }

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}

	public void showStammdaten() {
		mainApp.showStammdaten();
	}
}
