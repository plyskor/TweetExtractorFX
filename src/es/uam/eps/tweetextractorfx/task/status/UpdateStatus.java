/**
 * 
 */
package es.uam.eps.tweetextractorfx.task.status;

import java.util.List;

import es.uam.eps.tweetextractorfx.model.Tweet;
import javafx.scene.control.Alert;
import twitter4j.Status;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class UpdateStatus {
	private Integer nTweets;
	private Alert alert;
	List<Status> statusList=null;
	List<Tweet>tweetList=null;
	/**
	 * 
	 */
	public UpdateStatus(Integer nTweets,Alert alert) {
		this.nTweets=nTweets;
		this.alert=alert;
	}
	/**
	 * @return the nTweets
	 */
	public Integer getnTweets() {
		return nTweets;
	}
	/**
	 * @param nTweets the nTweets to set
	 */
	public void setnTweets(Integer nTweets) {
		this.nTweets = nTweets;
	}
	/**
	 * @return the alert
	 */
	public Alert getAlert() {
		return alert;
	}
	/**
	 * @param alert the alert to set
	 */
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	/**
	 * @return the statusList
	 */
	public List<Status> getStatusList() {
		return statusList;
	}
	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}
	/**
	 * @return the tweetList
	 */
	public List<Tweet> getTweetList() {
		return tweetList;
	}
	/**
	 * @param tweetList the tweetList to set
	 */
	public void setTweetList(List<Tweet> tweetList) {
		this.tweetList = tweetList;
	}
	public void incrementNTweets(){
		this.nTweets=this.nTweets+1;
	}

}
