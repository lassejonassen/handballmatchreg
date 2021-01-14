package presentation;
import data.Match;
import logic.iMatchImpl;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MatchMenu {
	private Button createMatchBtn = new Button("Opret Kamp");
	private Button deleteMatchBtn = new Button("Slet Kamp");
	private Button updateMatchBtn = new Button("Opdater Kamp");
	private Button backBtn = new Button("Tilbage");
	private Layout layout = new Layout();
	
	public MatchMenu(Stage stage) {
		layout.left.getChildren().addAll(createMatchBtn, deleteMatchBtn, updateMatchBtn,backBtn);
		layout.left.setBottomAnchor(backBtn, 0.0);
		layout.left.setTopAnchor(createMatchBtn, 0.0);
		layout.left.setTopAnchor(updateMatchBtn, 50.0);
		layout.left.setTopAnchor(deleteMatchBtn, 100.0);
		matchBtnFunctionality(stage);
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	
	private void matchBtnFunctionality(Stage stage) {
		Menu menu = new Menu();
		backBtn.setOnAction(e -> menu.showMenu(stage));
		createMatchBtn.setOnAction(e-> new CreateMatch());
		Scene scene = new Scene(layout.root);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		stage.setScene(scene);
	}
	
	private void deleteMatch() {
		iMatchImpl matchImpl = new iMatchImpl();
		
	}
	
	private void updateMatch() {
		iMatchImpl matchImpl = new iMatchImpl();
	}
}
