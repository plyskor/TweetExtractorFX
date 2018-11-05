/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class LoginState {
	private User user;
	private Integer state= Constants.UNKNOWN_LOGIN_ERROR;
	/**
	 * 
	 */
	public LoginState() {
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

}
