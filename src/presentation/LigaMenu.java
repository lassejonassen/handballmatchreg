package presentation;

import logic.LeagueImpl;

import java.util.ArrayList;
import java.util.Optional;

import data.DataLayer;
import data.League;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;


public class LigaMenu 
{	
	private Button createLeagueBtn = new Button("Opret Liga");
	private Button updateLigaBtn = new Button("Opdater Liga");
	private Button deleteLigaBtn = new Button("Slet Liga");
	private ComboBox<League> leagueDropdown = new ComboBox<League>();
	
	private Button backBtn = new Button("Tilbage");
	
	private Button createTeamBtn = new Button("Opret hold");
	private Button updateTeamBtn = new Button("Opdatere hold");
	
	private Layout layout = new Layout();
	
	
	public LigaMenu(Stage stage)
	{
		layout.left.getChildren().addAll(createLeagueBtn, updateLigaBtn, deleteLigaBtn, backBtn, leagueDropdown);
		layout.bottom.getChildren().addAll(updateTeamBtn, createTeamBtn);
		
		layout.left.setTopAnchor(createLeagueBtn, 0.0);
		layout.left.setTopAnchor(updateLigaBtn, 50.0);
		layout.left.setTopAnchor(deleteLigaBtn, 100.0);
		layout.left.setTopAnchor(leagueDropdown, 150.0);
		layout.left.setBottomAnchor(backBtn, 0.0);
		
		ligaButtonFunctionality(stage);
		leagueDropDown();
		new LeagueTableView(layout);
		
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	private void ligaButtonFunctionality(Stage stage)
	{
		UpdateHold updateHold = new UpdateHold();
		Menu menu = new Menu();
		createLeagueBtn.setOnAction(e -> createLeague());
//		updateLigaBtn.setOnAction(e -> insert thingy here);
		deleteLigaBtn.setOnAction(e -> deleteLeague());
		backBtn.setOnAction(e -> menu.showMenu(stage));
		updateTeamBtn.setOnAction(e -> updateHold.showHoldUpdate());
//		createTeamBtn.setOnAction(e -> holdMenu.showCreateTeamWindow());
	}
	
	// Author: Lasse Jonassen
	// Created: 12-01-2021
	private void leagueDropDown() {
		LeagueImpl leagueImpl = new LeagueImpl();
		leagueDropdown.getItems().addAll(leagueImpl.getAllLeagues());
		
	}
	
	private void createLeague() {
		LeagueImpl leagueImpl = new LeagueImpl();
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Add a new League");
		dialog.setHeaderText("Look, a Text Input Dialog");
		dialog.setContentText("Please enter the name of the league");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
		    leagueImpl.createLeague(result);
	}
	
	private void deleteLeague() {
		LeagueImpl leagueImpl = new LeagueImpl();
		ArrayList<League> leagues = leagueImpl.getAllLeagues();
		ChoiceDialog<League> dialog = new ChoiceDialog<>(leagues.get(0), leagues);
		dialog.setTitle("Choose league");
		dialog.setHeaderText("Look, a Choice Dialog");
		dialog.setContentText("Choose league: ");
		Optional<League> result = dialog.showAndWait();
		if (result.isPresent())
			if (confirmDelete())
				leagueImpl.deleteLeague(result);
	}
	
	private boolean confirmDelete() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm deletion of league");
		alert.setHeaderText("Are you sure you want to delete the choosen league?");
		alert.setContentText("Choose your option.");
		ButtonType confirmBtn = new ButtonType("Yes");
		ButtonType cancelBtn = new ButtonType("No", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(confirmBtn, cancelBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == confirmBtn)
			return true;
		else if (result.get() == cancelBtn)
		    return false;
		return false;
	}

}
