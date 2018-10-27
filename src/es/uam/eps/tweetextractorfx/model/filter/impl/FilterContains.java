/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;


import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.Constants.FilterTypes;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Jose Antonio García del Saz
 *
 */
@Entity
@DiscriminatorValue(value=FilterTypes.Values.TYPE_FILTER_CONTAINS)
@XmlRootElement(name="filterContains")
public class FilterContains extends Filter {
	@XmlTransient
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	@CollectionTable(name="perm_filter_contains_keyword_list", joinColumns=@JoinColumn(name="filter"))
	@Column(name="keyword_list", length=20)
	private List<String> keywordsList=FXCollections.observableArrayList();
	@Transient
	@XmlTransient
	private StringProperty summary=new SimpleStringProperty();
	@Column(name="summary")
	@XmlTransient
	private String summaryString= new String("Contiene: ");;
	public FilterContains() {
		this.setLABEL(Constants.STRING_FILTER_CONTAINS);
	}

	public FilterContains(FilterContains filter) {
		if(filter!=null) {
			for(String word:filter.getKeywordsList()){
				keywordsList.add(word);
			}
			summaryString=filter.getSummary().get();
			summary.set(filter.getSummary().get());
		}
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
	public List<String> getKeywordsList() {
		return keywordsList;
	}

	/**
	 * @param keywordsXmlList the keywordsXmlList to set
	 */
	public void setKeywordsList(List<String> keywordsXmlList) {
		this.keywordsList = keywordsXmlList;
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
		for(String keyWord:keywordsList) {
			loadKeywordWord(keyWord);
		}
	}

}