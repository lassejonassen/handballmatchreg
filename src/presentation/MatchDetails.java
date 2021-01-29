package presentation;

import data.Match;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GoalImpl;
import logic.MatchImpl;
import logic.ReportDTOImpl;
import logic.SuspensionImpl;

public class MatchDetails 
{
	private Button homeTeamAddGoalBtn = new Button("Hjemme hold +1");
	private Button awayTeamAddGoalBtn = new Button("Ude hold +1");
	private Button homeTeamDeleteGoalBtn = new Button("Hjemme hold -1");
	private Button awayTeamDeleteGoalBtn = new Button("Ude hold -1");
	private Button homeTeamSuspension = new Button("Hjemme udvisning");
	private Button awayTeamSuspension = new Button("Ude udvisning");
	private Button matchReportBtn = new Button("Kamp rapport");
	private Button deleteSuspensionAwayBtn = new Button("Fjern Udvisning - Ude");
	private Button deleteSuspensionHomeBtn = new Button("Fjern Udvisning - Hjemme");
	private Button startMatchBtn = new Button("START kamp");
	private Button resumeMatchBtn = new Button("Forsaet kamp");
	private Button stopMatchBtn = new Button("STOP kamp");
	private Button closeBtn = new Button("Luk");

	private int STARTTIME = 0;
	private Timeline timeline;
	private Label timerLabel = new Label();
	private int timeSeconds = STARTTIME;
	private int gameLength = 10;
	private int timeMinutes = 0;
	private int totalTime;
	private boolean matchPaused = false;
	private boolean matchStarted = false;

	private Label homeTeamName = new Label();
	private Label awayTeamName = new Label();
	private Label homeScore = new Label();
	private Label awayScore = new Label();
	private Label homeSuspensionsText = new Label("Hjemme udvisninger");
	private Label homeSuspensions = new Label();
	private Label awaySuspensionsText = new Label("Ude udvisninger");
	private Label awaySuspensions = new Label();

	private int i = 0;
	private int j = 0;
	
	private int goalId;
	private int team1Suspensions;
	private int team2Suspensions;
	private int teamID;
	
	private Scene scene;
	private Stage window = new Stage();

	private ChildLayout childLayout = new ChildLayout();
	private MatchImpl matchImpl = new MatchImpl();
	private SuspensionImpl suspensionImpl = new SuspensionImpl();
	private GoalImpl goalImpl = new GoalImpl();
	private ReportDTOImpl reportDTOImpl = new ReportDTOImpl();
	private Validation valid = new Validation();

	public MatchDetails(Match match) 
	{	
		gridConstraints();
		showMatchDetails(match);
		detailBtnFunctionality(match);
		matchTeamDetails(match);
	}

	public void showMatchDetails(Match match) 
	{
		childLayout.childCenter.add(timerLabel, 2, 0);
		childLayout.childCenter.add(homeTeamName, 0, 1);
		childLayout.childCenter.add(homeScore, 1, 1);
		childLayout.childCenter.add(awayScore, 3, 1);
		childLayout.childCenter.add(awayTeamName, 4, 1);
		childLayout.childCenter.add(homeTeamAddGoalBtn, 0, 2);
		childLayout.childCenter.add(homeTeamDeleteGoalBtn, 0, 3);
		childLayout.childCenter.add(homeTeamSuspension, 1, 2);
		childLayout.childCenter.add(awayTeamSuspension, 3, 2);
		childLayout.childCenter.add(awayTeamAddGoalBtn, 4, 2);
		childLayout.childCenter.add(awayTeamDeleteGoalBtn, 4, 3);
		childLayout.childCenter.add(matchReportBtn, 2, 3);
		childLayout.childCenter.add(deleteSuspensionHomeBtn, 1, 3);
		childLayout.childCenter.add(homeSuspensionsText, 0, 4);
		childLayout.childCenter.add(homeSuspensions, 1, 4);
		childLayout.childCenter.add(deleteSuspensionAwayBtn, 3, 3);
		childLayout.childCenter.add(awaySuspensions, 3, 4);
		childLayout.childCenter.add(awaySuspensionsText, 4, 4);
		childLayout.childCenter.add(resumeMatchBtn, 1, 5);
		childLayout.childCenter.add(startMatchBtn, 2, 5);
		childLayout.childCenter.add(stopMatchBtn, 3, 5);

		childLayout.childBottom.getChildren().addAll(closeBtn);
		
		GridPane.setHalignment(homeTeamName, HPos.CENTER);
		GridPane.setHalignment(homeScore, HPos.CENTER);
		GridPane.setHalignment(awayScore, HPos.CENTER);
		GridPane.setHalignment(awayTeamName, HPos.CENTER);
		GridPane.setHalignment(homeSuspensions, HPos.CENTER);
		GridPane.setHalignment(awaySuspensions, HPos.CENTER);
		GridPane.setHalignment(timerLabel, HPos.CENTER);

		timerLabel.setText(timeMinutes + ":" + timeSeconds);
		
		timerLabel.setId("timerLabel");
		homeTeamName.setId("matchDetailLabels");
		homeScore.setId("matchDetailLabels");
		awayScore.setId("matchDetailLabels");
		awayTeamName.setId("matchDetailLabels");
		homeSuspensions.setId("matchDetailLabels");
		awaySuspensions.setId("matchDetailLabels");
		homeSuspensionsText.setId("matchDetailLabels");
		awaySuspensionsText.setId("matchDetailLabels");

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
		homeScore.setText("" + match.getTeam1Goals());
		awayScore.setText("" + match.getTeam2Goals());
		homeSuspensions.setText("" + 0);
		awaySuspensions.setText("" + 0);
	}

	private void homeTeamAddGoal(Match match) 
	{
		if (timeSeconds != 0) 
		{
			if (totalTime < gameLength) 
			{
				goalImpl.create(match, match.getTeam1Id(), totalTime);
				i++;
				match.setTeam1Goals(i);
				homeScore.setText("" + match.getTeam1Goals());
			}
		}
	}

	private void awayTeamAddGoal(Match match) 
	{
		if (timeSeconds != 0) 
		{
			if (totalTime < gameLength) 
			{
				goalImpl.create(match, match.getTeam2Id(), totalTime);
				j++;
				match.setTeam2Goals(j);
				awayScore.setText("" + match.getTeam2Goals());
			}
		}
	}

	private void homeTeamDeleteGoal(Match match, int goalId) 
	{
		if (timeSeconds != 0) 
		{
			if (timeSeconds < gameLength) 
			{
				if (match.getTeam1Goals() > 0) 
				{
					goalImpl.delete(match, match.getTeam1Id(), goalId);
					i--;
					match.setTeam1Goals(i);
					homeScore.setText("" + match.getTeam1Goals());
				}
			}
		}
	}

	private void awayTeamDeleteGoal(Match match, int goalId) 
	{

		if (timeSeconds != 0) 
		{
			if (timeSeconds < gameLength) 
			{
				if (match.getTeam2Goals() > 0) 
				{
					goalImpl.delete(match, match.getTeam2Id(), goalId);
					j--;
					match.setTeam2Goals(j);
					awayScore.setText("" + match.getTeam2Goals());
				}
			}
		}
	}

	private void matchDataUpdate(Match match) 
	{
		matchImpl.updateMatch(match);
		matchImpl.matchPlayed(match);
		timeline.stop();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void timerLabelUpdate(Match match) 
	{
		timeSeconds = STARTTIME;
		timerLabel.setText(timeMinutes + ":" + timeSeconds);
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() 
		{
			@Override
			public void handle(Event arg0) {
				timeSeconds++;
				timerLabel.setText(timeMinutes + ":" + timeSeconds);
				totalTime = (timeMinutes * 60) + timeSeconds;
				if (timeSeconds == 60) {
					timeMinutes++;
					timeSeconds = 0;
					timerLabel.setText(timeMinutes + ":" + timeSeconds);
				}

				if (totalTime == gameLength) 
				{
					timeline.stop();
					matchDataUpdate(match);
				}
			}
		}));
		timeline.playFromStart();
	}

	private void createSuspensionHome(Match match, int teamId, int totalTime) 
	{
		teamId = match.getTeam1Id();
		if (timeSeconds != 0) 
		{
			if (totalTime < gameLength) 
			{
				suspensionImpl.create(match, teamId, totalTime);
				team1Suspensions++;
				homeSuspensions.setText("" + team1Suspensions);
				
			}
		}
	}

	private void createSuspensionAway(Match match, int teamId, int totalTime) 
	{
		teamId = match.getTeam2Id();
		if (timeSeconds != 0) 
		{
			if (totalTime < gameLength) 
			{
				suspensionImpl.create(match, teamId, totalTime);
				team2Suspensions++;
				awaySuspensions.setText("" + team2Suspensions);
			}
		}
	}

	private void deleteSuspensionHome(Match match) 
	{
		if (timeSeconds != 0) 
		{
			if (timeSeconds < gameLength) {
				if (team1Suspensions > 0) {
					suspensionImpl.delete(match, match.getTeam1Id());
					team1Suspensions--;
					homeSuspensions.setText("" + team1Suspensions);
				}
			
			}
		}
	}

	private void deleteSuspensionAway(Match match) 
	{
		if (timeSeconds != 0) {
			if (timeSeconds < gameLength) {
				if (team2Suspensions > 0) {
					suspensionImpl.delete(match, match.getTeam2Id());
					team2Suspensions--;
					awaySuspensions.setText("" + team2Suspensions);
				}
				
			}
		}
	}

	private void detailBtnFunctionality(Match match) 
	{
		homeTeamAddGoalBtn.setOnAction(e -> {
			if (matchPaused)
				valid.matchPausedWarning();
			else
				homeTeamAddGoal(match);
		});
		awayTeamAddGoalBtn.setOnAction(e -> {
			if (matchPaused)
				valid.matchPausedWarning();
			else
				awayTeamAddGoal(match);
		});
		homeTeamDeleteGoalBtn.setOnAction(e -> {
			if (matchPaused)
				valid.matchPausedWarning();
			else
				homeTeamDeleteGoal(match, goalId);
		});
		awayTeamDeleteGoalBtn.setOnAction(e -> {
			if (matchPaused)
				valid.matchPausedWarning();
			else
				awayTeamDeleteGoal(match, goalId);
		});
		matchReportBtn.setOnAction(e -> {
			if (matchPaused)
				valid.matchPausedWarning();
			else
				new MatchReport(match,reportDTOImpl.read(match));
		});
		homeTeamSuspension.setOnAction(e -> {
			if (matchPaused)
				valid.matchPausedWarning();
			else
				createSuspensionHome(match, teamID, totalTime);
		});
		awayTeamSuspension.setOnAction(e -> {
			if (matchPaused)
				valid.matchPausedWarning();
			else
				createSuspensionAway(match, teamID, totalTime);
		});
		deleteSuspensionHomeBtn.setOnAction(e -> {
			if (matchPaused)
				valid.matchPausedWarning();
			else
				deleteSuspensionHome(match);
		});
		deleteSuspensionAwayBtn.setOnAction(e -> {
			if (matchPaused)
				valid.matchPausedWarning();
			else
				deleteSuspensionAway(match);
		});
		matchReportBtn.setOnAction(e -> {
			if (match.getPlayed().equals("yes"))
				new MatchReport(match, reportDTOImpl.read(match));
			else
				valid.matchNotPlayedWarning();
		});
		startMatchBtn.setOnAction(e -> {
			if (matchStarted)
				valid.matchStartedWarning();
			else if (match.getPlayed().equals("no")) {
				timerLabelUpdate(match);
				matchStarted = true;
			}
			else if (match.getPlayed().equals("yes"))
				valid.matchPlayedWarning();
		});
		stopMatchBtn.setOnAction(e -> {
			if (match.getPlayed().equals("yes"))
				valid.matchStopWarning();
			else if (matchStarted) {
				timeline.pause();
				matchPaused = true;
			}
			else if (!matchStarted)
				valid.matchStopWarning2();
		});
		resumeMatchBtn.setOnAction(e -> {
			if (match.getPlayed().equals("yes"))
				valid.matchResumeWarning();
			else if (matchPaused) {
				matchPaused = false;
				timeline.play();
			}
			else if (!matchPaused)
				valid.matchResumeWarning2();
		});
		closeBtn.setOnAction(e -> window.close());
	}
	private void gridConstraints()
	{
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(20);
		ColumnConstraints col2 = new ColumnConstraints();
		col1.setPercentWidth(20);
		ColumnConstraints col3 = new ColumnConstraints();
		col1.setPercentWidth(20);
		ColumnConstraints col4 = new ColumnConstraints();
		col1.setPercentWidth(20);
		ColumnConstraints col5 = new ColumnConstraints();
		col1.setPercentWidth(20);
		
		childLayout.childCenter.getColumnConstraints().addAll(col1, col2, col3, col4, col5);
	}
}
