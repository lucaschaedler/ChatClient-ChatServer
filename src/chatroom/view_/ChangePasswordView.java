package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChangePasswordView extends Stage {

	public Label oldPasswordLbl, newPasswordLbl;
	public TextField oldPassword, newPassword;
	public Button confirmBtn;
	public GridPane pane;

	public ChangePasswordView() {
		pane = new GridPane();
		oldPasswordLbl = new Label("\tOld Password:\t");
		newPasswordLbl = new Label("\tNew Password:\t");
		oldPassword = new TextField();
		newPassword = new TextField();
		confirmBtn = new Button("Confirm");

		pane.add(oldPasswordLbl, 1, 1);
		pane.add(oldPassword, 2, 1);
		pane.add(newPasswordLbl, 1, 2);
		pane.add(newPassword, 2, 2);
		pane.add(confirmBtn, 3, 3);

		pane.setVgap(5);

		Scene scene = new Scene(pane, 450, 120);
		this.setScene(scene);
		this.show();
		this.setAlwaysOnTop(true);
		
		updateTexts();
	}
	
	public void failedToChangePassword() {
		Label failedLbl = new Label ("Failed to change Password");
		failedLbl.setTextFill(Color.RED);
		pane.add(failedLbl, 1, 4);
		this.sizeToScene();
	}
	
	 public void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator(); 
	       
	       confirmBtn.setText(t.getString("loginview.button.confirmbtn"));
	       oldPasswordLbl.setText(t.getString("changepasswordview.label.newpasswordlbl"));
	       newPasswordLbl.setText(t.getString("changepasswordview.label.oldpasswordlbl"));
	       this.setTitle(t.getString("changepasswordview.titel"));
	       
	    }

	public Button getConfirmBtn() {
		return confirmBtn;
	}

}
