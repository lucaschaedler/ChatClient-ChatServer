package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InformationView extends Stage {

	public Label info;
	public HBox box;

	public InformationView() {
		box = new HBox();
		info = new Label("You are in no Chatroom, Please enter one!");

		box.getChildren().add(info);

		Scene scene = new Scene(box);
		this.setScene(scene);
		this.setAlwaysOnTop(true);
		this.sizeToScene();

		updateTexts();
		this.show();
	}

	public void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		info.setText(t.getString("informationview.label"));
		this.setTitle(t.getString("informationview.titel"));
		this.sizeToScene();
	}

	public void start() {
		this.show();		
	}

}
