/**
 * 
 */
package es.uam.eps.tweetextractorfx.model;

/**
 * @author Jose Antonio García del Saz
 *
 */
public class GeoLocation {
private double latitude;
private double longitude;
public GeoLocation(double latitude, double longitude) {
	super();
	this.latitude = latitude;
	this.longitude = longitude;
}
/**
 * @return the latitude
 */
public double getLatitude() {
	return latitude;
}
/**
 * @param latitude the latitude to set
 */
public void setLatitude(double latitude) {
	this.latitude = latitude;
}
/**
 * @return the longitude
 */
public double getLongitude() {
	return longitude;
}
/**
 * @param longitude the longitude to set
 */
public void setLongitude(double longitude) {
	this.longitude = longitude;
}

}
