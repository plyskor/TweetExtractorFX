/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.Constants.FilterTypes;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
@Entity
@DiscriminatorValue(value=FilterTypes.Values.TYPE_FILTER_LIST)
@XmlRootElement(name="filterList")
public class FilterList extends Filter {
	@Column(name="list_name", length=30)
	private String listName= new String();
	@Column(name="list_account", length=60)
	private String account= new String();

	/**
	 * 
	 */
	public FilterList() {
		this.summary=new String("Tweeted by an account in the list ");
		this.setLABEL(Constants.STRING_FILTER_LIST);
	}
	public FilterList(FilterList filter) {
		this.summary=new String("Tweeted by an account in the list ");
		this.setLABEL(Constants.STRING_FILTER_LIST);
		if(filter!=null) {
			this.setListName(filter.getListName());
			this.setAccount(filter.getAccount());
			this.setSummary(filter.getSummary());
			this.summaryProperty.set(filter.getSummary());
		}
	}
	
	/**
	 * @return the listName
	 */
	@XmlTransient
	public String getListName() {
		return listName;
	}
	/**
	 * @param listName the listName to set
	 */
	public void setListName(String listName) {
		this.listName=listName;
		summary=summary.concat("'"+listName+"' of the account '"+account+"'");
		this.summaryProperty.set(summary);
	}
	/**
	 * @return the account
	 */
	@XmlTransient
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account=account;
	}

	@Override
	public String toQuery() {
		if(account==null||listName==null) {
			return null;
		}else {
			return new String("list:"+account+"/"+listName+" ");
		}
	}
	@Override
	public void loadXml() {
		// TODO Auto-generated method stub
		
	}

}
