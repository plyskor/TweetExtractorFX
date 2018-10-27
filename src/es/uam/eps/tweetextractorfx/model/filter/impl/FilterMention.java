/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.filter.impl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.filter.Filter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Jose Antonio García del Saz
 *
 */
@XmlRootElement(name = "filterMention")
public class FilterMention extends Filter {
	@XmlTransient
	private StringProperty summary = new SimpleStringProperty("");
	@XmlTransient
	private ObservableList<String> mentionList = FXCollections.observableArrayList();
	@XmlTransient
	private String summaryString = new String("Menciona a: ");
	private List<String> mentionXmlList = new ArrayList<String>();

	public FilterMention(FilterMention filter) {
		this.setLABEL(Constants.STRING_FILTER_MENTION);
		if (filter != null) {
			for (String word : filter.getMentionList()) {
				mentionList.add(word);
				mentionXmlList.add(word);
			}
			summaryString = filter.getSummary().get();
			summary.set(filter.getSummary().get());
		}
	}

	/**
	 * @return the mentionList
	 */
	@XmlTransient
	public ObservableList<String> getMentionList() {
		return mentionList;
	}

	/**
	 * @param mentionList the mentionList to set
	 */
	public void setMentionList(ObservableList<String> mentionList) {
		this.mentionList = mentionList;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary.set(summary);
	}

	public FilterMention() {
		this.setLABEL(Constants.STRING_FILTER_MENTION);
	}

	@XmlTransient
	@Override
	public StringProperty getSummary() {
		return summary;
	}

	public void addMention(String mention) {
		loadMention(mention);
		mentionXmlList.add(mention);
	}

	public void loadMention(String mention) {
		if (mentionList.isEmpty()) {
			summaryString = summaryString.concat("@" + mention);
			summary.set(summaryString);
		} else {
			summaryString = summaryString.concat(", @" + mention);
			summary.set(summaryString);
		}
		mentionList.add(mention);
	}

	@Override
	public String toQuery() {
		if (mentionList == null) {
			return null;
		} else {
			String ret = new String("");
			for (String mention : mentionList) {
				ret = ret.concat("@" + mention + " ");
			}
			return ret;
		}
	}

	/**
	 * @return the summaryString
	 */
	@XmlTransient
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
	 * @return the metionXmlList
	 */
	@XmlElementWrapper(name = "mentionList")
	@XmlElement(name = "mention")
	public List<String> getMentionXmlList() {
		return mentionXmlList;
	}

	/**
	 * @param metionXmlList the metionXmlList to set
	 */
	public void setMentionXmlList(List<String> mentionXmlList) {
		this.mentionXmlList = mentionXmlList;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(StringProperty summary) {
		this.summary = summary;
	}

	@Override
	public void loadXml() {
		for (String mention : mentionXmlList) {
			loadMention(mention);
		}
	}

}
