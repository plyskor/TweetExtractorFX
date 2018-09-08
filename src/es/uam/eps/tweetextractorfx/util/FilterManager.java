/**
 * 
 */
package es.uam.eps.tweetextractorfx.util;

import java.util.List;

import es.uam.eps.tweetextractorfx.model.Constants;
import es.uam.eps.tweetextractorfx.model.filter.Filter;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class FilterManager {

	/**
	 * 
	 */
	public FilterManager() {
		
	}
	public static boolean isFilterListLogic(List<Filter> filterList) {
		if(filterList==null) {
			return false;
		}else {
			boolean ret =true;
			for(Filter filter:filterList) {
				if(!filter.getClass().getCanonicalName().equals(Constants.CLASS_FILTER_NOT)&&!filter.getClass().getCanonicalName().equals(Constants.CLASS_FILTER_OR)) {
					ret=false;
				}
			}
			return ret;
		}
		
	}

}
