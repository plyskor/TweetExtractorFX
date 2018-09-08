package es.uam.eps.tweetextractorfx;

import java.io.IOException;

import es.uam.eps.tweetextractorfx.model.filter.*;
import es.uam.eps.tweetextractorfx.model.filter.impl.*;
import es.uam.eps.tweetextractorfx.view.QueryConstructorControl;
import es.uam.eps.tweetextractorfx.view.RootLayoutControl;
import es.uam.eps.tweetextractorfx.view.WelcomeScreenControl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class MainApplication extends Application {
	private Stage primaryStage;
    private BorderPane rootLayout;
    /*Available filters for Queries*/
    private ObservableList<Filter> availableFilters = FXCollections.observableArrayList(); 

    
	public MainApplication() {
        initAvailableFilters();
	}

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
	/*Mostrando la pantalla de bienvenida*/
	
	public void showWelcomeScreen() {
        try {
            // Load elcome screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("view/WelcomeScreen.fxml"));
            AnchorPane welcomeScreen = (AnchorPane) loader.load();

            // Set welcome screen into the center of root layout.
            rootLayout.setCenter(welcomeScreen);

            // Give the controller access to the main app.
            WelcomeScreenControl controller = loader.getController();
            controller.setMainApplication(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showQueryConstructor() {
        try {
            // Load query constructor
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("view/QueryConstructor.fxml"));
            
            AnchorPane queryConstructor = (AnchorPane) loader.load();
            // Set query constructor into the center of root layout.
            rootLayout.setCenter(queryConstructor);
            
            // Give the controller access to the main app.
            QueryConstructorControl controller = loader.getController();
            controller.setMainApplication(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) {
		launch(args);
	}
	public void initAvailableFilters() {
		availableFilters.add(new FilterContains());
		availableFilters.add(new FilterContainsExact());
		availableFilters.add(new FilterMention());
		availableFilters.add(new FilterHashtag());
		availableFilters.add(new FilterFrom());
		availableFilters.add(new FilterTo());
		availableFilters.add(new FilterList());
		//availableFilters.add(new FilterAttitude());
		availableFilters.add(new FilterSince());
		availableFilters.add(new FilterUntil());
		availableFilters.add(new FilterUrl());
		//availableFilters.add(new FilterQuestion());
	}

	/**
	 * @return the availableFilters
	 */
	public ObservableList<Filter> getAvailableFilters() {
		return availableFilters;
	}

	/**
	 * @param availableFilters the availableFilters to set
	 */
	public void setAvailableFilters(ObservableList<Filter> availableFilters) {
		this.availableFilters = availableFilters;
	}

	/**
	 * @return the primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * @param primaryStage the primaryStage to set
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * @return the rootLayout
	 */
	public BorderPane getRootLayout() {
		return rootLayout;
	}

	/**
	 * @param rootLayout the rootLayout to set
	 */
	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
}
