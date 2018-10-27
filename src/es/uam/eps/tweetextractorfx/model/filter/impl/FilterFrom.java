/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

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
@XmlRootElement(name="filterFrom")
public class FilterFrom extends Filter {
	@XmlTransient
	private StringProperty nickName= new SimpleStringProperty();
	private String nickNameXml = new String("");

	public FilterFrom(FilterFrom filter) {
		this.summary=new String("Tweeteado por: @");
		this.setLABEL(Constants.STRING_FILTER_FROM);
		if(filter!=null) {
			summary=filter.getSummary();
			summaryProperty.set(filter.getSummary());
			this.nickName.set(filter.getNickName().get());
			this.setNickNameXml(filter.getNickNameXml());
		}
	}
	/**
	 * @return the nickName
	 */
	@XmlTransient
	public StringProperty getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName.set(nickName);
		this.nickNameXml=new String(nickName);
		summary=summary.concat(nickName);
		summaryProperty.set(summary);
	}


	/**
	 * @return the nickNameXml
	 */
	public String getNickNameXml() {
		return nickNameXml;
	}
	/**
	 * @param nickNameXml the nickNameXml to set
	 */
	public void setNickNameXml(String nickNameXml) {
		this.nickNameXml = nickNameXml;
		if(nickNameXml!=null)setNickName(nickNameXml);
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(StringProperty nickName) {
		this.nickName = nickName;
	}


	/**
	 * 
	 */
	public FilterFrom() {
		this.summary=new String("Tweeteado por: @");
		this.setLABEL(Constants.STRING_FILTER_FROM);
	}

	@Override
	public String toQuery() {
		if(nickName==null) {
			return null;
		}else {
			return new String("from:"+nickName+" ");
		}
	}
	@Override
	public void loadXml() {
		// TODO Auto-generated method stub
		
	}

}
