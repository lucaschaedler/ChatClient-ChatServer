package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginView extends Stage {

	public GridPane pane;
	public Button confirmBtn, createAccountBtn;
	public TextField userName;
	public PasswordField password;
	public Label userNameLbl, passwordLbl;

	public LoginView() {
		pane = new GridPane();
		confirmBtn = new Button("Confirm");
		createAccountBtn = new Button("Create Account");
		userNameLbl = new Label("\tUsername:\t");
		passwordLbl = new Label("\tPassword:\t");
		userName = new TextField();
		password = new PasswordField();

		pane.add(userNameLbl, 1, 1);
		pane.add(userName, 2, 1);
		pane.add(passwordLbl, 1, 2);
		pane.add(password, 2, 2);
		pane.add(createAccountBtn, 1, 4);
		pane.add(confirmBtn, 3, 4);

		pane.setVgap(5);

		Scene scene = new Scene(pane);
		this.setScene(scene);
		// this.show();
		this.setAlwaysOnTop(true);

		updateTexts();

	}

	public void start() {
		this.show();
	}

	public void stop() {
		this.hide();
	}

	public String getUserName() {
		return userName.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public Button getConfirmBtn() {
		return confirmBtn;
	}

	public Button getCreateAccountBtn() {
		return createAccountBtn;
	}

	public Stage getLoginFrame() {
		return this;
	}

	public void failedToDoLogin() {
		Label failedLbl = new Label("Failed to do Login");
		failedLbl.setTextFill(Color.RED);
		pane.add(failedLbl, 2, 4);
		this.sizeToScene();
	}

	public void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();

		userNameLbl.setText(t.getString("loginview.label.usernameLbl"));
		passwordLbl.setText(t.getString("loginview.label.passswordlbl"));
		confirmBtn.setText(t.getString("loginview.button.confirmbtn"));
		createAccountBtn.setText(t.getString("loginview.button.createaccountbtn"));
		this.setTitle(t.getString("loginview.titel"));
	}

}
