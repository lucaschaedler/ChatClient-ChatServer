package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InformationView extends Stage {

	public Label info;
	public VBox box;
	
	public static Image noRoom = new Image(InformationView.class.getResourceAsStream("no_room.jpg"));
	public ImageView imageView;
			
	public InformationView() {
		box = new VBox();
		String h = Double.toString(noRoom.getHeight()) + " " + Double.toString(noRoom.getWidth());
		info = new Label("Please enter a Chatroom!");
		imageView = new ImageView(noRoom);
		imageView.setFitHeight(489);
		imageView.setFitWidth(363);

		box.getChildren().addAll(info, imageView);

		Scene scene = new Scene(box);
		scene.getStylesheets().add(getClass().getResource("informationview.css").toExternalForm());
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
