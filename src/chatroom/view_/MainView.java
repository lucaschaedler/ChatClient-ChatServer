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
	private MenuBar menuBar = new MenuBar();
	private HBox bottomBox = new HBox();
	private VBox buttonsBoxLeft = new VBox();
	private VBox buttonsBoxRight = new VBox();

	public Button addRoomBtn, joinRoomBtn, blockUserBtn, sendMessageBtn;

	private Menu fileMenu, profileMenu, preferencesMenu, languageMenu;
	public MenuItem refreshItem, exitItem, createAccountItem, loginItem, myProfileItem, changePasswordItem,
	menuLogoutItem, blockListItem, deleteAccountItem, deLanguageItem, enLanguageItem;

	public ScrollPane screenScrollPane, roomListScrollPane, userListScrollPane, writeScrollPane;
	public TextArea screenTextArea, writeTextArea;
	public ListView<String> roomListView, userListView;

	protected static Image msn = new Image(MainView.class.getResourceAsStream("msn_logo.png"));
	
	
	public MainView() {
		
		fileMenu = new Menu("File");
		refreshItem = new MenuItem("Refresh");
		exitItem = new MenuItem("Exit");
		profileMenu = new Menu("Profile");
		createAccountItem = new MenuItem("Create Account");
		loginItem = new MenuItem("Login");
		myProfileItem = new MenuItem("My Profile");
		changePasswordItem = new MenuItem("Change Password");
		menuLogoutItem = new MenuItem("Logout");
		preferencesMenu = new Menu("Preferences");
		blockListItem = new MenuItem("Blocklist");
		deleteAccountItem = new MenuItem("Delete Account");
		languageMenu = new Menu("Language");
		deLanguageItem = new MenuItem("DE");
		enLanguageItem = new MenuItem("EN");
		addRoomBtn = new Button("Add Room");
		joinRoomBtn = new Button("Join Room");
		blockUserBtn = new Button("Block");
		sendMessageBtn = new Button("Send");
		screenScrollPane = new ScrollPane();
		screenTextArea = new TextArea();
		roomListScrollPane = new ScrollPane();
		roomListView = new ListView<>();
		userListScrollPane = new ScrollPane();
		userListView = new ListView<>();
		writeScrollPane = new ScrollPane();
		writeTextArea = new TextArea();
		
		// MenuBar
		fileMenu.getItems().addAll(refreshItem, exitItem);
		profileMenu.getItems().addAll(createAccountItem, loginItem, myProfileItem, changePasswordItem, menuLogoutItem);
		languageMenu.getItems().addAll(deLanguageItem, enLanguageItem);
		preferencesMenu.getItems().addAll(languageMenu, blockListItem, deleteAccountItem);
		menuBar.getMenus().addAll(fileMenu, profileMenu, preferencesMenu);

		// Chatbox
		screenTextArea.setText("--->>>Nachrichten anzeigen lassen<<<---");
		screenTextArea.setEditable(false); //show messages only
		screenTextArea.setMinSize(700, 400);
		screenTextArea.setId("screenArea"); //CSS
		screenScrollPane.setContent(screenTextArea);
		screenScrollPane.setPadding(new Insets(10));
		screenScrollPane.setMinSize(720, 420);

		// Chatrooms-Online-Box
		ObservableList<String> chatRooms = FXCollections.observableArrayList("gayroom", "playaroom", "girlsroom",
				"shippinoroom");
		roomListView.setItems(chatRooms);
		roomListView.setMaxWidth(160);
		roomListScrollPane.setContent(roomListView);
		roomListScrollPane.setPadding(new Insets(10));
		roomListScrollPane.setMaxWidth(180);
		
		// Users-Online-Box
		ObservableList<String> otherUsersOnline = FXCollections.observableArrayList("herbert33", "jakob89", "free_aladin",
				"bastian", "prisitina_gangsta", "player69", "mrr0omantico", "zidane", "dubischdochvumheim22", "gustavo", "chillyvanilly",
				"brudi04", "herpes", "kaspar", "riesen05", "teleskop", "kurva", "guest04", "perle", "hannes", "guest05",
				"johannes", "chatter44", "brudabeforeluda");
		userListView.setItems(otherUsersOnline);
		userListView.setMaxWidth(160);
		userListScrollPane.setContent(userListView);
		userListScrollPane.setPadding(new Insets(10));
		userListScrollPane.setMaxWidth(180);

		// MessageBox
		writeTextArea.setText("--->>>Nachrichten schreiben<<<---");
		writeTextArea.setMinWidth(700);
		writeTextArea.setMaxHeight(125);
		writeTextArea.setId("writeArea"); //CSS
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
		bottomBox.getChildren().addAll(buttonsBoxLeft, writeScrollPane, buttonsBoxRight);

		root.setTop(menuBar);
		root.setLeft(roomListScrollPane);
		root.setCenter(screenScrollPane);
		root.setRight(userListScrollPane);
		root.setBottom(bottomBox);

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
		
		fileMenu.setText(t.getString("mainview.menu.file"));
		refreshItem.setText(t.getString("mainview.menu.file.refresh"));
		exitItem.setText(t.getString("mainview.menu.file.exit"));
		profileMenu.setText(t.getString("mainview.menu.profile"));
		createAccountItem.setText(t.getString("mainview.menu.profile.createaccount"));
		loginItem.setText(t.getString("mainview.menu.profile.login"));
		myProfileItem.setText(t.getString("mainview.menu.profile.myprofile"));
		changePasswordItem.setText(t.getString("mainview.menu.profile.changepassword"));
		menuLogoutItem.setText(t.getString("mainview.menu.profile.logout"));
		preferencesMenu.setText(t.getString("mainview.menu.preferences"));
		blockListItem.setText(t.getString("mainview.menu.preferences.blocklist"));
		deleteAccountItem.setText(t.getString("mainview.menu.preferences.deleteaccount"));
		languageMenu.setText(t.getString("mainview.menu.preferences.languagemenu"));
		addRoomBtn.setText(t.getString("mainview.button.addroom"));
		joinRoomBtn.setText(t.getString("mainview.button.joinroom"));
		blockUserBtn.setText(t.getString("mainview.button.blockuser"));
		sendMessageBtn.setText(t.getString("mainview.button.sendmessage"));
		
		
		
		
	}
}
