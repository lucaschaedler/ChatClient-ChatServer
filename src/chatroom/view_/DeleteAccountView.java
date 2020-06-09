package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteAccountView extends Stage{
	
	public VBox root;
	public HBox hbox;
	public Label warningLbl;
	public Button closeBtn, confirmBtn;
	
	public DeleteAccountView() {
		warningLbl = new Label();
		closeBtn = new Button();
		closeBtn.setPadding(new Insets(10));
		confirmBtn = new Button();
		confirmBtn.setPadding(new Insets(10));
		hbox = new HBox();
		root = new VBox();
		hbox.getChildren().addAll(closeBtn, confirmBtn);
		root.getChildren().addAll(warningLbl, hbox);
		
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
		
		warningLbl.setText(t.getString("deleteaccountview.label.warning"));
		closeBtn.setText(t.getString("deleteaccountview.button.close"));
		confirmBtn.setText(t.getString("deleteaccountview.button.confirm"));
		this.setTitle(t.getString("deleteaccountview.titel"));
		this.sizeToScene();
	}
	public void stop() {
		this.close();
	}

}
