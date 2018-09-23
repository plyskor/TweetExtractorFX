/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import es.uam.eps.tweetextractorfx.util.DateAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Jose Antonio García del Saz
 *
 */

@XmlRootElement(name = "extraction")
@XmlSeeAlso({ es.uam.eps.tweetextractorfx.model.filter.impl.FilterHashtag.class,
		es.uam.eps.tweetextractorfx.model.filter.impl.FilterContains.class,
		es.uam.eps.tweetextractorfx.model.filter.impl.FilterContainsExact.class,
		es.uam.eps.tweetextractorfx.model.filter.impl.FilterList.class,
		es.uam.eps.tweetextractorfx.model.filter.impl.FilterFrom.class })
@XmlType(propOrder={"id","creationDate","lastModificationDate","filterXmlList"})
public class Extraction {

	/**
	 * 
	 */
	private String id;
	private Date creationDate;
	private Date lastModificationDate;
	@XmlTransient
	private ObservableList<Tweet> tweetList;
	@XmlTransient
	private ObservableList<Filter> filterList;
	
	private List<Filter> filterXmlList = new ArrayList<Filter>();

	public Extraction() {
		creationDate = new Date();
		lastModificationDate = new Date();
		tweetList = FXCollections.observableArrayList();
		filterList = FXCollections.observableArrayList();
		id = UUID.randomUUID().toString();
	}

	public int howManyTweets() {
		if (tweetList == null) {
			return 0;
		} else {
			return tweetList.size();
		}
	}

	/**
	 * @return the creationDate
	 */
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creatingDate) {
		this.creationDate = creatingDate;
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
		if (list != null) {
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
		if (tweet != null) {
			tweetList.add(tweet);
		}
	}

	/**
	 * @param list the list of filters to add
	 */
	public void addFilters(List<Filter> list) {
		if (list != null) {
			filterList.addAll(list);
			filterXmlList.addAll(list);
		}
	}

	/**
	 * @return the filterXmlList
	 */
	@XmlElementWrapper(name="filters")
	@XmlAnyElement(lax = true)
	public List<Filter> getFilterXmlList() {
		return filterXmlList;
	}

	/**
	 * @param filterXmlList the filterXmlList to set
	 */
	public void setFilterXmlList(List<Filter> filterXmlList) {
		this.filterXmlList = filterXmlList;
	}

	/**
	 * @param filter the filter to add
	 */
	public void addFilter(Filter filter) {
		if (filter != null) {
			filterList.add(filter);
			filterXmlList.add(filter);
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
	public void loadFiltersXml() {
	for(Filter filter:filterXmlList) {
		filterList.add(filter);
	}
	}
}
