/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Jose Antonio García del Saz
 *
 */
public final class Constants {

	/*
	 * Integers
	 */
			//Available filters
			public final static Integer INTEGER_FILTER_CONTAINS=0;
			public final static Integer INTEGER_FILTER_CONTAINS_EXACT=1;
			public final static Integer INTEGER_FILTER_OR=2;
			public final static Integer INTEGER_FILTER_NOT_CONTAINS=3;
			public final static Integer INTEGER_FILTER_HASHTAG=4;
			public final static Integer INTEGER_FILTER_FROM=5;
			public final static Integer INTEGER_FILTER_TO=6;
			public final static Integer INTEGER_FILTER_LIST=7;
			public final static Integer INTEGER_FILTER_MENTION=8;
			public final static Integer INTEGER_FILTER_URL=9;
			public final static Integer INTEGER_FILTER_SINCE=10;
			public final static Integer INTEGER_FILTER_UNTIL=11;
			public final static Integer INTEGER_FILTER_ATTITUDE=12;
			public final static Integer INTEGER_FILTER_QUESTION=13;
	/*
	*  Strings
	*/
			//Available filters
			public final static String STRING_FILTER_CONTAINS= "Contiene";
			public final static String STRING_FILTER_CONTAINS_EXACT= "Contiene literalmente";
			public final static String STRING_FILTER_OR= "OR lógico";
			public final static String STRING_FILTER_NOT_CONTAINS= "No contiene";
			public final static String STRING_FILTER_HASHTAG= "Contiene el hashtag";
			public final static String STRING_FILTER_FROM= "Tweeteado por";
			public final static String STRING_FILTER_TO= "Respondiendo a";
			public final static String STRING_FILTER_LIST= "Tweeteado por una cuenta de una lista";
			public final static String STRING_FILTER_MENTION= "Mencionando a";
			public final static String STRING_FILTER_URL= "Contiene una URL que contenga";
			public final static String STRING_FILTER_SINCE= "Desde la fecha";
			public final static String STRING_FILTER_UNTIL= "Hasta la fecha";
			public final static String STRING_FILTER_ATTITUDE= "Con una actitud";
			public final static String STRING_FILTER_QUESTION= "Contiene una o más preguntas";
	/*
	 * Classes
	 */
			public final static String CLASS_FILTER_CONTAINS= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterContains";
			public final static String CLASS_FILTER_CONTAINS_EXACT= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterContainsExact";
			//public final static String CLASS_FILTER_OR= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterContains";
			//public final static String CLASS_FILTER_NOT_CONTAINS= "No contiene";
			public final static String CLASS_FILTER_HASHTAG= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterHashtag";
			public final static String CLASS_FILTER_FROM= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterFrom";
			public final static String CLASS_FILTER_TO= "Respondiendo a";
			public final static String CLASS_FILTER_LIST= "Tweeteado por una cuenta de una lista";
			public final static String CLASS_FILTER_MENTION= "Mencionando a";
			public final static String CLASS_FILTER_URL= "Contiene una URL que contenga";
			public final static String CLASS_FILTER_SINCE= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterSince";
			public final static String CLASS_FILTER_UNTIL= "es.uam.eps.tweetextractorfx.model.filter.impl.FilterUntil";
			public final static String CLASS_FILTER_ATTITUDE= "Con una actitud";
			public final static String CLASS_FILTER_QUESTION= "Contiene una o más preguntas";
}
