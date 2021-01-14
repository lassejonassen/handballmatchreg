package presentation;

import java.util.ArrayList;
import data.Team;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.TeamImpl;

public class LeagueTableView {
	
	public LeagueTableView(Layout layout) {
		TeamImpl teamImpl = new TeamImpl();
		ArrayList<TableColumn<Team, String>> columns = new ArrayList<TableColumn<Team, String>>();
		
		TableView<Team> leagueTable = new TableView<>();
		leagueTable.setPrefHeight(640);
		
		TableColumn<Team, String> idColumn = new TableColumn<>("ID");
		idColumn.setPrefWidth(50);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		columns.add(idColumn);
		
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
        
        TableColumn<Team, String> leagueIdColumn = new TableColumn<>("LEAGUE ID");
        leagueIdColumn.setPrefWidth(150);
        leagueIdColumn.setCellValueFactory(new PropertyValueFactory<>("leagueId"));
        columns.add(leagueIdColumn);
        
        for (TableColumn<Team, String> tc : columns)
        	leagueTable.getColumns().add(tc);
        
        layout.root.setCenter(leagueTable);
        leagueTable.getItems().addAll(teamImpl.getAllTeams(1));
	}

}
