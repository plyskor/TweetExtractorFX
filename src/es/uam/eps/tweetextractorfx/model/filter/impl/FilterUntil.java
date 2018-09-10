/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class FilterUntil implements Filter {
	private final static Integer ID=Constants.INTEGER_FILTER_UNTIL;
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_UNTIL);
	private StringProperty summary=new SimpleStringProperty();
	private LocalDate date;
	public FilterUntil(FilterUntil filter) {
		if(filter!=null) {
			this.date=filter.getDate();
		}
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
	public FilterUntil() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public StringProperty getSummary() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(date!=null) {
			summary.set("Hasta: "+(date).format(formatter));
		}	
		return summary;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary.set(summary);
	}
	@Override
	public String toQuery() {
		if(date==null) {
			return null;
		}else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return new String("until:"+(date).format(formatter)+" ");
		}
	}

}
