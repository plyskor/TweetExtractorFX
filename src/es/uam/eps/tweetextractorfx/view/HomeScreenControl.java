/**
 * 
 */
package es.uam.eps.tweetextractorfx.view;

import java.io.IOException;

import es.uam.eps.tweetextractorfx.MainApplication;
import es.uam.eps.tweetextractorfx.error.ErrorDialog;
import es.uam.eps.tweetextractorfx.view.dialog.auth.ChangePasswordDialogControl;
import es.uam.eps.tweetextractorfx.view.dialog.credentials.AddCredentialsDialogControl;
import es.uam.eps.tweetextractorfx.view.extraction.ShowUserExtractionsControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class HomeScreenControl {
	/*Reference to the MainApplication*/
    private MainApplication mainApplication;
    @FXML
    private ImageView logoView;
    @FXML
    private Text userView;
	/**
	 * 
	 */
	public HomeScreenControl() {
	}
	@FXML
	private void initialize() {
        Image logo = new Image("icon.png");
        if(logo!=null)logoView.setImage(logo);
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
        this.getMainApplication().getRootLayoutController().addLogOut();
        String nickName = this.getMainApplication().getCurrentUser().getNickname().substring(0, 1).toUpperCase() +this.getMainApplication().getCurrentUser().getNickname().substring(1);
        userView.setText(nickName);
	}
	
	/**
	 * @return the logoView
	 */
	public ImageView getLogoView() {
		return logoView;
	}
	/**
	 * @param logoView the logoView to set
	 */
	public void setLogoView(ImageView logoView) {
		this.logoView = logoView;
	}
	@FXML
	public void handleCreateExtraction() {
		if(!this.getMainApplication().getCurrentUser().hasAnyCredentials()) {
			ErrorDialog.showErrorNoCredentials();
			return;
		}
		this.getMainApplication().showQueryConstructor();
	}

	@FXML
	public void handleAddCredentials() {
		showAddCredentials();
	}
	@FXML
	public void handleLogOut() {
		this.getMainApplication().getRootLayoutController().logOut();
	}
	@FXML
	public void handleManageCredentials() {
		this.getMainApplication().showManageCredentials();
	}
	
	@FXML
	public void handleChangePassword() {
		showUpdatePassword();
	}
	@FXML 
	public void handleManageExtractions() {
		this.getMainApplication().showUserExtractions();
	}
	/*DIALOGOS*/

	public void showAddCredentials() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HomeScreenControl.class.getResource("dialog/credentials/AddCredentialsDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApplication.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the dialogStage to the controller.
			AddCredentialsDialogControl controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApplication(mainApplication);
			// Show the dialog and wait until the user closes it, then add filter
			dialogStage.showAndWait();
			
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	public void showUpdatePassword() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HomeScreenControl.class.getResource("dialog/auth/ChangePasswordDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApplication.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the dialogStage to the controller.
			ChangePasswordDialogControl controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApplication(mainApplication);
			// Show the dialog and wait until the user closes it, then add filter
			dialogStage.showAndWait();
			
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
}
