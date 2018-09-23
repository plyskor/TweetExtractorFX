package es.uam.eps.tweetextractorfx.twitterapi;


import java.util.ArrayList;
import java.util.List;

import es.uam.eps.tweetextractorfx.error.ErrorDialog;
import es.uam.eps.tweetextractorfx.model.Credentials;
import es.uam.eps.tweetextractorfx.model.Extraction;
import es.uam.eps.tweetextractorfx.model.Tweet;
import es.uam.eps.tweetextractorfx.util.FilterManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterExtractor {
	private ConfigurationBuilder cb;
	private TwitterFactory tf;
	private Twitter twitter;
	private Credentials credentials;
	private Query query;

	public TwitterExtractor(String consulta,Credentials credentials) {
		super();
		if(credentials==null)return;
		/*Configuramos la API con nuestros datos provisionales*/
		setCredentials(credentials);
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(false).setOAuthConsumerKey(this.credentials.getConsumerKey())
		.setOAuthConsumerSecret(this.credentials.getConsumerSecret())
		.setOAuthAccessToken(this.credentials.getAccessToken()).setTweetModeExtended(true)
		.setOAuthAccessTokenSecret(this.credentials.getAccessTokenSecret());
		/*Instanciamos la conexión*/
		 tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}
	public Twitter getTwitter() {
		return twitter;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(String query) {
		if(query!=null) {
			this.query = new Query(query);
		}
	}
	public List<Tweet> execute() throws TwitterException{
		List<Tweet> ret = new ArrayList<Tweet>();
		List<Status> statusList = getStatusListExecution();
		if(statusList==null)return ret;
		for(Status status : statusList) {
			ret.add(new Tweet(status));
		}
		return ret;
	}
	public List<Status> getStatusListExecution() throws TwitterException{
		List<Status>ret=new ArrayList<Status>();
		try {
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	ret.add(tweet);
                }
            } while ((query = result.nextQuery()) != null);
            return ret;
        } catch (TwitterException te) {
            te.printStackTrace();
            //*CONNECTION ISSUE
            if(te.getStatusCode()==-1&&te.getErrorCode()==-1) {
            handleConnectionIssue();
            }else 
            //*RATELIMIT
            if(te.getStatusCode()==429&&te.getErrorCode()==88) {
            	handleRateLimit();
            	if(ret!=null&&!ret.isEmpty()) {
            		return ret;
            	}
            }else {
            	ErrorDialog.showErrorTwitterExecution(te.getMessage());
                System.out.println("Failed to search tweets: " + te.getMessage());
            }
            throw(te);
        }
	}
	public TwitterFactory getTf() {
		return tf;
	}
	public void setTf(TwitterFactory tf) {
		this.tf = tf;
	}
	public ConfigurationBuilder getCb() {
		return cb;
	}
	public void setCb(ConfigurationBuilder cb) {
		this.cb = cb;
	}
	public void setCredentials(Credentials credentials) {
			this.credentials=credentials;
	}
	public void handleRateLimit() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atención");
		alert.setHeaderText("Límite de la cuenta");
		alert.setContentText(
				"El límite de la API ha sido alcanzado.\nEl límite se resetea dentro de "+this.limit("/search/tweets").getSecondsUntilReset()+" segundos.\nInténtelo más tarde.");
		alert.showAndWait();
		
		return;
	}
	public void handleConnectionIssue() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Atención");
        alert.setHeaderText("Error de conexión");
        alert.setContentText(
                      "Se ha producido un error no determinado conectándose a Twitter. Revise su configuración de red y reinténtelo");
        alert.showAndWait();
       
        return;
	}
	public int updateExtraction(Extraction extraction) throws TwitterException {
		if (extraction==null)return -1;
		int ret=0;
		this.setQuery(FilterManager.getQueryFromFilters(extraction.getFilterList()));
		List<Status>toAdd= getStatusListExecution();
		if(toAdd==null)return -1;
		for(Status status:toAdd) {
			if(!extraction.contains(status)) {
				extraction.addTweet(new Tweet(status));
				ret++;
			}
		}
		return ret;
	}
	public RateLimitStatus limit(String endpoint) {
		  try {
			  RateLimitStatus status = twitter.getRateLimitStatus().get(endpoint);
			return status;
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		  return null;
		}
}
