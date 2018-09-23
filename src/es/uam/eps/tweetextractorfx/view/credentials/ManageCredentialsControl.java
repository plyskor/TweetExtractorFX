package es.uam.eps.tweetextractorfx.view.credentials;

import java.io.IOException;

import es.uam.eps.tweetextractorfx.MainApplication;
import es.uam.eps.tweetextractorfx.error.ErrorDialog;
import es.uam.eps.tweetextractorfx.model.Credentials;
import es.uam.eps.tweetextractorfx.util.XMLManager;
import es.uam.eps.tweetextractorfx.view.HomeScreenControl;
import es.uam.eps.tweetextractorfx.view.dialog.credentials.AddCredentialsDialogControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class ManageCredentialsControl {
	/* Reference to the MainApplication */
	private MainApplication mainApplication;
	@FXML
	private TreeTableView<String> credentialsTableView;
	@FXML
	private TreeTableColumn<String, String> credentialsColumn;
	private Credentials selectedCredentials;

	public ManageCredentialsControl() {
		super();
	}

	@FXML
	public void initialize() {
		credentialsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));
		credentialsTableView.getSelectionModel().selectedItemProperty().addListener((c, oldValue, newValue) -> {
			if (newValue != null && !newValue.getValue().startsWith("@")) {
				Platform.runLater(() -> credentialsTableView.getSelectionModel().clearSelection());
				selectedCredentials=null;
			} else {
				if(newValue!=null) {
				selectedCredentials = this.getMainApplication().getCurrentUser()
						.getCredentials(newValue.getValue().substring(1));
				}
			}
		});
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
		this.updateTreeTableView();

	}

	@SuppressWarnings("unchecked")
	private void updateTreeTableView() {
		TreeItem<String> root = new TreeItem<String>(
				"Credenciales de Twitter de " + this.getMainApplication().getCurrentUser().getNickname());
		credentialsTableView.setRoot(root);
		for (Credentials credentials : this.getMainApplication().getCurrentUser().getCredentialList()) {
			TreeItem<String> credentialsNode = new TreeItem<String>("@" + credentials.getAccountScreenName());
			TreeItem<String> consumerKey = new TreeItem<String>("consumerKey: " + credentials.getConsumerKey());
			TreeItem<String> consumerSecret = new TreeItem<String>(
					"consumerSecret: " + credentials.getConsumerSecret());
			TreeItem<String> accessToken = new TreeItem<String>("accessToken: " + credentials.getAccessToken());
			TreeItem<String> accessTokenSecret = new TreeItem<String>(
					"consumerTokenSecret: " + credentials.getAccessTokenSecret());
			credentialsNode.getChildren().addAll(consumerKey, consumerSecret, accessToken, accessTokenSecret);
			root.getChildren().add(credentialsNode);
		}
	}

	/**
	 * @return the credentialsTableView
	 */
	public TreeTableView<String> getCredentialsTableView() {
		return credentialsTableView;
	}

	/**
	 * @param credentialsTableView the credentialsTableView to set
	 */
	public void setCredentialsTableView(TreeTableView<String> credentialsTableView) {
		this.credentialsTableView = credentialsTableView;
	}

	/**
	 * @return the credentialsColumn
	 */
	public TreeTableColumn<String, String> getCredentialsColumn() {
		return credentialsColumn;
	}

	/**
	 * @param credentialsColumn the credentialsColumn to set
	 */
	public void setCredentialsColumn(TreeTableColumn<String, String> credentialsColumn) {
		this.credentialsColumn = credentialsColumn;
	}

	/**
	 * @return the selectedCredentials
	 */
	public Credentials getSelectedCredentials() {
		return selectedCredentials;
	}

	/**
	 * @param selectedCredentials the selectedCredentials to set
	 */
	public void setSelectedCredentials(Credentials selectedCredentials) {
		this.selectedCredentials = selectedCredentials;
	}

	@FXML
	public void handleBack() {
		this.getMainApplication().showHomeScreen();
	}

	@FXML
	public void handleAddCredentials() {
		showAddCredentials(null);
	}

	@FXML
	public void handleEditCredentials() {
		if(selectedCredentials==null) {
			ErrorDialog.showErrorNoSelectedCredentials();
				return;
		}
		showAddCredentials(selectedCredentials);
		this.updateTreeTableView();
		try {
			XMLManager.saveUserList(this.getMainApplication().getUserList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handleRemoveCredentials() {
		if(selectedCredentials==null) {
			ErrorDialog.showErrorNoSelectedCredentials();
				return;
		}else {
			this.getMainApplication().getCurrentUser().getCredentialList().remove(selectedCredentials);
			selectedCredentials=null;
			this.updateTreeTableView();
			try {
				XMLManager.saveUserList(this.getMainApplication().getUserList());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void showAddCredentials(Credentials credentials) {
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
			controller.setCredentials(credentials);
			controller.setModifcation(true);
			// Show the dialog and wait until the user closes it, then add filter
			dialogStage.showAndWait();
			
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}
