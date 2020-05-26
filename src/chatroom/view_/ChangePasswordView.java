package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChangePasswordView extends Stage {

	public Label oldPasswordLbl, newPasswordLbl;
	public PasswordField oldPassword, newPassword;
	public Button confirmBtn;
	public GridPane pane;
	public Label failedLbl;

	public ChangePasswordView() {
		pane = new GridPane();
		oldPasswordLbl = new Label("\tOld Password:\t");
		newPasswordLbl = new Label("\tNew Password:\t");
		oldPassword = new PasswordField();
		newPassword = new PasswordField();
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
		failedLbl = new Label ("Failed to change Password");
		failedLbl.setTextFill(Color.RED);
		pane.add(failedLbl, 2, 3);
		this.sizeToScene();
		updateTexts();
	}
	
	 public void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator(); 
	       
	       confirmBtn.setText(t.getString("loginview.button.confirmbtn"));
	       oldPasswordLbl.setText(t.getString("changepasswordview.label.oldpasswordlbl"));
	       newPasswordLbl.setText(t.getString("changepasswordview.label.newpasswordlbl"));
	       try {
	    	   failedLbl.setText(t.getString("changepasswordview.label.failedlbl"));   
	       }catch(Exception e) {
	    	  // ignore nullpointer when label doesnt exist
	       }
	      
	       this.setTitle(t.getString("changepasswordview.titel"));
	       this.sizeToScene();
	       
	    }

	public Button getConfirmBtn() {
		return confirmBtn;
	}

}
