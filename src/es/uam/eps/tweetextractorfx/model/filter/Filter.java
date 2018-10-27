/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import es.uam.eps.tweetextractorfx.model.Constants.FilterTypes;
import es.uam.eps.tweetextractorfx.model.Extraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
@Entity
@Table(name="perm_fiter")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "filter_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Filter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "identifier")
	private int idDB;
	@XmlTransient
	@Column(name = "filter_type", length=5,nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	public FilterTypes filterType;
	@Transient
	private StringProperty LABEL;
	@ManyToOne
	@XmlTransient
	private Extraction extraction;
	public Filter(){
		LABEL = new SimpleStringProperty();
	}
	/**
	 * @return the lABEL
	 */
	public StringProperty getLABEL() {
		return LABEL;
	}

	/**
	 * @param lABEL the lABEL to set
	 */
	public void setLABEL(String lABEL) {
		if (lABEL != null)
			LABEL.set(lABEL);
	}

	public abstract StringProperty getSummary();

	/**
	 * @return the query
	 */

	public abstract String toQuery();

	/**
	 * @return the idDB
	 */
	public int getIdDB() {
		return idDB;
	}
	/**
	 * @param idDB the idDB to set
	 */
	public void setIdDB(int idDB) {
		this.idDB = idDB;
	}
	/**
	 * @return the filterType
	 */
	public FilterTypes getFilterType() {
		return filterType;
	}
	/**
	 * @param filterType the filterType to set
	 */
	public void setFilterType(FilterTypes filterType) {
		this.filterType = filterType;
	}
	/**
	 * @return the extraction
	 */
	@XmlTransient
	public Extraction getExtraction() {
		return extraction;
	}
	/**
	 * @param extraction the extraction to set
	 */
	public void setExtraction(Extraction extraction) {
		this.extraction = extraction;
	}
	/**
	 * @param lABEL the lABEL to set
	 */
	public void setLABEL(StringProperty lABEL) {
		LABEL = lABEL;
	}
	public abstract void loadXml();
}
