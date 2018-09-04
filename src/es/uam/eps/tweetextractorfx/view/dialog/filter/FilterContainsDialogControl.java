/**
 * 
 */
package es.uam.eps.tweetextractorfx.view.dialog.filter;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class FilterContainsDialogControl {
	@FXML
	private ListView<StringProperty> selectedWordsView;
	@FXML
	private TextField wordToAdd;
	private ObservableList<StringProperty> selectedWordsList = FXCollections.observableArrayList();
    
    private Stage dialogStage;
	/**
	 * 
	 */
	public FilterContainsDialogControl() {
		// TODO Auto-generated constructor stub
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
	public ListView<StringProperty> getSelectedWordsView() {
		return selectedWordsView;
	}

	/**
	 * @param selectedWordsView the selectedWordsView to set
	 */
	public void setSelectedWordsView(ListView<StringProperty> selectedWordsView) {
		this.selectedWordsView = selectedWordsView;
	}

	/**
	 * @return the selectedWordsList
	 */
	public ObservableList<StringProperty> getSelectedWordsList() {
		return selectedWordsList;
	}

	/**
	 * @param selectedWordsList the selectedWordsList to set
	 */
	public void setSelectedWordsList(ObservableList<StringProperty> selectedWordsList) {
		this.selectedWordsList = selectedWordsList;
	}
	@FXML
	public void handleAddWord() {

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
