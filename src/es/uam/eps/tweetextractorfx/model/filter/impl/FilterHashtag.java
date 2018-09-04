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
public class FilterHashtag implements Filter {

	private final static Integer ID=Constants.INTEGER_FILTER_HASHTAG;
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_HASHTAG);

	/**
	 * 
	 */
	public FilterHashtag() {
		// TODO Auto-generated constructor stub
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

}
