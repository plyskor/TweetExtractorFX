package es.uam.eps.tweetextractorfx.twitterapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterExtractor {
	private ConfigurationBuilder cb;
	private TwitterFactory tf;
	private Twitter twitter;
	private Properties prop;
	private InputStream input;
	private Query query;

	public TwitterExtractor(String consulta) {
		super();
		if(consulta==null) {
			return;
		}
		/*Cargamos las confs de conexión*/
		prop = new Properties();
		try {
			input = TwitterExtractor.class.getResourceAsStream("twitter4j.properties");
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		/*Configuramos la API con nuestros datos*/
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(false).setOAuthConsumerKey(prop.getProperty("oauth.consumerKey"))
				.setOAuthConsumerSecret(prop.getProperty("oauth.consumerSecret"))
				.setOAuthAccessToken(prop.getProperty("oauth.accessToken")).setTweetModeExtended(true)
				.setOAuthAccessTokenSecret(prop.getProperty("oauth.accessTokenSecret"));
		/*Instanciamos la conexión*/
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
		query = new Query(consulta);
	}
	public Twitter getTwitter() {
		return twitter;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	public List<Status> execute(){
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
            System.out.println("Failed to search tweets: " + te.getMessage());
            return null;
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
}
