package presentation;

import java.util.ArrayList;

import data.League;
import data.Match;
import logic.LeagueImpl;
import logic.MatchImpl;

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
		leagueDropdown.getItems().addAll(leagueImpl.getAllLeagues());
		leagueDropdown.setPromptText("VÃ¯Â¿Â½lg liga");
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
		matchTable = new TableView<Match>();
		matchTable.getItems().clear();
		ArrayList<TableColumn<Match, String>> columns = new ArrayList<TableColumn<Match, String>>();
		matchTable.setPrefHeight(640);

		TableColumn<Match, String> matchID = new TableColumn<>("ID");
		matchID.setPrefWidth(150);
		matchID.setCellValueFactory(new PropertyValueFactory<>("id"));
		columns.add(matchID);

		TableColumn<Match, String> team1ID = new TableColumn<>("HOLD 1");
		team1ID.setPrefWidth(50);
		team1ID.setCellValueFactory(new PropertyValueFactory<>("team1Id"));
		columns.add(team1ID);
		
		TableColumn<Match, String> team1Goals = new TableColumn<>("MÅL");
		team1Goals.setPrefWidth(50);
		team1Goals.setCellValueFactory(new PropertyValueFactory<>("team1Goals"));
		columns.add(team1Goals);

		TableColumn<Match, String> dividerColumn = new TableColumn<>("-");
		dividerColumn.setPrefWidth(50);
		dividerColumn.setCellValueFactory(new PropertyValueFactory<>("divider"));
		columns.add(dividerColumn);
		
		TableColumn<Match, String> team2Goals = new TableColumn<>("MÅL");
		team2Goals.setPrefWidth(50);
		team2Goals.setCellValueFactory(new PropertyValueFactory<>("team2Goals"));
		columns.add(team2Goals);
		
		TableColumn<Match, String> team2ID = new TableColumn<>("HOLD 2");
		team2ID.setPrefWidth(50);
		team2ID.setCellValueFactory(new PropertyValueFactory<>("team2Id"));
		columns.add(team2ID);
		
		for (TableColumn<Match, String> tc : columns)
			matchTable.getColumns().add(tc);
		matchTable.getItems().addAll(matchImpl.getAllMatches(league));
		
		
	}
	
	private void openMatchDetails() {
		matchTable.setOnMouseClicked(event -> {
		    if (matchTable.getSelectionModel().getSelectedItem() != null) {
		        new MatchDetails(matchTable.getSelectionModel().getSelectedItem());
		    }
		});
	}
}
