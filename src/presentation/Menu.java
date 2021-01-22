package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Menu 
{
	private Button showLigaBtn = new Button("Liga");
	private Button showMatchBtn = new Button("Kamp");
	
	
	@SuppressWarnings("static-access")
	public Menu(Stage stage)
	{
		Layout layout = new Layout();
		layout.left.getChildren().addAll(showLigaBtn,showMatchBtn);
		
		layout.left.setTopAnchor(showLigaBtn, 0.0);
		layout.left.setTopAnchor(showMatchBtn, 50.0);

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
  }
}
