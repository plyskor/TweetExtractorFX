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
public class FilterTo extends Filter {
	private StringProperty nickName= new SimpleStringProperty();
 //new String("Respondiendo a: @");
	
	public FilterTo (FilterTo filter) {
		this.setLABEL(Constants.STRING_FILTER_TO);
		if(filter!=null) {
			summary=filter.getSummary();
			summaryProperty.set(filter.getSummary());
			this.nickName.set(filter.getNickName().get());
		}
	}

	/**
	 * 
	 */
	public FilterTo() {
		this.setLABEL(Constants.STRING_FILTER_TO);
	}

	/**
	 * @return the nickName
	 */
	public StringProperty getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName.set(nickName);
		summary=summary.concat(nickName);
		summaryProperty.set(summary);
	}

	@Override
	public String toQuery() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void loadXml() {
		// TODO Auto-generated method stub
		
	}

}
