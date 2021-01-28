package presentation;

import java.util.Optional;

import data.Team;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class Validation {

	protected boolean confirmChanges(String str) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Accepter ændringer til: " + str);
		alert.setHeaderText("Er du sikker på du vil lave ændringer?");
		alert.setContentText("Vælg en mulighed");
		ButtonType confirmBtn = new ButtonType("Ja", ButtonData.OK_DONE);
		ButtonType cancelBtn = new ButtonType("Nej", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(confirmBtn, cancelBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			return true;
		else if (result.get() == cancelBtn)
			return false;
		return false;
	}
	
	protected boolean confirmMatchCreate(Team team1, Team team2)
	{
		if(team1.getId()==team2.getId())
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Samme hold valgt to gange");
			alert.setHeaderText("DU HAR VALGT DET SAMME HOLD TO GANGE");
			alert.setContentText("Vælg venligst to forskellige hold");
			alert.show();
			return false;
		}else
		{
			return true;
		}
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
		alert.setTitle("Kamp ADVARSEL");
		alert.setHeaderText("Kampen er blevet spillet");
		alert.setContentText("Du kan ikke starte en kamp to gange.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}

	protected void teamLeagueAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Hold FEJL");
		alert.setHeaderText("Holdet er ikke tildelt en liga");
		alert.setContentText("Du skal vaelge hvilken liga holdet skal tilhoere.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}

	protected void matchResumeWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Kamp ADVARSEL");
		alert.setHeaderText("Kampen er allerede faerdig.");
		alert.setContentText("Du kan ikke fortsaette en kamp som er faerdig.");
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
		alert.setContentText("Du kan ikke fortsaette en kamp, som ikke er paa pause.");
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
		alert.setContentText("Du kan ikke stoppe en kamp som er fÃ¦rdig.");
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
		alert.setTitle("Liga FEJL");
		alert.setHeaderText("Der er ikke valgt en liga");
		alert.setContentText("Du skal vaelge en liga faar du kan trykke 'Opdater'");
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
		alert.setContentText("Du kan ikke give mål eller udvisninger så længe kampen er pauset.");
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}
	
	protected void csvImportError(String str) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Import FEJL");
		alert.setHeaderText("Der opstod en fejl under importering af rapport.");
		alert.setContentText(str);
		ButtonType confirmBtn = new ButtonType("OK", ButtonData.OK_DONE);
		alert.getButtonTypes().setAll(confirmBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			alert.close();
	}
	
	
}
