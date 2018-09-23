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
@XmlRootElement(name="filterContains")
public class FilterContains implements Filter {
	@XmlTransient
	private final static Integer ID=Constants.INTEGER_FILTER_CONTAINS;
	@XmlTransient
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_CONTAINS);
	@XmlTransient
	private ObservableList<String> keywordsList=FXCollections.observableArrayList();
	@XmlTransient
	private StringProperty summary=new SimpleStringProperty();
	private List<String>keywordsXmlList=new ArrayList<String>();
	@XmlTransient
	private String summaryString= new String("Contiene: ");;
	public FilterContains() {
		
	}

	public FilterContains(FilterContains filter) {
		if(filter!=null) {
			for(String word:filter.getKeywordsList()){
				keywordsList.add(word);
				keywordsXmlList.add(word);
			}
			summaryString=filter.getSummary().get();
			summary.set(filter.getSummary().get());
		}
	}

	/**
	 * @return the id
	 */
	@XmlTransient
	public  Integer getId() {
		return ID;
	}

	/**
	 * @return the label
	 */
	@XmlTransient
	public  StringProperty getLabel() {
		return LABEL;
	}

	/**
	 * @return the keywordsList
	 */
	@XmlTransient
	public ObservableList<String> getKeywordsList() {
		return keywordsList;
	}

	/**
	 * @param keywordsList the keywordsList to set
	 */
	public void setKeywordsList(ObservableList<String> keywordsList) {
		this.keywordsList = keywordsList;
	}

	/**
	 * @return the keywordsXmlList
	 */
	@XmlElementWrapper(name = "keywordList")
    @XmlElement(name = "keyword")
	public List<String> getKeywordsXmlList() {
		return keywordsXmlList;
	}

	/**
	 * @param keywordsXmlList the keywordsXmlList to set
	 */
	public void setKeywordsXmlList(List<String> keywordsXmlList) {
		this.keywordsXmlList = keywordsXmlList;
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
	public void setSummary(StringProperty summary) {
		this.summary = summary;
	}
	@XmlTransient
	@Override
	public StringProperty getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary.set(summary);
	}
	
	public void addKeywordWord(String word) {
		loadKeywordWord(word);
		keywordsXmlList.add(word);
	}
	public void loadKeywordWord(String word) {
		if(keywordsList.isEmpty()) {
			summaryString=summaryString.concat(word);
			summary.set(summaryString);
		}else {
			summaryString=summaryString.concat(", "+word);
			summary.set(summaryString);
		}
		keywordsList.add(word);
	}
	@Override
	public String toQuery() {
		String ret = new String("");
		if(keywordsList!=null) {
			for(String keyWord:keywordsList) {
				ret=ret.concat(keyWord+" ");
			}
			return ret;
		}else {
			return null;
		}	
	}

	@Override
	public void loadXml() {
		for(String keyWord:keywordsXmlList) {
			loadKeywordWord(keyWord);
		}
	}

}