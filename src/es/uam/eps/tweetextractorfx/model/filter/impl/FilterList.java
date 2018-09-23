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
public class FilterList implements Filter {
	@XmlTransient
	private final static Integer ID=Constants.INTEGER_FILTER_LIST;
	@XmlTransient
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_LIST);
	@XmlTransient
	private StringProperty listName= new SimpleStringProperty();
	@XmlTransient
	private StringProperty account= new SimpleStringProperty();
	@XmlTransient
	private StringProperty summary=new SimpleStringProperty();
	@XmlTransient
	private String summaryString = new String("Enviado desde una cuenta en la lista ");
	private String listNameXml=new String("");
	private String accountXml=new String("");
	/**
	 * 
	 */
	public FilterList() {
		// TODO Auto-generated constructor stub
	}
	public FilterList(FilterList filter) {
		if(filter!=null) {
			this.setAccountXml(filter.getAccountXml());
			this.setListNameXml(filter.getListNameXml());
			this.setListName(filter.getListName().get());
			this.setAccount(filter.getAccount().get());
			this.setSummary(filter.getSummary().get());
			this.setSummaryString(filter.getSummaryString());
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
		summaryString=summaryString.concat("'"+listName+"' de la cuenta '"+account.get()+"'");
		this.setSummary(summaryString);
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
	 * @return the summaryString
	 */
	@XmlTransient
	public String getSummaryString() {
		return summaryString;
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
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(StringProperty summary) {
		this.summary = summary;
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
	/**
	 * @return the id
	 */
	@XmlTransient
	public  Integer getId() {
		return ID;
	}
	/**
	 * @return the label
	 */
	@XmlTransient
	public  StringProperty getLabel() {
		return LABEL;
	}
	@XmlTransient
	@Override
	public StringProperty getSummary() {
		return summary;
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
