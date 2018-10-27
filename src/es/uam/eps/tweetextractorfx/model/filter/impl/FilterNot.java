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
	private StringProperty summary=new SimpleStringProperty();
	private String summaryString= new String("");;
	/**
	 * 
	 */
	public FilterNot() {
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
		this.setSummaryString("NOT ("+filter.getSummary().get()+")");
		this.setSummary(summaryString);
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

	/* (non-Javadoc)
	 * @see es.uam.eps.tweetextractorfx.model.filter.Filter#getSummary()
	 */
	@Override
	public StringProperty getSummary() {
		return summary;
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
