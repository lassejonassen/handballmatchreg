package presentation;

import java.util.ArrayList;

import data.Goal;
import data.Match;
import data.Suspension;
import data.Team;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GoalImpl;
import logic.MatchImpl;
import logic.SuspensionImpl;

public class MatchDetails 
{
	private Button homeTeamScored = new Button("Hjemme hold +1");
	private Button awayTeamScored = new Button("Ude hold +1");
	private Button homeTeamSuspension = new Button("Hjemme udvisning");
	private Button awayTeamSuspension = new Button("Ude udvisning");
	private Button matchReportBtn = new Button("Kamp rapport");
	private Button deleteSuspensionAwayBtn = new Button("Fjern Udvisning - Ude");
	private Button deleteSuspensionHomeBtn = new Button("Fjern Udvisning - Hjemme");
	private Button showSuspensionsHomeBtn = new Button("Vis udvisninger - Hjemme");
	private Button showSuspensionsAwayBtn = new Button("Vis udvisninger - Ude");
	
	private int STARTTIME = 0;
	private Timeline timeline;
	private Label timerLabel = new Label();
	private int timeSeconds = STARTTIME;
	private int gameLength = 5;
	
	private Label homeTeamName = new Label();
	private Label awayTeamName = new Label();
	private Label homeScore = new Label();
	private Label awayScore = new Label();
	
	private int i = 0;
	private int j = 0;
	
	private int team1Goals;
	private int team2Goals;
	
	private Scene scene;
	private Stage window = new Stage();
	
	private ChildLayout childLayout = new ChildLayout();
	private MatchImpl matchImpl = new MatchImpl();
	private SuspensionImpl suspensionImpl = new SuspensionImpl();
	private GoalImpl goalImpl = new GoalImpl();
	
	private int teamID;
	private int suspensionID;
	private String time;
	
	private ArrayList<Goal> goalList = new ArrayList<Goal>();
	private ArrayList<Suspension> suspensionList = new ArrayList<Suspension>();
	
	public MatchDetails(Match match)
	{
		showMatchDetails(match);
		detailBtnFunctionality(match);
		timerLabelUpdate(match);
		matchTeamDetails(match);
	}
	
	public void showMatchDetails(Match match)
	{
		childLayout.childCenter.add(timerLabel, 2, 0);
		childLayout.childCenter.add(homeTeamName, 0, 1);
		childLayout.childCenter.add(homeScore, 1, 1);
		childLayout.childCenter.add(awayScore, 3, 1);
		childLayout.childCenter.add(awayTeamName, 4, 1);
		GridPane.setHalignment(homeTeamName, HPos.CENTER);
		GridPane.setHalignment(homeScore, HPos.CENTER);
		GridPane.setHalignment(awayScore, HPos.CENTER);
		GridPane.setHalignment(awayTeamName, HPos.CENTER);
		homeTeamName.setStyle("-fx-font-size: 2em;");
		homeScore.setStyle("-fx-font-size: 2em;");
		awayScore.setStyle("-fx-font-size: 2em;");
		awayTeamName.setStyle("-fx-font-size: 2em;");
		
		childLayout.childCenter.add(homeTeamScored, 0, 2);
		childLayout.childCenter.add(homeTeamSuspension, 1, 2);
		childLayout.childCenter.add(awayTeamSuspension, 3, 2);
		childLayout.childCenter.add(awayTeamScored, 4, 2);
		childLayout.childCenter.add(matchReportBtn, 2, 3);
		childLayout.childCenter.add(deleteSuspensionAwayBtn, 3, 3);
		childLayout.childCenter.add(deleteSuspensionHomeBtn, 1, 3);
		childLayout.childCenter.add(showSuspensionsHomeBtn, 0, 4);
		childLayout.childCenter.add(deleteSuspensionAwayBtn, 3, 4);

		timerLabel.setText("" + timeSeconds);
		timerLabel.setStyle("-fx-font-size: 4em;");
		GridPane.setHalignment(timerLabel, HPos.CENTER);
		
		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Kamp detaljer");
		window.show();
	}
	
	private void matchTeamDetails(Match match)
	{
		homeTeamName.setText("" + match.getTeam1Name());
		awayTeamName.setText("" + match.getTeam2Name());
		homeScore.setText(""+ match.getTeam1Goals());
		awayScore.setText("" + match.getTeam2Goals());	
	}
	
	private void homeTeamScoreUpdate(Match match)
	{ 
		if(timeSeconds < gameLength)
		{
			goalImpl.create(match, match.getTeam1Id(), timeSeconds);
			i++;
			match.setTeam1Goals(i);
			homeScore.setText("" + match.getTeam1Goals());
			System.out.println(match.getTeam1Name() + " Mål: " + match.getTeam1Goals() + "Tid: " + timeSeconds);
		}else
		{
			System.out.println("Kamp over STAPH");
		}
	}
	private void awayTeamScoreUpdate(Match match)
	{ 
		if(timeSeconds < gameLength)
		{
			goalImpl.create(match, match.getTeam2Id(), timeSeconds);
			j++;
			match.setTeam2Goals(j);
			awayScore.setText("" + match.getTeam2Goals());
		}else
		{
			System.out.println("Kamp over STAPH");
		}
	}
	
	private void matchDataUpdate(Match match)
	{
		team1Goals = match.getTeam1Goals();
		team2Goals = match.getTeam2Goals();
		System.out.println("Hold 1s mål: " + team1Goals);
		System.out.println("Hold 2s mål: " + team2Goals);
		matchImpl.updateMatch(match);
	}
	
	@SuppressWarnings("unchecked")
	private void timerLabelUpdate(Match match)
	{
		timeSeconds = STARTTIME;
		timerLabel.setText("" + timeSeconds);
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() 
		{
			@Override
			public void handle(Event arg0) {
				timeSeconds++;
				timerLabel.setText("" + timeSeconds);
				if(timeSeconds == gameLength)
				{
					timeline.stop();
					matchDataUpdate(match);
				}
			}
		}));
		timeline.playFromStart();
	}
	
	private void createSuspensionHome(Match match, int teamId, int time) {
		teamId = match.getTeam1Id();
		suspensionImpl.create(match, teamId, time);
	}
	
	private void createSuspensionAway(Match match, int teamId, int time) {
		teamId = match.getTeam2Id();
		suspensionImpl.create(match, teamId, time);
	}
	
	private void deleteSuspensionHome(Match match, int teamId, int suspensionID) {
		teamId = match.getTeam1Id();
		suspensionImpl.delete(match, teamId, suspensionID);
	}
	
	private void deleteSuspensionAway(Match match, int teamId, int suspensionID) {
		teamId = match.getTeam2Id();
		suspensionImpl.delete(match, teamId, suspensionID);
	}
	
	private void showSuspensionsHome(Match match, int teamID, int suspensionID, String time) { 
		teamID = match.getTeam1Id();
		time = timeSeconds.toString();
	}

	private void showSuspensionsAway(Match match, int teamId, int suspensionID, String time) { 
		teamId = match.getTeam2Id();
		time = timeSeconds.toString();
		
		Suspension suspension = new Suspension();
		
	}
	
	private void detailBtnFunctionality(Match match)
	{
		homeTeamScored.setOnAction(e -> homeTeamScoreUpdate(match));
		awayTeamScored.setOnAction(e -> awayTeamScoreUpdate(match));
		matchReportBtn.setOnAction(e -> new MatchReport(match));
		homeTeamSuspension.setOnAction(e -> createSuspensionHome(match, teamID, time));
		awayTeamSuspension.setOnAction(e -> createSuspensionAway(match, teamID, time));
		deleteSuspensionHomeBtn.setOnAction(e -> deleteSuspensionHome(match, teamID, suspensionID));
		deleteSuspensionAwayBtn.setOnAction(e -> deleteSuspensionAway(match, teamID, suspensionID));
		showSuspensionsHomeBtn.setOnAction(e -> showSuspensionsHome(match, teamID, suspensionID, time));
		showSuspensionsAwayBtn.setOnAction(e -> showSuspensionsAway(match, teamID, suspensionID, time));
	}

}
