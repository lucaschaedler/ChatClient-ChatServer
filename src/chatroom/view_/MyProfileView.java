package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import chatroom.model.App_Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyProfileView extends Stage{

	public Label titleLbl, usernameLbl1, usernameLbl2, statusLbl1, statusLbl2;
	public Button closeBtn;
	
	public static Image euler = new Image(MyProfileView.class.getResourceAsStream("euler.jpg")); 
	public ImageView userImageView;
	
	public MyProfileView() {
		VBox root = new VBox();
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(30);
		userImageView = new ImageView(euler);
		userImageView.setFitHeight(70);
		userImageView.setFitWidth(70);
		titleLbl = new Label("My Profile");
		titleLbl.setId("usernameTitle");
		hbox.getChildren().addAll(userImageView, titleLbl);
		
		GridPane gridPane = new GridPane();
		usernameLbl1 = new Label("ppp");
		usernameLbl2 = new Label();
		statusLbl1 = new Label("Status:");
		statusLbl2 = new Label();
		gridPane.add(usernameLbl1, 0, 0);
		gridPane.add(usernameLbl2, 1, 0);
		gridPane.add(statusLbl1, 0, 2);
		gridPane.add(statusLbl2, 1, 2);
		gridPane.setHgap(30);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10));
		
		VBox vbox = new VBox();
		closeBtn = new Button("Close");
		vbox.getChildren().add(closeBtn);
		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(hbox, gridPane, vbox);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("myprofileview.css").toExternalForm());
		this.setScene(scene);
		this.sizeToScene();
		this.centerOnScreen();
		this.resizableProperty().setValue(Boolean.FALSE); //no maximizing allowed
		this.show();
		this.setAlwaysOnTop(true);
		
		updateTexts();
	}
	
	public void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		
		usernameLbl1.setText(t.getString("myprofileview.label.username"));
		statusLbl1.setText(t.getString("myprofileview.label.status"));
		closeBtn.setText(t.getString("myprofileview.button.close"));
		this.setTitle(t.getString("myprofileview.titel"));
		this.sizeToScene();
	}
	public void stop() {
		this.close();
	}
}
