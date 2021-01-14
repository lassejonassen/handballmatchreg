package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MatchMenu {
	private Button createMatchBtn = new Button("Opret Kamp");
	private Button backBtn = new Button("Tilbage");
	private Layout layout = new Layout();

	public MatchMenu(Stage stage) {
		layout.left.getChildren().addAll(createMatchBtn,backBtn);
		layout.left.setBottomAnchor(backBtn, 0.0);
		layout.left.setTopAnchor(createMatchBtn, 0.0);
		
		Menu menu = new Menu();
		CreateMatch createMatch = new CreateMatch();
		backBtn.setOnAction(e -> menu.showMenu(stage));
		createMatchBtn.setOnAction(e-> createMatch.showMatchCreate());
			
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
