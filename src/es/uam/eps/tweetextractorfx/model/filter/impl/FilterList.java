/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
@XmlRootElement(name="filterList")
public class FilterList extends Filter {
	
	@XmlTransient
	private StringProperty listName= new SimpleStringProperty();
	@XmlTransient
	private StringProperty account= new SimpleStringProperty();
	private String listNameXml=new String("");
	private String accountXml=new String("");
	/**
	 * 
	 */
	public FilterList() {
		this.summary=new String("Enviado desde una cuenta en la lista ");
		this.setLABEL(Constants.STRING_FILTER_LIST);
	}
	public FilterList(FilterList filter) {
		this.summary=new String("Enviado desde una cuenta en la lista ");
		this.setLABEL(Constants.STRING_FILTER_LIST);
		if(filter!=null) {
			this.setAccountXml(filter.getAccountXml());
			this.setListNameXml(filter.getListNameXml());
			this.setListName(filter.getListName().get());
			this.setAccount(filter.getAccount().get());
			this.setSummary(filter.getSummary());
			this.summaryProperty.set(filter.getSummary());
		}
	}
	
	/**
	 * @return the listName
	 */
	@XmlTransient
	public StringProperty getListName() {
		return listName;
	}
	/**
	 * @param listName the listName to set
	 */
	public void setListName(String listName) {
		this.listName.set(listName);
		summary=summary.concat("'"+listName+"' de la cuenta '"+account.get()+"'");
		this.summaryProperty.set(summary);
		this.listNameXml=new String(listName);
		this.setAccountXml(this.getAccount().get());
	}
	/**
	 * @return the account
	 */
	@XmlTransient
	public StringProperty getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account.set(account);
	}

	
	/**
	 * @return the listNameXml
	 */
	@XmlElement(name="listName")
	public String getListNameXml() {
		return listNameXml;
	}
	/**
	 * @param listNameXml the listNameXml to set
	 */
	public void setListNameXml(String listNameXml) {
		this.listNameXml = listNameXml;
		this.setListName(listNameXml);
	}
	/**
	 * @return the accountXml
	 */
	@XmlElement(name="account")
	public String getAccountXml() {
		return accountXml;
	}
	/**
	 * @param accountXml the accountXml to set
	 */
	public void setAccountXml(String accountXml) {
		this.accountXml = accountXml;
		this.setAccount(accountXml);
	}
	/**
	 * @param listName the listName to set
	 */
	public void setListName(StringProperty listName) {
		this.listName = listName;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(StringProperty account) {
		this.account = account;
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
