/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import es.uam.eps.tweetextractorfx.util.LocalDateAdapter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
@XmlRootElement(name="filterUntil")
public class FilterUntil extends Filter {
	@XmlTransient
	private StringProperty summary=new SimpleStringProperty();
	private LocalDate date;
	public FilterUntil(FilterUntil filter) {
		this.setLABEL(Constants.STRING_FILTER_UNTIL);
		if(filter!=null) {
			this.date=filter.getDate();
		}
	}
	/**
	 * 
	 */
	public FilterUntil() {
		this.setLABEL(Constants.STRING_FILTER_UNTIL);
	}
	@XmlTransient
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
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
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
	@Override
	public void loadXml() {		
	}

}
