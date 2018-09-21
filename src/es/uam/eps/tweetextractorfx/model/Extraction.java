/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.uam.eps.tweetextractorfx.model.filter.Filter;
import es.uam.eps.tweetextractorfx.util.DateAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Jose Antonio García del Saz
 *
 */
@XmlRootElement(name="extraction")
public class Extraction {

	/**
	 * 
	 */
	private String id; 
	private Date creatingDate;
	private Date lastModificationDate;
	@XmlTransient
	private ObservableList<Tweet> tweetList;
	@XmlTransient
	private ObservableList<Filter> filterList;
	
	
	public Extraction() {
		creatingDate=new Date();
		lastModificationDate=new Date();
		tweetList= FXCollections.observableArrayList();
		filterList= FXCollections.observableArrayList();
		id = UUID.randomUUID().toString();
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
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getCreatingDate() {
		return creatingDate;
	}
	/**
	 * @param creatingDate the creatingDate to set
	 */
	public void setCreatingDate(Date creatingDate) {
		this.creatingDate = creatingDate;
	}
	/**
	 * @return the lastModificationDate
	 */
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getLastModificationDate() {
		return lastModificationDate;
	}
	/**
	 * @param lastModificationDate the lastModificationDate to set
	 */
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}
	/**
	 * @return the tweetList
	 */
	@XmlTransient
	public ObservableList<Tweet> getTweetList() {
		return tweetList;
	}
	/**
	 * @param tweetList the tweetList to set
	 */
	public void setTweetList(ObservableList<Tweet> tweetList) {
		this.tweetList = tweetList;
	}
	/**
	 * @param list the list of tweets to add
	 */
	public void addTweets(List<Tweet> list) {
		if(list!=null) {
			tweetList.addAll(list);
		}
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param tweet the tweet to add
	 */
	public void addTweet(Tweet tweet) {
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
	@XmlTransient
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
