/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

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
public class FilterHashtag implements Filter {

	private final static Integer ID=Constants.INTEGER_FILTER_HASHTAG;
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_HASHTAG);
	private ObservableList<String> hashtagList=FXCollections.observableArrayList();
	private StringProperty summary=new SimpleStringProperty();
	private String summaryString= new String("Hashtags: ");;
	
	public FilterHashtag(FilterHashtag filter) {
		if(filter!=null) {
			for(String word:filter.getHashtagList()){
				hashtagList.add(word);
			}
			summaryString=filter.getSummary().get();
			summary.set(filter.getSummary().get());
		}
	}
	/**
	 * 
	 */
	public FilterHashtag() {
		
	}

	/**
	 * @return the id
	 */
	public  Integer getId() {
		return ID;
	}

	/**
	 * @return the label
	 */
	public  StringProperty getLabel() {
		return LABEL;
	}

	@Override
	public StringProperty getSummary() {
		return summary;
	}

	/**
	 * @return the hashtagList
	 */
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
	 * @param hashtag the hashtag to add
	 */
	public void addHashtag(String hashtag) {
		if(hashtagList.isEmpty()) {
			summaryString=summaryString.concat(hashtag);
			summary.set(summaryString);
		}else {
			summaryString=summaryString.concat(", "+hashtag);
			summary.set(summaryString);
		}
		hashtagList.add(hashtag);
	}
}
