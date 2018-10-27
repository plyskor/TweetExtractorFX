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
	private ObservableList<String> mentionList = FXCollections.observableArrayList();
	private List<String> mentionXmlList = new ArrayList<String>();
	public FilterMention(FilterMention filter) {
		this.summary=new String("Menciona a: ");
		this.setLABEL(Constants.STRING_FILTER_MENTION);
		if (filter != null) {
			for (String word : filter.getMentionList()) {
				mentionList.add(word);
				mentionXmlList.add(word);
			}
			summary= filter.getSummary();
			summaryProperty.set(filter.getSummary());
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


	public FilterMention() {
		this.summary=new String("Menciona a: ");
		this.setLABEL(Constants.STRING_FILTER_MENTION);
	}



	public void addMention(String mention) {
		loadMention(mention);
		mentionXmlList.add(mention);
	}

	public void loadMention(String mention) {
		if (mentionList.isEmpty()) {
			summary = summary.concat("@" + mention);
			summaryProperty.set(summary);
		} else {
			summary = summary.concat(", @" + mention);
			summaryProperty.set(summary);
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

	@Override
	public void loadXml() {
		for (String mention : mentionXmlList) {
			loadMention(mention);
		}
	}

}
