package es.uam.eps.tweetextractorfx.twitterapi;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private HashMap<String,String> credentials;
	private Query query;

	public TwitterExtractor(String consulta,HashMap<String,String> credentials) {
		super();
		/*Configuramos la API con nuestros datos provisionales*/
		setCredentials(credentials);
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(false).setOAuthConsumerKey(this.credentials.get("consumerKey"))
		.setOAuthConsumerSecret(this.credentials.get("consumerSecret"))
		.setOAuthAccessToken(this.credentials.get("accessToken")).setTweetModeExtended(true)
		.setOAuthAccessTokenSecret(this.credentials.get("accessTokenSecret"));
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
	public List<Status> execute() throws TwitterException{
		List<Status> ret = new ArrayList<Status>();
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
            }
            //*RATELIMIT
            if(te.getStatusCode()==429&&te.getErrorCode()==88) {
            	handleRateLimit();
            }
            System.out.println("Failed to search tweets: " + te.getMessage());
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
	public void setCredentials(HashMap<String,String> credentials) {
		if(credentials==null){
			this.credentials = new HashMap<String,String>();
			this.credentials.put("consumerKey", "WHnn9ajf9fRiEjoQ400vJjR28");
			this.credentials.put("consumerSecret","YmtYa3xLn8bhix0mqq90We3ldVGfX2laqDlIhxY31X07Psz7Bp");
			this.credentials.put("accessToken","985480472896724997-9pXqJgxLfDseps3ZvVRaz2IQjtht13j");
			this.credentials.put("accessTokenSecret", "TWffQRGH4WwPnSgjCvlRVwHN9gpsbn1TelzktzSu2NHWa");
		}else {
			this.credentials = new HashMap<String,String>();
			this.credentials.put("consumerKey", credentials.get("consumerKey"));
			this.credentials.put("consumerSecret",credentials.get("consumerSecret"));
			this.credentials.put("accessToken",credentials.get("accessToken"));
			this.credentials.put("accessTokenSecret", credentials.get("accessTokenSecret"));
		}
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
