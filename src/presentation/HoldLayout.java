package presentation;

import javafx.scene.layout.*;

public class HoldLayout 
{
	protected BorderPane childRoot = new BorderPane();
	private HBox childTop = new HBox();
	private HBox childBottom = new HBox();
	private VBox childLeft = new VBox();
	private VBox childRight = new VBox();
	
	public HoldLayout()
	{
		childTopLayout();
		childBottomLayout();
		childLeftLayout();
		childRightLayout();
	}
	
	private void childTopLayout()
	{
		childRoot.setTop(childTop);
		childTop.setId("childTop");
	}
	
	private void childBottomLayout()
	{
		childRoot.setBottom(childBottom);
		childBottom.setId("childBottom");
	}
	
	private void childLeftLayout()
	{
		childRoot.setLeft(childLeft);
		childLeft.setId("childLeft");
	}
	
	private void childRightLayout()
	{
		childRoot.setRight(childRight);
		childRight.setId("childRight");
	}

}
