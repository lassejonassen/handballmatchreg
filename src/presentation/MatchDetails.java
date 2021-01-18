package presentation;

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
	private int gameLength = 20;
	
	private Label homeTeamName = new Label("Hjemme holdets navn");
	private Label awayTeamName = new Label("Ude holdets navn");
	private Label homeScore = new Label("Hjemme holdets score");
	private Label awayScore = new Label("Ude holdets score");
	
	private Scene scene;
	private Stage window = new Stage();
	
	private ChildLayout childLayout = new ChildLayout();
	
	public MatchDetails()
	{
		showMatchDetails();
		detailBtnFunctionality();
		timerLabelUpdate();
	}
	
	public void showMatchDetails()
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
//		timerLabel.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(timerLabel, HPos.CENTER);
		
		scene = new Scene(childLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Kamp detaljer");
		window.show();
	}
	
	@SuppressWarnings("unchecked")
	private void timerLabelUpdate()
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
				}
			}
		}));
		timeline.playFromStart();
	}
	
	private void detailBtnFunctionality()
	{
		matchReportBtn.setOnAction(e -> new MatchReport());
	}

}
