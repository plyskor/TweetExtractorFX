package es.uam.eps.tweetextractorfx.view.extraction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import es.uam.eps.tweetextractorfx.MainApplication;
import es.uam.eps.tweetextractorfx.error.ErrorDialog;
import es.uam.eps.tweetextractorfx.model.Extraction;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import es.uam.eps.tweetextractorfx.util.XMLManager;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class ShowUserExtractionsControl {
	/* Reference to the MainApplication */
	private MainApplication mainApplication;
	@FXML
	private TreeTableView<String> extractionTableView;
	@FXML
	private TreeTableColumn<String, String> extractionColumn;
	private Extraction selectedExtraction;

	public ShowUserExtractionsControl() {
		super();
	}

	@FXML
	public void initialize() {
		extractionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));
		extractionTableView.getSelectionModel().selectedItemProperty().addListener((c, oldValue, newValue) -> {
			if ((newValue != null && newValue.getValue()!=null&&!newValue.getValue().startsWith("Extracción"))||newValue == null || newValue.getValue()==null) {
				Platform.runLater(() -> extractionTableView.getSelectionModel().clearSelection());
				selectedExtraction=null;
			} else {
				if(newValue!=null) {
				selectedExtraction = this.getMainApplication().getCurrentUser()
						.getExtraction(newValue.getValue().substring(11));
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
				"Extracciones de " + this.getMainApplication().getCurrentUser().getNickname());
		extractionTableView.setRoot(root);
		for (Extraction extraction : this.getMainApplication().getCurrentUser().getExtractionList()) {
			if(extraction!=null) {
			TreeItem<String> extractionNode = new TreeItem<String>("Extracción " + extraction.getId());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			TreeItem<String> createdAt = new TreeItem<String>("Creada el: " + df.format(extraction.getCreationDate()));
			TreeItem<String> lastModified = new TreeItem<String>(
					"Ùltima modificación: " + df.format(extraction.getLastModificationDate()));
			TreeItem<String> filtersNode = new TreeItem<String>("Filtros");
			for(Filter filter:extraction.getFilterList()) {
				TreeItem<String> filterItem = new TreeItem<String>(filter.getSummary());
				filtersNode.getChildren().add(filterItem);
			}
			extractionNode.getChildren().addAll(createdAt,lastModified,filtersNode);
			root.getChildren().add(extractionNode);
		}
		}	
	}

	/**
	 * @return the extractionTableView
	 */
	public TreeTableView<String> getExtractionTableView() {
		return extractionTableView;
	}

	/**
	 * @param extractionTableView the extractionTableView to set
	 */
	public void setExtractionTableView(TreeTableView<String> credentialsTableView) {
		this.extractionTableView = credentialsTableView;
	}

	/**
	 * @return the extractionColumn
	 */
	public TreeTableColumn<String, String> getExtractionColumn() {
		return extractionColumn;
	}

	/**
	 * @param extractionColumn the extractionColumn to set
	 */
	public void setExtractionColumn(TreeTableColumn<String, String> credentialsColumn) {
		this.extractionColumn = credentialsColumn;
	}

	/**
	 * @return the selectedExtraction
	 */
	public Extraction getSelectedExtraction() {
		return selectedExtraction;
	}

	/**
	 * @param selectedExtraction the selectedExtraction to set
	 */
	public void setSelectedExtraction(Extraction selectedExtraction) {
		this.selectedExtraction = selectedExtraction;
	}

	@FXML
	public void handleBack() {
		this.getMainApplication().showHomeScreen();
	}

	@FXML
	public void handleAddExtraction() {
	}

	@FXML
	public void handleEditExtraction() {
		this.getMainApplication().showExtractionDetails(selectedExtraction);
	}

	@FXML
	public void handleRemoveExtraction() {
		if(selectedExtraction==null) {
			ErrorDialog.showErrorNoSelectedExtraction();
				return;
		}else {
			this.getMainApplication().getCurrentUser().removeExtractionFromList(selectedExtraction);
			XMLManager.deleteExtraction(selectedExtraction);
			
			selectedExtraction=null;
			this.updateTreeTableView();
			try {
				XMLManager.saveUserList(this.getMainApplication().getUserList());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
