/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter;

import javafx.beans.property.StringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
public interface Filter {
	/**
	 * @return the id
	 */
	public Integer getId();

	/**
	 * @return the label
	 */
	public StringProperty getLabel();
	/**
	 * @return the label
	 */
	public StringProperty getSummary();
	/**
	 * @return the query
	 */
	public String toQuery();
}
