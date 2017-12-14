package htl.diplomarbeit.ksm.view;

import htl.diplomarbeit.ksm.MainApp;
import htl.diplomarbeit.ksm.model.Person;
import htl.diplomarbeit.ksm.model.Rechnung;
import htl.diplomarbeit.ksm.model.Rechnungsposition;
import htl.diplomarbeit.ksm.model.Rechnungsuebersicht;
import htl.diplomarbeit.ksm.util.DatabaseConnection;
//import htl.diplomarbeit.ksm.util.Word;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class RechnungsController {

	@FXML
	private TableView<Rechnungsuebersicht> rechnungstable;
	
	@FXML
	public ComboBox<String> anredeBox;

	@FXML
	public TextField vornameField;

	@FXML
	public TextField nachnameField;

	@FXML
	public TextField strasseField;

	@FXML
	private ComboBox<String> landBox;

	@FXML
	public TextField plzField;

	@FXML
	public TextField ortField;

	@FXML
	private TextField rechnungsnrField;

	@FXML
	private DatePicker datumPicker;

	@FXML
	private DatePicker lieferdatumPicker;

	@FXML
	public TextField kundennrField;
	
	@FXML
	private TableColumn<Rechnungsuebersicht, String> datumColumn;

	@FXML
	private TableColumn<Rechnungsuebersicht, String> lieferdatumColumn;

	@FXML
	private TableColumn<Rechnungsuebersicht, Number> rechnungsnrColumn;

	@FXML
	private TableColumn<Rechnungsuebersicht, Number> kundennrColumn;

	@FXML
	private TableColumn<Rechnungsuebersicht, String> vornameColumn;

	@FXML
	private TableColumn<Rechnungsuebersicht, String> nachnameColumn;
	@FXML
	private TableColumn<Rechnungsuebersicht, String> telefonColumn;
	@FXML
	private TableColumn<Rechnungsuebersicht, String> emailColumn;
	@FXML
	private TableColumn<Rechnungsuebersicht, String> bruttoColumn;

	@FXML
	private TableView<Rechnungsposition> tableID;

	@FXML
	private TableColumn<Rechnungsposition, Integer> cID;

	@FXML
	private TableColumn<Rechnungsposition, String> cArtnr;

	@FXML
	private TableColumn<Rechnungsposition, String> cEinheit;

	@FXML
	private TableColumn<Rechnungsposition, String> cBezeichnung;

	@FXML
	private TableColumn<Rechnungsposition, String> cEinzelpreis;

	@FXML
	private TableColumn<Rechnungsposition, String> cGesamtpreis;

	@FXML
	private Label nettoLabel;

	@FXML
	private Label bruttoLabel;
	
    @FXML
    private TabPane rechnungTabPane;
    
    @FXML
    private Tab eingabeTab;
    
    @FXML
    private Tab uebersichtTab;
    
    String vorname, nachname, geschlecht, strasse, plz, stadt, datum, lieferdatum, nettobetrag, bruttobetrag;
	int kunden_id, rechnung_id;
	
	MainApp mainApp;
	
	public ObservableList<Rechnungsuebersicht> rechnungsData = FXCollections.observableArrayList();

	Stage dialogStage;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	LocalDate date;
	private int pos_id = 1;
	private int rechnungs_id = DatabaseConnection.getHighestRechnungsId();
	
	final ObservableList<Rechnungsposition> data = FXCollections.observableArrayList();
	final ObservableList<Rechnung> rechnungen = FXCollections.observableArrayList();

	public void initialize() {		
		vornameColumn.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
		nachnameColumn.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
		datumColumn.setCellValueFactory(cellData -> cellData.getValue().datumProperty());
		lieferdatumColumn.setCellValueFactory(cellData -> cellData.getValue().lieferdatumProperty());
		kundennrColumn.setCellValueFactory(cellData -> cellData.getValue().kundennrPropery());
		rechnungsnrColumn.setCellValueFactory(cellData -> cellData.getValue().rechnungsnrProperty());
		telefonColumn.setCellValueFactory(cellData -> cellData.getValue().telefonProperty());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		bruttoColumn.setCellValueFactory(cellData -> cellData.getValue().bruttoProperty());
		
		StammdatenController.checkWindow = true;
		
		// Setzt DatePicker auf das Aktuelle Datum
		datumPicker.setPromptText(dateFormatter());
		System.out.println(datumPicker.getPromptText());
		
		
		rechnungs_id += 1;
		rechnungsnrField.setText(String.valueOf(rechnungs_id));

		cID.setCellValueFactory(new PropertyValueFactory<Rechnungsposition, Integer>("rID"));
		cBezeichnung.setCellValueFactory(new PropertyValueFactory<Rechnungsposition, String>("rBezeichnung"));
		cEinzelpreis.setCellValueFactory(new PropertyValueFactory<Rechnungsposition, String>("rEinzelpreis"));

		tableID.setEditable(true);
//		Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
//			public TableCell call(TableColumn p) {
//				System.out.println(new EditingCell().toString());
//				return new EditingCell();
//			}
//		};
	
		cBezeichnung.setCellFactory(TextFieldTableCell.<Rechnungsposition>forTableColumn());
		cEinzelpreis.setCellFactory(TextFieldTableCell.<Rechnungsposition>forTableColumn());
	
//		tableID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {          
//                       System.out.println(newValue.getRID());
//        });
		
		cBezeichnung.setOnEditCommit(x -> x.getRowValue().setRBezeichnung(x.getNewValue()));		
		cEinzelpreis.setOnEditCommit(x -> {
			checkForValidInput(x.getNewValue());
			x.getRowValue().setREinzelpreis(x.getNewValue());
			sumBetrag();
		});	
		
		//Nimmt die Werte von der geklickten Reihe
		rechnungstable.getSelectionModel().selectedItemProperty()
		.addListener(			
		(observable, oldValue, newValue) -> { 
			
			vorname = newValue.getVorname();
			nachname = newValue.getNachname();
			datum = newValue.getDatum();
			lieferdatum = newValue.getLieferdatum();
			kunden_id = (int) newValue.getKundenId();
			rechnung_id = (int) newValue.getRechnungsId();
			nettobetrag = newValue.getNettobetrag();
			bruttobetrag = newValue.getBruttobetrag();
			System.out.println(kunden_id);
			for (Person kunde : DatabaseConnection.tempList) {
				if(kunde.getCustomerId() == kunden_id){
					geschlecht = kunde.getGender();
					strasse = kunde.getStreet();
					plz = kunde.getPostalCode();
					stadt = kunde.getCity();
				}
			}
		});
	
		rechnungstable.setRowFactory(rowevent -> {
			TableRow<Rechnungsuebersicht> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					System.out.println("klick klick");
					rechnungTabPane.getSelectionModel().select(eingabeTab);
					kundennrField.setText(Integer.toString(kunden_id));
					rechnungsnrField.setText(Integer.toString(rechnung_id));
					vornameField.setText(vorname);
					nachnameField.setText(nachname);
					strasseField.setText(strasse);
					plzField.setText(plz);
					ortField.setText(stadt);
					anredeBox.setValue(geschlecht);
					datumPicker.setValue(LocalDate.parse(datum, dateFormat));
					lieferdatumPicker.setValue(LocalDate.parse(lieferdatum, dateFormat));
					nettoLabel.setText(nettobetrag);
					bruttoLabel.setText(bruttobetrag);
					tableID.setItems(DatabaseConnection.getFromDatabaseRechnungsposition(rechnung_id));
				}
			});
			return row;
		});
		
		tableID.setItems(data);
		
    	rechnungstable.setItems(DatabaseConnection.getFromDatabaseRechnung());
	}

	@FXML
    void testBtn() {
    	for (Rechnungsposition rechnung : data) {    		
    		System.out.println(rechnung.getRBezeichnung() + " " + rechnung.getREinzelpreis());
		}
    }
    
    @FXML
    void onTestdaten(ActionEvent event) {
    	kundennrField.setText("1");
    	anredeBox.setValue("Herr");
    	vornameField.setText("Max");
    	nachnameField.setText("Mustermann");
    	strasseField.setText("Musterstraﬂe 666");
    	plzField.setText("1220");
    	ortField.setText("Wien");
    }
	@FXML
    void onFelderLeeren(ActionEvent event) {
    	kundennrField.setText(null);
    	anredeBox.setValue(null);
    	vornameField.setText(null);
    	nachnameField.setText(null);
    	strasseField.setText(null);
    	landBox.setValue(null);
    	plzField.setText(null);
    	ortField.setText(null);
    }
	
    @FXML
    void addPosition(ActionEvent event) {
    	data.add(new Rechnungsposition(pos_id++, "", "10,00"));
    	System.out.println("Added");
    }
    
	public static String dateFormatter() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date();
		String t = dateFormat.format(date);
		return t;
	}
	
	public ObservableList<Rechnungsposition> getRechnungspositionen(){
		return data;
	}
	
	//Helper-Klassen
    private void checkForValidInput(String preis) {
    	//ToDo: Check with regex
	}
    
	public void sumBetrag(){
		double sumTemp = 0.0;	
		for (Rechnungsposition rechnung : data) {
			String temp = rechnung.getREinzelpreis().replace(',', '.');
			sumTemp += Double.parseDouble(temp);
		}		
		nettoLabel.setText(String.valueOf(sumTemp).replace('.', ','));
		bruttoLabel.setText(String.valueOf(sumTemp*1.2).replace('.', ','));
	}
	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub
		this.dialogStage = dialogStage;
	}
	
	
    @FXML
    void onOK() {
    	if (DatabaseConnection.checkRechnungsId(Integer.parseInt(rechnungsnrField.getText()))) {
			System.out.println("Nummer bereits vorhanden");
		}else
		{
			System.out.println("Nummer nicht vorhanden");
		}
    	
    	
        String datum = datumPicker.getValue().format(dateFormat);
        String lieferdatum = lieferdatumPicker.getValue().format(dateFormat);
    	
    	BigDecimal netto = new BigDecimal(nettoLabel.getText().replace(',','.'));
    	BigDecimal brutto = new BigDecimal(bruttoLabel.getText().replace(',','.'));

    	
    	DatabaseConnection.instertInDatabaseRechnung(Integer.parseInt(rechnungsnrField.getText()), Integer.parseInt(kundennrField.getText()), datum, lieferdatum, netto, brutto);
    	for (Rechnungsposition rechnungsposition : data) {
    		BigDecimal einzelpreis = new BigDecimal(rechnungsposition.getREinzelpreis().replace(',', '.'));
    		DatabaseConnection.instertInDatabaseRechnungsposition(rechnungsposition.getRID(), Integer.parseInt(rechnungsnrField.getText()), rechnungsposition.getRBezeichnung(), einzelpreis);
		}
    	
    	rechnungstable.setItems(DatabaseConnection.getFromDatabaseRechnung());
    }

    @FXML
    void onExport(ActionEvent event) {
    	String dateiname = "Rechnung_" + rechnungsnrField.getText() + ".docx";
    	createWordDocumentRechnung(dateiname);
    }
    
	public void createWordDocumentRechnung(String dateiname){
//		XWPFDocument document = new XWPFDocument(); // neues Dokument erstellen
//
//		XWPFParagraph paragraphAnschrift = document.createParagraph(); // neuen Paragraph erstellen
//		XWPFRun runAnschrift = paragraphAnschrift.createRun();		
		
//		runAnschrift.addBreak();
//		runAnschrift.addBreak();
//		runAnschrift.addBreak();
//		//Anrede
//		if (anredeBox.getValue().equals("M‰nnlich")) {
//			runAnschrift.setText("Herr"); runAnschrift.addBreak();
//		}else if (anredeBox.getValue().equals("Weiblich"))
//		{
//			runAnschrift.setText("Frau"); runAnschrift.addBreak();
//		}
//		runAnschrift.setText(vornameField.getText() + " "); runAnschrift.setText(nachnameField.getText()); runAnschrift.addBreak();
//		runAnschrift.setText(strasseField.getText()); runAnschrift.addBreak();
//		runAnschrift.setText(plzField.getText() + " "); runAnschrift.setText(ortField.getText());
//		
//		runAnschrift.addBreak();
//		runAnschrift.addBreak();
//		runAnschrift.addBreak();
//		runAnschrift.addBreak();
//		runAnschrift.addBreak();
//		runAnschrift.addBreak();
		
//		XWPFParagraph paragraphRechnungsInfo = document.createParagraph(); // neuen Paragraph erstellen
//		XWPFRun runRechnungsInfo = paragraphAnschrift.createRun();	
//		
//		runRechnungsInfo.setText("Rechnung Nr. " + rechnungsnrField.getText());
//		try {
//			FileOutputStream output = new FileOutputStream(dateiname);
//			document.write(output);
//			output.close();
//			System.out.println(dateiname + " wurde erstellt.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
    
    @FXML
    void onCancel(ActionEvent event) {
    	dialogStage.close();
    }
    
	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
		
	}
	
	public void showStammdaten(){
		mainApp.showStammdaten();
	}
	
	
}
