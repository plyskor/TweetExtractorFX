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
public class FilterMention implements Filter {
	private final static Integer ID=Constants.INTEGER_FILTER_MENTION;
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_MENTION);
	private StringProperty summary=new SimpleStringProperty("");
	private ObservableList<String> mentionList=FXCollections.observableArrayList();
	private String summaryString= new String("Menciona a: ");;
	public FilterMention(FilterMention filter) {
		if(filter!=null) {
			for(String word:filter.getMentionList()){
				mentionList.add(word);
			}
			summaryString=filter.getSummary().get();
			summary.set(filter.getSummary().get());
		}
	}
	/**
	 * @return the mentionList
	 */
	public ObservableList<String> getMentionList() {
		return mentionList;
	}

	/**
	 * @param mentionList the mentionList to set
	 */
	public void setMentionList(ObservableList<String> mentionList) {
		this.mentionList = mentionList;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary.set(summary);
	}


	public FilterMention() {
		
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
	public void addMention(String mention) {
		if(mentionList.isEmpty()) {
			summaryString=summaryString.concat("@"+mention);
			summary.set(summaryString);
		}else {
			summaryString=summaryString.concat(", @"+mention);
			summary.set(summaryString);
		}
		mentionList.add(mention);
	}

}
