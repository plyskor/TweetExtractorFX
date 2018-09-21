/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.uam.eps.tweetextractorfx.util.DateAdapter;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.UserMentionEntity;

/**
 * Clase para encapsular los datos de la clase twitter4j.Status y guardarlos mejor en XML
 * @author Jose Antonio García del Saz
 *
 */
public class Tweet {
	private Date createdAt;
	private Date lastUpdated;
	private int favouriteCount;
	private GeoLocation geoLocation;
	private List<String> hashtagList;
	private long id;
	private String inReplyToScreenName;
	private long inReplyToStatusId;
	private String lang;
	private Tweet quotedTweet;
	private int retweetCount;
	private String source;
	private String text;
	private String userScreenName;
	private List<String> userMentions;
	private boolean possiblySensitive;
	private boolean favorited;
	private boolean retweet;
	private boolean retweeted;
	public Tweet(Status tweet) {
		if (tweet==null)return;
		lastUpdated= new Date();
		if(tweet.getCreatedAt()!=null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				createdAt=df.parse(df.format(tweet.getCreatedAt()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		this.favouriteCount=tweet.getFavoriteCount();
		if(tweet.getGeoLocation()!=null)this.geoLocation=new GeoLocation(tweet.getGeoLocation().getLatitude(), tweet.getGeoLocation().getLongitude());
		this.hashtagList= new ArrayList<String>();
		for(HashtagEntity entity : tweet.getHashtagEntities()) {
			hashtagList.add(entity.getText());
		}
		this.id=tweet.getId();
		if((tweet.getInReplyToScreenName())!=null)
		this.inReplyToScreenName= new String (tweet.getInReplyToScreenName());
		this.inReplyToStatusId=tweet.getInReplyToStatusId();
		if(tweet.getLang()!=null)
		this.lang=new String(tweet.getLang());
		if(tweet.getQuotedStatus()!=null) {
			this.quotedTweet= new Tweet (tweet.getQuotedStatus());
		}
		this.retweetCount=tweet.getRetweetCount();
		if (tweet.getSource()!=null)
		this.source=new String (tweet.getSource());
		if((tweet.getText())!=null)
		this.text = new String (tweet.getText());
		if(tweet.getUser().getScreenName()!=null)
		this.userScreenName = new String (tweet.getUser().getScreenName());
		this.userMentions= new ArrayList<String>();
		for(UserMentionEntity entity : tweet.getUserMentionEntities()) {
			this.userMentions.add(entity.getScreenName());
		}
		this.favorited=tweet.isFavorited();
		this.possiblySensitive=tweet.isPossiblySensitive();
		this.retweet=tweet.isRetweet();
		this.retweeted=tweet.isRetweeted();
	}
	
	/**
	 * @return the possiblySensitive
	 */
	public boolean isPossiblySensitive() {
		return possiblySensitive;
	}

	/**
	 * @param possiblySensitive the possiblySensitive to set
	 */
	public void setPossiblySensitive(boolean possiblySensitive) {
		this.possiblySensitive = possiblySensitive;
	}

	/**
	 * @return the lastUpdated
	 */
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the createdAt
	 */
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the favouriteCount
	 */
	public int getFavouriteCount() {
		return favouriteCount;
	}
	/**
	 * @param favouriteCount the favouriteCount to set
	 */
	public void setFavouriteCount(int favouriteCount) {
		this.favouriteCount = favouriteCount;
	}
	/**
	 * @return the geoLocation
	 */
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}
	/**
	 * @param geoLocation the geoLocation to set
	 */
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}
	/**
	 * @return the hashtagList
	 */
	@XmlElementWrapper(name = "hashtagList")
    @XmlElement(name = "hashtag")
	public List<String> getHashtagList() {
		return hashtagList;
	}
	/**
	 * @param hashtagList the hashtagList to set
	 */
	public void setHashtagList(List<String> hashtagList) {
		this.hashtagList = hashtagList;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the inReplyToScreenName
	 */
	public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}
	/**
	 * @param inReplyToScreenName the inReplyToScreenName to set
	 */
	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}
	/**
	 * @return the inReplyToStatusId
	 */
	public long getInReplyToStatusId() {
		return inReplyToStatusId;
	}
	/**
	 * @param inReplyToStatusId the inReplyToStatusId to set
	 */
	public void setInReplyToStatusId(long inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	/**
	 * @return the quotedTweet
	 */
	public Tweet getQuotedTweet() {
		return quotedTweet;
	}
	/**
	 * @param quotedTweet the quotedTweet to set
	 */
	public void setQuotedTweet(Tweet quotedTweet) {
		this.quotedTweet = quotedTweet;
	}
	/**
	 * @return the retweetCount
	 */
	public int getRetweetCount() {
		return retweetCount;
	}
	/**
	 * @param retweetCount the retweetCount to set
	 */
	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the userScreenName
	 */
	public String getUserScreenName() {
		return userScreenName;
	}
	/**
	 * @param userScreenName the userScreenName to set
	 */
	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}
	/**
	 * @return the userMentions
	 */
	@XmlElementWrapper(name = "userMentions")
    @XmlElement(name = "usermention")
	public List<String> getUserMentions() {
		return userMentions;
	}
	/**
	 * @param userMentions the userMentions to set
	 */
	public void setUserMentions(List<String> userMentions) {
		this.userMentions = userMentions;
	}
	/**
	 * @return the favorited
	 */
	public boolean isFavorited() {
		return favorited;
	}
	/**
	 * @param favorited the favorited to set
	 */
	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}
	/**
	 * @return the retweet
	 */
	public boolean isRetweet() {
		return retweet;
	}
	/**
	 * @param retweet the retweet to set
	 */
	public void setRetweet(boolean retweet) {
		this.retweet = retweet;
	}
	/**
	 * @return the retweeted
	 */
	public boolean isRetweeted() {
		return retweeted;
	}
	/**
	 * @param retweeted the retweeted to set
	 */
	public void setRetweeted(boolean retweeted) {
		this.retweeted = retweeted;
	}
	
}
