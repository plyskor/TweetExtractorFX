/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Jose Antonio García del Saz
 *
 */
@XmlRootElement(name="filterHashtag")
public class FilterHashtag extends Filter {
	@XmlTransient
	private ObservableList<String> hashtagList=FXCollections.observableArrayList();

	private List<String> hashtagXmlList=new ArrayList<String>();
	public FilterHashtag(FilterHashtag filter) {
		this.summary=new String("Hashtags: ");
		this.setLABEL(Constants.STRING_FILTER_HASHTAG);
		if(filter!=null) {
			for(String word:filter.getHashtagList()){
				hashtagList.add(word);
				hashtagXmlList.add(word);
			}
			summary=filter.getSummary();
			summaryProperty.set(filter.getSummary());
		}
	}
	/**
	 * 
	 */
	public FilterHashtag() {
		this.summary=new String("Hashtags: ");
		this.setLABEL(Constants.STRING_FILTER_HASHTAG);
	}


	/**
	 * @return the hashtagList
	 */
	@XmlTransient
	public ObservableList<String> getHashtagList() {
		return hashtagList;
	}

	/**
	 * @param hashtagList the hashtagList to set
	 */
	public void setHashtagList(ObservableList<String> hashtagList) {
		this.hashtagList = hashtagList;
	}
	
	/**
	 * @return the hashtagXmlList
	 */
	@XmlElementWrapper(name = "hashtagList")
    @XmlElement(name = "hashtag")
	public List<String> getHashtagXmlList() {
		return hashtagXmlList;
	}
	/**
	 * @param hashtagXmlList the hashtagXmlList to set
	 */
	public void setHashtagXmlList(List<String> hashtagXmlList) {
		this.hashtagXmlList = hashtagXmlList;
		if(hashtagXmlList!=null) {
			for(String hashtag: hashtagXmlList) {
				addHashtag(hashtag);
			}
		}
	}
	
	/**
	 * @param hashtag the hashtag to add
	 */
	public void addHashtag(String hashtag) {
		loadHashtag(hashtag);
		hashtagXmlList.add(hashtag);
	}
public void loadHashtag(String hashtag) {
	if(hashtagList.isEmpty()) {
		summary=summary.concat("#"+hashtag);
		summaryProperty.set(summary);
	}else {
		summary=summary.concat(", #"+hashtag);
		summaryProperty.set(summary);
	}
	hashtagList.add(hashtag);
}
	@Override
	public String toQuery() {
		if(hashtagList==null) {
			return null;
		}else {
			String ret=new String("");
			for(String hashtag:hashtagList) {
				ret=ret.concat("#"+hashtag+" ");
			}
			return ret;
		}
	}
	@Override
	public void loadXml() {
		for(String hashtag:hashtagXmlList) {
			loadHashtag(hashtag);
		}		
	}
}
