package es.uam.eps.tweetextractor;

import java.io.IOException;

import es.uam.eps.tweetextractor.view.RootLayoutControl;
import es.uam.eps.tweetextractor.view.WelcomeScreenControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class MainApplication extends Application {
	private Stage primaryStage;
    private BorderPane rootLayout;
    
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        //showPersonOverview();
	}
	
	/* Initialize the RootLayout */
	public void initRootLayout() {
    	try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutControl controller = loader.getController();
            controller.setMainApplication(this);
            showWelcomeScreen();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	/*Mostrando una Layout*/
	
	public void showWelcomeScreen() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("view/WelcomeScreen.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            WelcomeScreenControl controller = loader.getController();
            controller.setMainApplication(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
