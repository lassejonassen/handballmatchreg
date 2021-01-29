package presentation;


import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class Layout 
{
	protected BorderPane root = new BorderPane();
	protected HBox top = new HBox();
	protected HBox bottom = new HBox(15);
	protected HBox center = new HBox();
	protected AnchorPane left = new AnchorPane();
	protected VBox right = new VBox();
	protected Label heading = new Label();
	
	public Layout() 
	{
		topLayout();
		bottomLayout();
		centerLayout();
		leftLayout();
		rightLayout();
		rootId();
	}
	
	private void rootId()
	{
		root.setId("root");
	}
	
	private void topLayout()
	{
		root.setTop(top);
		top.setId("top");		
		
		top.getChildren().add(heading);
		heading.setText("Midtvejsprojekt - Kampregistering");
	}
	
	private void bottomLayout()
	{
		root.setBottom(bottom);
		bottom.setId("bottom");
	}
	
	private void centerLayout()
	{
		root.setCenter(center);
		center.setId("center");
	}
	
	private void leftLayout()
	{
		root.setLeft(left);
		left.setId("left");
	}
	
	private void rightLayout()
	{
		root.setRight(right);
		right.setId("right");
	}

}
