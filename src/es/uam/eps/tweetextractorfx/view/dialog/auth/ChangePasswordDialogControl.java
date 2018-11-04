/**
 * 
 */
package es.uam.eps.tweetextractorfx.view.dialog.auth;

import org.mindrot.jbcrypt.BCrypt;

import es.uam.eps.tweetextractorfx.MainApplication;
import es.uam.eps.tweetextractorfx.dao.service.UserService;
import es.uam.eps.tweetextractorfx.error.ErrorDialog;
import es.uam.eps.tweetextractorfx.model.User;
import es.uam.eps.tweetextractorfx.util.XMLManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class ChangePasswordDialogControl {
	private Stage dialogStage;
	@FXML
	private PasswordField oldPasswordField;
	@FXML
	private PasswordField passwordField1;
	@FXML
	private PasswordField passwordField2;
	private MainApplication mainApplication;
	public ChangePasswordDialogControl() {
		super();
	}
	public Stage getDialogStage() {
		return dialogStage;
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public PasswordField getOldPasswordField() {
		return oldPasswordField;
	}
	public void setOldPasswordField(PasswordField oldPasswordField) {
		this.oldPasswordField = oldPasswordField;
	}
	public PasswordField getPasswordField1() {
		return passwordField1;
	}
	public void setPasswordField1(PasswordField passwordField1) {
		this.passwordField1 = passwordField1;
	}
	public PasswordField getPasswordField2() {
		return passwordField2;
	}
	public void setPasswordField2(PasswordField passwordField2) {
		this.passwordField2 = passwordField2;
	}
	public MainApplication getMainApplication() {
		return mainApplication;
	}
	public void setMainApplication(MainApplication mainApplication) {
		this.mainApplication = mainApplication;
	}
	@FXML
	public void handleCancel() {
		this.getDialogStage().close();
	}
	@FXML
	public void handleChangePassword() {
		String pass = oldPasswordField.getText().trim();
		if(pass.isEmpty()) {
			ErrorDialog.showErrorPassEmpty();
			return;
		}
		User userLogged = this.getMainApplication().getCurrentUser();
		boolean passOK = BCrypt.checkpw(pass, userLogged.getPassword());
		if(!passOK) {
			ErrorDialog.showErrorLoginFailed();
			return;
		}
		String password1=passwordField1.getText().replace("\r", "").replace("\n", "");
		if(password1.trim().isEmpty()||!NewUserDialogControl.checkPassword(password1)) {
			ErrorDialog.showErrorPasswordCheck();
			return;
		}
		String password2=passwordField2.getText();
		if(!password1.equals(password2)) {
			ErrorDialog.showErrorPasswordMismatch();
			return;
		}
		userLogged.setPassword(BCrypt.hashpw(password1, BCrypt.gensalt(12)));
		UserService userService = new UserService();
		userService.update(userLogged);
		try {
			XMLManager.saveUserList(this.getMainApplication().getUserList());
		} catch (Exception e) {
			ErrorDialog.showErrorSaveUser(e.getMessage());
			return;
		}
		ErrorDialog.showSuccessUpdatePassword();
		this.dialogStage.close();
	}
	
}
