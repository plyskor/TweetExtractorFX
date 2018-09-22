package es.uam.eps.tweetextractorfx.view.dialog.auth;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

import es.uam.eps.tweetextractorfx.MainApplication;
import es.uam.eps.tweetextractorfx.error.ErrorDialog;
import es.uam.eps.tweetextractorfx.model.User;
import es.uam.eps.tweetextractorfx.util.XMLManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NewUserDialogControl {
	private Stage dialogStage;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField1;
	@FXML
	private PasswordField passwordField2;
	private MainApplication mainApplication;
	public NewUserDialogControl() {

	}
	@FXML 
	private void initialize() {
		
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
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	/**
	 * @return the userNameField
	 */
	public TextField getUserNameField() {
		return userNameField;
	}
	/**
	 * @param userNameField the userNameField to set
	 */
	public void setUserNameField(TextField userNameField) {
		this.userNameField = userNameField;
	}
	/**
	 * @return the passwordField1
	 */
	public PasswordField getPasswordField1() {
		return passwordField1;
	}
	/**
	 * @param passwordField1 the passwordField1 to set
	 */
	public void setPasswordField1(PasswordField passwordField1) {
		this.passwordField1 = passwordField1;
	}
	/**
	 * @return the passwordField2
	 */
	public PasswordField getPasswordField2() {
		return passwordField2;
	}
	/**
	 * @param passwordField2 the passwordField2 to set
	 */
	public void setPasswordField2(PasswordField passwordField2) {
		this.passwordField2 = passwordField2;
	}
	
	/**
	 * @return the mainApplication
	 */
	public MainApplication getMainApplication() {
		return mainApplication;
	}
	/**
	 * @param mainApplication the mainApplication to set
	 */
	public void setMainApplication(MainApplication mainApplication) {
		this.mainApplication = mainApplication;
	}
	@FXML 
	public void handleCancel() {
		this.getDialogStage().close();
	}
	@FXML
	public void handleCreateUser() {
		String userName=userNameField.getText().trim();
		if(userName.trim().isEmpty()||userName.length()<3) {
			ErrorDialog.showErrorEmptyUser();
			return;
		}
		if(this.getMainApplication().existsUser(userName)) {
			ErrorDialog.showErrorExistingUser();
			return;
		}
		String password1=passwordField1.getText().replace("\r", "").replace("\n", "");
		if(password1.trim().isEmpty()||!checkPassword(password1)) {
			ErrorDialog.showErrorPasswordCheck();
			return;
		}
		String password2=passwordField2.getText();
		if(!password1.equals(password2)) {
			ErrorDialog.showErrorPasswordMismatch();
			return;
		}
		User newUser = new User(userName,BCrypt.hashpw(password1, BCrypt.gensalt(12)));
		this.getMainApplication().getUserList().add(newUser);
		try {
			XMLManager.saveUserList(this.getMainApplication().getUserList());
		} catch (Exception e) {
			ErrorDialog.showErrorSaveUser(e.getMessage());
			return;
		}
		ErrorDialog.showSuccessCreateUser();
		this.dialogStage.close();
	}
	
	public static boolean checkPassword(String password) {
		 String pattern = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{6,16})";
		 Pattern p = Pattern.compile(pattern);
	     Matcher m = p.matcher(password);
		return m.matches();
	}
}
