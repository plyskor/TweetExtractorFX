/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class RegisterStatus {
	private Integer status=Constants.UNKNOWN_REGISTER_ERROR;
	private User user=null;
	/**
	 * 
	 */
	public RegisterStatus() {
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
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

}
