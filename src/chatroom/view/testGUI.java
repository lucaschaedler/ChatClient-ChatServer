package chatroom.view;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * 	Prototyp-GUI für ChatRoom
 */
public class testGUI extends Application {

	VBox root = new VBox();
	MenuBar menuBar = new MenuBar();
	Menu menu = new Menu("Hilfe");
	GridPane gridPane = new GridPane();
	ScrollPane screenScrollPane = new ScrollPane();
	TextArea screenTextArea = new TextArea();
	ScrollPane listScrollPane = new ScrollPane();
	ListView<String> listView = new ListView<>();
	ScrollPane writeScrollPane = new ScrollPane();
	TextArea writeTextArea = new TextArea();
	Button sendBtn = new Button("Senden");
	Image msn = new Image(testGUI.class.getResourceAsStream("msn_logo.png"));

	@Override
	public void start(Stage primaryStage) throws Exception {
		menuBar.getMenus().add(menu);

		// chatbox
		screenTextArea.setText("--->>>Nachrichten anzeigen lassen<<<---");
		screenTextArea.setEditable(false); // nur zum Anzeigen von Nachrichten
		screenTextArea.setMinSize(475, 400);
		screenScrollPane.setContent(screenTextArea);
		screenScrollPane.setPadding(new Insets(10));

		// Users-online-box
		ObservableList<String> otherUsersOnline = FXCollections.observableArrayList("herbert33", "jakob89", "free_aladin",
				"bastian", "prisitina_gangsta", "player69", "mrr0omantico", "zidane", "dubischdochvumheim22", "gustavo", "chillyvanilly",
				"brudi04", "herpes", "kaspar", "riesen05", "teleskop", "kurva", "guest04", "perle", "hannes", "guest05",
				"johannes", "chatter44", "brudabeforeluda");
		listView.setItems(otherUsersOnline);
		listScrollPane.setContent(listView);
		listScrollPane.setMaxWidth(150);
		listScrollPane.setPadding(new Insets(10));

		// messagebox
		writeTextArea.setMaxSize(475, 60);
		writeTextArea.setText("--->>>Nachrichten schreiben<<<---");
		writeTextArea.setOnMouseClicked((event) -> {
			writeTextArea.setText("");
		});
		writeScrollPane.setMaxSize(500, 80);
		writeScrollPane.setContent(writeTextArea);
		writeScrollPane.setPadding(new Insets(10));

		//sendButton
		sendBtn.setMinSize(150, 80);

		gridPane.add(screenScrollPane, 0, 0, 5, 10);
		gridPane.add(listScrollPane, 6, 0, 1, 10);
		gridPane.add(writeScrollPane, 0, 11, 5, 1);
		gridPane.add(sendBtn, 6, 11, 1, 1);
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		root.getChildren().addAll(menuBar, gridPane);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(msn);
		primaryStage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}
}
