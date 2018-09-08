package es.uam.eps.tweetextractorfx.view.dialog.filter;

import org.w3c.dom.ls.LSInput;

import es.uam.eps.tweetextractorfx.model.filter.impl.FilterList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FilterListDialogControl {
	private FilterList filter;
	@FXML
	private TextField accountField;
	@FXML
	private TextField listField;
    private Stage dialogStage;

	public FilterListDialogControl() {
		initialize();
	}
	private void initialize() {
		filter=new FilterList();
	}
	/**
	 * @return the filter
	 */
	public FilterList getFilter() {
		return filter;
	}
	/**
	 * @param filter the filter to set
	 */
	public void setFilter(FilterList filter) {
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
	 * @return the accountField
	 */
	public TextField getAccountField() {
		return accountField;
	}
	/**
	 * @param accountField the accountField to set
	 */
	public void setAccountField(TextField accountField) {
		this.accountField = accountField;
	}
	/**
	 * @return the listField
	 */
	public TextField getListField() {
		return listField;
	}
	/**
	 * @param listField the listField to set
	 */
	public void setListField(TextField listField) {
		this.listField = listField;
	}
	public void handleDone() {
		if(accountField.getText().trim().isEmpty()||listField.getText().trim().isEmpty()) {
			//Algún campo está vacío
			showErrorEmptyFields();
			accountField.clear();
			listField.clear();
		}else{
			String[] accountChecker =accountField.getText().replaceAll("^[,\\s]+", "").split("[,\\s]+");
			String[] listChecker =listField.getText().replaceAll("^[,\\s]+", "").split("[,\\s]+");
			if(accountChecker.length>1||listChecker.length>1) {
				showErrorWrongValues();
			}else {
				filter.setAccount(accountField.getText());
				filter.setListName(listField.getText());
				this.getDialogStage().close();
			}
		}
	}
	private void showErrorWrongValues() {
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Alguno de los campos es incoherente");
    	alert.setContentText("Recuerde que las cuentas de Twitter y los nombres de listas se componen de una sola expresión sin espacios.");
    	alert.showAndWait();
        return;
	}
	private void showErrorEmptyFields() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Alguno de los campos está vacío");
    	alert.setContentText("Por favor, seleccione una cuenta de Twitter y un nombre de lista existente en esa cuenta.");
    	alert.showAndWait();
        return;
    }
	public void handelCancel() {
		this.filter=null;
		this.getDialogStage().close();
	}
	
	
}
