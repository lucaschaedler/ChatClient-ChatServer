package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * 	Main-GUI für ChatRoom -- evt noch ein Status-Label (in buttonsLeft) implementieren
 */
public class MainView extends Stage {

	private BorderPane root = new BorderPane();

	protected static Image msn = new Image(MainView.class.getResourceAsStream("msn_logo.png"));
	
	
	public MainView() {
		

		addRoomBtn = new Button("Add Room");
		joinRoomBtn = new Button("Join Room");
		blockUserBtn = new Button("Block");
		sendMessageBtn = new Button("Send");
		writeScrollPane = new ScrollPane();
		writeTextArea = new TextArea();
		
		// MenuBar
		

		// Chatbox


		// Chatrooms-Online-Box

		
		// Users-Online-Box


		// MessageBox


		// BottomBox
		
		root.setTop(menuBar);
		root.setLeft(roomListScrollPane);
		root.setCenter(screenScrollPane);
		root.setRight(userListScrollPane);
		root.setBottom(bottomBox);

		//Create Gui methode
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		this.setScene(scene);
		this.sizeToScene();
		this.centerOnScreen();
		this.resizableProperty().setValue(Boolean.FALSE); //no maximizing allowed
		this.getIcons().add(msn);
		this.setTitle("Chat Messenger");
		this.show();
	}
	
	public String getMessageText() {
		return writeTextArea.getText();
	}
	
	public void setToBackground() { //Bei Login im Hintergrund
		this.setToBackground();
	}
	
	public void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		
		addRoomBtn.setText(t.getString("mainview.button.addroom"));
		joinRoomBtn.setText(t.getString("mainview.button.joinroom"));
		blockUserBtn.setText(t.getString("mainview.button.blockuser"));
		sendMessageBtn.setText(t.getString("mainview.button.sendmessage"));
		
		
		
		
	}
}
