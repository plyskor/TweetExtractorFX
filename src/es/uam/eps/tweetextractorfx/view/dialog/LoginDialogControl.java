/**
 * 
 */
package es.uam.eps.tweetextractorfx.view.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class LoginDialogControl {
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	
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
	
	
}
