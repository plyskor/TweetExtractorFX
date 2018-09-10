/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class FilterUrl implements Filter {
	private final static Integer ID=Constants.INTEGER_FILTER_URL;
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_URL);
	private StringProperty keyWord= new SimpleStringProperty();
	private StringProperty summary=new SimpleStringProperty();
	private String summaryString = new String("Con una URL que contiene: ");
	/**
	 * 
	 */
	public FilterUrl() {

	}
	public FilterUrl(FilterUrl filter) {
		if(filter!=null) {
			summaryString=filter.getSummary().get();
			summary.set(filter.getSummary().get());
			this.keyWord.set(filter.getKeyWord().get());
		}
	}
	/**
	 * @return the keyWord
	 */
	public StringProperty getKeyWord() {
		return keyWord;
	}

	/**
	 * @param keyWord the keyWord to set
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord.set(keyWord);
		summaryString=summaryString.concat("'"+keyWord+"'");
		summary.set(summaryString);
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
	@Override
	public String toQuery() {
		if(keyWord==null) {
			return null;
		}else {
			return new String("url:"+keyWord+" ");
		}
	}

}
