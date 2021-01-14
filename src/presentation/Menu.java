package presentation;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu 
{
	private Button showLigaBtn = new Button("Liga");
	private Button showMatchBtn = new Button("Kamp");
	private Button phOpretHoldKnap = new Button("PlaceHolder hold knap thingy");
	private Button btn4 = new Button("this is button 4");
	
	
	public void showMenu(Stage stage)
	{
		Layout layout = new Layout();
		layout.left.getChildren().addAll(showLigaBtn,showMatchBtn,phOpretHoldKnap,btn4);
		
		layout.left.setTopAnchor(showLigaBtn, 0.0);
		layout.left.setTopAnchor(showMatchBtn, 50.0);
		layout.left.setTopAnchor(phOpretHoldKnap, 100.0);
		layout.left.setTopAnchor(btn4, 150.0);

		buttonFunctionality(stage);
		
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void buttonFunctionality(Stage stage)
	{
		showMatchBtn.setOnAction(e -> new MatchMenu(stage));
		showLigaBtn.setOnAction(e -> new LigaMenu(stage));
		phOpretHoldKnap.setOnAction(e -> new CreateTeam());
  }
}
