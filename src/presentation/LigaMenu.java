package presentation;

import logic.LeagueImpl;
import logic.TeamImpl;
import data.League;
import data.Team;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class LigaMenu {
	private Button createLeagueBtn = new Button("Opret Liga");
	private Button updateLeagueBtn = new Button("Opdater Liga");
	private Button deleteLigaBtn = new Button("Slet Liga");
	private Button backBtn = new Button("Tilbage");
	private Button createTeamBtn = new Button("Opret hold");
	private Button updateTeamBtn = new Button("Opdatere hold");
	private Button printBtn = new Button("Print");
	
	private Button refreshBtn = new Button("Opdaterer");
	
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
		layout.left.getChildren().addAll(createLeagueBtn, updateLeagueBtn, deleteLigaBtn, backBtn, leagueDropdown, refreshBtn, printBtn);
		layout.bottom.getChildren().addAll(updateTeamBtn, createTeamBtn);
		leagueDropdown.setId("leagueDropDown");
		layout.left.setTopAnchor(createLeagueBtn, 0.0);
		layout.left.setTopAnchor(updateLeagueBtn, 50.0);
		layout.left.setTopAnchor(deleteLigaBtn, 100.0);
		layout.left.setTopAnchor(leagueDropdown, 150.0);
		layout.left.setTopAnchor(refreshBtn, 200.0);
		layout.left.setBottomAnchor(backBtn, 0.0);
		layout.left.setTopAnchor(printBtn, 250.0);
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
		refreshBtn.setOnAction(e -> {
			if (leagueDropdown.getSelectionModel().getSelectedItem() == null)
				validate.noLeagueChoosenAlert();
			else
				loadTableView(leagueDropdown.getSelectionModel().getSelectedItem());
		});
	}

	private void leagueDropDown() {
		leagueDropdown.getItems().clear();
		leagueDropdown.getItems().addAll(leagueImpl.getAllLeagues());
		leagueDropdown.setPromptText("Vaelg liga");
	}

	private void createLeague() {
		ChildLayout childLayout = new ChildLayout();
		Label header = new Label("Opret ny liga");
		Label guideLabel = new Label("Skriv venligst navnet paa den nye liga: ");
		TextField leagueNameField = new TextField();
		leagueNameField.setPromptText("Liga navn");
		Button addBtn = new Button("OK");
		Button cancelBtn = new Button("Annuller");
		String str = "liga";
		
		childLayout.childTop.getChildren().add(header);
		childLayout.childCenter.add(guideLabel, 0, 0);
		childLayout.childCenter.add(leagueNameField, 1, 0);
		childLayout.childBottom.getChildren().addAll(addBtn, cancelBtn);
		
		Scene scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
		addBtn.setOnAction(e -> {
			if (validate.emptyStringWarning(leagueNameField.getText()))
				if (validate.confirmChanges(str)) {
					leagueImpl.createLeague(new League(leagueNameField.getText()));
					stage.close();
					leagueDropDown();
				}
		});
		cancelBtn.setOnAction(e -> stage.close());
	}

	private void deleteLeague() {
		ChildLayout childLayout = new ChildLayout();
		Button addBtn = new Button("OK");
		Button cancelBtn = new Button("Annuller");
		Label header = new Label("Slet en liga");
		Label guideLabel = new Label("VÃ¦lg venligst den liga du vil slette");
		ComboBox<League> leagues = new ComboBox<>();
		String str = "liga";
		
		childLayout.childTop.getChildren().add(header);
		childLayout.childCenter.add(guideLabel, 0, 0);
		childLayout.childCenter.add(leagues, 1, 0);
		childLayout.childBottom.getChildren().addAll(addBtn,cancelBtn);
		
		leagues.getItems().addAll(leagueImpl.getAllLeagues());
		
		Scene scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
		addBtn.setOnAction(e -> {
			if(!(leagues.getSelectionModel().getSelectedItem() == null))
			{
				if (validate.confirmChanges(str)) {
					leagueImpl.deleteLeague(leagues.getSelectionModel().getSelectedItem());
					stage.close();
					leagueDropDown();
				}
			}
			
		});
		cancelBtn.setOnAction(e -> stage.close());
	}

	private void updateLeague() {
		ChildLayout childLayout = new ChildLayout();
		Label header = new Label("Opdater en liga");
		Label guideLabel = new Label("VÃ¦lg den liga du vil opdaterer");
		Label guideLabel2 = new Label("Skriv ligaens nye navn");
		ComboBox<League> leagues = new ComboBox<>();
		TextField leagueNameField = new TextField();
		Button cancelBtn = new Button("Annuller");
		Button confirmBtn = new Button("OK");
		String str = "liga";
		
		childLayout.childTop.getChildren().add(header);
		childLayout.childCenter.add(guideLabel, 0, 0);
		childLayout.childCenter.add(leagues, 1, 0);
		childLayout.childCenter.add(guideLabel2, 0, 1);
		childLayout.childCenter.add(leagueNameField, 1, 1);
		childLayout.childBottom.getChildren().addAll(confirmBtn,cancelBtn);
		
		leagueNameField.setPromptText("Liga navn");
		leagues.getItems().addAll(leagueImpl.getAllLeagues());		
		
		Scene scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
		leagues.setOnAction(e -> leagueNameField.setText(""));
		confirmBtn.setOnAction(e -> {
			League league = leagues.getSelectionModel().getSelectedItem();
			if (league != null) {
				if (validate.emptyStringWarning(leagueNameField.getText()))
					if (validate.confirmChanges(str)) {
						leagueImpl.updateLeague(leagues.getSelectionModel().getSelectedItem(), leagueNameField.getText());
						stage.close();
						leagueDropDown();
					}
			}
			else
				validate.noLeagueChoosenAlert();
		});
		cancelBtn.setOnAction(e -> stage.close());
	}

	@SuppressWarnings("unchecked")
	// Generic Array: Setting the sorting order of the tableview.
	private void loadTableView(League league) {
		leagueTable = new TableView<Team>();
		ArrayList<TableColumn<Team, String>> columns = 
				new ArrayList<TableColumn<Team, String>>();
		leagueTable.setPrefHeight(640);
		
		TableColumn<Team, String> idColumn = new TableColumn<Team, String>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		columns.add(idColumn);
		idColumn.setSortable(false);

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
		pointsColumn.setSortType(TableColumn.SortType.DESCENDING);
		columns.add(pointsColumn);

		for (TableColumn<Team, String> tc : columns)
			leagueTable.getColumns().add(tc);
		
		layout.root.setCenter(leagueTable);
		
		ObservableList<Team> data = 
				FXCollections.observableArrayList(teamImpl.getAllTeams(league.getId()));
		leagueTable.setItems(data);
		pointsColumn.setSortType(TableColumn.SortType.DESCENDING);
		leagueTable.getSortOrder().setAll(pointsColumn);
		
		printBtn.setOnAction(e -> exportLeague(data));
	}
	
	private void exportLeague(ObservableList<Team> data) {
		Stage window = new Stage();
		DirectoryChooser dirChooser = new DirectoryChooser();
		File selectedDir = dirChooser.showDialog(window);
		 
		try {
			LocalDate date = LocalDate.now();
			FileWriter writer = new FileWriter(selectedDir + "/ligastilling " + date + ".csv");
			writer.append("id");
			writer.append(", ");
			writer.append("name");
			writer.append(", ");
			writer.append("matchesTotal");
			writer.append(", ");
			writer.append("matchesWon");
			writer.append(", ");
			writer.append("matchesLost");
			writer.append(", ");
			writer.append("matchesDraw");
			writer.append(", ");
			writer.append("goals");
			writer.append(", ");
			writer.append("goalsIn");
			writer.append(", ");
			writer.append("points");
			writer.append(", ");
			writer.append("leagueId");
			writer.append('\n');
			StringBuilder sb = new StringBuilder();
			for (Team team : data) {
				sb.append(team.exportString());
				sb.append('\n');
			}
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
