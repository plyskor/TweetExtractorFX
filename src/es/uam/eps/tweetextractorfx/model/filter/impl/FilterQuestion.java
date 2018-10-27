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
public class FilterQuestion extends Filter {
	/**
	 * 
	 */
	public FilterQuestion() {
		this.setLABEL(Constants.STRING_FILTER_QUESTION);	
		}

	@Override
	public StringProperty getSummary() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toQuery() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void loadXml() {
		// TODO Auto-generated method stub
		
	}

}
