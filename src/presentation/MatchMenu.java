package presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MatchMenu {
	private Button createMatchBtn = new Button("Opret Kamp");
	private Button backBtn = new Button("Tilbage");
<<<<<<< HEAD
	
=======
>>>>>>> 813bf7f0bdc81a87676c47992a3aadffb6c7f8f9
	private Layout layout = new Layout();
	
	public MatchMenu(Stage stage) {
		layout.left.getChildren().addAll(createMatchBtn,backBtn);
		layout.left.setBottomAnchor(backBtn, 0.0);
		layout.left.setTopAnchor(createMatchBtn, 0.0);
		matchBtnFunctionality(stage);
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	
	private void matchBtnFunctionality(Stage stage) {
		Menu menu = new Menu();
		backBtn.setOnAction(e -> menu.showMenu(stage));
<<<<<<< HEAD
		createMatchBtn.setOnAction(e-> new CreateMatch());
=======
		createMatchBtn.setOnAction(e-> createMatch.showMatchCreate());
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
>>>>>>> 813bf7f0bdc81a87676c47992a3aadffb6c7f8f9
	}
}
