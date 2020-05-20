package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;

/*
 * MenuBar App_View
 */

public class ChatMenu extends MenuBar {

	private Menu fileMenu, profileMenu, preferencesMenu;
	public Menu languageMenu;
	public MenuItem refreshItem, exitItem, createAccountItem, loginItem, myProfileItem, changePasswordItem, logoutItem,
			blockListItem, deleteAccountItem, deLanguageItem, enLanguageItem;
	public Menu userName;

	public ChatMenu() {
		fileMenu = new Menu();
		refreshItem = new MenuItem();
		exitItem = new MenuItem();
		profileMenu = new Menu();
		createAccountItem = new MenuItem();
		loginItem = new MenuItem();
		myProfileItem = new MenuItem();
		changePasswordItem = new MenuItem();
		logoutItem = new MenuItem();
		preferencesMenu = new Menu();
		blockListItem = new MenuItem();
		deleteAccountItem = new MenuItem();
		languageMenu = new Menu();

		fileMenu.getItems().addAll(refreshItem, exitItem);
		profileMenu.getItems().addAll(createAccountItem, loginItem, myProfileItem, changePasswordItem, logoutItem);
		preferencesMenu.getItems().addAll(languageMenu, blockListItem, deleteAccountItem);
		this.getMenus().addAll(fileMenu, profileMenu, preferencesMenu);

		Region space = new Region();
		userName = new Menu("-");
		userName.setDisable(false);
		this.getChildren().add(space);
		this.getMenus().add(userName);
		userName.setId("Userstatus");
		updateTexts();
	}

	public void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();

		fileMenu.setText(t.getString("mainview.menu.file"));
		refreshItem.setText(t.getString("mainview.menu.file.refresh"));
		exitItem.setText(t.getString("mainview.menu.file.exit"));
		profileMenu.setText(t.getString("mainview.menu.profile"));
		createAccountItem.setText(t.getString("mainview.menu.profile.createaccount"));
		loginItem.setText(t.getString("mainview.menu.profile.login"));
		myProfileItem.setText(t.getString("mainview.menu.profile.myprofile"));
		changePasswordItem.setText(t.getString("mainview.menu.profile.changepassword"));
		logoutItem.setText(t.getString("mainview.menu.profile.logout"));
		preferencesMenu.setText(t.getString("mainview.menu.preferences"));
		blockListItem.setText(t.getString("mainview.menu.preferences.blocklist"));
		deleteAccountItem.setText(t.getString("mainview.menu.preferences.deleteaccount"));
		languageMenu.setText(t.getString("mainview.menu.preferences.languagemenu"));
	}

	public MenuItem getCreateAccountItem() {
		return createAccountItem;
	}

	public MenuItem getLoginItem() {
		return loginItem;
	}

	public MenuItem getLogoutItem() {
		return logoutItem;
	}

	public void showUser(String name) {
		userName.setText(name + " is logged in");
	}

}
