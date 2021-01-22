package presentation;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class Validation {
		
	protected boolean confirmChanges() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Confirm changes to league");
		alert.setHeaderText("Are you sure you want to make changes?");
		alert.setContentText("Choose your option.");
		ButtonType confirmBtn = new ButtonType("Yes", ButtonData.OK_DONE);
		ButtonType cancelBtn = new ButtonType("No", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(confirmBtn, cancelBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			return true;
		else if (result.get() == cancelBtn)
			return false;
		return false;
	}

	protected boolean emptyStringWarning(String str) {
		if (str.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Tomt felt advarsel");
			alert.setHeaderText("DU HAR ET TOMT FELT!");
			alert.setContentText("Skriv venligst noget i tekst feltet");
			alert.show();
			return false;
		} else {
			return true;
		}
	}
	
	protected void matchPlayedWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Match WARNING");
		alert.setHeaderText("The match have already been played.");
		alert.setContentText("You cannot start the match twice");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}

}
