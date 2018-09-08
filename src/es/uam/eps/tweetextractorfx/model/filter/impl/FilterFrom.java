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
public class FilterFrom implements Filter {

	private final static Integer ID=Constants.INTEGER_FILTER_FROM;
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_FROM);
	private StringProperty nickName= new SimpleStringProperty();
	private StringProperty summary=new SimpleStringProperty();
	private String summaryString = new String("Tweeteado por: @");

	public FilterFrom(FilterFrom filter) {
		if(filter!=null) {
			summaryString=filter.getSummary().get();
			summary.set(filter.getSummary().get());
			this.nickName.set(filter.getNickName().get());
		}
	}
	/**
	 * @return the nickName
	 */
	public StringProperty getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName.set(nickName);
		summaryString=summaryString.concat(nickName);
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

	/**
	 * 
	 */
	public FilterFrom() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public StringProperty getSummary() {
		return summary;
	}

}
