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
public class FilterNot extends Filter {
	private Filter filter;
	/**
	 * 
	 */
	public FilterNot() {
		this.summary=new String("");
		this.setLABEL("");
	}
	/**
	 * @return the filter
	 */
	public Filter getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(Filter filter) {
		this.filter = filter;
		this.setSummary("NOT ("+filter.getSummary()+")");
		this.summaryProperty.set(summary);
	}


	@Override
	public String toQuery() {
		if(filter==null) {
			return null;
		}else {
			return new String("-"+filter.toQuery());
		}
	}

	@Override
	public void loadXml() {
		// TODO Auto-generated method stub
		
	}

}
