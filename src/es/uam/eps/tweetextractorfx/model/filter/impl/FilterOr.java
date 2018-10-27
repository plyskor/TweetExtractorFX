/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * @author Jose Antonio García del Saz
 *
 */
@XmlRootElement(name="FilterOr")
public class FilterOr extends Filter {
	private List<Filter> filterList=new ArrayList<Filter>();
	@XmlTransient
	private StringProperty summary=new SimpleStringProperty();
	@XmlTransient
	private String summaryString= new String("");;

	/**
	 * 
	 */
	public FilterOr() {
		super();
		this.setLABEL("");
	}
	
	public void addAll(ObservableList<Filter> observableList) {
		if(observableList!=null) {
			for(int i=0;i<observableList.size();i++) {
				filterList.add(observableList.get(i));
				if(i==observableList.size()-1) {
					summaryString=summaryString.concat("("+observableList.get(i).getSummary().get() + ")");
				}else {
					summaryString=summaryString.concat("("+observableList.get(i).getSummary().get() + ") OR ");
				}
			}
			summary.set(summaryString);
		}
	}




	/* (non-Javadoc)
	 * @see es.uam.eps.tweetextractorfx.model.filter.Filter#getSummary()
	 */
	@XmlTransient
	@Override
	public StringProperty getSummary() {
		return summary;
	}

	/**
	 * @return the filterList
	 */
	@XmlElementWrapper(name = "filterOrList")
	@XmlAnyElement(lax = true)
	public List<Filter> getFilterList() {
		return filterList;
	}

	/**
	 * @param filterList the filterList to set
	 */
	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}

	/**
	 * @return the summaryString
	 */
	@XmlTransient
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
		this.summary.set(summary);;
	}

	@Override
	public String toQuery() {
		if(filterList==null) {
			return null;
		}else {
			String ret = new String("(");
			for(int i=0;i<filterList.size();i++) {
				if(i==filterList.size()-1) {
					ret=ret.concat(filterList.get(i).toQuery());
				}else {
					ret=ret.concat(filterList.get(i).toQuery()+" OR ");
				}
			}
			ret=ret.concat(") ");
			return ret;
		}
	}

	@Override
	public void loadXml() {
		// TODO Auto-generated method stub
		
	}

}
