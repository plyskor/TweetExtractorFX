package es.uam.eps.tweetextractorfx.view.dialog.auth;

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
	@FXML 
	public void handleCancel() {
		this.getDialogStage().close();
	}
	@FXML
	public void handleCreateUser() {
		String userName=userNameField.getText();
		if(userName.trim().isEmpty()||userName.length()<3) {
			showErrorEmptyUser();
			return;
		}
		String password1=passwordField1.getText();
		if(password1.trim().isEmpty()||!checkPassword(password1)) {
			showErrorPasswordCheck();
		}
		String password2=passwordField2.getText();
	}
	private void showErrorPasswordCheck() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Contraseña incorrecta");
		alert.setContentText("Este usuario ya está registrado. Por favor, elija otro nombre de usuario o inicie sesión.");
		alert.showAndWait();
		return;		
	}
	private void showErrorExistingUser() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Usuario existente");
		alert.setContentText("Este usuario ya está registrado. Por favor, elija otro nombre de usuario o inicie sesión.");
		alert.showAndWait();
		return;
	}
	private void showErrorEmptyUser() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Nombre de usuario vacío");
		alert.setContentText("Por favor, elija un nombre de usuario de al menos 3 caracteres.");
		alert.showAndWait();
		return;
	}
	private void showErrorPasswordMismatch() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Información");
		alert.setHeaderText("Las contrasñas no coinciden");
		alert.setContentText("Las contraseñas introducidas no coinciden. Por favor, inténtalo de nuevo.");
		alert.showAndWait();
		return;
	}
	private boolean checkPassword(String password) {
		 String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$\n";
		return password.matches(pattern);
	}
}
