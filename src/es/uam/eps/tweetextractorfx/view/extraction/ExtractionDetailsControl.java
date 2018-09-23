/**
 * 
 */
package es.uam.eps.tweetextractorfx.view.extraction;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import es.uam.eps.tweetextractorfx.MainApplication;
import es.uam.eps.tweetextractorfx.error.ErrorDialog;
import es.uam.eps.tweetextractorfx.model.Extraction;
import es.uam.eps.tweetextractorfx.model.Tweet;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import es.uam.eps.tweetextractorfx.twitterapi.TwitterExtractor;
import es.uam.eps.tweetextractorfx.util.FilterManager;
import es.uam.eps.tweetextractorfx.util.XMLManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import twitter4j.Status;
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
		this.getTweetsTable().setItems(extraction.getTweetList());
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

	/**
	 * @param queryResult the queryResult to set
	 */
	public void setQueryResult(List<Tweet> queryResult) {
		if (queryResult != null) {
			this.getExtraction().getTweetList().clear();
			for (Tweet tweet : queryResult) {
				this.getExtraction().getTweetList().add(tweet);
			}
		}
	}

	public void executeQuery() throws TwitterException {
		twitterextractor=new TwitterExtractor(null, this.getMainApplication().getCurrentUser().getCredentialList().get(0));
		twitterextractor.setQuery(FilterManager.getQueryFromFilters(extraction.getFilterList()) + "-filter:retweets");
		if (twitterextractor != null&& twitterextractor.getQuery() != null) {
			try {
				this.setQueryResult(twitterextractor.execute());
			} catch (TwitterException e) {
				e.printStackTrace();
				throw (e);
			}
		}
		try {
			XMLManager.saveExtraction(extraction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	@FXML
	public void handleCancel() {
		this.mainApplication.showHomeScreen();
	}
	@FXML
	public void handleUpdateExtraction() {
		try {
			twitterextractor=new TwitterExtractor(null, this.getMainApplication().getCurrentUser().getCredentialList().get(0));
			int added=twitterextractor.updateExtraction(extraction);
			ErrorDialog.showUpdateQueryResults(added);
			XMLManager.saveExtraction(extraction);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} 
	}

	/**
	 * @return the extraction
	 */
	public Extraction getExtraction() {
		return extraction;
	}

	/**
	 * @param extraction the extraction to set
	 */
	public void setExtraction(Extraction extraction) {
		this.extraction = extraction;
	}

}
