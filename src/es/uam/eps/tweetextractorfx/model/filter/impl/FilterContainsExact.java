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
@XmlRootElement(name="filterContainsExact")
public class FilterContainsExact extends Filter {
	
	@XmlTransient
	private ObservableList<String> keywordsList=FXCollections.observableArrayList();
	private List<String>keywordXmlList = new ArrayList<String>();
	public FilterContainsExact() {
		this.summary=new String("Contiene exactamente: ");
		this.setLABEL(Constants.STRING_FILTER_CONTAINS_EXACT);
	}
	
	public FilterContainsExact(FilterContainsExact filter) {
		this.summary=new String("Contiene exactamente: ");
		this.setLABEL(Constants.STRING_FILTER_CONTAINS_EXACT);
		if(filter!=null) {
			for(String word:filter.getKeywordsList()){
				keywordsList.add(word);
				keywordXmlList.add(word);
			}
			summary=filter.getSummary();
			summaryProperty.set(filter.getSummary());
		}
	}

	/**
	 * @return the keywordsList
	 */
	@XmlTransient
	public ObservableList<String> getKeywordsList() {
		return keywordsList;
	}


	/**
	 * @return the keywordXmlList
	 */
	@XmlElementWrapper(name = "expressionList")
    @XmlElement(name = "expression")
	public List<String> getKeywordXmlList() {
		return keywordXmlList;
	}
	/**
	 * @param keywordXmlList the keywordXmlList to set
	 */
	public void setKeywordXmlList(List<String> keywordXmlList) {
		this.keywordXmlList = keywordXmlList;
		if(keywordXmlList!=null) {
			for(String expression:keywordXmlList) {
				this.addKeywordWord(expression);
			}
		}
	}

	/**
	 * @param keywordsList the keywordsList to set
	 */
	public void setKeywordsList(ObservableList<String> keywordsList) {
		this.keywordsList = keywordsList;
	}

	
	public void addKeywordWord(String word) {
		loadKeywordWord(word);
		keywordXmlList.add(word);
	}
	public void loadKeywordWord(String word) {
		if(keywordsList.isEmpty()) {
			summary=summary.concat("\""+word+"\"");
			summaryProperty.set(summary);
		}else {
			summary=summary.concat(", \""+word+"\"");
			summaryProperty.set(summary);
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

	@Override
	public void loadXml() {
		for(String expression:keywordXmlList) {
			loadKeywordWord(expression);
		}
	}

	
}
