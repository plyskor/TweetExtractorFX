package es.uam.eps.tweetextractorfx.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Credentials {
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	
	private String accountScreenName;
	public Credentials() {
	}

	/**
	 * @return the consumerKey
	 */
	public String getConsumerKey() {
		return consumerKey;
	}

	/**
	 * @return the accountScreenName
	 */
	@XmlAttribute(name="acoountScreenName")
	public String getAccountScreenName() {
		return accountScreenName;
	}

	/**
	 * @param accountScreenName the accountScreenName to set
	 */
	public void setAccountScreenName(String accountScreenName) {
		this.accountScreenName = accountScreenName;
	}

	/**
	 * @param consumerKey the consumerKey to set
	 */
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	/**
	 * @return the consumerSecret
	 */
	public String getConsumerSecret() {
		return consumerSecret;
	}

	/**
	 * @param consumerSecret the consumerSecret to set
	 */
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the accessTokenSecret
	 */
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	/**
	 * @param accessTokenSecret the accessTokenSecret to set
	 */
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public boolean equals(Credentials credentials) {
		if(credentials==null)return false;
		return(accountScreenName.equals(credentials.getAccountScreenName())&&(consumerKey.equals(credentials.getConsumerKey())&&consumerSecret.equals(credentials.getConsumerSecret())&&accessToken.equals(credentials.getAccessToken())&&accessTokenSecret.equals(credentials.getAccessTokenSecret())));
	}

}
