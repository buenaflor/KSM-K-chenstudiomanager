package htl.diplomarbeit.ksm.view;

import htl.diplomarbeit.ksm.model.NachKalkulation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class NachKalkulationController {

	Stage dialogStage;
	ObservableList data = FXCollections.observableArrayList();
	@FXML
	private Button addLine_btn;
	
	@FXML
	private TableView nachkalk_table;
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void initialize(){
		
		nachkalk_table.setStyle("-fx-background-color:red");
	}
	
	@FXML
	private void addLine(){
		data.add(new NachKalkulation());
		
		nachkalk_table.setItems(data);
		
	}

}
