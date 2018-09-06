package es.uam.eps.tweetextractorfx.view;

import java.io.IOException;

import es.uam.eps.tweetextractorfx.MainApplication;
import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import es.uam.eps.tweetextractorfx.model.filter.impl.*;
import es.uam.eps.tweetextractorfx.view.dialog.filter.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML
    private TableView<Filter> addedFilterTable;
    @FXML
    private TableColumn<Filter, String> addedFiltersColumn;

    /*Added filters to the Query*/
    private ObservableList<Filter> addedFiltersList = FXCollections.observableArrayList(); 


    private Filter selectedAvailableFilter;
    private Filter selectedAddedFilter;
  	/**
	 * @return the availableFiltersTable
	 */
	public TableView<Filter> getAvailableFiltersTable() {
		return availableFiltersTable;
	}

	/**
	 * @return the addedFilterTable
	 */
	public TableView<Filter> getAddedFilterTable() {
		return addedFilterTable;
	}

	/**
	 * @return the selectedAddedFilter
	 */
	public Filter getSelectedAddedFilter() {
		return selectedAddedFilter;
	}

	/**
	 * @param selectedAddedFilter the selectedAddedFilter to set
	 */
	public void setSelectedAddedFilter(Filter selectedAddedFilter) {
		this.selectedAddedFilter = selectedAddedFilter;
	}

	/**
	 * @param addedFilterTable the addedFilterTable to set
	 */
	public void setAddedFilterTable(TableView<Filter> addedFilterTable) {
		this.addedFilterTable = addedFilterTable;
	}

	/**
	 * @return the addedFiltersColumn
	 */
	public TableColumn<Filter, String> getAddedFiltersColumn() {
		return addedFiltersColumn;
	}

	/**
	 * @param addedFiltersColumn the addedFiltersColumn to set
	 */
	public void setAddedFiltersColumn(TableColumn<Filter, String> addedFiltersColumn) {
		this.addedFiltersColumn = addedFiltersColumn;
	}

	/**
	 * @return the addedFiltersList
	 */
	public ObservableList<Filter> getAddedFiltersList() {
		return addedFiltersList;
	}

	/**
	 * @param addedFiltersList the addedFiltersList to set
	 */
	public void setAddedFiltersList(ObservableList<Filter> addedFiltersList) {
		this.addedFiltersList = addedFiltersList;
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
		addedFilterTable.setItems(addedFiltersList);
	}
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        availableFiltersColumn.setCellValueFactory(cellData -> cellData.getValue().getLabel());
        addedFiltersColumn.setCellValueFactory(cellData -> cellData.getValue().getSummary());

        // Listen for selection changes and show the person details when changed.
        availableFiltersTable.getSelectionModel().selectedItemProperty().addListener(
               (observable, oldValue, newValue) -> setSelectedAvailableFilter(newValue));
        addedFilterTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setSelectedAddedFilter(newValue));
        
        selectedAvailableFilter=null;
        selectedAddedFilter=null;
    }
    @FXML
    public void handleAddFilter() {
    	if(selectedAvailableFilter==null) {
    		showErrorSelectFilterAdd();
    	}else {
    		switch(selectedAvailableFilter.getClass().getCanonicalName()) {
    			case (Constants.CLASS_FILTER_CONTAINS):
    				showFilterContainsDialog();
    				break;
    			case (Constants.CLASS_FILTER_CONTAINS_EXACT):
    				showFilterContainsExactDialog();
    				break;
    			case (Constants.CLASS_FILTER_SINCE):
    				showFilterSinceDialog();
    				break;
    			case (Constants.CLASS_FILTER_UNTIL):
    				showFilterUntilDialog();
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
    private void showErrorSelectFilterAdd() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ningún filtro seleccionado");
    	alert.setContentText("Por favor, seleccione un filtro para añadir de la lista de la izquierda");
    	alert.showAndWait();
        return;
    }
    @FXML
    public void handleDeleteFilter() {
    	if(selectedAddedFilter==null) {
    		showErrorSelectFilterRemove();
    	}else {
        	addedFiltersList.remove(selectedAddedFilter);
    	}
    }
    private void showErrorSelectFilterRemove() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ningún filtro seleccionado");
    	alert.setContentText("Por favor, seleccione un filtro para eliminar de la lista de la derecha");
    	alert.showAndWait();
        return;
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
            addedFiltersList.add(new FilterContains(controller.getFilter()));
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
    public void showFilterContainsExactDialog(){
    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(QueryConstructorControl.class.getResource("dialog/filter/FilterContainsExactDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApplication.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the dialogStage to the controller.
            FilterContainsExactDialogControl controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            addedFiltersList.add(new FilterContainsExact(controller.getFilter()));
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
    public void showFilterSinceDialog(){
    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(QueryConstructorControl.class.getResource("dialog/filter/FilterSinceDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApplication.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the dialogStage to the controller.
            FilterSinceDialogControl controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            addedFiltersList.add(new FilterSince(controller.getFilter()));
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
    public void showFilterUntilDialog(){
    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(QueryConstructorControl.class.getResource("dialog/filter/FilterUntilDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApplication.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the dialogStage to the controller.
            FilterUntilDialogControl controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            addedFiltersList.add(new FilterUntil(controller.getFilter()));
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
