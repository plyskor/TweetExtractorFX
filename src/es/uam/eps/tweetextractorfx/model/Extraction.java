/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

import java.time.LocalDate;
import java.util.List;

import es.uam.eps.tweetextractorfx.model.filter.Filter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import twitter4j.Status;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class Extraction {

	/**
	 * 
	 */
	private LocalDate creatingDate;
	private LocalDate lastModificationDate;
	private ObservableList<Status> tweetList;
	private ObservableList<Filter> filterList;
	
	
	public Extraction() {
		creatingDate=LocalDate.now();
		lastModificationDate=LocalDate.now();
		tweetList= FXCollections.observableArrayList();
		filterList= FXCollections.observableArrayList();
		
	}
	public int howManyTweets() {
		if(tweetList==null) {
			return 0;
		}else {
			return tweetList.size();
		}
	}
	/**
	 * @return the creatingDate
	 */
	public LocalDate getCreatingDate() {
		return creatingDate;
	}
	/**
	 * @param creatingDate the creatingDate to set
	 */
	public void setCreatingDate(LocalDate creatingDate) {
		this.creatingDate = creatingDate;
	}
	/**
	 * @return the lastModificationDate
	 */
	public LocalDate getLastModificationDate() {
		return lastModificationDate;
	}
	/**
	 * @param lastModificationDate the lastModificationDate to set
	 */
	public void setLastModificationDate(LocalDate lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}
	/**
	 * @return the tweetList
	 */
	public ObservableList<Status> getTweetList() {
		return tweetList;
	}
	/**
	 * @param tweetList the tweetList to set
	 */
	public void setTweetList(ObservableList<Status> tweetList) {
		this.tweetList = tweetList;
	}
	/**
	 * @param list the list of tweets to add
	 */
	public void addTweets(List<Status> list) {
		if(list!=null) {
			tweetList.addAll(list);
		}
	}
	/**
	 * @param tweet the tweet to add
	 */
	public void addTweet(Status tweet) {
		if(tweet!=null) {
			tweetList.add(tweet);
		}
	}
	/**
	 * @param list the list of filters to add
	 */
	public void addFilters(List<Filter> list) {
		if(list!=null) {
			filterList.addAll(list);
		}
	}
	/**
	 * @param filter the filter to add
	 */
	public void addFilter(Filter filter) {
		if(filter!=null) {
			filterList.add(filter);
		}
	}
	/**
	 * @return the filterList
	 */
	public ObservableList<Filter> getFilterList() {
		return filterList;
	}
	/**
	 * @param filterList the filterList to set
	 */
	public void setFilterList(ObservableList<Filter> filterList) {
		this.filterList = filterList;
	}
	
}
