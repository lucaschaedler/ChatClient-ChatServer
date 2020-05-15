package chatroom.view_;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class ChatScreenView extends ScrollPane {
	
	public TextArea screenTextArea;

	public ChatScreenView() {
		
		screenTextArea = new TextArea();
		screenTextArea.setText("--->>>Nachrichten anzeigen lassen<<<---");
		screenTextArea.setEditable(false); //show messages only
		screenTextArea.setMinSize(700, 400);
		screenTextArea.setId("screenArea"); //CSS
		this.setContent(screenTextArea);
		this.setPadding(new Insets(10));
		this.setMinSize(720, 420);
	}
	
	
}