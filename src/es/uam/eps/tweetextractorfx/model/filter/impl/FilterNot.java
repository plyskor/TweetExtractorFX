/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import es.uam.eps.tweetextractorfx.model.Constants.FilterTypes;
import es.uam.eps.tweetextractorfx.model.filter.Filter;


/**
 * @author Jose Antonio García del Saz
 *
 */
@Entity
@DiscriminatorValue(value=FilterTypes.Values.TYPE_FILTER_NOT)
@XmlRootElement(name = "filterNot")
public class FilterNot extends Filter {
	@OneToOne(cascade=CascadeType.ALL,optional=true)
	@JoinColumn(nullable=true)
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
