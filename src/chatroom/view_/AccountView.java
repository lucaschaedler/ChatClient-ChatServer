package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AccountView extends Stage {

	public Button confirmBtn, cancelBtn;
	public TextField userName, password, passwordConfirm;
	public Label userNameLbl, passwordLbl, passwordConfirmLbl;
	public GridPane pane;

	public AccountView() {
		pane = new GridPane();
		confirmBtn = new Button("Confirm");
		cancelBtn = new Button("Cancel");
		userName = new TextField();
		password = new TextField();
		passwordConfirm = new TextField();
		userNameLbl = new Label("\tUsername:\t");
		passwordLbl = new Label("\tPassword:\t");
		passwordConfirmLbl = new Label("\tCondfirm Password:\t");

		pane.add(userNameLbl, 1, 1);
		pane.add(userName, 2, 1);
		pane.add(passwordLbl, 1, 2);
		pane.add(password, 2, 2);
		pane.add(passwordConfirmLbl, 1, 3);
		pane.add(passwordConfirm, 2, 3);
		pane.add(cancelBtn, 1, 4);
		pane.add(confirmBtn, 3, 4);

		pane.setVgap(5);

		Scene scene = new Scene(pane);
		this.setTitle("Create Account");
		this.setScene(scene);
		this.show();
		this.setAlwaysOnTop(true);
		
		updateTexts();

	}

	public void stop() {
		this.hide();
	}

	public void closeButtonAction() {
		Stage stage = (Stage) cancelBtn.getScene().getWindow();
		stage.close();
	}

	public String getUserName() {
		return userName.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public Button getConfirmCreateAccountBtn() {
		return confirmBtn;
	}

	public Button getCancelBtn() {
		return cancelBtn;
	}

	public Stage getCreateAccountView() {
		return this;
	}

	public void failedToCreateAccount() {
		Label failedLbl = new Label("Failed to create Account");
		failedLbl.setTextFill(Color.RED);
		pane.add(failedLbl, 0, 4);
		this.sizeToScene();
	}

	public void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();

		userNameLbl.setText(t.getString("loginview.label.usernameLbl"));
		passwordLbl.setText(t.getString("loginview.label.passswordlbl"));
		passwordConfirmLbl.setText(t.getString("accountview.label.passwordconfirmlbl"));
		confirmBtn.setText(t.getString("loginview.button.confirmbtn"));
		cancelBtn.setText(t.getString("accountview.button.cancelBtn"));

	}

}
