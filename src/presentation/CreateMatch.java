package presentation;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreateMatch {
	
	private ComboBox homeChoice = new ComboBox();
	private ComboBox awayChoice = new ComboBox();
	private ComboBox ligaChoice = new ComboBox();
	
	private Stage window = new Stage();
	private Scene scene;
	
	private ChildLayout teamLayout = new ChildLayout();
	
	public CreateMatch()
	{
		showMatchCreate();
		matchCreateCenter();
	}
	
	protected void showMatchCreate() {
		Scene scene = new Scene(teamLayout.childRoot);
		scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
		window.setScene(scene);
		window.setTitle("Tilføj kamp");
		window.show();
	}
	
	private void matchCreateCenter() {
		// ComboBox kalde på hold liste.
		
		teamLayout.childCenter.add(new Label("Valg af liga"), 0, 0);
		teamLayout.childCenter.add(ligaChoice, 2, 0);
		teamLayout.childCenter.add(new Label("Hjemme"), 0, 1);
		teamLayout.childCenter.add(homeChoice, 0, 2);
		teamLayout.childCenter.add(new Label(" - "), 1, 1);
		teamLayout.childCenter.add(new Label("Ude"), 2, 1);
		teamLayout.childCenter.add(awayChoice, 2, 2);
	}
	
}
