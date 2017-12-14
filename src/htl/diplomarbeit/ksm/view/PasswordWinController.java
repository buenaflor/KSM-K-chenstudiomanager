package htl.diplomarbeit.ksm.view;

import htl.diplomarbeit.ksm.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PasswordWinController {

	private static final String password = "guess";
	private MainApp mainApp;
	Stage dialogStage;

	@FXML
	private PasswordField passField;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	@FXML
	private void handleOk() {

		if (!passField.getText().equals(password)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Fehler");
			alert.setHeaderText("Passwort ist falsch!");
			alert.setContentText("Versuchen Sie es erneut");

			alert.showAndWait();
		} else {
			mainApp.showNachKalkulation();
			dialogStage.close();
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
