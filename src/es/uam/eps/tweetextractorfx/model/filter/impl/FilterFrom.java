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
public class FilterFrom implements Filter {
	@XmlTransient
	private final static Integer ID=Constants.INTEGER_FILTER_FROM;
	@XmlTransient
	private final static StringProperty LABEL=new SimpleStringProperty(Constants.STRING_FILTER_FROM);
	@XmlTransient
	private StringProperty nickName= new SimpleStringProperty();
	@XmlTransient
	private StringProperty summary=new SimpleStringProperty();
	@XmlTransient
	private String summaryString = new String("Tweeteado por: @");
	private String nickNameXml = new String("");

	public FilterFrom(FilterFrom filter) {
		if(filter!=null) {
			summaryString=filter.getSummary().get();
			summary.set(filter.getSummary().get());
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
		summaryString=summaryString.concat(nickName);
		summary.set(summaryString);
	}

	/**
	 * @return the summaryString
	 */
	@XmlTransient
	public String getSummaryString() {
		return summaryString;
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

	/**
	 * 
	 */
	public FilterFrom() {
		// TODO Auto-generated constructor stub
	}
	@XmlTransient
	@Override
	public StringProperty getSummary() {
		return summary;
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
