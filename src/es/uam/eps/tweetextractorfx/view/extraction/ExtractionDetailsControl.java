/**
 * 
 */
package es.uam.eps.tweetextractorfx.view.extraction;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import es.uam.eps.tweetextractorfx.MainApplication;
import es.uam.eps.tweetextractorfx.dao.service.ExtractionService;
import es.uam.eps.tweetextractorfx.dao.service.TweetService;
import es.uam.eps.tweetextractorfx.error.ErrorDialog;
import es.uam.eps.tweetextractorfx.model.Extraction;
import es.uam.eps.tweetextractorfx.model.Tweet;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import es.uam.eps.tweetextractorfx.task.LoadTweetsTask;
import es.uam.eps.tweetextractorfx.task.UpdateExtractionTask;
import es.uam.eps.tweetextractorfx.task.status.UpdateStatus;
import es.uam.eps.tweetextractorfx.twitterapi.TwitterExtractor;
import es.uam.eps.tweetextractorfx.util.XMLManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import twitter4j.TwitterException;

/**
 * @author Jose Antonio Garc��a del Saz
 *
 */
public class ExtractionDetailsControl {
	private MainApplication mainApplication;
	@FXML
	private TableView<Tweet> tweetsTable;
	@FXML
	private TableColumn<Tweet, String> tweetsColumn;
	@FXML
	private Label authorLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label idLabel;
	@FXML
	private Label langLabel;

	private Tweet selectedQueryResult;
	private Extraction extraction;
	private Stage loadingDialog = null;
	private Alert alertUpdate;
	private ObservableList<Tweet> tweetObservableList = FXCollections.observableArrayList();

	private TwitterExtractor twitterextractor;

	/**
	 * 
	 */
	public ExtractionDetailsControl() {
		super();
	}

	@FXML
	private void initialize() {
		tweetsColumn.setCellFactory(tc -> {
			TableCell<Tweet, String> cell = new TableCell<>();
			Text text = new Text();
			cell.setGraphic(text);
			cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
			/* Cargamos la fuente emoji */
			InputStream stream = this.getClass().getClassLoader().getResourceAsStream("OpenSansEmoji.ttf");
			Font openSansEmoji = null;
			try {
				openSansEmoji = Font.createFont(Font.TRUETYPE_FONT, stream);
			} catch (FontFormatException | IOException e) {
				e.printStackTrace();
			}
			/* Ponemos la fuente en cada celda */
			if (openSansEmoji != null)
				cell.setFont(javafx.scene.text.Font.font(java.awt.Font.MONOSPACED, 35));

			text.wrappingWidthProperty().bind(tweetsColumn.widthProperty());
			text.textProperty().bind(cell.itemProperty());
			return cell;
		});
		tweetsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getText()));
		tweetsTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> setSelectedQueryResult(newValue));
	}

	/**
	 * @return the tweetsTable
	 */
	public TableView<Tweet> getTweetsTable() {
		return tweetsTable;
	}

	/**
	 * @param tweetsTable the tweetsTable to set
	 */
	public void setTweetsTable(TableView<Tweet> tweetsTable) {
		this.tweetsTable = tweetsTable;
	}

	/**
	 * @return the tweetObservableList
	 */
	public ObservableList<Tweet> getTweetObservableList() {
		return tweetObservableList;
	}

	/**
	 * @param tweetObservableList the tweetObservableList to set
	 */
	public void setTweetObservableList(ObservableList<Tweet> tweetObservableList) {
		this.tweetObservableList = tweetObservableList;
	}

	/**
	 * @return the selectedQueryResult
	 */
	public Tweet getSelectedQueryResult() {
		return selectedQueryResult;
	}

	/**
	 * @param selectedQueryResult the selectedQueryResult to set
	 */
	public void setSelectedQueryResult(Tweet selectedQueryResult) {
		this.selectedQueryResult = selectedQueryResult;
		if (selectedQueryResult != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			authorLabel.setText(selectedQueryResult.getUserScreenName());
			dateLabel.setText(df.format(selectedQueryResult.getCreatedAt()));
			idLabel.setText(new String("" + selectedQueryResult.getId()));
			Locale loc = new Locale(selectedQueryResult.getLang());
			langLabel.setText(loc.getDisplayLanguage(loc));
		} else {
			authorLabel.setText("");
			dateLabel.setText("");
			idLabel.setText("");
			langLabel.setText("");
		}
	}

	/**
	 * @return the tweetsColumn
	 */
	public TableColumn<Tweet, String> getTweetsColumn() {
		return tweetsColumn;
	}

	/**
	 * @param tweetsColumn the tweetsColumn to set
	 */
	public void setTweetsColumn(TableColumn<Tweet, String> tweetsColumn) {
		this.tweetsColumn = tweetsColumn;
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
		this.getTweetsTable().setItems(this.tweetObservableList);
		authorLabel.setText("");
		dateLabel.setText("");
		idLabel.setText("");
		langLabel.setText("");
	}

	/**
	 * @param filterList the filterList to set
	 */
	public void setFilterList(ObservableList<Filter> filterList) {
	}

	public void executeQuery() throws TwitterException {
		twitterextractor = new TwitterExtractor(null,
				this.getMainApplication().getCurrentUser().getCredentialList().get(0));
		UpdateExtractionTask updateTask = new UpdateExtractionTask(twitterextractor, extraction);
		updateTask.setOnSucceeded(e -> {
			if (loadingDialog != null)
				loadingDialog.close();
		});
		updateTask.setOnFailed(e -> {
			if (loadingDialog != null)
				loadingDialog.close();
		});
		Thread thread = new Thread(updateTask);
		thread.setName(updateTask.getClass().getCanonicalName());
		thread.start();
		loadingDialog = mainApplication.showLoadingDialog("Extracting");
		loadingDialog.showAndWait();
	}

	@FXML
	public void handleCancel() {
		this.mainApplication.showHomeScreen();
	}

	@FXML
	public void handleDelete() {
		if (this.selectedQueryResult == null) {
			ErrorDialog.showErrorNoTweetSelected();
			return;
		}
		this.getExtraction().getTweetList().remove(selectedQueryResult);
		TweetService tweetService = new TweetService();
		tweetService.delete(selectedQueryResult.getIdDB());
		;
		tweetObservableList.remove(selectedQueryResult);
	}

	@FXML
	public void handleUpdateExtraction() {
		twitterextractor = new TwitterExtractor(null,
				this.getMainApplication().getCurrentUser().getCredentialList().get(0));
		Task<UpdateStatus> updateTask = new UpdateExtractionTask(twitterextractor, extraction);
		updateTask.setOnSucceeded(e -> {
			UpdateStatus result = updateTask.getValue();
			if (result == null)
				return;
			if (result.getnTweets() > 0) {
				this.tweetObservableList.addAll(result.getTweetList());
				try {
					ExtractionService extractionService = new ExtractionService();
					extractionService.update(this.getExtraction());
				} catch (Exception ex) {
					if (loadingDialog != null)
						loadingDialog.close();
					ex.printStackTrace();
				}
			}
			if (loadingDialog != null)
				loadingDialog.close();
			alertUpdate=twitterextractor.onError();
			if(alertUpdate==null)alertUpdate = ErrorDialog.showUpdateQueryResults(result.getnTweets());

		});
		updateTask.setOnFailed(e -> {
			if (loadingDialog != null)
				loadingDialog.close();
		});
		Thread thread = new Thread(updateTask);
		thread.setName(updateTask.getClass().getCanonicalName());
		thread.start();
		loadingDialog = mainApplication.showLoadingDialog("Extracting...");
		loadingDialog.showAndWait();
		if (alertUpdate != null)
			alertUpdate.showAndWait();
	}

	/**
	 * @return the extraction
	 */
	public Extraction getExtraction() {
		return extraction;
	}

	public void refreshTweetObservableList() {
		if (extraction != null && extraction.getFilterList() != null) {
			LoadTweetsTask loadTask = new LoadTweetsTask(extraction);
			loadTask.setOnSucceeded(e -> {
				this.tweetObservableList.clear();
				this.tweetObservableList.setAll(extraction.getTweetList());
				if (loadingDialog != null)
					loadingDialog.close();

			});
			loadTask.setOnFailed(e -> {
				if (loadingDialog != null)
					loadingDialog.close();
			});
			Thread thread = new Thread(loadTask);
			thread.setName(loadTask.getClass().getCanonicalName());
			thread.start();
			loadingDialog = mainApplication.showLoadingDialog("Loading tweets...");
			loadingDialog.showAndWait();
		}
	}

	/**
	 * @param extraction the extraction to set
	 */
	public void setExtraction(Extraction extraction) {
		this.extraction = extraction;
	}
	@FXML
	public void handleExport() {
		FileChooser fileChooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        // Show save file dialog
        File file = fileChooser.showSaveDialog(this.mainApplication.getPrimaryStage());
        if (file != null) {
        	try {
				XMLManager.saveTweetListToFile(extraction, file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
