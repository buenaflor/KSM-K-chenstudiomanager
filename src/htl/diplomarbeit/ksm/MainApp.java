package htl.diplomarbeit.ksm;

import java.io.IOException;

import htl.diplomarbeit.ksm.model.Person;
import htl.diplomarbeit.ksm.view.AuftragsController;
import htl.diplomarbeit.ksm.view.NachKalkulationController;
import htl.diplomarbeit.ksm.view.PasswordWinController;
import htl.diplomarbeit.ksm.view.PersonEditDialogController;
import htl.diplomarbeit.ksm.view.PersonOverviewController;
import htl.diplomarbeit.ksm.view.RechnungsController;
import htl.diplomarbeit.ksm.view.RootLayoutController;
import htl.diplomarbeit.ksm.view.StammdatenController;
import htl.diplomarbeit.ksm.view.StatistikController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private RootLayoutController mRootLayoutController;
	private PersonOverviewController mPersonOverviewController;
	private RechnungsController mRechnungsController;
	private AuftragsController mAuftragsController;
	private StammdatenController mStammdatenController;
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	/**
	 * Returns the data as an observable list of Persons.
	 * 
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("KSM - Küchenstudiomanager");
		this.primaryStage.getIcons().add( new Image(
				MainApp.class.getResourceAsStream( "icon.png" )));
		
		initRootLayout();
		showPersonOverview();
		
		mRootLayoutController.overviewController = mPersonOverviewController;
		mRootLayoutController.filterSuche();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			RootLayoutController controller = loader.getController();
			controller.overviewController = this.mPersonOverviewController;
			controller.setMainApp(this);
			this.mRootLayoutController = controller;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPersonOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			PersonOverviewController controller = loader.getController();
			this.mPersonOverviewController = controller;
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person
	 *            the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPersonEditDialog(Person person) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Personen bearbeiten");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			PersonEditDialogController controller = loader.getController();
			controller.overviewController = mPersonOverviewController;
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);
			dialogStage.setResizable(false);
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showNachKalkulation() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/NachKalkulation.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Nachkalkulation");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			NachKalkulationController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 
	 */
	public void showPasswordField() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PasswordWin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Passwort eingeben");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			PasswordWinController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showRechnungserstellung() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RechnungErfassen.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Rechnungserstellung");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			RechnungsController controller = loader.getController();
			this.mRechnungsController = controller;
			controller.setMainApp(this);
			controller.setDialogStage(dialogStage);
			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAuftragsVerwaltung() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AuftragsVerwaltung.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Auftragsverwaltung");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			AuftragsController controller = loader.getController();
			this.mAuftragsController = controller;
			controller.setMainApp(this);
			controller.setDialogStage(dialogStage);
			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showStatistik() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Statistik.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Statistik");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the persons into the controller.
			 StatistikController controller = loader.getController();
			 
//			 controller.setPersonData(personData);

			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showStammdaten() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Stammdaten.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Stammdaten");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			StammdatenController controller = loader.getController();
			controller.rechnungController = this.mRechnungsController;
			controller.auftragController = this.mAuftragsController;
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);

			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
