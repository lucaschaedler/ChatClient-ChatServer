package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserPanel extends HBox {

	private VBox buttonsBoxLeft = new VBox();
	private VBox buttonsBoxRight = new VBox();
	public Button addRoomBtn, joinRoomBtn, blockUserBtn, sendMessageBtn;
	public ScrollPane writeScrollPane;
	public TextArea writeTextArea;

	public UserPanel() {
		// MessageBox
		writeTextArea.setText("--->>>Nachrichten schreiben<<<---");
		writeTextArea.setMinWidth(700);
		writeTextArea.setMaxHeight(125);
		writeTextArea.setId("writeArea"); // CSS
		writeTextArea.setOnMouseClicked((event) -> {
			writeTextArea.setText("");
		});
		writeScrollPane.setContent(writeTextArea);
		writeScrollPane.setPadding(new Insets(10));
		writeScrollPane.setMinWidth(720);
		writeScrollPane.setMaxHeight(145);

		// BottomBox
		addRoomBtn.setMinSize(100, 60);
		joinRoomBtn.setMinSize(100, 60);
		blockUserBtn.setMinSize(100, 60);
		sendMessageBtn.setMinSize(100, 60);
		buttonsBoxLeft.getChildren().addAll(addRoomBtn, joinRoomBtn);
		buttonsBoxLeft.setSpacing(5);
		buttonsBoxLeft.setPadding(new Insets(10));
		buttonsBoxRight.getChildren().addAll(blockUserBtn, sendMessageBtn);
		buttonsBoxRight.setSpacing(5);
		buttonsBoxRight.setPadding(new Insets(10));
		this.getChildren().addAll(buttonsBoxLeft, writeScrollPane, buttonsBoxRight);
	}

	public String getMessageText() {
		return writeTextArea.getText();
	}
	
	public void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		
		addRoomBtn.setText(t.getString("mainview.button.addroom"));
		joinRoomBtn.setText(t.getString("mainview.button.joinroom"));
		blockUserBtn.setText(t.getString("mainview.button.blockuser"));
		sendMessageBtn.setText(t.getString("mainview.button.sendmessage"));
	}
}