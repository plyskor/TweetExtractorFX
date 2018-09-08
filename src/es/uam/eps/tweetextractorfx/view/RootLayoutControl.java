package es.uam.eps.tweetextractorfx.view;

import java.io.IOException;
import java.util.Properties;
import es.uam.eps.tweetextractorfx.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RootLayoutControl {
	/*Reference to the MainApplication*/
    private MainApplication mainApplication;

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

	/**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        String message= new String("Extractor de datos para Twitter usando JavaFX \nAutor: Jose Antonio García del Saz\nVersion: ");
		try {
			final Properties properties = new Properties();
			properties.load(this.getClass().getClassLoader().getResourceAsStream("tweetextractorfx.properties"));
			message=message.concat(properties.getProperty("tweetextractorfx.version"));
		} catch (IOException  e) {
			e.printStackTrace();
		}
        Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("TweetExtractorFX");
    	alert.setHeaderText("Acerca de...");
    	alert.setContentText(message);
    	alert.showAndWait();
    }
    /**
     * Opens the new query constructor
     */
    @FXML
    private void handleNewQuery() {
    	mainApplication.showQueryConstructor();
    }
    /**
     * Closes the application.
     */
    @FXML
    private void handleStart() {
    	mainApplication.showWelcomeScreen();
    }
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    /**
     * Opens the birthday statistics.
     */
}
