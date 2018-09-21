/**
 * 
 */
package es.uam.eps.tweetextractorfx.view.dialog.auth;


import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

import com.sun.media.jfxmedia.logging.Logger;

import es.uam.eps.tweetextractorfx.model.User;
import es.uam.eps.tweetextractorfx.util.XMLManager;
import es.uam.eps.tweetextractorfx.view.WelcomeScreenControl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class LoginDialogControl {
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	private Stage dialogStage;
	private WelcomeScreenControl welcomeScreenControl;
	/*
	 * 
	 */
	public LoginDialogControl() {
		initialize();
	}

	private void initialize() {
		
	}

	/**
	 * @return the userField
	 */
	public TextField getUserField() {
		return userField;
	}

	/**
	 * @param userField the userField to set
	 */
	public void setUserField(TextField userField) {
		this.userField = userField;
	}

	/**
	 * @return the passField
	 */
	public PasswordField getPassField() {
		return passField;
	}

	/**
	 * @param passField the passField to set
	 */
	public void setPassField(PasswordField passField) {
		this.passField = passField;
	}

	/**
	 * @return the dialogStage
	 */
	public Stage getDialogStage() {
		return dialogStage;
	}

	/**
	 * @param dialogStage the dialogStage to set
	 */
	public void setDialogStage(Stage dialogState) {
		this.dialogStage = dialogState;
	}
	
	

	/**
	 * @return the welcomeScreenControl
	 */
	public WelcomeScreenControl getWelcomeScreenControl() {
		return welcomeScreenControl;
	}

	/**
	 * @param welcomeScreenControl the welcomeScreenControl to set
	 */
	public void setWelcomeScreenControl(WelcomeScreenControl welcomeScreenControl) {
		this.welcomeScreenControl = welcomeScreenControl;
		this.getWelcomeScreenControl().getMainApplication().updateUserList();

	}

	@FXML 
	public void handleCancel() {
		this.getDialogStage().close();
	}
	@FXML
	public void handleLogin() {
		String userName=userField.getText().trim();
		if(userName.isEmpty()) {
			showErrorUserEmpty();
			return;
		}
		if(!this.getWelcomeScreenControl().getMainApplication().existsUser(userName)) {
			showErrorExistsUSer();
			return;
		}
		String pass = passField.getText().trim();
		if(pass.isEmpty()) {
			showErrorPassEmpty();
			return;
		}
		User userLogged = this.getWelcomeScreenControl().getMainApplication().getUser(userName);
		boolean passOK = BCrypt.checkpw(pass, userLogged.getPassword());
		if(passOK) {
			this.getWelcomeScreenControl().getMainApplication().setCurrentUser(userLogged);
			this.getWelcomeScreenControl().getMainApplication().getCurrentUser().setLastConnectionDate(new Date());
			try {
				XMLManager.saveUserList(this.getWelcomeScreenControl().getMainApplication().getUserList());
			} catch (Exception e) {

			}
			this.getDialogStage().close();
			this.getWelcomeScreenControl().getMainApplication().showHomeScreen();
			return;
		}else {
			showErrorLoginFailed();
			return;
		}
	}
	@FXML
	public void handleRegister() {
		this.getWelcomeScreenControl().showNewAccountDialog();
		this.getDialogStage().close();

	}
	private void showErrorLoginFailed() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Contraseña incorrecta");
		alert.setContentText("La contraseña introducida es incorrecta. Inténtalo de nuevo.");
		alert.showAndWait();
		return;
	}

	private void showErrorExistsUSer() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Usuario inexistente");
		alert.setContentText("No existe ninguna cuenta registrada con ese nombre de usuario. Por favor, inténtalo de nuevo.");
		alert.showAndWait();
		return;
	}

	private void showErrorPassEmpty() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Contraseña vacía");
		alert.setContentText("Por favor, introduzca la contraseña para iniciar sesión.");
		alert.showAndWait();
		return;
	}

	private void showErrorUserEmpty() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Nombre de usuario vacío");
		alert.setContentText("Por favor, introduzca un nombre de usuario para iniciar sesión.");
		alert.showAndWait();
		return;
	}
	
}
