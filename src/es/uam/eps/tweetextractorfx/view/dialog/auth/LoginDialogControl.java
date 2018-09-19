/**
 * 
 */
package es.uam.eps.tweetextractorfx.view.dialog.auth;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	/**
	 * 
	 */
	public LoginDialogControl() {
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
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
	@FXML 
	public void handleCancel() {
		this.getDialogStage().close();
	}
	
}
