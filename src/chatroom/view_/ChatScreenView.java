package chatroom.view_;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class ChatScreenView extends ScrollPane {
	
	public TextArea screenTextArea;

	public ChatScreenView() {
		
		screenTextArea = new TextArea();
		screenTextArea.setText("--->>>Nachrichten anzeigen lassen<<<---");
		screenTextArea.setEditable(false); //show messages only
		screenTextArea.setPrefSize(710, 400);
		screenTextArea.setId("screenArea"); //CSS
		this.setContent(screenTextArea);
		this.setPadding(new Insets(10));
		this.setPrefSize(720, 420);
		
	}

	public void createNewMessageLine(String message) {
		screenTextArea.appendText(message + "\n");
		this.setContent(screenTextArea);
	}
	
	public void removeAllMessages () {
		screenTextArea.clear();
		this.setContent(screenTextArea);
	}
	
	
}
