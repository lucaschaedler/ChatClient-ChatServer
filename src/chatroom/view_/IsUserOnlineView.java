package chatroom.view_;
import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class IsUserOnlineView extends Stage {

	public GridPane pane;
	public TextField userField;
	public Label info;
	public Label lbl;
	public Button confirmOnlineBtn;
	public Label resultOnline, resultOffline;

	public void isUserOnlineView() {
		pane = new GridPane();
		userField = new TextField();
		info = new Label();
		lbl = new Label("Username");
		confirmOnlineBtn = new Button();
		
		
		pane.add(info, 0, 0);
		pane.add(lbl, 0, 1);
		pane.add(userField, 1, 1);
		pane.add(confirmOnlineBtn, 2, 3);

		Scene scene = new Scene(pane);
		this.setAlwaysOnTop(true);
		this.setScene(scene);
		
		updateTexts();
	}

	private void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		
		info.setText(t.getString("isuseronline.info"));
		lbl.setText(t.getString("isuseronline.lbl"));
		confirmOnlineBtn.setText(t.getString("isuseronline.confirmBtn"));
		
		try {
			resultOnline.setText(t.getString("isuseronline.resultonline"));
		}catch(Exception e) {
			//nothing
		}
		try {
			resultOffline.setText(t.getString("isuseronline.resultoffline"));
		}catch(Exception e) {
			//nothing
		}
		this.setTitle(t.getString("isuseronline.titel"));
		
	}

	public void start() {
		this.show();
	}

	public void stop() {
		this.close();
	}
	
	public void userIsOnline() {
		resultOnline = new Label();
		pane.add(resultOnline, 2, 2);
		updateTexts();
	}
	
	public void userIsOffline() {
		resultOffline = new Label();
		pane.add(resultOffline, 2, 2);
		updateTexts();
	}
	public void removeResult() {
		try{
			pane.getChildren().remove(resultOnline);
		}catch(Exception x) {
			//nothing
		}
		
		try{
			pane.getChildren().remove(resultOffline);
		}catch(Exception x) {
			//nothing
		}
	}

}
