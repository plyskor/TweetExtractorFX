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
public class FilterContainsExact implements Filter {
	

	private final static Integer ID=Constants.INTEGER_FILTER_CONTAINS_EXACT;
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_CONTAINS_EXACT);
	private ObservableList<String> keywordsList=FXCollections.observableArrayList();
	private StringProperty summary=new SimpleStringProperty();
	private String summaryString= new String("Contiene exactamente: ");;
	public FilterContainsExact() {
		
	}
	public FilterContainsExact(FilterContainsExact filter) {
		if(filter!=null) {
			for(String word:filter.getKeywordsList()){
				keywordsList.add(word);
			}
			summaryString=filter.getSummary().get();
			summary.set(filter.getSummary().get());
		}
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

	/**
	 * @return the keywordsList
	 */
	public ObservableList<String> getKeywordsList() {
		return keywordsList;
	}

	/**
	 * @param keywordsList the keywordsList to set
	 */
	public void setKeywordsList(ObservableList<String> keywordsList) {
		this.keywordsList = keywordsList;
	}

	@Override
	public StringProperty getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary.set(summary);
	}
	
	public void addKeywordWord(String word) {
		if(keywordsList.isEmpty()) {
			summaryString=summaryString.concat("\""+word+"\"");
			summary.set(summaryString);
		}else {
			summaryString=summaryString.concat(", \""+word+"\"");
			summary.set(summaryString);
		}
		keywordsList.add(word);
	}
	@Override
	public String toQuery() {
		String ret =new String("");
		if(keywordsList!=null) {
			for(String expression:keywordsList) {
				ret=ret.concat("\""+expression+"\" ");
			}
			return ret;
		}else {
			return null;
		}
	}

	
}
