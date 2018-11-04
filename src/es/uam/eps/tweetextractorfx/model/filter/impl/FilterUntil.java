/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.Constants.FilterTypes;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import es.uam.eps.tweetextractorfx.util.LocalDateAdapter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
@Entity
@DiscriminatorValue(value=FilterTypes.Values.TYPE_FILTER_UNTIL)
@XmlRootElement(name="filterUntil")
public class FilterUntil extends Filter {
	@Column(name="until_date")
	private LocalDate date;
	public FilterUntil(FilterUntil filter) {
		this.setLABEL(Constants.STRING_FILTER_UNTIL);
		if(filter!=null) {
			this.date=filter.getDate();
			this.summary=filter.getSummary();
			this.summaryProperty.set(filter.getSummary());
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
	public StringProperty getSummaryProperty() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(date!=null) {
			summaryProperty.set("Until: "+(date).format(formatter));
			summary= new String(summaryProperty.get());
		}	
		return summaryProperty;
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
