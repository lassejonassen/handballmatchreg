package presentation;

import data.Match;
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
import logic.MatchImpl;

public class MatchDetails 
{
	private Button homeTeamScored = new Button("Hjemme hold +1");
	private Button awayTeamScored = new Button("Ude hold +1");
	private Button homeTeamSuspension = new Button("Hjemme udvisning");
	private Button awayTeamSuspension = new Button("Ude udvisning");
	private Button matchReportBtn = new Button("Kamp rapport");
	
	private Integer STARTTIME = 0;
	private Timeline timeline;
	private Label timerLabel = new Label();
	private Integer timeSeconds = STARTTIME;
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
		
		timerLabel.setText(timeSeconds.toString());
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
//		matchImpl.updateMatch(match, team1Goals, team2Goals);
	}
	
	@SuppressWarnings("unchecked")
	private void timerLabelUpdate(Match match)
	{
		timeSeconds = STARTTIME;
		timerLabel.setText(timeSeconds.toString());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() 
		{
			@Override
			public void handle(Event arg0) {
				timeSeconds++;
				timerLabel.setText(timeSeconds.toString());
				if(timeSeconds == gameLength)
				{
					timeline.stop();
					matchDataUpdate(match);
				}
			}
		}));
		timeline.playFromStart();
	}
	
	private void detailBtnFunctionality(Match match)
	{
		homeTeamScored.setOnAction(e -> homeTeamScoreUpdate(match));
		awayTeamScored.setOnAction(e -> awayTeamScoreUpdate(match));
		matchReportBtn.setOnAction(e -> new MatchReport(match));
	}

}
