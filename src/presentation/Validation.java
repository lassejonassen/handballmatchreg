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

	protected void teamLeagueAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Team ERROR");
		alert.setHeaderText("Team hasn't been asigned a league.");
		alert.setContentText("Du skal vælge hvilken liga holdet skal tilhøre.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}

	protected void matchResumeWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Kamp ADVARSEL");
		alert.setHeaderText("Kampen er allerede færdig.");
		alert.setContentText("Du kan ikke fortsætte en kamp som er færdig.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}

	protected void matchResumeWarning2() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Kamp ADVARSEL");
		alert.setHeaderText("Kampen er ikke stoppet.");
		alert.setContentText("Du kan ikke fortsætte en kamp, som ikke er på pause.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}

	protected void matchStopWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Kamp ADVARSEL");
		alert.setHeaderText("Kampen er stoppet");
		alert.setContentText("Du kan ikke stoppe en kamp som er færdig.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}

	protected void matchStopWarning2() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Kamp ADVARSEL");
		alert.setHeaderText("Kampen ikke igang.");
		alert.setContentText("Du kan ikke stoppe en kamp der ikke er i gang.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}

	protected void matchNotPlayedWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Kamp ADVARSEL");
		alert.setHeaderText("Kampen er ikke spillet.");
		alert.setContentText("Du kan ikke se rapporten, hvis kampen ikke er spillet.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}

	protected void noLeagueChoosenAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("League ERROR");
		alert.setHeaderText("A league hasn't been choosen");
		alert.setContentText("Du skal vælge en liga før du kan trykke 'Opdater'");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}
	
	protected void matchStartedWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Kamp ADVARSEL");
		alert.setHeaderText("Kampen er igang");
		alert.setContentText("Du kan ikke starte en kampe som allerede er i gang.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}
	
	protected void matchPausedWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Kamp ADVARSEL");
		alert.setHeaderText("Kampen er pauset");
		alert.setContentText("Du kan ikke give m�l eller udvisninger s� l�nge kampen er pauset.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}
	
	
}
