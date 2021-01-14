package presentation;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreateMatch {
	
	private Label homeLabel = new Label("Hjemme");
	private Label awayLabel = new Label("Ude");
	private Label vsLabel = new Label("-");
	private Label leagueLabel = new Label("Valg af liga");
	
	private ComboBox homeChoice = new ComboBox();
	private ComboBox awayChoice = new ComboBox();
	private ComboBox ligaChoice = new ComboBox();
	
	private Stage window = new Stage();
	private Scene scene;
	
	private ChildLayout teamLayout = new ChildLayout();
	
	protected void showMatchCreate() {
		Scene scene = new Scene(teamLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Tilføj kamp");
		window.show();
	}
	
	private void matchCreateCenter() {
		// ComboBox kalde på hold liste.
		teamLayout.childCenter.add(homeLabel, 1, 0);
		teamLayout.childCenter.add(awayLabel, 3, 0);
		teamLayout.childCenter.add(vsLabel, 2, 1);
		teamLayout.childCenter.add(homeChoice, 1, 1);
		teamLayout.childCenter.add(awayChoice, 3, 1);
		teamLayout.childCenter.add(ligaChoice, 0, 0);
	}
	
}
