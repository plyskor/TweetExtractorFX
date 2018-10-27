/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;


/**
 * @author Jose Antonio García del Saz
 *
 */

public final class Constants {
	public enum FilterTypes{
		FC(Values.TYPE_FILTER_CONTAINS), 
		FCE(Values.TYPE_FILTER_CONTAINS_EXACT),
		FOR(Values.TYPE_FILTER_OR), 	
		FNOT(Values.TYPE_FILTER_NOT), 		
		FHASH(Values.TYPE_FILTER_HASHTAG), 		
		FFROM(Values.TYPE_FILTER_FROM), 		
		FTO(Values.TYPE_FILTER_TO), 
		FLST(Values.TYPE_FILTER_LIST), 
		FMNT(Values.TYPE_FILTER_MENTION), 	
		FURL(Values.TYPE_FILTER_URL),		
		FSNC(Values.TYPE_FILTER_SINCE), 
		FUNTL(Values.TYPE_FILTER_UNTIL), 
		FATT(Values.TYPE_FILTER_ATTITUDE), 
		FQST(Values.TYPE_FILTER_QUESTION);
		private FilterTypes (String type) {
		     //if (!this.name().equals(type))
		       // throw new IllegalArgumentException("Incorrect use of FilterTypes");
		  }
		public static class Values{
			//Available filters
			public final static String TYPE_FILTER_CONTAINS="FC";
			public final static String TYPE_FILTER_CONTAINS_EXACT="FCE";
			public final static String TYPE_FILTER_OR="FOR";
			public final static String TYPE_FILTER_NOT="FNOT";
			public final static String TYPE_FILTER_HASHTAG="FHASH";
			public final static String TYPE_FILTER_FROM="FFROM";
			public final static String TYPE_FILTER_TO="FTO";
			public final static String TYPE_FILTER_LIST="FLST";
			public final static String TYPE_FILTER_MENTION="FMNT";
			public final static String TYPE_FILTER_URL="FURL";
			public final static String TYPE_FILTER_SINCE="FSNC";
			public final static String TYPE_FILTER_UNTIL="FUNTL";
			public final static String TYPE_FILTER_ATTITUDE="FATT";
			public final static String TYPE_FILTER_QUESTION="FQST";
		}
	}

			
	/*
	*  Generic Strings
	*/
			//Available filters
			public final static String STRING_FILTER_CONTAINS= "Contiene";
			public final static String STRING_FILTER_CONTAINS_EXACT= "Contiene literalmente";
			public final static String STRING_FILTER_HASHTAG= "Contiene el hashtag";
			public final static String STRING_FILTER_FROM= "Tweeteado por";
			public final static String STRING_FILTER_TO= "Respondiendo a";
			public final static String STRING_FILTER_LIST= "Tweeteado por cualquier cuenta de una lista";
			public final static String STRING_FILTER_MENTION= "Mencionando a";
			public final static String STRING_FILTER_URL= "Con una URL que contenga";
			public final static String STRING_FILTER_SINCE= "Desde la fecha";
			public final static String STRING_FILTER_UNTIL= "Hasta la fecha";
			public final static String STRING_FILTER_ATTITUDE= "Con una actitud";
			public final static String STRING_FILTER_QUESTION= "Contiene una o más preguntas";
	/*
	 * Classes
	 */
			public final static String CLASS_FILTER_CONTAINS= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterContains";
			public final static String CLASS_FILTER_CONTAINS_EXACT= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterContainsExact";
			public final static String CLASS_FILTER_OR= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterOr";
			public final static String CLASS_FILTER_NOT= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterNot";
			public final static String CLASS_FILTER_HASHTAG= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterHashtag";
			public final static String CLASS_FILTER_FROM= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterFrom";
			public final static String CLASS_FILTER_TO= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterTo";
			public final static String CLASS_FILTER_LIST= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterList";
			public final static String CLASS_FILTER_MENTION= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterMention";
			public final static String CLASS_FILTER_URL= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterUrl";
			public final static String CLASS_FILTER_SINCE= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterSince";
			public final static String CLASS_FILTER_UNTIL= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterUntil";
			public final static String CLASS_FILTER_ATTITUDE= "Con una actitud";
			public final static String CLASS_FILTER_QUESTION= "Contiene una o más preguntas";
			
	/*
	 * Paths
	 */
			public static final String PERSISTENCE_PATH = System.getProperty("user.home") + "/TweetExtractorFX/";
			public static final String AUTH_PATH = PERSISTENCE_PATH+".auth/";
			public static final String EXTRACTION_DATA_PATH = PERSISTENCE_PATH+".extractionData/";
	/*
	 * Files
	 */
			public static final String FILE_USERS = AUTH_PATH+"users.xml";

}
