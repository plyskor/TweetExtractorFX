/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.uam.eps.tweetextractorfx.twitterapi.TwitterExtractor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class User {
	private TwitterExtractor twitterExtractor;
	private String nickname;
	private String password;
	private LocalDate creationDate;
	private LocalDate lastConnectionDate=null;
	private List<Map> credentials;
	private ObservableList<Extraction> extractionList;
	private ObservableList<Extraction> extractionQueue;
	/**
	 * 
	 */
	public User(String nickname,String password) {
			this.setNickname(nickname);
			this.setPassword(password);
			creationDate=LocalDate.now();
			twitterExtractor= new TwitterExtractor(null, null);
			extractionList= FXCollections.observableArrayList(); 
			extractionQueue= FXCollections.observableArrayList();
			credentials = new ArrayList<Map>();
	}
	/**
	 * @return the twitterExtractor
	 */
	public TwitterExtractor getTwitterExtractor() {
		return twitterExtractor;
	}
	/**
	 * @param twitterExtractor the twitterExtractor to set
	 */
	public void setTwitterExtractor(TwitterExtractor twitterExtractor) {
		this.twitterExtractor = twitterExtractor;
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
	public LocalDate getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the lastConnectionDate
	 */
	public LocalDate getLastConnectionDate() {
		return lastConnectionDate;
	}
	/**
	 * @param lastConnectionDate the lastConnectionDate to set
	 */
	public void setLastConnectionDate(LocalDate lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}
	/**
	 * @return the credentials
	 */
	public List<Map> getCredentials() {
		return credentials;
	}
	/**
	 * @param credentials the credentials to set
	 */
	public void setCredentials(List<Map> credentials) {
		this.credentials = credentials;
	}
	/**
	 * @param credentials the credentials to add
	 */
	public void addCredentials(Map<String,String> credentials) {
		if(credentials!=null) {
			this.credentials.add(credentials);
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
