package es.uam.eps.tweetextractorfx.view;

import java.io.IOException;

import es.uam.eps.tweetextractorfx.MainApplication;
import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import es.uam.eps.tweetextractorfx.view.dialog.filter.FilterContainsDialogControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;





public class QueryConstructorControl {
	/*Reference to the MainApplication*/
    private MainApplication mainApplication;
    @FXML
    private TableView<Filter> availableFiltersTable;
    @FXML
    private TableColumn<Filter, String> availableFiltersColumn;


    private Filter selectedAvailableFilter;
  	/**
	 * @return the availableFiltersTable
	 */
	public TableView<Filter> getAvailableFiltersTable() {
		return availableFiltersTable;
	}

	/**
	 * @param availableFiltersTable the availableFiltersTable to set
	 */
	public void setAvailableFiltersTable(TableView<Filter> availableFiltersTable) {
		this.availableFiltersTable = availableFiltersTable;
	}


	/**
	 * @return the availableFiltersColumn
	 */
	public TableColumn<Filter, String> getAvailableFiltersColumn() {
		return availableFiltersColumn;
	}

	/**
	 * @param availableFiltersColumn the availableFiltersColumn to set
	 */
	public void setAvailableFiltersColumn(TableColumn<Filter, String> availableFiltersColumn) {
		this.availableFiltersColumn = availableFiltersColumn;
	}

	/**
	 * @return the selectedAvailableFilter
	 */
	public Filter getSelectedAvailableFilter() {
		return selectedAvailableFilter;
	}

	/**
	 * @param selectedAvailableFilter the selectedAvailableFilter to set
	 */
	public void setSelectedAvailableFilter(Filter selectedAvailableFilter) {
		this.selectedAvailableFilter = selectedAvailableFilter;
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
		availableFiltersTable.setItems(mainApplication.getAvailableFilters());
	}
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        availableFiltersColumn.setCellValueFactory(cellData -> cellData.getValue().getLabel());
  
        // Listen for selection changes and show the person details when changed.
        availableFiltersTable.getSelectionModel().selectedItemProperty().addListener(
               (observable, oldValue, newValue) -> setSelectedAvailableFilter(newValue));
        selectedAvailableFilter=null;
    }
    @FXML
    public void handleAddFilter() {
    	if(selectedAvailableFilter==null) {
    		showErrorSelectFilter();
    	}else {
    		switch(selectedAvailableFilter.getClass().getCanonicalName()) {
    			case (Constants.CLASS_FILTER_CONTAINS):
    				showFilterContainsDialog();
    				break;
    			case (Constants.CLASS_FILTER_CONTAINS_EXACT):
    				//etc
    				break;
    			default:
    				// ERROR: Unknown Filter
    	        	Alert alert = new Alert(AlertType.ERROR);
    	        	alert.setTitle("Error");
    	        	alert.setHeaderText("Filtro desconocido");
    	        	alert.setContentText("Se ha producido un error al añadir el filtro. Por favor, vuelva a intentarlo.");
    	        	alert.showAndWait();
    				break;
    		}
    	}
    }
    private void showErrorSelectFilter() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ningún filtro seleccionado");
    	alert.setContentText("Por favor, seleccione un filtro para añadir de la lista de la izquierda");
    	alert.showAndWait();
        return;
    }
    @FXML
    public void handleDeleteFilter() {
    	
    }
    @FXML
    public void handleSaveQuery() {
    	
    }
    public void showFilterContainsDialog(){
    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(QueryConstructorControl.class.getResource("dialog/filter/FilterContainsDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApplication.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the dialogStage to the controller.
            FilterContainsDialogControl controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
