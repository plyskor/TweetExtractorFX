package es.uam.eps.tweetextractor.model.filter.impl;

import es.uam.eps.tweetextractor.model.Constants;
import es.uam.eps.tweetextractor.model.filter.Filter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * @author Jose Antonio García del Saz
 *
 */
public class FilterContainsExact implements Filter {
	private final static Integer ID=Constants.INTEGER_FILTER_CONTAINS_EXACT;
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_CONTAINS_EXACT);

	public FilterContainsExact() {
		
	}

}
