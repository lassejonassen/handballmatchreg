package presentation;

import logic.LeagueImpl;
import logic.TeamImpl;
import data.League;
import data.Team;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LigaMenu {
	private Button createLeagueBtn = new Button("Opret Liga");
	private Button updateLeagueBtn = new Button("Opdater Liga");
	private Button deleteLigaBtn = new Button("Slet Liga");
	private Button backBtn = new Button("Tilbage");
	private Button createTeamBtn = new Button("Opret hold");
	private Button updateTeamBtn = new Button("Opdatere hold");
	private Layout layout = new Layout();
	private Validation validate = new Validation();
	private TableView<Team> leagueTable = new TableView<Team>();
	private ComboBox<League> leagueDropdown = new ComboBox<League>();
	private LeagueImpl leagueImpl = new LeagueImpl();
	private TeamImpl teamImpl = new TeamImpl();
	
	public LigaMenu(Stage stage) {
		ligaButtonFunctionality(stage);
		leagueDropDown();
		showLeagueMenu(stage);
		updateTableView();
	}

	private void updateTableView() {
		leagueDropdown.setOnAction(e -> {
			loadTableView(leagueDropdown.getSelectionModel().getSelectedItem());
		});
	}

	@SuppressWarnings("static-access")
	private void showLeagueMenu(Stage stage) {
		layout.left.getChildren().addAll(createLeagueBtn, updateLeagueBtn, deleteLigaBtn, backBtn, leagueDropdown);
		layout.bottom.getChildren().addAll(updateTeamBtn, createTeamBtn);
		leagueDropdown.setId("leagueDropDown");
		layout.left.setTopAnchor(createLeagueBtn, 0.0);
		layout.left.setTopAnchor(updateLeagueBtn, 50.0);
		layout.left.setTopAnchor(deleteLigaBtn, 100.0);
		layout.left.setTopAnchor(leagueDropdown, 150.0);
		layout.left.setBottomAnchor(backBtn, 0.0);
		layout.root.setCenter(leagueTable);
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	private void ligaButtonFunctionality(Stage stage) {
		createLeagueBtn.setOnAction(e -> createLeague());
		updateLeagueBtn.setOnAction(e -> updateLeague());
		deleteLigaBtn.setOnAction(e -> deleteLeague());
		backBtn.setOnAction(e -> new Menu(stage));
		updateTeamBtn.setOnAction(e -> new UpdateTeam());
		createTeamBtn.setOnAction(e -> new CreateTeam());
	}

	private void leagueDropDown() {
		leagueDropdown.getItems().clear();
		leagueDropdown.getItems().addAll(leagueImpl.getAllLeagues());
		leagueDropdown.setPromptText("Vaelg liga");
	}

	private void createLeague() {
		ChildLayout layout = new ChildLayout();
		Label header = new Label("Opret ny liga");
		Label guideLabel = new Label("Skriv venligst navnet paa den nye liga: ");
		TextField leagueNameField = new TextField();
		leagueNameField.setPromptText("Liga navn");
		Button addBtn = new Button("OK");
		Button cancelBtn = new Button("Annuller");
		Scene scene = new Scene(layout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		layout.childTop.getChildren().add(header);
		layout.childCenter.add(guideLabel, 0, 0);
		layout.childCenter.add(leagueNameField, 1, 0);
		layout.childCenter.add(addBtn, 0, 1);
		layout.childCenter.add(cancelBtn, 1, 1);
		addBtn.setOnAction(e -> {
			if (validate.emptyStringWarning(leagueNameField.getText()))
				if (validate.confirmChanges()) {
					leagueImpl.createLeague(new League(leagueNameField.getText()));
					stage.close();
					leagueDropDown();
				}
		});
		cancelBtn.setOnAction(e -> stage.close());
	}

	private void deleteLeague() {
		ChildLayout layout = new ChildLayout();
		Label header = new Label("Slet en liga");
		layout.childTop.getChildren().add(header);
		Label guideLabel = new Label("VÃ¦lg venligst den liga du vil slette");
		layout.childCenter.add(guideLabel, 0, 0);
		ComboBox<League> leagues = new ComboBox<>();
		leagues.getItems().addAll(leagueImpl.getAllLeagues());
		layout.childCenter.add(leagues, 1, 0);
		Button addBtn = new Button("OK");
		Button cancelBtn = new Button("Annuller");
		layout.childCenter.add(addBtn, 0, 1);
		layout.childCenter.add(cancelBtn, 1, 1);
		Scene scene = new Scene(layout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		addBtn.setOnAction(e -> {
			if (validate.confirmChanges()) {
				leagueImpl.deleteLeague(leagues.getSelectionModel().getSelectedItem());
				stage.close();
				leagueDropDown();
			}
		});
		cancelBtn.setOnAction(e -> stage.close());
	}

	private void updateLeague() {
		ChildLayout layout = new ChildLayout();
		Label header = new Label("Opdater en liga");
		layout.childTop.getChildren().add(header);
		Label guideLabel = new Label("VÃ¦lg den liga du vil opdaterer");
		layout.childCenter.add(guideLabel, 0, 0);
		ComboBox<League> leagues = new ComboBox<>();
		leagues.getItems().addAll(leagueImpl.getAllLeagues());
		layout.childCenter.add(leagues, 1, 0);
		Label guideLabel2 = new Label("Skriv ligaens nye navn");
		layout.childCenter.add(guideLabel2, 0, 1);
		TextField leagueNameField = new TextField();
		leagueNameField.setPromptText("Liga navn");
		layout.childCenter.add(leagueNameField, 1, 1);
		Button addBtn = new Button("OK");
		Button cancelBtn = new Button("Annuller");
		layout.childCenter.add(addBtn, 0, 2);
		layout.childCenter.add(cancelBtn, 1, 2);
		Scene scene = new Scene(layout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		leagues.setOnAction(e -> leagueNameField.setText(""));
		addBtn.setOnAction(e -> {
			if (validate.confirmChanges()) {
				leagueImpl.updateLeague(leagues.getSelectionModel().getSelectedItem(), leagueNameField.getText());
				stage.close();
				leagueDropDown();
			}
		});
		cancelBtn.setOnAction(e -> stage.close());
	}

	private void loadTableView(League league) {
		leagueTable = new TableView<Team>();
		ArrayList<TableColumn<Team, String>> columns = 
				new ArrayList<TableColumn<Team, String>>();
		leagueTable.setPrefHeight(640);

		TableColumn<Team, String> nameColumn = new TableColumn<>("NAME");
		nameColumn.setPrefWidth(150);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		columns.add(nameColumn);

		TableColumn<Team, String> matchesTotalColumn = new TableColumn<>("TOTAL");
		matchesTotalColumn.setPrefWidth(50);
		matchesTotalColumn.setCellValueFactory(new PropertyValueFactory<>("matchesTotal"));
		columns.add(matchesTotalColumn);

		TableColumn<Team, String> matchesWonColumn = new TableColumn<>("WON");
		matchesWonColumn.setPrefWidth(50);
		matchesWonColumn.setCellValueFactory(new PropertyValueFactory<>("matchesWon"));
		columns.add(matchesWonColumn);

		TableColumn<Team, String> matchesLostColumn = new TableColumn<>("LOST");
		matchesLostColumn.setPrefWidth(50);
		matchesLostColumn.setCellValueFactory(new PropertyValueFactory<>("matchesLost"));
		columns.add(matchesLostColumn);

		TableColumn<Team, String> matchesDrawColumn = new TableColumn<>("DRAW");
		matchesDrawColumn.setPrefWidth(50);
		matchesDrawColumn.setCellValueFactory(new PropertyValueFactory<>("matchesDraw"));
		columns.add(matchesDrawColumn);

		TableColumn<Team, String> goalsColumn = new TableColumn<>("GOALS");
		goalsColumn.setPrefWidth(50);
		goalsColumn.setCellValueFactory(new PropertyValueFactory<>("goals"));
		columns.add(goalsColumn);

		TableColumn<Team, String> goalsInColumn = new TableColumn<>("GOALS IN");
		goalsInColumn.setPrefWidth(150);
		goalsInColumn.setCellValueFactory(new PropertyValueFactory<>("goalsIn"));
		columns.add(goalsInColumn);
		TableColumn<Team, String> pointsColumn = new TableColumn<>("POINTS");
		pointsColumn.setPrefWidth(150);
		pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
		columns.add(pointsColumn);

		for (TableColumn<Team, String> tc : columns)
			leagueTable.getColumns().add(tc);
		
		layout.root.setCenter(leagueTable);
		leagueTable.getItems().addAll(teamImpl.getAllTeams(league.getId()));
		
		
//		ArrayList<Team> teamList = new ArrayList<>();
//		if (teamList.size() <= 0) {
//			leagueTable.getItems().addAll(teamImpl.getAllTeams(league.getId()));
//				teamList.addAll(teamImpl.getAllTeams(league.getId()));
//			} else if (teamList.size() > 0) {
//				leagueTable.getItems().addAll(teamList);
//			}
	}

}
