package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.abstractClasses.View;
import chatroom.ServiceLocator;
import chatroom.model.App_Model;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_View extends View<App_Model> {
	
	//classes
	public LoginView loginView;
	public AccountView accountView;

	public App_View(Stage stage, App_Model model) {
		super(stage, model);
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
		stage.setScene(create_GUI());

	}

	@Override
	protected Scene create_GUI() {

		return scene;
	}

	public void createLoginView() {
		loginView = new LoginView();
	}
	
	public Button getConfirmLoginBtn() {
		return loginView.getConfirmBtn();
	}
	
	public void createAccountView() {
		accountView = new AccountView();
	}

	public Button getCreateAccountBtn() {
		return accountView.getConfirmBtn();
	}

	

}