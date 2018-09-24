/**
 * 
 */
package es.uam.eps.tweetextractorfx.util;

import java.util.Date;

import es.uam.eps.tweetextractorfx.model.Extraction;
import es.uam.eps.tweetextractorfx.twitterapi.TwitterExtractor;
import javafx.concurrent.Task;
import twitter4j.TwitterException;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class UpdateExtractionTask extends Task<Integer>{
	private TwitterExtractor twitter;
	private Extraction extraction;
	
	public UpdateExtractionTask(TwitterExtractor twitter, Extraction extraction) {
		super();
		this.twitter = twitter;
		this.extraction = extraction;
	}

	@Override
	protected Integer call() throws Exception {
		Integer ret=0;
    	try {
			ret=twitter.updateExtraction(extraction);
			if(ret>0) {
				extraction.setLastModificationDate(new Date());
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
        return ret ;
	}

}
