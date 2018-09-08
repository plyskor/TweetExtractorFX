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
public class FilterList implements Filter {
	private final static Integer ID=Constants.INTEGER_FILTER_LIST;
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_LIST);
	private StringProperty listName= new SimpleStringProperty();
	private StringProperty account= new SimpleStringProperty();
	private StringProperty summary=new SimpleStringProperty();
	private String summaryString = new String("Enviado desde una cuenta en la lista ");
	/**
	 * 
	 */
	public FilterList() {
		// TODO Auto-generated constructor stub
	}
	public FilterList(FilterList filter) {
		if(filter!=null) {
			this.setListName(filter.getListName().get());
			this.setAccount(filter.getAccount().get());
			this.setSummary(filter.getSummary().get());
			this.setSummaryString(filter.getSummaryString());
		}
	}
	
	/**
	 * @return the listName
	 */
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
	}
	/**
	 * @return the account
	 */
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
	@Override
	public StringProperty getSummary() {
		return summary;
	}

}
