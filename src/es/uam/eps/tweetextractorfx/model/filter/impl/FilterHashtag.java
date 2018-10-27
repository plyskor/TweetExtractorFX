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
	@XmlTransient
	private StringProperty summary=new SimpleStringProperty();
	@XmlTransient
	private String summaryString= new String("Hashtags: ");;
	
	private List<String> hashtagXmlList=new ArrayList<String>();
	public FilterHashtag(FilterHashtag filter) {
		this.setLABEL(Constants.STRING_FILTER_HASHTAG);
		if(filter!=null) {
			for(String word:filter.getHashtagList()){
				hashtagList.add(word);
				hashtagXmlList.add(word);
			}
			summaryString=filter.getSummary().get();
			summary.set(filter.getSummary().get());
		}
	}
	/**
	 * 
	 */
	public FilterHashtag() {
		this.setLABEL(Constants.STRING_FILTER_HASHTAG);
	}


	@XmlTransient
	@Override
	public StringProperty getSummary() {
		return summary;
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
	 * @return the summaryString
	 */
	@XmlTransient
	public String getSummaryString() {
		return summaryString;
	}

	/**
	 * @param summaryString the summaryString to set
	 */
	public void setSummaryString(String summaryString) {
		this.summaryString = summaryString;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary.set(summary);
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
	 * @param summary the summary to set
	 */
	public void setSummary(StringProperty summary) {
		this.summary = summary;
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
		summaryString=summaryString.concat("#"+hashtag);
		summary.set(summaryString);
	}else {
		summaryString=summaryString.concat(", #"+hashtag);
		summary.set(summaryString);
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
