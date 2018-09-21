/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import es.uam.eps.tweetextractorfx.util.DateAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * @author Jose Antonio García del Saz
 *
 */
@XmlType(propOrder={"nickname", "password", "creationDate","lastConnectionDate","credentials"})
public class User {
	private String nickname;
	private String password;
	private Date creationDate;
	private Date lastConnectionDate=null;
    // XmlElement sets the name of the entities
	private List<Credentials> credentialList;
	private ObservableList<Extraction> extractionList;
	private ObservableList<Extraction> extractionQueue;
	/**
	 * 
	 */
	public User(String nickname,String password) {
			this.setNickname(nickname);
			this.setPassword(password);
			creationDate=new Date();
			extractionList= FXCollections.observableArrayList(); 
			extractionQueue= FXCollections.observableArrayList();
			credentialList = new ArrayList<Credentials>();
	}
	public User() {
		extractionList= FXCollections.observableArrayList(); 
		extractionQueue= FXCollections.observableArrayList();
		credentialList = new ArrayList<Credentials>();
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the creationDate
	 */
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the lastConnectionDate
	 */
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getLastConnectionDate() {
		return lastConnectionDate;
	}
	/**
	 * @param lastConnectionDate the lastConnectionDate to set
	 */
	public void setLastConnectionDate(Date lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}
	/**
	 * @return the credentials
	 */
	@XmlElementWrapper(name = "credentialsList")
	public List<Credentials> getCredentials() {
		return credentialList;
	}
	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(List<Credentials> credentials) {
		this.credentialList = credentials;
	}
	/**
	 * @param credentials the credentials to add
	 */
	public void addCredentials(Credentials credentials) {
		if(credentials!=null) {
			this.credentialList.add(credentials);
		}
	}
	/**
	 * @return the extractionList
	 */
	public List<Extraction> getExtractionList() {
		return extractionList;
	}
	/**
	 * @param extractionList the extractionList to set
	 */
	public void setExtractionList(ObservableList<Extraction> extractionList) {
		this.extractionList = extractionList;
	}
	/**
	 * @return the extractionQueue
	 */
	public List<Extraction> getExtractionQueue() {
		return extractionQueue;
	}
	/**
	 * @param extractionQueue the extractionQueue to set
	 */
	public void setExtractionQueue(ObservableList<Extraction> extractionQueue) {
		this.extractionQueue = extractionQueue;
	}
	/**
	 * @param extraction the extraction to add to the list
	 */
	public void addExtractionToList(Extraction extraction) {
		if (extraction!=null) {
			this.getExtractionList().add(extraction);
		}
	}
	/**
	 * @param extraction the extraction to add to the queue
	 */
	public void addExtractionToQueue(Extraction extraction) {
		if(extraction!=null) {
			this.getExtractionQueue().add(extraction);
		}
	}
	
}
