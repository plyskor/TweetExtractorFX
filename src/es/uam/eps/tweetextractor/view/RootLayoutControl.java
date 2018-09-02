package es.uam.eps.tweetextractor.view;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import es.uam.eps.tweetextractor.MainApplication;
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
    	MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model;
        String message= new String("Extractor de datos para Twitter usando JavaFX \nAutor: Jose Antonio García del Saz\nVersion: ");
		try {
			model = reader.read(new FileReader("pom.xml"));
			message=message.concat(model.getVersion());
		} catch (IOException | XmlPullParserException  e) {
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
