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
public class FilterTo implements Filter {
	private final static Integer ID=Constants.INTEGER_FILTER_TO;
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_TO);
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
	public FilterTo() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public StringProperty getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

}
