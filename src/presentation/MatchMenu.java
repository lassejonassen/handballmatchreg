package presentation;

import java.util.ArrayList;

import data.League;
import data.Match;
import data.Team;
import logic.LeagueImpl;
import logic.TeamImpl;
import logic.iMatchImpl;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MatchMenu {
	
	/*
	 * Author: Lucas Elley
	 * Date: 14/01/2021
	 */
	
	private Button createMatchBtn = new Button("Opret Kamp");
	private Button deleteMatchBtn = new Button("Slet Kamp");
	private Button updateMatchBtn = new Button("Opdater Kamp");
	private Button backBtn = new Button("Tilbage");
	private Layout layout = new Layout();
	
	private TableView<Match> matchTable = new TableView<>();
	private ComboBox<League> leagueDropdown = new ComboBox<League>();
	
	public MatchMenu(Stage stage) {
		updateTableView();
		leagueDropDown();
		matchBtnFunctionality(stage);
		showMatchMenu(stage);
		
	}
	@SuppressWarnings("static-access")
	private void showMatchMenu(Stage stage)
	{
		layout.left.getChildren().addAll(createMatchBtn, deleteMatchBtn, updateMatchBtn,backBtn, leagueDropdown);
		layout.left.setBottomAnchor(backBtn, 0.0);
		layout.left.setTopAnchor(createMatchBtn, 0.0);
		layout.left.setTopAnchor(updateMatchBtn, 50.0);
		layout.left.setTopAnchor(deleteMatchBtn, 100.0);
		layout.left.setTopAnchor(leagueDropdown, 150.0);
		layout.root.setCenter(matchTable);
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	private void updateTableView() {
		leagueDropdown.setOnAction(e -> {
			loadTableView(leagueDropdown.getSelectionModel().getSelectedItem());
		});
	}
	
	private void leagueDropDown() {
		LeagueImpl leagueImpl = new LeagueImpl();
		leagueDropdown.getItems().addAll(leagueImpl.getAllLeagues());
		leagueDropdown.setPromptText("Vï¿½lg liga");

	}
	
	private void matchBtnFunctionality(Stage stage) {
		Menu menu = new Menu();
		backBtn.setOnAction(e -> menu.showMenu(stage));
		createMatchBtn.setOnAction(e-> new CreateMatch());
		deleteMatchBtn.setOnAction(e -> new DeleteMatch());
	}
	
	private void deleteMatch() {
		iMatchImpl matchImpl = new iMatchImpl();
		ChildLayout layout = new ChildLayout();
		Label header = new Label("Slet en kamp");
		layout.childTop.getChildren().add(header);
		Label guideLabel = new Label("Vælg kamp du vil slette: ");
		layout.childCenter.add(guideLabel, 0, 0);
		
		
	}
	
	private void updateMatch() {
		iMatchImpl matchImpl = new iMatchImpl();
		ChildLayout layout = new ChildLayout();
		Label header = new Label("Opdater Kamp");
		layout.childTop.getChildren().add(header); 
		
	}
	
	private void loadTableView(League league) {
		iMatchImpl matchImpl = new iMatchImpl();
		ArrayList<TableColumn<Match, String>> columns = new ArrayList<TableColumn<Match, String>>();
		matchTable.setPrefHeight(640);

		TableColumn<Match, String> nameColumn = new TableColumn<>("HOLD NAVN");
		nameColumn.setPrefWidth(150);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("team_name"));
		columns.add(nameColumn);

		TableColumn<Match, String> matchesTotalColumn = new TableColumn<>("MÅL");
		matchesTotalColumn.setPrefWidth(50);
		matchesTotalColumn.setCellValueFactory(new PropertyValueFactory<>("team_goals"));
		columns.add(matchesTotalColumn);

		TableColumn<Match, String> matchesWonColumn = new TableColumn<>("-");
		matchesWonColumn.setPrefWidth(50);
		matchesWonColumn.setCellValueFactory(new PropertyValueFactory<>(""));
		columns.add(matchesWonColumn);

		TableColumn<Match, String> matchesLostColumn = new TableColumn<>("MÅL");
		matchesLostColumn.setPrefWidth(50);
		matchesLostColumn.setCellValueFactory(new PropertyValueFactory<>("team_goals"));
		columns.add(matchesLostColumn);

		TableColumn<Match, String> matchesDrawColumn = new TableColumn<>("HOLD NAVN");
		matchesDrawColumn.setPrefWidth(50);
		matchesDrawColumn.setCellValueFactory(new PropertyValueFactory<>("team1_name"));
		columns.add(matchesDrawColumn);

		for (TableColumn<Match, String> tc : columns)
			matchTable.getColumns().add(tc);
		matchTable.getItems().addAll(matchImpl.getAllMatches(league.getId()));
	}
}
