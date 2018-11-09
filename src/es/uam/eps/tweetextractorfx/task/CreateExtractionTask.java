/**
 * 
 */
package es.uam.eps.tweetextractorfx.task;

import es.uam.eps.tweetextractorfx.dao.service.ExtractionService;
import es.uam.eps.tweetextractorfx.model.Extraction;
import javafx.concurrent.Task;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class CreateExtractionTask extends Task<Integer>{
	private Extraction extraction;
	/**
	 * 
	 */
	public CreateExtractionTask(Extraction extraction) {
		this.extraction=extraction;
	}

	@Override
	protected Integer call() throws Exception {
		if(this.extraction==null)return -1;
		ExtractionService extractionService = new ExtractionService();
		extractionService.persist(extraction);
		return 0;
	}

}
