package presentation;

import java.util.ArrayList;

import data.League;
import data.Match;
import logic.LeagueImpl;
import logic.MatchImpl;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MatchMenu {
	private LeagueImpl leagueImpl = new LeagueImpl();
	private MatchImpl matchImpl = new MatchImpl();	
	private Button createMatchBtn = new Button("Opret Kamp");
	private Button deleteMatchBtn = new Button("Slet Kamp");
	private Button refreshBtn = new Button("Opdater");
	private Button importMatchBtn = new Button("Importer");
	private Button backBtn = new Button("Tilbage");
	private Layout layout = new Layout();
	private ComboBox<League> leagueDropdown = new ComboBox<League>();
	private TableView<Match> tableViewMatches = new TableView<Match>();
	private Validation validate = new Validation();
	
	public MatchMenu(Stage stage) {
		matchBtnFunctionality(stage);
		showMatchMenu(stage);
		leagueDropDown();
		updateTableView();
	}
	
	@SuppressWarnings("static-access")
	private void showMatchMenu(Stage stage)
	{
		layout.left.getChildren().addAll(createMatchBtn, deleteMatchBtn,backBtn, leagueDropdown, refreshBtn, importMatchBtn);
		layout.left.setBottomAnchor(backBtn, 0.0);
		layout.left.setTopAnchor(createMatchBtn, 0.0);
		layout.left.setTopAnchor(deleteMatchBtn, 50.0);
		layout.left.setTopAnchor(leagueDropdown, 100.0);
		layout.left.setTopAnchor(refreshBtn, 150.0);
		layout.left.setTopAnchor(importMatchBtn, 200.0);
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
		leagueDropdown.getItems().clear();
		leagueDropdown.getItems().addAll(leagueImpl.getAllLeagues());
		leagueDropdown.setPromptText("Vaelg liga");
	}
	
	private void matchBtnFunctionality(Stage stage) {
		backBtn.setOnAction(e -> new Menu(stage));
		createMatchBtn.setOnAction(e-> new CreateMatch());
		deleteMatchBtn.setOnAction(e -> new DeleteMatch());
		refreshBtn.setOnAction(e -> {
			if (leagueDropdown.getSelectionModel().getSelectedItem() == null)
				validate.noLeagueChoosenAlert();
			else
				loadTableView(leagueDropdown.getSelectionModel().getSelectedItem());
		});
		importMatchBtn.setOnAction(e -> new ImportMatch());
	}
	
	@SuppressWarnings("unchecked")
	private void loadTableView(League league) {
		tableViewMatches = new TableView<Match>();
		ArrayList<TableColumn<Match, String>> columns = 
				new ArrayList<TableColumn<Match, String>>();
		
		TableColumn<Match, String> idColumn = new TableColumn<Match, String>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("matchID"));
		columns.add(idColumn);
		
		TableColumn<Match, String> team1NameColumn = new TableColumn<Match, String>("HOLDNAVN");
		team1NameColumn.setCellValueFactory(new PropertyValueFactory<>("team1Name"));
		columns.add(team1NameColumn);
		
		TableColumn<Match, String> team1GoalsColumn = new TableColumn<Match, String>("MÅL");
		team1GoalsColumn.setCellValueFactory(new PropertyValueFactory<>("team1Goals"));
		columns.add(team1GoalsColumn);
		
		TableColumn<Match, String> dividerColumn = new TableColumn<Match, String>("-");
		dividerColumn.setCellValueFactory(new PropertyValueFactory<>("divider"));
		columns.add(dividerColumn);
		
		TableColumn<Match, String> team2GoalsColumn = new TableColumn<Match, String>("MÅL");
		team2GoalsColumn.setCellValueFactory(new PropertyValueFactory<>("team2Goals"));
		columns.add(team2GoalsColumn);
		
		TableColumn<Match, String> team2NameColumn = new TableColumn<Match, String>("HOLDNAVN");
		team2NameColumn.setCellValueFactory(new PropertyValueFactory<>("team2Name"));
		columns.add(team2NameColumn);
		
		TableColumn<Match, String> playedColumn = new TableColumn<Match, String>("PLAYED");
		playedColumn.setCellValueFactory(new PropertyValueFactory<>("played"));
		columns.add(playedColumn);
		
		for (TableColumn<Match, String> tc : columns)
			tableViewMatches.getColumns().add(tc);
		
		ArrayList<Match> matchList = new ArrayList<Match>();
		matchList.addAll(matchImpl.getMatchesByLeagueID(league));
		tableViewMatches.getItems().addAll(matchList);
		layout.root.setCenter(tableViewMatches);
		
		idColumn.setSortType(TableColumn.SortType.DESCENDING);
		tableViewMatches.getSortOrder().setAll(idColumn);
		
		tableViewMatches.setOnMouseClicked(e -> {
			if(tableViewMatches.getSelectionModel().getSelectedItem() != null) {
				new MatchDetails(tableViewMatches.getSelectionModel().getSelectedItem());
			}
		});
	}
}
