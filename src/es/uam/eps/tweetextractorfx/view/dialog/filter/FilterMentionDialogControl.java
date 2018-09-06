/**
 * 
 */
package es.uam.eps.tweetextractorfx.view.dialog.filter;

import es.uam.eps.tweetextractorfx.model.filter.impl.FilterMention;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class FilterMentionDialogControl {
	@FXML
	private ListView<String> selectedWordsView;
	@FXML
	private TextField wordToAdd;
    private FilterMention filter;
    private Stage dialogStage;
	/**
	 * 
	 */
	public FilterMentionDialogControl() {
		initialize();
	}

	/**
	 * @return the filter
	 */
	public FilterMention getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(FilterMention filter) {
		this.filter = filter;
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
		selectedWordsView.setItems(filter.getMentionList());
	}

	/**
	 * @return the wordToAdd
	 */
	public TextField getWordToAdd() {
		return wordToAdd;
	}

	/**
	 * @param wordToAdd the wordToAdd to set
	 */
	public void setWordToAdd(TextField wordToAdd) {
		this.wordToAdd = wordToAdd;
	}

	/**
	 * @return the selectedWordsView
	 */
	public ListView<String> getSelectedWordsView() {
		return selectedWordsView;
	}

	/**
	 * @param selectedWordsView the selectedWordsView to set
	 */
	public void setSelectedWordsView(ListView<String> selectedWordsView) {
		this.selectedWordsView = selectedWordsView;
	}

	private void initialize() {
		filter= new FilterMention();
		filter.getMentionList().clear();
	}
	@FXML
	public void handleAddWord() {
		if (wordToAdd.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Información");
	    	alert.setHeaderText("Ningúna palabra que añadir");
	    	alert.setContentText("Por favor, escriba una o varias palabras para añadirlas al filtro.");
	    	alert.showAndWait();
		}else {
			String[] wordsToAdd =wordToAdd.getText().replaceAll("^[,\\s]+", "").split("[,\\s]+");
			if (wordsToAdd.length!=1){
				//A decidir
				}else {
				if(!filter.getMentionList().contains(wordsToAdd[0]))filter.addMention(wordsToAdd[0]);
			}
			
			wordToAdd.clear();
		}
	}
	@FXML
	public void handleCancel() {
		dialogStage.close();
	}
	@FXML
	public void handleDone() {
		dialogStage.close();
	}

}
