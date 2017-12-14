package htl.diplomarbeit.ksm.view;

import htl.diplomarbeit.ksm.MainApp;
import htl.diplomarbeit.ksm.model.Person;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class RootLayoutController {
	private MainApp mainApp;
	public PersonOverviewController overviewController;
	@FXML
	private TableView<Person> personTable;
	@FXML
	public TextField filterField;

	@FXML
	private void initialize() {
		
	}

	public void filterSuche() {
		filterField.setOnKeyTyped(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				try {
					overviewController.setItems(filterField);
					
				} catch (Exception e) {
					e.getMessage();
				}
			}
		});
	}
	// TODO Auto-generated method stub

	@FXML
	private void showPasswordField() {
		mainApp.showPasswordField();
	}

	@FXML
	private void showStatistik() {
		mainApp.showStatistik();
	}

	@FXML
	private void showRechnungserstellung() {
		mainApp.showRechnungserstellung();
	}

	@FXML
	private void showAuftragsVerwaltung() {
		mainApp.showAuftragsVerwaltung();
	}
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}
}
