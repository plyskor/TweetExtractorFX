/**
 * 
 */
package es.uam.eps.tweetextractorfx.model.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import es.uam.eps.tweetextractorfx.model.Tweet;

/**
 * @author Jose Antonio García del Saz
 *
 */
@XmlRootElement(name = "tweets")
public class TweetListWrapper {
	private List<Tweet> users;

	/**
	 * @return the users
	 */
	@XmlElement(name="tweet")
	public List<Tweet> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<Tweet> users) {
		this.users = users;
	}
	
}
