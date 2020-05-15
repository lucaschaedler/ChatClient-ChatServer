package chatroom.view_;

import ch.fhnw.richards.topic10_JavaAppTemplate.jat_v2.commonClasses.Translator;
import chatroom.ServiceLocator;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class ChatMenu extends MenuBar {

	private Menu fileMenu, profileMenu, preferencesMenu, languageMenu;
	public MenuItem refreshItem, exitItem, createAccountItem, loginItem, myProfileItem, changePasswordItem,
	menuLogoutItem, blockListItem, deleteAccountItem, deLanguageItem, enLanguageItem;
	
	public ChatMenu() {
		fileMenu = new Menu("File");
		refreshItem = new MenuItem("Refresh");
		exitItem = new MenuItem("Exit");
		profileMenu = new Menu("Profile");
		createAccountItem = new MenuItem("Create Account");
		loginItem = new MenuItem("Login");
		myProfileItem = new MenuItem("My Profile");
		changePasswordItem = new MenuItem("Change Password");
		menuLogoutItem = new MenuItem("Logout");
		preferencesMenu = new Menu("Preferences");
		blockListItem = new MenuItem("Blocklist");
		deleteAccountItem = new MenuItem("Delete Account");
		languageMenu = new Menu("Language");
		deLanguageItem = new MenuItem("DE");
		enLanguageItem = new MenuItem("EN");
		
		fileMenu.getItems().addAll(refreshItem, exitItem);
		profileMenu.getItems().addAll(createAccountItem, loginItem, myProfileItem, changePasswordItem, menuLogoutItem);
		languageMenu.getItems().addAll(deLanguageItem, enLanguageItem);
		preferencesMenu.getItems().addAll(languageMenu, blockListItem, deleteAccountItem);
		this.getMenus().addAll(fileMenu, profileMenu, preferencesMenu);
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
		menuLogoutItem.setText(t.getString("mainview.menu.profile.logout"));
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
}
