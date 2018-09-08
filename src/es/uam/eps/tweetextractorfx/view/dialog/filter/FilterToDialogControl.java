package es.uam.eps.tweetextractorfx.view.dialog.filter;

import es.uam.eps.tweetextractorfx.model.filter.impl.FilterTo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FilterToDialogControl {
	private FilterTo filter;
	@FXML
	private TextField nickToAdd;
    private Stage dialogStage;

	public FilterToDialogControl() {
		initialize();
	}
	private void initialize() {
		filter=new FilterTo();
	}
	/**
	 * @return the filter
	 */
	public FilterTo getFilter() {
		return filter;
	}
	/**
	 * @param filter the filter to set
	 */
	public void setFilter(FilterTo filter) {
		this.filter = filter;
	}
	/**
	 * @return the nickToAdd
	 */
	public TextField getNickToAdd() {
		return nickToAdd;
	}
	/**
	 * @param nickToAdd the nickToAdd to set
	 */
	public void setNickToAdd(TextField nickToAdd) {
		this.nickToAdd = nickToAdd;
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
	public void handleAddNick() {
		if (nickToAdd.getText().trim().isEmpty()) {
			showErrorEmptyNickname();
		}else {
			filter.setNickName(nickToAdd.getText().trim());
			this.getDialogStage().close();
		}
		
	}
	private void showErrorEmptyNickname() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Información");
    	alert.setHeaderText("Ningún nombre de usuario seleccionado");
    	alert.setContentText("Por favor, seleccione un nombre de usuario como destino de los tweets.");
    	alert.showAndWait();
        return;
    }
	public void handelCancel() {
		this.filter=null;
		this.getDialogStage().close();
	}
	
	
}
