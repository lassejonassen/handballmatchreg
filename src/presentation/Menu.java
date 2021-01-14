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
	private Button btn3 = new Button("this is button 3");
	private Button btn4 = new Button("this is button 4");
	
	
	public void showMenu(Stage stage)
	{
		Layout layout = new Layout();
		layout.left.getChildren().addAll(showLigaBtn,showMatchBtn,btn3,btn4);
		
		layout.left.setTopAnchor(showLigaBtn, 0.0);
		layout.left.setTopAnchor(showMatchBtn, 50.0);
		layout.left.setTopAnchor(btn3, 100.0);
		layout.left.setTopAnchor(btn4, 150.0);

		buttonFunctionality(stage);
		
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void buttonFunctionality(Stage stage)
	{
		LigaMenu ligaMenu = new LigaMenu();
		MatchMenu matchMenu = new MatchMenu();
		showLigaBtn.setOnAction(e -> ligaMenu.ligaShowMenu(stage));
		showMatchBtn.setOnAction(e -> matchMenu.showMatchMenu(stage));
		showLigaBtn.setOnAction(e -> new LigaMenu(stage));
  }
}
