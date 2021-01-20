package presentation;

import java.util.ArrayList;

import data.League;
import data.Match;
import data.Team;
import logic.LeagueImpl;
import logic.MatchImpl;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MatchMenu {
	private LeagueImpl leagueImpl = new LeagueImpl();
	private MatchImpl matchImpl = new MatchImpl();	
	private Button createMatchBtn = new Button("Opret Kamp");
	private Button deleteMatchBtn = new Button("Slet Kamp");
	private Button updateMatchBtn = new Button("Opdater Kamp");
	private Button backBtn = new Button("Tilbage");
	private Layout layout = new Layout();
	private TableView<Match> matchTable = new TableView<>();
	private ComboBox<League> leagueDropdown = new ComboBox<League>();
	
	
	private TableView<Match> tableViewMatches = new TableView<Match>();
	
	public MatchMenu(Stage stage) {
		matchBtnFunctionality(stage);
		showMatchMenu(stage);
		leagueDropDown();
		updateTableView();
		openMatchDetails();
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
	}
	
	private void updateMatch() {
		ChildLayout layout = new ChildLayout();
		Label header = new Label("Opdater Kamp");
		layout.childTop.getChildren().add(header); 
	}
	
	private void loadTableView(League league) {
		tableViewMatches = new TableView<Match>();
		ArrayList<TableColumn<Match, String>> columns = 
				new ArrayList<TableColumn<Match, String>>();
		
		TableColumn<Match, String> matchIDColumn = new TableColumn<Match, String>("ID");
		matchIDColumn.setCellValueFactory(new PropertyValueFactory<>("matchID"));
		columns.add(matchIDColumn);
		
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
		
		for (TableColumn<Match, String> tc : columns)
			tableViewMatches.getColumns().add(tc);
		
		ArrayList<Match> matchList = new ArrayList<Match>();
		matchList.addAll(matchImpl.getMatchesByLeagueID(league));
		tableViewMatches.getItems().addAll(matchList);
		layout.root.setCenter(tableViewMatches);
	}
	
	private void openMatchDetails() {
		matchTable.setOnMouseClicked(event -> {
		    if (matchTable.getSelectionModel().getSelectedItem() != null) {
		        new MatchDetails(matchTable.getSelectionModel().getSelectedItem());
		    }
		});
	}
}
