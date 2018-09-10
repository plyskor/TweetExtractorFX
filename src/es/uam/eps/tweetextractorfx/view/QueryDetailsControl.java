/**
 * 
 */
package es.uam.eps.tweetextractorfx.view;

import com.gluonhq.charm.glisten.control.CardPane;
import com.gluonhq.charm.glisten.control.CardCell;

import es.uam.eps.tweetextractorfx.MainApplication;
import es.uam.eps.tweetextractorfx.model.filter.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class QueryDetailsControl {
	private MainApplication mainApplication;

	@FXML
	private CardPane<Filter> tweets= new CardPane<>();
	private ObservableList<Filter> addedFiltersList ;
	
	/**
	 * 
	 */
	public QueryDetailsControl() {
		super();
		initialize();
	}

	private void initialize() {
		addedFiltersList= FXCollections.observableArrayList();
		tweets.setCellFactory(p -> new CardCell<Filter>() {

	        private final FilterCard card;
	        {
	            card = new FilterCard();
	        }

	        @Override
	        public void updateItem(Filter item, boolean empty) {
	            super.updateItem(item, empty);
	            if (item != null && !empty) {
	                card.setText(item.getSummary().get());
	                setGraphic(card);
	                setText(null);
	            } else {
	                setGraphic(null);
	                setText(null);
	            }
	        }
	    });
	}

	protected Node createContent(Filter item) {
		//Node ret = new Node();
		return null;
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
	}

	/**
	 * @return the tweets
	 */
	public CardPane<Filter> getTweets() {
		return tweets;
	}

	/**
	 * @param tweets the tweets to set
	 */
	public void setTweets(CardPane<Filter> tweets) {
		this.tweets = tweets;
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
		tweets.getItems().addAll(addedFiltersList);
	}

}
