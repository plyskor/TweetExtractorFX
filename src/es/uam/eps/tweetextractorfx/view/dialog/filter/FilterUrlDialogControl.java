package es.uam.eps.tweetextractorfx.view.dialog.filter;

import es.uam.eps.tweetextractorfx.model.filter.impl.FilterUrl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FilterUrlDialogControl {
	private FilterUrl filter;
	@FXML
	private TextField keyWordField;
    private Stage dialogStage;

	public FilterUrlDialogControl() {
		initialize();
	}
	private void initialize() {
		filter=new FilterUrl();
	}
	/**
	 * @return the filter
	 */
	public FilterUrl getFilter() {
		return filter;
	}
	/**
	 * @param filter the filter to set
	 */
	public void setFilter(FilterUrl filter) {
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
	}
	
	/**
	 * @return the keyWord
	 */
	public TextField getKeyWord() {
		return keyWordField;
	}
	/**
	 * @param keyWord the keyWord to set
	 */
	public void setKeyWord(TextField keyWord) {
		this.keyWordField = keyWord;
	}
	public void handleAddKeyWord() {
		if (keyWordField.getText().trim().isEmpty()) {
			showErrorEmptyNickname();
		}else {
			filter.setKeyWord(keyWordField.getText().trim());
			this.getDialogStage().close();
		}
		
	}
	private void showErrorEmptyNickname() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ningún nombre de usuario seleccionado");
    	alert.setContentText("Por favor, seleccione un nombre de usuario como origen de los tweets.");
    	alert.showAndWait();
        return;
    }
	public void handelCancel() {
		this.filter=null;
		this.getDialogStage().close();
	}
	
	
}
