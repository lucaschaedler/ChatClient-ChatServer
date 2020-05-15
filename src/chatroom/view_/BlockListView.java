package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BlockListView extends Stage {

	public ScrollPane scrollPane;
	public ListView<String> listView;
	public Button unblockBtn;
	public VBox pane;

	public BlockListView() {
		scrollPane = new ScrollPane();
		listView = new ListView<>();
		unblockBtn = new Button("Unblock");
		pane = new VBox();

		ObservableList<String> blockedUser = FXCollections.observableArrayList("Test1", "Test2", "Test3");
		listView.setItems(blockedUser);
		listView.setMaxWidth(500);
		scrollPane.setContent(listView);
		scrollPane.setMaxWidth(500);

		pane.getChildren().addAll(scrollPane, unblockBtn);

		Scene scene = new Scene(pane, 300, 400);
		this.setScene(scene);
		this.setTitle("Block List");
		this.show();
		this.setAlwaysOnTop(true);
	}
	
	 public void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator(); 
	       
	       unblockBtn.setText(t.getString("blocklistview.button.unblockbtn"));


	       
	    }

}
